package com.plataforma.portafolios.dto;

import com.plataforma.portafolios.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ChatMessage {
    private String content;
    private Long senderProfileId;
    private Long receiverProfileId;
}
