package liftistHeap;

public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {
	private static class LeftistNode<AnyType> {
		LeftistNode(AnyType theElement) {
			this(theElement, null, null);
		}

		LeftistNode(AnyType theElement, LeftistNode<AnyType> lt, LeftistNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
			npl = 0;
		}

		AnyType element;// �ڵ��е�����
		LeftistNode<AnyType> left;
		LeftistNode<AnyType> right;
		int npl;// ��·����
	}

	private LeftistNode<AnyType> root;

	public LeftistHeap() {
		root = null;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public AnyType deleteMin() {
		if (isEmpty()) {
			System.out.println("error");
		}
		AnyType minItem = root.element;
		root = merge(root.left, root.right);
		return minItem;
	}

	public AnyType findMin() {
		if (isEmpty()) {
			System.out.println("error");
		}
		return root.element;
	}

	public void insert(AnyType x) {
		root = merge(new LeftistNode<>(x), root);
	}

	public void merge(LeftistHeap<AnyType> rhs) {
		if (this == rhs) {
			return;
		}
		root = merge(root, rhs.root);
		rhs.root = null;
	}

	/*
	 * ������������
	 */
	private static <AnyType> void swapChildren(LeftistNode<AnyType> t) {
		LeftistNode<AnyType> tmp = t.left;
		t.left = t.right;
		t.right = tmp;
	}

	private LeftistNode<AnyType> merge1(LeftistNode<AnyType> h1, LeftistNode<AnyType> h2) {
		if (h1.left == null) {// ��������Ϊ����h2�ӵ�������
			h1.left = h2;
		} else {
			h1.right = merge(h1.right, h2);//��������Ϊ����h1��h2merge
			if (h1.left.npl < h1.right.npl) {//����������ʽ�ѵ�����������������·�������ڵ�������������·������
				swapChildren(h1);//������������
				h1.npl = h1.npl + 1;
			}
		}
		return h1;
	}

	private LeftistNode<AnyType> merge(LeftistNode<AnyType> h1, LeftistNode<AnyType> h2) {
		if (h1 == null) {
			return h2;
		}
		if (h2 == null) {
			return h1;
		}
		if (h1.element.compareTo(h2.element) < 0) {
			return merge1(h1, h2);
		} else {
			return merge1(h2, h1);
		}
	}
}
