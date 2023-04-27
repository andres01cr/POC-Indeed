import React from "react";
import { BrowserRouter } from "react-router-dom";

import App from "./App";
import * as serviceWorker from "./serviceWorker";

function Root() {
  return (
    <BrowserRouter>
      <App />
    </BrowserRouter>
  );
}

const container = document.getElementById("root");

serviceWorker.unregister();

ReactDOM.render(<Root />, container);
