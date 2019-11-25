#!/usr/bin/env bash
echo "Send jars to server"
echo 'ProjectWeb1!"' | scp -r ./staging project@backend1:/home/project/backend-app-prod
