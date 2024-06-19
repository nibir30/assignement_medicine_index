import axios from 'axios'

class AdminMedicineService {
  constructor() {
    this.url = import.meta.env.VITE_API_URL
  }

  async saveMedicine(data) {
    const headers = { 'Content-Type': 'multipart/form-data' }

    const response = await axios.post(this.url + '/medicine/save', data, { headers })
    const resData = response.data
    return resData
  }

  async getMedicinePaginated(params) {
    const response = await axios.get(this.url + '/public/medicine/get-paginated', { params: params })
    const resData = response.data
    return resData
  }
}

export default new AdminMedicineService()
