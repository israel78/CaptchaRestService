package es.service;

import java.util.ArrayList;

public interface ImagesService {

    public ArrayList<ImagesDataResponse> getProcessImages(ArrayList<String> ImageUrl);
}
