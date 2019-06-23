package com.cassoviacode.javaws.repository;

import com.cassoviacode.javaws.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    Stream<Building> findAllByActivated(boolean activated);
}
