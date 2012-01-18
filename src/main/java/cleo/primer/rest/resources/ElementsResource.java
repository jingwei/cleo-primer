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

package cleo.primer.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cleo.primer.ElementDAO;
import cleo.primer.rest.model.ElementDTO;
import cleo.primer.rest.model.ElementListDTO;
import cleo.primer.rest.model.ExceptionDTO;
import cleo.search.collector.Collector;
import cleo.search.collector.SortedCollector;

@Component
@Scope("request")
@Path("/elements")
public class ElementsResource {
    // Allows to insert contextual objects such as 
    // ServletContext, Request, Response and UriInfo
    @Context
    UriInfo uriInfo;
    
    @Context
    Request request;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ElementListDTO getElements() {
        List<ElementDTO> list = ElementDAO.INSTANCE.getElements();
        return new ElementListDTO(list); 
    }
    
    @GET
    @Path("/{start}..{end}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ElementListDTO getElements(@PathParam("start")int start, @PathParam("end")int end) {
        List<ElementDTO> list = new ArrayList<ElementDTO>();
        
        for(int index = start; index <= end; index++) {
            ElementDTO dto = ElementDAO.INSTANCE.getElement(index);
            if(dto != null) { 
                list.add(dto);
            }
        }
        
        return new ElementListDTO(list); 
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addElements(ElementListDTO elementListDTO) {
        for(ElementDTO elementDTO : elementListDTO.elements) {
            try {
                ElementDAO.INSTANCE.insertElement(elementDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return Response.status(Status.OK).build();
    }
    
    @POST
    @Path("/_")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addElement(ElementDTO elementDTO) {
        try {
            ElementDAO.INSTANCE.insertElement(elementDTO);
            return Response.status(Status.OK).build();
        } catch (Exception e) {
            return Response.status(Status.SEE_OTHER).entity(new ExceptionDTO(e)).build();
        }
    }
    
    @GET
    @Path("/{index}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getElement(@PathParam("index")int index) {
        try {
            ElementDTO dto = ElementDAO.INSTANCE.getElement(index);
            return Response.status(Status.OK).entity(dto).build();
        } catch(Exception e) {
            return Response.status(Status.NOT_FOUND).entity(new ExceptionDTO(e)).build();
        }
    }
    
    @PUT
    @Path("/{index}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putElement(@PathParam("index")int index, ElementDTO elementDTO) {
        ElementDTO old;
        try {
            old = ElementDAO.INSTANCE.updateElement(elementDTO);
            return Response.status(Status.OK).entity(old).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(new ExceptionDTO(e)).build();
        }
    }
    
    @DELETE
    @Path("/{index}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteElement(@PathParam("index")int index) {
        try {
            ElementDTO old = ElementDAO.INSTANCE.deleteElement(index);
            return Response.status(Status.OK).entity(old).build();
        } catch(Exception e) {
            return Response.status(Status.NOT_FOUND).entity(new ExceptionDTO(e)).build();
        }
    }
    
    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ElementListDTO search(@QueryParam("uid")int uid,
                                 @QueryParam("query")String query) {
        if(query == null) {
            return new ElementListDTO();
        }
        
        String[] terms = query.replaceAll("\\W+", " ").toLowerCase().split(" ");
        Collector<ElementDTO> collector = new SortedCollector<ElementDTO>(10, 100);
        collector = ElementDAO.INSTANCE.getSearcher().search(uid, terms, collector);
        
        return new ElementListDTO(collector.elements());
    }
    
    @POST
    @Path("/flush")
    @Produces(MediaType.TEXT_HTML)
    public Response flush() {
    	try {
    		ElementDAO.INSTANCE.getIndexer().flush();
            return Response.status(Status.OK).build();
    	} catch(IOException ioe) {
    	    return Response.status(Status.SERVICE_UNAVAILABLE).entity(new ExceptionDTO(ioe)).build();
    	}
    }
}
