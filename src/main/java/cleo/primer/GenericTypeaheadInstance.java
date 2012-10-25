/*
 * Copyright (c) 2011-2012
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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cleo.search.Element;
import cleo.search.Indexer;
import cleo.search.MultiIndexer;
import cleo.search.selector.ScoredElementSelectorFactory;
import cleo.search.store.ArrayStoreElement;
import cleo.search.store.MultiArrayStoreElement;
import cleo.search.tool.GenericTypeaheadInitializer;
import cleo.search.typeahead.GenericTypeahead;
import cleo.search.typeahead.GenericTypeaheadConfig;
import cleo.search.typeahead.MultiTypeahead;
import cleo.search.typeahead.Typeahead;
import cleo.search.typeahead.TypeaheadConfigFactory;

/**
 * GenericTypeaheadInstance
 * 
 * @author jwu
 * @since 12/22, 2011
 */
public class GenericTypeaheadInstance<E extends Element> implements TypeaheadInstance<E> {
    final Indexer<E> indexer;
    final Typeahead<E> searcher;
    final ArrayStoreElement<E> elementStore;
    
    public GenericTypeaheadInstance(String name, File configPath) throws Exception {
        File[] configFiles = configPath.listFiles(new FileFilter() {
            public boolean accept(File path) {
                return path.getName().endsWith(".config");
            }
        });
        
        List<Indexer<E>> indexerList = new ArrayList<Indexer<E>>();
        List<Typeahead<E>> searcherList = new ArrayList<Typeahead<E>>();
        List<ArrayStoreElement<E>> storeList = new ArrayList<ArrayStoreElement<E>>();
        
        for(File configFile : configFiles) {
            System.out.println(configFile.getPath());
            GenericTypeahead<E> gta = createTypeahead(configFile);
            indexerList.add(gta);
            searcherList.add(gta);
            storeList.add(gta.getElementStore());
        }
        
        // Create indexer, searcher and elementStore
        indexer = new MultiIndexer<E>(name, indexerList);
        searcher = new MultiTypeahead<E>(name, searcherList);
        elementStore = new MultiArrayStoreElement<E>(storeList);
        
        // Flush indexes upon shutdown
        addShutdownHook();
    }
    
    protected void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    indexer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    protected GenericTypeahead<E> createTypeahead(File configFile) throws Exception {
        // Create typeahead config
        GenericTypeaheadConfig<E> config =
            TypeaheadConfigFactory.createGenericTypeaheadConfig(configFile);
        config.setSelectorFactory(new ScoredElementSelectorFactory<E>());
        
        // Create typeahead initializer
        GenericTypeaheadInitializer<E> initializer =
            new GenericTypeaheadInitializer<E>(config);
        
        return (GenericTypeahead<E>)initializer.getTypeahead();
    }
    
    public final Indexer<E> getIndexer() {
        return indexer;
    }
    
    public final Typeahead<E> getSearcher() {
        return searcher;
    }
    
    public final ArrayStoreElement<E> getElementStore() {
        return elementStore;
    }
}
