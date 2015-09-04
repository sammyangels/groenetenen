package be.vdab.dao;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.PostcodeReeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class FiliaalDAOImpl implements FiliaalDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final FiliaalRowMapper rowMapper = new FiliaalRowMapper();
    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final String BEGIN_SQL =
            "select id, naam, hoofdFiliaal, straat, huisNr, postcode, gemeente," +
                    "inGebruikName, waardeGebouw from filialen ";
    private static final String SQL_FIND_ALL = BEGIN_SQL + "order by naam";
    private static final String SQL_FIND_BY_POSTCODE = BEGIN_SQL + "where postcode between :van and :tot order by naam";
    private static final String SQL_READ = BEGIN_SQL + "where id = :id";
    private static final String SQL_FIND_AANTAL_FILIALEN = "select count(*) from filialen";
    private static final String SQL_FIND_AANTAL_WERKNEMERS = "select count(*) from werknemers where filiaalId = ?";
    private static final String SQL_DELETE = "delete from filialen where id = ?";
    private static final String SQL_UPDATE = "update filialen set naam=?, hoofdFiliaal=?, straat=?, huisNr=?," +
            "postcode=?, gemeente=?, inGebruikName=?, waardeGebouw=? where id=?";


    @Autowired
    FiliaalDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("filialen");
        simpleJdbcInsert.usingGeneratedKeyColumns("id");
    }

    @Override
    public void create(Filiaal filiaal) {
        Map<String, Object> kolomWaarden = new HashMap<>();
        kolomWaarden.put("naam", filiaal.getNaam());
        kolomWaarden.put("hoofdFiliaal", filiaal.isHoofdFiliaal());
        kolomWaarden.put("straat", filiaal.getAdres().getStraat());
        kolomWaarden.put("huisNr", filiaal.getAdres().getHuisNr());
        kolomWaarden.put("postcode", filiaal.getAdres().getPostcode());
        kolomWaarden.put("gemeente", filiaal.getAdres().getGemeente());
        kolomWaarden.put("inGebruikName", filiaal.getInGebruikName());
        kolomWaarden.put("waardeGebouw", filiaal.getWaardeGebouw());
        Number id = simpleJdbcInsert.executeAndReturnKey(kolomWaarden);
        filiaal.setId(id.longValue());
    }

    @Override
    public Filiaal read(long id) {
        Map<String, Long> parameters = Collections.singletonMap("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(SQL_READ, parameters, rowMapper);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Filiaal filiaal) {
        jdbcTemplate.update(SQL_UPDATE, filiaal.getNaam(), filiaal.isHoofdFiliaal(),
                filiaal.getAdres().getStraat(), filiaal.getAdres().getHuisNr(),
                filiaal.getAdres().getPostcode(), filiaal.getAdres().getGemeente(),
                filiaal.getInGebruikName(), filiaal.getWaardeGebouw(), filiaal.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public List<Filiaal> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public long findAantalFilialen() {
        return jdbcTemplate.queryForObject(SQL_FIND_AANTAL_FILIALEN, Long.class);
    }

    @Override
    public long findAantalWerknemers(long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_AANTAL_WERKNEMERS, Long.class, id);
    }

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("van", reeks.getVanpostcode());
        parameters.put("tot", reeks.getTotpostcode());

        return namedParameterJdbcTemplate.query(SQL_FIND_BY_POSTCODE, parameters, rowMapper);
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
