package com.github.mkopylec.recaptcha.validation;

import static java.lang.String.format;

public class RecaptchaValidationException extends RuntimeException {

	private static final long serialVersionUID = -6622097580198174781L;

	public RecaptchaValidationException(String userResponse, String verificationUrl, Throwable cause) {
        super(format("Error validating reCAPTCHA. User response: '%s', verification URL: '%s'", userResponse, verificationUrl), cause);
    }
}
