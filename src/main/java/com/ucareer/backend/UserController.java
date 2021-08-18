package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
/*
    in this class,
    all of them just use responseBody instead of Users
 */
public class UserController {

    //use User Service which have the details of the logic
    @Autowired
    UserService userService;


    /*
    return a ResponseEntity, statusOk means 200, internalServerError means internet error
    get a User List then put them to RequestBody's result,
     */
    @GetMapping("api/v1/Users")
    public ResponseEntity<ResponseBody> findAllUser()
    {
        try
        {
            List<User> findAll = userService.findAllUser();
            ResponseBody<List> responseBody = new ResponseBody();
            responseBody.setResult(findAll);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
    get a User then put it to RequestBody's result,
     */
    @GetMapping("api/v1/Users/{id}")
    public ResponseEntity<ResponseBody> findAUser(@PathVariable Long id)
    {
        try
        {
            User findOne = userService.findOneUser(id);
            ResponseBody<User> responseBody = new ResponseBody();
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
    create a User then put it to RequestBody's result,
     */
    @PostMapping("api/v1/Users")
    public ResponseEntity<ResponseBody> createAUser(@RequestBody User user)
    {
        try
        {
            User createOne = userService.createOneUser(user);
            ResponseBody<User> responseBody = new ResponseBody();
            responseBody.setResult(createOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }


    /*
    update a User then put it to RequestBody's result,
     */
    @PutMapping("api/v1/Users/{id}")
    public ResponseEntity<ResponseBody> updateOneUser(@PathVariable Long id, @RequestBody User user)
    {
        try
        {
            User findOne = userService.findOneUser(id);
            ResponseBody<User> responseBody = new ResponseBody();
            if(findOne == null)
            {
                responseBody.setMessage("item "+ id + " can not be found");
                responseBody.setError(new Exception("item can not be found"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            User saveOne = userService.updateOneUser(id,user);
            responseBody.setResult(saveOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }


    /*
    delete a User then return success to RequestBody's result,
     */
    @DeleteMapping("api/v1/Users/{id}")
    public ResponseEntity<ResponseBody> deleteOneUser(@PathVariable Long id)
    {
        ResponseBody<Boolean> responseBody = new ResponseBody();
        try
        {
            boolean success = userService.deleteOneUser(id);
            responseBody.setResult(success);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
