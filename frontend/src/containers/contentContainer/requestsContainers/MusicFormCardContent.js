import {Card, Col, Form, InputGroup, Row} from "react-bootstrap";
import {useState} from "react";
import {isEmpty} from "lodash";
import {postNewMusicBand, updateMusicBand} from "../../../_api/client";
import {TOKEN} from "../../../_api/_options";
import {notify} from "../../notificationContainer/notifications";
import {processErr} from "../../../_api/errorProcessing";

export const MusicFormCardContent = (props) => {
    const [groupName, setGroupName] = useState('')
    const [x, setX] = useState(NaN)
    const [y, setY] = useState(NaN)
    const [numberOfParticipants, setNumberOfParticipants] = useState(NaN)
    const [sales, setSales] = useState(NaN)
    const [genre, setGenre] = useState('')
    const [description, setDescription] = useState('')

    const [id, setId] = useState(NaN)

    const {formMethod} = props

    const validateForm = (formMethod) => {
        let expr = !(
            isEmpty(groupName)
            || isNaN(x)
            || isNaN(y)
            || isNaN(numberOfParticipants)
            || isNaN(sales)
            || isEmpty(description)
        )
        if (formMethod === 'PUT' || formMethod === 'DEL')
            expr = expr && !isNaN(id)
        return expr
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        if (validateForm()) {
            switch (formMethod) {
                case 'POST': {
                    postNewMusicBand(TOKEN, null, {
                        name: groupName,
                        x: x,
                        y: y,
                        numOfParticipants: numberOfParticipants,
                        description: description,
                        genre: genre,
                        sales: sales
                    })
                        .then(data => data.json())
                        .then(response => {
                                if (response.status) {
                                    response.errors.map((err, _) => {
                                        notify(processErr(err), 'error')
                                    })
                                } else notify(`Music Band [${response.id}] has been successfully created`, 'yay')
                            }
                        )
                    break;
                }
                case 'PUT': {
                    updateMusicBand(TOKEN, null, {
                        id: id,
                        name: groupName,
                        x: x,
                        y: y,
                        numOfParticipants: numberOfParticipants,
                        description: description,
                        genre: genre,
                        sales: sales
                    })
                        .then(data => {
                            if (data.status === 200) notify(`Music Band [${id}] has been successfully updated`, 'yay')
                            else if (data.status === 400) {
                                data.json()
                                    .then(response =>  response.errors.map((err, _) => notify(processErr(err), 'error')))

                            } else data.json()
                                .then(response => notify(response.message, 'error'))
                        })

                    break;
                }
            }
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
                                onChange={e => setGroupName(e.target.value)}
                                required/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter coordinates:</Form.Label>
                            <Row className={`w-100 text-center`}>
                                <Col className={`float-left`}>
                                    <Form.Control
                                        type={`number`}
                                        placeholder={`X`}
                                        value={x}
                                        onChange={e => setX(Number(e.target.value))}
                                        required/>
                                </Col>
                                <Col className={`float-right`}>
                                    <Form.Control
                                        type={`number`}
                                        placeholder={`Y`}
                                        value={y}
                                        onChange={e => setY(Number(e.target.value))}
                                        required/>
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
                                required
                            />
                        </Form.Group>
                        <Form.Label>Enter label' sales:</Form.Label>
                        <InputGroup>
                            <Form.Control
                                type={`number`}
                                placeholder={`Sales`}
                                value={sales}
                                onChange={e => setSales(Number(e.target.value))}
                                required
                            />
                        </InputGroup>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Select genre</Form.Label>
                            <Form.Control
                                required
                                as={'select'}
                                value={genre}
                                onChange={e => setGenre(e.target.value)}>
                                <option value={null}>Choose option</option>
                                <option value={'PSYCHEDELIC_ROCK'}>PSYCHEDELIC ROCK</option>
                                <option value={'RAP'}>RAP</option>
                                <option value={'PSYCHEDELIC_CLOUD_RAP'}>PSYCHEDELIC CLOUD RAP</option>
                                <option value={'POP'}>POP</option>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                    {
                        (formMethod === 'PUT' || formMethod === 'DEL') &&
                        <Col>
                            <Form.Group>
                                <Form.Label>Enter ID</Form.Label>
                                <Form.Control
                                    type={`number`}
                                    placeholder={`ID`}
                                    value={id}
                                    onChange={e => setId(Number(e.target.value))}
                                    required/>
                            </Form.Group>
                        </Col>

                    }

                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Enter description</Form.Label>
                            <Form.Control
                                as={'textarea'}
                                value={description}
                                onChange={e => setDescription(e.target.value)}
                                required
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