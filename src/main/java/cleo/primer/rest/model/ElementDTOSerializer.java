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

/*
 * Copyright (c) 2011 LinkedIn, Inc
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

import krati.io.serializer.JavaSerializer;

import cleo.search.ElementSerializationException;
import cleo.search.ElementSerializer;

/**
 * SimpleElementSerializer
 * 
 * @author jwu
 * @since 01/20, 2011
 */
public class ElementDTOSerializer implements ElementSerializer<ElementDTO> {
    private final JavaSerializer<ElementDTO> serializer = new JavaSerializer<ElementDTO>();
    
    @Override
    public ElementDTO deserialize(byte[] bytes) throws ElementSerializationException {
        return serializer.deserialize(bytes);
    }
    
    @Override
    public byte[] serialize(ElementDTO object) throws ElementSerializationException {
        return serializer.serialize(object);
    }
}
