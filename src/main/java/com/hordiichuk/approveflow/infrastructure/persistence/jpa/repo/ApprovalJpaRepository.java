package com.hordiichuk.approveflow.infrastructure.persistence.jpa.repo;

import com.hordiichuk.approveflow.infrastructure.persistence.jpa.entity.ApprovalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApprovalJpaRepository extends JpaRepository<ApprovalJpaEntity, UUID> {
}