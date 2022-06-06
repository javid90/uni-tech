package az.unibank.unitech.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import az.unibank.unitech.exception.GeneralException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utility {
	
	public static String convertToJsonString(final Object obj) {

        try {

            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {

            throw new GeneralException(e.getMessage());

        }

    }

}
