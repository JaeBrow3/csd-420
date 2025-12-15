package com.james.dev;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FanDaoTest {

    private static FanDao dao;

    @BeforeAll
    static void setup() {
        dao = new FanDao();
    }

    @Test
    @Order(1)
    void testConnectionWorks() throws Exception {
        try (Connection con = dao.getConnection()) {
            assertNotNull(con);
            assertFalse(con.isClosed());
        }
    }

    @Test
    @Order(2)
    void testGetFanByIdExists() {
        // assumes your table has ID 1 from your inserts
        Fan fan = dao.getFanById(1);
        assertNotNull(fan);
        assertEquals(1, fan.getId());
    }

    @Test
    @Order(3)
    void testUpdateFan() {
        // read first
        Fan fan = dao.getFanById(1);
        assertNotNull(fan);

        // update to new team
        Fan updated = new Fan(1, fan.getFirstName(), fan.getLastName(), "UpdatedTeam");
        boolean ok = dao.updateFan(updated);
        assertTrue(ok);

        // confirm update
        Fan reread = dao.getFanById(1);
        assertNotNull(reread);
        assertEquals("UpdatedTeam", reread.getFavoriteTeam());
    }
}
