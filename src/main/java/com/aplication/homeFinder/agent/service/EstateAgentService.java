package com.aplication.homeFinder.agent.service;

import com.aplication.homeFinder.agent.model.EstateAgent;
import com.aplication.homeFinder.agent.respository.EstateAgentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
public class EstateAgentService {

    private final EstateAgentRepository estateAgentRepository;
    private final AgentMapper agentMapper;

    public EstateAgent addAgent(EstateAgentDto estateAgentDto) {
        return estateAgentRepository.save(agentMapper.mapper(estateAgentDto));
    }

    public List<EstateAgentDto> findAgent() {
        return estateAgentRepository
                .findAll()
                .stream()
                .map(agentMapper::mapAgent)
                .toList();
    }

    public void deleteAgent(Long id) {
        estateAgentRepository.deleteById(id);
    }

}
