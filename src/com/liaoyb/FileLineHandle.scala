package com.liaoyb

import java.io.{PrintWriter, File}

import scala.io.Source

/**
  * 文件中 字符串行的处理()
  */
object FileLineHandle {
  def main(args: Array[String]) {
    val filePath="E:\\test\\file_line_handle.txt"
    val file=new File(filePath)
    val handleResult=Source.fromFile(filePath).getLines().map(lineHandle).mkString(",\n")
    println(handleResult)


    //结果写入文件
    val resultFile=new File(file.getParentFile+"/lineHandle.txt")
    println(resultFile)
    resultFile.createNewFile()
    val writer = new PrintWriter(resultFile)
    writer.write(handleResult)
    writer.close()

  }

  //每行的处理,前后加上引号
  def lineHandle(line:String): String ={
    s""""$line"""".stripMargin
  }
}
