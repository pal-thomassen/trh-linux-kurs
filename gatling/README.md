# Gatling

To run gatling you first need to run the script `fetchGatling.sh`. This downloads and unzips Gatling 2.2 bundle.

To run the DatabaseSimulation included here, first you must edit the script. 

Change the baseURL to point to your copy of the java-application. 

Then if needed tweak the different settings for `setUp`. The default example is to `rampUsers(10) over(1 minute)`. Another example is `atOnceUsers(100)`. See the gatling documentation for more examples, http://gatling.io/docs/2.1.7/general/simulation_setup.html

To run gatling run the following command `./gatling-charts-highcharts-bundle-2.2.0-SNAPSHOT/bin/gatling.sh -sf .` This runs Gatling and tells gatling to search for simulations in the current directory (-sf .).
