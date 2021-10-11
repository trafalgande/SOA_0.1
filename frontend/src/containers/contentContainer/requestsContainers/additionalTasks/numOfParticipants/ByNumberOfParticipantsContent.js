import {Card, Col, Form, Row} from "react-bootstrap";
import {useState} from "react";
import {countMusicBandsByNumberOfParticipantsLessThen} from "../../../../../_api/client";
import {TOKEN} from "../../../../../_api/_options";

export const ByNumberOfParticipantsContent = () => {

    const [numberOfParticipants, setNumberOfParticipants] = useState(0)
    const [result, setResult] = useState(0)

    const handleValueChange = (e) => {
        e.preventDefault()
        setNumberOfParticipants(Number(e.target.value))
        countMusicBandsByNumberOfParticipantsLessThen(TOKEN, null, numberOfParticipants)
            .then(data => data.json())
            .then(r => setResult(r))
    }

    return (
        <Card.Body>
            <Form>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>
                                Enter number of participants:
                            </Form.Label>
                            <Form.Control
                                type={'number'}
                                placeholder={'# of participants'}
                                value={numberOfParticipants}
                                onChange={e => handleValueChange(e)}
                                required
                            />
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <span className={`text-muted`}>
                            Result: <strong>{result}</strong> groups have less then <strong>{numberOfParticipants}</strong> number of participants
                        </span>
                    </Col>
                </Row>
            </Form>

        < /Card.Body>
    )

}