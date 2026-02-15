package com.hordiichuk.approveflow.infrastructure.config;

import com.hordiichuk.approveflow.application.approval.port.out.LoadApprovalPort;
import com.hordiichuk.approveflow.application.approval.port.out.SaveApprovalPort;
import com.hordiichuk.approveflow.application.approval.usecase.CreateApprovalUseCase;
import com.hordiichuk.approveflow.application.approval.usecase.GetApprovalUseCase;
import com.hordiichuk.approveflow.application.approval.usecase.SubmitApprovalUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    CreateApprovalUseCase createApprovalUseCase(SaveApprovalPort saveApprovalPort) {
        return new CreateApprovalUseCase(saveApprovalPort);
    };

    @Bean
    public SubmitApprovalUseCase submitApprovalUseCase(LoadApprovalPort loadPort, SaveApprovalPort savePort) {
        return new SubmitApprovalUseCase(loadPort, savePort);
    }

    @Bean
    public GetApprovalUseCase getApprovalUseCase(LoadApprovalPort loadApprovalPort) {
        return new GetApprovalUseCase(loadApprovalPort);
    }
}