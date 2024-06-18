// import React, { useState, useEffect } from 'react';
// import { useParams, useNavigate } from 'react-router-dom';
// import DeviceService from '../services/DeviceService';

// function UpdateDevice() {
//   const navigate = useNavigate();
//   const { deviceId } = useParams();

//   const [deviceData, setDeviceData] = useState({
//     deviceName: '',
//     deviceType: '',
//     purchaseDate: '',
//     expirationDate: '',
//     endOfSupportDate: '',
//     status: ''
//   });

//   useEffect(() => {
//     fetchDeviceDataById(deviceId);
//   }, [deviceId]);

//   const fetchDeviceDataById = async (deviceId) => {
//     try {
//       //const token = localStorage.getItem('token'); // Retrieve the token from localStorage
//       const response = await DeviceService.getDeviceById(deviceId);
//       console.log(response);
//       if (response) {
//         setDeviceData(response);
//       } else {
//         console.error('Error: Device data is undefined.');
//       }
//     } catch (error) {
//       console.error('Error fetching device data:', error);
//     }
//   };

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setDeviceData((prevDeviceData) => ({
//       ...prevDeviceData,
//       [name]: value
//     }));
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       const res = await DeviceService.updateDevice(deviceData);
//       console.log(res);
//       navigate("/admin/device-management");
//     } catch (error) {
//       console.error('Error updating device:', error);
//       alert(error.message || 'An error occurred while updating device.');
//     }
//   };

//   return (
//     <div className="auth-container">
//       <h2>Update Device</h2>
//       <form onSubmit={handleSubmit}>
//         <div className="form-group">
//           <label>Device Name:</label>
//           <input type="text" name="deviceName" value={deviceData.deviceName || ''} onChange={handleInputChange} />
//         </div>
//         <div className="form-group">
//           <label>Device Type:</label>
//           <input type="text" name="deviceType" value={deviceData.deviceType || ''} onChange={handleInputChange} />
//         </div>
//         <div className="form-group">
//           <label>Purchase Date:</label>
//           <input type="date" name="purchaseDate" value={deviceData.purchaseDate || ''} onChange={handleInputChange} />
//         </div>
//         <div className="form-group">
//           <label>Expiration Date:</label>
//           <input type="date" name="expirationDate" value={deviceData.expirationDate || ''} onChange={handleInputChange} />
//         </div>
//         <div className="form-group">
//           <label>End of Support Date:</label>
//           <input type="date" name="endOfSupportDate" value={deviceData.endOfSupportDate || ''} onChange={handleInputChange} />
//         </div>
//         <div className="form-group">
//           <label>Status:</label>
//           <input type="text" name="status" value={deviceData.status || ''} onChange={handleInputChange} />
//         </div>
//         <button type="submit">Update</button>
//       </form>
//     </div>
//   );
// }

// export default UpdateDevice;

import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import DeviceService from '../services/DeviceService';
import 'C:\\Users\\praveenkumar.sg\\Documents\\react-hooks-jwt-auth\\src\\UpdateDevice.css'; // Import your CSS file for component-specific styles

function UpdateDevice() {
  const navigate = useNavigate();
  const { deviceId } = useParams();

  const [deviceData, setDeviceData] = useState({
    deviceName: '',
    deviceType: '',
    purchaseDate: '',
    expirationDate: '',
    endOfSupportDate: '',
    status: ''
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchDeviceDataById(deviceId);
  }, [deviceId]);

  const fetchDeviceDataById = async (deviceId) => {
    try {
      const response = await DeviceService.getDeviceById(deviceId);
      if (response) {
        setDeviceData(response);
      } else {
        setError('Device data not found.');
      }
    } catch (error) {
      setError('Error fetching device data.');
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setDeviceData((prevDeviceData) => ({
      ...prevDeviceData,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await DeviceService.updateDevice(deviceData);
      alert('Device updated successfully');
      navigate("/admin/device-management");
    } catch (error) {
      setError(error.message || 'An error occurred while updating device.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="update-device-container">
      <h2 className="page-title">Update Device</h2>
      {error && <div className="error-message">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Device Name:</label>
          <input type="text" name="deviceName" value={deviceData.deviceName || ''} onChange={handleInputChange} />
        </div>
        <div className="form-group">
          <label>Device Type:</label>
          <input type="text" name="deviceType" value={deviceData.deviceType || ''} onChange={handleInputChange} />
        </div>
        <div className="form-group">
          <label>Purchase Date:</label>
          <input type="date" name="purchaseDate" value={deviceData.purchaseDate || ''} onChange={handleInputChange} />
        </div>
        <div className="form-group">
          <label>Expiration Date:</label>
          <input type="date" name="expirationDate" value={deviceData.expirationDate || ''} onChange={handleInputChange} />
        </div>
        <div className="form-group">
          <label>End of Support Date:</label>
          <input type="date" name="endOfSupportDate" value={deviceData.endOfSupportDate || ''} onChange={handleInputChange} />
        </div>
        <div className="form-group">
          <label>Status:</label>
          <input type="text" name="status" value={deviceData.status || ''} onChange={handleInputChange} />
        </div>
        <button type="submit" className="btn-update-update" disabled={loading}>{loading ? 'Updating...' : 'Update'}</button>
      </form>
    </div>
  );
}

export default UpdateDevice;

