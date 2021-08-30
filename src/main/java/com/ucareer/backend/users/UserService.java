package com.ucareer.backend.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    /*
    Get all data from User
    Use UserRepository's function findAll, UserRepository is extends JpaRepository
    then return the result
    select * from User
     */
    public List<User> findAllUser()
    {
        List<User> findAll = userRepository.findAll();
        return findAll;
    }


    /*
    Get a User by id
    Use UserRepository's function findById
    pass a variable(id) which customer input
    then return the result
    select * from User where id = xx

    if parameter is not a int, then error 400, bad request.
     */
    public User findOneUser(Long id)
    {
        User findOne = userRepository.findById(id).orElse(null);
        if (findOne == null)
        {
            return null;
        }
        return findOne;
    }


    /*
    Create a User
    pass the request body to the db
    created_at is autofilled & cause this is the first time to modify the cpu, so create time equals to the modify time
    insert into User(col_name1, col_name2) values(value1, value2);

    if with id, Error 405, method not allowed
     */
    public User createOneUser(User requestbody)
    {
        requestbody.setStatus("Initial");
        User createOne = userRepository.save(requestbody);
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
    //********Actually, because of do some setting on front end, for update, do not need if condition **** new added  20210817 10pm
    public User updateOneUser(Long id, User requestBody)
    {
        User findOne = userRepository.findById(id).orElse(null);
        if (findOne == null)
        {
            return null;
        }
        //set status when update
        findOne.setStatus("Updated");

        //if email in request body have value then update
        if(requestBody.getEmail()!=null && requestBody.getEmail()!= "")
        {
            findOne.setEmail(requestBody.getEmail());
        }

        //if password in request body have value then update
        if(requestBody.getPassword() != null && requestBody.getPassword() !="")
        {
            findOne.setPassword(requestBody.getPassword());
        }

        //if user in request body have value then update
        if(requestBody.getUsername()!=null && requestBody.getUsername()!="")
        {
            findOne.setUsername(requestBody.getUsername());
        }

        User updateOne = userRepository.save(findOne);
        return updateOne;
    }

    /*
    delete a User by id
    delete from User where id = xx;
    if without id, error 405, method not allowed
     */
    public Boolean deleteOneUser(Long id)
    {
        userRepository.deleteById(id);
        return true;
    }


    /*
    check username exist or not
     */
    public User getByUsername(String username){
        return this.userRepository.findDistinctByUsername(username);
    }

    /*
    save User
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
