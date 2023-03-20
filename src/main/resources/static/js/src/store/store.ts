import { createStore } from 'vuex'
import type Note from "@/models/Note";
import type User from "@/models/User";
import type JsonError from "@/models/JsonError";
export interface State {
    profile: User | JsonError
    notes: Note[],
    isProfileFetched: boolean
}

export default createStore<State>({
    state: {
        profile: {isError: true, text: 'Profile isn`t initialized'},
        notes: [],
        isProfileFetched: false
    },
    getters: {
        allNotes: state => (state.notes || []).sort((a, b) => -(a.id - b.id))
    },
    mutations: {
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
            // const res = await mainApi.add(note)
            // const final = await res.body
            // setTimeout(commit('addNotesMutation', final), 2000)
        },
        async deleteNoteAction({commit, state}, note) {
            // mainApi.remove(note.id)
            // commit('deleteMessageMutation', note.id)
        },
        async editNoteAction({commit, state}, note) {
            // const res = await mainApi.edit(note)
            // const final = await res.body
            // commit('editMessageMutation', final)
        }
    }
})
