package gamifiedproject.Model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questiontitle;
    
    private String questionOption1;

    private String questionOption2;
   
    private String questionOption3;
  
    private String questionOption4;
    
    

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
    
   
    @OneToOne
    @JoinColumn(name = "question")
    private Lesson lesson;

   
}
