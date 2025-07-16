import { LogoDev } from "@mui/icons-material";
import { AppBar, Box, Toolbar, Typography } from "@mui/material";
import AbstractCardList from "../../core/abstracts/card/AbstractCardList";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const domains = [
    {
        title: "ChaosDB",
        imageSrc: "https://picsum.photos/400",
        urlExtension: "chaos"
    }
]

const Homepage = () => {
    const navigate = useNavigate();

    return (
        <div>
            <AppBar position="relative">
                <Toolbar sx={{backgroundColor: "white"}}>
                    <LogoDev htmlColor="black" fontSize="large" sx={{paddingRight: "15px"}}/>

                    <Typography variant="h6" color="black">
                        Code Baron
                    </Typography>

                    <Box sx={{ flexGrow: 1 }}/>
                </Toolbar>
            </AppBar>

            <Body>
                <AbstractCardList
                    cards={
                        domains.map((domainEl) => ({
                            title: domainEl.title,
                            imageSrc: domainEl.imageSrc,
                            onClick: () => navigate(`/${domainEl.urlExtension}`),
                        }))
                    }
                />
            </Body>
        </div>
    );
}

const Body = styled.div`
    padding: 15px;
`;

export default Homepage;