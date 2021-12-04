package es.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import es.Rest.ImagesRest;
import es.Rest.deserializer.ImagesInfoDeserializer;
import es.domain.ImagesDataResponse;
import io.reactivex.Observable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImagesRest imagesRest;

    @Override
    public ArrayList<ImagesDataResponse> getProcessImages(ArrayList<String> ImageUrlList) {
    ArrayList<ImagesDataResponse> imagesDataResponseArrayList = new ArrayList();
        Observable observable = Observable.fromIterable(ImageUrlList);
        observable.subscribe(
                i -> {
                    ObjectMapper mapper = new ObjectMapper();
                    SimpleModule module = new SimpleModule();
                    module.addDeserializer(ImagesDataResponse.class, new ImagesInfoDeserializer());
                    mapper.registerModule(module);
                    JSONObject result =  imagesRest.callapi(i.toString(),"Faces");
                    //todo mapeo objeto primera parte
                    JSONObject result2 = imagesRest.callapi(i.toString(),"Description");
                    //todo segundo mapeo
                    ImagesDataResponse imagesDataResponse = mapper.readValue(result,result2, ImagesDataResponse.class);
                    imagesDataResponseArrayList.add(imagesDataResponse);
                } //OnNext



        );
        return imagesDataResponseArrayList;
    }
}
