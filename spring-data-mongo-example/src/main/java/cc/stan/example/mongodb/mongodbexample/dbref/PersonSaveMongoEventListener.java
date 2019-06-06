package cc.stan.example.mongodb.mongodbexample.dbref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class PersonSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if ((source instanceof Person) && (((Person) source).getEmailAddress() != null)) {
            mongoOperations.save(((Person) source).getEmailAddress());
        }
    }
}
