package hr.xmjosic.xmjbrewery.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerDto(UUID id, String name) {
}
