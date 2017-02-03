"""
page 10
first ML method - linear classfication
"""

# pylint: disable=I0011,C0103

import numpy as np

from sklearn import datasets
from sklearn.cross_validation import train_test_split
from sklearn import preprocessing

import matplotlib.pyplot as plt

plt.close()

# P10
iris = datasets.load_iris()
X_iris, y_iris = iris.data, iris.target
print(X_iris.shape, y_iris.shape)


# Get dataset with only the first tow attributes
X, y = X_iris[:, :2], y_iris

# Split the dataset into a training and a testing set
# Test set will be the 25% taken randomly
X_train, X_test, y_train, y_test = \
                train_test_split(X, y, test_size=0.25, random_state=33)
print('X_train.shape, y_train.shape :')
print(X_train.shape, y_train.shape)

# Standardize the features
scaler = preprocessing.StandardScaler().fit(X_train)
X_train = scaler.transform(X_train)
X_test = scaler.transform(X_test)

# P11
colors = ['red', 'greenyellow', 'blue']
for i in range(len(colors)):
    xs = X_train[:, 0][y_train == i]
    ys = X_train[:, 1][y_train == i]
    plt.scatter(xs, ys, c=colors[i])
plt.legend(iris.target_names)
plt.xlabel('Sepal length')
plt.ylabel('Sepal width')

# P13
# separation Iris setosa and others
from sklearn.linear_model import SGDClassifier
clf = SGDClassifier()
clf.fit(X_train, y_train)

print('clf.coefficient :')
print(clf.coef_)
print('clf.intercept :')
print(clf.intercept_)

# P14
# plot decision boundaries
x_min, x_max = X_train[:, 0].min() - .5, X_train[:, 0].max() + .5
y_min, y_max = X_train[:, 1].min() - .5, X_train[:, 1].max() + .5
xs = np.arange(x_min, x_max, 0.5)
fig, axes = plt.subplots(1, 3)
fig.set_size_inches(10, 6)
for i in [0, 1, 2]:
    axes[i].set_aspect('equal')
    axes[i].set_title('Class ' + str(i) + ' versus the rest')
    axes[i].set_xlabel('Sepal length')
    axes[i].set_ylabel('Sepal width')
    axes[i].set_xlim(x_min, x_max)
    axes[i].set_ylim(y_min, y_max)
    plt.sca(axes[i])
    for j in range(len(colors)):
        px = X_train[:, 0][y_train == j]
        py = X_train[:, 1][y_train == j]
        plt.scatter(px, py, c=colors[j])
        #plt.scatter(X_train[:, 0], X_train[:, 1], c=y_train, cmap=plt.cm.prism)
    ys = (-clf.intercept_[i] - xs * clf.coef_[i, 0]) / clf.coef_[i, 1]
    plt.plot(xs, ys, hold=True)

# P15
# predict new flow
# sepal width 4.7, length 3.1
print('New flow - sepal width 4.7, length 3.1 :')
print(clf.predict(scaler.transform([[4.7, 3.1]])))

# p15
# decision (with distance from boundary)
print('Decision with distance from boundary :')
print(clf.decision_function(scaler.transform([[4.7, 3.1]])))
