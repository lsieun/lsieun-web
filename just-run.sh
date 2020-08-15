#!/bin/bash
FQCN="lsieun.KnowThyself"
DIR_PATH="$(cd "$(dirname "$0")"; pwd -P)"
CLASS_PATH="${DIR_PATH}/target/classes"
java -cp "${CLASS_PATH}" "${FQCN}"