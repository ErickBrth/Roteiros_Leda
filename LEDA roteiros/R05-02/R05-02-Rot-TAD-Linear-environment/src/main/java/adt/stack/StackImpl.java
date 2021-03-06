package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		T retorno = null;
		if (!isEmpty()) {
			retorno = array[top];	
		}
		 return retorno;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
			
		
	}

	@Override
	public boolean isFull() {
		return top == array.length - 1;
	
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new StackOverflowException();
			} else {
				top += 1;
				array[top] = element;
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			int aux = top;
			top -= 1;
			return array[aux];
		}

	}

}
