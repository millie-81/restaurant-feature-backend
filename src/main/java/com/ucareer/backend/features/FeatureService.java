package com.ucareer.backend.features;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    final
    FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    // find all features
    public List<Feature>  getFeatures(){
        return featureRepository.findAll();
    }

    // find one feature
    public Feature getFeature(Long id){
        return featureRepository.findDistinctById(id);
    }

    //create a feature
    public Feature createFeature(Feature feature){
        return featureRepository.save(feature);
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
    public Boolean deleteFeature(Long id){
        featureRepository.deleteById(id);
        return  true;
    }
}
