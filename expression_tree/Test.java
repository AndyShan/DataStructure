package binaryTree;

import java.util.Stack;
/*
 * ���ʽ����ʵ��
 * �����׺���ʽ�������ʽ�����������
 */
public class Test {
	/*
	 * �������ڵ��ڲ��ඨ��
	 */
	static class BinaryTreeNode {
		char emelemt;//Ԫ��
		BinaryTreeNode lChild;//����
		BinaryTreeNode rChild;//�Һ���

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
	 * ���ʽڵ�Ԫ�ط���
	 */
	static void visit(BinaryTreeNode bNode) {
		System.out.print(bNode.emelemt);
	}

	/*
	 * �������
	 */
	static void inorder(BinaryTreeNode bNode) {
		if (bNode != null) {
			inorder(bNode.getlChild());
			visit(bNode);
			inorder(bNode.getrChild());
		}
	}

	public static void main(String args[]) {
		char[] postFixExp = { '1', '2', '3', '*', '+', '4', '5', '*', '6','+','7','*','+' };//��׺���ʽ
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		int index = 0;
		while (index <= 12) {
			if (Character.isDigit(postFixExp[index])) {//�ж��Ƿ�Ϊ����
				BinaryTreeNode charNode = new BinaryTreeNode(postFixExp[index], null, null);//�������ֽ�������ΪԪ�صĵ��ڵ�
				stack.push(charNode);//���õ��ڵ�ѹջ
			} else {
				BinaryTreeNode expTree = new BinaryTreeNode(postFixExp[index], stack.pop(), stack.pop());//��������������½��������ڵ㣬����ջ������Ԫ�طֱ���Ϊ�Һ��Ӻ����ӣ��������Ϊ�ڵ�Ԫ��
				stack.push(expTree);
			}
			index++;
		}
		
		inorder(stack.peek());
	}
}
