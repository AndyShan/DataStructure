package binarySerachTree;

public class BinarySerachTree<AnyType extends Comparable<? super AnyType>> {
	/*
	 * 二叉搜索树节点内部类
	 */
	private static class BinaryNode<AnyType> {
		
		/*
		 * 构造方法
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

	private BinaryNode<AnyType> root;//二叉搜索树的根节点

	/*
	 * 构造方法
	 */
	public BinarySerachTree() {
		root = null;
	}

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
	 * 查找
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/*
	 * 查找最小
	 */
	public AnyType findMin() {
		if (isEmpty()) {
			System.out.println("二叉树为空");
		}
		return findMin(root).element;
	}

	/*
	 * 查找最大
	 */
	public AnyType findMax() {
		if (isEmpty()) {
			System.out.println("二叉树为空");
		}
		return findMax(root).element;
	}

	/*
	 * 插入
	 */
	public void insert(AnyType x) {
		root = insert(x, root);
	}
	
	/*
	 * 删除
	 */
	public void remove(AnyType x) {
		root = remove(x,root);
	}
	
	/*
	 * 按序输出树
	 */
	public void printTree(){
		if (isEmpty()) {
			System.out.println("空树");
		}else { 
			printTree(root);
		}
	}
	
	/*
	 * 查找方法的递归实现
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null) {
			return false;
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {//若小于当前节点，则向左子树搜索
			return contains(x, t.left);
		} else if (compareResult > 0) {//若大于当前节点则向右子树搜索
			return contains(x, t.right);
		} else {
			return true;//查找成功
		}
	}

	/*
	 * 查找树中最小元素的递归实现
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
	 * 查找树中最大元素的非递归实现
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
	 * 插入的实现
	 */
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
		if (t == null) {//遍历到空节点时新建节点插入
			return new BinaryNode<AnyType>(x, null, null);
		}
		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {//若有重复节点则不进行操作
			;
		}
		return t;
	}

	/*
	 * 删除的实现（复制删除法）
	 * 若被删除节点为叶节点则直接删除，若有一个子树则改变父节点的指针，若有两个子树则在右子树中寻找最小的节点替换
	 * 右子树中最小节点就是中序遍历后被删除节点的后继节点
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
		} else if (t.left != null && t.right != null) {//两个子树的情况
			t.element = findMin(t.right).element;//找到右子树中最小的
			t.right = remove(t.element, t.right);
		}else{
			t = (t.left != null) ? t.left:t.right;
		}
		return t;
	}
	
	/*
	 * 按序输出（升序）树中节点的值的实现
	 * 由于是二叉搜索树，所以按序输出其实就是中序遍历
	 */
	private void printTree(BinaryNode<AnyType> t) {
		if (t != null) {
			printTree(t.left);
			System.out.print(t.element);
			printTree(t.right);
		}
	}
	
}