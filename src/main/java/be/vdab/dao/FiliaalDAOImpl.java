package be.vdab.dao;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.PostcodeReeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class FiliaalDAOImpl implements FiliaalDAO {

    private final Map<Long, Filiaal> filialen = new ConcurrentHashMap<>();
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final FiliaalRowMapper rowMapper = new FiliaalRowMapper();

    private static final String BEGIN_SQL =
            "select id, naam, hoofdFiliaal, straat, huisNr, postcode, gemeente," +
                    "inGebruikName, waardeGebouw from filialen ";
    private static final String SQL_FIND_ALL = BEGIN_SQL + "order by naam";

    @Autowired
    FiliaalDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void create(Filiaal filiaal) {
        filiaal.setId(Collections.max(filialen.keySet()) + 1);
        filialen.put(filiaal.getId(), filiaal);
    }

    @Override
    public Filiaal read(long id) {
        return filialen.get(id);
    }

    @Override
    public void update(Filiaal filiaal) {
        filialen.put(filiaal.getId(), filiaal);
    }

    @Override
    public void delete(long id) {
        filialen.remove(id);
    }

    @Override
    public List<Filiaal> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public long findAantalFilialen() {
        return filialen.size();
    }

    @Override
    public long findAantalWerknemers(long id) {
        return id == 1L ? 7L : 0L;
    }

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
        List<Filiaal> filialen = new ArrayList<>();
        for (Filiaal filiaal : this.filialen.values()) {
            if (reeks.bevat(filiaal.getAdres().getPostcode())) {
                filialen.add(filiaal);
            }
        }
        return filialen;
    }

    private static class FiliaalRowMapper implements RowMapper<Filiaal> {
        @Override
        public Filiaal mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return new Filiaal(resultSet.getLong("id"), resultSet.getString("naam"),
                    resultSet.getBoolean("hoofdfiliaal"), resultSet.getBigDecimal("waardeGebouw"), resultSet.getDate("inGebruikName"),
                    new Adres(resultSet.getString("straat"),resultSet.getString("huisNr"),
                    resultSet.getInt("postcode"), resultSet.getString("gemeente")));
        }
    }
}
