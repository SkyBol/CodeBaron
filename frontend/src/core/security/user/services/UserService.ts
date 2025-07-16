import api from "../../../config/Api";
import { User } from "../models/User.model";

const UserService = {
  getUser: async (userID: string) => {
    return await api.get<User>(`/user/${userID}`);
  },

  updateUser: (user: User) => {
    return api.put(`/user/${user.id}`, user);
  },

  // TODO: Create User

  getAllUsers: () => {
    return api.get(`/user`);
  },

  deleteUser: (id: string) => {
    return api.delete(`/user/${id}`);
  },
};

export default UserService;