package com.hordiichuk.approveflow.shared.error;

import com.hordiichuk.approveflow.shared.id.ApprovalId;

public class ApprovalNotFoundException extends RuntimeException {
    public ApprovalNotFoundException(ApprovalId id) {
        super("Approval not found: " + id.value());
    }
}