package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DevicesController
{


    final DeviceRepository deviceRepository;
    @Autowired
    public DevicesController(DeviceRepository deviceRepository)
    {
        this.deviceRepository = deviceRepository;
    }

    //it's a api for serch all devices
    @GetMapping("api/v1/devices")
    public List<Device> getDevices()
    {
        //declare a list to store the devices
        List<Device> findList = deviceRepository.findAll();
        return findList;

//        //new a list
//        Device device1 = new Device();
//        Device device2 = new Device();
//        Device device3 = new Device();
//        Device device4 = new Device();
//        Device device5 = new Device();
//
//        //pass the value to the device
//        device1.setId("1");
//        device1.setName("A1");
//        device1.setPrice(10);
//
//        device2.setId("2");
//        device2.setName("A2");
//        device2.setPrice(20);
//
//        device3.setId("3");
//        device3.setName("A3");
//        device3.setPrice(30);
//
//        device4.setId("4");
//        device4.setName("A4");
//        device4.setPrice(40);
//
//        device5.setId("5");
//        device5.setName("A5");
//        device5.setPrice(50);
//
//        //add the devices to the device list
//        deviceList.add(device1);
//        deviceList.add(device2);
//        deviceList.add(device3);
//        deviceList.add(device4);
//        deviceList.add(device5);

        //return the result
        //return deviceList;
    }

    //it's a api for search a device by id
    /*

     */
    @GetMapping("api/v1/devices/{id}")
    public Device getDeviceById(@PathVariable long id)
    {
        //new a list
        Device findOne = deviceRepository.findById(id).orElse(null);
        return findOne;
//        //pass the value to the device
//        device1.setId(id);
//        device1.setName("A1");
//        device1.setPrice(10);

        //return the result
        //return device1;
    }

    //it's a api for create a device
    @PostMapping("api/v1/devices")
    public Device createDevice(@RequestBody Device device)
    {
        //device.setId(device.getId());
        device.setName(device.getName());
        device.setPrice(device.getPrice());
        return device;
    }

    //it's a api for update a device
    @PutMapping("api/v1/devices/{id}")
    public Device updateDevice(@PathVariable String id, @RequestBody Device device)
    {
        //device.setId(id);
        device.setName(device.getName());
        device.setPrice(device.getPrice());
        return device;
    }

    //it's a api for delete a device
    @DeleteMapping("api/v1/devices/{id}")
    public String deleteDevice(@PathVariable String id)
    {
        return ("Delete is Successful");
    }
}
