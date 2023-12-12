const {createApp}= Vue

let app=createApp({
    data(){
        return{
            data:[],
            accounts:[],
            transactions:[],

    }
},

created(){
    this.loadData()
},
methods:{
    loadData(){
        axios.get("/api/accounts/1")
        .then (response=>{
            this.data=response.data
            console.log(this.data)
            this.transactions=response.data.transactions
            console.log(this.transactions)
            
        } )
           
        .catch(error=>console.log(error))
    },


}

}).mount("#app")