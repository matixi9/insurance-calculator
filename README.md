# Vehicle Insurance Calculation System (Insurance API)

## 1. Application Topic
The application is a server-side Quoting Engine created with **Spring Boot**. It allows clients to submit driver and vehicle parameters to calculate the final insurance premium based on configurable business rules and risk strategies.

**Main Functionalities:**
* Accepting quote requests (driver data + car data).
* **Configurable Pricing:** Base prices and risk multipliers are defined in `application.properties`.
* **Flexible Risk Strategy Pattern:** Easy to extend logic for new risk factors.
* In-memory storage of calculated offers.

---

## 2. Domain Model and Business Rules

### Main Entities (Model)
1.  **InsuranceRequest (Quote Request):** Input object containing:
    * `pesel` (String) - client identifier/National ID (validated).
    * `age` (int) - driver's age (min. 18).
    * `type` (Enum) - vehicle type (CAR, TRUCK, MOTORCYCLE, BIKE).
    * `engineCapacity` (double) - vehicle engine capacity (e.g., 2.0).
    * `vehicleAge` (int) - age of the vehicle in years.
    * `hadAccident` (boolean) - accident history flag.

2.  **InsuranceOffer (Offer):** Output object containing:
    * `id` (int) - unique offer number.
    * `finalPrice` (double) - calculated premium.
    * `description` (String) - offer details.
    * `creationDate` (LocalDate) - calculation date.

### Business Rules & Configuration
Base prices and multipliers can be changed in `src/main/resources/application.properties` without recompiling the code.

**Current Pricing Logic:**
1.  **Base Calculation:** `Global Base Price` + `Vehicle Type Price`.
2.  **Risk Multipliers (Strategies):**
    * **Young Driver:** Drivers under 24 years old (default x1.3).
    * **Accident History:** Drivers who caused an accident (default x1.6).
    * **New Car:** Vehicles younger than 5 years (default x1.5).
    * **Old Car:** Vehicles older than 20 years (default x1.2).
    * **High Engine Capacity:** Engines >= 3.0L (default x1.5).

---

## 3. WebAPI - Documentation

### Endpoint List

| Method | Endpoint          | Description |
| :--- |:------------------| :--- |
| `POST` | `/insurance`      | Accepts `InsuranceRequest`, calculates price, saves, and returns `InsuranceOffer`. |
| `GET` | `/insurance/{id}` | Retrieves details of a calculated offer by ID. |
| `GET` | `/insurance`      | Returns a list of all offers in the current session. |
| `PUT` | `/insurance/{id}` | Updates an existing offer with new parameters. |
| `DELETE` | `/insurance/{id}` | Removes an offer from the system. |

### Sample Input Data (JSON for POST)
```json
{
  "pesel": "98020212345",
  "age": 20,
  "type": "CAR",
  "engineCapacity": 1.6,
  "vehicleAge": 4,
  "hadAccident": false
}