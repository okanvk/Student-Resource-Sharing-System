package com.vukat.studentresource.repository;

import com.vukat.studentresource.domain.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends CrudRepository<Resource,Long> {



}
