package az.unibank.unitech.exception;

import java.util.Date;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDetails {

    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String errorDetail;
    private String path;

    public ErrorDetails(Date timestamp, HttpStatus status, String message, String errorDetail, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.errorDetail = errorDetail;
        this.path = path;
    }

}
