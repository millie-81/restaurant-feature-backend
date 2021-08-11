package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CpuController {
    @Autowired
    CpuRepository cpuRepository;

    /*
    Get all data from cpu
    Use CpuRepository's function findAll, CpuRepository is extends JpaRepository
    then return the result
    select * from cpu
     */
    @GetMapping("api/v1/Cpu")
    public List<Cpu> getAllCpu()
    {
        List<Cpu> findAll = cpuRepository.findAll();
        return findAll;
    }

    /*
    Get a Cpu by id
    Use CpuRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from computer where id = xx

    if parameter is not a int, then error 400, bad request.
     */
    @GetMapping("api/v1/Cpu/{id}")
    public Cpu getACpu(@PathVariable Long id)
    {
        //orElse(null) means if can not find, return null
        Cpu findOne = cpuRepository.findById(id).orElse(null);
        return findOne;
    }

    /*
    Create a cpu
    pass the request body to the db
    created_at is autofilled & cause this is the first time to modify the cpu, so create time equals to the modify time
    insert into Cpu(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
     */
    @PostMapping("api/v1/Cpu")
    public Cpu createACpu( @RequestBody Cpu cpu)
    {
        //first time to insert so status is initial
        cpu.setStatus("Initial");
        Cpu createOne = cpuRepository.save(cpu);
        return createOne;
    }

    /*
    Update a cpu
    pass the id and request body that user input
    *
    *
    update cpu set col_name1 = value1, col_name2 = value2 where id = xx;
    ???????????????????????????????????????????
    if id = null, do insert , if id exist , do update.... but id is a parameter, why it should input in request body
     */
    @PutMapping("api/v1/Cpu/{id}")
    public Cpu updateACpu(@PathVariable Long id, @RequestBody Cpu cpu)
    {
        Cpu updateOne = cpuRepository.findById(id).orElse(null);//this null means this function null

        //if there's no cpu object, return null, means can't find id = parameter 's cpu
        if(updateOne == null)
        {
            return null;
        }

        //when do update, then change the status to updated
        updateOne.setStatus("Updated");


        //if core in request body have value then update
        if(cpu.getCore()!=0)
        {
            updateOne.setCore(cpu.getCore());
        }

        //if label in request body have value then update
        if(cpu.getLabel() != null && cpu.getLabel() != "")
        {
            updateOne.setLabel(cpu.getLabel());
        }

        //if description in request body have value then update
        if(cpu.getDescription() != null && cpu.getDescription() != "")
        {
            updateOne.setDescription(cpu.getDescription());
        }

        //if price in request body have value then update
        if(cpu.getPrice()!=0)
        {
            updateOne.setPrice(cpu.getPrice());
        }

        //if speed in request body have value then update
        if(cpu.getSpeed() != null && cpu.getSpeed() != "")
        {
            updateOne.setSpeed(cpu.getSpeed());
        }

        Cpu showUdateOne = cpuRepository.save(updateOne);
        return showUdateOne;
    }

    /*
    delete a cpu by id
    delete from cpu where id = xx;
    if without id, error 405, method not allowed
     */
    @DeleteMapping("api/v1/Cpu/{id}")
    public String deleteACpu(@PathVariable Long id)
    {
        try
        {
            cpuRepository.deleteById(id);
            return ("Successful");
        }
        catch (Exception e)
        {
            return ("Something wrong");
        }
    }
}
