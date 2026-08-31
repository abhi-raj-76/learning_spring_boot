import React, { Component } from 'react'
import axios from 'axios'
class Form extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         proId: '',
         proName: '',
         proPrice:'',
         proImage: null
      }
    }
    handleState = event =>{
        const {name,value,files} = event.target
        this.setState({
            [name] : files ? files[0]: value
            //if the name then value or if the file then files[0] value
        })
    }
    handleSubmit = event =>{
        const formData = new FormData();
        const product = {
            proId:this.state.proId,
            proName:this.state.proName,
            proPrice:this.state.proPrice
        };

        /*formData.append('proId',this.state.proId);
        formData.append('proName',this.state.proName);
        formData.append('proPrice',this.state.proPrice);*/
        //this above code will not work because in the backend we are
        // expecting prod+img not (proId+proName+...proImage)
        // so we need to combine the all these in one prod

        formData.append('prod',new Blob(
            [JSON.stringify(product)],
            {type:"application/json"}
        ))
        formData.append('proImage',this.state.proImage);

        axios.post('http://localhost:8080/product',formData)
        .then(response => {
            console.log(response)
        })
        .catch(error => {
            console.log(error)
        })
    }
  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label> Product Id</label>
        <input type='text' name='proId' value={this.state.proId} onChange={this.handleState}/>
        <label> Product Name</label>
        <input type='text' name='proName' value={this.state.proName} onChange={this.handleState}/>
        <label> Price</label>
        <input type='text' name='proPrice' value={this.state.proPrice} onChange={this.handleState}/>
        <label>Product Image</label>
        <input
        type='file'
        name='proImage'
        accept='image/*'
        onChange={this.handleState}/>
        <button type='submit'>Submit Form</button>
      </form>
    )
  }
}

export default Form
