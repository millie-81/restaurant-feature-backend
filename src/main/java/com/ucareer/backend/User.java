package com.ucareer.backend;

import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String password;
    private String email;
    private String status;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }


    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }


    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }


    public Date getModified_at()
    {
        return modified_at;
    }
    public void setModified_at(Date modified_at)
    {
        this.modified_at = modified_at;
    }


    public Date getCreated_at()
    {
        return created_at;
    }
    public void setCreated_at(Date created_at)
    {
        this.created_at = created_at;
    }


    @Temporal(TIMESTAMP)
    private Date modified_at;

    @Temporal(TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    private Date created_at;
}
