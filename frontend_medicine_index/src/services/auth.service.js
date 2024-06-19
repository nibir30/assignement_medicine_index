import axios from 'axios'

class AuthService {
  constructor() {
    this.url = import.meta.env.VITE_API_URL
  }

  async login(data) {
    const response = await axios.post(this.url + '/public/auth/login', data)
    const resData = response.data
    return resData
  }
}

export default new AuthService()
