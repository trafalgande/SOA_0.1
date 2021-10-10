import {getAllMusicBands} from "../../../_api/client";
import {TableItemEditButton} from "./TableItemEditButton";

const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2ODU5ODU0NC1hYWRjLTQ2ZjgtODBjYS1lOTI0MWM3NzZjOGMsbG1hbyIsImlhdCI6MTYzMzgwNzkzOSwiZXhwIjoxNjM0NDEyNzM5fQ.PBy06PlyHx13yvSqCOLOniNVU-wfjwtDwvJ0s1OMFbgegFfPLdugPuwMRHdRhi97ig6bfvKA59bmEAvU0YKBNw'

const fetchData = () => {
    return getAllMusicBands(token, null)
        .then(data => data.json())
}
const assembleContentRows = () => {
    let rows = []
    fetchData().then(content => {
        content.map((element, i) => (
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
                        edit: <TableItemEditButton/>
                    }
                )
            )
        )
    })
    return rows
}

export const data = {
    columns: [
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
    rows: assembleContentRows()
}

