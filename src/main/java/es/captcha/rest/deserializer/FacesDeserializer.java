package es.captcha.rest.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import es.captcha.domain.FaceRectangleResponse;
import es.captcha.domain.FaceResponse;

import java.io.IOException;

public class FacesDeserializer extends JsonDeserializer<FaceResponse> {
    @Override
    public FaceResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        FaceResponse faceResponse = new FaceResponse();
        FaceRectangleResponse faceRectangleResponse = new FaceRectangleResponse();
        faceResponse.setAge(node.get("age")!=null?node.get("age").asInt():0);
        faceResponse.setGender(node.get("gender")!=null?node.get("gender").asText():null);
        if(node.get("faceRectangle")!=null){
            faceRectangleResponse.setHeight(node.get("faceRectangle").get("height")!=null?node.get("faceRectangle").get("height").asInt():0);
            faceRectangleResponse.setTop(node.get("faceRectangle").get("top")!=null?node.get("faceRectangle").get("top").asInt():0);
            faceRectangleResponse.setLeft(node.get("faceRectangle").get("left")!=null?node.get("faceRectangle").get("left").asInt():0);
            faceRectangleResponse.setWidth(node.get("faceRectangle").get("width")!=null?node.get("faceRectangle").get("width").asInt():0);
        }
        faceResponse.setFaceRectangleResponse(faceRectangleResponse);
        return faceResponse;
    }
}
