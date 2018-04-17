package com.spring.votingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.votingsystem.entity.Candidate;;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Integer>{

	public Candidate findById(long id);
}
