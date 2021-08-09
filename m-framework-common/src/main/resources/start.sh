#!/bin/bash

start(){
 echo "----------启动服务----------"
 echo "$1"
}
JARS="$*"
stop(){
  #echo "$0"
  echo "----------停止服务----------"
  #curl  "https://tcmdbapi.coolcollege.cn/v2/"$1"/home_page/module_setting?type=pc&access_token=fefcccb737c65e66805b1a1074596bc3" | python -m json.tool
  for i in "$JARS";
  do
      echo $i
  done
}

case "$1" in
  start)
    start "$2"
  ;;
  stop)
    stop "$2"
  ;;
  *)
    echo "sh startAll.sh [start|stop] [all|模块名]"
    exit 1
  ;;
esac
exit 0
