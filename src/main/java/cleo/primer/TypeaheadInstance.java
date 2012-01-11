/*
 * Copyright (c) 2011-2012 Jingwei Wu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

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
