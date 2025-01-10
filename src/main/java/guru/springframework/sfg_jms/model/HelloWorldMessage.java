package guru.springframework.sfg_jms.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable {

	private static final long serialVersionUID = -4247165004247542390L;
	private UUID id;	
	private String message;
}
