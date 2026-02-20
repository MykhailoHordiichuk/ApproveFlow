package com.hordiichuk.approveflow.integration;

import com.hordiichuk.approveflow.testconfig.TestcontainersConfiguration;
import com.hordiichuk.approveflow.domain.approval.model.Decision;
import com.hordiichuk.approveflow.application.approval.usecase.CreateApprovalUseCase;
import com.hordiichuk.approveflow.application.approval.usecase.DecideApprovalUseCase;
import com.hordiichuk.approveflow.application.approval.usecase.GetApprovalUseCase;
import com.hordiichuk.approveflow.application.approval.usecase.SubmitApprovalUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class ApprovalFlowIT {

    @Autowired
    CreateApprovalUseCase create;
    @Autowired
    SubmitApprovalUseCase submit;
    @Autowired
    DecideApprovalUseCase decide;
    @Autowired
    GetApprovalUseCase get;

    @Test
    void full_persistence_flow_works() {

        var id = create.create();

        submit.submit(id);

        decide.decide(id, Decision.APPROVE);

        var view = get.get(id);

        assertThat(view.status()).isEqualTo("APPROVED");
    }
}