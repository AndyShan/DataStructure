package binary_heap;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;// ����Ԫ�ظ���
	private AnyType[] array;// ʵ�ֶѵ�����

	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	public BinaryHeap(int capacity) {
		currentSize = 0;
		array = (AnyType[]) new Comparable[capacity + 1];
	}
	
	public BinaryHeap(AnyType[] items) {
		currentSize = items.length;
		array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];		
		int i = 1;
		for (AnyType item:items) {
			array[i++] = item;
		}
		buildHeap();
	}

	/*
	 * ����
	 */
	public void insert(AnyType x) {
		if (currentSize == array.length -1 ){
			enlargeArray(array.length*2 - 1); 
			int hole = ++currentSize;//�½��ڵ���Ϊ�ʼ�Ŀ�Ѩ
			for (array[0] = x;x.compareTo(array[hole/2])<0;hole /= 2) {//��xС�ڵ�ǰ��Ѩ�ĸ��ڵ����ѭ����
				array[hole] = array[hole / 2];//����Ѩ��������ڵ�
			}
			array[hole] = x;//��x�����Ѩ
		}
	}
	
	/*
	 * ��չ�ѳ�
	 */
	private void enlargeArray(int newSize) {
		AnyType[] old = array;
		array = (AnyType[]) new Comparable[newSize];
		for (int i = 0;i<old.length;i++) {
			array[i] = old[i];
		}
	}

	public AnyType findMin() {
		if (isEmpty()) {
			System.out.println("error");
		}
		return array[1];
	}
	
	public AnyType deleteMin(){
		if (isEmpty()) {
			System.out.println("error");
		}
		AnyType minItem = findMin();
		array[1] = array[currentSize--];//�����һ�������ڿ�Ѩ�У���ʱҲ���Ǹ��ڵ�
		percolateDown(1);//�Կ�ѨΪ1��ʼ����
		
		return minItem;
	}
	private void buildHeap() {
		for (int i = currentSize/2;i>0;i--) {
			percolateDown(i);
		}
	}
	public boolean isEmpty() {
		return currentSize == 0;
	}
	/*
	 * ����
	 */
	private void percolateDown(int hole) {
		int child;
		AnyType tmp = array[hole];//ʹtmp���ڴ��������Ѩ��ֵ��Ҳ������Ҫȷ��λ�õ�ֵ
		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;//ʹchildָ��ǰ��Ѩ����ڵ�
			if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {//�ҳ���ǰ��Ѩ�����ڵ��н�С���Ǹ�,���ҽڵ�С����ڵ㣬��ʹ�ӽڵ�ָ���ҽڵ�
				child++;
			}
			if (array[child].compareTo(tmp) < 0) {//����ǰ��Ѩ���ӽڵ�С�ڿ�Ѩ��ֵ�����ӽڵ����ڸ�������Ѩ���Ƶ�������
				array[hole] = array[child];
			} else {//����Ѩ��ֵС���ӽڵ��ֵ��ֹͣ����
				break;
			}
			array[hole] = tmp;//��ʱ�Ŀ�Ѩ�������tmp��λ��
		}
	}

}
