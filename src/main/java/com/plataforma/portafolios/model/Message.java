package com.plataforma.portafolios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_profile_id")
    private Profile sender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_profile_id")
    private Profile receiver;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne()
    @JoinColumn(name = "chat_id")
    private Chat chat;


}

