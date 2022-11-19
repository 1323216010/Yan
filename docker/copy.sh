#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20220814.sql ./mysql/db
cp ../sql/ry_config_20220510.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../yan-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy yan-gateway "
cp ../yan-gateway/target/yan-gateway.jar ./ruoyi/gateway/jar

echo "begin copy yan-auth "
cp ../yan-auth/target/yan-auth.jar ./ruoyi/auth/jar

echo "begin copy yan-visual "
cp ../yan-visual/yan-monitor/target/yan-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy yan-modules-system "
cp ../yan-modules/yan-system/target/yan-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy yan-modules-file "
cp ../yan-modules/yan-file/target/yan-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy yan-modules-job "
cp ../yan-modules/yan-job/target/yan-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy yan-modules-gen "
cp ../yan-modules/yan-gen/target/yan-modules-gen.jar ./ruoyi/modules/gen/jar

