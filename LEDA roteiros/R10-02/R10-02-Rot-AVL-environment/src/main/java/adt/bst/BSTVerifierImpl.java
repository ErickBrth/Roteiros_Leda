package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {

	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}

	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		BSTNode<T> root = bst.getRoot();
		BSTNode<T> left = (BSTNode<T>) bst.getRoot().getLeft();
		BSTNode<T> right = (BSTNode<T>) bst.getRoot().getRight();

		return isBST(root, left, right);
	}

	private boolean isBST(BSTNode<?> root, BSTNode<?> left, BSTNode<?> right) {
		boolean isBst = false;
		if (root != null) {
			isBst = true;
		}

		if (left != null && ((T) root.getData()).compareTo((T) left.getData()) <= 0) {
			isBst = false;
		}

		if (right != null && ((T) root.getData()).compareTo((T) right.getData()) >= 0) {
			isBst = false;
		}

		return isBST((BSTNode) root.getLeft(), left, root) && isBST((BSTNode) root.getRight(), root, right);
	}

}
