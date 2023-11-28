package com.ll.sb20231117.domain.base.attr.repository;

import com.ll.sb20231117.domain.base.attr.entity.Attr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttrRepository extends JpaRepository<Attr, Long> {
}
