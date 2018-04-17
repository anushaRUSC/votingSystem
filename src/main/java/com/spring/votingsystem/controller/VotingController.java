package com.spring.votingsystem.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.votingsystem.entity.Candidate;
import com.spring.votingsystem.entity.Citizen;
import com.spring.votingsystem.repositories.CandidateRepo;
import com.spring.votingsystem.repositories.CitizenRepo;

@Controller
public class VotingController {
	
	public final Logger logger = Logger.getLogger(VotingController.class);
	
	@Autowired
	CitizenRepo citizenRepo;
	
	@Autowired
	CandidateRepo candidateRep;

	@RequestMapping("/")
	public String goToVote() {
		logger.info("Returning vote.html file");
		return "vote.html";
		
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam String name, Model model, HttpSession session) {
		
		logger.info("Getting citizen from database");
		Citizen citizen = citizenRepo.findByName(name);
		
		logger.info("Putting citizen into session");
		session.setAttribute("citizen", citizen);
		
		if(!citizen.getHasVoted()) {
			logger.info("Putting candidates into model");
			List<Candidate> candidates = candidateRep.findAll();
			model.addAttribute("candidates", candidates);
			return "/performVote.html";
		}
		else
			return "/alreadyVoted.html";		
	}
	
	@RequestMapping("/voteFor")
	public String voteFor(@RequestParam Long id, HttpSession session) {
		logger.info("voting");
		Citizen citizen = (Citizen)session.getAttribute("citizen");
		
		if(!citizen.getHasVoted()) {
			citizen.setHasVoted(true);
			Candidate c = candidateRep.findById((long)id);	
			logger.info("voting for candidate " + c.getCandidate_name());
			c.setNumberOfVotes(c.getNumberOfVotes()+1);
			candidateRep.save(c);
			citizenRepo.save(citizen);
			return "voted.html";
		}
		
		return "alreadyVoted.html";
	}
}
