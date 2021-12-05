package es.Rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import es.domain.DescriptionResponse;
import es.domain.ImagesDataResponse;
import es.domain.faceResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ImagesInfoDeserializer extends JsonDeserializer<ImagesDataResponse> {
    @Override
    public ImagesDataResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        ImagesDataResponse imagesDataResponse = new ImagesDataResponse();
        if(node.get("faces")!=null&&node.get("faces").get(0)!=null){
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(faceResponse.class, new FacesDeserializer());
            mapper.registerModule(module);
            TypeReference<ArrayList<faceResponse>> tRef = new TypeReference<ArrayList<faceResponse>>() {};
            ArrayList <faceResponse> gradesList = mapper.readValue(node.get("faces").toString(), tRef);
            imagesDataResponse.setFaceResponseList(gradesList);
            mapper = new ObjectMapper();
            module = new SimpleModule();
            module.addDeserializer(DescriptionResponse.class, new DescriptionDeserializer());
            mapper.registerModule(module);
            DescriptionResponse descriptionResponse = mapper.readValue(node.get("object2").get("description").toString(), DescriptionResponse.class);
            imagesDataResponse.setDescriptionResponse(descriptionResponse);
        }
        return imagesDataResponse;
    }
}
