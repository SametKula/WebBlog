package com.sametkula.webBlog.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_images")
@ToString
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Lob
    @Column(name = "image", length = 10000)
    private byte[] image;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
}
