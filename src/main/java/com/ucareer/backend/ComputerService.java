package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    @Autowired
    ComputerRepository computerRepository;

    /*
    Get all data from computer
    Use ComputerRepository's function findAll, ComputerRepository is extends JpaRepository
    then return the result
    select * from computer
     */
    public List<Computer> findAllComputer()
    {
        List<Computer> findAll = computerRepository.findAll();
        return findAll;
    }

    /*
    Get a computer by id
    Use ComputerRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from computer where id = xx

    if parameter is not a int, then error 400, bad request.
     */
    public Computer findById(Long id)
    {
        Computer findOne = computerRepository.findById(id).orElse(null);
        if(findOne == null)
        {
            return null;
        }
        return findOne;
    }

    /*
    Create a computer
    pass the request body to the db
    created_at is autofilled
    insert into Computer(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
     */
    public Computer createOneComputer(Computer requestBody)
    {
        requestBody.setStatus("Initial");
        Computer createOne = computerRepository.save(requestBody);
        return createOne;
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
    public Computer updateOneComputer(Long id, Computer requestBody)
    {
        Computer findOne = computerRepository.findById(id).orElse(null);
        if (findOne == null)
        {
            return null;
        }
        requestBody.setStatus("Updated");
        //if label in request body have value then update
        if(requestBody.getLable() != null && requestBody.getLable() !="")
        {
            findOne.setLable(requestBody.getLable());
        }

        //if price in request body have value then update
        if(requestBody.getPrice()!= 0)
        {
            findOne.setPrice(requestBody.getPrice());
        }

        //if type in request body have value then update
        if(requestBody.getType() != null && requestBody.getType() != "")
        {
            findOne.setType(requestBody.getType());
        }
        Computer UpdateOne = computerRepository.save(findOne);
        return UpdateOne;
    }

    /*
    delete a computer by id
    delete from Computer where id = xx;
    if without id, error 405, method not allowed
     */
    public Boolean deleteOneComputer(Long id)
    {
        computerRepository.deleteById(id);
        return true;
    }

}
