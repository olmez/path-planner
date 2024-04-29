# build
mvn clean package

# run
java -jar target/pathplanning.PathPlanningService-1.0-SNAPSHOT.jar

# run in background
nohup java -jar target/pathplanning.PathPlanningService-1.0-SNAPSHOT.jar &

# run and log
nohup java -jar target/pathplanning.PathPlanningService-1.0-SNAPSHOT.jar > pathplanning.log 2>&1 &
