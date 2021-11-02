package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar
 * o pivô. Essa técnica consiste no seguinte: 1. Comparar o elemento mais a
 * esquerda, o central e o mais a direita do intervalo. 2. Ordenar os elementos,
 * tal que: A[left] < A[center] < A[right]. 3. Adotar o A[center] como pivô. 4.
 * Colocar o pivô na penúltima posição A[right-1]. 5. Aplicar o
 * particionamento considerando o vetor menor, de A[left+1] até A[right-1]. 6.
 * Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && array.length > 1 && array != null) {
			int index_pivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, index_pivot - 1);
			sort(array, index_pivot + 1, rightIndex);
		}
	}

	public int partition(T[] values, int left, int right) {
		int meio = (left + right) / 2;
		T a = values[left];
		T b = values[meio];
		T c = values[right];
		int medianaIndex = 0;

		if (a.compareTo(b) < 0) {
			if (b.compareTo(c) < 0) {
				medianaIndex = meio;

			} else if (a.compareTo(c) < 0) {
				medianaIndex = right;

			} else {
				medianaIndex = left;
			}
		} else if (c.compareTo(b) < 0) {
			medianaIndex = meio;
		} else if (c.compareTo(a) < 0) {
			medianaIndex = right;
		} else {
			medianaIndex = left;
		}
		
		Util.swap(values, medianaIndex, right);
		
		T pivot = values[right];
		int i = left-1;
		
		for (int j = left; j <= right-1; j++) {
			if (values[j].compareTo(pivot) <= 0) {
				i = i+1;
				Util.swap(values, i, j);
			}
		}
		Util.swap(values, i+1, right);
		return i+1;
	}

}
