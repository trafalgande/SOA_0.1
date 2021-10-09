import {Col, Container, Row} from "react-bootstrap";

export const NavigationBar = () => {
    return (
        <Container className={`p-3`}>
            <Row>
                <Col>
                    Navigation item
                </Col>
                <Col>
                    Navigation item
                </Col>
                <Col>
                    Navigation item
                </Col>
                <Col>
                    Navigation item
                </Col>
            </Row>
        </Container>
    )
}