package cleo.primer;

/**
 * RestDAO
 * 
 * @author jwu
 * @since 01/05, 2012
 */
public interface RestDAO<T> {

    // HTTP-GET
    public T getElement(int elementId);
    
    // HTTP-DELETE
    public T deleteElement(int elementId) throws Exception;
    
    // HTTP-PUT
    public T updateElement(T element) throws Exception;
    
    // HTTP-POST
    public boolean insertElement(T element) throws Exception;
}
