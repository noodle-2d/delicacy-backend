package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Дмитрий on 10/2/2017.
 */
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> { }
