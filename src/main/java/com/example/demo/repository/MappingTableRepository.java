package com.example.demo.repository;
import com.example.demo.entity.MappingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MappingTableRepository extends JpaRepository<MappingTable, Integer> {
    List<MappingTable> findAll();
}

