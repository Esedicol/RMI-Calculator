# Remote Mothod Invocation (Calculator)

NOTE: To get this application working the server class has to be run first followed by running the Calculator frame class.

![UML Diagram](https://github.com/Esedicol/RMI-Calculator/blob/master/GUI.png)

## Methods
The client requests one of the 4 calculator methods provided by the server through the registry services provided:
- Add(int x, int y)
- Subtract(int x, int y)
- Multiply(int x, int y)
- Divide(int x, int y)

## Procedure
1) Client selects two integer operands and one operator by clicking buttons in the application GUI
2) Submit request to server to invoke a chose method
3) Server returns result to client where is it outputed in the textfiled
