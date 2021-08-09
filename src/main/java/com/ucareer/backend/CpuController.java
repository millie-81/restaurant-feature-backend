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
        Cpu updateOne = cpuRepository.findById(id).orElse(null);
        //set the create_at , if miss this one, it will be null.
        cpu.setCreated_at(updateOne.getCreated_at());
        //set status as updated
        cpu.setStatus("Updated");
        //let the id is the id which is customer input, if miss this one, id should be null, the it do insert
        cpu.setId(id);
        updateOne = cpuRepository.save(cpu);
        return updateOne;
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
