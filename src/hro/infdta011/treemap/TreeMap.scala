package hro.infdta011.treemap

import scala.annotation.tailrec

class TreeMap[K <% Ordered[K], V] private (private val key: K, private val value: V, private val left: TreeMap[K, V], private val right: TreeMap[K, V], val size: Int) {
  def this(key: K, value: V) = this(key, value, null, null, 1);

  def +(key: K, value: V): TreeMap[K, V] = TreeMap.transform(TreeMap.add(key, value, this))

  def -(key: K): TreeMap[K, V] = TreeMap.transform(TreeMap.remove(key, this))

  def get(key: K): Option[V] = TreeMap.get(key, this)

  def contains(key: K): Boolean = get(key).isDefined

  def isEmpty: Boolean = size == 0
}

object TreeMap {
  private def transform[K <% Ordered[K], V](map: TreeMap[K, V]): TreeMap[K, V] = {
    if(map != null) {
      val d = size(map.left) - size(map.right)
      if(d > 1) {
        return add(map.key, map.value, add(map.left, map.right))
      } else if(d < -1) {
        return add(map.key, map.value, add(map.right, map.left))
      }
    }
    map
  }

  private def get[K <% Ordered[K], V](key: K, map: TreeMap[K, V]): Option[V] =
    if (map == null || map.isEmpty) {
      None
    } else if (key == map.key) {
      Some(map.value)
    } else if (key < map.key) {
      get(key, map.left)
    } else {
      get(key, map.right)
    }

  private def add[K <% Ordered[K], V](key: K, value: V, map: TreeMap[K, V]): TreeMap[K, V] =
    if (map == null || map.isEmpty) {
      new TreeMap[K, V](key, value)
    } else if (key == map.key) {
      new TreeMap[K, V](key, value, map.left, map.right, map.size)
    } else if (key < map.key) {
      new TreeMap[K, V](map.key, map.value, add(key, value, map.left), map.right, map.size + 1)
    } else {
      new TreeMap[K, V](map.key, map.value, map.left, add(key, value, map.right), map.size + 1)
    }

  private def add[K <% Ordered[K], V](parent: TreeMap[K, V], child: TreeMap[K, V]): TreeMap[K, V] =
    if(child == null || child.isEmpty) {
      parent
    } else {
      add(add(add(child.key, child.value, parent), child.left), child.right)
    }

  private def remove[K <% Ordered[K], V](key: K, map: TreeMap[K, V]): TreeMap[K, V] =
    if (map == null || map.isEmpty || !map.contains(key)) {
      map
    } else if (map.key == key) {
      if (map.size == 1) {
        new TreeMap[K, V](key, map.value, null, null, 0)
      } else if(size(map.left) < size(map.right)) {
        add(map.right, map.left)
      } else {
        add(map.left, map.right)
      }
    } else if (key < map.key) {
      new TreeMap[K, V](map.key, map.value, remove(key, map.left), map.right, map.size - 1)
    } else {
      new TreeMap[K, V](map.key, map.value, map.left, remove(key, map.right), map.size - 1)
    }

  private def size[K <% Ordered[K], V](map: TreeMap[K, V]): Int = if (map == null) 0 else map.size
}