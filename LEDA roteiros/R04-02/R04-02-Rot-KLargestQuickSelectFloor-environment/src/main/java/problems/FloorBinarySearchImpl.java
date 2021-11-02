package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array.length > 1 && array != null) {
			sort(array, 0, array.length - 1);
		}
		return floor(array, x, 0, array.length - 1);

	}

	private Integer floor(Integer[] array, Integer x, int left, int right) {
		Integer floor = null;

		if (left <= right && left >= 0 && right <= array.length) {
			int mediana = (left + right) / 2;

			if (x < mediana) {
				return null;
			}
			if (x.compareTo(array[mediana]) == 0) {
				floor = array[mediana];

			} else if (x.compareTo(array[mediana]) < 0) {
				if (array[mediana - 1].compareTo(x) < 0) {
					floor = array[mediana - 1];
				} else {
					floor = floor(array, x, left, mediana - 1);
				}
			} else if (mediana == array.length - 1) {
				floor = array[mediana];
			} else {
				floor = floor(array, x, mediana + 1, right);
			}
		}
		return floor;
	}

	private void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (rightIndex < array.length && leftIndex < rightIndex && array.length > 1 && array != null) {
			int index_pivot = this.partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, index_pivot - 1);
			sort(array, index_pivot + 1, rightIndex);
		}
	}

	private int partition(Integer[] array, int left, int right) {
		int range = right - left + 1;
		int rand_pivot_index = (int) (Math.random() * range) + left;

		// troca o valor aleatório escolhido com a primeira posição
		util.Util.swap(array, left, rand_pivot_index);

		int pivot = array[left];
		int i = left;

		for (int j = left + 1; j <= right; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				i += 1;
				util.Util.swap(array, i, j);
			}
		}

		// troca pivot (values[left]) com i.
		util.Util.swap(array, left, i);

		return i;
	}

}
