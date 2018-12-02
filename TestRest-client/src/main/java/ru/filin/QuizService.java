package ru.filin;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.filin.DTO.Quiz;

import javax.ws.rs.*;
import java.util.List;

@Path("rest/quiz")
public interface QuizService extends RestService {
    @GET
    public void findAll(MethodCallback<List<Quiz>> quizzes);

    @GET
    @Path("{id}")
    public void findByID(@PathParam("id") long id, MethodCallback<Quiz> quiz);

    @POST
    public void addQuiz(Quiz quiz, MethodCallback<Void> callback);

    @PUT
    public void updateQuiz(Quiz quiz, MethodCallback<Void> callback);
}

