# Spark

Fast and easy, to use framework of big data world

# What is Apache Spark™?

Apache Spark™ is a multi-language engine for executing data engineering,
data science, and machine learning on single-node machines or clusters.

# Key features

- Batch/streaming data
- SQL analytics
- Data science at scale
- Machine learning

# What is Apache Spark?

Apache Spark is Lightning-fast cluster computing technology.

- Big data
- Distributed system
- [MapReduce](#Map Reduce)

# Way spark

- Readability(Less code)
- can work with cached data in memory which makes it faster
- testability
- Interactive
- Unify Big data
    - whether you are processing:
        - Batch data
        - streaming data
        - up to graphing
        - or machine learning

Apache Spark is a lightning-fast cluster computing technology,
designed for fast computation. It is based on Hadoop MapReduce,
and it extends the MapReduce model to efficiently use it for more
types of computations, which includes interactive queries and stream processing.
The main feature of Spark is its in-memory cluster computing that increases the
processing speed of an application.

# Map Reduce

- Distributed system

Map reduce is parallelling the processing across a distribution of machines.
So to solve a big data problem by throwing it against big processing power,

## Disadvantages of Map reduce

- algorithm complexities
- disc bottleneck
- limited to patching

# RDD

- Stands for resilient distributed Dataset.
- Immutable
- DAG
    - Source
    - Transformation
        - Map
        - filter
        - ...
    - Sink

all the transformation are lazy
the actions will trigger the computation

- actions
    - collect
    - count
    - reduce
    - ...

Those actions will trigger the DAG execution and result in some final action against the data
We can think of it as a collection,similar to list or an array.
the processing of the rdd is done through

- drivers and
- executors(workers)

the driver creates a spark context
the spark context uses the cluster manages to assign task to the executors