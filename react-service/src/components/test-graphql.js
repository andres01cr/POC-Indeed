const TestGraphql = () => {

    const { id } = useParams();

    const { loading, data, error } = useProductAndReviewsQuery({
        variables: {
            id
        },
    })

    if (loading) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="visually-hidden">Loading...</span>
            </Spinner>
        </div>
    }

    if (error) {
        return <Container>Error...</Container>
    }

    return (
        <Container>
            <Row>
                <Card>
                    <Card.Header className="text-center text-primary">Reviews</Card.Header>
                    <ListGroup variant="flush">
                        {data.product.reviews.length === 0 && <ListGroup.Item>There are no reviews</ListGroup.Item>}
                        {data.product.reviews.map(review => (
                                <ListGroup.Item key={review.id}>
                                    <>
                                        {review.text}
                                        <StarRating rating={review.starRating}/>
                                    </>
                                </ListGroup.Item>
                        ))}
                    </ListGroup>
                </Card>
            </Row>
        </Container>
    )
}

export default TestGraphql;