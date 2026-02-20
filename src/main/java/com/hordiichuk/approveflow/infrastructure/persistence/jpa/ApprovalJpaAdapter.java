package com.hordiichuk.approveflow.infrastructure.persistence.jpa;

import com.hordiichuk.approveflow.application.approval.port.out.LoadApprovalPort;
import com.hordiichuk.approveflow.application.approval.port.out.SaveApprovalPort;
import com.hordiichuk.approveflow.domain.approval.model.Approval;
import com.hordiichuk.approveflow.infrastructure.persistence.jpa.mapper.ApprovalJpaMapper;
import com.hordiichuk.approveflow.infrastructure.persistence.jpa.repo.ApprovalJpaRepository;
import com.hordiichuk.approveflow.shared.id.ApprovalId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Profile("!test")
@Transactional
public class ApprovalJpaAdapter implements LoadApprovalPort, SaveApprovalPort {

    private final ApprovalJpaRepository repo;
    private final ApprovalJpaMapper mapper = new ApprovalJpaMapper();

    public ApprovalJpaAdapter(ApprovalJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Approval> load(ApprovalId id){
        return repo.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public void save(Approval approval) {
        repo.save(mapper.toEntity(approval));
    }
}