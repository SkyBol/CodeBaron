package ch.asu.chaosdb.domain.chaos.content.types;

import jakarta.persistence.Column;

import java.util.Set;

public class ChaosNovel {
  // TODO: Chapters of Texts that can be saved

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    private Set<String> chapters;
}
