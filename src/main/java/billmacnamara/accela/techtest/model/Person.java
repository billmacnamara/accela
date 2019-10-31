package billmacnamara.accela.techtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private Long id;

    @Column(name = "firstname", length = 25)
    private String firstName;
    @Column(name = "lastname", length = 25)
    private String lastName;

    @Override
    public String toString() {
        return String.format("ID: %1$s; Name: %2$s %3$s", this.getId(), this.getFirstName(), this.getLastName());
    }

}
