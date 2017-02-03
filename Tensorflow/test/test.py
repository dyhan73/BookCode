import tensorflow as tf

a = tf.placeholder("float")
b = tf.placeholder("float")

y = tf.matmul(a, b)

sess = tf.Session()

#print(sess.run(y, feed_dict = {a:3, b:3}))

#print(sess.run(y, feed_dict = {a:[[2,3],[4,5]], b:3}))

print(sess.run(y, feed_dict = {a:[[2,3],[4,5]], b:[[1,2], [3,4]]}))
