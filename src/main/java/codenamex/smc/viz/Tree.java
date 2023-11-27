package codenamex.smc.viz;

public interface Tree <E> extends Iterable<E> {
    /** Return true if the element is in the tree */
    public boolean search(E e);

    public boolean insert(E e);

    public boolean delete(E e);

    public void preorder();

    public void inorder();

    public void postorder();

    public int getSize();

    public boolean isEmpty();

}