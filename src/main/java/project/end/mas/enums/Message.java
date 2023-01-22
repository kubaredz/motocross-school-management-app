package project.end.mas.enums;

import lombok.Getter;

public enum Message {
    FAILED_JOIN("failed"),
    SUCCESS_JOIN("success"),
    MOTOCROSS_NONE("motocross-none"),
    TOURNAMENTS_NONE("tournaments-none");

    @Getter
    String message;

    Message(String message) {
        this.message = message;
    }
}
