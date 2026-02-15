package com.hordiichuk.approveflow.infrastructure.persistence.inmemory;

import com.hordiichuk.approveflow.application.approval.port.out.LoadApprovalPort;
import com.hordiichuk.approveflow.application.approval.port.out.SaveApprovalPort;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.shared.id.ApprovalId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

public class InMemoryApprovalRepository implements LoadApprovalPort, SaveApprovalPort {

    private final Map<ApprovalId, Approval> store = new HashMap<>();

    @Override
    public Optional<Approval> load(ApprovalId id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void save(Approval approval) {
        store.put(approval.id(), approval);
    }
}