package MvnTest;


import org.springframework.context.annotation.*;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.*;
//import org.springframework.stereotype.Component;
import java.util.function.*;


public class App 
{
    public static void main( String[] args )
    {
				//registerBean
				var ctxt_1 = new AnnotationConfigApplicationContext(RegiConfig.class);
				Supplier<Dog> sup = ()->new Dog("Doge");
				ctxt_1.registerBean("myDoggo",Dog.class,sup);
				var dgo = ctxt_1.getBean("myDoggo",Dog.class);
				dgo.bark();

				//Component & CompomentScan
				var ctxt_2 = new AnnotationConfigApplicationContext(ComConfig.class);
				var prt = ctxt_2.getBean(Parrot.class);
				prt.speak();	

				//Bean
				var ctxt_3 = new AnnotationConfigApplicationContext(BeanConfig.class);
				var p = ctxt_3.getBean("parrot",Parrot.class);
				p.speak();	

				var h = ctxt_3.getBean("myFirst",String.class);
				System.out.println("string val: " + h);

				var b = ctxt_3.getBean("bye",String.class);
				System.out.println("string val: " + b);


				var de = ctxt_3.getBean(String.class);
				System.out.println("string val: " + de);

				var i = ctxt_3.getBean("giveMeInt", Integer.class);
				System.out.println("integer val: " + i);

    }
}

@Configuration
class BeanConfig{
	@Bean
	Parrot parrot(){
		var p = new Parrot();
		p.setName("Sung");
		return p;
	}
	@Bean(name="myFirst")
	String hello(){
		return "HALLO~~";
	}

	@Bean
	@Primary
	String bye(){
		return "Bye..";
	}
	@Bean
	Integer giveMeInt(){
		return 104;
	}
}


@Configuration
@ComponentScan(basePackages = "MvnTest")
class ComConfig{
}

@Component
class Parrot{
	private String name;
	public Parrot(){this.name = "default name";}
	public void setName(String name){
		this.name = name;
	}
	public void init(){
		this.name = "Kiki";
	}
	public void speak(){
		System.out.println("Hi, I am " + this.name + " a parrot!");
	}
}

@Configuration
class RegiConfig{
}

class Dog{
	private String name;
	public Dog(String name){this.name=name;}
	public void setName(String name){this.name = name;}
	public void bark(){System.out.println("I am Dog " + this.name);}
}

//import org.apache.commons.codec.digest.DigestUtils;
//System.out.println(DigestUtils.sha256Hex(args[0]));
