package com.chatbot;

import java.util.List;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import domain.Says;

@Service
public interface SaysRepository extends PagingAndSortingRepository<Says, String> {
		List<Says> findByInput(String input);
		
}
