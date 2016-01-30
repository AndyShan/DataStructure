package avlTree;

import org.omg.CORBA.Any;

public class AvlTree<AnyType extends Comparable<? super AnyType>> {
	/*
	 * avl树节点内部类
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

	private AvlNode<AnyType> root;// 根节点

	/*
	 * 置空
	 */
	public void makeEmpty() {
		root = null;
	}

	/*
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*
	 * 查找最大
	 */
	public AnyType findMax() {
		if (isEmpty()) {
			System.out.println("error");
		}
		return findMax(root).element;
	}

	/*
	 * 查找最小
	 */
	public AnyType findMin() {
		if (isEmpty()) {
			System.out.println("error");
		}
		return findMin(root).element;
	}

	/*
	 * 查找
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/*
	 * 按序输出avl树
	 */
	public void printTree() {
		printTree(root);
	}

	/*
	 * 判断树是否平衡
	 */
	public void checkBalance() {
		checkBalance(root);
	}

	/*
	 * 删除
	 */
	public void remove(AnyType x) {
		remove(x, root);
	}

	/*
	 * 增加
	 */
	public void insert(AnyType x) {
		insert(x,root);
	}
	/*
	 * 算高度
	 */
	private int height(AvlNode<AnyType> t) {
		return t == null ? -1 : t.height;
	}

	/*
	 * 查找最大元素实现
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
	 * 查找最小元素实现
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
	 * 查找算法实现
	 */
	private boolean contains(AnyType x, AvlNode<AnyType> t) {
		while (t != null) {
			int compareResult = x.compareTo(t.element);// 将x与当前节点比较
			if (compareResult < 0) {// 若小于当前节点，向左寻找
				t = t.lChild;
			} else if (compareResult > 0) {// 若大于当前节点向右寻找
				t = t.rChild;
			} else {
				return true;// 查找成功
			}
		}
		return false;
	}

	/*
	 * 中序遍历输出avl树
	 */
	private void printTree(AvlNode<AnyType> t) {
		if (t != null) {
			printTree(t.lChild);
			System.out.println(t.element);
			printTree(t.rChild);
		}
	}

	private static final int ALLOWED_IMBALANCE = 1;// 平衡二叉树的准许高度差为1
	/*
	 * 将不平衡的二叉树平衡的方法
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
	 * 插入算法
	 */
	private AvlNode<AnyType> insert(AnyType x,AvlNode<AnyType> t) {
		if (t == null) {
			return new AvlNode<AnyType>(x, null, null);
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {//插入节点小于当前节点
			t.lChild = insert(x,t.lChild);
		}else if (compareResult > 0) {//插入节点大于当前节点
			t.rChild = insert(x,t.rChild);
		}else {
			;//重复元素，不进行操作
		}
		return balance(t);
	}
	/*
	 * 删除算法
	 */
	private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t) {
		if (t == null) {
			return t;// 未找到
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {// 小于当前节点，向左子树寻找
			t.lChild = remove(x, t.lChild);
		} else if (compareResult > 0) {// 大于当前节点，向右子树寻找
			t.rChild = remove(x, t.rChild);
		} else if (t.lChild != null && t.rChild != null) {// 找到节点，且节点有两个孩子
			t.element = findMin(t.rChild).element;// 在右子树中寻找最小节点，也就是中序遍历（按序排序）后的后继节点替换当前的节点
			t.rChild = remove(t.element, t.rChild);// 在该节点原来的位置删掉它
		} else {// 找到节点且节点只有一个孩子
			t = (t.lChild != null) ? t.lChild : t.rChild;// 若左孩子不为空则将左孩子替换要删除的节点，反之用右孩子替换
		}
		return balance(t);// 删除后可能不平衡，调用平衡算法将二叉树平衡
	}

	/*
	 * 检查Avl树是否平衡
	 */
	private int checkBalance(AvlNode<AnyType> t) {
		if (t == null) {
			return -1;
		}
		if (t != null) {
			int hl = checkBalance(t.lChild);
			int hr = checkBalance(t.rChild);
			if (Math.abs(height(t.lChild) - height(t.rChild)) > 1 || height(t.lChild) != hl || height(t.rChild) != hr) {// 若不满足条件，则输出
				System.out.println("不平衡！");
			}
		}
		return height(t);
	}

	/*
	 * LL型，左单旋
	 */
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
		AvlNode<AnyType> k1 = k2.lChild;
		k2.lChild = k1.rChild;
		k1.rChild = k2;
		k2.height = Math.max(height(k2.lChild), height(k2.rChild)) + 1;// 更新高度
		k1.height = Math.max(height(k1.lChild), k2.height) + 1;
		return k1;
	}

	/*
	 * RR,右单旋
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
	 * LR，左双旋
	 */
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
		k3.lChild = rotateWithRightChild(k3.lChild);
		return rotateWithLeftChild(k3);
	}

	/*
	 * RL，左双旋
	 */
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k1) {
		k1.rChild = rotateWithLeftChild(k1.rChild);
		return rotateWithRightChild(k1);
	}

}
