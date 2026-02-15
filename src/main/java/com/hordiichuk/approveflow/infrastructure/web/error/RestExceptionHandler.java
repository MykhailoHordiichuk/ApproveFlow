package com.hordiichuk.approveflow.infrastructure.web.error;

import com.hordiichuk.approveflow.shared.error.ApprovalAlreadySubmittedException;
import com.hordiichuk.approveflow.shared.error.ApprovalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApprovalNotFoundException.class)
    public ApiError handleNotFound(ApprovalNotFoundException ex) {
        return new ApiError("APPROVAL_NOT_FOUND", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ApprovalAlreadySubmittedException.class)
    public ApiError handleConflict(ApprovalAlreadySubmittedException ex) {
        return new ApiError("APPROVAL_ALREADY_SUBMITTED", ex.getMessage());
    }

    public record ApiError(String code, String message) {}
}