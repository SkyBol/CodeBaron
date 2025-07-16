import styled from "styled-components";
import AbstractCard, { AbstractCardProps } from "./AbstractCard";

interface AbstractCardListProps {
    cards: (AbstractCardProps | React.ReactNode)[];
}

const AbstractCardList = ({ cards } : AbstractCardListProps) => {
    return (
        <CardListContainer>
            {
                cards.map((card) => {
                    if ((card as AbstractCardProps).title !== undefined) {
                        return <AbstractCard {...(card as AbstractCardProps)}/>
                    }

                    return cards as React.ReactNode
                })
            }
        </CardListContainer>
    )
}

const CardListContainer = styled.div`
    
`;

export default AbstractCardList;