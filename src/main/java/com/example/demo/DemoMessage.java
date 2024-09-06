package com.example.demo;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class DemoMessage {
    
    private String message;
    @Id
    @UuidGenerator
    private String guid;

    public DemoMessage() {  }

    public DemoMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "DemoMessage{" +
                "message='" + message + '\'' +
                ", guid='" + guid + '\'' +
                '}';
    }

    
}
