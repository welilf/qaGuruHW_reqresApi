package models.request;

import lombok.Data;

@Data
public class AuthBodyModel {
    private String email;
    private String password;
}