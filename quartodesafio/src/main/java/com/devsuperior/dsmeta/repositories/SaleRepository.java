package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.devsuperior.dsmeta.entities.Sale;


import projections.SummarySaleProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
    @Query("SELECT obj FROM Sale obj " +
            "JOIN FETCH obj.seller " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
     Page<Sale> searchReport(@Param("minDate") LocalDate minDate,
                             @Param("maxDate") LocalDate maxDate,
                             @Param("name") String name,
                             Pageable pageable);
    
    @Query("""
            SELECT s.seller.name AS sellerName, 
                   SUM(s.amount) AS totalSales
            FROM Sale s
            WHERE (:minDate IS NULL OR s.date >= :minDate)
              AND (:maxDate IS NULL OR s.date <= :maxDate)
            GROUP BY s.seller.name
            ORDER BY totalSales DESC
               """)
        List<SummarySaleProjection> searchSummaryBySeller(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate);
    

}
