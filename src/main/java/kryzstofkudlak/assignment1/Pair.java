package kryzstofkudlak.assignment1;

public class Pair<K, V> {

	private K obj1;
	private V obj2;
	
	public Pair(K str1, V str2) {
		this.obj1 = str1;
		this.obj2 = str2;
	}
	
	public K getLeft() {
		return obj1;
	}
	
	public V getRight() {
		return obj2;
	}
	
}
