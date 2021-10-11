import "./styles.css"
import {useEffect, useMemo, useState} from "react";
import {withDefaultOptions, TOKEN} from "../../../_api/_options";
import {usePagination, useSortBy, useTable} from "react-table";
import {getAllMusicBands} from "../../../_api/client";
import {TableItemDeleteButton} from "./TableItemDeleteButton";
import {AiOutlineDown} from "@react-icons/all-files/ai/AiOutlineDown";
import {AiOutlineUp} from "@react-icons/all-files/ai/AiOutlineUp";
import {Col, Form, FormControl, InputGroup, Row} from "react-bootstrap";
import {AiOutlineForward} from "react-icons/all";
import {IoChevronForwardSharp} from "@react-icons/all-files/io5/IoChevronForwardSharp";
import {AiOutlineBackward} from "@react-icons/all-files/ai/AiOutlineBackward";
import {IoChevronBackSharp} from "@react-icons/all-files/io5/IoChevronBackSharp";
import {notify} from "../../notificationContainer/notifications";

export const TableContent = () => {

    const [tableData, setTableData] = useState([])
    const [search, setSearch] = useState("")
    const pageSizes = [5, 10, 25]

    const fetchTableData = (options = withDefaultOptions()) => {
        let _tableData = []
        getAllMusicBands(TOKEN, options)
            .catch(err => notify('Internal error', 'error'))
            .then(data => data.json())
            .then(res => {
                res.content.map((element, i) => {
                        let _id = element.id
                        _tableData[i] = ({
                                id: _id,
                                name: element.name,
                                genre: element.genre,
                                sales: element.label.sales.toFixed(2),
                                numberOfParticipants: element.numberOfParticipants,
                                x: element.coordinates.x.toFixed(2),
                                y: element.coordinates.y.toFixed(2),
                                description: element.description,
                                actions: <TableItemDeleteButton onClick={refresh} id={_id}/>
                            }
                        )
                    }
                )
                setTableData(_tableData)
                setSearch("")
            })

    }

    useEffect(fetchTableData, [])

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
            }
        ], []
    )

    const refresh = () => {
        fetchTableData()
    }

    const withFilter = (item) => {
        return item.name.includes(search)
            || item.genre.includes(search)
            || item.description.includes(search)
        /* || String(item.id).includes(search)
         || String(item.sales).includes(search)
         || String(item.numberOfParticipants).includes(search)
         || String(item.x).includes(search)
         || String(item.y).includes(search)*/
    }

    const handleSearch = (searchItem) => {
        if (searchItem.includes('@')) {
            if (searchItem.includes('@desc<')) {
                const param = searchItem.replace('@desc<', '')
                fetchTableData(withDefaultOptions(`by-description-length=${Number(param)}`))
            } else if (searchItem.includes('@sales>')) {
                const param = searchItem.replace('@sales>', '')
                fetchTableData(withDefaultOptions(`by-label-value=${Number(param)}`))
            }
        } else {
            setTableData(tableData.filter(item => withFilter(item)))
        }
    }

    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        prepareRow,
        page,
        canPreviousPage,
        canNextPage,
        pageOptions,
        pageCount,
        gotoPage,
        nextPage,
        previousPage,
        setPageSize,
        state: {pageIndex, pageSize},
    } = useTable({
        columns,
        data: tableData,
        initialState: {pageIndex: 0},
    }, useSortBy, usePagination)

    return (
        <>
            <Row className={`m-2`}>
                <Col sm={4}>
                    <button onClick={() => gotoPage(0)} disabled={!canPreviousPage}
                            className={`button-4 mr-2`}>
                   <span>
                       <AiOutlineBackward/>
                   </span>

                    </button>
                    <button onClick={() => previousPage()} disabled={!canPreviousPage}
                            className={`button-4 mr-2`}>
                   <span>
                       <IoChevronBackSharp/>
                   </span>
                    </button>
                    <button onClick={() => nextPage()} disabled={!canNextPage}
                            className={`button-4 mr-2`}>
                    <span>
                        <IoChevronForwardSharp/>
                    </span>

                    </button>
                    <button onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage}
                            className={`button-4 mr-2`}>
                    <span>
                        <AiOutlineForward/>
                    </span>
                    </button>
                </Col>
            </Row>
            <Row className={`m-2`}>
                <Col sm={3}>
                    <Form>
                        <InputGroup className={`w-50`}>
                            <InputGroup.Text>Show</InputGroup.Text>
                            <FormControl
                                as={`select`}
                                value={pageSize}
                                onChange={e => {
                                    setPageSize(Number(e.target.value))
                                }}
                            >
                                {pageSizes.map(pageSize => (
                                    <option key={pageSize} value={pageSize}>
                                        {pageSize}
                                    </option>
                                ))}
                            </FormControl>
                        </InputGroup>
                    </Form>
                </Col>
                <Col sm={4}>
                    <Form>
                        <InputGroup className={`w-50`}>
                            <InputGroup.Text>Page</InputGroup.Text>
                            <FormControl
                                id={`inlinePage`}
                                type={`number`}
                                value={pageIndex + 1}
                                onChange={e => {
                                    const page = e.target.value ? Number(e.target.value) - 1 : 0
                                    gotoPage(page)
                                }}
                                style={{width: '50px'}}
                            />
                            <InputGroup.Text> of {pageOptions.length}</InputGroup.Text>
                        </InputGroup>
                    </Form>
                </Col>

                <Col sm={4} className={`float-right`}>
                    <Form>
                        <InputGroup className={`w-100`}>
                            <FormControl
                                placeholder={`Search`}
                                type={`text`}
                                value={search ? search : ""}
                                onChange={e => {
                                    setSearch(e.target.value)
                                }}
                                onBlur={() => {
                                    if (search === "") refresh()
                                    else handleSearch(search)
                                }}
                            />
                        </InputGroup>
                    </Form>
                </Col>
            </Row>


            <table className={`table table-hover table-responsive-sm sticky-top`}
                   {...getTableProps}>
                <thead>

                {headerGroups.map(group => {
                    return (
                        <tr {...group.getHeaderGroupProps()}>
                            {group.headers.map(col => (
                                col.Header !== 'Actions' ?
                                    <th {...col.getHeaderProps(col.getSortByToggleProps())}>
                                        {col.render('Header')}
                                        {
                                            col.isSorted
                                                ? col.isSortedDesc
                                                    ? <AiOutlineDown/>
                                                    : <AiOutlineUp/>
                                                : ''
                                        }

                                    </th> :

                                    <th {...col.getHeaderProps()}>
                                        {col.render('Header')}
                                    </th>
                            ))}
                        </tr>
                    )
                })}
                </thead>
                <tbody {...getTableBodyProps()} >
                {page.map((page, _) => {
                    prepareRow(page);
                    return (
                        <tr {...page.getRowProps()}>
                            {page.cells.map((cell) => {
                                return (
                                    <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
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