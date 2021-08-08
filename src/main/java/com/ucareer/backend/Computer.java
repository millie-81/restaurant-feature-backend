package com.ucareer.backend;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity //annotation , Link to Computer table in DB
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
    private Date modified_at;

    @Temporal(TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    private Date created_at;
}
