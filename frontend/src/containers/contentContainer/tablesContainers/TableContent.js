import {Table} from "react-bootstrap";

export const TableContent = () => {
    return (
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>ID</th>
                <th>Band Name</th>
                <th>Genre</th>
                <th>Label</th>
                <th>Number of participants</th>
                <th>Coordinates</th>
                <th>Creation Date</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <th>1</th>
                    <th>1</th>
                    <th>1</th>
                    <th>1</th>
                    <th>1</th>
                    <th>1</th>
                    <th>1</th>
                    <th>1</th>
                </tr>
            </tbody>
        </Table>
    )
}