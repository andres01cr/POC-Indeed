import React, { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import { Mutation } from 'react-apollo';
import gql from 'graphql-tag';

const ADD_TUTORIAL = gql`
  mutation($title: title, $description: description, $published ) {
    createPost(title: "Test",
    authorId: "420d29b8-5b91-47c9-8960-9f7785ab9521",
  	text: "Any Text" ){
    	id
    	title
    	author {
        id,
        name
      }
    }
  }
`;

export default class AddTutorial extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.onChangePublished = this.onChangePublished.bind(this);
    this.saveTutorial = this.saveTutorial.bind(this);
    this.newTutorial = this.newTutorial.bind(this);

    this.state = {
      id: null,
      title: "",
      description: "", 
      published: false,
      submitted: false
    };
  }

  onChangeTitle(e) {
    this.setState({
      title: e.target.value
    });
  }

  onChangePublished(e) {
    this.setState({
      published: e.target.checked
    });
  }

  onChangeDescription(e) {
    this.setState({
      description: e.target.value
    });
  }

  saveTutorial() {
    var data = {
      title: this.state.title,
      description: this.state.description,
      published: this.state.published
    };

    TutorialDataService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          title: response.data.title,
          description: response.data.description,
          published: response.data.published,

          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newTutorial() {
    this.setState({
      id: null,
      title: "",
      description: "",
      published: false,

      submitted: false
    });
  }

  renderSubmitButton() {
    
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
            <button className="btn btn-success" onClick={this.newTutorial}>
              Add
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="title">Title</label>
              <input
                type="text"
                className="form-control"
                id="title"
                required
                value={this.state.title}
                onChange={this.onChangeTitle}
                name="title"
              />
            </div>

            <div className="form-group">
              <label htmlFor="description">Description</label>
              <input
                type="text"
                className="form-control"
                id="description"
                required
                value={this.state.description}
                onChange={this.onChangeDescription}
                name="description"
              />
            </div>
            <div className="form-group">
              <label htmlFor="published">Published</label>
              <input
                type="checkbox"
                id="published"
                required
                checked={this.state.published}
                onChange={this.onChangePublished}
                name="published"
              />
            </div>

            <Mutation mutation={ADD_TUTORIAL} variables={{title:this.state.title, description:this.state.description , published: this.state.published}}>
              {newTutorial => (
                <button className="btn btn-success"  type="button" onClick={newTutorial}>
                  Submit
                </button>
              )}
            </Mutation>
          </div>
        )}
      </div>
    );
  }
}
