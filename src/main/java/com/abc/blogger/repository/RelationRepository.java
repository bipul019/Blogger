package com.abc.blogger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abc.blogger.entity.Relation;

@Repository
public interface RelationRepository extends CrudRepository<Relation,Integer> {

	List<Relation> findAllByFollower(Integer id);


}