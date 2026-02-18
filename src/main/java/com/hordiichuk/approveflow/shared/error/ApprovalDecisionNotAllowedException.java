package com.hordiichuk.approveflow.shared.error;

import com.hordiichuk.approveflow.domain.approval.model.ApprovalStatus;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

public class ApprovalDecisionNotAllowedException extends RuntimeException {
    public ApprovalDecisionNotAllowedException(ApprovalId id, ApprovalStatus status) {
        super("Decision not allowed for approval " + id.value() + " with status " + status);
    }
}