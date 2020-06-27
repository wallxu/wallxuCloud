package com.wallxu.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author: xukf
 * @email: xukf1@ziroom.com
 * @date: 2020/5/22 14:56
 * @since 1.0.0
 */

@Configuration
@ComponentScan(basePackages={"com.wallxu"},
        excludeFilters={@
                ComponentScan.Filter(type= FilterType.ANNOTATION,value= EnableWebMvc.class)})
public class RootConfig {

}