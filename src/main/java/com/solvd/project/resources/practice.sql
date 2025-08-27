-- 1. PolicyHolders with their Policies
SELECT PH.Name, P.Type, P.Status
FROM PolicyHolders PH
LEFT JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId;

-- 2. Drivers with assigned Vehicles
SELECT D.DriverId, D.License, V.Model
FROM Drivers D
LEFT JOIN DriverVehicle DV ON D.DriverId = DV.DriverID
LEFT JOIN Vehicles V ON DV.VehicleID = V.VehicleId;

-- 3. Vehicles with assigned Drivers
SELECT V.Model, D.License
FROM Vehicles V
LEFT JOIN DriverVehicle DV ON V.VehicleID = DV.VehicleId
LEFT JOIN Drivers D ON DV.DriverID = D.DriverId;

-- 4. Claims with Policies
SELECT C.ClaimId, C.Amount, P.Type
FROM Claims C
LEFT JOIN Policies P ON C.PolicyID = P.PolicyId;

-- 5. Policies with PolicyHolders and Claims
SELECT PH.Name, P.Type, C.Amount
FROM PolicyHolders PH
LEFT JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
LEFT JOIN Claims C ON P.PolicyId = C.PolicyId;

-- 6. Vehicles with Claims
SELECT V.Model, C.Amount
FROM Vehicles V
LEFT JOIN Claims C ON V.VehicleId = C.VehicleID;

-- 7. Drivers with Claims
SELECT D.License, C.Amount
FROM Drivers D
LEFT JOIN Claims C ON D.DriverId = C.DriverID;

-- 8. PolicyHolders
SELECT PH.Name
FROM PolicyHolders PH
LEFT JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId

-- 9. Drivers with Experience and Vehicle VINs
SELECT D.License, D.Experience, V.VIN
FROM Drivers D
LEFT JOIN DriverVehicle DV ON D.DriverId = DV.DriverID
LEFT JOIN Vehicles V ON DV.VehicleID = V.VehicleId;

-- 10. Claims with PolicyHolders
SELECT PH.Name, C.Amount
FROM PolicyHolders PH
LEFT JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
LEFT JOIN Claims C ON P.PolicyId = C.PolicyId;



-- 1. Policies with PolicyHolders
SELECT PH.Name, P.Type
FROM Policies P
RIGHT JOIN PolicyHolders PH ON PH.PolicyHoldersId = P.PolicyHolderId;

-- 2. Vehicles with Drivers
SELECT V.Model, D.License
FROM DriverVehicle DV
RIGHT JOIN Vehicles V ON DV.VehicleID = V.VehicleId
RIGHT JOIN Drivers D ON DV.DriverID = D.DriverId;

-- 3. Claims with Policies
SELECT C.Amount, P.Type
FROM Claims C
RIGHT JOIN Policies P ON C.PolicyId = P.PolicyId;

-- 4. Vehicles with PolicyHolders
SELECT V.Model, PH.Name
FROM Vehicles V
RIGHT JOIN Policies P ON V.PolicyId = P.PolicyId
RIGHT JOIN PolicyHolders PH ON P.PolicyHolderId = PH.PolicyHoldersId;

-- 5. Drivers with Vehicles
SELECT D.License, V.Model
FROM Drivers D
RIGHT JOIN DriverVehicle DV ON D.DriverId = DV.DriverID
RIGHT JOIN Vehicles V ON DV.VehicleID = V.VehicleId;

-- 6. Claims with Drivers
SELECT C.Amount, D.License
FROM Claims C
RIGHT JOIN Drivers D ON C.DriverId = D.DriverId;

-- 7. Claims with Vehicles
SELECT C.Amount, V.Model
FROM Claims C
RIGHT JOIN Vehicles V ON C.VehicleId = V.VehicleId;

-- 8. Policies with Claims
SELECT P.Type, C.Amount
FROM Policies P
RIGHT JOIN Claims C ON P.PolicyId = C.PolicyId;

-- 9. Vehicles with Driver Experience
SELECT V.Model, D.Experience
FROM Vehicles V
RIGHT JOIN DriverVehicle DV ON V.VehicleId = DV.VehicleID
RIGHT JOIN Drivers D ON DV.DriverID = D.DriverId;

-- 10. PolicyHolders with Claims
SELECT PH.Name, C.Amount
FROM PolicyHolders PH
RIGHT JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
RIGHT JOIN Claims C ON P.PolicyId = C.PolicyId;


-- 1. PolicyHolders with Policies
SELECT PH.Name, P.Type
FROM PolicyHolders PH
INNER JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId;

-- 2. Drivers with Vehicles
SELECT D.License, V.Model
FROM Drivers D
INNER JOIN DriverVehicle DV ON D.DriverId = DV.DriverID
INNER JOIN Vehicles V ON DV.VehicleID = V.VehicleId;

-- 3. Claims with Policies
SELECT C.Amount, P.Type
FROM Claims C
INNER JOIN Policies P ON C.PolicyId = P.PolicyId;

-- 4. Vehicles with Drivers
SELECT V.Model, D.License
FROM Vehicles V
INNER JOIN DriverVehicle DV ON V.VehicleId = DV.VehicleID
INNER JOIN Drivers D ON DV.DriverID = D.DriverId;

-- 5. PolicyHolders with Claims
SELECT PH.Name, C.Amount
FROM PolicyHolders PH
INNER JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
INNER JOIN Claims C ON P.PolicyId = C.PolicyId;

