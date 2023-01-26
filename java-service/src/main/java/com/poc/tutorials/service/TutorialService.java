package com.poc.tutorials.service;

import com.poc.tutorials.model.TutorialEntity;
import com.poc.tutorials.repository.TutorialRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class TutorialService {

  @Autowired private TutorialRepository tutorialRepository;
  @Autowired private TutorialEntity tutorialEntity;

  public TutorialEntity createTutorial(TutorialEntity tutorialEntity) {
    return tutorialRepository.save(tutorialEntity);
  }

  public void deleteTutorialById(UUID tutorialId) {
    tutorialRepository.deleteById(tutorialId);
  }

  public TutorialEntity updateTutorialById(UUID tutorialId, TutorialEntity tutorialEntity) {
//    TutorialEntity tutorialEntityFound = getTutorialById(tutorialId);

    return tutorialRepository.save(tutorialEntity);
  }

  public void deleteAllTutorials(TutorialEntity tutorialEntity) {
    tutorialRepository.deleteAll();
  }

//  public TutorialEntity getTutorialById(UUID tutorialId) {
//    tutorialEntity.builder()
//
//    return tutorialRepository.findById(tutorialId).map(emp -> tutorialEntity.);
//
//  }

  public List<TutorialEntity> getAllTutorials(String title) {
    return tutorialRepository.findByTitleContaining(title);
  }

  public List<TutorialEntity> getTutorialsPublished() {
    return tutorialRepository.findByPublished(true);
  }

}
