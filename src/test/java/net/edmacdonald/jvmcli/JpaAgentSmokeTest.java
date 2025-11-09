package net.edmacdonald.jvmcli;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class JpaAgentSmokeTest {

    @Autowired EntityManager em;

    @Test
    @Transactional
    void touchHibernate() {
        // access EM to ensure Hibernate initializes and ServiceLoader paths are exercised
        em.isOpen();
    }
}

