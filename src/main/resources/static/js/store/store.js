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
        allNotes: state => (state.notes || []).sort((a, b) => -(a.id - b.id))
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
        },
        deleteMessageMutation(state, id) {
            const deletionIndex = state.notes.findIndex(item => item.id === id)

            if (deletionIndex > -1) {
                state.notes = [
                    ...state.notes.slice(0, deletionIndex),
                    ...state.notes.slice(deletionIndex + 1)
                ]
            }
        },
        editMessageMutation(state, note) {
            const updateIndex = state.notes.findIndex(item => item.id === note.id)

            state.notes = [
                ...state.notes.slice(0, updateIndex),
                note,
                ...state.notes.slice(updateIndex + 1)
            ]
        }
    },
    actions: {
        async addNoteAction({commit, state}, note) {
             const res = await mainApi.add(note)
             const final = await res.body
             setTimeout(commit('addNotesMutation', final), 2000)
        },
        async deleteNoteAction({commit, state}, note) {
            mainApi.remove(note.id)
            commit('deleteMessageMutation', note.id)
        },
        async editNoteAction({commit, state}, note) {
            const res = await mainApi.edit(note)
            const final = await res.body
            commit('editMessageMutation', final)
        }
    }
})
