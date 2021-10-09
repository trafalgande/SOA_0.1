import {CreateMusicBandFormCard} from "./createMusicBand/CreateMusicBandFormCard";
import {Col, Container, Row} from "react-bootstrap";
import {UpdateMusicBandFormCard} from "./updateMusicBand/UpdateMusicBandFormCard";

export const RequestsContentContainer = () => {
    return (
        <Container className={`p-2`}>
            <Row className={`mb-5`}>
                <Col sm={6}>
                    <CreateMusicBandFormCard/>
                </Col>
                <Col sm={6}>
                    <UpdateMusicBandFormCard/>
                </Col>
            </Row>
        </Container>

    )
}