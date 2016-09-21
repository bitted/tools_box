package com.cyou.test;

public class MyHashMap<K, V> {
	
	// initialization capacity
	private int capacity = 10;
	// total entities
	private int size = 0;
	private Entity<K, V>[] entities = null;

	@SuppressWarnings("unchecked")
	public MyHashMap() {
		entities = new Entity[capacity];
	}

	public void put(K key, V value) {
		if (key == null) {
			throw new RuntimeException("The key is null");
		}
		reHash();
		Entity<K, V> newEntity = new Entity<K, V>(key, value);
		put(newEntity, this.entities, this.capacity);
	}

	private void put(Entity<K, V> newEntity, Entity<K, V>[] entities, int capacity) {
		int index = newEntity.getKey().hashCode() % capacity;
		Entity<K, V> entity = entities[index];
		Entity<K, V> firstEntity = entities[index];
		if (entity == null) {
			entities[index] = newEntity;
			size++;
		} else {
			if (newEntity.getKey().equals(entity.getKey())) {// Find the same key for the first entity, if find then replace the
																// old value to new value
				newEntity.setNext(entity.getNext());
				newEntity.setPre(entity.getPre());
				if (entity.getNext() != null) {
					entity.getNext().setPre(newEntity);
				}
				entities[index] = newEntity;
			} else if (entity.getNext() != null) {
				while (entity.getNext() != null) {// Find the same key for all the next entity, if find then replace the old value
													// to new value
					entity = entity.getNext();
					if (newEntity.getKey().equals(entity.getKey())) {
						newEntity.setPre(entity.getPre());
						newEntity.setNext(entity.getNext());
						if (entity.getNext() != null) {
							entity.getNext().setPre(newEntity);
						}
						entities[index] = newEntity;
						return;
					}
				}
				// Cannot find the same key, then insert the new entity at the header
				newEntity.setNext(firstEntity);
				newEntity.setPre(firstEntity.getPre());
				firstEntity.setPre(newEntity);
				entities[index] = newEntity;
				size++;
			} else {
				// Cannot find the same key, then put the new entity in head
				newEntity.setNext(firstEntity);
				firstEntity.setPre(newEntity);
				entities[index] = newEntity;
				size++;
			}
		}
	}

	public V get(K key) {
		if (key == null) {
			throw new RuntimeException("The key is null");
		}
		int index = key.hashCode() % capacity;
		Entity<K, V> entity = entities[index];
		if (entity != null) {
			if (entity.getKey().equals(key)) {
				return entity.getValue();
			} else {
				entity = entity.getNext();
				while (entity != null) {
					if (entity.getKey().equals(key)) {
						return entity.getValue();
					}
					entity = entity.getNext();
				}
			}

		}
		return null;
	}

	public void remove(K key) {
		if (key == null) {
			throw new RuntimeException("The key is null");
		}
		int index = key.hashCode() % capacity;
		Entity<K, V> entity = entities[index];
		if (entity != null) {
			if (entity.getKey().equals(key)) {
				if (entity.getNext() != null) {// remove the first entity
					entity.getNext().setPre(entity.getPre());
					entities[index] = entity.getNext();
					entity = null;
				} else {// empty this index
					entities[index] = null;
				}
				size--;
			} else {
				entity = entity.getNext();
				while (entity != null) {
					if (entity.getKey().equals(key)) {
						if (entity.getNext() != null) {
							entity.getPre().setNext(entity.getNext());
							entity.getNext().setPre(entity.getPre());
							entity = null;
						} else {
							// release the found entity
							entity.getPre().setNext(null);
							entity = null;
						}
						size--;
						return;
					}
					entity = entity.getNext();
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < capacity; i++) {
			sb.append("index=").append(i).append("[");
			boolean hasEntity = false;
			Entity<K, V> entity = entities[i];
			if (entity != null) {
				hasEntity = true;
			}
			while (entity != null) {
				sb.append("[").append(entity.getKey()).append("=").append(entity.getValue()).append("]").append(",");
				entity = entity.getNext();
			}
			if (hasEntity) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]\n");
		}
		return sb.toString();
	}

	/**
	 * Simple re-hash strategy, if the size is bigger than capacity, then do re-hash action
	 */
	private void reHash() {
		if (size >= capacity) {
			int newCapacity = capacity * 2;
			@SuppressWarnings("unchecked")
			Entity<K, V>[] newEntities = new Entity[newCapacity];
			for (int i = 0; i < capacity; i++) {
				Entity<K, V> entity = entities[i];
				while (entity != null) {
					put(entity, newEntities, newCapacity);
					entity = entity.getNext();
				}
			}
			this.capacity = newCapacity;
			this.entities = newEntities;
		}
	}

	public static void main(String[] args) {
		MyHashMap<String, String> map = new MyHashMap<String, String>();
		map.put("one", "1");
		map.put("two", "2");
		map.put("three", "3");
		map.put("four", "4");
		map.put("five", "5");
		map.put("six", "6");
		map.put("seven", "7");
		map.put("eight", "8");
		map.put("nine", "9");
		map.put("ten", "10");
		System.out.println(map.get("one"));
		System.out.println(map.get("two"));
		System.out.println(map.get("three"));
		System.out.println(map.get("four"));
		System.out.println(map.get("five"));
		System.out.println(map.get("six"));
		System.out.println(map.get("seven"));
		System.out.println(map.get("eight"));
		System.out.println(map.get("nine"));
		System.out.println(map.get("ten"));
		System.out.println(map.toString());

		map.remove("nine");
		map.remove("three");
		System.out.println(map.get("one"));
		System.out.println(map.get("two"));
		System.out.println(map.get("three"));
		System.out.println(map.get("four"));
		System.out.println(map.get("five"));
		System.out.println(map.get("six"));
		System.out.println(map.get("seven"));
		System.out.println(map.get("eight"));
		System.out.println(map.get("nine"));
		System.out.println(map.get("ten"));
		System.out.println(map.toString());
	}
}

class Entity<K, V> {
	private K key;
	private V value;
	private Entity<K, V> pre;
	private Entity<K, V> next;

	public Entity(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Entity<K, V> getPre() {
		return pre;
	}

	public void setPre(Entity<K, V> pre) {
		this.pre = pre;
	}

	public Entity<K, V> getNext() {
		return next;
	}

	public void setNext(Entity<K, V> next) {
		this.next = next;
	}

}
