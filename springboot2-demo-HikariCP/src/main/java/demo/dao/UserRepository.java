package demo.dao;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsernameIsAndPasswordIs(String userName, String password);
}
