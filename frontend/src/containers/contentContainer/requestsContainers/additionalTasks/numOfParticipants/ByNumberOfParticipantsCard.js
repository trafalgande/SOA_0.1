import {ExpandableContainer} from "../../../../expandableContainer/ExpandableContainer";
import {ByNumberOfParticipantsContent} from "./ByNumberOfParticipantsContent";

export const ByNumberOfParticipantsCard = () => {
    return (
        <ExpandableContainer title={"Count by number of participants"}>
            <ByNumberOfParticipantsContent/>
        </ExpandableContainer>
    )
}