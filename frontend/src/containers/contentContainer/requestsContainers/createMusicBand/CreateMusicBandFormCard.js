import '../styles.css';
import {ExpandableContainer} from "../../../expandableContainer/ExpandableContainer";
import {CreateMusicBandFormContent} from "./CreateMusicBandFormContent";

export const CreateMusicBandFormCard = () => {
    return (
        <ExpandableContainer title={"Create music band"}>
            <CreateMusicBandFormContent/>
        </ExpandableContainer>
    )
}