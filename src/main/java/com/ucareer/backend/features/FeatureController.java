package com.ucareer.backend.features;

import com.ucareer.backend.ResponseBody;
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

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    //get all features
    @GetMapping("api/v1/landings/{landingsId}/features")
    public ResponseEntity<ResponseBody> getFeatures(@PathVariable Long landingsId) {
        try {
            List<Feature> foundList = featureService.getFeatures(landingsId);
            ResponseBody<List> responseBody = new ResponseBody<>();
            responseBody.setResult(foundList);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    //get a feature
    @GetMapping("api/v1/landings/{landingsId}/features/{id}")
    public ResponseEntity<ResponseBody> getAFeature(@PathVariable Long landingsId,@PathVariable Long id) {
        try {
            Feature foundOne = featureService.getFeature(id,landingsId);
            ResponseBody<Feature> responseBody = new ResponseBody<>();
            if (foundOne == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            responseBody.setResult(foundOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //create a feature
    @PostMapping("api/v1/landings/langdingsId/features")
    public ResponseEntity<ResponseBody> createFeature(@RequestBody Feature feature,@PathVariable Long landingsId) {
        try {
            Feature saveOne = featureService.createFeature(feature);
            ResponseBody<Feature> responseBody = new ResponseBody<>();
            responseBody.setResult(saveOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //update a feature
    @PutMapping("api/v1/landings/{landingsId}/features/{id}")
    public ResponseEntity<ResponseBody> updateFeature(@RequestBody Feature feature, @PathVariable Long id,@PathVariable Long landingsId) {
        try {
            Feature foundOne = featureService.getFeature(id,landingsId);
            ResponseBody<Feature> responseBody = new ResponseBody<>();
            if (foundOne == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
            }
            Feature updatedOne = featureService.updateFeature(feature, foundOne);
            responseBody.setResult(updatedOne);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    //delete a feature
    @DeleteMapping("api/v1/landings/{landingsId}/features/{id}")
    public ResponseEntity<ResponseBody> deleteFeature(@PathVariable Long id,@PathVariable Long landingsId) {
        try {
            Boolean success = featureService.deleteFeature(id,landingsId);
            ResponseBody<Boolean> responseBody = new ResponseBody<>();
            responseBody.setResult(success);
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}