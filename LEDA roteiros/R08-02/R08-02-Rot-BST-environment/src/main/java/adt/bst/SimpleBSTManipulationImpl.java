package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals(tree1.getRoot(), tree2.getRoot());
	}

	private boolean equals(BTNode<T> root1, BTNode<T> root2) {
		if(root1 == null && root2 ==  null) return true;
		if(root1 == null || root2 == null) return false;
		if(!root1.equals(root2)) return false;
		return equals(root1.getLeft(), root2.getLeft()) && equals(root1.getRight(), root2.getRight());
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar(tree1.getRoot(),tree2.getRoot());
	}

	private boolean isSimilar(BTNode<T> root1, BTNode<T> root2) {
		if(root1 == null && root2 ==  null) return true;
		if(root1 == null || root2 == null) return false;
		return isSimilar(root1.getLeft(),root2.getLeft()) && isSimilar(root1.getRight(), root2.getRight());
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		BSTNode<T> element = tree.minimum();
		while (k > 1){
			if (element == null){
				return null;
			}
			element = tree.sucessor(element.getData());
			--k;
		}
		return element.getData();
	}

}
