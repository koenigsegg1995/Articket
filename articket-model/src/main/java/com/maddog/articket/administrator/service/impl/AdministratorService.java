package com.maddog.articket.administrator.service.impl;

import com.maddog.articket.administrator.dao.AdministratorRepository;
import com.maddog.articket.administrator.entity.Administrator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("administratorService")
public class AdministratorService {

	@Autowired
	AdministratorRepository repository ;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addAdministrator(Administrator administrator) {
		repository.save(administrator);
	}
	
	public void updateAdministrator(Administrator administrator) {
		repository.save(administrator);
	}
	
//	public void deleteAdministrator(Integer administratorID) {
//		if(repository.existsById(administratorID))
//			repository.deleteByAdministratorID(administratorID);
//			repository.deleteById(administratorID);
//	}
	
	public Administrator getOneAdministrator(Integer administratorID) {
		Optional<Administrator> optional = repository.findById(administratorID);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}
	
	public List<Administrator> getALL(){
		return repository.findAll();
	}
	
	public Administrator getAdministratorAccount(String administratorAccount) {
		return repository.findByAdministratorAccount(administratorAccount);
	}
}
