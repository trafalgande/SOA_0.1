import {getAllMusicBands} from "../../../_api/client";
import {TableItemEditButton} from "./TableItemEditButton";
import {useState} from "react";
import {AiOutlineDelete} from "react-icons/all";

const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2ODU5ODU0NC1hYWRjLTQ2ZjgtODBjYS1lOTI0MWM3NzZjOGMsbG1hbyIsImlhdCI6MTYzMzgwNzkzOSwiZXhwIjoxNjM0NDEyNzM5fQ.PBy06PlyHx13yvSqCOLOniNVU-wfjwtDwvJ0s1OMFbgegFfPLdugPuwMRHdRhi97ig6bfvKA59bmEAvU0YKBNw'

export const fetchTableData = (options) => {
    return getAllMusicBands(token, options)
        .then(data => data.json())
}


const assembleContentRows = () => {
    let rows = []
    let response = {}
    fetchTableData().then(r => {
        response = r
        r.content.map((element, i) => (
                rows[i] = (
                    {
                        id: element.id,
                        name: element.name,
                        genre: element.genre,
                        sales: element.label.sales.toFixed(2),
                        numberOfParticipants: element.numberOfParticipants,
                        x: element.coordinates.x.toFixed(2),
                        y: element.coordinates.y.toFixed(2),
                        description: element.description,
                        actions: <TableItemEditButton/>
                    }
                )
            )
        )
    })
    return [rows, response]
}

export const [rows, response] = assembleContentRows()
export const data = React.useMemo(
    () => [
        {
            Header: '#',
            accessor: 'id',
        },
        {
            Header: 'Band Name',
            accessor: 'name',
        },
        {
            Header: 'Genre',
            accessor: 'genre',
        },
        {
            Header: 'Label [Sales]',
            accessor: 'sales',
        },
        {
            Header: '# of participants',
            accessor: 'numberOfParticipants',
        },
        {
            Header: 'X',
            accessor: 'x',
        },
        {
            Header: 'Y',
            accessor: 'y',
        },
        {
            Header: 'Description',
            accessor: 'description',
        },
        {
            Header: 'Actions',
            accessor: 'actions',
            Cell: (props) => {
                const rowId = props.row.id
                return (
                    <div>
                        <span onClick={() => deleteRow(rowId)}>
                            <AiOutlineDelete/>
                        </span>
                    </div>
                )
            }
        }
    ], []
)

const deleteRow = (id) => {
    console.log(id)
}

/*    columns: [
        {
            label: '#',
            field: 'id',
            width: 50,
            attributes: {
                'aria-controls': 'DataTable',
                'aria-label': '#',
            },
        },
        {
            label: 'Band Name',
            field: 'name',
            sort: 'asc',
            width: 150
        },
        {
            label: 'Genre',
            field: 'genre',
            sort: 'asc',
            width: 150
        },
        {
            label: 'Label [Sales]',
            field: 'sales',
            sort: 'asc',
            width: 150
        },
        {
            label: '# of participants',
            field: 'numberOfParticipants',
            sort: 'asc',
            width: 150
        },
        {
            label: 'X',
            field: 'x',
            sort: 'asc',
            width: 100
        },
        {
            label: 'Y',
            field: 'y',
            sort: 'asc',
            width: 100
        },
        {
            label: 'Description',
            field: 'description',
            sort: 'asc',
            width: 150
        },
        {
            label: 'Edit',
            field: 'edit',
            sort: 'disabled',
            width: 50
        }
    ],
*/

