package quicksort;

public class QuickSort {
//	static int index = 0;

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int low, int high) {
		if (high < low) {
			return;
		}
//		index++;
//		System.out.println("\n分割第" + index + "次");
		int j = partition(a, low, high);
		int k = j - 1;
//		System.out.println("递归排序左半部分" + " low:" + low + " j-1:" + k);
		sort(a, low, j - 1);// 递归排序左半部分
		int m = j + 1;
//		System.out.println("递归排序左半部分" + " j+1:" + m + " high:" + high);
		sort(a, j + 1, high);// 递归排序右半部分
	}

	private static int partition(Comparable[] a, int low, int high) {
		int i = low, j = high + 1;// 左右扫描指针
		Comparable v = a[low];// 切分元素
		while (true) {//指针循环查找，直到左右指针重合
			System.out.println("\n-------main");
			while (i < high && less(a[++i], v)) {//左指针向右查找比切分元素a[o]小的元素
				System.out.println("1" + " i:" + i);
				if (i == high) {
					break;
				}
			}
			while (j > low && less(v, a[--j])) {//右指针向左查找比切分元素a[o]小的元素
				System.out.println("2" + " j:" + j);
				if (j == low) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
//			System.out.println("交换"+" i:"+i+" j:"+j);
			exch(a, i, j);
		}
//		System.out.println("main交换"+" i:"+i+" j:"+j);
		exch(a, low, j);
		return j;
	}

	private static void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
}
