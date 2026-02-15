package com.hordiichuk.approveflow.application.approval.usecase;

import com.hordiichuk.approveflow.application.approval.port.out.LoadApprovalPort;
import com.hordiichuk.approveflow.application.approval.port.out.SaveApprovalPort;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.shared.error.ApprovalNotFoundException;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

public class SubmitApprovalUseCase {

    private final LoadApprovalPort loadPort;
    private final SaveApprovalPort savePort;

    public SubmitApprovalUseCase(LoadApprovalPort loadPort, SaveApprovalPort savePort) {
        this.loadPort = loadPort;
        this.savePort = savePort;
    }

    public void submit(ApprovalId id) {
        Approval approval = loadPort.load(id)
                .orElseThrow(() -> new ApprovalNotFoundException(id));

        approval.submit();
        savePort.save(approval);
    }
}