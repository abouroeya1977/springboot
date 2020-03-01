package ma.cigma.springsecurity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.springsecurity.service.model.MyAction;

public interface MyActionRepository extends JpaRepository<MyAction, Long>{
	List<MyAction> findAllByAction(String action);

}
