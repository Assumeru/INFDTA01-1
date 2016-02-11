package hro.infdta011

import scala.io.Source
import hro.infdta011.treemap.UserMap

object Part1 {
  def main(args: Array[String]): Unit = {
    val users = readData(args(0))
    println(users.size)
    println(users.get(4).get.getPref(103))
  }

  def readData(file: String): UserMap = {
    var users = new UserMap();
    for(line <- Source.fromFile(file).getLines()) {
      val parts = line.split(",")
      if(parts.length == 3) {
        val id = parts(0).toInt
        val user = users.get(id).getOrElse({
          users + new User(id)
        })
        user.setPref(parts(1).toInt, parts(2).toFloat)
      }
    }
    users
  }
}