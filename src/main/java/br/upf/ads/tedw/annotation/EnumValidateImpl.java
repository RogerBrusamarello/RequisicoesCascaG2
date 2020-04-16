package br.upf.ads.tedw.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidateImpl implements ConstraintValidator<EnumValidate, Object> {

	Class<? extends Enum<?>> enumClass;
	
	@Override
	public void initialize(EnumValidate constraintAnnotation) { 
		this.enumClass = constraintAnnotation.enumClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {		
		try {
			for (Enum<?> element : enumClass.getEnumConstants()) {
				if (element.toString().equals(value.toString())) {
					return true;
				}
			}
			return false;
		} catch (Throwable ex) {
			return false;
		}
	}

}
