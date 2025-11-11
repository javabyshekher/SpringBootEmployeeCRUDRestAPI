package in.ashokit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private Instant timestamp;
    private String requestUri;

}
