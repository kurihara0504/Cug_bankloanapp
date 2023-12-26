CREATE DATABASE cug_bankloan DEFAULT CHARACTER SET utf8mb4;

CREATE USER IF NOT EXISTS 'moneyforward'@'%' IDENTIFIED BY 'mf123da';

GRANT ALL ON cug_bankloan.* TO moneyforward@'%';