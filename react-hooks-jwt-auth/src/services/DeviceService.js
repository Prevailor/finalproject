

// DeviceService.js
import axios from "axios";
import authHeader from "./auth-header";
 
const BASE_URL = "http://localhost:9090/api";
 
const addDevice = async (device) => {
  try {
    const response = await axios.post(`${BASE_URL}/admin/addDevices`, device, {
      headers: {
        'Content-Type': 'application/json',
        ...authHeader(),
        'Access-Control-Allow-Origin': '*'
      }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const updateDevice = async (device) => {
  try {
    const response = await axios.put(`${BASE_URL}/admin/updateDevices`, device, {
      headers: {
         ...authHeader() ,
        'Access-Control-Allow-Origin': '*'
        }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const deleteDevice = async (deviceId) => {
  try {
    const response = await axios.post(`${BASE_URL}/admin/deletedevice/${deviceId}`, null, {
      params: { deviceId },
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const getAllDevices = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/admin/getalldevices`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};


 
const getDeviceById = async (deviceId) => {
  try {
    const response = await axios.get(`${BASE_URL}/admin/getdevice?id=${deviceId}`, {
      params: { deviceId },
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const DeviceService = {
  addDevice,
  updateDevice,
  deleteDevice,
  getAllDevices,
  getDeviceById,
};
 
export default DeviceService;
 