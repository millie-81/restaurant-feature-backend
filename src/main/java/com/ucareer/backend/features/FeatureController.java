package com.ucareer.backend.features;

import com.ucareer.backend.ResponseBody;
import com.ucareer.backend.landings.Landings;
import com.ucareer.backend.landings.LandingsRepository;
import com.ucareer.backend.users.User;
import com.ucareer.backend.users.UserRepository;
import com.ucareer.backend.users.UserService;
import com.ucareer.backend.util.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FeatureController {
    final
    FeatureService featureService;
    final
    UserService userService;
    final
    LandingsRepository landingsRepository;

    public FeatureController(FeatureService featureService, LandingsRepository landingsRepository, UserService userService) {
        this.featureService = featureService;

        this.landingsRepository = landingsRepository;
        this.userService = userService;
    }

    //get all features
    @GetMapping("api/v1/landings/me/features")
    public ResponseEntity<ResponseBody> getFeatures( @RequestHeader("Authorization") String token) {
        try {
            ResponseBody<List> responseBody = new ResponseBody<>();
            String username = TokenHelper.VerifyToken(token);
            User foundOne = userService.getByUsername(username);
            if(foundOne ==  null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            List<Feature> foundList = foundOne.getLandings().getFeatures();
            responseBody.setResult(foundList);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }



    //get a feature
    @GetMapping("api/v1/landings/me/features/{id}")
    public ResponseEntity<ResponseBody> getAFeature(@PathVariable Long id,@RequestHeader("Authorization") String token) {
        try {
            Feature foundFeature = featureService.getFeature(id);
            ResponseBody<Feature> responseBody = new ResponseBody();
            String username = TokenHelper.VerifyToken(token);
            User foundOne = userService.getByUsername(username);
            if(foundOne ==  null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            if (foundFeature == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            responseBody.setResult(foundFeature);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //create a feature
    @PostMapping("api/v1/landings/me/features")
    public ResponseEntity<ResponseBody> createFeature(@RequestBody Feature feature,@RequestHeader("Authorization") String token) {
        try {
            // verify the token
            ResponseBody<Feature> responseBody = new ResponseBody();
            String username = TokenHelper.VerifyToken(token);
            User foundOne = userService.getByUsername(username);
            if(foundOne == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            Landings landings = foundOne.getLandings();
            Long landingId = landings.getId();
            Landings landing = landingsRepository.findById(landingId).orElse(null);
            if(landing == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            Feature saveOne = featureService.createFeature(feature);
            saveOne.setLandings(landing);
            saveOne = featureService.createFeature(saveOne);
            responseBody.setResult(saveOne);
            responseBody.setMessage("create feature successfully");
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //update a feature
    @PutMapping("api/v1/landings/me/features/{id}")
    public ResponseEntity<ResponseBody> updateFeature(@RequestBody Feature feature, @PathVariable Long id,@RequestHeader("Authorization") String token) {
        try {
            ResponseBody<Feature> responseBody = new ResponseBody();
            String username = TokenHelper.VerifyToken(token);
            User foundOne = userService.getByUsername(username);
            if(foundOne == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }

            Feature foundFeature = featureService.allowAccess(id,foundOne);
            if(foundFeature == null){
                responseBody.setMessage("not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            Feature updatedOne = featureService.updateFeature(feature,foundFeature);
            responseBody.setResult(updatedOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //delete a feature
    @DeleteMapping("api/v1/landings/me/features/{id}")
    public ResponseEntity<ResponseBody> deleteFeature(@PathVariable Long id,@RequestHeader("Authorization") String token) {
        try {
            ResponseBody<Boolean> responseBody = new ResponseBody();
            String username = TokenHelper.VerifyToken(token);
            User foundOne = userService.getByUsername(username);
            if(foundOne == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            Boolean success = featureService.deleteFeature(id,foundOne);
            if(success == false){
                responseBody.setMessage("cannot delete");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            responseBody.setResult(success);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}