import type { FunctionComponent } from "react";
import Router from "./core/config/Router";
import { ActiveUserContextProvider } from "./core/security/user/contexts/ActiveUserContext";

const App: FunctionComponent = () => {
  return (
    <ActiveUserContextProvider>
      <Router />
    </ActiveUserContextProvider>
  );
}

export default App;