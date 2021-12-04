package es.Rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class ImagesInfoDeserializer extends JsonDeserializer<ImagesInfoDeserializer> {
    @Override
    public ImagesInfoDeserializer deserialize(JsonParser p,JsonParser p2, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode node2 = p2.getCodec().readTree(p2);


        return new ImagesInfoDeserializer();
    }
}
