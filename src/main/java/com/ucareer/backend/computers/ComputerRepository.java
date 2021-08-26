package com.ucareer.backend.computers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Long> {
    //List<Computer> getComputerByPriceGreaterThan(int Price);
}
