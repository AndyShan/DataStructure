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
//		System.out.println("\n�ָ��" + index + "��");
		int j = partition(a, low, high);
		int k = j - 1;
//		System.out.println("�ݹ�������벿��" + " low:" + low + " j-1:" + k);
		sort(a, low, j - 1);// �ݹ�������벿��
		int m = j + 1;
//		System.out.println("�ݹ�������벿��" + " j+1:" + m + " high:" + high);
		sort(a, j + 1, high);// �ݹ������Ұ벿��
	}

	private static int partition(Comparable[] a, int low, int high) {
		int i = low, j = high + 1;// ����ɨ��ָ��
		Comparable v = a[low];// �з�Ԫ��
		while (true) {//ָ��ѭ�����ң�ֱ������ָ���غ�
			System.out.println("\n-------main");
			while (i < high && less(a[++i], v)) {//��ָ�����Ҳ��ұ��з�Ԫ��a[o]С��Ԫ��
				System.out.println("1" + " i:" + i);
				if (i == high) {
					break;
				}
			}
			while (j > low && less(v, a[--j])) {//��ָ��������ұ��з�Ԫ��a[o]С��Ԫ��
				System.out.println("2" + " j:" + j);
				if (j == low) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
//			System.out.println("����"+" i:"+i+" j:"+j);
			exch(a, i, j);
		}
//		System.out.println("main����"+" i:"+i+" j:"+j);
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
