package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    Get all data from User
    Use UserRepository's function findAll, UserRepository is extends JpaRepository
    then return the result
    select * from User
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
    Get a User by id
    Use UserRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from User where id = xx

    if parameter is not a int, then error 400, bad request.
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
    Create a User
    pass the request body to the db
    created_at is autofilled & cause this is the first time to modify the cpu, so create time equals to the modify time
    insert into User(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
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
    Update a cpu
    pass the id and request body that user input
    *
    *
    update User set col_name1 = value1, col_name2 = value2 where id = xx;
    ???????????????????????????????????????????
    if id = null, do insert , if id exist , do update.... but id is a parameter, why it should input in request body
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
    delete a User by id
    delete from User where id = xx;
    if without id, error 405, method not allowed
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
