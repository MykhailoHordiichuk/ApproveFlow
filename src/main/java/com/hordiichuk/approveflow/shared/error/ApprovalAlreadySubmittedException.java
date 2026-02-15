package com.hordiichuk.approveflow.shared.error;

import com.hordiichuk.approveflow.shared.id.ApprovalId;

public class ApprovalAlreadySubmittedException extends RuntimeException {
    public ApprovalAlreadySubmittedException(ApprovalId id) {
        super("Approval already submitted: " + id.value());
    }
}
