package com.kien.restwithspring.repository;

import com.kien.restwithspring.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
