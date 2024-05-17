package gamifiedproject.Model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gamifiedproject.Model.Repository.AnswerRepository;
import gamifiedproject.Model.entities.Answer;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private  AnswerRepository answerRepository;

    

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}
