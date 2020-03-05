#!/bin/bash
DIR_PATH="$(cd "$(dirname "$0")"; pwd -P)"
CLASS_PATH="${DIR_PATH}/target/classes"

echo "dir = ${DIR_PATH}"
echo "class path = ${CLASS_PATH}"

function copy_resource {
  local from_path="${DIR_PATH}/${1}"
  local to_path="${CLASS_PATH}/${1}"

  if [ -e "${from_path}" -a -f "${from_path}" ]
  then
    cp -f "${from_path}" "${to_path}"
  fi
}

cd "${DIR_PATH}"
mvn clean compile

copy_resource config.properties
copy_resource logging.properties

cp -rf "${DIR_PATH}/videos" "${CLASS_PATH}/static/"
cd "${CLASS_PATH}"

nohup java lsieun.KnowThyself > /dev/null 2>&1 &