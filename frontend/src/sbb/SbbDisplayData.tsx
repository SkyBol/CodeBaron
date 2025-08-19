import axios, { AxiosInstance } from "axios";
import SbbDisplay from "./SbbDisplay";
import { useEffect, useState } from "react";
import Board from "./models/Board";

const StationID: string = "8506013";
const API: AxiosInstance = axios.create({ baseURL: `https://transport.opendata.ch/v1/` });

const fetchBoard = async (limit: number = 10): Promise<Board> => {
    const board = await API.get(`stationboard?id=${StationID}&limit=${limit}`);
    return board.data;
}

const SbbDisplayData = () => {
    const [board, setBoard] = useState<Board | null>(null);
    const [limiter, setLimiter] = useState<number>(0);

    useEffect(() => {
        const get = async () => {
            if (limiter <= 0) {
                console.log(`A is now: ${limiter}`)
                setLimiter(limiter + 1);
                const boardData = await fetchBoard();
                console.log(boardData);
                setBoard(boardData);
            }
        }

        console.log("ABC");
        get();
    }, [limiter]);

    // If board null, return null;
    if (board === null) return null;

    return <SbbDisplay board={board}/>
}

export default SbbDisplayData;