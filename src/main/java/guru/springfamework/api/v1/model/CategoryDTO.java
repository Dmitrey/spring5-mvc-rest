package guru.springfamework.api.v1.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by jt on 9/24/17.
 */
@Data
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
}
