import { IconButton, styled, TextField, Typography } from "@mui/material";
import Pagination from "./Pagination";
import { Forward, NavigateBefore, NavigateNext, SkipNext, SkipPrevious } from "@mui/icons-material";
import { useEffect, useState } from "react";

interface PagingProps {
    maxItems: number;
    pagination: Pagination;
    setPagination: (pagination: Pagination) => void;
}

const Paging = ({ maxItems, pagination, setPagination } : PagingProps) => {
    const maxPages = Math.ceil(maxItems / pagination.size);

    const onBackSkipClick = () => {
        setPagination({
            size: pagination.size,
            page: 1,
        });
    }
    const onBackClick = () => {
        setPagination({
            size: pagination.size,
            page: pagination.page - 1,
        });
    }
    const onNextClick = () => {
        setPagination({
            size: pagination.size,
            page: pagination.page + 1,
        });
    }
    const onNextSkipClick = () => {
        setPagination({
            size: pagination.size,
            page: maxPages,
        });
    }

    return (
        <div style={{display: 'flex'}}>
            {/* To first page */}
            <StyledIconButton
                onClick={onBackSkipClick}
                disabled={pagination.page <= 1}
            >
                <SkipPrevious/>
            </StyledIconButton>

            {/* Back by 1 */}
            <StyledIconButton
                onClick={onBackClick}
                disabled={pagination.page <= 1}
            >
                <NavigateBefore/>
            </StyledIconButton>

            {/* Current Page */}
            <Selector
                maxPages={maxPages}
                pagination={pagination}
                setPagination={setPagination}
            />

            {/* Next by 1 */}
            <StyledIconButton
                onClick={onNextClick}
                disabled={pagination.page + 1 >= maxPages}
            >
                <NavigateNext/>
            </StyledIconButton>

            {/* To last page */}
            <StyledIconButton
                onClick={onNextSkipClick}
                disabled={pagination.page + 1 >= maxPages}
            >
                <SkipNext/>
            </StyledIconButton>
        </div>
    );
}

interface SelectorProps {
    maxPages: number;
    pagination: Pagination;
    setPagination: (pagination: Pagination) => void;
}

const Selector = ({maxPages, pagination, setPagination}: SelectorProps) => {
    const [value, setValue] = useState<string>(`${pagination.page + 1}`);

    useEffect(() => {
        setValue(`${pagination.page + 1}`);
    }, [pagination]);

    const valueToInt = parseInt(value);
    const valueIsCorrect = !isNaN(valueToInt);

    const selectorConfirm = () => {
        setPagination({
            size: pagination.size,
            page: valueToInt - 1,
        });
    }

    const jumpToPageAvailable = valueIsCorrect && valueToInt !== pagination.page && valueToInt >= 1 && valueToInt <= maxPages;

    return (
        <div style={{display: 'flex', alignItems: 'center', gap: '5px'}}>
            <StyledInputSelectorField
                hiddenLabel
                value={value}
                onChange={event => setValue(event.target.value)}
                disabled={maxPages === 1}
            />

            <div style={{display: 'flex', width: '50px', justifyContent: 'center'}}>
                {/* Shows max Page & Go button when entered new Page number */}
                {
                    (value === `${pagination.page}` || valueToInt === pagination.page)
                        ? <StyledInputGoButton
                            onClick={selectorConfirm}
                            disabled={!jumpToPageAvailable}
                        ><Forward/></StyledInputGoButton>
                        : <Typography>of {maxPages}</Typography>
                }
            </div>
        </div>
    );
}

const StyledInputSelectorField = styled(TextField)`
    width: 50px;
`;

const StyledInputGoButton = styled(IconButton)`
    font-size: 1.25rem;
`;

const StyledIconButton = styled(IconButton)`
    aspect-ratio: 1/1;
`;

export default Paging;