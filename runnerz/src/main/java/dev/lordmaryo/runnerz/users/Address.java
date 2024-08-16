package dev.lordmaryo.runnerz.users;

public record Address(
        String street,
        String city,
        String state,
        String zipCode,
        String suite
) {
}
