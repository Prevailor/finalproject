 
import React, { useState, useEffect, useRef } from 'react';
import DeviceService from '../services/DeviceService';
import { Link, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'C:\\Users\\praveenkumar.sg\\Documents\\react-hooks-jwt-auth\\src\\DeviceManagementPage.css'; // Import custom CSS for styling

const DeviceManagementPage = () => {
  const [newDevice, setNewDevice] = useState({
    deviceName: '',
    deviceType: '',
    purchaseDate: '',
    expirationDate: '',
    endOfSupportDate: '',
    status: ''
  });
  const [devices, setDevices] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selectedDevice, setSelectedDevice] = useState(null);
  const [isDetailsVisible, setIsDetailsVisible] = useState(false); // State to control selected device details visibility
  const form = useRef();
  const navigate = useNavigate();

  useEffect(() => {
    fetchDevices();
  }, []);

  const fetchDevices = () => {
    DeviceService.getAllDevices()
      .then(response => {
        setDevices(response);
      })
      .catch(error => {
        console.error('Error fetching devices:', error);
      });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewDevice({ ...newDevice, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setLoading(true);

    DeviceService.addDevice(newDevice)
      .then(() => {
        alert('Device added successfully');
        setNewDevice({
          deviceName: '',
          deviceType: '',
          purchaseDate: '',
          expirationDate: '',
          endOfSupportDate: '',
          status: ''
        });
        fetchDevices();
      })
      .catch(error => {
        console.error('Error adding device:', error);
        alert('An error occurred while adding device');
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const deleteDevice = (deviceId) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this device?');
    if (confirmDelete) {
      DeviceService.deleteDevice(deviceId)
        .then(() => {
          fetchDevices();
        })
        .catch(error => {
          console.error('Error deleting device:', error);
        });
    }
  };

  const fetchDeviceById = async (deviceId) => {
    try {
      const device = await DeviceService.getDeviceById(deviceId);
      setSelectedDevice(device);
      setIsDetailsVisible(true); // Show selected device details
    } catch (error) {
      console.error('Error fetching device by ID:', error);
    }
  };

  const handleCloseDetails = () => {
    setIsDetailsVisible(false); // Hide selected device details
  };

  const generateReport = () => {
    const header = ['ID', 'Name', 'Type', 'Purchase Date', 'Expiration Date', 'End of Support Date', 'Status'];
    const rows = devices.map(device => [device.deviceId, device.deviceName, device.deviceType, device.purchaseDate, device.expirationDate, device.endOfSupportDate, device.status]);
    const reportData = [header, ...rows];
    const csvContent = reportData.map(row => row.join(',')).join('\n');
    const blob = new Blob([csvContent], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', 'device_report.csv');
    document.body.appendChild(link);
    link.click();
  };
  return (
    <div className="container">
      <h2 className="page-title">Device Management Page</h2>
      <div className="card card-container">
        <form onSubmit={handleSubmit} ref={form}>
          <div className="form-group">
            <label htmlFor="deviceName">Device Name:</label>
            <input type="text" className="form-control" id="deviceName" name="deviceName" value={newDevice.deviceName} onChange={handleInputChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="deviceType">Device Type:</label>
            <input type="text" className="form-control" id="deviceType" name="deviceType" value={newDevice.deviceType} onChange={handleInputChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="purchaseDate">Purchase Date:</label>
            <input type="date" className="form-control" id="purchaseDate" name="purchaseDate" value={newDevice.purchaseDate} onChange={handleInputChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="expirationDate">Expiration Date:</label>
            <input type="date" className="form-control" id="expirationDate" name="expirationDate" value={newDevice.expirationDate} onChange={handleInputChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="endOfSupportDate">End of Support Date:</label>
            <input type="date" className="form-control" id="endOfSupportDate" name="endOfSupportDate" value={newDevice.endOfSupportDate} onChange={handleInputChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="status">Status:</label>
            <select className="form-control" id="status" name="status" value={newDevice.status} onChange={handleInputChange} required>
              <option value="">Select Status</option>
              <option value="Active">Active</option>
              <option value="Expired">Expired</option>
              <option value="Unsupported">Unsupported</option>
            </select>
          </div>
          <button type="submit" className="btn btn-primary" disabled={loading}>Add Device</button>
        </form>
      </div>

      <table className="table mt-4">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Purchase Date</th>
            <th>Expiration Date</th>
            <th>End of Support Date</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {devices.map(device => (
            <tr key={device.deviceId}>
              <td>{device.deviceId}</td>
              <td>{device.deviceName}</td>
              <td>{device.deviceType}</td>
              <td>{device.purchaseDate}</td>
              <td>{device.expirationDate}</td>
              <td>{device.endOfSupportDate}</td>
              <td>{device.status}</td>
              <td>
                <button className="btn btn-primary" onClick={() => fetchDeviceById(device.deviceId)}>View</button>
                <button className="btn btn-danger" onClick={() => deleteDevice(device.deviceId)}>Delete</button>
                <button className="btn btn-update">
                  <Link to={`/update-device/${device.deviceId}`}>Update</Link>
                </button>           
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {isDetailsVisible && selectedDevice && (
  <div className="selected-device-ticket">
    <button className="close-btn" onClick={handleCloseDetails}>X</button>
    <div className="selected-device-details">
      <h3 className="ticket-title">Selected Device Details:</h3>
      <div className="ticket-text">
        <p><strong>Device Name:</strong> {selectedDevice.deviceName}</p>
        <p><strong>Device Type:</strong> {selectedDevice.deviceType}</p>
        <p><strong>Purchase Date:</strong> {selectedDevice.purchaseDate}</p>
        <p><strong>Expiration Date:</strong> {selectedDevice.expirationDate}</p>
        <p><strong>End of Support Date:</strong> {selectedDevice.endOfSupportDate}</p>
        <p><strong>Status:</strong> {selectedDevice.status}</p>
      </div>
    </div>
  </div>
)}

      <button className="btn btn-report" onClick={generateReport}>Generate Report</button>
    </div>
  );
};

export default DeviceManagementPage;