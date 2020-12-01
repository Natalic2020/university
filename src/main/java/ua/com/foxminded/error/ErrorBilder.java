package ua.com.foxminded.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ErrorBilder {

    public ResponseEntity fillErrors(BindingResult bindingResult, String object) {
        List<SubError>  subErrors = new ArrayList<SubError>();
        bindingResult.getFieldErrors().forEach(error ->
        {        
            SubError subError = new SubError().setField(error.getField())
                    .setMessage(error.getDefaultMessage())
                    .setObject(object);
            Optional.ofNullable(error.getRejectedValue())
            .ifPresent(errorValue -> subError.setRejectedValue(errorValue.toString()));   
            subErrors.add(subError);    
        });     
      return new ResponseEntity(new ErrorDescriptor().setSubErrors(subErrors), HttpStatus.BAD_REQUEST);
    }    
}
