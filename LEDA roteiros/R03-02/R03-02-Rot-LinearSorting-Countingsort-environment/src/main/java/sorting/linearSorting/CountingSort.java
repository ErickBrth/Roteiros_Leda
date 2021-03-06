package sorting.linearSorting;


import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {

			int maior = array[leftIndex];
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if (array[i] > maior) {
					maior = array[i];
				}
			}
			int[] counting = new int[maior + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				counting[array[i]]++;
			}

			for (int i = 1; i < counting.length; i++) {
				counting[i] = counting[i] + counting[i - 1];
			}

			int[] ordered = new int[(rightIndex - leftIndex) + 1];

			for(int i = rightIndex; i >= leftIndex; i--) {
				ordered[counting[array[i]] - 1] = array[i];
				counting[array[i]] -= 1;
			}
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = ordered[i - leftIndex];
			}
		}

	}

}
