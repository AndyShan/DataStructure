package avlTree;

import org.omg.CORBA.Any;

public class AvlTree<AnyType extends Comparable<? super AnyType>> {
	/*
	 * avl���ڵ��ڲ���
	 */
	private static class AvlNode<AnyType> {
		AvlNode(AnyType element) {
			this(element, null, null);
		}

		AvlNode(AnyType element, AvlNode<AnyType> lChild, AvlNode<AnyType> rChild) {
			this.element = element;
			this.lChild = lChild;
			this.rChild = rChild;
		}

		AnyType element;
		AvlNode<AnyType> lChild;
		AvlNode<AnyType> rChild;
		int height;
	}

	private AvlNode<AnyType> root;// ���ڵ�

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
	 * �������
	 */
	public AnyType findMax() {
		if (isEmpty()) {
			System.out.println("error");
		}
		return findMax(root).element;
	}

	/*
	 * ������С
	 */
	public AnyType findMin() {
		if (isEmpty()) {
			System.out.println("error");
		}
		return findMin(root).element;
	}

	/*
	 * ����
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/*
	 * �������avl��
	 */
	public void printTree() {
		printTree(root);
	}

	/*
	 * �ж����Ƿ�ƽ��
	 */
	public void checkBalance() {
		checkBalance(root);
	}

	/*
	 * ɾ��
	 */
	public void remove(AnyType x) {
		remove(x, root);
	}

	/*
	 * ����
	 */
	public void insert(AnyType x) {
		insert(x,root);
	}
	/*
	 * ��߶�
	 */
	private int height(AvlNode<AnyType> t) {
		return t == null ? -1 : t.height;
	}

	/*
	 * �������Ԫ��ʵ��
	 */
	private AvlNode<AnyType> findMax(AvlNode<AnyType> t) {
		if (t == null) {
			return t;
		}
		while (t.rChild != null) {
			t = t.rChild;
		}
		return t;
	}

	/*
	 * ������СԪ��ʵ��
	 */
	private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
		if (t == null) {
			return t;
		}
		while (t.lChild != null) {
			t = t.lChild;
		}
		return t;
	}

	/*
	 * �����㷨ʵ��
	 */
	private boolean contains(AnyType x, AvlNode<AnyType> t) {
		while (t != null) {
			int compareResult = x.compareTo(t.element);// ��x�뵱ǰ�ڵ�Ƚ�
			if (compareResult < 0) {// ��С�ڵ�ǰ�ڵ㣬����Ѱ��
				t = t.lChild;
			} else if (compareResult > 0) {// �����ڵ�ǰ�ڵ�����Ѱ��
				t = t.rChild;
			} else {
				return true;// ���ҳɹ�
			}
		}
		return false;
	}

	/*
	 * ����������avl��
	 */
	private void printTree(AvlNode<AnyType> t) {
		if (t != null) {
			printTree(t.lChild);
			System.out.println(t.element);
			printTree(t.rChild);
		}
	}

	private static final int ALLOWED_IMBALANCE = 1;// ƽ���������׼��߶Ȳ�Ϊ1
	/*
	 * ����ƽ��Ķ�����ƽ��ķ���
	 */

	private AvlNode<AnyType> balance(AvlNode<AnyType> t) {
		if (t == null) {
			return t;
		}
		if (height(t.lChild) - height(t.rChild) > ALLOWED_IMBALANCE) {
			if (height(t.lChild.lChild) >= height(t.lChild.rChild)) {
				t = rotateWithLeftChild(t);
			} else {
				t = doubleWithLeftChild(t);
			}
		} else if (height(t.rChild) - height(t.lChild) > ALLOWED_IMBALANCE) {
			if (height(t.rChild.rChild) >= height(t.rChild.lChild)) {
				t = rotateWithRightChild(t);
			} else {
				t = doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.lChild), height(t.rChild)) + 1;
		return t;
	}
	/*
	 * �����㷨
	 */
	private AvlNode<AnyType> insert(AnyType x,AvlNode<AnyType> t) {
		if (t == null) {
			return new AvlNode<AnyType>(x, null, null);
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {//����ڵ�С�ڵ�ǰ�ڵ�
			t.lChild = insert(x,t.lChild);
		}else if (compareResult > 0) {//����ڵ���ڵ�ǰ�ڵ�
			t.rChild = insert(x,t.rChild);
		}else {
			;//�ظ�Ԫ�أ������в���
		}
		return balance(t);
	}
	/*
	 * ɾ���㷨
	 */
	private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t) {
		if (t == null) {
			return t;// δ�ҵ�
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {// С�ڵ�ǰ�ڵ㣬��������Ѱ��
			t.lChild = remove(x, t.lChild);
		} else if (compareResult > 0) {// ���ڵ�ǰ�ڵ㣬��������Ѱ��
			t.rChild = remove(x, t.rChild);
		} else if (t.lChild != null && t.rChild != null) {// �ҵ��ڵ㣬�ҽڵ�����������
			t.element = findMin(t.rChild).element;// ����������Ѱ����С�ڵ㣬Ҳ��������������������򣩺�ĺ�̽ڵ��滻��ǰ�Ľڵ�
			t.rChild = remove(t.element, t.rChild);// �ڸýڵ�ԭ����λ��ɾ����
		} else {// �ҵ��ڵ��ҽڵ�ֻ��һ������
			t = (t.lChild != null) ? t.lChild : t.rChild;// �����Ӳ�Ϊ���������滻Ҫɾ���Ľڵ㣬��֮���Һ����滻
		}
		return balance(t);// ɾ������ܲ�ƽ�⣬����ƽ���㷨��������ƽ��
	}

	/*
	 * ���Avl���Ƿ�ƽ��
	 */
	private int checkBalance(AvlNode<AnyType> t) {
		if (t == null) {
			return -1;
		}
		if (t != null) {
			int hl = checkBalance(t.lChild);
			int hr = checkBalance(t.rChild);
			if (Math.abs(height(t.lChild) - height(t.rChild)) > 1 || height(t.lChild) != hl || height(t.rChild) != hr) {// �������������������
				System.out.println("��ƽ�⣡");
			}
		}
		return height(t);
	}

	/*
	 * LL�ͣ�����
	 */
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
		AvlNode<AnyType> k1 = k2.lChild;
		k2.lChild = k1.rChild;
		k1.rChild = k2;
		k2.height = Math.max(height(k2.lChild), height(k2.rChild)) + 1;// ���¸߶�
		k1.height = Math.max(height(k1.lChild), k2.height) + 1;
		return k1;
	}

	/*
	 * RR,�ҵ���
	 */
	private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1) {
		AvlNode<AnyType> k2 = k1.rChild;
		k1.rChild = k2.lChild;
		k2.lChild = k1;
		k1.height = Math.max(height(k1.lChild), height(k1.rChild)) + 1;
		k2.height = Math.max(height(k2.rChild), k1.height) + 1;
		return k2;
	}

	/*
	 * LR����˫��
	 */
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
		k3.lChild = rotateWithRightChild(k3.lChild);
		return rotateWithLeftChild(k3);
	}

	/*
	 * RL����˫��
	 */
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k1) {
		k1.rChild = rotateWithLeftChild(k1.rChild);
		return rotateWithRightChild(k1);
	}

}
