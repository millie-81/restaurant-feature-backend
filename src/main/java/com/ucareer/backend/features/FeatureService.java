package com.ucareer.backend.features;

import com.ucareer.backend.landings.LandingsRepository;
import com.ucareer.backend.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {
    final
    FeatureRepository featureRepository;
    final
    LandingsRepository landingsRepository;

    public FeatureService(FeatureRepository featureRepository, LandingsRepository landingsRepository) {
        this.featureRepository = featureRepository;
        this.landingsRepository = landingsRepository;
    }

    // find all features
    public List<Feature>  getFeatures(){
        return featureRepository.findAll();
    }

    // find one feature
    public Feature getFeature(Long id){
        return featureRepository.findById(id).orElse(null);
    }

    //create a feature
    public Feature createFeature(Feature feature){
      Feature savedOne = featureRepository.save(feature);
      return savedOne;
    }

    //update a feature, foundOne is the feature which we get from database
    public Feature updateFeature(Feature feature, Feature foundOne){
        if(feature.getTitle() != null){
            foundOne.setTitle(feature.getTitle());
        }
        if(feature.getDescription() != null){
            foundOne.setDescription(feature.getDescription());
        }
        if(feature.getIconUrl() != null){
            foundOne.setIconUrl(feature.getIconUrl());
        }
        if(feature.getStatus() != null){
            foundOne.setStatus(feature.getStatus());
        }
        return featureRepository.save(foundOne);
    }

    //public delete feature
    public Boolean deleteFeature(Long id, User user){
        if(allowAccess(id,user) != null){
            featureRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Feature allowAccess(Long id, User user){
        Feature foundFeature = getFeature(id);
        if (foundFeature == null) {
            return null;
        }
        if(foundFeature.getLandings().getId() != user.getLandings().getId()){
            return null;
        }

        return foundFeature;
    }
}

