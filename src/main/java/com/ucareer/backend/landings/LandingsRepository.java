package com.ucareer.backend.landings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LandingsRepository extends JpaRepository<Landings, Long> {
    Landings findDistinctById(Long id);
}
