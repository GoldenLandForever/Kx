
export default{
    state:{
        status: "matching",  // matching，playing
        model:"none",//none,local,bot,match
        socket: null,
        opponent_username: "",
        opponent_photo: "",
        loser: "none",  // none、all、A、B
        a_id: '',
        b_id: '',
        a_rating: '',//rank分
        b_rating: '',
        // aMap: [0,0,0,0,0,0,0,0,0],//当前棋盘
        // bMap: [0,0,0,0,0,0,0,0,0],
        aMap: [1,1,1,1,1,1,1,1,0],//当前棋盘
        bMap: [0,0,0,0,0,0,0,0,0],
        a_score: 0,//当前对局得分
        b_score: 0
    },
    getters:{

    },
    mutations:{
        clearMap(state){
            state.aMap = [0,0,0,0,0,0,0,0,0];
            state.bMap =[0,0,0,0,0,0,0,0,0];
        },
        updateModel(state,model){
            state.model = model;
        },
        updateStatus(state, status) {
            state.status = status;
        },
        updateLoser(state, loser) {
            state.loser = loser;
        },
    },
    actions:{

    },
    module:{

    }
}