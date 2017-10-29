import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

public class SDA1606917645L5A {
	public static void main (String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out));

		String [] inputs;
		String tmpInput = null;

		while((tmpInput = scan.readLine()) != null){
			inputs = tmpInput.split(";");
			switch(inputs[0]){
				case "ADD":
					break;
				case "REMOVE":
					break;
				case "CONTAINS":
					break;
				case "PREORDER":
					break;
				case "POSTORDER":
					break;
				case "ASCENDING":
					break;
				case "DESCENDING":
					break;
				case "MAX":
					break;
				case "MIN":
					break;
			}
		}
	}
}

/**
 *
 * Kelas Binary Search Tree
 * Mahasiswa tidak diwajibkan menggunakan template ini, namun sangat disarankan menggunakan template ini
 * Pada template ini, diasumsikan kelas yang ingin dipakai mengimplementasikan (implements) interface Comparable
 * NOTE : Tidak semua method yang dibutuhkan sudah disediakan templatenya pada kelas ini sehingga mahasiswa harus menambahkan sendiri method yang dianggap perlu
 * @author Jahns Christian Albert
 *
*/
class BSTree<E extends Comparable<E>> {

	/**
	  *
	  * Kelas yang merepresentasikan node pada tree
	  * @author Jahns Christian Albert
	  *
	*/
	private static class Node<E> {

		E elem;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		/**
		 *
		 * Constructor
		 * @param elemen pada node
		 * @param node kiri
		 * @param node kanan
		 * @param node parent
		 *
		*/
		public Node(E elem, Node<E> left, Node<E> right, Node<E> parent){
			this.elem = elem;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
	}

	private Node<E> root;


	/**
	  *
	  * Constructor Kelas Binary Search Tree
	  *
	*/
	public BSTree(){
		root = null;
	}

	/**
	  *
	  * Mengetahui apakah tree kosong atau tidak
	  * @return true jika kosong, false jika sebaliknya
	  *
	*/
	public boolean isEmpty(){
		return root == null;
	}

	/**
	  *
	  * Menambahkan objek ke dalam tree
	  * @param elemen yang ingin ditambahkan
	  * @return true jika elemen berhasil ditambahkan, false jika elemen sudah terdapat pada tree
	  *
	*/
	public boolean add(E elem){
		boolean res = true;
		if(root == null){
			// TO DO : Lengkapi bagian ini
			root = new Node<>(elem, null, null, null);
		} else {
			// Node<E> prev = null;
			Node<E> current = root;
			while(current != null){
				E currElem = current.elem;
				if(elem.compareTo(currElem) < 0){
					// TO DO : Lengkapi bagian ini
					current = current.left;
				} else if(elem.compareTo(currElem) > 0){
					// TO DO : Lengkapi bagian ini
					current = current.right;
				} else {
					// TO DO : Lengkapi bagian ini
					res = false;
					break;
				}
			}
			// TO DO : Lengkapi bagian ini
		}
		return res;
	}

	/**
	  *
	  * Mendapatkan node dengan elemen tertentu
	  * @param elemen yang ingin dicari nodenya
	  * @return node dari elemen pada parameter, null jika tidak ditemukan
	  *
	*/
	private Node<E> find(E elem){
		Node<E> res = null;
		if(root != null){
			Node<E> current = root;
			boolean found = false;
			while(!found && current != null){
				E currElem = current.elem;
				if(elem.compareTo(currElem) < 0){
					// TO DO : Lengkapi bagian ini
					current = current.left;
				} else if(elem.compareTo(currElem) > 0){
					// TO DO : Lengkapi bagian ini
					current = current.right;
				} else {
					// TO DO : Lengkapi bagian ini
					res = current;
				}
			}
		}
		return res;
	}

	/**
	 *
	 * Menghapus objek dari tree, menggunakan successor inorder untuk menghapus elemen yang memiliki left node dan right node
	 * Manfaatkan method minNode(Node<E> node) untuk mencari successor inorder
	 * @param elemen yang ingin dihapus
	 * @return true jika elemen ditemukan dan berhasil dihapus, false jika elemen tidak ditemukan
	 *
	*/
	public boolean remove2(E elem){
		boolean res = false;
		Node<E> node = find(elem);
		if(node != null){
			if(node.left == null){
				if(node.right == null){ } else { }
			} else {
				if(node.right == null){ } else { }
			}
			res = true;
		}
		return res;
	}

