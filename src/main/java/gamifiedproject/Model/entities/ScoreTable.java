package gamifiedproject.Model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "scoretable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score_range") // Renamed from "range"
    private Integer scoreRange;

    @Column(unique = true, name = "gmail")
    private String gmail;

    @Column(name="stars")
    private double stars;

    @Column(name="totalscore")
    private double totalScore;

    @OneToOne(mappedBy = "scoretable")
    private Course course;
}

