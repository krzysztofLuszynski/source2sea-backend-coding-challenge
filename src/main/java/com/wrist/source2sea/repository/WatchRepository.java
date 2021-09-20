package com.wrist.source2sea.repository;

import com.wrist.source2sea.persistence.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchRepository extends JpaRepository<Watch, Long> {
}
