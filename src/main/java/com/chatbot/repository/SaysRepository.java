package com.chatbot.repository;

import java.util.List;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.chatbot.model.Says;

@Service
public interface SaysRepository extends PagingAndSortingRepository<Says, String> {
		List<Says> findALLByInput(String input);
		
}
