package com.sametkula.webBlog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_picture")
public class AccountPicture {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    @Column(name = "picture_data")
    private byte[] picture;

    @OneToOne(mappedBy = "accountPicture")
    @JoinColumn(name = "account_id")
    private Account account;
}
