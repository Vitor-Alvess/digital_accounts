package com.alves.vitor.DigitalAccounts.infra.persistence;

import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class OperationRepositoryImpl implements OperationRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Operation> rowMapper = (rs, rowNum) -> {
        return Operation.builder()
                .ID(rs.getInt("id"))
                .account(Account.builder()
                        .ID(rs.getInt("accountid"))
                        .agency(rs.getString("agency"))
                        .number(rs.getString("number"))
                        .type(rs.getString("type").charAt(0))
                        .currency(rs.getString("currency").charAt(0))
                        .build())
                .type(rs.getString("type").charAt(0))
                .amount(rs.getBigDecimal("amount"))
                .time(rs.getTimestamp("time").toLocalDateTime())
                .build();
    };

    private final String defaultQuery = """
            SELECT
                o.id,
                a.id AS accountid,
                a.agency AS agency,
                a.number AS number,
                a.currency AS currency,
                a.totalamount AS totalamount,
                o."type" AS type,
                o.amount AS amount,
                o.time AS time
            FROM
                operation o
            INNER JOIN
                account a ON
                a.id = o.accountid
            """;

    @Autowired
    public OperationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Operation> findByType(char type) {
        String query = defaultQuery +
                """
                WHERE
                    o."type" = ? AND
                    a.active = true;
                """;

        return jdbcTemplate.query(query, rowMapper, type);
    }

    @Override
    public List<Operation> findByAccount(Account account) {
        String query = defaultQuery +
                """
                WHERE
                    a.agency = ? AND
                    a.number = ? AND
                    a.active = true;        
                """;

        return jdbcTemplate.query(query, rowMapper, account.getAgency(), account.getNumber());
    }

    @Override
    public List<Operation> findByDate(LocalDate date) {
        String query = defaultQuery +
                """
                WHERE
                    o.time BETWEEN ? AND ? AND
                    a.active = true;
                """;

        return jdbcTemplate.query(query, rowMapper, date, date.plusDays(1));
    }

    @Override
    public List<Operation> findByInterval(LocalDate start, LocalDate end) {
        String query = defaultQuery +
                """
                WHERE
                    o.time BETWEEN ? AND ? AND
                    a.active = true;
                """;

        return jdbcTemplate.query(query, rowMapper, start, end);
    }

    @Override
    public Operation register(Operation operation) {
        String query = """
                INSERT INTO operation (accountid, "type", amount)
                VALUES (?, ?, ?)
                RETURNING id;
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, operation.getAccount().getID());
            ps.setString(2, String.valueOf(operation.getType()));
            ps.setBigDecimal(3, operation.getAmount());

            return ps;
        }, keyHolder);

        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        return findById(id);
    }

    private Operation findById(int id) {
        String query = defaultQuery +
                """
                WHERE
                    o.id = ?
                """;

        return jdbcTemplate.queryForObject(query, rowMapper, id);
    }
}
