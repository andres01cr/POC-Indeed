const startGateway = (config, apolloGatewayConfig) => {
    const corsOptions = {
        origin: config.CORS_ALLOWED_ORIGINS.split(", "),
        credentials: config.CORS_ALLOW_CREDENTIALS
    }

    const gateway = new ApolloGateway(apolloGatewayConfig);

    const server = new ApolloServer({
        gateway,
        cors: corsOptions,
        csrfPrevention: false,
        subscriptions: false,
        cache: "bounded",
        context: ({req}) => ({
            authHeaderValue: req.headers.authorization
        }),
        plugins: [
            {
                async serverWillStart() {
                    if (process.env.NODE_ENV !== 'development') {
                        registerService(config)
                    }
                    return {
                        async serverWillStop() {
                            if (process.env.NODE_ENV !== 'development') {
                                await unregisterService()
                            }
                        }
                    }
                }
            }
        ]
    });

    server.listen({
        port: config.PORT,
    }).then(({ url }) => {
        console.log(`🚀 Graph Router ready at ${url}`);
    });
}
