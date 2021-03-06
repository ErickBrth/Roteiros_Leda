package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot(), -1);
	}

	private int height(BSTNode<T> node, int height) {
		if (!node.isEmpty()) {

			int left = height((BSTNode<T>) node.getLeft(), height + 1);
			int right = height((BSTNode<T>) node.getRight(), height + 1);
			height = Math.max(left, right);

		}

		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> nodeResult = new BSTNode<T>();

		if (!this.isEmpty()) {

			nodeResult = this.search(this.getRoot(), element);

		}

		return nodeResult;

	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> nodeResult= new BSTNode<T>();

		if (!node.isEmpty()) {

			if (node.getData().compareTo(element) < 0) {

				nodeResult = this.search((BSTNode<T>) node.getRight(), element);

			} else if (node.getData().compareTo(element) > 0) {

				nodeResult = this.search((BSTNode<T>) node.getLeft(), element);

			} else {

				nodeResult = node;

			}

		}

		return nodeResult;
	}

	@Override
	public void insert(T element) {
		this.insert(this.getRoot(), element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {

			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());

		} else if (node.getData().compareTo(element) < 0) {

			this.insert((BSTNode<T>) node.getRight(), element);

		} else if (node.getData().compareTo(element) > 0) {

			this.insert((BSTNode<T>) node.getLeft(), element);

		}
	}

	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> nodeResult = null;

		if (!this.isEmpty()) {
			nodeResult = this.maximum(this.getRoot());
		}
		return nodeResult;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {

			node = this.maximum((BSTNode<T>) node.getRight());
		}
		return node;
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> nodeResult = null;
		if (!this.isEmpty()) {
			nodeResult = this.minimum(this.getRoot());
		}
		return nodeResult;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			node = this.minimum((BSTNode<T>) node.getLeft());
		}
		return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element), nodeSucessor = null;

		if (!node.isEmpty()) {

			if (node.getRight().isEmpty()) {
				nodeSucessor = (BSTNode<T>) node.getParent();

				while (nodeSucessor != null && nodeSucessor.getData().compareTo(node.getData()) < 0) {
					node = nodeSucessor;
					nodeSucessor = (BSTNode<T>) node.getParent();
				}
			} else {
				nodeSucessor = this.minimum((BSTNode<T>) node.getRight());
			}
		}
		return nodeSucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element), nodePredecessor = null;

		if (!node.isEmpty()) {

			if (node.getLeft().isEmpty()) {
				nodePredecessor = (BSTNode<T>) node.getParent();

				while (nodePredecessor != null && nodePredecessor.getData().compareTo(node.getData()) > 0) {
					node = nodePredecessor;
					nodePredecessor = (BSTNode<T>) node.getParent();
				}
			} else {
				nodePredecessor = this.maximum((BSTNode<T>) node.getLeft());
			}
		}
		return nodePredecessor;

	}
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);

			} else if (node.getRight().isEmpty() || node.getLeft().isEmpty()) {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				if (parent != null) {
					if (!parent.getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							parent.setRight(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setRight(node.getRight());
							node.getRight().setParent(parent);
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							parent.setLeft(node.getLeft());
							node.getLeft().setParent(parent);
						} else {
							parent.setLeft(node.getRight());
							node.getRight().setParent(parent);
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> list = new ArrayList<T>();
		preOrder(list, this.getRoot());

		return (T[]) list.toArray(new Comparable[this.size()]);
	}

	private void preOrder(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			list.add(node.getData());
			preOrder(list, (BSTNode<T>) node.getLeft());
			preOrder(list, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] order() {
		ArrayList<T> list = new ArrayList<T>();
		Order(list, this.getRoot());

		return (T[]) list.toArray(new Comparable[this.size()]);
	}

	private void Order(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			Order(list, (BSTNode<T>) node.getLeft());
			list.add(node.getData());
			Order(list, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> list = new ArrayList<T>();
		postOrder(list, this.getRoot());

		return (T[]) list.toArray(new Comparable[this.size()]);
	}

	private void postOrder(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(list, (BSTNode<T>) node.getLeft());
			postOrder(list, (BSTNode<T>) node.getRight());
			list.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // inductive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
