package mk_tech.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note extends BaseObject {

    private String noteText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name="timecard_id")
    private Timecard timecard;
}
