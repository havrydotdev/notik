import { Store } from 'vuex'
import User from "@/models/User";
import JsonError from "@/models/JsonError";
import Note from "@/models/Note";

declare module '@vue/runtime-core' {
    // declare your own store states
    interface State {
        profile: User | JsonError
        notes: Note[],
        isProfileFetched: boolean
    }

    // provide typings for `this.$store`
    interface ComponentCustomProperties {
        $store: Store<State>
    }
}