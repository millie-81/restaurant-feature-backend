package com.ucareer.backend.landings;

import com.ucareer.backend.features.Feature;

import com.ucareer.backend.heads.Head;
import com.ucareer.backend.menu_items.Menu_Item;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class Landings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String status;

    @Temporal(TIMESTAMP)
    @CreatedDate
    @CreationTimestamp
    private Date created_at;

    @Temporal(TIMESTAMP)
    @LastModifiedDate
    @UpdateTimestamp
    private Date modified_at;

    @OneToOne
    @JoinColumn(name = "header_id")
    private Head head;

    @OneToMany(mappedBy = "landings")
    private List<Feature> features;

    @OneToMany
    @JoinColumn(name = "landing_id")
    private List<Menu_Item> menu_items;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }


    public Date getModified_at() {
        return modified_at;
    }
    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }


    public Head getHead() {
        return head;
    }
    public void setHead(Head head) {
        this.head = head;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Menu_Item> getMenu_items() {
        return menu_items;
    }
    public void setMenu_items(List<Menu_Item> menu_items) {
        this.menu_items = menu_items;
    }
}
