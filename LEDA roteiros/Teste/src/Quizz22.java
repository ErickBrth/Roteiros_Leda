public class Quizz22 {

	public int[] ordena(int[] A, int[] B) {
		int[] C = new int[A.length + B.length];
		int auxB = B.length-1;
		int auxA = 0;
		int i = 0;
		
		while (auxA < A.length && auxB >= 0) {
			if (auxB < 0 ) {
				C[i] = A[auxA];
				break;
			}else if(auxA > A.length){
				C[i] = B[auxA];
				break;
			}
			if (A[auxA] < B[auxB]) {
				C[i] = A[auxA];
				auxA++;
				i++;
			} else {
				C[i] = B[auxB ];
				auxB--;
				i++;
			}
			
			
		}
		while (auxA < A.length) {
			if (auxB < 0 ) {
				C[i] = A[auxA];
				auxB++;
				break;
			}else if(auxA > A.length){
				C[i] = B[auxA];
				break;
			}
			if (A[auxA] <= B[auxB]) {
				C[i] = A[auxA];
				auxA++;
				i++;
			} else {
				C[i] = B[auxB ];
				auxB--;
				i++;
			}
		}

		return C;
	}
}
