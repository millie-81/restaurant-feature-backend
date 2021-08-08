package com.ucareer.backend;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table(name = "Devices") // change table name to Devices , if without @Table, it should be default
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private int price;


    private String status = "Active";

    @Temporal(TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    private Date dateAt;



    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return this.id;
    }


    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
    public int getPrice() {
        return this.price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateAt() {
        return dateAt;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }

}
