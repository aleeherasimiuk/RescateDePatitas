#!/bin/bash

case $1 in
  install)
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven dependency:resolve
    ;;
  test)
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven test
    ;;
  package)
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven package
    ;;
  run)
    docker exec -it rescate_de_patitas_app java -jar /home/app/target/RescatePatitas-jar-with-dependencies.jar
    ;;
  clean)
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven clean
    ;;
  all)
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven clean
    docker exec -it rescate_de_patitas_app mvn -Duser.home=/var/maven -Dmaven.test.skip package
    docker exec -it rescate_de_patitas_app java -jar /home/app/target/RescatePatitas-jar-with-dependencies.jar
    ;;
  *)
    echo 'Wrong parameter, valid parameters are: install, test, package, run, all'
    exit 1
    ;;
esac

exit 0
