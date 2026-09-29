# AI Mini Project: Student Performance Prediction System
# Directly runnable in Google Colab / Python
#
# Required libraries:
# pandas, numpy, matplotlib, scikit-learn
#
# If running in Google Colab, run:
# !pip install pandas numpy matplotlib scikit-learn

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_absolute_error, mean_squared_error, r2_score

# ============================================================
# 1. CREATE SAMPLE STUDENT DATASET
# ============================================================

data = {
    "Study_Hours": [
        2, 3, 4, 5, 6, 7, 8, 1, 3, 5,
        6, 7, 4, 8, 9, 2, 5, 6, 7, 4,
        3, 8, 9, 6, 5, 2, 7, 8, 4, 6
    ],
    "Attendance": [
        60, 65, 70, 75, 80, 85, 90, 55, 68, 78,
        82, 88, 72, 92, 95, 58, 76, 84, 89, 73,
        67, 91, 96, 83, 77, 62, 87, 93, 71, 81
    ],
    "Previous_Marks": [
        45, 50, 55, 60, 65, 70, 75, 40, 52, 62,
        68, 73, 57, 78, 82, 43, 61, 69, 74, 56,
        49, 80, 85, 71, 64, 46, 77, 81, 54, 67
    ],
    "Assignment_Score": [
        50, 55, 60, 65, 70, 75, 80, 45, 58, 68,
        72, 78, 63, 82, 88, 48, 67, 74, 79, 61,
        56, 85, 90, 76, 69, 52, 83, 87, 59, 73
    ],
    "Internal_Marks": [
        48, 52, 58, 62, 68, 73, 78, 42, 55, 65,
        70, 76, 61, 80, 86, 45, 64, 71, 77, 59,
        53, 83, 88, 73, 67, 49, 81, 85, 57, 70
    ],
    "Final_Marks": [
        45, 50, 56, 61, 67, 72, 78, 40, 53, 64,
        69, 75, 60, 81, 87, 43, 63, 71, 76, 58,
        51, 84, 89, 74, 66, 47, 80, 86, 55, 69
    ]
}

df = pd.DataFrame(data)

print("=" * 60)
print("        AI STUDENT PERFORMANCE PREDICTION SYSTEM")
print("=" * 60)

print("\nDataset Preview:")
print(df.head())

# ============================================================
# 2. PREPARE DATA
# ============================================================

X = df[
    [
        "Study_Hours",
        "Attendance",
        "Previous_Marks",
        "Assignment_Score",
        "Internal_Marks"
    ]
]

y = df["Final_Marks"]

# Split dataset into training and testing data
X_train, X_test, y_train, y_test = train_test_split(
    X, y,
    test_size=0.2,
    random_state=42
)

# ============================================================
# 3. TRAIN MACHINE LEARNING MODEL
# ============================================================

model = LinearRegression()
model.fit(X_train, y_train)

# ============================================================
# 4. MODEL EVALUATION
# ============================================================

y_pred = model.predict(X_test)

mae = mean_absolute_error(y_test, y_pred)
rmse = np.sqrt(mean_squared_error(y_test, y_pred))
r2 = r2_score(y_test, y_pred)

print("\nModel Evaluation:")
print(f"Mean Absolute Error : {mae:.2f}")
print(f"Root Mean Square Error : {rmse:.2f}")
print(f"R² Score : {r2:.2f}")

# ============================================================
# 5. PERFORMANCE CLASSIFICATION
# ============================================================

def classify_performance(score):
    if score >= 85:
        return "Excellent"
    elif score >= 70:
        return "Good"
    elif score >= 50:
        return "Average"
    else:
        return "Needs Improvement"

# ============================================================
# 6. USER INPUT FOR PREDICTION
# ============================================================

print("\n" + "=" * 60)
print("           ENTER STUDENT DETAILS")
print("=" * 60)

try:
    study_hours = float(input("Study Hours per Day: "))
    attendance = float(input("Attendance Percentage: "))
    previous_marks = float(input("Previous Exam Marks: "))
    assignment_score = float(input("Assignment Score: "))
    internal_marks = float(input("Internal Assessment Marks: "))

except ValueError:
    print("\nInvalid input. Using sample values instead.")
    study_hours = 6
    attendance = 85
    previous_marks = 72
    assignment_score = 80
    internal_marks = 75

student = pd.DataFrame({
    "Study_Hours": [study_hours],
    "Attendance": [attendance],
    "Previous_Marks": [previous_marks],
    "Assignment_Score": [assignment_score],
    "Internal_Marks": [internal_marks]
})

predicted_score = model.predict(student)[0]

# Keep prediction within realistic range
predicted_score = max(0, min(100, predicted_score))

performance = classify_performance(predicted_score)

# ============================================================
# 7. DISPLAY PREDICTION
# ============================================================

print("\n" + "=" * 60)
print("              PREDICTION RESULT")
print("=" * 60)

print(f"Study Hours          : {study_hours:.1f}")
print(f"Attendance           : {attendance:.1f}%")
print(f"Previous Marks       : {previous_marks:.1f}")
print(f"Assignment Score     : {assignment_score:.1f}")
print(f"Internal Marks       : {internal_marks:.1f}")
print(f"Predicted Final Mark : {predicted_score:.2f}")
print(f"Performance          : {performance}")

if predicted_score >= 70:
    print("Recommendation       : Keep up the good work!")
elif predicted_score >= 50:
    print("Recommendation       : Increase study time and practice.")
else:
    print("Recommendation       : Increase study time and seek academic support.")

# ============================================================
# 8. VISUALIZATION 1 - STUDY HOURS VS FINAL MARKS
# ============================================================

plt.figure(figsize=(8, 5))
plt.scatter(df["Study_Hours"], df["Final_Marks"], s=70)
plt.xlabel("Study Hours per Day")
plt.ylabel("Final Marks")
plt.title("Study Hours vs Final Marks")
plt.grid(True, alpha=0.3)
plt.show()

# ============================================================
# 9. VISUALIZATION 2 - ATTENDANCE VS FINAL MARKS
# ============================================================

plt.figure(figsize=(8, 5))
plt.scatter(df["Attendance"], df["Final_Marks"], s=70)
plt.xlabel("Attendance Percentage")
plt.ylabel("Final Marks")
plt.title("Attendance vs Final Marks")
plt.grid(True, alpha=0.3)
plt.show()

# ============================================================
# 10. ACTUAL VS PREDICTED MARKS
# ============================================================

plt.figure(figsize=(8, 5))
plt.scatter(y_test, y_pred, s=70)
plt.plot(
    [y_test.min(), y_test.max()],
    [y_test.min(), y_test.max()]
)
plt.xlabel("Actual Marks")
plt.ylabel("Predicted Marks")
plt.title("Actual vs Predicted Final Marks")
plt.grid(True, alpha=0.3)
plt.show()

print("\n" + "=" * 60)
print("              PROJECT COMPLETED")
print("=" * 60)
print("AI Student Performance Prediction System executed successfully.")
