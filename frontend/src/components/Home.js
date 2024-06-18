// import React, { useState, useEffect } from "react";

// import UserService from "../services/user.service";

// const Home = () => {
//   const [content, setContent] = useState("");

//   useEffect(() => {
//     UserService.getPublicContent().then(
//       (response) => {
//         setContent(response.data);
//       },
//       (error) => {
//         const _content =
//           (error.response && error.response.data) ||
//           error.message ||
//           error.toString();

//         setContent(_content);
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

// export default Home;


import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'C:\\Users\\praveenkumar.sg\\Documents\\react-hooks-jwt-auth\\src\\Home.css'; // Import custom CSS for additional styling
 

//   const [expandedFeatures, setExpandedFeatures] = useState({});
//   const [showMore, setShowMore] = useState(false);
 
//   const toggleFeature = (feature) => {
//     setExpandedFeatures(prevState => ({
//       ...prevState,
//       [feature]: !prevState[feature]
//     }));
//   };
 
//   const features = [
//     {
//       title: "Lifecycle Management",
//       description: "Implement full lifecycle management for both hardware devices and software licenses, from procurement to expiration."
//     },
//     {
//       title: "Compliance and Reporting",
//       description: "Ensure compliance with licensing terms and provide detailed reporting features for audit and management purposes."
//     },
//     {
//       title: "Automated Tracking",
//       description: "Automate tracking of each phase: Order, Deployment, Audit, and Expiry."
//     },
//     {
//       title: "Custom Reports",
//       description: "Generate custom reports on device and software status, compliance levels, and lifecycle stages."
//     },
//     {
//       title: "User Management",
//       description: "Admin and user level access controls to ensure secure and efficient management."
//     }
//   ];
 
//   return (
//     <div className="container mt-5">
//       {/* Hero Section */}
//       <header className="jumbotrons text-center text-white py-5" style={{ backgroundColor: '#28a745' }}>
//         <h1 className="display-4">License Lifecycle Tracker</h1>
//         <p className="lead">Elevate your license management to unparalleled efficiency and compliance.</p>
//         <p className="mantra">Seamlessly Manage. Effortlessly Comply. Insightfully Optimize.</p>
//       </header>
 
//       {/* Features Section */}
//       <section className="mb-5">
//         <div className="row text-center">
//           {features.slice(0, 2).map((feature, index) => (
//             <div key={index} className="col-md-6 col-sm-12 mb-4">
//               <div className={`card border-success h-100 ${expandedFeatures[feature.title] ? 'expanded' : ''}`}>
//                 <button
//                   className="btn btn-link card-body text-success text-decoration-none"
//                   onClick={() => toggleFeature(feature.title)}
//                 >
//                   <div className="card-content">
//                     {expandedFeatures[feature.title] ? (
//                       <p className="card-text">{feature.description}</p>
//                     ) : (
//                       <h5 className="card-title">{feature.title}</h5>
//                     )}
//                   </div>
//                 </button>
//               </div>
//             </div>
//           ))}
//         </div>
//       </section>
 
//       {/* More Features Button */}
//       <div className="text-center mb-5">
//         {!showMore && (
//           <button className="btn btn-success" onClick={() => setShowMore(!showMore)}>
//             Explore Additional Features
//           </button>
//         )}
//       </div>
 
//       {/* Additional Features Section */}
//       {showMore && (
//         <section className="mb-5">
//           <div className="row text-center">
//             {features.slice(2).map((feature, index) => (
//               <div key={index} className="col-md-4 col-sm-12 mb-4">
//                 <div className={`card border-success h-100 ${expandedFeatures[feature.title] ? 'expanded' : ''}`}>
//                   <button
//                     className="btn btn-link card-body text-success text-decoration-none"
//                     onClick={() => toggleFeature(feature.title)}
//                   >
//                     <div className="card-content">
//                       {expandedFeatures[feature.title] ? (
//                         <p className="card-text">{feature.description}</p>
//                       ) : (
//                         <h5 className="card-title">{feature.title}</h5>
//                       )}
//                     </div>
//                   </button>
//                 </div>
//               </div>
//             ))}
//           </div>
//           <br></br>
//           <div className="text-center mt-4">
//             <button className="btn btn-success" onClick={() => setShowMore(!showMore)}>
//               Hide Additional Features
//             </button>
//           </div>
//         </section>
//       )}
//     </div>
//   );
// };

