import java.util.List;
import java.util.LinkedList;
class BSTree<E extends Comparable<? super E>> {

	private static class Node<E> {
		E elem;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E elem, Node<E> left, Node<E> right, Node<E> parent){

			this.elem = elem;
			this.left = left;
			this.right = right;
			this.parent = parent;

		}
	}

	private Node<E> root;

	public BSTree(){ root = null; }

	public boolean isEmpty(){ return root == null; }

	public boolean add(E elem){
		boolean res = false;

		if(root == null){
		} else {

			Node<E> prev = null;
			Node<E> current = root;
			while(current != null){

				E currElem = current.elem;
				if(elem.compareTo(currElem) < 0){

					// TO DO : Lengkapi bagian ini

				} else if(elem.compareTo(currElem) > 0){

					// TO DO : Lengkapi bagian ini

				} else {

					// TO DO : Lengkapi bagian ini

				}

			}

			// TO DO : Lengkapi bagian ini

		}

return res;
	}

	private Node<E> find(E elem){
		Node<E> res = null;

		if(root != null){

			Node<E> current = root;
			boolean found = false;
			while(!found && current != null){

				E currElem = current.elem;
				if(elem.compareTo(currElem) < 0){

					// TO DO : Lengkapi bagian ini

				} else if(elem.compareTo(currElem) > 0){

					// TO DO : Lengkapi bagian ini

				} else {

					// TO DO : Lengkapi bagian ini

				}

			}

		}

		return res;
	}

	public boolean remove(E elem){
		boolean res = false;

		Node<E> node = find(elem);

		if(node != null){

			if(node.left == null){

				if(node.right == null){

					// TO DO : Lengkapi bagian ini

				} else {

					// TO DO : Lengkapi bagian ini

				}

			} else {

				if(node.right == null){

					// TO DO : Lengkapi bagian ini

				} else {

					// TO DO : Lengkapi bagian ini

				}

			}

			res = true;

		}

		return res;
	}

	public E min(){
		E res = null;
		Node<E> minNode = minNode(root);

		if(minNode != null){

			res = minNode.elem;

		}

		return res;
	}

	private Node<E> minNode(Node<E> node){
		Node<E> res = null;
		if(node != null){

			Node<E> current = node;
			// TO DO : Lengkapi bagian ini

		}

		return res;
	}

	public E max(){
		E res = null;
		Node<E> maxNode = maxNode(root);

		if(maxNode != null){

			res = maxNode.elem;

		}

		return res;
	}

	private Node<E> maxNode(Node<E> node){
		Node<E> res = null;
		if(node != null){

			Node<E> current = node;
			// TO DO : Lengkapi bagian ini

		}

		return res;
	}

	public boolean contains(E elem){
		return true;
	}

	public List<E> preOrder(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return preOrder(root,list);
	}

	private List<E> preOrder(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}

	public List<E> postOrder(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return postOrder(root,list);
	}

	private List<E> postOrder(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}


	public List<E> inOrderAscending(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return inOrderAscending(root,list);
	}

	private List<E> inOrderAscending(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}

	public List<E> inOrderDescending(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return inOrderDescending(root,list);
	}

	private List<E> inOrderDescending(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}
}
