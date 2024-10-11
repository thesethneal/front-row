import axios from "axios";
  
export default {
    getUser() {
        return axios.get('/user');
    },

    updateUserPhoneNumber(phoneNumber) {
        return axios.put('/user', null, {params: { phoneNumber: phoneNumber }});
    },

    getRole() {
        return axios.get('/user-role')
    }
  
}