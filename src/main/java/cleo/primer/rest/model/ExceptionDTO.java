package cleo.primer.rest.model;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class ExceptionDTO {
    
    @XmlElement(name = "class")
    public String className;
    
    @XmlElement
    public String message;
    
    @XmlElement(name = "stack-trace")
    public String stackTrace;
    
    public ExceptionDTO() {}
    
    public ExceptionDTO(Exception exception) {
        this.className = exception.getClass().getName();
        this.message = exception.getMessage();
        
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        this.stackTrace = stringWriter.toString();
    }
}