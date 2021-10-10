import "./styles.css"
import {MDBDataTable, MDBDataTableV5, MDBTable} from "mdbreact";
import {useEffect, useMemo, useState} from "react";
import {data} from "./_tableData";
import {defaultOptions} from "../../../_api/_options";
import {useTable} from "react-table/src/hooks/useTable";
import {AiOutlineDelete} from "react-icons/all";

export const TableContent = () => {


    const [tableData, setTableData] = useState([])
    const [searchTitle, setSearchTitle] = useState("")
    const [page, setPage] = useState(1)
    const [count, setCount] = useState(0)
    const [pageSize, setPageSize] = useState(3)

    const columns = useMemo(
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

    const data = useMemo(
        () => [

        ], []
    )

    const pageSizes = [5, 10, 25]


    const {fetchTableData} = require("./_tableData")

    const retrieveData = () => {
        fetchTableData(
            defaultOptions
        ).then(res => {
            setTableData(res.content)
            setCount(res.totalPages)
        })
    }

    const deleteRow = (id) => {
        console.log(id)
    }

    const handlePageChange = (e, value) => {
        setPage(value)
    }

    const handlePageSizeChange = (e) => {
        setPageSize(e.target.value)
        setPage(1)
    }

    useEffect(retrieveData,
        [fetchTableData, page, pageSizes])


    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        rows,
        prepareRow
    } = useTable({
        columns: data.columns,
        data: data.rows

    })

    return (
        <>
            <table className={`table table-striped table-bordered`}
                   {...getTableProps}>
                <thead>
                {headerGroups.map(group => {
                    <tr {...group.getHeaderGroupProps()}>
                        {group.headers.map(col => (
                            <th {...col.getHeaderProps()}>
                                {col.render("Header")}
                            </th>
                        ))}
                    </tr>
                })}
                </thead>
                <tbody {...getTableBodyProps()}>
                {rows.map((row, i) => {
                    prepareRow(row);
                    return (
                        <tr {...row.getRowProps()}>
                            {row.cells.map((cell) => {
                                return (
                                    <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                                );
                            })}
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </>
    )
}