-- 6. Vehicles with Claims
SELECT V.Model, C.Amount
FROM Vehicles V
INNER JOIN Claims C ON V.VehicleId = C.VehicleId;

-- 7. Drivers with Claims
SELECT D.License, C.Amount
FROM Drivers D
INNER JOIN Claims C ON D.DriverId = C.DriverId;

-- 8. Policies with Vehicles
SELECT P.Type, V.Model
FROM Policies P
INNER JOIN Vehicles V ON P.PolicyId = V.PolicyId;

-- 9. Drivers with Experience and VINs
SELECT D.Experience, V.VIN
FROM Drivers D
INNER JOIN DriverVehicle DV ON D.DriverId = DV.DriverID
INNER JOIN Vehicles V ON DV.VehicleID = V.VehicleId;

-- 10. Claims with PolicyHolders and Vehicles
SELECT PH.Name, V.Model, C.Amount
FROM PolicyHolders PH
INNER JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
INNER JOIN Vehicles V ON P.PolicyId = V.PolicyID
INNER JOIN Claims C ON V.VehicleId = C.VehicleID;



-- 1. PolicyHolders and Policies
SELECT PH.Name, P.Type
FROM PolicyHolders PH
FULL OUTER JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId;

-- 2. Drivers and Vehicles
SELECT D.License, V.Model
FROM Drivers D
FULL OUTER JOIN DriverVehicle DV ON D.DriverId = DV.DriverID
FULL OUTER JOIN Vehicles V ON DV.VehicleID = V.VehicleId;

-- 3. Claims and Policies
SELECT C.Amount, P.Type
FROM Claims C
FULL OUTER JOIN Policies P ON C.PolicyId = P.PolicyId;

-- 4. Vehicles and Drivers
SELECT V.Model, D.License
FROM Vehicles V
FULL OUTER JOIN DriverVehicle DV ON V.VehicleId = DV.VehicleID
FULL OUTER JOIN Drivers D ON DV.DriverID = D.DriverId;

-- 5. PolicyHolders and Claims
SELECT PH.Name, C.Amount
FROM PolicyHolders PH
FULL OUTER JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
FULL OUTER JOIN Claims C ON P.PolicyId = C.PolicyId;

-- 6. Vehicles and Claims
SELECT V.Model, C.Amount
FROM Vehicles V
FULL OUTER JOIN Claims C ON V.VehicleId = C.VehicleID;

-- 7. Drivers and Claims
SELECT D.License, C.Amount
FROM Drivers D
FULL OUTER JOIN Claims C ON D.DriverId = C.DriverID;

-- 8. Policies and Vehicles
SELECT P.Type, V.Model
FROM Policies P
FULL OUTER JOIN Vehicles V ON P.PolicyId = V.PolicyID;

-- 9. Drivers and VINs
SELECT D.License, V.VIN
FROM Drivers D
FULL OUTER JOIN DriverVehicle DV ON D.DriverId = DV.DriverID
FULL OUTER JOIN Vehicles V ON DV.VehicleID = V.VehicleId;

-- 10. Claims and PolicyHolders
SELECT PH.Name, C.Amount
FROM PolicyHolders PH
FULL OUTER JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
FULL OUTER JOIN Claims C ON P.PolicyId = C.PolicyId;


-- 1. Min of claims per status
SELECT Status, Min(Amount) AS MinAmount
FROM Claims
GROUP BY Status;

-- 2. Average claim amount per status
SELECT Status, AVG(Amount) AS AvgAmount
FROM Claims
GROUP BY Status;

-- 3. Max of policies per type
SELECT Type, Max(Type) AS MaxType
FROM Policies
GROUP BY Type;


-- 1. Claim types with average amount > 5000
SELECT Status, AVG(Amount) AS AvgAmount
FROM Claims
GROUP BY Status
HAVING AVG(Amount) > 5000;

-- 2. Filters policy types that appears at least twice
SELECT Type, COUNT(*) AS PolicyCount
FROM Policies
GROUP BY Type
HAVING COUNT(*) >= 2;

--3. Claim categories where the total payout across all claims exceeds 10,000.
SELECT Status, SUM(Amount) AS TotalClaimed
FROM Claims
GROUP BY Status
HAVING SUM(Amount) > 10000;

--Big query that joins whole database
SELECT
  PH.PolicyHoldersId,
  PH.Name AS PolicyHolderName,
  PH.Contact,
  PH.DOB,

  P.PolicyId,
  P.Type AS PolicyType,
  P.Coverage,
  P.StartDate,
  P.EndDate,
  P.Status AS PolicyStatus,

  V.VehicleId,
  V.Model AS VehicleModel,
  V.Registration_Year,
  V.VIN,

  D.DriverId,
  D.License AS DriverLicense,
  D.Experience AS DriverExperience,

  C.ClaimId,
  C.Amount AS ClaimAmount,
  C.Status AS ClaimStatus,
  C.Date_Filled

FROM PolicyHolders PH
LEFT JOIN Policies P ON PH.PolicyHoldersId = P.PolicyHolderId
LEFT JOIN Vehicles V ON P.PolicyId = V.PolicyID
LEFT JOIN DriverVehicle DV ON V.VehicleId = DV.VehicleID
LEFT JOIN Drivers D ON DV.DriverID = D.DriverId
LEFT JOIN Claims C ON P.PolicyId = C.PolicyId