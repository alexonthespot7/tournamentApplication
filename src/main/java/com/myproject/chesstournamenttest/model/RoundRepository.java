package com.myproject.chesstournamenttest.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Long> {
	@Override
	List<Round> findAll();
	
	@Query(value="SELECT * FROM round JOIN stage ON (stage.stageid = round.stageid) WHERE stage.is_current = true OR result <> 'No'", nativeQuery=true)
	List<Round> findAllCurrentAndPlayed();
	
	@Query(value="SELECT * FROM round WHERE roundid = ?1", nativeQuery=true)
	Round findRoundById(Long id);
	
	@Query(value="SELECT roundid, result, user1_id, user2_id, round.stageid FROM round JOIN stage ON (stage.stageid = round.stageid) WHERE stage.is_current = true", nativeQuery = true)
	List<Round> findPlayedCurrentRounds();
	
	@Query(value="SELECT * FROM round WHERE stageid = ?1", nativeQuery = true)
	List<Round> findRoundsByStage(Long stageid);
	
	@Query(value="SELECT COUNT(roundid) FROM round JOIN stage ON (stage.stageid = round.stageid) WHERE stage.is_current = true", nativeQuery = true)
	int findQuantityOfCurrent();
	
	@Query(value="SELECT COUNT(roundid) FROM round JOIN stage ON (stage.stageid = round.stageid) WHERE stage.is_current = true AND result <> 'No'", nativeQuery = true)
	int quantityOfPlayed();
	
	@Query(value="SELECT * FROM round JOIN stage ON (stage.stageid = round.stageid) WHERE stage.stage = 'final'", nativeQuery=true)
	Round findFinal();
}