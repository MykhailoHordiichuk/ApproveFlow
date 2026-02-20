package com.hordiichuk.approveflow.unit.application.approval.usecase;

import com.hordiichuk.approveflow.application.approval.usecase.SubmitApprovalUseCase;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.domain.approval.model.ApprovalStatus;
import com.hordiichuk.approveflow.infrastructure.persistence.inmemory.InMemoryApprovalRepository;
import com.hordiichuk.approveflow.shared.error.ApprovalNotFoundException;
import com.hordiichuk.approveflow.shared.id.ApprovalId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SubmitApprovalUseCaseTest {

    @Test
    void submit_loads_submits_and_saves() {
        var repo = new InMemoryApprovalRepository();
        var uc = new SubmitApprovalUseCase(repo, repo);

        ApprovalId id = new ApprovalId(UUID.randomUUID());
        repo.save(Approval.createNew(id));

        uc.submit(id);

        Approval reloaded = repo.load(id).orElseThrow();
        assertEquals(ApprovalStatus.SUBMITTED, reloaded.status());
    }

    @Test
    void submit_throws_if_not_found() {
        var repo = new InMemoryApprovalRepository();
        var uc = new SubmitApprovalUseCase(repo, repo);

        ApprovalId id = new ApprovalId(UUID.randomUUID());

        assertThrows(ApprovalNotFoundException.class, () -> uc.submit(id));
    }
}