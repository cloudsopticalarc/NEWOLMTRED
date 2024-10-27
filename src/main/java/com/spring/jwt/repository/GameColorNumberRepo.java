package com.spring.jwt.repository;

import com.spring.jwt.entity.GameColorNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameColorNumberRepo extends JpaRepository<GameColorNumber, Integer> {
    @Query("SELECT gcn FROM GameColorNumber gcn WHERE gcn.userReferenceId = :userReferenceId AND gcn.period = :period AND gcn.winStatus = :winStatus")
    List<GameColorNumber> findByRefAndPeriodAndWinStatus(@Param("userReferenceId") String userReferenceId,
                                                         @Param("period") String period,
                                                         @Param("winStatus") Boolean winStatus);


    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.black = :status",nativeQuery = false)
    Integer finByBlack(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.red = :status",nativeQuery = false)
    Integer findByRed(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.yellow = :status",nativeQuery = false)
    Integer findByYellow(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.zero = :status",nativeQuery = false)
    Integer findByZero(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.one = :status",nativeQuery = false)
    Integer findByOne(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.two = :status",nativeQuery = false)
    Integer findByTwo(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.three = :status",nativeQuery = false)
    Integer findByThree(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.four = :status",nativeQuery = false)
    Integer findByFour(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.five = :status",nativeQuery = false)
    Integer findByFive(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.six = :status",nativeQuery = false)
    Integer findBySix(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.seven = :status",nativeQuery = false)
    Integer findBySeven(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.eight = :status",nativeQuery = false)
    Integer findByEight(@Param("status") Boolean status);

    @Query(value = "SELECT SUM(g.amount) as totalAmount FROM GameColorNumber g WHERE g.nine = :status",nativeQuery = false)
    Integer findByNine(@Param("status") Boolean status);
}
