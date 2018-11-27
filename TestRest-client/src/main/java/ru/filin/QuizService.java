package ru.filin;

    import org.fusesource.restygwt.client.Method;
    import org.fusesource.restygwt.client.MethodCallback;
    import org.fusesource.restygwt.client.RestService;
    import ru.filin.DTO.QuizDTO;

    import javax.ws.rs.*;
    import java.util.List;

@Path("rest/quiz")
public interface QuizService extends RestService {
    @GET
    public void findAll(MethodCallback<List<QuizDTO>> quizzes);

    @GET
    @Path("{id}")
    public void findByID(@PathParam("id") long id, MethodCallback<QuizDTO> quiz);

    @POST
    public void addQuiz(QuizDTO quizDTO, MethodCallback<Void> callback);

    @PUT
    public void updateQuiz(QuizDTO quizDTO, MethodCallback<Void> callback);
}

