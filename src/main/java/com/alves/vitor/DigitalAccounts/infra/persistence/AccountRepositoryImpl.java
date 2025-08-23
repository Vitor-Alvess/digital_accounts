package com.alves.vitor.DigitalAccounts.infra.persistence;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Account> rowMapper =
            (rs, rowNum) -> Account.builder()
                    .ID(rs.getInt("id"))
                    .agency(rs.getString("agency"))
                    .number(rs.getString("number"))
                    .type(rs.getString("type").charAt(0))
                    .totalAmount(rs.getBigDecimal("totalamount"))
                    .currency(rs.getString("currency").charAt(0))
                    .holder(Person.builder()
                            .ID(rs.getInt("personid"))
                            .name(rs.getString("name"))
                            .cpf(rs.getString("cpf"))
                            .build())
                    .modifiedAt(rs.getTimestamp("modified_at").toLocalDateTime())
                    .build();

    private String defaultQuery = """ 
                    SELECT
                        a.agency AS agency,
                        a."number" AS number,
                        a."type" AS type,
                        a.currency AS currency,
                        a.totalamount AS totalamount,
                        p.name AS name
                    FROM
                        account a
                    INNER JOIN
                        person p ON
                        p.id = a.personid
                    """;

    @Autowired
    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> findByAgency(String agency) {
        String query = defaultQuery +
                """
                WHERE
                    a.agency = ? AND
                    p.active = true
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            return Account.builder()
                    .agency(rs.getString("agency"))
                    .number(rs.getString("number"))
                    .type(rs.getString("type").charAt(0))
                    .totalAmount(rs.getBigDecimal("totalamount"))
                    .currency(rs.getString("currency").charAt(0))
                    .holder(Person.builder()
                            .name(rs.getString("name"))
                            .build())
                    .build();
        }, agency);
    }

    @Override
    public List<Account> findByCpf(String cpf) {
        String query = defaultQuery +
                """
                WHERE
                    p.cpf = ? AND
                    p.active = true
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            return Account.builder()
                    .agency(rs.getString("agency"))
                    .number(rs.getString("number"))
                    .type(rs.getString("type").charAt(0))
                    .totalAmount(rs.getBigDecimal("totalamount"))
                    .currency(rs.getString("currency").charAt(0))
                    .holder(Person.builder()
                            .name(rs.getString("name"))
                            .build())
                    .build();
        }, cpf);
    }

    @Override
    public Account findByAgencyAndNumber(String agency, String number) {
        String query = defaultQuery +
                """
                WHERE
                    a.agency = ? AND
                    a.number = ? AND
                    p.active = true;
                """;

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            return Account.builder()
                    .agency(rs.getString("agency"))
                    .number(rs.getString("number"))
                    .type(rs.getString("type").charAt(0))
                    .totalAmount(rs.getBigDecimal("totalamount"))
                    .currency(rs.getString("currency").charAt(0))
                    .holder(Person.builder()
                            .name(rs.getString("name"))
                            .build())
                    .build();
        }, agency, number).stream().findFirst().orElse(null);

    }

    @Override
    public Account register(Account account) {
        String query = """
                INSERT INTO account (agency, "number", "type", currency, personid)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id;
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getAgency());
            ps.setString(2, account.getNumber());
            ps.setString(3, String.valueOf(account.getType()));
            ps.setString(4, String.valueOf(account.getCurrency()));
            ps.setInt(5, account.getHolder().getID());

            return ps;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return findById(id);
    }

    @Override
    public Account update(Account newAccount) {
        String query = """
                UPDATE
                    public.account
                SET
                    "type" = ?,
                    totalamount = ?,
                    currency = ?,
                    modified_at = NOW()
                WHERE
                    agency = ? AND
                    number = ?
                RETURNING id;
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(newAccount.getType()));
            ps.setBigDecimal(2, newAccount.getTotalAmount());
            ps.setString(3, String.valueOf(newAccount.getCurrency()));
            ps.setString(4, newAccount.getAgency());
            ps.setString(5, newAccount.getNumber());

            return ps;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return findById(id);
    }

    @Override
    public Account delete(String agency, String number) {
        String query = """
                UPDATE
                    public.account
                SET
                    active = false,
                    modified_at = NOW()
                WHERE
                    agency = ? AND
                    number = ?
                RETURNING id;
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, agency);
            ps.setString(2, number);

            return ps;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return findById(id);
    }

    private Account findById(int id) {
        String query = """
                SELECT
                    a.id,
                    a.agency AS agency,
                    a."number" AS number,
                    a."type" AS type,
                    a.totalamount AS totalamount,
                    a.currency AS currency,
                    p.id AS personid,
                    p.name AS name,
                    p.cpf AS cpf,
                    a.modified_at AS modified_at
                FROM
                    account a
                INNER JOIN
                    person p ON
                    p.id = a.personid
                WHERE
                    a.id = ?;
                """;

        return jdbcTemplate.queryForObject(query, rowMapper, id);
    }
}
