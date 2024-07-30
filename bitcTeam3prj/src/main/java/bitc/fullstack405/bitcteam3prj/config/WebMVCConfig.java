package bitc.fullstack405.bitcteam3prj.config;

import bitc.fullstack405.bitcteam3prj.inteceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

//  인터셉터, excludePathPatterns()로 로그인 필요없는 뷰페이지 추가 가능
//  현재 세션 유지시간 1시간
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheck()).addPathPatterns("/loginSuccess"); // 로그인 필요 페이지 추가 가능
  }
}
