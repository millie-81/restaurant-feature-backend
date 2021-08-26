package com.ucareer.backend.cpus;

import com.ucareer.backend.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CpuController {


    final CpuService cpuService;
    @Autowired
    public CpuController(CpuService cpuService){this.cpuService = cpuService;}

    /*
    Get all data from cpu
    Use CpuRepository's function findAll, CpuRepository is extends JpaRepository
    then return the result
    select * from cpu
     */
    @GetMapping("api/v1/Cpus")
    public ResponseEntity<com.ucareer.backend.ResponseBody> getAllCpus()
    {
        try
        {
            List<Cpu> findAll = cpuService.findAllCpu();
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
    Get a Cpu by id
    Use CpuRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from computer where id = xx

    if parameter is not a int, then error 400, bad request.
     */
    @GetMapping("api/v1/Cpus/{id}")
    public ResponseEntity<com.ucareer.backend.ResponseBody> getOneCpu(@PathVariable Long id)
    {
        try
        {
            Cpu findOne = cpuService.findOneCpu(id);
            com.ucareer.backend.ResponseBody<Cpu> responseBody = new com.ucareer.backend.ResponseBody();
            if(findOne == null)
            {
                responseBody.setMessage("item "+ id + " can not be found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            responseBody.setResult(findOne);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
    Create a cpu
    pass the request body to the db
    created_at is autofilled & cause this is the first time to modify the cpu, so create time equals to the modify time
    insert into Cpu(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
     */
    @PostMapping("api/v1/Cpus")
    public ResponseEntity<com.ucareer.backend.ResponseBody> createOneCpu(@RequestBody Cpu cpu)
    {
        try
        {
            Cpu createOne = cpuService.createOneCpu(cpu);
            com.ucareer.backend.ResponseBody<Cpu> responseBody = new com.ucareer.backend.ResponseBody();
            responseBody.setResult(createOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
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
    @PutMapping("api/v1/Cpus/{id}")
    public ResponseEntity <com.ucareer.backend.ResponseBody> updateOneCpu(@PathVariable Long id, @RequestBody Cpu cpu)
    {
        try
        {
            Cpu findOne = cpuService.findOneCpu(id);
            com.ucareer.backend.ResponseBody<Cpu> responseBody = new com.ucareer.backend.ResponseBody();
            if(findOne == null)
            {
                responseBody.setMessage("item "+ id + " can not be found");
                responseBody.setError(new Exception("item can not be found"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            Cpu saveOne = cpuService.updateOneCpu(id,cpu);
            responseBody.setResult(saveOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
    delete a cpu by id
    delete from cpu where id = xx;
    if without id, error 405, method not allowed
     */
    @DeleteMapping("api/v1/Cpus/{id}")
    public ResponseEntity<com.ucareer.backend.ResponseBody> deleteOneCpu(@PathVariable Long id)
    {
        com.ucareer.backend.ResponseBody<Boolean> responseBody = new ResponseBody();
        try
        {
            boolean success = cpuService.deleteOneCpu(id);
            responseBody.setResult(success);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
