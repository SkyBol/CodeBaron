package ch.asu.chaosdb.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class ResponseError implements Serializable {
  private LocalDateTime timeStamp;
  private Map<String, String> errors;

  ResponseError(String messageKey, String message) {
    Map<String, String> errors = new HashMap<>();
    errors.put(messageKey, message);
    this.setErrors(errors);
    this.setTimeStamp(LocalDateTime.now());
  }

  ResponseError(Map<String, String> errors) {
    this.setErrors(errors);
    this.setTimeStamp(LocalDateTime.now());
  }
}
