DROP TABLE IF EXISTS DEVICE_PARAMETERS;
DROP TABLE IF EXISTS current_meter_consumption;

CREATE TABLE device_parameters(
  paramId INT  PRIMARY KEY,
  parameter_name VARCHAR(250) NOT NULL,
  createdBy VARCHAR(250) NOT NULL,
  updatedBy VARCHAR(250) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_date TIMESTAMP NOT NULL
);

CREATE TABLE current_meter_consumption (
  recordId INT  PRIMARY KEY,
  meterNo VARCHAR(20) NOT NULL,
  currentReading BIGINT(20) NOT NULL,
  totalEnergyConsumed BIGINT(20) NOT NULL,
  createdBy VARCHAR(250) NOT NULL,
  updatedBy VARCHAR(250) NOT NULL,
  createdDate TIMESTAMP NOT NULL,
  updatedDate TIMESTAMP NOT NULL
);


