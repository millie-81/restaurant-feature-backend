package com.ucareer.backend.features;

import com.ucareer.backend.landings.LandingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {
    final
    FeatureRepository featureRepository;
    @Autowired
    LandingsRepository landingsRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    // find all features
    public List<Feature>  getFeatures(Long landingsId){
        return featureRepository.findByLandingsId(landingsId);
    }

    // find one feature
    public Feature getFeature(Long id, Long landingsId){
        return featureRepository.findByIdAndLandingsId(id,landingsId);
    }

    //create a feature
    public Feature createFeature(Feature feature,Long landingsId){
       landingsRepository.findById(landingsId)
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
    public Boolean deleteFeature(Long id,Long landingsId){
        return featureRepository.deleteByIdAndLandingsId(id, landingsId);
    }
}
