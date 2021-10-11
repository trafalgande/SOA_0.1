import {ExpandableContainer} from "../../../../expandableContainer/ExpandableContainer";
import {ByLabelValueContent} from "./ByLabelValueContent";

export const ByLabelValueCard = () => {
    return (
        <ExpandableContainer title={"By label sales"}>
            <ByLabelValueContent/>
        </ExpandableContainer>
    )
}