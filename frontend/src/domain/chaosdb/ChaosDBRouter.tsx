import { Route, Routes } from "react-router-dom";
import ChaosTable from "./components/pages/ChaosTable/ChaosTable";

const ChaosDBRouter = () => {
    return (
        <Routes>
            <Route path={"*"} element={<ChaosTable />} />
        </Routes>
    )
}

export default ChaosDBRouter;