import { Card, CardActionArea, CardContent, CardMedia, Typography } from "@mui/material";

export interface AbstractCardProps {
    imageSrc: string;
    title: string;
    onClick: () => void;
}

const AbstractCard = ({ imageSrc, title, onClick } : AbstractCardProps) => {
    return (
        <Card sx={{ maxWidth: 350 }}>
            <CardActionArea onClick={onClick}>
                <CardMedia
                    component="img"
                    height="140"
                    image={imageSrc}
                    alt="Random Image"
                />
                <CardContent>
                    <Typography variant="h5" component="div">
                        {title}
                    </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    )
}

export default AbstractCard;