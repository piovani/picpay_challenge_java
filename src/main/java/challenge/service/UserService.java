package challenge.service;

import challenge.domain.user.User;
import challenge.domain.user.UserType;
import challenge.dtos.UserDTO;
import challenge.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.COMMON) {
            throw new Exception("O seu tipo de usuário nao está autorizado à fazer a transação");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo Insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserDTO data) {
        User user = new User(data);
        this.repository.save(user);
        return user;
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public List<User> getAll() {
        return this.repository.findAll();
    }
}
