package ua.com.foxminded.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ErrorBilder {

    public ResponseEntity fillErrors(BindingResult bindingResult, String object) {
        List<SubError>  subErrors = new ArrayList<SubError>();
        bindingResult.getFieldErrors().forEach(error ->
        {
            subErrors.add(new SubError().setField(error.getField())
                    .setMessage(error.getDefaultMessage()).setObject(object));

        });
      
      return new ResponseEntity(new ErrorValidDescriptor().setSubErrors(subErrors), HttpStatus.BAD_REQUEST);
    }    
}
