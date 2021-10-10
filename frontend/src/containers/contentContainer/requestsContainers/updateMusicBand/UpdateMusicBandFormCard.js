import '../styles.css';
import {ExpandableContainer} from "../../../expandableContainer/ExpandableContainer";
import {UpdateMusicBandFormContent} from "./UpdateMusicBandFormContent";

export const UpdateMusicBandFormCard = () => {
    return (
        <ExpandableContainer title={"Update music band"}>
            <UpdateMusicBandFormContent/>
        </ExpandableContainer>
    )
}