package com.ucareer.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Long> {
    //List<Computer> getComputerByPriceGreaterThan(int Price);
}
