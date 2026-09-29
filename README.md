# AI Student Performance Prediction

An AI/ML mini project that predicts a student's final academic marks based on study hours, attendance, previous examination marks, assignment score, and internal assessment marks.

## Project Overview

The **AI Student Performance Prediction System** uses a Machine Learning model to estimate a student's final marks. The project uses **Linear Regression** and provides a simple prediction system through the Python terminal.

## Features

* Student performance prediction
* Uses multiple academic factors
* Linear Regression machine learning model
* Model evaluation using MAE, RMSE, and R² Score
* Performance classification
* Personalized performance recommendation
* Data visualization using Matplotlib
* Easy to run using Python or VS Code

## Input Parameters

The system accepts the following information:

* Study Hours per Day
* Attendance Percentage
* Previous Exam Marks
* Assignment Score
* Internal Assessment Marks

## Technologies Used

* Python
* NumPy
* Pandas
* Matplotlib
* Scikit-learn
* Linear Regression
* VS Code

## Project Structure

```text
AI-Student-Performance-Prediction/
│
├── AI_Student_Performance_Prediction.py
├── requirements_student_prediction.txt
├── README.md
├── .gitignore
└── LICENSE
```

## Installation

Clone the repository:

```bash
git clone https://github.com/YOUR-USERNAME/AI-Student-Performance-Prediction.git
```

Go to the project directory:

```bash
cd AI-Student-Performance-Prediction
```

Install the required libraries:

```bash
python -m pip install -r requirements_student_prediction.txt
```

## Run the Project

Run the following command:

```bash
python AI_Student_Performance_Prediction.py
```

The program will ask for student information such as study hours, attendance, previous marks, assignment score, and internal marks.

## Example Input

```text
Study Hours per Day: 6
Attendance Percentage: 85
Previous Exam Marks: 72
Assignment Score: 80
Internal Assessment Marks: 75
```

## Output

The system displays:

* Predicted Final Marks
* Performance Category
* Recommendation
* Model evaluation results

It also generates graphs showing relationships between student performance factors and final marks.

## Machine Learning Model

The project uses **Linear Regression** to predict final student marks from the given input features.

### Performance Categories

| Predicted Marks | Category          |
| --------------- | ----------------- |
| 85 and above    | Excellent         |
| 70–84           | Good              |
| 50–69           | Average           |
| Below 50        | Needs Improvement |

## Visualizations

The project generates visualizations for:

1. Study Hours vs Final Marks
2. Attendance vs Final Marks
3. Actual vs Predicted Final Marks

## Future Scope

* Use a larger real-world dataset
* Compare multiple machine learning algorithms
* Add a graphical user interface
* Create a web-based dashboard
* Store student records in a database
* Add more academic and behavioral features

## Author

**Student Performance Prediction – AI/ML Mini Project**

## License

This project is licensed under the MIT License.
