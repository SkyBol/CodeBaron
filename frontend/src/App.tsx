import type { FunctionComponent } from "react";
// import Router from "./core/config/Router";
import { ActiveUserContextProvider } from "./core/security/user/contexts/ActiveUserContext";
import SbbDisplayData from "./sbb/SbbDisplayData";

const App: FunctionComponent = () => {
  return (
    <ActiveUserContextProvider>
      <SbbDisplayData />
      {/* <Router /> */}
    </ActiveUserContextProvider>
  );
}

export default App;