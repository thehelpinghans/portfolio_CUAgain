package com.green.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatBotIntentionRepository extends JpaRepository<ChatBotIntention, Long> {

	Optional<ChatBotIntention> findByName(String token);

	Optional<ChatBotIntention> findByNameAndUpper(String token, ChatBotIntention upper);
}
