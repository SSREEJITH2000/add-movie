package moviebookingsystem.contract.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import moviebookingsystem.constant.Genre;

@Getter
@Setter
public class MovieRequest {
    @NotBlank
    private String name;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
