package com.ucareer.backend.computers;


import com.ucareer.backend.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComputerController {


    @Autowired
    ComputerService computerService;

    /*
    Get all data from computer
    Use ComputerRepository's function findAll, ComputerRepository is extends JpaRepository
    then return the result
    select * from computer
     */
    @GetMapping("api/v1/Computers")
    public ResponseEntity<com.ucareer.backend.ResponseBody> findAllComputers()
    {
        try
        {
            List<Computer> findAll = computerService.findAllComputer();
            com.ucareer.backend.ResponseBody<List> responseBody = new com.ucareer.backend.ResponseBody();
            responseBody.setResult(findAll);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }

    }

    /*
    Get a computer by id
    Use ComputerRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from computer where id = xx

    if parameter is not a int, then error 400, bad request.
     */
    @GetMapping("api/v1/Computers/{id}")
    public ResponseEntity<com.ucareer.backend.ResponseBody> findOneComputer (@PathVariable Long id)
    {
        try
        {
            Computer findOne = computerService.findById(id);
            com.ucareer.backend.ResponseBody<Computer> responseBody = new com.ucareer.backend.ResponseBody();
            if(findOne == null)
            {
                responseBody.setMessage("item "+ id + " can not be found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            responseBody.setResult(findOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
    Create a computer
    pass the request body to the db
    created_at is autofilled
    insert into Computer(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
     */
    @PostMapping("api/v1/Computers")
    public ResponseEntity<com.ucareer.backend.ResponseBody> createOneComputer(@RequestBody Computer computer)
    {
        try
        {
            Computer createOne = computerService.createOneComputer(computer);
            com.ucareer.backend.ResponseBody<Computer> responseBody = new com.ucareer.backend.ResponseBody();
            responseBody.setResult(createOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
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
    @PutMapping("api/v1/Computers/{id}")
    public ResponseEntity <com.ucareer.backend.ResponseBody> updateOneComputer(@PathVariable long id, @RequestBody Computer computer)
    {
        try
        {
            Computer findOne = computerService.findById(id);
            com.ucareer.backend.ResponseBody<Computer> responseBody = new com.ucareer.backend.ResponseBody();
            if(findOne == null)
            {
                responseBody.setMessage("item "+ id + " can not be found");
                responseBody.setError(new Exception("item can not be found"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            Computer saveOne = computerService.updateOneComputer(id,computer);
            responseBody.setResult(saveOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
    delete a computer by id
    delete from Computer where id = xx;
    if without id, error 405, method not allowed
     */
    @DeleteMapping("api/v1/Computers/{id}")
    public ResponseEntity<com.ucareer.backend.ResponseBody> deleteOneComputer(@PathVariable Long id)
    {
        com.ucareer.backend.ResponseBody<Boolean> responseBody = new ResponseBody();
        try
        {
            boolean success = computerService.deleteOneComputer(id);
            responseBody.setResult(success);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping("api/v1/Computers")
//    public List<Computer> findComputerGreaterThan(@RequestParam int price)
//    {
//        List<Computer> getAll = computerRepository.getComputerByPriceGreaterThan(price);
//        return  getAll;
//    }
}
