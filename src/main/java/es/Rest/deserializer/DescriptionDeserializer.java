package es.Rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import es.domain.CaptionResponse;
import es.domain.DescriptionResponse;

import java.io.IOException;
import java.util.ArrayList;

public class DescriptionDeserializer extends JsonDeserializer<DescriptionResponse> {
    @Override
    public DescriptionResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        DescriptionResponse descriptionResponse = new DescriptionResponse();
        CaptionResponse captionResponse = new CaptionResponse();
        ObjectMapper mapper2 = new ObjectMapper();
        SimpleModule  module = new SimpleModule();
        module.addDeserializer(CaptionResponse.class, new CaptionsDeserializer());
        mapper2.registerModule(module);
        TypeReference<ArrayList<CaptionResponse>> tRef = new TypeReference<ArrayList<CaptionResponse>>() {};
        ArrayList<CaptionResponse> captionResponseList= mapper2.readValue(node.get("captions").toString(), tRef);
        descriptionResponse.setCaptionResponseList(captionResponseList);
        descriptionResponse.setTags(mapper.writeValueAsString(node.get("tags")));
        return  descriptionResponse;
    }
}
