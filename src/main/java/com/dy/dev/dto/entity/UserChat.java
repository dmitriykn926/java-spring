package com.dy.dev.dto.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "chat"})
@EqualsAndHashCode(exclude = {"user", "chat"})
@Builder
@Entity
@Table(name = "users_chat")
public class UserChat implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    void setUser(User user) {
        this.user = user;
        this.user.getUserChats().add(this);
    }

    void setChat(Chat chat) {
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }

}
