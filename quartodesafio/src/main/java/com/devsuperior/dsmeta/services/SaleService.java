package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

import projections.SummarySaleProjection;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	  public Page<SaleReportDTO> getReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
	        
	        // Se maxDate não informado, assume hoje
	        LocalDate maxDate = maxDateStr.isEmpty()
	                ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
	                : LocalDate.parse(maxDateStr);

	        // Se minDate não informado, assume 1 ano antes de maxDate
	        LocalDate minDate = minDateStr.isEmpty()
	                ? maxDate.minusYears(1L)
	                : LocalDate.parse(minDateStr);

	        String nameFilter = (name == null) ? "" : name;

	       Page<Sale> result = repository.searchReport(minDate, maxDate, nameFilter, pageable);

	    return result.map(sale -> new SaleReportDTO(sale));
	    }
	  
	  
	  public List<SummarySaleProjection> getSummary(String minDateStr, String maxDateStr) {
		    
		    // Se maxDate não informado, assume hoje
		    LocalDate maxDate = maxDateStr.isEmpty()
		            ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
		            : LocalDate.parse(maxDateStr);

		    // Se minDate não informado, assume 1 ano antes de maxDate
		    LocalDate minDate = minDateStr.isEmpty()
		            ? maxDate.minusYears(1L)
		            : LocalDate.parse(minDateStr);

		    return repository.searchSummaryBySeller(minDate, maxDate);
		}
	  
	    
}
