package com.hordiichuk.approveflow.infrastructure.web.rest;

import com.hordiichuk.approveflow.application.approval.usecase.CreateApprovalUseCase;
import com.hordiichuk.approveflow.application.approval.usecase.GetApprovalUseCase;
import com.hordiichuk.approveflow.application.approval.usecase.SubmitApprovalUseCase;
import com.hordiichuk.approveflow.shared.id.ApprovalId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.net.URI;

import java.util.UUID;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {

    private final SubmitApprovalUseCase submitApprovalUseCase;

    private final CreateApprovalUseCase createApprovalUseCase;

    private final GetApprovalUseCase getApprovalUseCase;

    public ApprovalController(CreateApprovalUseCase createApprovalUseCase,
                              SubmitApprovalUseCase submitApprovalUseCase,
                              GetApprovalUseCase getApprovalUseCase) {
        this.createApprovalUseCase = createApprovalUseCase;
        this.submitApprovalUseCase = submitApprovalUseCase;
        this.getApprovalUseCase = getApprovalUseCase;
    }

    @PostMapping("/{id}/submit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void submit(@PathVariable UUID id) {
        submitApprovalUseCase.submit(new ApprovalId(id));
    }

    @PostMapping
    public ResponseEntity<CreateApprovalResponse> create() {
        var id = createApprovalUseCase.create();
        return ResponseEntity
                .created(URI.create("/approvals/" + id.value()))
                .body(new CreateApprovalResponse(id.value()));
    }
    public record CreateApprovalResponse(UUID id) {}

    @GetMapping("/{id}")
    public GetApprovalResponse get(@PathVariable UUID id) {
        var view = getApprovalUseCase.get(new ApprovalId(id));
        return new GetApprovalResponse(view.id(), view.status());
    }

    public record GetApprovalResponse(UUID id, String status) {}
}
