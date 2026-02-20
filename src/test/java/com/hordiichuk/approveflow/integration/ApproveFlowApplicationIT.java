package com.hordiichuk.approveflow.integration;

import com.hordiichuk.approveflow.testconfig.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ApproveFlowApplicationIT {
    @Test void contextLoads() {}
}