package com.hordiichuk.approveflow.application.approval.usecase;

import com.hordiichuk.approveflow.application.approval.port.out.SaveApprovalPort;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

import java.util.UUID;

public class CreateApprovalUseCase {

    private final SaveApprovalPort savePort;

    public CreateApprovalUseCase(SaveApprovalPort savePort) {
        this.savePort = savePort;
    }

    public ApprovalId create(){
        ApprovalId id = new ApprovalId(UUID.randomUUID());
        Approval approval = Approval.createNew(id);

        savePort.save(approval);

        return id;
    }
}
