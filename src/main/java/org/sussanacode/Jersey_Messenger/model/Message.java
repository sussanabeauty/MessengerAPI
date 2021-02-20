package org.sussanacode.Jersey_Messenger.model;

import java.util.Date;
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement //enable us to display our message in a xml file
public class Message {
    private long id;
    private String author;
    private Date created;
    private String message;
    private Map<Long, Comment> comments = new HashMap<>();
    private List<Link> links = new ArrayList<>();
    
    
    
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    @XmlTransient // don't want comment to show when message is loaded, show only when requested
    public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

    
    
    public Message() {
    }

    public Message(long id, String author, String message) {
        this.id = id;
        this.author = author;
        this.created = new Date();
        this.message = message;
    }
    
    
    public void addLink(String url, String rel) {
    	Link link = new Link();
    	link.setLink(url);
    	link.setRel(rel);
    	links.add(link);
    }

}
