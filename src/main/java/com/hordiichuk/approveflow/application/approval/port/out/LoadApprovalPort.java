package com.hordiichuk.approveflow.application.approval.port.out;

import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

import java.util.Optional;

public interface LoadApprovalPort {
    Optional<Approval> load(ApprovalId id);
}