package com.ucareer.backend;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity //annotation , Link to Computer table in DB
//@DynamicUpdate  use this annotation, if there miss some
public class Computer {

    //annotation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String lable;
    private int price;
    private String type;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }


    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }


    public String getLable()
    {
        return lable;
    }
    public void setLable(String lable)
    {
        this.lable = lable;
    }


    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }


    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
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
    @LastModifiedDate
    @Column(name = "modified_at")
    @UpdateTimestamp
    private Date modified_at;

    @Temporal(TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    //@Column(updatable = false)
    private Date created_at;
}
