package com.hordiichuk.approveflow.application.approval.usecase;

import com.hordiichuk.approveflow.application.approval.port.out.LoadApprovalPort;
import com.hordiichuk.approveflow.application.approval.port.out.SaveApprovalPort;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.domain.approval.model.Decision;
import com.hordiichuk.approveflow.shared.error.ApprovalNotFoundException;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

public class DecideApprovalUseCase {

    private final LoadApprovalPort loadPort;
    private final SaveApprovalPort savePort;

    public DecideApprovalUseCase(LoadApprovalPort loadPort, SaveApprovalPort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    public void decide(ApprovalId id, Decision decision) {
        Approval approval = loadPort.load(id)
                .orElseThrow(() -> new ApprovalNotFoundException(id));

        approval.decide(decision);
        savePort.save(approval);
    }
}