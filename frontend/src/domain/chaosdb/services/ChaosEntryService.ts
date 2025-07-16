import { AxiosResponse } from "axios";
import AbstractService from "../../../core/abstracts/services/AbstractService";
import ChaosEntry from "../models/ChaosEntry";
import api from "../../../core/config/Api";
import { TEXT } from "../models/ChaosContent";

class ChaosEntryService extends AbstractService<ChaosEntry> {
    async uploadFile(data: File): Promise<AxiosResponse> {
        // Create a FormData object
        const formData = new FormData();

        // Check if data is a Blob
        if (data instanceof Blob) {
            // Create a File object from the Blob
            const file = new File([data], data.name, { type: data.type });
            // Append the File object to the FormData
            formData.append('file', file);
        } else {
            // If it's not a Blob, directly append it to FormData
            formData.append('file', data);
        }

        // Upload file
        return api.post(this.base + "upload", formData);
    }

    postText(text: TEXT): Promise<AxiosResponse> {
        return api.post(this.base + "text", text);
    }

    getImagePath(storageId: string) {
        return `http://localhost:8080/chaos/${storageId}/file`;
    }

    count(): Promise<AxiosResponse> {
        return api.get(this.base + "count");
    }
}

export default new ChaosEntryService("/chaos/");