package com.session7.bai1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration
@EnableWebMvc
@ComponentScan("com.session7")
public class AppConfig {
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(templateEngine());
        return resolver;
    }
}


//report:
// 1. Lỗi Tên cửa hàng bị null:
//Nguyên nhân: Cơ chế Data Binding của Spring dựa vào việc khớp tên thuộc tính name trong thẻ HTML với tên trường (field) trong Class POJO.
//Chi tiết: Trong file HTML, bạn đang đặt name="restaurantName", nhưng trong class RestaurantProfile, thuộc tính lại được khai báo là private String name. Vì hai tên này không đồng nhất, Spring không thể tìm thấy nơi để đổ dữ liệu vào, dẫn đến giá trị mặc định là null.
// 2. Lỗi Checkbox trạng thái active:
//Nguyên nhân: Sai lệch kiểu dữ liệu (Data Type Mismatch).
//Chi tiết: Trường active trong Java có kiểu boolean (chỉ nhận true hoặc false). Tuy nhiên, thẻ HTML lại định nghĩa value="MỞ_CỬA". Khi submit form, trình duyệt sẽ gửi chuỗi văn bản "MỞ_CỬA" về server. Spring không thể tự động chuyển đổi (convert) một chuỗi văn bản bất kỳ sang giá trị logic true/false. Ngoài ra, nếu checkbox không được tích, trình duyệt sẽ không gửi bất kỳ dữ liệu nào của trường đó về server, dễ gây lỗi logic nếu không xử lý trường hợp mặc định.