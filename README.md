You are required to write a program which filters application request log extracts by a number of different properties.

A log extract file contains a header row, followed by zero or more data rows, in comma-separated value format. The
first column is the Unix timestamp (in seconds) of the time the request was made, the second column is the country
from which the request originated, and the third column is the time in milliseconds which the request took to complete.
The data rows are not sorted, grouped, or ordered.

An example file is:

    REQUEST_TIMESTAMP,COUNTRY_CODE,RESPONSE_TIME
    1433190845,US,539
    1432917066,GB,37

The features which you must implement have been prototyped in the class com.teamitg.DataFilterer. You must implement the
features in this class without changing the signatures of any methods, except that you are allowed to change the return types
of the DataFilterer methods so they return List<SomeOtherType> rather than List<?>. You should not add any new maven dependencies.

You must also provide evidence that the features you have implemented work correctly.

The time allotted to the test is 1 hour. Your solution will be judged on a number of criteria pertinent to good
software development practice. Incomplete solutions are acceptable.
