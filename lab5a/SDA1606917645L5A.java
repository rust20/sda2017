import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

public class SDA1606917645L5A {
	static BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));
	public static void main (String[] args) throws IOException {

		String [] inputs;
		String tmpInput = null;

		Tree<String> tree = new Tree<>();

		while((tmpInput = scan.readLine()) != null){
			inputs = tmpInput.split(";");
			boolean status;
			List<String> list = null; if (inputs.length < 1) continue;
			switch(inputs[0]){
				case "ADD":
					status = tree.add(inputs[1]);
					if (status){
						print.println(inputs[1] + " berhasil ditambahkan ke dalam tree");
					} else {
						print.println(inputs[1] + " sudah dimasukkan sebelumnya");
					}
					break;
				case "REMOVE":
					status = tree.remove(inputs[1]);
					if (status){
						print.println(inputs[1] + " berhasil dihapus dari tree");
					} else {
						print.println(inputs[1] + " tidak ditemukan");
					}
					break;
				case "CONTAINS":
					status = tree.contains(inputs[1]);
					if (status){
						print.println(inputs[1] + " terdapat pada tree");
					} else {
						print.println(inputs[1] + " tidak terdapat pada tree");
					}
					break;
				case "PREORDER":
					list = tree.preOrder();
					if (list != null)
						print.println(printList(list));
					else
						print.println("Tidak ada elemen pada tree");
					break;
				case "POSTORDER":
					list = tree.postOrder();
					if (list != null)
						print.println(printList(list));
					else
						print.println("Tidak ada elemen pada tree");
					break;
				case "ASCENDING":
					list = tree.inOrderAscending();
					if (list != null)
						print.println(printList(list));
					else
						print.println("Tidak ada elemen pada tree");
					break;
				case "DESCENDING":
					list = tree.inOrderDescending();
					if (list != null)
						print.println(printList(list));
					else
						print.println("Tidak ada elemen pada tree");
					break;
				case "MAX":
					if (tree.max() != null)
						print.println(tree.max() + " merupakan elemen dengan nilai tertinggi");
					else
						print.println("Tidak ada elemen pada tree");
					break;
				case "MIN":
					if (tree.max() != null)
						print.println(tree.min() +  " merupakan elemen dengan nilai terendah");
					else
						print.println("Tidak ada elemen pada tree");
					break;
			}
			print.flush();
		}
		// print.flush();
	}

	public static String printList(List<String> list){
		String res = "";
		for(int i = 0; i < list.size()-1; i++){
			res += list.get(i) + ";";
		}
		res += list.get(list.size()-1);
		return res;
	}
}


class Tree<E extends Comparable<E>>{

	private Node<E> root;
	public Tree(){
		root = null;
	}
	public boolean isEmpty(){
		return root == null;
	}

	public boolean add(E elem){
		if (root == null) {
			root = new Node<>(elem , null, null);
			return true;
		} else {
			return add(elem, root, null);
		}
	}

	public boolean add(E elem, Node<E> current, Node<E> prev){
		if (current == null) {
			if (elem.compareTo(prev.elem) < 0)
				prev.left = new Node<>(elem, null, null);
			else
				prev.right = new Node<>(elem, null, null);
			return true;
		} else if (elem.compareTo(current.elem) < 0){
			return add(elem, current.left, current);
		} else if (elem.compareTo(current.elem) > 0){
			return add(elem, current.right, current);
		} else {
			return false;
		} }

	public boolean remove(E elem){
		if (root == null) {
			return false;
		} else {
			return remove(elem, root, null);
		}
	}

	private boolean remove(E elem, Node<E> current, Node<E> prev){
		if (current == null){
			return false;
		} else if (elem.compareTo(current.elem) < 0){
			return remove(elem, current.left, current);
		} else if (elem.compareTo(current.elem) > 0){
			return remove(elem, current.right, current);
		} else {
			if (current.elem.compareTo(root.elem) == 0){
				Node<E> tempNode = minNode(current.right, null);
				remove(minNode(current.right, null).elem);
				root.elem = tempNode.elem;
			} else if (current.left == null && current.right == null){
				if(elem.compareTo(prev.elem) < 0)
					prev.left = null;
				else
					prev.right = null;
			} else if (current.left == null || current.right == null){
				if(elem.compareTo(prev.elem) < 0) {
					prev.left = (current.left != null)?current.left:current.right;
				} else {
					prev.right = (current.left != null)?current.left:current.right;
				}
			} else {
				Node<E> tempNode = minNode(current.right, null);
				remove(minNode(current.right, null).elem);
				if(elem.compareTo(prev.elem) < 0) {
					prev.left.elem = tempNode.elem;
				} else {
					prev.right.elem = tempNode.elem;
				}
			}
			return true;
		}
	}

	// public void removeMin (Node<E> current, Node<E> prev){
		// if (current == null) prev.left = null;
		// else removeMin(current.left, current);
	// }

	public E min(){
		if (root == null) return null;
		return minNode(root, null).elem;
	}
	private Node<E> minNode(Node<E> current, Node<E> prev){
		if (current == null) return prev;
		else return minNode(current.left, current);
	}
	public E max(){
		if (root == null) return null;
		return maxNode(root, null).elem;
	}
	private Node<E> maxNode(Node<E> current, Node<E> prev){
		if(current == null) return prev;
		else return maxNode(current.right, current);
	}
	public boolean contains(E elem){
		if (root == null) return false;
		Node<E> current = root;
		boolean found = false;
		while(current != null){
			if (elem.compareTo(current.elem) < 0){
				current = current.left;
			} else if(elem.compareTo(current.elem) > 0){
				current = current.right;
			} else {
				found = true;
				break;
			}
		}
		return found;
	}
	public List<E> preOrder(){
		if (root == null) return null;
		List<E> list = new LinkedList<>();
		preOrder(root, list);
		return list;
	}
	private void preOrder(Node<E> current, List<E> list){
		if (current != null){
			list.add(current.elem);
			preOrder(current.left, list);
			preOrder(current.right, list);
		}
	}

	public List<E> postOrder(){
		if (root == null) return null;
		List<E> list = new LinkedList<>();
		postOrder(root, list);
		return list;
	}
	private void postOrder(Node<E> current, List<E> list){
		if (current != null){
			postOrder(current.left, list);
			postOrder(current.right, list);
			list.add(current.elem);
		}
	}

	public List<E> inOrderAscending(){
		if (root == null) return null;
		List<E> list = new LinkedList<>();
		inOrderAscending(root, list);
		return list;
	}
	private void inOrderAscending(Node<E> current, List<E> list){
		if (current != null){
			inOrderAscending(current.left, list);
			list.add(current.elem);
			inOrderAscending(current.right, list);
		}
	}

	public List<E> inOrderDescending(){
		if (root == null) return null;
		List<E> list = new LinkedList<>();
		inOrderDescending(root, list);
		return list;
	}
	private void inOrderDescending(Node<E> current, List<E> list){
		if (current != null){
			inOrderDescending(current.right, list);
			list.add(current.elem);
			inOrderDescending(current.left, list);
		}
	}

	private static class Node<E> {
		E elem;
		Node<E> left, right;

		public Node(E elem, Node<E> left, Node<E> right) {
			this.elem = elem;
			this.left = left;
			this.right = right;
		}
	}
}
