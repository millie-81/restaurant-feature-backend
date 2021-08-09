package com.ucareer.backend;

import jdk.jfr.Timespan;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String status;
    private String label;
    private String description;
    private int price;
    private int core;
    private String speed;

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


    public String getLabel()
    {
        return label;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }


    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }


    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }


    public int getCore()
    {
        return core;
    }
    public void setCore(int core)
    {
        this.core = core;
    }


    public String getSpeed()
    {
        return speed;
    }
    public void setSpeed(String speed)
    {
        this.speed = speed;
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
    @UpdateTimestamp
    private Date modified_at;

    @Temporal(TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    @Column(updatable = false)
    private Date created_at;
}
