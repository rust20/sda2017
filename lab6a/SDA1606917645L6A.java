import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SDA1606917645L6A {
	public static void main (String[] args) throws IOException{
		BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter printer = new PrintWriter(new OutputStreamWriter(System.out));


		String tmpInput = null;
		String [] input = null;

		PriorityQueue<Integer> queue = new PriorityQueue<>();

		while((tmpInput = scanner.readLine()) != null){
			input = tmpInput.split(" ");
			int value = 0;
			if (input.length > 1) {
				value = Integer.valueOf(input[1]);
			}

			switch(input[0]){
				case "INPUT":
					queue.add(value);
					printer.println("elemen dengan nilai " + value + " telah ditambahkan");
					break;
				case "REMOVE":
					if (queue.poll() != null)
						printer.println(value + " dihapus dari heap");
					else
						printer.println("min heap kosong");
					break;
				case "NUM_PERCOLATE_UP":
					printer.println("percolateup");
					break;
				case "NUM_PERCOLATE_DOWN":
					printer.println("percolatedown");
					break;
				case "NUM_ELEMENT":
					printer.println("element " + queue.size());
					break;
			}
		}
		printer.flush();
	}
}

class PriorityQueue<E extends Comparable<E>>{
	ArrayList<E> list = null;
	ArrayList<Integer> percolateUpNum;
	ArrayList<Integer> percolateDownNum;
	private int size;

	public PriorityQueue(){
		this.size = 0;
		list = new ArrayList<E>();
		percolateUpNum = new ArrayList<Integer>();
		percolateDownNum = new ArrayList<Integer>();
	}

	public boolean add(E element){
		list.add(element);
		percolateUp(size-1);
		size++;
		return true;
	}

	public E poll(){
		if (isEmpty()) return null;

		E temp = list.get(0);
		swap(0, size-1);
		percolateDown(0);
		size--;

		return temp;
	}

	public E peek(){
		return list.get(0);
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public ArrayList<E> toArrayList(){
		return new ArrayList<E>(list);
	}

	private boolean isOutOfBound(int index){
		return index < 0 || index >= size;
	}

	private int getParent(int index){
		if (isOutOfBound(index)) return -1;
		else return index/2;
	}

	private int getRightChild(int index){
		if (isOutOfBound(index)) return -1;
		else return (index + 1) * 2;
	}

	private int getLeftChild(int index){
		if (isOutOfBound(index)) return -1;
		else return index * 2 + 1;
	}

	private void percolateUp(int index){
		if (index != 0 && !isOutOfBound(index)) {
			int parentIdx = getParent(index);
			E current = list.get(index);
			E parent = list.get(parentIdx);
			if (current.compareTo(parent) < 0) {
				swap(index, parentIdx);
				percolateUp(parentIdx);
			}
		}
	}

	private void percolateDown(int index){
		if (index != 0 && !isOutOfBound(index)) {
			int leftChildIdx = getLeftChild(index);
			int rightChildIdx = getRightChild(index);
			E current = list.get(index);
			E leftChild = list.get(leftChildIdx);
			E rightChild = list.get(rightChildIdx);

			if (current.compareTo(leftChild) < 0 && current.compareTo(rightChild) < 0) {
				// do nothing
			} else if (leftChild.compareTo(current) < 0 && leftChild.compareTo(rightChild) < 0) {
				swap(index, leftChildIdx);
				percolateDown(leftChildIdx);
			} else if (rightChild.compareTo(leftChild) < 0 && rightChild.compareTo(current) < 0) {
				swap(index, rightChildIdx);
				percolateDown(rightChildIdx);
			}
		}

	}

	private boolean swap(int idx1, int idx2){
		if (isOutOfBound(idx1) || isOutOfBound(idx2)) return false;

		E element1 = list.get(idx1);
		E element2 = list.get(idx2);
		list.set(idx1, element2);
		list.set(idx2, element1);
		return true;
	}
}
