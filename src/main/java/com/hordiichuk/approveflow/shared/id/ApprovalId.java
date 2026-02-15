package com.hordiichuk.approveflow.shared.id;

import java.util.UUID;

public record ApprovalId(UUID value) {
    public static ApprovalId newId(){
        return new ApprovalId(UUID.randomUUID());
    }
}
