package mergesort;

public class Test {
	public static void main(String args[]) {
		Comparable[] a = {2,3,5,1,4,8};
		MergeSort.sort(a);
		for (int i = 0;i<a.length;i++) {
			System.out.println(a[i]);
		}
	}
}
