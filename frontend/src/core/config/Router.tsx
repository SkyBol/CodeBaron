import { Route, Routes } from "react-router-dom";
import Homepage from "../../domain/CodeBaron/Homepage";
import ChaosDB from "../../domain/chaosdb/ChaosDB";

const Router = () => {
    /* These are only Core-Level Routings! */
    return (
        <Routes>
            <Route path={"chaos"} element={<ChaosDB/>}/>
            <Route path={"*"} element={<Homepage/>}/>
        </Routes>
    );
}

export default Router;