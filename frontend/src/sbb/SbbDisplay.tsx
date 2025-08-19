import styled from "styled-components";
import Board from "./models/Board";
import { Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import moment from 'moment';

const SbbColorBlue = "#2d327d";

interface SbbDisplayProps {
    board: Board
}

const SbbDisplay = ({board}: SbbDisplayProps) => {
    return (
        <BoardContainer>
            <TableContainer>
                <Table>
                    <TableHead>
                    </TableHead>
                    <TableBody>
                        {
                            board.stationboard.map((station, i) => (
                                <TableRow key={i}>
                                    <TableCellContainer>{station.category} {station.number}</TableCellContainer>
                                    <TableCellContainer>{moment(station.stop.departure).format("hh:mm")}</TableCellContainer>
                                    <TableCellContainer>{station.stop.prognosis.departure}</TableCellContainer>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </TableContainer>
        </BoardContainer>
    );
}

const TableCellContainer = styled(TableCell)`
    color: white;
`;

const TableContainer = styled(Table)`
    background-color: ${SbbColorBlue};
    color: ${SbbColorBlue}
`;

const BoardContainer = styled.div`
    height: 100vh;
    width: 100vw;
`;

export default SbbDisplay;