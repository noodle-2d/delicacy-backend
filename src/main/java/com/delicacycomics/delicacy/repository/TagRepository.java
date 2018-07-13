package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Дмитрий on 10/2/2017.
 */
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

   // @Query("select t from Tag t where t.name = :query")
   // Page<Tag> getTagByQuery(@Param(value = "query") String query);
}
