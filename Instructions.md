# Netflix GIF Service

## Instructions
This document is providing instructions on how to build, run tests and start the Netflix Gif Service on port 8080. Please take 
a look at the respective sections for instructions on how to execute each step. With this being a Gradle project, 
it would require gradle be installed on your machine if not already installed. Installation can be found here at https://gradle.org/install/

### How to Build
To build project, execute the following command in terminal:
`gradle clean build`

### How to Run tests
To run tests, execute the following command in terminal:
`gradle clean test`

_Note: Tests will also run with every `gradle clean build`._

### How to Start service
To start service on port 8080, execute the following command in terminal:
`gradle bootRun`

## Improvements for Production
Before this service can be production ready, there are a few improvements that need to be implemented. By addressing 
Security and Latency, and providing a bit more functionality, this service can be ready for production.

### Security
* Implementing Oauth/SSO layer for users to get authorized before hitting the service maintaining a security layer around the service. 
* With SSO we can also implement 15 min session timeouts, requiring the user to log in after 15 min of inactivity.
* In case of bad actors, we can rate limit the number of `searchTerms` possible to a MAX of 10, to prevent bad actors from overloading the system. 
* For additional security for our `API_KEY`, we can encrypt our `API_KEY` at rest and decrypt when we need to make api calls. 

### Latency
* To improve latency of this service, we can add caching, to cache the users top 20 gif searches, with a set expiration time of 30 days.

### Functionality
* As of right now, the height for the gif is static(hard coded), but we can add `height` as one of the requestParams, for users to enter their desire height of gifs they want to retrieve. 
* Based on user searches, we can provide suggestions using the `api.giphy.com/v1/tags/related/<term>` endpoint, offered in the Giphy API.
* The current limit for number of gifs returned per `searchTerm` is 10, we can give users the ability to increase the size to 15, 20 or 25, with a MAX of 25 gifs returned.
* The Giphy API has more capabilities such as, searching in different languages and ratings, we can modify our API calls to enable users to select their desire language and rating.
