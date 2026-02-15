package com.hordiichuk.approveflow.domain.approval.model;

import com.hordiichuk.approveflow.shared.error.ApprovalAlreadySubmittedException;
import com.hordiichuk.approveflow.shared.id.ApprovalId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApprovalTest {

    @Test
    void newApproval_isDraft(){
        var approval = Approval.createNew(ApprovalId.newId());
        assertEquals(ApprovalStatus.DRAFT, approval.status());
    }

    @Test
    void submit_changesStatusToSubmitted(){
        var approval = Approval.createNew(ApprovalId.newId());

        approval.submit();

        assertEquals(ApprovalStatus.SUBMITTED, approval.status());
    }

    @Test
    void submit_notAllowedIfAlreadySubmitted(){
        var approval = Approval.createNew(ApprovalId.newId());
        approval.submit();

        assertThrows(ApprovalAlreadySubmittedException.class, approval::submit);
    }
}
