"""
Logistic Regression example from the scratch
by dyhan 2017.01.16
"""

import numpy as np
import matplotlib.pyplot as plt
from sklearn import datasets

class LogisticRegression():
    """Logistic regression class"""

    def __init__(self, x, y):
        self._x = np.matrix(x)
        self._x = self._feature_scailing()
        self._x = np.concatenate((np.ones([len(self._x), 1]), self._x), axis=1)
        self._yorg = y
        self._y = np.matrix([1 if e == 0 else 0 for e in y]).T
        self._theta = np.matrix([0, 1, 1])


    def _feature_scailing(self):
        x_std = np.std(self._x, axis=0)
        x_mean = np.mean(self._x, axis=0)
        print(x_std)
        print(x_mean)
        #print((x-x_mean) / x_std)
        return (x - x_mean) / x_std


    def sigmoid(self):
        """sigmoid funciton (hypothesis function)"""
        return 1/(1 + np.power(np.e, (-1 * np.dot(self._x, self._theta.T))))


    def plot(self):
        """ploting"""
        plt.xlabel('Sepal length')
        plt.ylabel('Sepal width')
        plt.scatter(self._x[:, 1], self._x[:, 2], c=self._yorg)
        xs = np.arange(self._x[:,1].min(), self._x[:,1].max(), 0.05)
        ys = (- self._theta[0,0] - self._theta[0,1] * xs) / self._theta[0,2]
        plt.plot(xs, ys, color='blue')
        return


    def cost_function(self):
        cost = np.dot(self._y.T, np.log(self.sigmoid())) \
               + np.dot((1 - self._y).T, np.log(1 - self.sigmoid()))
        cost = -cost / len(self._y)
        return cost


    def gradient_decent(self, alpha=0.3):
        """gradient decent"""
        self._theta = self._theta \
                      - alpha * np.dot((self.sigmoid() - self._y).T, self._x)
        return self._theta


    def fit(self, gap=0.0000001):
        pass


if __name__ == '__main__':
    plt.close()

    iris = datasets.load_iris()
    x = iris.data[:, :2]
    y = iris.target

    lr = LogisticRegression(x, y)
    lr.plot()
    plt.show()
    input('Press any key to continue...')

    print('Initial Theta : ', lr._theta)
    print('Initial Cost : ', lr.cost_function())

    for i in range(100):
        print('\niteration ', i)
        print('theta : ', lr.gradient_decent())
        print('cost : ', lr.cost_function())
        if i % 10 == 0:
            lr.plot()

    plt.show()
    input('Press any key to end...')
