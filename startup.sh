#!/bin/bash
FQCN="lsieun.KnowThyself"
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

copy_resource application.properties
copy_resource logging.properties

cp -rf "${DIR_PATH}/videos" "${CLASS_PATH}/static/"

nohup java -cp "${CLASS_PATH}" "${FQCN}" > /dev/null 2>&1 &