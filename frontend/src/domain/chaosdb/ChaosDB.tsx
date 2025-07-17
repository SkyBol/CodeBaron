import { Add, LogoDev } from "@mui/icons-material";
import Navbar from "../../common-components/navbar/Navbar";
import ChaosDBRouter from "./ChaosDBRouter";
import { IconButton } from "@mui/material";
import ChaosEntryCreatePopup from "./components/molecules/ChaosEntryCreatePopup/ChaosEntryCreatePopup";
import { useState } from "react";

const ChaosDB = () => {
    const [createOpen, setCreateOpen] = useState<boolean>(false);

    return (
        <>
            <Navbar
                title="ChaosDB"
                icon={<LogoDev htmlColor="black" fontSize="large" sx={{paddingRight: "15px"}}/>}
                actions={<IconButton onClick={() => setCreateOpen(true)}><Add/></IconButton>}
                isSubNav
            />
            <ChaosEntryCreatePopup isOpen={createOpen} setOpen={setCreateOpen} />
            <ChaosDBRouter/>
        </>
    )
}

export default ChaosDB;