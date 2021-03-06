package com.ruhua.domain.common.base;

import java.io.Serializable;

public class KeyValue<K, V>
  implements Serializable
{
  private K key;
  private V value;

  public KeyValue(K key, V value)
  {
    this.key = key;
    this.value = value;
  }

  public KeyValue() {
  }

  public K getKey() {
    return this.key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return this.value;
  }

  public void setValue(V value) {
    this.value = value;
  }
}