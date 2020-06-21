package com.toyproject.resourcemanagement.repository;

import com.toyproject.resourcemanagement.model.Server;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends AerospikeRepository<Server,Integer> {
}
