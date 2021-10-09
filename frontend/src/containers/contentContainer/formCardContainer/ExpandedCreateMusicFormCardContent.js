import {Card, Col, Form, InputGroup, Row} from "react-bootstrap";
import {useState} from "react";
import {isEmpty} from "lodash";

export const ExpandedCreateMusicFormCardContent = () => {
    const [groupName, setGroupName] = useState('')
    const [x, setX] = useState(NaN)
    const [y, setY] = useState(NaN)
    const [numberOfParticipants, setNumberOfParticipants] = useState(NaN)
    const [sales, setSales] = useState(NaN)
    const [genre, setGenre] = useState('')
    const [description, setDescription] = useState('')

    const validateForm = () => {
        return !(isEmpty(groupName)
            || isNaN(x)
            || isNaN(y)
            || isNaN(numberOfParticipants)
            || isNaN(sales)
            || isEmpty(description))
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        if (validateForm()) {
            console.log(groupName)
            console.log(x)
            console.log(y)
            console.log(numberOfParticipants)
            console.log(sales)
            console.log(genre)
            console.log(description)
        }
    }

    return (
        <Card.Body>
            <Form>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Enter group name:</Form.Label>
                            <Form.Control
                                type={`text`}
                                placeholder={`Group name`}
                                value={groupName}
                                onChange={e => setGroupName(e.target.value)}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter coordinates:</Form.Label>
                            <Row className={`w-100 text-center`}>
                                <Col className={`float-left`}>
                                    <Form.Control
                                        type={`number`}
                                        placeholder={`X`}
                                        value={x}
                                        onChange={e => setX(Number(e.target.value))}/>
                                </Col>
                                <Col className={`float-right`}>
                                    <Form.Control
                                        type={`number`}
                                        placeholder={`Y`}
                                        value={y}
                                        onChange={e => setY(Number(e.target.value))}/>
                                </Col>
                            </Row>
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group>
                            <Form.Label>Enter number of participants:</Form.Label>
                            <Form.Control
                                type={`number`}
                                placeholder={`Number of participants`}
                                value={numberOfParticipants}
                                onChange={e => setNumberOfParticipants(Number(e.target.value))}
                            />
                        </Form.Group>
                        <Form.Label>Enter label' sales:</Form.Label>
                        <InputGroup>
                            <Form.Control
                                type={`number`}
                                placeholder={`Sales`}
                                value={sales}
                                onChange={e => setSales(Number(e.target.value))}
                            />
                        </InputGroup>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Select genre</Form.Label>
                            <Form.Control
                                as={'select'}
                                value={genre}
                                onChange={e => setGenre(e.target.value)}>
                                <option>Option 1</option>
                                <option>Option 2</option>
                                <option>Option 3</option>
                                <option>Option 4</option>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Enter description</Form.Label>
                            <Form.Control
                                as={'textarea'}
                                value={description}
                                onChange={e => setDescription(e.target.value)}
                            />
                        </Form.Group>
                    </Col>
                </Row>
            </Form>
            <Row className={`text-center`}>
                <Col>
                    <button
                        className={`btn-fancy-simple ${!validateForm() ? `disabled` : 'enabled'} w-75`}
                        onClick={handleSubmit}>
                        Submit
                    </button>
                </Col>
            </Row>
        </Card.Body>
    )
}