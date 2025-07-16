import { ArrowLeft } from "@mui/icons-material";
import { AppBar, Box, IconButton, Toolbar, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

interface NavbarProps {
    title: string;
    icon: React.ReactNode;
    isSubNav?: boolean;
    actions?: React.ReactNode[] | React.ReactNode;
}

const Navbar = ({ title, icon, isSubNav, actions } : NavbarProps) => {
    const navigate = useNavigate();

    return (
        <AppBar position="relative">
            <Toolbar sx={{backgroundColor: "white"}}>
                {
                    isSubNav && <IconButton onClick={() => navigate('/')}>
                        <ArrowLeft htmlColor="blue"/>
                    </IconButton>
                }

                {icon}

                <Typography variant="h6" color="black">
                    {title}
                </Typography>

                <Box sx={{ flexGrow: 1 }}/>

                {actions}
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;