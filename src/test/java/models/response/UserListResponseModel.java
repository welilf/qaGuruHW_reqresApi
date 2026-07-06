package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponseModel {
    private Integer page;
    private List<UserData> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserData {
        private Integer id;
        private String email;
    }
}