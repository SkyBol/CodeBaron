interface Coordinate {
    type: string;
    x: number;
    y: number;
}

interface Station {
    id: string;
    name: string;
    score: string; // ? Not sure
    coordinate: Coordinate;
    distance: string; // ? Not sure
}

interface Stop {
    station: Station;
    arrival: null; //!
    arrivalTimestamp: null //!
    departure: Date;
    departureTimestamp: number;
    delay: number;
    platform: string;
    prognosis: {
        platform: string;
        arrival: string;
        departure: string;
        capacity1st: null; //!
        capacity2nd: null; //!
    };
    realtimeAvailability: null;
    location: {
        id: string;
        anme: null; //!
        score: null; //!
        coordinate: Coordinate;
        distance: null; //!
    }
}

interface StationboardEntry {
    stop: Stop;
    name: string;
    category: string;
    subcategory: null; //!
    categoryCode: null; //!
    number: string;
    operator: string;
    to: string;
    passlist: Stop[];
    capacity1st: null; //!
    capacity2nd: null; //!
}

interface Board {
    station: Station;
    stationboard: StationboardEntry[]
}

export default Board;