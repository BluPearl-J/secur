package org.example.dtos.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.data.models.Role;

@Data
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private Role role;
}
