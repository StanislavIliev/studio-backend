package studio.demo.model.view;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class BaseViewModel {
        private String id;

        public BaseViewModel() {
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
