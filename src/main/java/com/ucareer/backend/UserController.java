package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    /*
    Get all data from User
    Use UserRepository's function findAll, UserRepository is extends JpaRepository
    then return the result
    select * from User
     */
    @GetMapping("api/v1/User")
    public List<User> findAllUser()
    {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    /*
    Get a User by id
    Use UserRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from User where id = xx

    if parameter is not a int, then error 400, bad request.
     */
    @GetMapping("api/v1/User/{id}")
    public User findAUser(@PathVariable Long id)
    {
        User getOne = userRepository.findById(id).orElse(null);
        return getOne;
    }

    /*
    Create a User
    pass the request body to the db
    created_at is autofilled & cause this is the first time to modify the cpu, so create time equals to the modify time
    insert into User(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
     */
    @PostMapping("api/v1/User")
    public User createAUser(@RequestBody User user)
    {
        user.setStatus("Initial");
        User createOne = userRepository.save(user);
        return createOne;
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
    @PutMapping("api/v1/User/{id}")
    public User updateAUser(@PathVariable Long id, @RequestBody User user)
    {
        User updateOne = userRepository.findById(id).orElse(null);
        //let the id is the id which is customer input, if miss this one, id should be null, the it do insert
        user.setId(id);
        //set status as updated
        user.setStatus("Updated");
        //set the create_at , if miss this one, it will be null.
        user.setCreated_at(updateOne.getCreated_at());

        //if request body no value of Email or empty value of Email, then keep the value
        if(user.getEmail()==null || user.getEmail()=="" )
        {
            user.setEmail(updateOne.getEmail());
        }

        //if request body no value of password or empty value of password, then keep the value
        if(user.getPassword() ==null || user.getPassword()=="" )
        {
            user.setPassword(updateOne.getPassword());
        }

        //if request body no value of username or empty value of username, then keep the value
        if(user.getUsername() ==null || user.getUsername()=="" )
        {
            user.setUsername(updateOne.getUsername());
        }

        updateOne = userRepository.save(user);
        return updateOne;
    }

    /*
    delete a User by id
    delete from User where id = xx;
    if without id, error 405, method not allowed
     */
    @DeleteMapping("api/v1/User/{id}")
    public String deleteAUser(@PathVariable Long id)
    {
        try
        {
            userRepository.deleteById(id);
            return "Successful";
        }
        catch (Exception e)
        {
            return "Something wrong.";
        }
    }
}
