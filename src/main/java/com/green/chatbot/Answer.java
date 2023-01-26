package com.green.chatbot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Getter
@Table(name = "answer")
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	private String content;

	private String keyword;
	
	public AnswerDTO toAnswerDTO() {
		return AnswerDTO.builder()
				.no(no).content(content).keyword(keyword)
				.build();
	}

}
