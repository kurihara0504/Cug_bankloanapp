@echo off
chcp 65001

rem create DataBase

rem You should change 'xxxxx' to your own root password.
rem You should change 'xxxxx' to your own workspace path.
set MysqlPassword=xxxxx
set WorkspacePath=xxxxx

echo Start to create local development environment. > result.log
echo Start to create local development environment.

set /p pushKey="Create DataBase(y/n)? : %pushKey%"
if "%pushKey%"=="y" (
	echo Insert data after create database.
    mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% < bankbook.sql >> result.log 2>&1
) else if "%pushKey%"=="n" (
	echo Skip the database creation.
) else (
	echo Because invalid value was entered, batch processing is terminated.
	pause
	exit
)

rem Insert data to your local database.

echo Start to insert data. >> result.log
echo Start to insert data.

mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% -v cug_bankloan < ..\docker\mysqldump\backup\bin\sql\digital_mypostbase.sql >> result.log 2>&1
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% -v cug_bankloan < update.sql >> result.log 2>&1

set BalanceCertificate=
set InvestmentTrust=
set ReInvestmentTrust=
set CardLoanStatement=
set QuickPowerOverdraft=
set QuickPowerOverdraftExcept=
set QuickPowerCertificateLoan=
set QuickPowerCertificateLoanExcept=
set RepaymentSchedule=
set RepaymentScheduleMidwayRateChange=
set RepaymentScheduleFixedRateEnd=
set FinancingStatement=
set CardLoanStatementVer2=

cd %WorkspacePath%/Cug_BankLoanApp/cug_bankloanapp/src

FOR /F %%i in ('where /r . BalanceCertificate.jrxml') DO @SET BalanceCertificate=%%i
FOR /F %%i in ('where /r . InvestmentTrust.jrxml') DO @SET InvestmentTrust=%%i
FOR /F %%i in ('where /r . ReInvestmentTrust.jrxml') DO @SET ReInvestmentTrust=%%i
FOR /F %%i in ('where /r . CardLoanStatement.jrxml') DO @SET CardLoanStatement=%%i
FOR /F %%i in ('where /r . QuickPowerOverdraft.jrxml') DO @SET QuickPowerOverdraft=%%i
FOR /F %%i in ('where /r . QuickPowerOverdraftExcept.jrxml') DO @SET QuickPowerOverdraftExcept=%%i
FOR /F %%i in ('where /r . QuickPowerCertificateLoan.jrxml') DO @SET QuickPowerCertificateLoan=%%i
FOR /F %%i in ('where /r . QuickPowerCertificateLoanExcept.jrxml') DO @SET QuickPowerCertificateLoanExcept=%%i
FOR /F %%i in ('where /r . RepaymentSchedule.jrxml') DO @SET RepaymentSchedule=%%i
FOR /F %%i in ('where /r . RepaymentScheduleMidwayRateChange.jrxml') DO @SET RepaymentScheduleMidwayRateChange=%%i
FOR /F %%i in ('where /r . RepaymentScheduleFixedRateEnd.jrxml') DO @SET RepaymentScheduleFixedRateEnd=%%i
FOR /F %%i in ('where /r . FinancingStatement.jrxml') DO @SET FinancingStatement=%%i
FOR /F %%i in ('where /r . CardLoanStatementVer2.jrxml') DO @SET CardLoanStatementVer2=%%i

mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%BalanceCertificate:\=/%' WHERE id = '1';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%InvestmentTrust:\=/%' WHERE id = '2';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%ReInvestmentTrust:\=/%' WHERE id = '3';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%CardLoanStatement:\=/%' WHERE id = '4';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%QuickPowerOverdraft:\=/%' WHERE id = '5';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%QuickPowerOverdraftExcept:\=/%' WHERE id = '6';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%QuickPowerCertificateLoan:\=/%' WHERE id = '7';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%QuickPowerCertificateLoanExcept:\=/%' WHERE id = '8';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%RepaymentSchedule:\=/%' WHERE id = '9';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%RepaymentScheduleMidwayRateChange:\=/%' WHERE id = '10';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%RepaymentScheduleFixedRateEnd:\=/%' WHERE id = '11';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%FinancingStatement:\=/%' WHERE id = '12';"
mysql -u root -h %MYSQL_HOST% -P %MYSQL_PORT% -p%MysqlPassword% cug_bankloan -e "UPDATE reportdefinition SET report_format_file_path = '%CardLoanStatementVer2:\=/%' WHERE id = '13';"

rem Start to place IndexController.java. (If it already exists under src, it will not be copied.)

if not exist "..\src\main\java\com\moneyforward\IndexController.java" (
	echo Start to place IndexController.java.
	copy .\IndexController.java ..\src\main\java\com\moneyforward\ >> result.log 2>&1
)

rem Start to place application-development.properties. (If it already exists under src, it will not be copied.)

if not exist "..\src\main\resources\application-development.properties" (
	echo Start to place application-development.properties.
	copy .\application-development.properties ..\src\main\resources\ >> result.log 2>&1
)

echo The creation of the local development environment has been completed. >> result.log
echo The creation of the local development environment has been completed.
pause