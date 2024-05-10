# **BookMyShow**
BookMyShow is a Java-based web application that provides an online platform for users to browse, book, and manage movie tickets for various theaters and shows.

**Overview**

BookMyShow simplifies the process of booking movie tickets by providing users with an intuitive interface to search for movies, view showtimes, select seats, and purchase tickets online. It also offers features for theater owners to manage their movie listings, show schedules, and seating arrangements.

**Project Structure**

The project follows a modular structure, with each module responsible for a specific aspect of the application:


**Controllers**

MovieController.java: Handles requests related to movies.

ShowController.java: Manages requests for shows and show seats.

TheaterController.java: Controls theater-related requests.

TicketController.java: Manages ticket-related requests.

UserController.java: Handles user-related requests.


**DTOs**

Data Transfer Objects (DTOs) are used to transfer data between the client and the server:

**RequestDTOs**

MovieRequestDTO.java: DTO for movie requests.

ShowRequestDTO.java: DTO for show requests.

ShowSeatsRequestDTO.java: DTO for show seat requests.

TheaterRequestDTO.java: DTO for theater requests.

TheaterSeatsRequestDTO.java: DTO for theater seat requests.

TicketRequestDTO.java: DTO for ticket requests.

UserRequestDTO.java: DTO for user requests.

**ResponseDTOs**

TicketResponseDTO.java: DTO for ticket responses.

UserResponseDTO.java: DTO for user responses.


**Enums**

Genre.java: Enumerates movie genres.

Language.java: Enumerates languages.

SeatType.java: Enumerates seat types.


**Exceptions**

Custom exceptions are defined for error handling:

EmailIdEmptyException.java: Thrown when the email ID is empty.

MovieNotFoundException.java: Thrown when a movie is not found.

ShowNotFoundException.java: Thrown when a show is not found.

TheaterNotFoundException.java: Thrown when a theater is not found.

TicketIdInvalidException.java: Thrown when the ticket ID is invalid.

UserAlreadyPresentException.java: Thrown when a user is already present.

UserNotFoundException.java: Thrown when a user is not found.


**Models**

Movie.java: Represents a movie.

Show.java: Represents a show.

ShowSeat.java: Represents a seat in a show.

Theater.java: Represents a theater.

TheaterSeat.java: Represents a seat in a theater.

Ticket.java: Represents a ticket.

User.java: Represents a user.


**Repository**

MovieRepository.java: Repository for managing movies.

ShowRepository.java: Repository for managing shows.

TheaterRepository.java: Repository for managing theaters.

TicketRepository.java: Repository for managing tickets.

UserRepository.java: Repository for managing users.


**Services**

MovieService.java: Service for movie-related operations.

ShowService.java: Service for show-related operations.

TheaterService.java: Service for theater-related operations.

TicketService.java: Service for ticket-related operations.

UserService.java: Service for user-related operations.


**Transformers**

MovieTransformer.java: Converts movie entities to DTOs and vice versa.

ShowTransformer.java: Converts show entities to DTOs and vice versa.

TheaterTransformer.java: Converts theater entities to DTOs and vice versa.

UserTransformer.java: Converts user entities to DTOs and vice versa.

**Endpoints and APIs**

**Movie Controller**

**Add Movie:**

Endpoint: POST /movie/add

Description: Add a new movie to the system.

Request Body: MovieRequestDTO

Response: Success message or error message

**Show Controller**

**Add Show:**

Endpoint: POST /show/add

Description: Add a new show to the system.

Request Body: ShowRequestDTO

Response: Success message or error message

**Associate Seats with Show:**

Endpoint: POST /show/associateSeats

Description: Associate seats with a specific show.

Request Body: ShowSeatsRequestDTO

Response: Success message or error message

**Get Most Recommended Movie Show:**

Endpoint: GET /show/getMostRecommendedMovieShow

Description: Retrieve the most recommended movie show.

Request Parameters: ShowRequestDTO

Response: Movie name

**Theater Controller**

**Add Theater:**

Endpoint: POST /theater/add

Description: Add a new theater to the system.

Request Body: TheaterRequestDTO

Response: Success message or error message

**Add Theater Seats:**

Endpoint: POST /theater/addTheaterSeats

Description: Add seats to a specific theater.

Request Body: TheaterSeatsRequestDTO

Response: Success message or error message

**Ticket Controller**

**Book Ticket:**

Endpoint: POST /ticket/bookTicket

Description: Book a ticket for a show.

Request Body: TicketRequestDTO

Response: TicketResponseDTO

**Cancel Ticket:**

Endpoint: DELETE /ticket/cancelTicket/{ticketId}

Description: Cancel a booked ticket.

Path Variable: Ticket ID

Response: Success message or error message

**User Controller**

**Add User:**

Endpoint: POST /user/add

Description: Add a new user to the system.

Request Body: UserRequestDTO

Response: Success message or error message

Get Oldest User:

Endpoint: GET /user/getOldestUser

Description: Retrieve the oldest user in the system.

Response: UserResponseDTO

**Find All Users Greater Than Age:**

Endpoint: GET /user/findAllUsersGreaterThanAge

Description: Retrieve all users greater than a specified age.

Request Parameter: age

Response: List of users or error message



**Application**

BookMyShowApplication.java: Main application class.


**Resources**

application.properties: Configuration file.


**Technologies Used**

Java

Spring Boot

Spring Data JPA

Hibernate

Maven

MySQL 


**Contributing**

Contributions to BookMyShow are welcome! Please fork the repository, make your changes, and submit a pull request.

