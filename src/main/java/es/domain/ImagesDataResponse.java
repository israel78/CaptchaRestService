package es.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImagesDataResponse implements Serializable {

    private List<faceResponse> faceResponseList;
    private DescriptionResponse descriptionResponse;
}
