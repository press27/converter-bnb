package eu.iba.auto_test.converterbnb.dao.model;

public class Employee {

    // Id пользователя
    private Long employeeId;

    // ФИО пользователя
    private String employeeFullName;

    // Логин Active Directory
    private String employeeLoginAD;

    // Тип пользователя
    private UserType userType;

    public Employee() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Employee setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public Employee setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
        return this;
    }

    public String getEmployeeLoginAD() {
        return employeeLoginAD;
    }

    public Employee setEmployeeLoginAD(String employeeLoginAD) {
        this.employeeLoginAD = employeeLoginAD;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public Employee setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }
}
