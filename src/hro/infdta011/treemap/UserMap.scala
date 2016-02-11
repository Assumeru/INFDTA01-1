package hro.infdta011.treemap

import hro.infdta011.User

class UserMap {
  var map: TreeMap[Int, User] = null

  def +(user: User): User = {
    if(map == null) {
      map = new TreeMap[Int, User](user.id, user)
    } else {
      map = map + (user.id, user)
    }
    user
  }

  def -(user: User) =
    if(map != null) {
      map = map - user.id
    }

  def get(id: Int): Option[User] =
    if(map == null) {
      None
    } else {
      map.get(id)
    }

  def size = if(map == null) 0 else map.size
}