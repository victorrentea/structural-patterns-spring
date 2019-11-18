package victor.training.oo.structural.facade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Customer {
	private Long id;
	private String name;
	private String email;
	private Site site;
}
