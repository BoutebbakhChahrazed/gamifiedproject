package gamifiedproject.Model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    
//    @OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "score_id", referencedColumnName = "id")
//	private Score score;

    // Autres champs et annotations selon vos besoins

   
}
