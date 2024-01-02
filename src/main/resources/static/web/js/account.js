const {createApp}= Vue

 
let app=createApp({
    data(){
        return{
            data:[],
            account:[],
            transactions:[],
            id:null, 

    }
},

created(){
    const search=location.search
    const params=new URLSearchParams(search)
    this.id=params.get("id")
    this.loadData()

},
methods:{
    loadData(){
        axios.get("/api/clients/current")
        .then (response=>{
            this.data=response.data
            console.log(this.data)
            this.account=response.data.accounts.find(account=> account.id==this.id)
            console.log(this.account)
            this.transactions=response.data.accounts.find(account=> account.id==this.id).transaction
            console.log(this.transactions)
        
            
            
        } )
           
        .catch(error=>console.log(error))
    },
   
    montoformateado(monto){
    if (monto!== undefined && monto !== null){
     return  monto.toLocaleString('en-US', { style: 'currency', currency: 'USD' })
    
    }
   },
   
   logout() {
    axios.post("/api/logout" )
        .then(response => {
        window.location.href="http://localhost:8080/web/pages/index.html"
        })

        .catch(error => console.log(error))
}


}

}).mount("#app")