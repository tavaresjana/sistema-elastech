package com.soulcode.sistemachamadosdois.repositories;

import com.soulcode.sistemachamadosdois.model.Client;
import com.soulcode.sistemachamadosdois.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Client> findByEmail(String email);
}
