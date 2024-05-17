package gamifiedproject.Model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamifiedproject.Model.Repository.QuestionRepository;
import gamifiedproject.Model.entities.Question;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private  QuestionRepository questionRepository;

    
   

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
