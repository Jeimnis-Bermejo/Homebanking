const {createApp}= Vue

let app=createApp({
    data(){
        return{
            data:[],
            cards:[], 
            cardType:""  ,
            colorType:"",
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
            this.cards=response.data.cards
            console.log(this.data)
            console.log(this.cards)
            
        } )
           
        .catch(error=>console.log(error))
    },

    logout() {
        axios.post("/api/logout" )
            .then(response => {
            window.location.href="http://localhost:8080/web/pages/index.html"
            })
    
            .catch(error => console.log(error))
    },
    creationCard() {
        axios.post("/api/clients/current/cards?type="+ this.cardType+ "&color="+this.colorType)
            .then(response => {
                console.log(response)

            }).catch(e => console.log(e))

    }

}

}).mount("#app")

