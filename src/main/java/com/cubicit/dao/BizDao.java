package com.cubicit.dao;

import java.io.IOException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.cubicit.controller.Biz;

@Repository
public class BizDao {
	
	@Autowired // ByType, @Qualifier ,@ByName
	private JdbcTemplate jdbcTemplate;
	
	public Biz findById(int did){
		String sql="select id,name,brand,doe from biz_service_tbl where id = "+did;
		List<Biz> bizList=jdbcTemplate.query(sql, new BeanPropertyRowMapper(Biz.class));
		/*if(bizList.size()==1){
			return bizList.get(0);
		}
		return null;*/
		return bizList.size()==1?bizList.get(0):null;
	}
	
	//true, false
	public boolean isAuth(String name,String password){
		List<Biz> bizList=jdbcTemplate.query("select id,name,brand,doe from biz_service_tbl where name =? and brand=?",
				new Object[]{name,password},
				new BeanPropertyRowMapper(Biz.class));
		return bizList.size() > 0;
	}
	
	public String updateByName(String name,String brand){
		int rows=jdbcTemplate.update("update biz_service_tbl set brand=? where name = ?",brand,name);
		String result="Number of row update is  = "+rows;
		return result;
	}
	
	public String deleteByName(String name){
		int rows=jdbcTemplate.update("delete from biz_service_tbl where name = ?",name);
		String result="Number of row deleted is  = "+rows;
		return result;
	}
	
	public String deleteById(int id){
		int rows=jdbcTemplate.update("delete from biz_service_tbl where id = ?",id);
		String result="Number of row deleted is  = "+rows;
		return result;
	}
	
	
	public void update(Biz biz){
		String sql="update biz_service_tbl set name=?,brand=? where id=?";
		Object[] data={biz.getName(),biz.getBrand(),biz.getId()};
		jdbcTemplate.update(sql,data);
	}
	
	
	public byte[] findPhotoById(int dbid){
		byte[] photo=jdbcTemplate.queryForObject("select photo from biz_service_tbl where id = "+dbid, byte[].class);
		return photo;
	}
	
	public void save(Biz biz){
		try {
			byte[] photo=biz.getFile().getBytes();
			LobHandler lobHandler=new DefaultLobHandler();
			SqlLobValue sqlLobValue=new SqlLobValue(photo,lobHandler);
			String sql="insert into biz_service_tbl(name,brand,doe,photo) values(?,?,?,?)";
			Object[] data={biz.getName(),biz.getBrand(),biz.getDoe(),sqlLobValue};
			int dataType[] ={Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.BLOB};
			jdbcTemplate.update(sql,data,dataType);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Biz> findAll(){
		List<Biz> bizList=jdbcTemplate.query("select id,name,brand,doe from biz_service_tbl", new BeanPropertyRowMapper(Biz.class));
		return bizList;
	}

}
