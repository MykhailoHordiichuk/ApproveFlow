package com.hordiichuk.approveflow.application.approval.port.out;

import com.hordiichuk.approveflow.domain.approval.model.Approval;

public interface SaveApprovalPort {
    void save(Approval approval);
}
