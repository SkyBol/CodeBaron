import ChaosEntry from "../../../models/ChaosEntry";
import TextType from "./mediaTypes/TextType";
import FileType from "./mediaTypes/FileType";

interface ChaosEntryCardProps {
    chaosEntry: ChaosEntry;
}

const ChaosEntryCard = ({ chaosEntry } : ChaosEntryCardProps) => {
    const renderContent = () => {
        switch (chaosEntry.chaosContent.mediaType) {
            case "TEXT":
                return TextType(chaosEntry);
            case "FILE":
                return FileType(chaosEntry);
            default:
                return <></>;
        }
    }

    return (
        <div style={{
            aspectRatio: 1,
        }}>
            { renderContent() }
        </div>
    );
}

export default ChaosEntryCard;