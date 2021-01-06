package framework.utils.data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.Normalizer;

public class DataConverter {

    public static <T> T convertJsonToObject(String json, Class<T> clazz) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, clazz);
    }

    public static String removeAccents(String text) {
        return text == null ? "" : Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
