import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Home} from "./containers/homeContainer/Home";

export const App = () => {
    return (
        <div className="bg">
          <Home/>
        </div>
    );
}

export default App;
