package com.hordiichuk.approveflow.infrastructure.persistence.jpa.mapper;

import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.domain.approval.model.ApprovalStatus;
import com.hordiichuk.approveflow.infrastructure.persistence.jpa.entity.ApprovalJpaEntity;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

public class ApprovalJpaMapper {

    public ApprovalJpaEntity toEntity(Approval domain) {
        return new ApprovalJpaEntity(domain.id().value(), domain.status().name());
    }

    public Approval toDomain (ApprovalJpaEntity entity){
        return Approval.reconstitute(
                new ApprovalId(entity.getId()),
                ApprovalStatus.valueOf(entity.getStatus())
        );
    }
}