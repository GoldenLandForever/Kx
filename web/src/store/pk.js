
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
        aMap: [0,0,0,0,0,0,0,0,0],//当前棋盘
        bMap: [0,0,0,0,0,0,0,0,0],
        a_score: 0,//当前对局得分
        b_score: 0,
        turn:'A',
        a_point: 0,
        b_point:0,
        a_photo:"",
        b_photo:"",
    },
    getters:{

    },
    mutations:{
        updateSocket(state, socket) {
            state.socket = socket;
        },
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
        updateOpponent(state, opponent) {
            state.opponent_username = opponent.username;
            state.opponent_photo = opponent.photo;
        },
        updateGame(state, game) {
            state.aMap = game.aMap;
            state.bMap = game.bMap;
            state.a_id = game.a_id;
            state.b_id = game.b_id;
            state.a_rating = game.a_rating;
            state.b_rating = game.b_rating;
            state.turn = game.turn;
            state.a_point = game.a_point;
            state.b_point = game.b_point;
            state.a_photo = game.a_photo;
            state.b_photo = game.b_photo;
        },
        updateRes(state,MyData){
            state.loser = MyData.loser;
            state.a_score = MyData.a_score;
            state.b_score = MyData.b_score;
        },
        updateApoint(state, MyData){
            state.a_point = MyData.a_point;
        },
        updateBpoint(state, MyData){
            state.b_point = MyData.b_point;
        },
        updateCurMap(state, MyData){
            state.turn = MyData.turn;
            state.aMap = MyData.aMap;
            state.bMap = MyData.bMap;
            state.a_point = MyData.a_point;
            state.b_point = MyData.b_point;
        },
    },
    actions:{

    },
    module:{

    }
}