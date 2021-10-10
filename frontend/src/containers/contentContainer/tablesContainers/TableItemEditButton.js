import {Button} from "react-bootstrap";
import {AiOutlineMenu} from "@react-icons/all-files/ai/AiOutlineMenu";

export const TableItemEditButton = (props) => {
    const {id} = props

    return (
        <button className={`btn-fancy-simple`}>
            <AiOutlineMenu/>
        </button>
    )
}