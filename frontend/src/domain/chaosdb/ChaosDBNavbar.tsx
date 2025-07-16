import { IconButton } from "@mui/material";
import Navbar from "../../common-components/navbar/Navbar";
import { Add, LogoDev } from "@mui/icons-material";
import ChaosEntryCreatePopup from "./components/molecules/ChaosEntryCreatePopup/ChaosEntryCreatePopup";
import { useState } from "react";

const ChaosNavbar = () => {
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
        </>
    )
}

export default ChaosNavbar;