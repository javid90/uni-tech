package az.unibank.unitech.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.springframework.http.HttpStatus;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse<T> {

    private HttpStatus status;
    private T data;
    private Object errors;
    private Object metadata;

    public static <T> RestResponse<T> ok() {
        
        RestResponse<T> response = new RestResponse<>();
        response.setStatus(HttpStatus.OK);
        
        return response;
        
    }

}

