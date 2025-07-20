package ch.asu.chaosdb.core.entries.storage;

import ch.asu.chaosdb.core.abstracts.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "encrypted_storage")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "encryption", discriminatorType = DiscriminatorType.STRING)
public class Storage extends AbstractEntity {
  @Column(name = "fileName", nullable = false)
  private String fileName;

  @Column(name = "fileType", nullable = false)
  private String fileType;

  @Column(name = "original_file_checksum", nullable = false)
  private String originalFileChecksum;
}
