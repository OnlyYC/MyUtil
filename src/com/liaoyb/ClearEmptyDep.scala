
import java.io.File


def deleteFiles(currFile:File): Unit ={
  if (currFile.isFile){
    currFile.delete()
    println(s"删除文件:${currFile.getAbsolutePath}")
  }else{
    val listFiles=currFile.listFiles()
    listFiles.foreach(deleteFiles)
    currFile.delete()
    println(s"删除文件夹:${currFile.getAbsolutePath}")
  }
}


/**
  * maven本地仓库中下载失败的库
  */
def findErrorDownloadJarInRep(faFiles: File): List[File] = faFiles match {
  case dir if dir.isDirectory
    && faFiles.listFiles.exists(p => p.isFile && p.getName.endsWith(".xml"))
    && faFiles.listFiles.exists(p => p.isDirectory && (p.getName == "jars"||p.getName=="srcs"))
    => Nil
  case dir if dir.isDirectory
    && faFiles.listFiles.exists(p => p.isFile && p.getName.endsWith(".xml"))
    => dir::Nil
  case dir if dir.isDirectory
    => faFiles.listFiles.map(findErrorDownloadJarInRep).reduce(_:::_)
  case file if file.isFile
    => Nil
}


/**
  * 清理本地maven仓库（删除下载失败的库）
  */
val rootPath = "C:\\Users\\admin\\.ivy2\\cache"
val rootFile = new File(rootPath)
findErrorDownloadJarInRep(rootFile).foreach(deleteFiles)



