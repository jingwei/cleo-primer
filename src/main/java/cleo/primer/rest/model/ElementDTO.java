/*
 * Copyright (c) 2011-2012 LinkedIn, Inc
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

package cleo.primer.rest.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import cleo.search.Element;

/**
 * ElementDTO
 * 
 * @author jwu
 * @since 12/22, 2011
 */
@XmlRootElement(name = "element")
public class ElementDTO implements Element, Cloneable {
    private static final long serialVersionUID = 1L;
    private static final String[] EMPTY_TERMS = new String[0];
    
    /**
     * Element ID.
     */
    private int id;
    
    /**
     * Element name.
     */
    private String name;
    
    /**
     * Element title
     */
    private String title;
    
    /**
     * Element media (e.g. picture URL)
     */
    private String media;
    
    /**
     * Element web URL
     */
    private String url;
    
    /**
     * Element score.
     */
    private float score;
    
    /**
     * The terms are standardized words that an element can be searched.
     */
    @XmlElement(name = "term")
    private String[] terms = EMPTY_TERMS;
    
    /**
     * Element creation timestamp. 
     */
    private long timestamp = System.currentTimeMillis();
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    @Override @XmlTransient()
    public void setTerms(String... terms) {
        this.terms = (terms == null) ? EMPTY_TERMS : terms;
    }
    
    @Override
    public String[] getTerms() {
        return terms;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setMedia(String media) {
        this.media = media;
    }
    
    public String getMedia() {
        return media;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public void setScore(float score) {
        this.score = score;
    }
    
    @Override
    public float getScore() {
        return score;
    }
    
    @Override
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public long getTimestamp() {
        return timestamp;
    }
    
    @Override @XmlTransient
    public int getElementId() {
        return id;
    }
    
    @Override
    public void setElementId(int id) {
        this.id = id;
    }
    
    @Override
    public Object clone() {
        ElementDTO obj = new ElementDTO();
        
        obj.id = id;
        obj.name = name;
        obj.title = title;
        obj.media = media;
        obj.score = score;
        obj.terms = terms;
        obj.timestamp = timestamp;
        
        return obj;
    }
    
    @Override
    public int hashCode() {
      int hashCode = id;
      hashCode += timestamp / 23;
      
      if(terms != null) {
          for(String t : terms) {
              hashCode += t.hashCode();
          }
      }
      
      return hashCode;
    }
    
    @Override
    public boolean equals(Object o) {
      if(o == null) return false;
      if(o.getClass() == getClass()) {
          ElementDTO e = (ElementDTO)o;
          return id == e.id &&
              timestamp == e.timestamp &&
              Arrays.equals(terms, e.terms) &&
              score == e.score;
      } else {
          return false;
      }
    }
    
    @Override
    public int compareTo(Element e) {
        return score < e.getScore() ? -1 : (score == e.getScore() ? (getElementId() - e.getElementId()) : 1);
    }
    
    public boolean isSearchable() {
        return terms != null && terms.length > 0;
    }
}
