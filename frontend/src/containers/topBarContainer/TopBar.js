import {Col, Container, Row} from "react-bootstrap";
import './styles.css'

export const TopBar = () => {
    return (
        <Container className="top-bar-container">
            <Row>
                <Col className="top-bar-brand-name">
                    <h3>MUSIC BANDS</h3>
                </Col>

                <Row className="top-bar-account-section">
                    <button
                        className="btn-fancy-simple">
                       SIGN IN
                    </button>
                    <div className={`vertical-divider`}/>
                    <button
                        className="btn-fancy-simple">
                        SIGN UP
                    </button>
                </Row>
            </Row>
        </Container>
    )
}