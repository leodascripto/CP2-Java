package br.com.fiap.roardemo.repository;

import br.com.fiap.roardemo.model.Roar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface RoarRepository extends JpaRepository<Roar, Long> {
    List<Roar> findAllByOrderByCreatedAtDesc();

    @Transactional
    @Modifying
    @Query("UPDATE Roar r SET r.likes = r.likes + 1 WHERE r.id = :id")
    void incrementLikes(Long id);
}