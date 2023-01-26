package com.poc.tutorials.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.poc.tutorials.model.TutorialEntity;
import com.poc.tutorials.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/api/v1/tutorials")
public class TutorialController {
  @Autowired
  private TutorialService tutorialService;

  /**
   * Adds a tutorial.
   *
   */
  @PostMapping
  public ResponseEntity<TutorialEntity> createTutorial(
    @Valid @RequestBody TutorialEntity tutorialEntity) {
    return ResponseEntity.ok(tutorialService.createTutorial(tutorialEntity));
  }

  /**
   * Delete a tutorial
   *
   */
  @DeleteMapping(value = "/{tutorialId}")
  public ResponseEntity<UUID>  deleteTutorial(
    @PathVariable("tutorialId")
    @NotNull
      UUID tutorialId) {
    tutorialService.deleteTutorialById(tutorialId);
    return ResponseEntity.ok(tutorialId);
  }

  /**
   * Delete all tutorials
   *
   */
  @DeleteMapping(value = "/{tutorialId}")
  public ResponseEntity<UUID>  deleteAllTutorial(
    @PathVariable("tutorialId")
    @NotNull
      UUID tutorialId) {
    tutorialService.deleteTutorialById(tutorialId);
    return ResponseEntity.ok(tutorialId);
  }

  /**
   * Updates a tutorial
   *
   */
  @PatchMapping(value = "/{tutorialId}")
  public ResponseEntity<TutorialEntity> updateTutorial(
    @PathVariable("id") UUID tutorialId,
    @Valid @RequestBody TutorialEntity tutorialEntity) {
    return ResponseEntity.ok(tutorialService.updateTutorialById(tutorialId, tutorialEntity));
  }

  /**
   * Get a tutorial by id
   *
   */
//  @GetMapping(value = "/{tutorialId}")
//  public ResponseEntity<TutorialEntity> getTutorialById(
//    @PathVariable("id") UUID tutorialId) {
//    return ResponseEntity.ok(tutorialService.getTutorialById(tutorialId));
//  }

  /**
   * Get all tutorials
   *
   */
  @GetMapping(value = "/")
  public ResponseEntity<List<TutorialEntity>> getAllTutorials(
     @PathVariable("title") String title) {

    List<TutorialEntity> tutorials = new ArrayList<TutorialEntity>();

    if (title == null)
      tutorialRepository.findAll().forEach(tutorials::add);
    else
      tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ResponseEntity.ok(tutorialService.getAllTutorials(title));
  }

  /**
   * Get tutorials published
   *
   */
  @GetMapping(value = "/")
  public ResponseEntity<List<TutorialEntity>> getTutorialsPublished() {
    return ResponseEntity.ok(tutorialService.getTutorialsPublished());
  }
}

