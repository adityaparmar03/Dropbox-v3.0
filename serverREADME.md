# Kayak

#### Steps for installing the project
Checkout this repo, install dependencies, then start the gulp process with the following:

```sh
$ git clone https://github.com/MeenakshiParyani/Kayak.git
$ cd react-front-end
$ npm install
$ npm start
$ cd kafka-front-end
$ npm install
$ npm start
$ cd kafka-back-end
$ npm install
$ npm start
$ Access the Kayak Portal on http://localhost:3000
```

# Redis Server config

### MAC OS Steps

```sh
# Install the Redis Server
$ brew install redis
# Start the Redis Server
$ redis-server       
```

### Linux OS Steps

```sh
# Install the Redis Server
$ wget http://download.redis.io/redis-stable.tar.gz
$ tar xvzf redis-stable.tar.gz
$ cd redis-stable
# Start the Redis Server
$ redis-server
$ make
```

### Redis CLI Verification
```sh
# Start the Redis CLI
$ redis-cli
# To see the redis cached key and value pair
$ KEYS *
```

### To enable caching on any query, the query should be in following format
```sh
query = User.find({});
query.where("id", req.session.userId);
query.lean() # Lean is what instructs Redis to cache the query results
query.exec(function(err, userInfo){
    // Process the Query Results
});
```

### List of Kafka Topics to be created
login
getapi
MmtCars
CleartripCars
AlamoCars
MmtHotels
CleartripHotels
TripAdvisorHotels
MmtFlights
CleartripFlights
ExpediaFlights
BookFlight
BookHotel
BookCar

### Use below command to create all the required Topics
> kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic <<topic_name>>

### Verify Topic Creation
> kafka-topics --list --zookeeper localhost:2181
