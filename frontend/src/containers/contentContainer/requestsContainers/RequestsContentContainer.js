import {CreateMusicBandFormCard} from "./createMusicBand/CreateMusicBandFormCard";
import {Col, Container, Row} from "react-bootstrap";
import {UpdateMusicBandFormCard} from "./updateMusicBand/UpdateMusicBandFormCard";
import {ByNumberOfParticipantsCard} from "./additionalTasks/numOfParticipants/ByNumberOfParticipantsCard";

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
            <Row>
                <Col sm={4}>
                    <ByNumberOfParticipantsCard/>
                </Col>
            </Row>
        </Container>

    )
}