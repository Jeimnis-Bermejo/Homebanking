const {createApp}= Vue

let app=createApp({
    data(){
        return{
            data:[],
            accounts:[],

    }
},

created(){
    this.loadData()
},
methods:{
    loadData(){
        axios.get("api/accounts/1")
        .then (response=>{
            this.data=response.data
            console.log(this.data)
            this.accounts=response.data.accounts
            console.log(this.accounts)
            
        } )
           
        .catch(error=>console.log(error))
    },




}

}).mount("#app")

