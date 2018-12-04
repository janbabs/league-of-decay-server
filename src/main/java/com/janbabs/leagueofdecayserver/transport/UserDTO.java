package com.janbabs.leagueofdecayserver.transport;

import com.janbabs.leagueofdecayserver.user.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDTO {
    @NotNull
    @Size(max = 20, min = 6, message = "Username must be between 6 and 20 characters!")
    private String username;
    @NotNull
    @Size(max = 30, min = 8, message = "Password must contains between 8 and 30 characters!")
    private String password;
    @NotNull
    private List<Role> roles;

    public UserDTO(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
