**LoadDatabase.class initially adds 3 customers:**  
firstName:"John", lastName:"Smith", personalId:"38602114422", gender:"male", birthDay:"1980-02-01"  
firstName:"Joan", lastName:"Juken", personalId:"495011231544", gender:"female", birthDay:"1995-01-25"  
firstName:"Martins", lastName:"Saimuskins", personalId:"33303-44343", gender:"male", birthDay:"1985-06-13"  

**Search by personal id or birth day:**  
http://localhost:8080/persons/search/38602114422
http://localhost:8080/persons/search/1980-02-01


**Logging to file:** persons.log

**Logging to database** is working when error is dropped(log level can be configured in properties file),
i.e. when search returned 0 results

**Database access:**
localhost:8080/h2
url=jdbc:h2:mem:testdb, username=sa, password=""


**Angular UI start:**  
persons-ng/ng serve -o
