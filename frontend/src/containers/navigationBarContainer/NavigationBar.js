import {Col, Container, Row} from "react-bootstrap";
import {Link} from "react-router-dom";
import './styles.css'

export const NavigationBar = () => {
    return (
        <Container className={`p-3 text-center`}>
            <Row>
                <Col>
                    <Link to={'/requests'} className={`custom-link`} style={{color:"#252525"}}> Requests </Link>
                </Col>
                <Col>
                    <Link to={'/tables'} className={`custom-link`} style={{color:"#252525"}}> Tables </Link>
                </Col>
            </Row>
        </Container>
    )
}