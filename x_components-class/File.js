import React, {Component} from 'react'
class File extends Component{
    render() {
        return(
            <h1>{this.props.name} {this.props.name2}</h1>
        )
    }
}
export default File