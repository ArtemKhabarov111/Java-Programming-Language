package org.example;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class PersonTest {
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Person.class).verify();
    }
}
