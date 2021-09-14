package com.ucareer.backend.features;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Long> {
    Feature findByIdAndLandingsId(Long id,Long landingsId);
    List<Feature> findByLandingsId(Long landingsId);
    Boolean deleteByIdAndLandingsId(Long id, Long landingsId);
}
