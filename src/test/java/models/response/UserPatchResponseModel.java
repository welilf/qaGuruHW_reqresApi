package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPatchResponseModel {
    private String name;
    private String job;
    private String updatedAt;
}