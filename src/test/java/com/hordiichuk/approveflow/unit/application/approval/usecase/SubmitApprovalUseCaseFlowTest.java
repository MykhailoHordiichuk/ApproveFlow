package com.hordiichuk.approveflow.unit.application.approval.usecase;

import com.hordiichuk.approveflow.application.approval.usecase.SubmitApprovalUseCase;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.domain.approval.model.ApprovalStatus;
import com.hordiichuk.approveflow.shared.id.ApprovalId;
import com.hordiichuk.approveflow.infrastructure.persistence.inmemory.InMemoryApprovalRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SubmitApprovalUseCaseFlowTest {

    @Test
    void submit_flow_works_without_mockito() {
        var repo = new InMemoryApprovalRepository();
        var useCase = new SubmitApprovalUseCase(repo, repo);

        ApprovalId id = new ApprovalId(UUID.randomUUID());
        repo.save(Approval.createNew(id));

        useCase.submit(id);

        Approval reloaded = repo.load(id).orElseThrow();
        assertEquals(ApprovalStatus.SUBMITTED, reloaded.status());
    }
}