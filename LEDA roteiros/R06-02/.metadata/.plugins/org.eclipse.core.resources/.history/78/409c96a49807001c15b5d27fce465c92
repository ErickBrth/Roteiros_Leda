package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int cont = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			cont += 1;
			aux = aux.next;
		}
		return cont;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxHead = this.head;

		while (!auxHead.isNIL() && auxHead.data != element) {
			auxHead = auxHead.next;
		}

		return auxHead.data;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = this.head;

		if (auxHead.isNIL()) {
			auxHead.data = element;
			auxHead.next = new SingleLinkedListNode<>();
		} else {
			while (!auxHead.isNIL()) {
				auxHead = auxHead.next;
			}
			auxHead.data = element;
			auxHead.next = new SingleLinkedListNode<>();
		}
	}

	@Override
	public void remove(T element) {
		if (this.head.data == element) {
			this.head = this.head.next;
		} else {
			SingleLinkedListNode<T> auxHead = this.head;

			while (!auxHead.isNIL() && auxHead.data != element) {
				auxHead = auxHead.next;
			}

			if (!auxHead.isNIL()) {
				auxHead.data = auxHead.next.data;
				auxHead.next = auxHead.next.next;

			}
		}
	}

	@Override
	public T[] toArray() {
		SingleLinkedListNode<T> auxHead = this.head;
		T[] result = (T[]) new Comparable[this.size()];

		int i = 0;
		while (!auxHead.isNIL()) {
			result[i] = (T) auxHead.data;
			auxHead = auxHead.next;
			i++;
		}

		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
