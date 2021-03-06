package adt.linkedList;

import java.util.ArrayList;


public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + next.size();
		}

	}

	@Override
	public T search(T element) {
		T retorno = null;
		if (next != null) {

			if (this.data == element) {
				retorno = this.data;
			} else {
				retorno = this.next.search(element);
			}
		}
		return retorno;

	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data == element) {
				this.data = this.next.data;
				this.next = this.next.next;
			} else {
				this.next.remove(element);
			}
		}
	}
	
	

	@Override
	public T[] toArray() {
		ArrayList<T> array = new ArrayList<T>();
		toArray(array, this);
		return (T[]) array.toArray(new Object[0]);
	}

	private void toArray(ArrayList<T> array, RecursiveSingleLinkedListImpl<T> node) {
		if (!node.isEmpty()) {
			array.add(node.data);
			toArray(array, node.next);
		}
		
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
