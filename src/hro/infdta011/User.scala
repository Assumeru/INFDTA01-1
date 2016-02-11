package hro.infdta011

import hro.infdta011.treemap.TreeMap

class User(val id: Int) {
  private var preferences: TreeMap[Int, Float] = null

  def setPref(id: Int, score: Float) =
    if(preferences == null) {
      preferences = new TreeMap[Int, Float](id, score)
    } else {
      preferences = preferences + (id, score)
    }

  def getPref(id: Int): Option[Float] =
    if(preferences == null) {
      None
    } else {
      preferences.get(id)
    }
}