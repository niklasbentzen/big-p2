# big-p2

T-764-DATA – Spring 2025

By Joshua Richard GUILLOUARD & Niklas Bentzen

---

This project implements a K-means clustering algorithm. We've used the algorithm to test different distance metrics and amounts of data.

The algorithm runs in Python using Pyspark and can be found in `./src/python/kmeans.ipynb`. The code is written in a Jupyter Notebook so we are able to load the data once as well as easier fixing errors. 
- The first part of the code initilises the Spark and sets the variables for the experiment. 
- Afterwards, the methods for finding the closest centroid and computing new centroids are defined. The first set of centroids is randomly found.
- At last we use iterate over the cluster in `max_iterations`times and create a graph over the loss per iteration.

---

For the project we were provided with a files of SIFT descriptors. We've converted the datasets to JSON files using the code found in `./src/scala/transform.scala`. This code was provided by Maria Pegia.