package com.poc.tutorials.service;

import com.poc.tutorials.model.TutorialEntity;
import com.poc.tutorials.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;


@Service
@Transactional
public class TutorialService {

  @Autowired private TutorialRepository tutorialRepository;

  public TutorialEntity createTutorial(TutorialEntity tutorialEntity) {
    return tutorialRepository.save(new TutorialEntity(tutorialEntity.getTitle(), tutorialEntity.getDescription(), tutorialEntity.isPublished()));
  }

  public TutorialEntity updateTutorialById(UUID tutorialId, TutorialEntity tutorialEntity) {
    //TutorialEntity tutorialEntityFound = getTutorialById(tutorialId);

    return tutorialRepository.save(tutorialEntity);
  }

  public void deleteTutorialById(UUID tutorialId) {
    tutorialRepository.deleteById(tutorialId);
  }

  public void deleteAllTutorials(TutorialEntity tutorialEntity) {
    tutorialRepository.deleteAll();
  }

  public Optional<TutorialEntity>  getTutorialById(UUID tutorialId) {
    return  tutorialRepository.findById(tutorialId);
  }

  public List<TutorialEntity> getAllTutorialsByTitle(String title) {

    List<TutorialEntity> tutorials = new ArrayList<TutorialEntity>();

    if (title == null)
      tutorialRepository.findAll().forEach(tutorials::add);
    else
      tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

    return tutorials;
  }

  public List<TutorialEntity> getTutorialsPublished() {
    return tutorialRepository.findByPublished(true);
  }


  public void deleteTutorial(UUID id) {
    tutorialRepository.deleteById(id);
  }

  public void deleteAllTutorials() {
    tutorialRepository.deleteAll();
  }
}
