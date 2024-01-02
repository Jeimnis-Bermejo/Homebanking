const { createApp } = Vue

let app = createApp({
    data() {
        return {
            data: [],
            name: "",
            lastname: "",
            email: "",
            password: "",

        }
    },

    created() {

    },
    methods: {
        login() {
            axios.post("/api/login?email=" + this.email + "&password=" + this.password)
                .then(response => {
                    if (response.status == 200) {
                        window.location.href = "./Accounts.html"
                    }
                    console.log(this.data)
                })

                .catch(error => console.log(error))
        },

        getEmail(event) {
            this.email = event.target.value
        },

        getPassword(event) {
            this.password = event.target.value

        },
        getName(event) {
            this.name = event.target.value
        },

        getLastName(event) {
            this.lastname = event.target.value

        },


        registerClients() {
            axios.post("/api/clients?firstName=" + this.name + "&lastName=" + this.lastname + "&email=" + this.email + "&password=" + this.password)
                .then(response => {
                    console.log(response)
                    this.login()

                })

                .catch(error => console.log(error))
        },

        abrirmodal() {
            const dialog = document.querySelector("dialog")
            dialog.showModal()
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


