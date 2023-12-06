const {createApp}= Vue

let app=createApp({
    data(){
        return{
            data:[],
            name:"",
            lastname:"",
            email:"",

    }
},

created(){
    this.loadData()
},
methods:{
    loadData(){
        axios.get("/clients")
        .then (response=>{
            this.data=response.data._embedded.clients
            console.log(this.data)
        } )
           
        .catch(error=>console.log(error))
    },


  createPerson(){
  axios.post("/clients",{ "firstName": this.name,
  "lastName": this.lastname,
  "email": this.email,})
  .then (response=>{
    this.data=response
    this.loadData()
  })

    .catch(error=>console.log(error))
    }

}

}).mount("#app")

