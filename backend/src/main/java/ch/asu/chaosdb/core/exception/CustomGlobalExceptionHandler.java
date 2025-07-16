package ch.asu.chaosdb.core.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
@AllArgsConstructor
public class CustomGlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseError handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    return new ResponseError(
        ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(
                Collectors.toMap(
                    FieldError::getField,
                    DefaultMessageSourceResolvable::getDefaultMessage
                )
            )
    );
  }

  @ExceptionHandler({NoSuchElementException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseError handleNoSuchElement() {
    return new ResponseError("element", "Element wurde nicht gefunden");
  }

  @ExceptionHandler({UsernameNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseError handleUsernameNotFound(Throwable e) {
    return new ResponseError("username", String.format("Email %s wurde nicht gefunden", e.getMessage()));
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseError handleHttp(Throwable e) {
    return new ResponseError("status", e.getMessage());
  }

  @ExceptionHandler({MultipartException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseError handleMultipartException(Throwable e) {
    return new ResponseError("multipart", e.getMessage());
  }

  @ExceptionHandler({FileNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ResponseError handleFileNotFound(Throwable e) {
    return new ResponseError("file", e.getMessage());
  }

  @ExceptionHandler({IOException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseError handleIOException(Throwable e) {
    return new ResponseError("ioException", e.getMessage());
  }

  @ExceptionHandler({RuntimeException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseError handleRuntimeException(Throwable e) {
    return new ResponseError("runtimeException", e.getMessage());
  }
}


