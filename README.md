## README

### CONTENTS
* [Introduction](#Introduction)
* [Compile Instructions](#Compile)
* [Run Instructions](#Run)
* [Source](#Source)

### [](id:Introduction)INTRODUCTION

The Hypothetical Operating System (HOS) attempts to simulate an operating system's various job scheduling strategies. The HOS is assumed to be running on two processors, executing two jobs in parallel each time unit. The HOS randomly generates a sample of jobs, places them in a waiting queue, and the executes them based on the following criteria:  
Case One:  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;First-Come, First-Served  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;First-Fit  
Case Two:  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;First-Come, First-Served  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Best-Fit  
Case Three:  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Shortest-Job, First-Served  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Best-Fit  
In all cases, the jobs are executed by the processors in a round robin fashion.  
For more information, please see the attached instructions document, project.doc.

### [](id:Compile)COMPILE INSTRUCTIONS

To compile the program, please unzip P_MS_DW and then, using a terminal, cd into
the unzipped folder; i.e.:

```
cd P_MS_DW
```

Ensure you have java installed, then execute:

```
javac *.java
```

This will compile all java files in the folder. Alternatively, you can compile them manually as such:

```
javac caseOne.java
javac caseTwo.java
javac caseThree.java
javac OS.java
javac Process.java
javac ProcessGenerator.java
javac MainMemory.java
javac MemoryModule.java
javac Queue.java
```

Please note that compiling the code is not strictly necessary, as precompiled classes are included.

### [](id:Run)RUN INSTRUCTIONS

In a terminal, execute the following command:

```
java HOS
```

Will execute the HOS using Case One.

The HOS can be passed up to two flags. The first flag represents the case type (please see introduction) that you would like the HOS to execute. The following are valid options for flag one:

```
1
2
3
4
```

As expected, 1 executes case one, 2 executes case 2, and 3 executes case 3.  
If you use 4 as the flag, all cases will be executed in order from 1-3.

Some examples of how to use the HOS follow:

```
java HOS 1 // Execute the HOS with case type 1.  
java HOS 2 // Execute the HOS with case type 2.  
java HOS 3 // Execute the HOS with case type 3.  
java HOS 4 // Execute the HOS, running case types 1, 2, and 3, sequentially, in that order.
```

The second flag should NOT BE USED unless debugging code. If you pass a 'v' as the second flag, the HOS will execute in verbose mode - meaning, it will print MUCH more data, some redundant, and some unecessary or unimportant to the user executing this program. Again, only use the v flag if debugging. It also breaks the nicer formatting of the output when not using the v flag.

As an example of how to use the v flag:

```
java HOS 4 v // Execute the HOS, running case types 1, 2, and 3, sequentially, in that order, while printing to the terminal verbosely debugging information.
```

Again - do not use the v flag without good reason.
  
### [](id:Source)SOURCE

**caseOne.java**  
Contains the code for executing the HOS for case one's criteria.

**caseTwo.java**  
Contains the code for executing the HOS for case two's criteria.

**caseThree.java**  
Contains the code for executing the HOS for case three's criteria.

**OS.java**  
Contains the core of the OS. Creates all needed objects, calls the scripts for executing the Jobs, and handles the flags the user passes.

**Process.java**  
Stores information relevant to a specific job/process; also contains
functions to set and retrieve this information.

**ProcessGenerator.java**  
Generates 20 process with random/default information for the Queue.

**MainMemory.java**  
Contains all MemoryModule objects, which it creates. It has functions
for setting and retrieving data for each MemoryModule as well as
itself. Also contains functions to assign and remove jobs from
positions in its memory.

**MemoryModule.java**  
Stores information relevant to a specific position in memory; also
contains functions to set and retrieve this information.

**Queue.java**  
Contains all Job objects, which it creates. It has functions for
setting and retrieving data for each Job as well as itself. Also
contains other functions.