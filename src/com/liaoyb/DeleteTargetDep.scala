
import java.io.File


def deleteFileInDir(currFile: File): Unit = {
  if (currFile.isFile) {
    currFile.delete()
    println(s"删除文件:${currFile.getAbsolutePath}")
  } else {
    val listFiles = currFile.listFiles()
    listFiles.foreach(deleteFileInDir)
    currFile.delete()
    println(s"删除文件夹:${currFile.getAbsolutePath}")
  }
}

def deleteTargetDirectory(faFiles: File): Unit = {
  faFiles match {
    case f if f.isDirectory && f.getName.equals("target") => deleteFileInDir(f)
    case otherDir if otherDir.isDirectory => faFiles.listFiles().foreach(deleteTargetDirectory)
    case _ =>
  }
}

/**
  * 清理源码项目中的target文件夹
  */
val rootPath = "E:\\code"
val rootFile = new File(rootPath)
deleteTargetDirectory(rootFile);





