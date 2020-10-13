DROP TABLE IF EXISTS DEVICE_PARAMETERS;
DROP TABLE IF EXISTS current_device_consumption;
DROP TABLE IF EXISTS device_status;
DROP TABLE IF EXISTS device_config;

CREATE TABLE device_parameters(
  paramId BIGINT PRIMARY KEY NOT NULL,
  parameter_name VARCHAR(250) NOT NULL,
  createdBy VARCHAR(250) NOT NULL,
  updatedBy VARCHAR(250) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  updated_date TIMESTAMP NOT NULL
);

CREATE TABLE current_device_consumption (
  recordId BIGINT PRIMARY KEY NOT NULL,
  deviceNo VARCHAR(20) NOT NULL,
  currentReading BIGINT(20) NOT NULL,
  totalEnergyConsumed BIGINT(20) NOT NULL,
  createdBy VARCHAR(250) NOT NULL,
  updatedBy VARCHAR(250) NOT NULL,
  createdDate TIMESTAMP NOT NULL,
  updatedDate TIMESTAMP NOT NULL
);

CREATE TABLE device_status (
  recordId BIGINT PRIMARY KEY NOT NULL,
  deviceNo VARCHAR(20) NOT NULL,
  active  BOOLEAN NOT NULL,
  createdBy VARCHAR(250) NOT NULL,
  updatedBy VARCHAR(250) NOT NULL,
  createdDate TIMESTAMP NOT NULL,
  updatedDate TIMESTAMP NOT NULL
);

CREATE TABLE device_config (
  recordId BIGINT PRIMARY KEY NOT NULL,
  deviceNo VARCHAR(50) NOT NULL,
  portType VARCHAR(50) NOT NULL,
  port  VARCHAR(20) NOT NULL,
  ipAddress  VARCHAR(20) NOT NULL,
  createdBy VARCHAR(250) DEFAULT NULL,
  updatedBy VARCHAR(250) DEFAULT NULL,
  createdDate TIMESTAMP NOT NULL,
  updatedDate TIMESTAMP NOT NULL
);


