import React, {Component} from "react";

class App extends Component {
    constructor(){
        super();
        this.state = { number:0 }
        this.clickHandler = this.clickHandler.bind(this);
    }

    clickHandler(){
        // this.setState({number:1});
        this.setState(prevState => {
            return { number: prevState.number + 1 };
        })
    }
    render(){
        return(
            <div>
                <h2>{this.state.number}</h2>
                <button onClick={this.clickHandler} className="btn btn-primary">Click Me!</button>
            </div>
        )
    }
}

export default App