import 'bootstrap/dist/css/bootstrap.min.css';
import {Route, Switch, withRouter} from "react-router-dom";
import {RequestsContentContainer} from "./containers/contentContainer/requestsContainers/RequestsContentContainer";
import {TopBar} from "./containers/topBarContainer/TopBar";
import {TableContentContainer} from "./containers/contentContainer/tablesContainers/TableContentContainer";
import {_Toaster} from "./containers/notificationContainer/notifications";

export const App = (props) => {
    const { history } = props

    return (
        <div className={`custom-bg`}>
            <_Toaster />
            <TopBar/>
            <Switch>
                <Route history={history} path={'/requests'} component={RequestsContentContainer}/>
                <Route history={history} path={'/tables'} component={TableContentContainer}/>
            </Switch>
        </div>

    );
}

export default withRouter(App);
