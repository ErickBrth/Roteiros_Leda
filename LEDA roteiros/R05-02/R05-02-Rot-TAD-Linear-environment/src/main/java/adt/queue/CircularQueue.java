package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}

		if (element != null) {
			if (this.tail == this.array.length - 1) {
				this.tail = -1;
			}
			if (this.head == -1) {
				this.head++;
			}
			this.array[++this.tail] = element;
			this.elements++;
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T result = this.array[this.head];

		if (this.head == this.array.length - 1) {
			this.head = -1;
		}
		if (this.head == -1) {
			this.head++;
		}

		this.head++;
		this.elements--;
		return result;
	}

	@Override
	public T head() {
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;	
		}
	}

