import {ExpandableContainer} from "../../../../expandableContainer/ExpandableContainer";
import {ByDescriptionLengthContent} from "./ByDescriptionLengthContent";

export const ByDescriptionLengthCard = () => {
    return (
        <ExpandableContainer title={"Description length task"}>
            <ByDescriptionLengthContent/>
        </ExpandableContainer>
    )
}