	public boolean remove(E elem){

		return res;
	}


	/**
	 *
	 * Mencari elemen dengan nilai paling kecil pada tree
	 * @return elemen dengan nilai paling kecil pada tree
	 *
	*/
	public E min(){
		return minNode(root).elem;
	}

	/**
	  *
	  * Method untuk mengembalikan node dengan elemen terkecil pada suatu subtree
	  * Hint : Manfaatkan struktur dari binary search tree
	  * @param node root dari subtree yang ingin dicari elemen terbesarnya
	  * @return node dengan elemen terkecil dari subtree yang diinginkan
	  *
	*/
	private Node<E> minNode(Node<E> node){
		Node<E> res = null;
		if(node != null){
			Node<E> current = node;
			// TO DO : Lengkapi bagian ini
		}
		return res;
	}

	/**
	 *
	 * Mencari elemen dengan nilai paling besar pada tree
	 * @return elemen dengan nilai paling besar pada tree
	 *
	*/
	public E max(){
		return maxNode(root).elem;
	}

	/**
	  *
	  * Method untuk mengembalikan node dengan elemen terbesar pada suatu subtree
	  * Hint : Manfaatkan struktur dari binary search tree
	  * @param node root dari subtree yang ingin dicari elemen terbesarnya
	  * @return node dengan elemen terbesar dari subtree yang diinginkan
	  *
	*/
	private Node<E> maxNode(Node<E> node){
		Node<E> res = null;
		if(node != null){
			Node<E> current = node;
			// TO DO : Lengkapi bagian ini
		}
		return res;
	}

	/**
	  *
	  * Mengetahui apakah sebuah objek sudah terdapat pada tree
	  * Asumsikan jika elem.compareTo(otherElem) == 0, maka elem dan otherElem merupakan objek yang sama
	  * Hint : Manfaatkan method find
	  * @param elemen yang ingin diketahui keberadaannya dalam tree
	  * @return true jika elemen ditemukan, false jika sebaliknya
	  *
	*/
	public boolean contains(E elem){
		// TO DO : Lengkapi method ini
		return true;
	}

	/**
	  * Mengembalikan tree dalam bentuk pre-order
	  * @return tree dalam bentuk pre-order sebagai list of E
	*/
	public List<E> preOrder(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return preOrder(root,list);
	}

	/**
	  *
	  * Method helper dari preOrder()
	  * @param node pointer
	  * @param list sebagai akumulator
	  * @return kumpulan elemen dari subtree yang rootnya adalah node parameter dengan urutan pre-order
	  *
	*/
	private List<E> preOrder(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}

	/**
	  * Mengembalikan tree dalam bentuk post-order
	  * @return tree dalam bentuk post-order sebagai list of E
	*/
	public List<E> postOrder(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return preOrder(root,list);
	}

	/**
	  *
	  * Method helper dari postOrder()
	  * @param node pointer
	  * @param list sebagai akumulator
	  * @return kumpulan elemen dari subtree yang rootnya adalah node parameter dengan urutan post-order
	  *
	*/
	private List<E> postOrder(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}


	/**
	  * Mengembalikan tree dalam bentuk in-order secara ascending
	  * @return tree dalam bentuk in-order secara ascending sebagai list of E
	*/
	public List<E> inOrderAscending(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return preOrder(root,list);
	}

	/**
	  *
	  * Method helper dari inOrderAscending()
	  * @param node pointer
	  * @param list sebagai akumulator
	  * @return kumpulan elemen dari subtree yang rootnya adalah node parameter dengan urutan in-order secara ascending
	  *
	*/
	private List<E> inOrderAscending(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}


	/**
	  * Mengembalikan tree dalam bentuk in-order secara descending
	  * @return tree dalam bentuk in-order secara descending sebagai list of E
	*/
	public List<E> inOrderDescending(){
		List<E> list = new LinkedList<>(); // default menggunakan LinkedList, silahkan menggunakan List yang sesuai dengan Anda
		return preOrder(root,list);
	}

	/**
	  *
	  * Method helper dari inOrderDescending()
	  * @param node pointer
	  * @param list sebagai akumulator
	  * @return kumpulan elemen dari subtree yang rootnya adalah node parameter dengan urutan in-order descending
	  *
	*/
	private List<E> inOrderDescending(Node<E> node, List<E> list){
		// TO DO : Lengkapi method ini
		return list;
	}
}
