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
     */
    @GetMapping("api/v1/Computer/{id}")
    public Computer getComputerById(@PathVariable Long id)
    {
        Computer findOne = computerRepository.findById(id).orElse(null);;
        return findOne;
    }

    /*
    Create a computer
    pass the request body to the db
    created_at is autofilled
    insert into Computer(col_name1, col_name2) values(value1, value2);
     */
    @PostMapping("api/v1/Computer")
    public Computer createComputer(@RequestBody Computer computer)
    {
        computer.setStatus("Active");
        Computer SaveOne = computerRepository.save(computer);
        return SaveOne;
    }

    /*
    Update a computer
    pass the id and request body that user input
    update computer set col_name1 = value1, col_name2 = value2 where id = xx;
     */
    @PutMapping("api/v1/Computer/{id}")
    public Computer updateOneComputer(@PathVariable long id, @RequestBody Computer computer)
    {
        Computer findOne = computerRepository.findById(id).orElse(null);
        findOne.setStatus("Active");
        Computer saveOne = computerRepository.save(computer);
        return saveOne;
    }

    /*
    delete a computer by id
    delete from Computer where id = xx;
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
