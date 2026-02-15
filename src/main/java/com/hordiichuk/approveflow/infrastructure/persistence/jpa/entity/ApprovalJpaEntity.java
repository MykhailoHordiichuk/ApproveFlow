package com.hordiichuk.approveflow.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "approvals")
public class ApprovalJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    protected ApprovalJpaEntity() {}

    public ApprovalJpaEntity(UUID id, String status) {
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }
}
