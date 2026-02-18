package com.hordiichuk.approveflow.domain.approval.model;

import com.hordiichuk.approveflow.shared.error.ApprovalDecisionNotAllowedException;
import com.hordiichuk.approveflow.shared.id.ApprovalId;
import com.hordiichuk.approveflow.shared.error.ApprovalAlreadySubmittedException;


import java.util.Objects;

public class Approval {

    private final ApprovalId id;
    private ApprovalStatus status;

    private Approval(ApprovalId id){
        this.id = Objects.requireNonNull(id);
        this.status = ApprovalStatus.DRAFT;
    }

    public static Approval createNew(ApprovalId id){
        return new Approval(id);
    }

    public static Approval reconstitute(ApprovalId id, ApprovalStatus status){
        Approval a = new Approval(Objects.requireNonNull(id));
        a.status = Objects.requireNonNull(status);
        return a;
    }

    public void submit() {
        if (this.status != ApprovalStatus.DRAFT) {
            throw new ApprovalAlreadySubmittedException(id);
        }
        this.status = ApprovalStatus.SUBMITTED;
    }

    public void decide(Decision decision) {
        Objects.requireNonNull(decision, "decision must not be null");

        if (this.status != ApprovalStatus.SUBMITTED) {
            throw new ApprovalDecisionNotAllowedException(id, this.status);
        }

        this.status = (decision == Decision.APPROVE)
                ? ApprovalStatus.APPROVED
                : ApprovalStatus.REJECTED;
    }

    public ApprovalId id (){
        return id;
    }

    public ApprovalStatus status() {
        return status;
    }
}
