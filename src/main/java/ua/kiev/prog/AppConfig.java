package ua.kiev.prog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/static/");
    }

    @Bean
    public CommandLineRunner demo(final ContactService contactService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Group group = new Group("Test");
                Contact contact;

                contactService.addGroup(group);

                for (int i = 0; i < 13; i++) {
                    contact = new Contact(null, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@test.com");
                    contactService.addContact(contact);
                }
                for (int i = 0; i < 10; i++) {
                    contact = new Contact(group, "Other" + i, "OtherSurname" + i, "7654321" + i, "user" + i + "@other.com");
                    contactService.addContact(contact);
                }
                Group groupOne = new Group("TestOne");

                contactService.addGroup(groupOne);

                for (int i = 13; i < 23; i++) {
                    contact = new Contact(groupOne, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@testOne.com");
                    contactService.addContact(contact);
                }
                Group groupTwo = new Group("TestTwo");
                contactService.addGroup(groupTwo);

                for (int i = 23; i < 33; i++) {
                    contact = new Contact(groupTwo, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@testTwo.com");
                    contactService.addContact(contact);
                }
                Picture picture;
                File picturesCatalog = new File("/Users/svetlana/Documents/JavaPro Progects/SP7Imp/src/main/webapp/WEB-INF/static");
                File[] picturesFileList = picturesCatalog.listFiles();
                for (File f : picturesFileList) {
                    picture = new Picture(f.getName());
                    contactService.addPicture(picture);
                }
            }
        };
    }
}
