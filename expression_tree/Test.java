package binaryTree;

import java.util.Stack;
/*
 * 表达式树的实现
 * 输入后缀表达式构建表达式树并中序输出
 */
public class Test {
	/*
	 * 二叉树节点内部类定义
	 */
	static class BinaryTreeNode {
		char emelemt;//元素
		BinaryTreeNode lChild;//左孩子
		BinaryTreeNode rChild;//右孩子

		public BinaryTreeNode(char emelemt, BinaryTreeNode rChild, BinaryTreeNode lChild) {
			this.emelemt = emelemt;
			this.lChild = lChild;
			this.rChild = rChild;
		}

		public char getEmelemt() {
			return emelemt;
		}

		public void setEmelemt(char emelemt) {
			this.emelemt = emelemt;
		}

		public BinaryTreeNode getlChild() {
			return lChild;
		}

		public void setlChild(BinaryTreeNode lChild) {
			this.lChild = lChild;
		}

		public BinaryTreeNode getrChild() {
			return rChild;
		}

		public void setrChild(BinaryTreeNode rChild) {
			this.rChild = rChild;
		}

	}

	/*
	 * 访问节点元素方法
	 */
	static void visit(BinaryTreeNode bNode) {
		System.out.print(bNode.emelemt);
	}

	/*
	 * 中序遍历
	 */
	static void inorder(BinaryTreeNode bNode) {
		if (bNode != null) {
			inorder(bNode.getlChild());
			visit(bNode);
			inorder(bNode.getrChild());
		}
	}

	public static void main(String args[]) {
		char[] postFixExp = { '1', '2', '3', '*', '+', '4', '5', '*', '6','+','7','*','+' };//后缀表达式
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		int index = 0;
		while (index <= 12) {
			if (Character.isDigit(postFixExp[index])) {//判断是否为数字
				BinaryTreeNode charNode = new BinaryTreeNode(postFixExp[index], null, null);//若是数字建立数字为元素的单节点
				stack.push(charNode);//将该单节点压栈
			} else {
				BinaryTreeNode expTree = new BinaryTreeNode(postFixExp[index], stack.pop(), stack.pop());//若是运算符，则新建二叉树节点，弹出栈顶两个元素分别作为右孩子和左孩子，以运算符为节点元素
				stack.push(expTree);
			}
			index++;
		}
		
		inorder(stack.peek());
	}
}
