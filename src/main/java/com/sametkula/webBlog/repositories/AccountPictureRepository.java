package com.sametkula.webBlog.repositories;

import com.sametkula.webBlog.model.AccountPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountPictureRepository extends JpaRepository<AccountPicture, UUID> {
}
