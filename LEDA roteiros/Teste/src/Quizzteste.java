
public class Quizzteste {
	public int[] ordena(int[] A, int[] B) {
		int[] C = new int[A.length + B.length];
		int auxB = B.length;
		int auxA = 0;
		int i = 0;
		while (auxA < A.length && auxB >= B.length) {
			if (A[auxA] <= B[auxB - 1]) {
				C[i] = A[auxA];

			} else {
				C[i] = B[auxB - 1];
				auxB--;
				i++;
			}
		}
		while (auxA < A.length) {

			if (A[auxA] <= B[auxB - 1]) {
				C[i] = A[auxA];
				auxA++;
				i++;
			} else {
				C[i] = B[auxB - 1];
				auxB--;
				i++;
			}
		}

		while (auxB >= B.length) {
			C[i] = B[auxB - 1];
			auxB--;
		}
		return C;
	}
}
