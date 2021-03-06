package business.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotBlank
    @NotEmpty
    @NotNull
    private String userName;
    @NotBlank
    @NotEmpty
    @NotNull
    private String password;
    private boolean login;
    private boolean adminUser;
    private ClientDTO clientDTO;

    public UserDTO(@NotBlank @NotEmpty @NotNull String userName, @NotBlank @NotEmpty @NotNull String password, boolean login, boolean adminUser, ClientDTO clientDTO) {
        this.userName = userName;
        this.password = password;
        this.login = login;
        this.adminUser = adminUser;
        this.clientDTO = clientDTO;
    }

    public UserDTO(@NotBlank @NotEmpty @NotNull String userName, @NotBlank @NotEmpty @NotNull String password, boolean login, boolean adminUser) {
        this.userName = userName;
        this.password = password;
        this.login = login;
        this.adminUser = adminUser;
    }

    public UserDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isAdminUser() {
        return adminUser;
    }

    public void setAdminUser(boolean adminUser) {
        this.adminUser = adminUser;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", login=" + login +
                ", adminUser=" + adminUser +
                ", clientDTO=" + clientDTO +
                '}';
    }
}
