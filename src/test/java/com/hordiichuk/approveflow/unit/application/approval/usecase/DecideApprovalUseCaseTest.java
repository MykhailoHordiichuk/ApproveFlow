package com.hordiichuk.approveflow.unit.application.approval.usecase;

import com.hordiichuk.approveflow.application.approval.usecase.DecideApprovalUseCase;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.domain.approval.model.ApprovalStatus;
import com.hordiichuk.approveflow.domain.approval.model.Decision;
import com.hordiichuk.approveflow.infrastructure.persistence.inmemory.InMemoryApprovalRepository;
import com.hordiichuk.approveflow.shared.error.ApprovalNotFoundException;
import com.hordiichuk.approveflow.shared.id.ApprovalId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DecideApprovalUseCaseTest {

    @Test
    void decide_approves_when_submitted() {
        var repo = new InMemoryApprovalRepository();
        var uc = new DecideApprovalUseCase(repo, repo);

        var id = ApprovalId.newId();
        var approval = Approval.createNew(id);
        approval.submit();
        repo.save(approval);

        uc.decide(id, Decision.APPROVE);

        assertEquals(ApprovalStatus.APPROVED,
                repo.load(id).orElseThrow().status());
    }

    @Test
    void decide_throws_if_not_found() {
        var repo = new InMemoryApprovalRepository();
        var uc = new DecideApprovalUseCase(repo, repo);

        var id = ApprovalId.newId();

        assertThrows(ApprovalNotFoundException.class,
                () -> uc.decide(id, Decision.APPROVE));
    }
}