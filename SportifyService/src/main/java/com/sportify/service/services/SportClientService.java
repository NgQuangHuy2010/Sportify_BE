package com.sportify.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportify.service.entities.Sport;
import com.sportify.service.repositories.SportClientRepository;

@Service
public class SportClientService {
	
	@Autowired
	private SportClientRepository sportClientRepository;

	public List<Sport> saveSports(List<Sport> sports) {
		return sportClientRepository.saveAll(sports);
	}
}
