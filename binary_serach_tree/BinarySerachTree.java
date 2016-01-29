package binarySerachTree;

public class BinarySerachTree<AnyType extends Comparable<? super AnyType>> {
	/*
	 * �����������ڵ��ڲ���
	 */
	private static class BinaryNode<AnyType> {
		
		/*
		 * ���췽��
		 */
		public BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}
		public BinaryNode(AnyType theElement, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
			this.element = theElement;
			this.left = left;
			this.right = right;
		}

		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;

	}

	private BinaryNode<AnyType> root;//�����������ĸ��ڵ�

	/*
	 * ���췽��
	 */
	public BinarySerachTree() {
		root = null;
	}

	/*
	 * �ÿ�
	 */
	public void makeEmpty() {
		root = null;
	}

	/*
	 * �ж��Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*
	 * ����
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/*
	 * ������С
	 */
	public AnyType findMin() {
		if (isEmpty()) {
			System.out.println("������Ϊ��");
		}
		return findMin(root).element;
	}

	/*
	 * �������
	 */
	public AnyType findMax() {
		if (isEmpty()) {
			System.out.println("������Ϊ��");
		}
		return findMax(root).element;
	}

	/*
	 * ����
	 */
	public void insert(AnyType x) {
		root = insert(x, root);
	}
	
	/*
	 * ɾ��
	 */
	public void remove(AnyType x) {
		root = remove(x,root);
	}
	
	/*
	 * ���������
	 */
	public void printTree(){
		if (isEmpty()) {
			System.out.println("����");
		}else { 
			printTree(root);
		}
	}
	
	/*
	 * ���ҷ����ĵݹ�ʵ��
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null) {
			return false;
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {//��С�ڵ�ǰ�ڵ㣬��������������
			return contains(x, t.left);
		} else if (compareResult > 0) {//�����ڵ�ǰ�ڵ���������������
			return contains(x, t.right);
		} else {
			return true;//���ҳɹ�
		}
	}

	/*
	 * ����������СԪ�صĵݹ�ʵ��
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t == null) {
			return null;
		} else if (t.left == null) {
			return t;
		}
		return findMin(t.left);
	}

	/*
	 * �����������Ԫ�صķǵݹ�ʵ��
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t != null) {
			while (t.right != null) {
				t = t.right;
			}
		}
		return t;
	}

	/*
	 * �����ʵ��
	 */
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
		if (t == null) {//�������սڵ�ʱ�½��ڵ����
			return new BinaryNode<AnyType>(x, null, null);
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {//�����ظ��ڵ��򲻽��в���
			;
		}
		return t;
	}

	/*
	 * ɾ����ʵ�֣�����ɾ������
	 * ����ɾ���ڵ�ΪҶ�ڵ���ֱ��ɾ��������һ��������ı丸�ڵ��ָ�룬������������������������Ѱ����С�Ľڵ��滻
	 * ����������С�ڵ�������������ɾ���ڵ�ĺ�̽ڵ�
	 */
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if (t == null) {
			return t;
		}

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0) {
			t.left = remove(x, t.left);
		} else if (compareResult > 0) {
			t.right = remove(x, t.right);
		} else if (t.left != null && t.right != null) {//�������������
			t.element = findMin(t.right).element;//�ҵ�����������С��
			t.right = remove(t.element, t.right);
		}else{
			t = (t.left != null) ? t.left:t.right;
		}
		return t;
	}
	
	/*
	 * ����������������нڵ��ֵ��ʵ��
	 * �����Ƕ��������������԰��������ʵ�����������
	 */
	private void printTree(BinaryNode<AnyType> t) {
		if (t != null) {
			printTree(t.left);
			System.out.print(t.element);
			printTree(t.right);
		}
	}
	
}