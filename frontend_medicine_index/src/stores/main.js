import { defineStore } from 'pinia'
import { ref } from 'vue'
import axios from 'axios'

export const useMainStore = defineStore('main', () => {
  /*const userName = ref('')
  const userType = ref('')
  const userEmail = ref('')
  const userAvatar = ref({})
  const userLoginId = ref('')
  const userStatus = ref('')
  let userData = JSON.parse(localStorage.getItem('user'))
if(userData!=null){
  userName.value = ref(userData.data.fullName)
  userType.value = ref(userData.data.user_type)
  userEmail.value = ref(userData.data.email)
  userAvatar.value = ref(userData.data.avatar)
  userLoginId.value = ref(userData.data.login_id)
  userStatus.value = ref(userData.data.active_yn)
}*/

  let userData = JSON.parse(localStorage.getItem('user'))
  const userName = ref(userData.data.fullName)
  const userType = ref(userData.data.user_type)
  const userEmail = ref(userData.data.email)
  const userAvatar = ref(userData.data.avatar)
  const userLoginId = ref(userData.data.login_id)
  const userStatus = ref(userData.data.active_yn)

  const isFieldFocusRegistered = ref(false)

  const clients = ref([])
  const history = ref([])

  function setUser(payload) {
    if (payload.name) {
      userName.value = payload.name
    }
    if (payload.type) {
      userType.value = payload.type
    }
  }

  function fetchSampleClients() {
    axios
      .get(`data-sources/clients.json?v=3`)
      .then((result) => {
        clients.value = result?.data?.data
      })
      .catch((error) => {
        alert(error.message)
      })
  }

  function fetchSampleHistory() {
    axios
      .get(`data-sources/history.json`)
      .then((result) => {
        history.value = result?.data?.data
      })
      .catch((error) => {
        alert(error.message)
      })
  }

  return {
    userType,
    userName,
    userEmail,
    userAvatar,
    userLoginId,
    userStatus,
    isFieldFocusRegistered,
    clients,
    history,
    setUser,
    fetchSampleClients,
    fetchSampleHistory
  }
})
