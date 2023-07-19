package az.inci.bmsreport.model;

import lombok.Data;

@Data
public class User
{
    private String token;
    private String userId;
    private String password;
    private String userName;
    private boolean isAdmin;
    private boolean isSuperVisor;
}
