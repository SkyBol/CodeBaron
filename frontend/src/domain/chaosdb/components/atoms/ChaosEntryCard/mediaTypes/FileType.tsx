import ChaosEntryService from "../../../../services/ChaosEntryService";
import ChaosEntry from "../../../../models/ChaosEntry";

export const FileType = (chaosEntry: ChaosEntry) => {
    if (chaosEntry.chaosContent.mediaType !== 'FILE') {
        return null;
    }

    const imageContent = (entryId: string) => (
        <img
            style={{
                width: "100%",
                height: "100%",
                objectFit: "cover",
            }}
            src={ChaosEntryService.getImagePath(entryId)}
        />
    );

    const renderByType = () => {
        if (chaosEntry.chaosContent.mediaType !== 'FILE') {
            return null;
        }

        switch (chaosEntry.chaosContent.fileType) {
            case "IMAGE":
                return imageContent(chaosEntry.id);
            case "AUDIO":
            case "VIDEO":
            case "DOCUMENT":
        }
        switch (chaosEntry.chaosContent.mediaType) {
            case 'FILE':
                
        }
    }

    return (
        <>
            { renderByType() }
        </>
    );
}

export default FileType;