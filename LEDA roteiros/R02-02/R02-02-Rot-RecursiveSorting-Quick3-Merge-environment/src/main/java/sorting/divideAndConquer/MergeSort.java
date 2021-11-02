package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length > 1 && leftIndex < rightIndex && array != null) {

			int middle = (leftIndex + rightIndex) / 2;

			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);

			merge(array, leftIndex, rightIndex);
		}
	}

	private T[] merge(T[] array, int leftIndex, int rightIndex) {
		// transfere os elementos entre left e right para um array auxiliar.

		T[] helper = Arrays.copyOf(array, array.length);

		int left = leftIndex;
		int middle = (leftIndex + rightIndex) / 2;
		int right = middle + 1;
		int k = left;

		while (left <= middle && right <= rightIndex) {

			if (helper[left].compareTo(helper[right]) <= 0) {
				array[k] = (T) helper[left];
				left++;

			} else {
				array[k] = (T) helper[right];
				right++;

			}
			k++;

		}
		// se a metade inicial não foi toda consumida, adiciona ao fim do array.
		while (left <= middle) {
			array[k] = (T) helper[left];
			left++;
			k++;

		}

		// se a metade final não foi toda consumida, adiciona ao fim do array.
		while (right <= rightIndex) {
			array[k] = (T) helper[right];
			right++;
			k++;
		}
		return helper;
	}

}
