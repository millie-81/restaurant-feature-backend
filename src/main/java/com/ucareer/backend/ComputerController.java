package com.ucareer.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComputerController {

    final ComputerRepository computerRepository;
    @Autowired
    public ComputerController(ComputerRepository computerRepository){ this.computerRepository = computerRepository; }

    /*
    Get all data from computer
    Use ComputerRepository's function findAll, ComputerRepository is extends JpaRepository
    then return the result
    select * from computer
     */
    @GetMapping("api/v1/Computer")
    public List<Computer> getComputers()
    {
        List<Computer> computerList = computerRepository.findAll();
        return computerList;
    }

    /*
    Get a computer by id
    Use ComputerRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from computer where id = xx

    if parameter is not a int, then error 400, bad request.
     */
    @GetMapping("api/v1/Computer/{id}")
    public Computer getComputerById(@PathVariable Long id)
    {
        //orElse(null) means if can not find, return null
        Computer findOne = computerRepository.findById(id).orElse(null);;
        return findOne;
    }

    /*
    Create a computer
    pass the request body to the db
    created_at is autofilled
    insert into Computer(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
     */
    @PostMapping("api/v1/Computer")
    public Computer createComputer(@RequestBody Computer computer)
    {
        computer.setStatus("Initial");
        Computer SaveOne = computerRepository.save(computer);
        return SaveOne;
    }

    /*
    Update a computer
    pass the id and request body that user input
    *
    *
    update computer set col_name1 = value1, col_name2 = value2 where id = xx;

    ???????????????????????????????????????????
    if id = null, do insert , if id exist , do update.... but id is a parameter, why it should input in request body
     */
    @PutMapping("api/v1/Computer/{id}")
    public Computer updateOneComputer(@PathVariable long id, @RequestBody Computer computer)
    {
        Computer findOne = computerRepository.findById(id).orElse(null);
        //set the create_at , if miss this one, it will be null.
        computer.setCreated_at(findOne.getCreated_at());
        //set status as updated
        computer.setStatus("Updated");
        //let the id is the id which is customer input, if miss this one, id should be null, the it do insert
        computer.setId(id);

        //if request body no value of label or empty value of label, then keep the value
        if(computer.getLable()==null || computer.getLable()=="" )
        {
            computer.setLable(findOne.getLable());
        }
        //if request body no price value, then keep
        if(computer.getPrice() == 0 )
        {
            computer.setPrice(findOne.getPrice());
        }
        //if request body no value of type or empty value, then keep.
        if(computer.getType()==null || computer.getType()=="" )
        {
            computer.setType(findOne.getType());
        }

        //update the computer
        findOne = computerRepository.save(computer);
        return findOne;
    }

    /*
    delete a computer by id
    delete from Computer where id = xx;
    if without id, error 405, method not allowed
     */
    @DeleteMapping("api/v1/Computer/{id}")
    public String deleteOne(@PathVariable Long id)
    {
        try{
            computerRepository.deleteById(id);
            return ("Successful");
        }
        catch (Exception e)
        {
            return ("Something Wrong");
        }
    }

}
