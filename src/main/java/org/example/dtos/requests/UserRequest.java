package org.example.dtos.requests;
import lombok.Data;
import org.example.data.models.Role;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
