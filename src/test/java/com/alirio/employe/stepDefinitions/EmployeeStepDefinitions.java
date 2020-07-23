package com.alirio.employe.stepDefinitions;

import com.alirio.CucumberSpringContextConfiguration;
import com.alirio.employe.entities.EmployeeRegister;
import com.alirio.employe.entities.GetEmployeesRegistered;
import com.alirio.utils.ResponseWrapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java8.En;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@ContextConfiguration(classes = CucumberSpringContextConfiguration.class)
public class EmployeeStepDefinitions implements En {

    @Value("${x.server.uri}")
    String xServerUri;
    @Value("${x.server.path}")
    String xBasePath;
    @Value("${x.service.delete.db}")
    String xServiceDeleteDB;
    @Value("${x.service.add.employee}")
    String xServiceAddEmployee;
    @Value("${x.service.get.employees}")
    String xServiceGetEmployees;

    @Autowired
    ResponseWrapper responseWrapper;

    /**Convert cell black in empty string ""
     * */
    @DataTableType(replaceWithEmptyString = "[blank]")
    public GetEmployeesRegistered convert(Map<String, String> entry) {
        return new GetEmployeesRegistered(entry.get("company"),
                entry.get("gender"), entry.get("rol"),
                entry.get("from"), entry.get("to"));
    }

    public EmployeeStepDefinitions() {
        Given("^The employee database is clean$", () -> {
            given().log().all().baseUri(xServerUri).basePath(xBasePath)
                    .when().delete(xServiceDeleteDB)
                    .then().log().all();
        });
        Given("^the company \"([^\"]*)\" registers new employees$", (String company, DataTable dataTable) -> {
            dataTable.asList(EmployeeRegister.class).forEach(employee -> {
                SerenityRest.given().log().all().baseUri(xServerUri).basePath(xBasePath)
                        .pathParam("company", company)
                        .body(employee)
                        .when().post(xServiceAddEmployee);
            });
        });

        Then("^I have the following employees$", (DataTable dataTable) -> {
            responseWrapper.getResponse().then().log().all();
            List<EmployeeRegister> actual = responseWrapper.getResponse()
                    .jsonPath().getList("", EmployeeRegister.class);
            List<EmployeeRegister> expected = dataTable.asList(EmployeeRegister.class);
            assertThat(actual, hasSize(expected.size()));
            assertThat(actual, containsInAnyOrder(expected.stream().map(SamePropertyValuesAs::samePropertyValuesAs).collect(Collectors.toList())));

        });
        When("^Get Employees Registered by$", (GetEmployeesRegistered getEmployeesRegistered) -> {
            responseWrapper.setResponse(SerenityRest.given().log().all().baseUri(xServerUri).basePath(xBasePath)
                    .body(getEmployeesRegistered)
                    .when().post(xServiceGetEmployees));
        });
    }
}
