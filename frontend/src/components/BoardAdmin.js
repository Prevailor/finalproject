// import React, { useState, useEffect } from "react";

// import UserService from "../services/user.service";
// import EventBus from "../common/EventBus";

// const BoardAdmin = () => {
//   const [content, setContent] = useState("");

//   useEffect(() => {
//     UserService.getAdminBoard().then(
//       (response) => {
//         setContent(response.data);
//       },
//       (error) => {
//         const _content =
//           (error.response &&
//             error.response.data &&
//             error.response.data.message) ||
//           error.message ||
//           error.toString();

//         setContent(_content);

//         if (error.response && error.response.status === 401) {
//           EventBus.dispatch("logout");
//         }
//       }
//     );
//   }, []);

//   return (
//     <div className="container">
//       <header className="jumbotron">
//         <h3>{content}</h3>
//       </header>
//     </div>
//   );
// };

// export default BoardAdmin;


// import React, { useState, useEffect } from "react";

// const BoardAdmin = () => {
//   const [selectedOption, setSelectedOption] = useState(""); // State to manage the selected option

//   const handleDropdownChange = (event) => {
//     const selectedPage = event.target.value;
//     setSelectedOption(selectedPage); // Update the selected option when dropdown value changes

//     // Manually redirect based on the selected option
//     if (selectedPage === "device") {
//       window.location.href = "/admin/device-management";
//     } else if (selectedPage === "software") {
//       window.location.href = "/admin/software-management";
//     }else if (selectedPage === "lifecycleEvent") {
//       window.location.href = "/admin/lifecycleEvent-management";
//     }
//   };

//   return (
//     <div className="container">
//       <header className="jumbotron">
//       <label htmlFor="crud" className="dropdown-label">CRUD operations:</label>
//         <select value={selectedOption} onChange={handleDropdownChange}>
//           <option value="">Select an option</option>
//           <option value="device">Device Management</option>
//           <option value="software">Software Management</option>
//           <option value="lifecycleEvent">LifecycleEvent Management</option>
//         </select>
//       </header>
//     </div>
//   );
// };

// export default BoardAdmin;

import React, { useState, useEffect } from "react";
import 'C:\\Users\\praveenkumar.sg\\Documents\\react-hooks-jwt-auth\\src\\BoardAdmin.css';

const BoardAdmin = () => {
  const [selectedOption, setSelectedOption] = useState("");

  const handleDropdownChange = (event) => {
    const selectedPage = event.target.value;
    setSelectedOption(selectedPage);

    switch (selectedPage) {
      case "device":
        window.location.href = "/admin/device-management";
        break;
      case "software":
        window.location.href = "/admin/software-management";
        break;
      case "lifecycleEvent":
        window.location.href = "/admin/lifecycleEvent-management";
        break;
      case "updateuserrole":
        window.location.href = "/admin/updateuserrole-management";
        break;
      default:
        // Handle default case if needed
        break;
    }
  };

  return (
    <div className="admin-container">
      <header className="admin-header jumbotron">
      <h1 className="admin-heading">Admin Dashboard</h1>
        <label htmlFor="crud" className="admin-dropdown-label">CRUD operations:</label>
        <select id="crud" className="admin-form-select form-select" value={selectedOption} onChange={handleDropdownChange}>
          <option value="">Select an option</option>
          <option value="device">Device Management</option>
          <option value="software">Software Management</option>
          <option value="lifecycleEvent">Lifecycle Event Management</option>
          <option value="updateuserrole">Update User Role</option>
        </select>
      </header>
    </div>
  );
};

export default BoardAdmin;