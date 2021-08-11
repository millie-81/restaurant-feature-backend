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
        User updateOne = userRepository.findById(id).orElse(null); //this null means this function null

        //if there's no user object, return null, means can't find id = parameter 's user
        if(updateOne == null)
        {
            return null;
        }

        //set status when update
        updateOne.setStatus("Updated");

        //if email in request body have value then update
        if(user.getEmail()!=null && user.getEmail()!= "")
        {
            updateOne.setEmail(user.getEmail());
        }

        //if password in request body have value then update
        if(user.getPassword() != null && user.getPassword() !="")
        {
            updateOne.setPassword(user.getPassword());
        }

        //if user in request body have value then update
        if(user.getUsername()!=null && user.getUsername()!="")
        {
            updateOne.setUsername(user.getUsername());
        }

        User showUpdateOne = userRepository.save(updateOne);
        return showUpdateOne;
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
