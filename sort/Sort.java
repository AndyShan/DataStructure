package sort;

public class Sort {
	/*
	 * 插入排序
	 */
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {
		int j;
		for (int p = 1; p < a.length; p++) {//默认有序区起始有一个元素，从数组的第二个元素开始插入排序
			AnyType tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {//当要插入的元素小于前面的元素时
				a[j] = a[j - 1];//前面的元素后移
			}
			a[j] = tmp;//将元素插入
		}
	}
	
	/*
	 * 希尔排序
	 * 使用shell增量
	 */
	public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) {
		int j;
		for (int gap = a.length / 2;gap > 0;gap /= 2) {//改变增量
			for (int i = gap;i<a.length;i++) {
				AnyType tmp = a[i];
				for (j = i;j >= gap && tmp.compareTo(a[j - gap]) < 0;j -= gap) {
					a[j] = a[j - gap];
				}
				a[j] = tmp;
			}
		}
	}
	
}
