package com.aplication.homeFinder.offer.repository;

import com.aplication.homeFinder.offer.model.ClientMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMessageRepository extends JpaRepository<ClientMessage, Long> {

}
