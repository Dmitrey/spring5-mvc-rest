package guru.springfamework.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    @Schema(description = "this is the first name", name = "first name", required = true, example = "ivan")
    private String firstname;
    @Schema(description = "this is the last name", required = true)
    private String lastname;
    private String customerUrl;
}
