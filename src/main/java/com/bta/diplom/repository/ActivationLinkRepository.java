package com.bta.diplom.repository;

import com.bta.diplom.model.ActivationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationLinkRepository
    extends JpaRepository<ActivationLink, Long> {
}
