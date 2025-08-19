package ch.asu.chaosdb.domain.chillhome;

import ch.asu.chaosdb.core.abstracts.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chill_home")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Accessors(chain = true)
public class ChillHome extends AbstractEntity {
    private String source;
    private String url;

    private String title;
    private String description;
    private List<String> images;

    private Boolean isActive;
    private LocalDate lastSeen;

    private LocalDate availableFrom;
    private LocalDate limitedUntil; // In most cases null

    private BigDecimal price; // CHF
    private BigDecimal rooms;
    private BigDecimal areaSqm;
    private Integer floor;
    private Integer totalFloors;

    // <-- Begin Address -->
    private String street;
    private String zip;
    private String city;
    private String canton;
    private String country;

    private Double latitude;
    private Double longitude;
    // <-- End Address -->

    // <-- Begin Extra Features -->
    private Boolean balcony;
    private Boolean elevator;
    private Boolean petsAllowed;
    private Boolean furnished;
    private Boolean parkingAvailableFree;
    // <-- End Extra Features -->
}
