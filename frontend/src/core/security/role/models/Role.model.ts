import {roles} from '../../../config/Security';
import { Authority } from '../../authorities/models/Authorities.model';

/**
 * Role type
 */
export type Role = {
  id: string;
  name: roles;
  authorities: Authority[];
};

export default Role;