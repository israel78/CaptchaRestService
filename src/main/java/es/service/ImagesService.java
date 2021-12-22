package es.service;

import es.captcha.domain.ImagesDataResponse;

import java.util.ArrayList;

public interface ImagesService {

    public ArrayList<ImagesDataResponse> getProcessImages(ArrayList<String> ImageUrl);
}
