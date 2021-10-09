import {Col, Container, Row} from "react-bootstrap";
import {TableContent} from "./TableContent";
import {ExpandableContainer} from "../../expandableContainer/ExpandableContainer";

export const TableContentContainer = () => {
    return (
        <Container className={`p-2`}>
            <Row className={`mb-5`}>
                <Col>
                    <ExpandableContainer title={"Table"}>
                        <TableContent/>
                    </ExpandableContainer>
                </Col>
            </Row>
        </Container>
    )
}