// Placeholder images
import lifecycleImage from '../images/lifecycle.jpg';
import complianceImage from '../images/compliance.jpg';
import userInterfaceImage from '../images/user-interface.jpg';
// const Home = () => {
//   return (
//       <div className="home-container">
//           <div className="hero">
//               <div className="hero-content">
//                   <h1>Welcome to License Lifecycle Tracker</h1>
//                   <p>Efficiently manage and monitor the lifecycle of licenses for network devices and software.</p>
//                   <button className="btn btn-primary">Get Started</button>
//               </div>
//           </div>
//           <div className="features-section">
//               <div className="feature">
//                   <img src={lifecycleImage} alt="Lifecycle Management" className="feature-image" />
//                   <div className="feature-text">
//                       <h2>Lifecycle Management</h2>
//                       <p>Track devices and software throughout their lifecycle from procurement to disposal.</p>
//                   </div>
//               </div>
//               <div className="feature">
//                   <img src={complianceImage} alt="Compliance and Reporting" className="feature-image" />
//                   <div className="feature-text">
//                       <h2>Compliance and Reporting</h2>
//                       <p>Ensure compliance with licensing terms and generate detailed reports.</p>
//                   </div>
//               </div>
//               <div className="feature">
//                   <img src={userInterfaceImage} alt="User-Friendly Interface" className="feature-image" />
//                   <div className="feature-text">
//                       <h2>User-Friendly Interface</h2>
//                       <p>Intuitive and accessible UI designed for ease of navigation and management.</p>
//                   </div>
//               </div>
//           </div>
//           <div className="testimonial-section">
//               <div className="testimonial">
//                   <p>"This platform has transformed how we manage our licenses. Highly recommended!"</p>
//                   <span>- John Doe, IT Manager</span>
//               </div>
//               <div className="testimonial">
//                   <p>"License Lifecycle Tracker has streamlined our compliance processes. It's a game-changer!"</p>
//                   <span>- Jane Smith, Compliance Officer</span>
//               </div>
//           </div>
//           <br></br>
//           <div className="cta-section">
//               <h2>Ready to Get Started?</h2>
//               <p>Sign up today to start managing your licenses with ease.</p>
//               <button className="btn btn-primary">Sign Up Now</button>
//           </div>
//       </div>
//   );
// };

// export default Home;
import { useNavigate } from 'react-router-dom';


const Home = () => {
  const navigate = useNavigate();

  const [expandedFeatures, setExpandedFeatures] = useState({
    'Lifecycle Management': false,
    'Compliance and Reporting': false,
    'User-Friendly Interface': false
  });

  const toggleFeature = (feature) => {
    setExpandedFeatures(prevState => ({
      ...prevState,
      [feature]: !prevState[feature]
    }));
  };

  const features = [
    {
      title: "Lifecycle Management",
      image: lifecycleImage,
      description: "Track devices and software throughout their lifecycle."
    },
    {
      title: "Compliance and Reporting",
      image: complianceImage,
      description: "Ensure compliance and generate reports."
    },
    {
      title: "User-Friendly Interface",
      image: userInterfaceImage,
      description: "Intuitive user interface for ease of use."
    }
  ];

  return (
    <div className="home-page">
      {/* Hero Section */}
      <div className="hero">
        <div className="hero-content">
          <h1>Welcome to License Lifecycle Tracker</h1>
          <p>Efficiently manage and monitor the lifecycle of licenses for network devices and software.</p>
          <button className="btn" onClick={() => navigate('/login')}>Get Started</button>
        </div>
      </div>

      {/* Features Section */}
      <div className="features-container">
        <div className="features-section">
          {/* Feature 1: Lifecycle Management */}
          <div className={`feature ${expandedFeatures['Lifecycle Management'] ? 'expanded' : ''}`} onClick={() => toggleFeature('Lifecycle Management')}>
            <img src={features[0].image} alt={features[0].title} className="feature-image" />
            <div className="feature-text">
              <h2 className="feature-title">Lifecycle Management</h2>
              <p className="feature-description">{features[0].description}</p>
            </div>
            {expandedFeatures['Lifecycle Management'] && (
              <div className="expanded-description">
                <p>Implement full lifecycle management for both hardware devices and software licenses.</p>
              </div>
            )}
          </div>

          {/* Feature 2: Compliance and Reporting */}
          <div className={`feature ${expandedFeatures['Compliance and Reporting'] ? 'expanded' : ''}`} onClick={() => toggleFeature('Compliance and Reporting')}>
            <img src={features[1].image} alt={features[1].title} className="feature-image" />
            <div className="feature-text">
              <h2 className="feature-title">Compliance and Reporting</h2>
              <p className="feature-description">{features[1].description}</p>
            </div>
            {expandedFeatures['Compliance and Reporting'] && (
              <div className="expanded-description">
                <p>Ensure compliance with licensing terms and provide detailed reporting features.</p>
              </div>
            )}
          </div>

          {/* Feature 3: User-Friendly Interface */}
          <div className={`feature ${expandedFeatures['User-Friendly Interface'] ? 'expanded' : ''}`} onClick={() => toggleFeature('User-Friendly Interface')}>
            <img src={features[2].image} alt={features[2].title} className="feature-image" />
            <div className="feature-text">
              <h2 className="feature-title">User-Friendly Interface</h2>
              <p className="feature-description">{features[2].description}</p>
            </div>
            {expandedFeatures['User-Friendly Interface'] && (
              <div className="expanded-description">
                <p>Admin and user level access controls to ensure secure and efficient management.</p>
              </div>
            )}
          </div>
        </div>
      </div>

      {/* CTA Section */}
      <div className="cta-section">
        <h2>Ready to Get Started?</h2>
        <p>Sign up today to start managing your licenses with ease.</p>
        <button className="btn" onClick={() => navigate('/register')}>Sign Up Now</button>
      </div>

      {/* Footer Section */}
      <footer className="footer">
        <div className="footer-content">
          <p>&copy; 2024 License Lifecycle Tracker. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default Home;