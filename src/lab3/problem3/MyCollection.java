package lab3.problem3;

public interface MyCollection<E> {
    boolean add(E element);
    boolean remove(E element);
    boolean contains(E element);
    int size();
    boolean isEmpty();
    void clear();
    Object[] toArray();
}
