package demo.dao;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 4543785261816598686L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String username;

    @Column(name = "pass_word")
    private String password;

}
