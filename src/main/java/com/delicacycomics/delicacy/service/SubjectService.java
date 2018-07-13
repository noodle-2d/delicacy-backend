package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.entity.Subject;
import com.delicacycomics.delicacy.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

  //  @Transactional
  //  public Subject getSubject(String name) {
  //      List<Subject> listOfSubjects = subjectRepository.findAll();               BULLSHIT
  //      ArrayList arrayList = new ArrayList(Arrays.asList(listOfSubjects));
  //      arrayList.contains(name);
  //      }
}
