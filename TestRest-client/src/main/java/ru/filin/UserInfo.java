package ru.filin;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.filin.DTO.UserDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/rest/users")
public interface UserInfo extends RestService {
    @GET
    public void getAllUsers(MethodCallback<List<UserDTO>> users);
}
