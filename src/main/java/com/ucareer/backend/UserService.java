package com.ucareer.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUser()
    {
        List<User> findAll = userRepository.findAll();
        return findAll;
    }

    public User findOneUser(Long id)
    {
        User findOne = userRepository.findById(id).orElse(null);
        if (findOne == null)
        {
            return null;
        }
        return findOne;
    }

    public User createOneUser(User requestbody)
    {
        User createOne = userRepository.save(requestbody);
        return createOne;
    }

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

    public Boolean deleteOneUser(Long id)
    {
        userRepository.deleteById(id);
        return true;
    }
}
