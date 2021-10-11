import {Card, Col, Form, InputGroup, Row} from "react-bootstrap";
import {useState} from "react";

export const ByDescriptionLengthContent = () => {

    const [descLength, setDescLength] = useState(NaN)
    const [result, setResult] = useState(NaN)


    return (
        <Card.Body>
            <Form>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>
                                Enter description length
                            </Form.Label>
                            <Form.Control
                            type={'number'}
                            placeholder={'Length'}
                            value={descLength}
                            onChange={e => setDescLength(e.target.value)}
                            required
                            />
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <span>
                            Count: {result}
                        </span>
                    </Col>
                </Row>
            </Form>

        < /Card.Body>
    )

}