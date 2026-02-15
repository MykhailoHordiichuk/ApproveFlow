package com.hordiichuk.approveflow;

import org.springframework.boot.SpringApplication;

public class TestApproveFlowApplication {

    public static void main(String[] args) {
        SpringApplication.from(ApproveFlowApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
