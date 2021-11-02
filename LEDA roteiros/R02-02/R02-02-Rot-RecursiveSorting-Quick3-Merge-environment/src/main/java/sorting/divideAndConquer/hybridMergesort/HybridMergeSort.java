package sorting.divideAndConquer.hybridMergesort;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * sguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes: - Ter
 * contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo .teste - A cada chamado do
 * método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados. - O InsertionSort utilizado no
 * algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort algorithm
	 * will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length > 1 && leftIndex < rightIndex && array != null) {
			MERGESORT_APPLICATIONS = 0;
			INSERTIONSORT_APPLICATIONS = 0;
			// confere se o apode usar o insertionsort at� o tamanho limite
			if (rightIndex <= leftIndex + SIZE_LIMIT - 1) {
				insertionSort(array, leftIndex, rightIndex);
				
			} else {
				int mid = leftIndex + (rightIndex - leftIndex) / 2;
				sort(array, leftIndex, mid);
				sort(array, mid + 1, rightIndex);
				merge(array, leftIndex, rightIndex);
				
			}
		}
	}

	private T[] merge(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS++;
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
		// se a metade inicial n�o foi toda consumida, adiciona ao fim do array.
		while (left <= middle) {
			array[k] = (T) helper[left];
			left++;
			k++;

		}

		// se a metade final n�o foi toda consumida, adiciona ao fim do array.
		while (right <= rightIndex) {
			array[k] = (T) helper[right];
			right++;
			k++;
		}
		
		return helper;
	}

	private void insertionSort(T[] array, int left, int right) {
		INSERTIONSORT_APPLICATIONS++;
		for (int i = left + 1; i <= right; i++) {
			T key = array[i];
			int j = i - 1;
			
			while (j >= left && array[j].compareTo(key) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
		
	}
}
