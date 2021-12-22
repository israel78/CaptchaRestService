package es.captcha.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import es.captcha.domain.CaptionResponse;

import java.io.IOException;

public class CaptionsDeserializer extends JsonDeserializer<CaptionResponse> {
    @Override
    public CaptionResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        CaptionResponse captionResponse = new CaptionResponse();
        captionResponse.setConfidence(node.get("confidence")!=null?node.get("confidence").asDouble():0);
        captionResponse.setText(node.get("text")!=null?node.get("text").asText():null);
        return  captionResponse;
    }
}
