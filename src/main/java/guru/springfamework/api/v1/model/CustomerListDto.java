package guru.springfamework.api.v1.model;

import guru.springfamework.domain.Customer;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDto {
    private List<CustomerDto> customers;
}
