package com.f1data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.f1data.domain.RaceTable;

@Repository
public interface RaceTableRepository extends MongoRepository<RaceTable, String> {

}
