package com.ucareer.backend.users;

import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
        return modifiedAt;
    }
    public void setModified_at(Date modified_at)
    {
        this.modifiedAt = modified_at;
    }


    public Date getCreated_at()
    {
        return createdAt;
    }
    public void setCreated_at(Date created_at)
    {
        this.createdAt = created_at;
    }


    @Temporal(TIMESTAMP)
    @LastModifiedDate
    @UpdateTimestamp
    private Date modifiedAt;

    @Temporal(TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    //@Column(updatable = false)
    private Date createdAt;
}
