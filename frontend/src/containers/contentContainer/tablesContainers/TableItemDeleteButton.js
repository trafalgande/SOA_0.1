import {AiFillDelete} from "@react-icons/all-files/ai/AiFillDelete";
import {deleteMusicBand} from "../../../_api/client";
import {TOKEN} from "../../../_api/_options";
import {notify} from "../../notificationContainer/notifications";

export const TableItemDeleteButton = (props) => {
    const {id, onClick} = props

    const deleteCurrentItem = (id) => {
        deleteMusicBand(TOKEN, null, id)
            .then(data => {
                if (data.status === 200) {
                    notify(`Music Band [${id}] has been successfully deleted`, 'yay')
                    onClick()
                } else data.json()
                    .then(response => notify(response.message, 'error'))
                    .then(onClick())
            })

    }

    return (
        <button className={`btn-fancy-simple-sm`} style={{width: "30px"}} onClick={() => {
            deleteCurrentItem(id)
        }}>
            <AiFillDelete className={`w-50 h-50`}/>
        </button>
    )
}