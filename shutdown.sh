#!/bin/bash
PROCESS_LINE=$(jps -l | grep "lsieun.KnowThyself")
if [ ${#PROCESS_LINE} -gt 0 ]; then
    echo ${PROCESS_LINE}
    PID=${PROCESS_LINE/" lsieun.KnowThyself"/}
    echo ${PID}
    kill -9 ${PID}
else
    echo "NO Process"
fi
