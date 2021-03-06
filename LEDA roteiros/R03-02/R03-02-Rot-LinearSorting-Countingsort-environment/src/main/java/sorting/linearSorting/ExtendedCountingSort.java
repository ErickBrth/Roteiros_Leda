package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {

			int k = array[leftIndex];
			int menor = array[leftIndex];
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if (array[i] > k) {
					k = array[i];
				}else if (array[i] < menor) {
					menor = array[i];
				}
			}

			int[] counting = new int[(k-menor) + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				counting[array[i] - menor]++;
			}

			for (int i = 1; i < counting.length; i++) {
				counting[i] = counting[i] + counting[i - 1];
			}

			int[] ordered = new int[(rightIndex - leftIndex) + 1];

			for (int i = rightIndex; i >= leftIndex; i--) {
				ordered[counting[array[i] - menor] - 1] = array[i];
				counting[array[i] - menor] -= 1;
			}
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = ordered[i - leftIndex];
			}

		}
	}
}
