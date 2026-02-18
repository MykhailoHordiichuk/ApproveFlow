package com.hordiichuk.approveflow.application.approval.usecase;

import com.hordiichuk.approveflow.application.approval.port.out.LoadApprovalPort;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.shared.error.ApprovalNotFoundException;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

import java.util.UUID;

public class GetApprovalUseCase {

    private final LoadApprovalPort loadPort;

    public GetApprovalUseCase(LoadApprovalPort loadPort) {
        this.loadPort = loadPort;
    }

    public ApprovalView get(ApprovalId id) {
        Approval approval = loadPort.load(id)
                .orElseThrow(() -> new ApprovalNotFoundException(id));

        return new ApprovalView(approval.id().value(), approval.status().name());
    }

    public record ApprovalView(UUID id, String status) {}
}