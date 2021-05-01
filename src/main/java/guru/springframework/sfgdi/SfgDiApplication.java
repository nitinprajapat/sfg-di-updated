package guru.springframework.sfgdi;

import guru.springframework.sfgdi.config.SfgConstructorConfig;
import guru.springframework.sfgdi.controllers.*;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.services.PrototypeBean;
import guru.springframework.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		MyController myController = (MyController) ctx.getBean("myController");

		System.out.println("------- Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("------ Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------- Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor" );
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		SingletonBean singletonBean= (SingletonBean) ctx.getBean("singletonBean");
		System.out.println(singletonBean.getMyScope());
		SingletonBean singletonBean1= (SingletonBean) ctx.getBean("singletonBean");
		System.out.println(singletonBean1.getMyScope());


		PrototypeBean prototypeBean= (PrototypeBean) ctx.getBean("prototypeBean");
		System.out.println(prototypeBean.getMyScope());
		PrototypeBean prototypeBean1= (PrototypeBean) ctx.getBean("prototypeBean");
		System.out.println(prototypeBean1.getMyScope());

		FakeDataSource fakeDataSource= (FakeDataSource) ctx.getBean("fakeDataSource");
		System.out.println(fakeDataSource.getUserName());
		System.out.println(fakeDataSource.getPassword());
		System.out.println(fakeDataSource.getJdbcUrl());

		System.out.println("---------------Constructor binding");
		SfgConstructorConfig sfgConstructorConfig=ctx.getBean(SfgConstructorConfig.class);
		System.out.println(sfgConstructorConfig.getUserName());
		System.out.println(sfgConstructorConfig.getPassword());
		System.out.println(sfgConstructorConfig.getJdbcUrl());

	}

}
