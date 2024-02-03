package jfs.backend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException2 extends RuntimeException{

	String resourceName;
	String fieldName;
	String fieldValue;
	public ResourceNotFoundException2(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s : %s" ,resourceName,fieldName,fieldValue ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
}
