package ru.blinov.polo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.blinov.polo.models.Store;

import java.util.List;

@Component
public class StoreDAO {

    private final JdbcTemplate jdbcTemplate;
    private int count = 0;

    @Autowired
    public StoreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<Store> index(){
        return jdbcTemplate.query("SELECT * FROM Store",new BeanPropertyRowMapper<>(Store.class));
    }

    public Store show(int id){
        return jdbcTemplate.query("SELECT * FROM Store WHERE id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Store.class))
                .stream().findAny().orElse(null);
    }

    public void save(Store store){

        jdbcTemplate.update("INSERT INTO Store(name, adress, phonenumber) VALUES(?, ?, ?)",
                store.getName(),store.getAdress(),store.getPhonenumber());
    }

    public void update(int id, Store updatedStore){
        jdbcTemplate.update("UPDATE Store SET name=?, adress=?, phonenumber=? WHERE id=?",
                updatedStore.getName(),updatedStore.getAdress(),updatedStore.getPhonenumber(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Store WHERE id=?",id);
    }
}
