package studio.demo.model.binding;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class BaseBindingModel {

        private String id;

        public BaseBindingModel() {
        }

        @Id
        @GeneratedValue(generator = "uuid-string")
        @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }



}
