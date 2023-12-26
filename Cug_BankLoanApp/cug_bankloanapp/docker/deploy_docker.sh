#!/bin/bash
set -e
path=/home/ec2-user/Cug_BankLoanApp/cug_bankloanapp/docker
LOG=$path/result.log
if [ $1 ]; then
        branch=$1
else
        branch=dev
fi
cd $path
{
	if [ -e $path/jetty/sp2.war ]; then
		rm -f $path/jetty/sp2.war
 	fi
 	if [ -d $path/jetty/ROOT ]; then
		rm -rf $path/jetty/ROOT/*
 	else
		mkdir $path/jetty/ROOT
 	fi
 	if [ -d $path/jetty/report ]; then
		rm -rf $path/jetty/report/*
 	else
		mkdir $path/jetty/report/
 	fi
 	git pull origin $branch
	cp -r /mnt/cug_deploy/sp2.war $path/jetty
	cp -r /home/ec2-user/Cug_BankLoanApp/cug_bankloanapp/src/main/resources/static/* $path/jetty/ROOT
 	cp -r /home/ec2-user/Cug_BankLoanApp/cug_bankloanapp/src/main/resources/report/* $path/jetty/report
	sudo cp -r /home/ec2-user/Cug_BankLoanApp/cug_bankloanapp/src/main/resources/report/* /var/share/jetty/report
	sudo chown -R mysql_docker:mysql_docker /var/share/jetty/report
	sudo cp -r /home/ec2-user/Cug_BankLoanApp/cug_bankloanapp/src/main/resources/static/banner/* /var/share/jetty/banner
	sudo chown -R mysql_docker:mysql_docker /var/share/jetty/banner
 	bash $path/obfuscateJs.sh
	if docker ps | grep mysql-dump-job; then
		docker exec mysql-dump-job bash /opt/mysql/backup/bin/insertData_bankbook.sh
		docker exec mysql-dump-job bash /opt/mysql/backup/bin/selectData_bankbook.sh internal_setting.sql
 		sleep 3
		echo "docker stop .."
		docker-compose down
	fi
	echo "docker run with docker-compose"
	set +e
	docker images | awk '/<none/{print $3}' | xargs docker rmi
	set -e
	docker-compose -f $path/docker-compose.yml up -d --force-recreate --build
} > ${LOG} 2>&1
