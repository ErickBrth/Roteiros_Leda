package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(element != null){
			boolean full = true;
			int i = 0;
			while(i < this.table.length){

				int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element,i);

				if (this.table[hash] == null || this.table[hash].equals(deletedElement) || this.table[hash].equals(element)){
					this.table[hash] = element;
					full = false;
					this.elements ++;
					break;
				}

				i++;
			}
			if(full) {
				throw new HashtableOverflowException();
			} else {
				this.COLLISIONS += i;

			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int i = 0;

			while (i < this.table.length) {

				int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, i);

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
		int i = -1;
		int probe = 0;
		int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
		while (table[hash] != null && probe < size()) {
			if (table[hash].equals(element)) {
				return hash;
			}
				probe++;
				hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
			
		}
		return i;
	}

}
