package com.spring.votingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="candidates")
@Entity
public class Candidate {
	
	@Id
	@Column(name="id")
	private Long id;
	
	public Candidate(Long id, String candidate_name) {
		super();
		this.id = id;
		this.candidate_name = candidate_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidate_name() {
		return candidate_name;
	}

	public void setCandidate_name(String candidate_name) {
		this.candidate_name = candidate_name;
	}

	
	public Candidate() {
		super();
	}


	@Column(name="candidate_name")
	public String candidate_name;
	
	@Column(name="numberOfVotes")
	private Integer numberOfVotes;

	public Integer getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setNumberOfVotes(Integer numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
		
}
