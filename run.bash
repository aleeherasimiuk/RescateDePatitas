#!/bin/bash
commands=('install' 'test')

case $1 in
  install)
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven dependency:resolve
    ;;

  test)
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven test
    ;;
  *)
    echo 'Wrong parameter, valid parameters are: install, test'
    exit 1
    ;;
esac

exit 0
