import {Card, Col, Form, Row} from "react-bootstrap";
import {useState} from "react";

export const ByLabelValueContent = () => {

    const [labelValue, setLabelValue] = useState(NaN)

    return (
        <Card.Body>
            <Form>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>
                                Enter label sales
                            </Form.Label>
                            <Form.Control
                                type={'number'}
                                placeholder={'Sales'}
                                value={labelValue}
                                onChange={e => setLabelValue(Number(e.target.value))}
                                required
                            />
                        </Form.Group>
                    </Col>
                </Row>
            </Form>

        < /Card.Body>
    )

}