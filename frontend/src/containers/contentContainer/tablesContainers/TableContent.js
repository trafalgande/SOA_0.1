import "./styles.css"
import {MDBDataTable, MDBDataTableV5, MDBTable} from "mdbreact";
import {data} from "./_tableData"

export const TableContent = () => {
    return (
            <MDBDataTable
                entriesOptions={[5, 20, 25]}
                scrollY
                entries={5}
                data={data}
            />
    )
}