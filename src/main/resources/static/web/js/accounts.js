const {createApp}= Vue

let app=createApp({
    data(){
        return{
            data:[],
            accounts:[],
            loans:[],  
    }
},

created(){
    this.loadData()
},
methods:{
    loadData(){
        axios.get("/api/clients/current")
        .then (response=>{
            this.data=response.data
             this.accounts=response.data.accounts.sort((a,b)=>a-b)
             this.loans=response.data.loans
            console.log(this.data)
            console.log(this.accounts)
            console.log(this.transactions)
            
        } )
           
        .catch(error=>console.log(error))
    },

    montoformateado(monto){
        if (monto!== undefined && monto !== null){
         return  monto.toLocaleString('en-US', { style: 'currency', currency: 'USD' })
        
        }
    
       }


}

}).mount("#app")

