import Vue from 'vue'
import Vuex from 'vuex'
import mainApi from "../api/mainApi";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem('token'),
        profile,
        notes
    },
    getters: {
        isValid: state => state.token !== null && state.token !== '' && state.token !== undefined && state.token !== 'undefined',
        sortedPosts: state => (state.notes || []).sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addTokenMutation(state, token) {
            state.token = token
        },
        addProfileMutation(state, profile) {
            state.profile = profile
        },
        addNotesMutation(state, note) {
            state.notes = [
                ...state.notes, note
            ]
        }
    },
    actions: {
        async addNoteAction({commit, state}, message) {
            await Vue.http.post('/notes', message).then(result => {
                const res = result.body
                commit("addNotesMutation", res)
            })
        }
    }
})
