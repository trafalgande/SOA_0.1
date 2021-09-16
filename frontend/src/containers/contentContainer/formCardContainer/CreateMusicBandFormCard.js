import {Card, Container} from "react-bootstrap"
import './styles.css'
import {ExpandedCreateMusicFormCardContent} from "./ExpandedCreateMusicFormCardContent";
import {ShrinkedCreateMusicFormCardContent} from "./ShrinkedCreateMusicFormCardContent";
import {useState} from "react";
import {AiOutlineArrowDown, AiOutlineArrowUp} from "react-icons/all";

export const CreateMusicBandFormCard = () => {
    const [clicked, setClicked] = useState(false)
    return (
        <Container className={`p-5`}>
            <Card className={`w-50 shadow`}>
                <Card.Title
                    className={`p-3 text-center`}
                    onClick={e => setClicked(!clicked)}>
                    Create music band <br/>{clicked ? <AiOutlineArrowUp/> : <AiOutlineArrowDown/>}
                </Card.Title>
                {
                    clicked ?
                        <ExpandedCreateMusicFormCardContent/> :
                        <ShrinkedCreateMusicFormCardContent/>
                }
            </Card>

        </Container>
    )
}