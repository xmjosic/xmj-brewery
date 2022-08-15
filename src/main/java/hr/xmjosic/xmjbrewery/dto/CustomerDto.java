package hr.xmjosic.xmjbrewery.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
public record CustomerDto(UUID id, @NotBlank @Size(min = 3, max = 100) String name) {
}
