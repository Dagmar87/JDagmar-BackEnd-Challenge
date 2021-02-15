package br.com.intertrack.backendchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.intertrack.backendchallenge.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer>{

}
