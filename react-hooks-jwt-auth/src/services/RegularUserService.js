import axios from "axios";
import authHeader from "./auth-header";
 
const BASE_URL = "http://localhost:9090/api/regularuser";
 
const viewDevices = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/getalldevices`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const receiveNotifications = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/notifications`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchAssets = async (keyword) => {
  try {
    const response = await axios.get(`${BASE_URL}/assets/search?keyword=${keyword}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchDevicesById = async (deviceId) => {
  try {
    const response = await axios.get(`${BASE_URL}/getdevice?deviceId=${deviceId}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchDevicesByName = async (deviceName) => {
  try {
    const response = await axios.get(`${BASE_URL}/getdevicebyname?deviceName=${deviceName}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchDevicesByType = async (deviceType) => {
  try {
    const response = await axios.get(`${BASE_URL}/getdevicesbydevicetype?deviceType=${deviceType}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchDevicesByPurchaseDate = async (purchaseDate) => {
  try {
    const response = await axios.get(`${BASE_URL}/getdevicesbypurchasedate?purchaseDate=${purchaseDate}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchDevicesByExpirationDate = async (expirationDate) => {
  try {
    const response = await axios.get(`${BASE_URL}/getdevicesbyexpirationdate?expirationDate=${expirationDate}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchDevicesByStatus = async (status) => {
  try {
    const response = await axios.get(`${BASE_URL}/getdevicesbystatus?deviceStatus=${status}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchDevicesBySupportEndDate = async (endOfSupportDate) => {
  try {
    const response = await axios.get(`${BASE_URL}/getdevicesbyendofsupportdate?endOfSupportDate=${endOfSupportDate}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const viewSoftwares = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/getallsoftwares`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchSoftwareById = async (softwareId) => {
  try {
    const response = await axios.get(`${BASE_URL}/getsoftware?softwareId=${softwareId}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchSoftwareByName = async (softwareName) => {
  try {
    const response = await axios.get(`${BASE_URL}/getsoftwarebyname?softwareName=${softwareName}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};

const searchSoftwareByLicenseKey = async (licenseKey) => {
    try {
      const response = await axios.get(`${BASE_URL}/getsoftwarebylicensekey?licenseKey=${licenseKey}`, {
        headers: { ...authHeader() }
      });
      return response.data;
    } catch (err) {
      throw err;
    }
  };
 
const searchSoftwareByPurchaseDate = async (purchaseDate) => {
  try {
    const response = await axios.get(`${BASE_URL}/getsoftwarebypurchasedate?purchaseDate=${purchaseDate}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchSoftwareByExpiryDate = async (expirationDate) => {
  try {
    const response = await axios.get(`${BASE_URL}/getsoftwarebyexpirationdate?expirationDate=${expirationDate}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchSoftwareBySupportEndDate = async (supportEndDate) => {
  try {
    const response = await axios.get(`${BASE_URL}/getsoftwarebyendofsupportdate?supportEndDate=${supportEndDate}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const searchSoftwareByStatus = async (status) => {
  try {
    const response = await axios.get(`${BASE_URL}/getsoftwarebystatus?status=${status}`, {
      headers: { ...authHeader() }
    });
    return response.data;
  } catch (err) {
    throw err;
  }
};
 
const RegularUserService = {
  viewDevices,
  viewSoftwares,
  receiveNotifications,
  searchAssets,
  searchDevicesById,
  searchDevicesByName,
  searchDevicesByType,
  searchDevicesByPurchaseDate,
  searchDevicesByExpirationDate,
  searchDevicesByStatus,
  searchDevicesBySupportEndDate,
  searchSoftwareById,
  searchSoftwareByName,
  searchSoftwareByLicenseKey,
  searchSoftwareByPurchaseDate,
  searchSoftwareByExpiryDate,
  searchSoftwareBySupportEndDate,
  searchSoftwareByStatus
};
 
export default RegularUserService;