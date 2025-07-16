import { Grid } from "@mui/material";
import ChaosEntry from "../../../models/ChaosEntry";
import ChaosEntryCard from "../../atoms/ChaosEntryCard/ChaosEntryCard";

interface ChaosEntryListProps {
    chaosEntries: ChaosEntry[];
}

const ChaosEntryList = ({ chaosEntries } : ChaosEntryListProps) => {
    return (
        <Grid container spacing={0}>
            {chaosEntries.map((chaosEntry) => (
                <Grid size={4}>
                    <ChaosEntryCard chaosEntry={chaosEntry}/>
                </Grid>
            ))}
        </Grid>
    );
}

export default ChaosEntryList;