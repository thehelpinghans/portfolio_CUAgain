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
@Table(name = "intention")
@Entity
public class ChatBotIntention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    private String name;

    @JoinColumn
    @ManyToOne
    private Answer answer;
    @JoinColumn
    @ManyToOne
    private ChatBotIntention upper;

}
