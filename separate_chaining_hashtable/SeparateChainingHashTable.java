package separateChainingHashTable;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<AnyType> {
	private static final int DEFAULT_TABLE_SIZE = 101;//Ĭ�ϱ�
	private List<AnyType>[] theLists;
	private int currentSize;

	/*
	 * ���췽��
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
	 * ����Ԫ��
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
	 * ɾ��Ԫ��
	 */
	public void remove(AnyType x) {
		List<AnyType> whichList = theLists[myHash(x)];
		if (whichList.contains(x)) {
			whichList.remove(x);
			currentSize--;
		}
	}
	
	/*
	 * ����Ԫ��
	 */
	public boolean contains(AnyType x) {
		List<AnyType> whichList = theLists[myHash(x)];
		return whichList.contains(x);
	}
	
	/*
	 * �ÿ�
	 */
	public void makeEmpty() {
		for (int i = 0;i<theLists.length;i++) {
			theLists[i].clear();
		}
		currentSize = 0;
	}
	
	/*
	 * ��String��hash
	 */
	public static int hash(String key,int tableSize) {
		int hashVal = 0;
		for (int i = 0;i < key.length();i++) {//Horner����
			hashVal = 37 * hashVal + key.charAt(i);
		}
		hashVal %= tableSize;
		if (hashVal < 0) {
			hashVal += tableSize;
		}
		return hashVal;
	}
	
	/*
	 * �����ʱ�������Ĵ�����
	 */
	private void reHash() {
		List<AnyType>[] oldList = theLists;
		theLists = new List[nextPrime(2 * theLists.length)];//�����µı�������HashTable
		for (int j = 0;j < theLists.length;j++) {
			theLists[j] = new LinkedList<>();
		}
		currentSize = 0;
		for (List<AnyType> list : oldList)  {//��ԭ���ֵ�����±�
			for (AnyType item : list) {
				insert(item);
			}
		}
	}
	
	/*
	 * hash����
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
	 * ����һ������Ϊ�����򷵻ظ����������������򷵻ظ���֮��ĵ�һ������
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
	 * �ж��Ƿ�Ϊ����
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
