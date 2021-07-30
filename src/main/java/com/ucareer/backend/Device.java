package com.ucareer.backend;

public class Device {

    String id;
    String name;
    int price;

    public void setId(String id)
    {
        this.id = id;
    }
    public String getId()
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
}
