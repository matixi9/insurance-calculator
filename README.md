# Vehicle Insurance Calculation System (Insurance API)

## 1. Application Topic
The application is a server-side Quoting Engine. It allows clients to submit driver and vehicle parameters, and then—based on defined business rules and risk strategies—calculates the final insurance price.

**Main Functionalities:**
* Accepting quote requests (driver data + car data).
* Logical validation of requests (e.g., rejecting uninsurable vehicles).
* Storing the history of calculated offers in the application's memory.

---

## 2. Domain Model and Business Rules

### Main Entities (Model)
1.  **InsuranceRequest (Quote Request):** Input object containing:
    * `pesel` (String) - client identifier/National ID.
    * `age` (int) - driver's age.
    * `type` (Enum) - vehicle type (Car, Truck, Motorcycle, Bike).
    * `hadAccident` (boolean) - whether the driver caused an accident in the last year.
2.  **InsuranceOffer (Offer):** Output object containing:
    * `id` (int) - unique offer number.
    * `finalPrice` (double) - calculated premium.
    * `description` (String) - offer details.
    * `creationDate` (LocalDate) - date of calculation.

### Business Rules (Validation)
The application ensures data integrity according to the insurer's rules:
1.  **Legal Age Rule:** The system rejects requests (throws an error/exception) if the driver's age is less than 18.
2.  **Vehicle Type Logic:** Different vehicle types have different base costs (e.g., Trucks are more expensive than Bikes).
3.  **Risk Factors:** The final price is adjusted based on risk factors (age, accident history) using the Strategy pattern.

---

## 3. WebAPI - Documentation

### Endpoint List

| Method | Endpoint          | Description |
| :--- |:------------------| :--- |
| `POST` | `/insurance`      | Accepts a request (`InsuranceRequest`), calculates the price, and returns the created offer (`InsuranceOffer`). |
| `GET` | `/insurance/{id}` | Retrieves details of a calculated offer based on its ID. |
| `GET` | `/insurance`      | Returns a list of all offers generated in the current session. |
| `PUT` | `/insurance/{id}` | Updates an existing offer (re-calculates price based on new data). |
| `DELETE` | `/insurance/{id}` | Removes an offer from the system. |

### Sample Input Data (JSON for POST)
```json
{
  "pesel": "98020212345",
  "age": 25,
  "type": "CAR",
  "hadAccident": true
}