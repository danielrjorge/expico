# Expico
Project to help manage a family cattle farming business.

The purpose and goal of this project is to build a User Interface that aids the farm owner in the daily operations.

Includes record of all the cattle that are part of the farm, as well as all the 
records of buying, selling and butchers of cattle the and financial records associated with these transactions, as well as all the owners involved.\
Then, since the farm owner has various distinct and separate pastures, it will also keep track of these pastures,
with the current cattle grazing in each pasture, and in the UI there will be an option to drag and drop the cattle 
to each pasture to aid in the daily management of the farm business.

More features are being considered to be added after this first development and these include:

- Vaccination
- Vehicles
- Field logging of events

### Current development progress

At this stage of development, the application has endpoints for:
- Cattle existences
- Cattle bought
- Cattle sold
- Cattle butchered
- Owners

This also includes the database to store these records, as well as the financial movements, including VAT, which were
done in conjunction with the cattle buys and sales, but don't have an exposed endpoint by themselves at this stage.

### Next steps
- Improve existing endpoints capabilities
- Implement land/pasture endpoint
- Build web front for interactions

Made with Java and Spring Boot, MySql database.