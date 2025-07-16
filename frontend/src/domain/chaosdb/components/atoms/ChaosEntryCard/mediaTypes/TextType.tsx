import { Typography } from "@mui/material";
import ChaosEntry from "../../../../models/ChaosEntry";

export const TextType = (chaosEntry: ChaosEntry) => {
    if (chaosEntry.chaosContent.mediaType !== 'TEXT') {
        return null;
    }

    return (
        <>
            <Typography variant="h5" component="div">
                { chaosEntry.chaosContent.title }
            </Typography>
        </>
    );
}

export default TextType;