import axios from "axios";
import authHeader from "./auth-header";
 
const BASE_URL = "http://localhost:9090/api/management";
 

 
const generateLifecycleReports = async (relatedIds) => {
  try {
    // Check if assetIds is an array to determine if it's a single ID or multiple IDs
    const url = Array.isArray(relatedIds)
      ? `${BASE_URL}/reports?relatedIds=${relatedIds.join(',')}`
      : `${BASE_URL}/reports/${relatedIds}`;
 
    const response = await axios.get(url, {
      headers: {
        ...authHeader(),
        'Access-Control-Allow-Origin': '*'
      }
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
 
const viewAllLifecycleEvents = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/getallLifecycleEvents`, {
      headers: {
        ...authHeader(),
        'Access-Control-Allow-Origin': '*'
      }
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
 
const ManagerService = {
  
  generateLifecycleReports,
  viewAllLifecycleEvents,
};
 
export default ManagerService;
 