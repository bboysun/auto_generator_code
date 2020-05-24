package com.darryl.snow.autogenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @Auther: Darryl
 * @Description: 逆向工程
 * @Date: 2020/05/24
 */
public class SnowAutoGenerator {
	/**
	 * 读取控制台内容
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	public static void main(String[] args) {
		/**
		 * 代码生成器
		 */
		AutoGenerator mpg = new AutoGenerator();

		/**
		 * 全局配置
		 */
		GlobalConfig globalConfig = new GlobalConfig();
		//生成文件的输出目录
		String projectPath = System.getProperty("user.dir");
		globalConfig.setOutputDir(projectPath + "/src/main/java");
		//Author设置作者
		globalConfig.setAuthor("darryl");
		//是否覆盖文件
		globalConfig.setFileOverride(true);
		//生成后打开文件
		globalConfig.setOpen(false);
		// XML 二级缓存
		globalConfig.setEnableCache(false);
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		globalConfig.setControllerName("%sController");
		globalConfig.setServiceName("%sService");
		globalConfig.setServiceImplName("%sServiceImpl");
		globalConfig.setMapperName("%sMapper");
		globalConfig.setXmlName("%sMapper");

		mpg.setGlobalConfig(globalConfig);

		/**
		 * 数据源配置
		 */
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		// 数据库类型,默认MYSQL
		dataSourceConfig.setDbType(DbType.MYSQL);
		//自定义数据类型转换
		dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
		dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/snow?characterEncoding=utf-8&serverTimezone=GMT%2B8" +
				"&useSSL=false");
		dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("你自己的密码");
		mpg.setDataSource(dataSourceConfig);

		/**
		 * 包配置
		 */
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(scanner("模块名"));
		//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
		pc.setParent("com.darryl.snow");
		//dao 包名
		pc.setMapper("dao");
		mpg.setPackageInfo(pc);


		/**
		 * 自定义配置
		 */
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
				Map<String, Object> map = new HashMap<>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};

		/**
		 * 模板
		 */
		//如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";


		/**
		 * 自定义输出配置
		 */
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/"+ pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		/**
		 * 配置模板
		 */
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		// templateConfig.setController();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		/**
		 * 策略配置
		 */
		StrategyConfig strategy = new StrategyConfig();
		//设置命名格式
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setInclude(scanner("表名,多个英文逗号分割").split(","));
		//实体是否为lombok模型（默认 false）
		strategy.setEntityLombokModel(true);
		//生成 @RestController 控制器
		strategy.setRestControllerStyle(true);
		//设置自定义继承的Entity类全称，带包名
		//strategy.setSuperEntityClass("com.darryl.snow.BaseEntity");
		//设置自定义继承的Controller类全称，带包名
		//strategy.setSuperControllerClass("com.darryl.snow.BaseController");
		//设置自定义基础的Entity类，公共字段
		strategy.setSuperEntityColumns("id");
		//驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		//表名前缀
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
