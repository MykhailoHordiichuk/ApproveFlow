package com.hordiichuk.approveflow.integration;

import com.hordiichuk.approveflow.testconfig.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class PersistenceSmokeIT {

    @Autowired
    JdbcTemplate jdbc;

    @Test
    void flywayCreatedApprovalsTable() {
        Integer count = jdbc.queryForObject(
                "select count(*) from information_schema.tables " +
                        "where table_schema='public' and table_name='approvals'",
                Integer.class
        );

        assertThat(count).isEqualTo(1);
    }
}