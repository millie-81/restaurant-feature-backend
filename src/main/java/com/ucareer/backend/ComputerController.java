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
        Computer findOne = computerRepository.findById(id).orElse(null);//this null means this function null

        //if there's no computer object, return null, means can't find id = parameter 's computer
        if(findOne == null)
        {
            return null;
        }

        ////when do update, then change the status to updated
        findOne.setStatus("Updated");

        //if label in request body have value then update
        if(computer.getLable() != null && computer.getLable() !="")
        {
            findOne.setLable(computer.getLable());
        }

        //if price in request body have value then update
        if(computer.getPrice()!= 0)
        {
            findOne.setPrice(computer.getPrice());
        }

        //if type in request body have value then update
        if(computer.getType() != null && computer.getType() != "")
        {
            findOne.setType(computer.getType());
        }

        //update the computer
        Computer showFindOne = computerRepository.save(findOne);
        return showFindOne;
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

//    @GetMapping("api/v1/Computer")
//    public List<Computer> findComputerGreaterThan(@RequestParam int price)
//    {
//        List<Computer> getAll = computerRepository.getComputerByPriceGreaterThan(price);
//        return  getAll;
//    }
}
