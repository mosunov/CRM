package com.crm.exception;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Data
@NoArgsConstructor
@ControllerAdvice
public class NotValidExceptionHandler extends ResponseEntityExceptionHandler {

    private Error error;

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                      Model model,
                                                      ServletWebRequest webRequest) {
        error = new Error();
        error.setErrorMessage(String.format("The parameter '%s' of value '%s' couldn't be converted to type '%s'",
                ex.getParameter().getParameterName(),ex.getValue(),ex.getRequiredType()));
        error.setErrorCode(ex.getErrorCode());
        String url = webRequest.getHeader("referer").replace("http://localhost:8080","");
        error.setUrl(url);
        model.addAttribute("error",error);
        return "error";
    }

}
