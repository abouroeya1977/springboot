package ma.cigma.springsecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.springsecurity.service.model.MyAction;

public interface MyActionRepository extends JpaRepository<MyAction, Long> {
	
	MyAction findByAction(String name);

}
