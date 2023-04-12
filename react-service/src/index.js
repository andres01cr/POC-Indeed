import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

// GraphQL Code
// 1. Get Apollo Provider to wrap all child components
import { ApolloProvider } from 'react-apollo';

// 2. get Apollo Cache and HttpLink
import { InMemoryCache } from 'apollo-cache-inmemory';
import { HttpLink } from 'apollo-link-http';

// 3. import Apollo Client
import { ApolloClient } from 'apollo-client';

import gql from 'graphql-tag';

import App from "./App";
import * as serviceWorker from "./serviceWorker";

const GITHUB_BASE_URL = 'http://localhost:8086/graphql';

const cache = new InMemoryCache();
const link = new HttpLink({ 
  uri: GITHUB_BASE_URL,
});


const client = new ApolloClient( {
  cache,
  link
} );

const container = document.getElementById("root");
const root = createRoot(container);

root.render(
  <ApolloProvider client={ client }>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </ApolloProvider>
);

serviceWorker.unregister();
