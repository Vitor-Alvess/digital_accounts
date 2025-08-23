package com.alves.vitor.DigitalAccounts.infra.persistence;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Person> rowMapper =
            (rs, rowNum) -> Person.builder()
                    .ID(rs.getInt("id"))
                    .cpf(rs.getString("cpf"))
                    .name(rs.getString("name"))
                    .gender(rs.getString("gender").charAt(0))
                    .birthDate(rs.getDate("birth").toLocalDate())
                    .ocupation(rs.getString("ocupation"))
                    .modifiedAt(rs.getTimestamp("modified_at").toLocalDateTime())
                    .build();

    @Autowired
    public PersonRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        String query = """
                SELECT * FROM public.person WHERE active = true;
                """;

        return jdbcTemplate.query(query, rowMapper).stream().toList();
    }

    @Override
    public List<Person> findByName(String name) {
        String query = """
                SELECT * FROM public.person WHERE name ALIKE ? AND active = true;
                """;

        return jdbcTemplate.query(query, rowMapper, new Object[]{name + '%'}).stream().toList();
    }

    @Override
    public Person findByCpf(String cpf) {
        String query = """
                SELECT * FROM public.person WHERE cpf LIKE ? AND active = true;
                """;

        return jdbcTemplate.query(query, rowMapper, cpf).stream().findFirst().orElse(null);
    }

    @Override
    public List<Person> findByOcupations(String... ocupations) {
        String query = """
                SELECT * FROM public.person WHERE ocupation = ANY(?) AND active = true
                """;

        return jdbcTemplate.query(query, ps -> {
            Array ocupationArray = ps.getConnection().createArrayOf("varchar", ocupations);
            ps.setArray(1, ocupationArray);

        }, rowMapper).stream().toList();
    }

    @Override
    public Person create(Person person) {
        String query = """
                INSERT INTO
                    public.person (cpf, "name", gender, birth, ocupation)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id;
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getCpf());
            ps.setString(2, person.getName());
            ps.setString(3, String.valueOf(person.getGender()));
            ps.setDate(4, Date.valueOf(person.getBirthDate()));
            ps.setString(5, person.getOcupation());

            return ps;
        }, keyHolder);

        int newId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return findById(newId);
    }

    @Override
    public Person update(String cpf, Person newPerson) {
        String query = """
                UPDATE
                    public.person
                SET
                    name = ?,
                    gender = ?,
                    ocupation = ?,
                    modified_at = NOW()
                WHERE
                    cpf = ?
                RETURNING id;
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, newPerson.getName());
            ps.setString(2, String.valueOf(newPerson.getGender()));
            ps.setString(3, newPerson.getOcupation());
            ps.setString(4, cpf);

            return ps;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return findById(id);
    }

    @Override
    public Person delete(String cpf) {
        String queryAccounts = """
                UPDATE
                    public.account
                SET
                    active = false,
                    modified_at = NOW()
                WHERE
                    personid = (SELECT id FROM public.person WHERE cpf = ?);
                """;

        jdbcTemplate.update(queryAccounts, cpf);

        String query = """
                UPDATE 
                    public.person
                SET 
                    active = false, 
                    modified_at = NOW()
                WHERE 
                    cpf = ?
                RETURNING id;
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cpf);

            return ps;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return findById(id);
    }

    private Person findById(int id) {
        String query = "SELECT * FROM public.person WHERE id = ?";

        return jdbcTemplate.queryForObject(query, rowMapper, id);
    }
}
