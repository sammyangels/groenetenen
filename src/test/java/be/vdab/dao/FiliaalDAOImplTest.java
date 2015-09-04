package be.vdab.dao;

import be.vdab.datasource.CreateTestDataSourceBean;
import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CreateTestDataSourceBean.class, CreateDAOBeans.class})
@Transactional
public class FiliaalDAOImplTest {
    @Autowired
    private FiliaalDAO filiaalDAO;

    @Test
    public void testCreate() throws Exception {
        Filiaal filiaal = new Filiaal("TestNaam", true, BigDecimal.ONE, new Date(), new Adres("Straat", "HuisNr", 1000, "Gemeente"));
        filiaal = filiaalDAO.save(filiaal);
        assertNotEquals(0, filiaal.getId());
    }
}