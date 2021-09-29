package com.ucareer.backend.users;

import com.ucareer.backend.landings.Landings;
import com.ucareer.backend.landings.LandingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final
    UserRepository userRepository;
    final
    LandingsRepository landingsRepository;

    public UserService(UserRepository userRepository, LandingsRepository landingsRepository) {
        this.userRepository = userRepository;
        this.landingsRepository = landingsRepository;
    }



    /*
    Get all data from User
    Use UserRepository's function findAll, UserRepository is extends JpaRepository
    then return the result
    select * from User
     */
    public List<User> findAllUser() {
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
    public User findOneUser(Long id) {
        User findOne = userRepository.findById(id).orElse(null);
        if (findOne == null) {
            return null;
        }
        return findOne;
    }

    public User createOneUser(User user){
        User savedOne = userRepository.save(user);
        Landings landings = new Landings();
        Landings savedLandings = landingsRepository.save(landings);
        savedOne.setLandings(savedLandings);
        savedOne = userRepository.save(savedOne);
        return savedOne;
    }


    public User updateOneUser(Long id, User requestBody) {
        User findOne = userRepository.findById(id).orElse(null);
        if (findOne == null) {
            return null;
        }
        //set status when update
        findOne.setStatus("Updated");

        //if email in request body have value then update
        if (requestBody.getEmail() != null && requestBody.getEmail() != "") {
            findOne.setEmail(requestBody.getEmail());
        }

        //if password in request body have value then update
//        if (requestBody.getPassword() != null && requestBody.getPassword() != "") {
//            findOne.setPassword(requestBody.getPassword());
//        }
        //change first name
        if(requestBody.getFirstName() != null && requestBody.getFirstName() != ""){
            findOne.setFirstName(requestBody.getFirstName());
        }
        //change last name
        if(requestBody.getLastName() != null && requestBody.getLastName() != ""){
            findOne.setLastName(requestBody.getLastName());
        }

        //change address
        if(requestBody.getAddress() != null && requestBody.getAddress() != ""){
            findOne.setAddress(requestBody.getAddress());
        }
        //if user in request body have value then update
        if (requestBody.getUsername() != null && requestBody.getUsername() != "") {
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
    public Boolean deleteOneUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }


    /*
    check username exist or not
     */
    public User getByUsername(String username) {
        return this.userRepository.findDistinctByUsername(username);
    }
}
