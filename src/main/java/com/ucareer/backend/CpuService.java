package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CpuService {

    @Autowired
    CpuRepository cpuRepository;

    public List<Cpu> findAllCpu()
    {
        List<Cpu> findAll = cpuRepository.findAll();
        return findAll;
    }

    public Cpu findOneCpu(Long id)
    {
        Cpu findOne = cpuRepository.findById(id).orElse(null);
        if (findOne == null)
        {
            return null;
        }
        return findOne;
    }

    public Cpu createOneCpu(Cpu requestBody)
    {
        requestBody.setStatus("Initial");
        Cpu createOne = cpuRepository.save(requestBody);
        return createOne;
    }

    public Cpu updateOneCpu(Long id, Cpu requestBody)
    {
        Cpu findOne = cpuRepository.findById(id).orElse(null);
        if(findOne == null)
        {
            return null;
        }
        requestBody.setStatus("Updated");
        if(requestBody.getCore()!=0)
        {
            findOne.setCore(requestBody.getCore());
        }

        //if label in request body have value then update
        if(requestBody.getLabel() != null && requestBody.getLabel() != "")
        {
            findOne.setLabel(requestBody.getLabel());
        }

        //if description in request body have value then update
        if(requestBody.getDescription() != null && requestBody.getDescription() != "")
        {
            findOne.setDescription(requestBody.getDescription());
        }

        //if price in request body have value then update
        if(requestBody.getPrice()!=0)
        {
            findOne.setPrice(requestBody.getPrice());
        }

        //if speed in request body have value then update
        if(requestBody.getSpeed() != null && requestBody.getSpeed() != "")
        {
            findOne.setSpeed(requestBody.getSpeed());
        }
        return findOne;
    }

    public Boolean deleteOne(Long id)
    {
        cpuRepository.deleteById(id);
        return true;
    }
}
