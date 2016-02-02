package separateChainingHashTable;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {
	private static final int DEFAULT_TABLE_SIZE = 101;//默认表长
	private List<AnyType>[] theLists;
	private int currentSize;

	/*
	 * 构造方法
	 */
	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0;i<theLists.length;i++) {
			theLists[i] = new LinkedList<>();
		}
	}
	
	/*
	 * 插入元素
	 */
	public void insert(AnyType x) {
		List<AnyType> whichList = theLists[myHash(x)];
		if (!whichList.contains(x)) {
			whichList.add(x);
			if (++currentSize > theLists.length) {
				reHash();
			}
		}
	}
	
	/*
	 * 删除元素
	 */
	public void remove(AnyType x) {
		List<AnyType> whichList = theLists[myHash(x)];
		if (whichList.contains(x)) {
			whichList.remove(x);
			currentSize--;
		}
	}
	
	/*
	 * 查找元素
	 */
	public boolean contains(AnyType x) {
		List<AnyType> whichList = theLists[myHash(x)];
		return whichList.contains(x);
	}
	
	/*
	 * 置空
	 */
	public void makeEmpty() {
		for (int i = 0;i<theLists.length;i++) {
			theLists[i].clear();
		}
		currentSize = 0;
	}
	
	/*
	 * 对String的hash
	 */
	public static int hash(String key,int tableSize) {
		int hashVal = 0;
		for (int i = 0;i < key.length();i++) {//Horner法则
			hashVal = 37 * hashVal + key.charAt(i);
		}
		hashVal %= tableSize;
		if (hashVal < 0) {
			hashVal += tableSize;
		}
		return hashVal;
	}
	
	/*
	 * 插入表时表长不够的处理函数
	 */
	private void reHash() {
		List<AnyType>[] oldList = theLists;
		theLists = new List[nextPrime(2 * theLists.length)];//创建新的表长更长的HashTable
		for (int j = 0;j < theLists.length;j++) {
			theLists[j] = new LinkedList<>();
		}
		currentSize = 0;
		for (List<AnyType> list : oldList)  {//将原表的值加入新表
			for (AnyType item : list) {
				insert(item);
			}
		}
	}
	
	/*
	 * hash方法
	 */
	private int myHash(AnyType x) {
		int hashVal = x.hashCode();
		hashVal %= theLists.length;
		if (hashVal < 0) {
			hashVal += theLists.length;
		}
		return hashVal;
	}
	
	/*
	 * 传入一个数若为素数则返回该数，若不是素数则返回该数之后的第一个素数
	 */
	private static int nextPrime(int n) {
		if (n % 2 == 0) {
			n++;
		}
		for (; !isPrime(n); n += 2) {
			;
		}
		return n;
	}

	/*
	 * 判断是否为素数
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3) {
			return true;
		}
		if (n == 1 || n % 2 == 0) {
			return false;
		}
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
