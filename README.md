# Student Details by College and Branch

## Backend

Maintained a table with the following fields.
- **ID** – Varchar – Primary Key - Not Null
- **Name** – Varchar – Not Null
- **College** – Varchar – Not Null
- **Branch** – Varchar – Not Null
- **Age** – Int – Not Null
- **Gender** – Varchar – Not Null

I have taken these 3 colleges into consideration.
- AEC
- ACET
- ACOE

And when it comes to branches let’s assume that only the following branches are there.
- CSE
- IT
- AIML
- DS
- IoT
- ECE

Here is a sample table.

| ID | Name    | College | Branch | Age | Gender |
|----|---------|---------|--------|-----|--------|
| 1  | Tigress | AEC     | AIML   | 20  | Female |
| 2  | Mantis  | ACET    | AIML   | 17  | Male   |
| 3  | Po      | ACOE    | AIML   | 16  | Male   |
| 4  | Shen    | ACET    | DS     | 19  | Female |
| 5  | Kai     | AEC     | AIML   | 21  | Male   |
| 6  | Oogway  | ACOE    | IoT    | 25  | Male   |

## Frontend

The frame should contain 4 ComboBoxes:
- Insert
- Delete
- Update
- View

ComboBox Insert should insert a student data if it satisfy all the conditions.  
ComboBox Delete should delete a student data from the database if it is present.  
ComboBox Update should update a student data if there are any mistakes.  
ComboBox View should will display the all the data present in the Students data.   

### Example

## Sample Data

| ID | Name    | College | Branch | Age | Gender |
|----|---------|---------|--------|-----|--------|
| 1  | Tigress | AEC     | AIML   | 20  | Female |
| 2  | Mantis  | ACET    | AIML   | 17  | Male   |
| 5  | Kai     | AEC     | AIML   | 21  | Male   |

## Validations

- Validations should be done, and shows a pop-up window appear if any error occurs.



