package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<>();
		this.last = (DoubleLinkedListNode<T>) this.head;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(),
						new DoubleLinkedListNode<>());
				head = newHead;
				last = newHead;
			} else {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
				if (getHead() instanceof DoubleLinkedListNode<?>) {
					newHead = (DoubleLinkedListNode<T>) head;
				}
				while (!newHead.next.isNIL()) {
					if (newHead.next instanceof DoubleLinkedListNode<?>) {
						newHead = (DoubleLinkedListNode<T>) newHead.getNext();
					}
				}

				newHead.setNext(new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), newHead));
				if (newHead.getNext() instanceof DoubleLinkedListNode<?>) {
					last = (DoubleLinkedListNode<T>) newHead.next;
				}

			}
		}
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) getHead();
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, auxHead, new DoubleLinkedListNode<>());

		newHead.next = head;
		newHead.previous = auxHead;
		auxHead.setPrevious(newHead);

		if (head.isNIL()) {
			last = newHead;
		}
		head = newHead;
	}

	@Override
	public void remove(T element) {
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) getHead();
		if (auxHead.data == element) {
			removeFirst();
		} else {
			auxHead = (DoubleLinkedListNode<T>) getHead();
			while (!auxHead.isNIL() && auxHead.data != element) {
				auxHead = (DoubleLinkedListNode<T>) auxHead.next;
			}
			if (!auxHead.isNIL()) {
				auxHead.previous.next = auxHead.next;
				((DoubleLinkedListNode<T>) auxHead.getNext()).setPrevious(auxHead.previous);

			}
		}
	}

	@Override
	public void removeFirst() {
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) getHead();
		if (!auxHead.isNIL()) {
			auxHead = (DoubleLinkedListNode<T>) auxHead.next;
			if (auxHead.isNIL()) {
				last = (DoubleLinkedListNode<T>) auxHead;
			} else {
					auxHead.setPrevious(new DoubleLinkedListNode<>());
			}
		}

	}

	@Override
	public void removeLast() {
		if (!last.isNIL()) {
			last = last.previous;
		}
		if (last.isNIL()) {
			head = last;
		} else {
			last.next = new DoubleLinkedListNode<>();
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
