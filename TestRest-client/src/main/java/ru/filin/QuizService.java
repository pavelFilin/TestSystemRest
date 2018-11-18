package ru.filin;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.filin.DTO.QuizDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("rest/users")
public interface QuizService extends RestService {
    @GET
    public void findAll(MethodCallback<List<QuizDTO>> quizzes);

    @GET
    @Path("{id}")
    public void findByID(@PathParam("id") long id, MethodCallback<QuizDTO> quiz);
}

