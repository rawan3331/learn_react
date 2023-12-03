import React, {Component} from "react";

class TeamMember extends Component{

    render(){
        return(
            <div  className="col-md-4 col-sm-6">
                <div className="card shadow-sm">
                    <div className="card-header bg-primary text-white text-center">
                    <img style={{ width: '100%', height: '200px', objectFit: 'cover' }} src={this.props.img} alt="" />
                    </div>
                    <div className="card-body">
                    <h2 className="card-title text-center mb-3">{this.props.name}</h2>
                    <h5 className="card-subtitle mb-3 text-muted text-center">{this.props.position}</h5>
                    <div className="card-text">
                        <p className="mb-1">Phone: {this.props.phone}</p>
                        <p className="mb-1">Email: {this.props.email}</p>
                        <p className="mb-1">Website: {this.props.website}</p>
                    </div>
                    </div>
                </div>
            </div>

        )
    }
}

export default TeamMember