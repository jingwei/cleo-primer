package cleo.primer;

import cleo.search.Element;
import cleo.search.Indexer;
import cleo.search.store.ArrayStoreElement;
import cleo.search.typeahead.Typeahead;

/**
 * TypeaheadInstance
 * 
 * @author jwu
 * @since 12/22, 2011
 */
public interface TypeaheadInstance<E extends Element> {
    
    /**
     * @return the typeahead indexer.
     */
    public Indexer<E> getIndexer();
    
    /**
     * @return the typeahead searcher.
     */
    public Typeahead<E> getSearcher();
    
    /**
     * @return the underlying element store.
     */
    public ArrayStoreElement<E> getElementStore();
}
