package com.revature.boot.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.boot.beans.Hotel;
/**
 * Welcome to Spring Data
 * @author kyles
 *
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{
	
	//query methods : findByPropertyName....fluff
	//the name of the method is parsed and they can determine what the method is trying to do
	
	public List<Hotel> findByPriceBetween(double low, double high);
	
	//say there is something we dont know how to do
	//jpql, which is kind of like hql
	
	
	@Query("from Hotel") // this is where the DQL logic would go
	public List<Hotel> noIdeaHowToDoThisThing();
	
}
