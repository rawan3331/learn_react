import React, {Component} from 'react'
import TeamMember from "./TeamMember";
import memberInfo from './memberInfo'


class Team extends Component{
    constructor(){
      super()
      this.state = {
          memberInfo : memberInfo,
          members: [],
          counter: 0,
      }

      this.clickEvent = this.clickEvent.bind(this)
    }



    clickEvent() {
      if(this.state.counter < this.state.memberInfo.length){
        this.state.members.push(this.state.memberInfo[this.state.counter]);
        this.setState(function(prevState){
          return {counter: prevState.counter+1}
        })
        console.log(this.state.members);

        this.newMembers = this.state.members.map(member =>{
          return <TeamMember
            key={member.id}
            img={member.img}
            name={member.name}
            position={member.position}
            phone={member.phone}
            email={member.email}
            website={member.website}
          />
        })
      }
    }
    render() {
        return(
            <div className="row">
              <button style={{margin:'20px'}} className="btn btn-primary btn-lg btn-block" onClick={this.clickEvent}>عرض الموظف</button>

              {this.newMembers}
            </div>
        )
    }
}
export default Team;

//
