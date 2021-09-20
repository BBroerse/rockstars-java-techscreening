package com.example.techscreening.repository;

import com.example.techscreening.model.Migration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author basbroerse
 */
public interface MigrationRepository extends JpaRepository<Migration, Long> {
}
