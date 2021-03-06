package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isFull()) {
				throw new HashtableOverflowException();
			}
			if (search(element) == null) {
				int probe = 0;
				int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
				while (probe < size() && table[hash] != null && !table[hash].toString().equals("D")) {
					super.COLLISIONS++;
					probe++;
					hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
				}

				table[hash] = element;
				super.elements++;

			}

		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {

			int i = 0;

			while (i < this.table.length) {
				int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);

				if (this.table[hash] == null) {
					break;
				} else if (this.table[hash].equals(element)) {
					this.table[hash] = this.deletedElement;
					this.elements--;
					break;
				}
				i++;
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null && !isEmpty()) {
			if (indexOf(element) != -1) {
				result = element;
			}
		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;

		if (element != null) {

			int iterator = 0;

			while (iterator < this.table.length) {

				int hash = ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, iterator);

				if (this.table[hash] == null) {
					break;
				} else if (this.table[hash].equals(element)) {
					index = hash;
					break;
				}

				iterator++;

			}

		}
		return index;

	}
}
