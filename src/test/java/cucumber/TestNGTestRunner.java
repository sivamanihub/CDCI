package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(features="src/test/java/cucumber",
glue="selenium.stepDefinition",
monochrome=true, 
tags = "@ErrorValidation", 
plugin= {"html:traget/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
}
