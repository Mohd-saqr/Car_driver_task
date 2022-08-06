# Car_driver_task

## all task done 
- look at test/java/carDriverAPPLICATIONtEST all test added for driver

# Tasks 

--------------------------------------
### Task#1:
Write a new Controller for maintaining cars (CRUD).
- Decide on your own how the methods should look like.
- Entity Car: Should have at least the following characteristics: license_plate,
  seat_count, convertible, rating, engine_type (electric, gas, ...)
- Entity Manufacturer: Decide on your own if you will use a new table or just a string
  column in the car table.
- Extend the DriverController to enable drivers to select a car they are driving
  with.
- Extend the DriverController to enable drivers to deselect a car.
- Extend the DriverDo to map the selected car to the driver.
  Add example data to resources/data.sql
  ### Task#2
  First come first serve: A car can be selected by exactly one ONLINE Driver. If a second
  driver tries to select an already used car you should throw a CarAlreadyInUseException.
  ### Task #3:
  Make use of the filter pattern to implement an endpoint in the DriverController to get a list of
  drivers with specific characteristics. Reuse the characteristics you implemented in task 1.
  ### Task #4
  Security: secure the API. It’s up to you how you are going to implement the security (Spring
  Security or JWT)
