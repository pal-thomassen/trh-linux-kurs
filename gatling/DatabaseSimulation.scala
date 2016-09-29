import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class DatabaseBeanstalkSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://default-environment.f6w8yrqd9r.us-east-1.elasticbeanstalk.com/database")

  val headers = Map("Content-Type" -> "application/x-www-form-urlencoded") // Note the headers specific to a given request

  val scn = scenario("Scenario Name") 
    .exec(http("request")
      .post("/insert")
      .headers(headers)
      .body(StringBody("""{"status" : "insert gatling 2"}""")).asJSON)

  setUp(scn.inject(rampUsers(10) over(1 minute)).protocols(httpConf))
}
