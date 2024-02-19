package com.plataforma.portafolios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    //IMPORTANT
    //chat with firstUsername and secondUsername is different to a chat with secondUsername and firstUsername
    //example: chatId: 1, firstUsername: Juan, secondUsername: Pedro NOT EQUAL firstUsername: Pedro, secondUsername: Juan
    //it is for finding easier the profile 's chats
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    private String firstUsername;
    private String secondUsername;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Message> messageList = new ArrayList<>();
}
