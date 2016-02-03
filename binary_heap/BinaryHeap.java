package binary_heap;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;// 堆中元素个数
	private AnyType[] array;// 实现堆的数组

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
	 * 插入
	 */
	public void insert(AnyType x) {
		if (currentSize == array.length -1 ){
			enlargeArray(array.length*2 - 1); 
			int hole = ++currentSize;//新建节点作为最开始的空穴
			for (array[0] = x;x.compareTo(array[hole/2])<0;hole /= 2) {//当x小于当前空穴的根节点继续循环体
				array[hole] = array[hole / 2];//将空穴移至其根节点
			}
			array[hole] = x;//将x插入空穴
		}
	}
	
	/*
	 * 扩展堆长
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
		array[1] = array[currentSize--];//将最后一个跟放在空穴中，此时也就是根节点
		percolateDown(1);//以空穴为1开始下滤
		
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
	 * 下滤
	 */
	private void percolateDown(int hole) {
		int child;
		AnyType tmp = array[hole];//使tmp等于传入参数空穴的值，也就是需要确定位置的值
		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;//使child指向当前空穴的左节点
			if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {//找出当前空穴两个节点中较小的那个,若右节点小于左节点，则使子节点指向右节点
				child++;
			}
			if (array[child].compareTo(tmp) < 0) {//若当前空穴的子节点小于空穴的值，则将子节点至于根处，空穴下移到子树上
				array[hole] = array[child];
			} else {//若空穴的值小于子节点的值，停止下滤
				break;
			}
			array[hole] = tmp;//此时的空穴就是添加tmp的位置
		}
	}

}
