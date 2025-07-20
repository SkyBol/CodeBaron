package ch.asu.chaosdb.core.entries.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter@Setter
@ConfigurationProperties("storage")
@Component
public class StorageConfig {
    /**
     * Folder location for storing files
     */
    private String location = "storage";

    /**
     * Default behaviour of uploaded Files
     */
    private boolean defaultFileEncryption = false;

    // A sub-folder of the 'location', for only unencrypted Files
    private String unencryptedFolder = "unencrypted";

    // A sub-folder of the 'location', for only encrypted Files
    private String encryptedFolder = "encrypted";

    /**
     * Pepper for encrypting Files
     */
    private String pepper = "examplePepper1234";
}
