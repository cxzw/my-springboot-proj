package com.xi.proj.repository;

import com.xi.proj.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<ProjectEntity,Integer>{
  ProjectEntity findByProjname(String projname);
}
