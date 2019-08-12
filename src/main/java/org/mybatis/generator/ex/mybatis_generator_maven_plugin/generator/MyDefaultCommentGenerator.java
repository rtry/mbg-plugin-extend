/**    
 * 文件名：MyDefaultCommentGenerator.java    
 *    
 * 版本信息：    
 * 日期：2018年4月24日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import org.apache.maven.plugin.logging.Log;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.Enums.ExampleProperty;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * 类名称：MyDefaultCommentGenerator <br>
 * 类描述: 自定义生成注解方式 <br>
 * 创建人：felicity <br>
 * 创建时间：2018年4月24日 下午6:04:23 <br>
 * 修改人：felicity <br>
 * 修改时间：2018年4月24日 下午6:04:23 <br>
 * 修改备注:
 * 
 * @version
 * @see
 */
public class MyDefaultCommentGenerator implements CommentGenerator {

	// ======================================================
	// 注解生成器，实现 接口：CommentGenerator
	// 来替换默认的实现 DefaultCommentGenerator
	// ======================================================

	private Properties properties;

	private boolean suppressDate;
	private boolean suppressAllComments;
	private String currentDateStr;

	/** If suppressAllComments is true, this option is ignored. */
	@SuppressWarnings("unused")
	private boolean addRemarkComments;

	private SimpleDateFormat dateFormat;

	private Log log;

	public void setLog(Log log) {
		this.log = log;
	}

	public MyDefaultCommentGenerator() {
		super();
		properties = new Properties();
		suppressDate = false;
		suppressAllComments = false;
		addRemarkComments = false;
		currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// add no file level comments by default
	}

	/**
	 * Adds a suitable comment to warn users that the element was generated, and
	 * when it was generated.
	 *
	 * @param xmlElement
	 *        the xml element
	 */
	@Override
	public void addComment(XmlElement xmlElement) {

		// if (suppressAllComments) {
		// return;
		// }
		//
		//		xmlElement.addElement(new TextElement("<!--")); //$NON-NLS-1$
		//
		// StringBuilder sb = new StringBuilder();
		//		sb.append("  WARNING - "); //$NON-NLS-1$
		// sb.append(MergeConstants.NEW_ELEMENT_TAG);
		// xmlElement.addElement(new TextElement(sb.toString()));
		// xmlElement.addElement(new TextElement(
		//				"  This element is automatically generated by MyBatis Generator, do not modify.")); //$NON-NLS-1$
		//
		// String s = getDateString();
		// if (s != null) {
		// sb.setLength(0);
		//			sb.append("  This element was generated on "); //$NON-NLS-1$
		// sb.append(s);
		// sb.append('.');
		// xmlElement.addElement(new TextElement(sb.toString()));
		// }
		//
		//		xmlElement.addElement(new TextElement("-->")); //$NON-NLS-1$
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		log.toString();
		// add no document level comments by default
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);

		suppressDate = isTrue(properties
				.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

		suppressAllComments = isTrue(properties
				.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

		addRemarkComments = isTrue(properties
				.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));

		String dateFormatString = properties
				.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
		if (StringUtility.stringHasValue(dateFormatString)) {
			dateFormat = new SimpleDateFormat(dateFormatString);
		}
	}

	/**
	 * This method adds the custom javadoc tag for. You may do nothing if you do
	 * not wish to include the Javadoc tag - however, if you do not include the
	 * Javadoc tag then the Java merge capability of the eclipse plugin will
	 * break.
	 *
	 * @param javaElement
	 *        the java element
	 * @param markAsDoNotDelete
	 *        the mark as do not delete
	 */
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		//		javaElement.addJavaDocLine(" *"); //$NON-NLS-1$
		// StringBuilder sb = new StringBuilder();
		//		sb.append(" * "); //$NON-NLS-1$
		// sb.append(MergeConstants.NEW_ELEMENT_TAG);
		// if (markAsDoNotDelete) {
		//			sb.append(" do_not_delete_during_merge"); //$NON-NLS-1$
		// }
		// String s = getDateString();
		// if (s != null) {
		// sb.append(' ');
		// sb.append(s);
		// }
		// javaElement.addJavaDocLine(sb.toString());
	}

	/**
	 * Returns a formated date string to include in the Javadoc tag and XML
	 * comments. You may return null if you do not want the date in these
	 * documentation elements.
	 * 
	 * @return a string representing the current timestamp, or null
	 */
	protected String getDateString() {
		if (suppressDate) {
			return null;
		} else if (dateFormat != null) {
			return dateFormat.format(new Date());
		} else {
			return new Date().toString();
		}
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString(); //$NON-NLS-1$
		method.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source field: " //$NON-NLS-1$
				+ introspectedTable.getFullyQualifiedTable().toString() + "." //$NON-NLS-1$
				+ introspectedColumn.getActualColumnName();
		method.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString(); //$NON-NLS-1$
		field.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source field: " //$NON-NLS-1$
				+ introspectedTable.getFullyQualifiedTable().toString() + "." //$NON-NLS-1$
				+ introspectedColumn.getActualColumnName();
		field.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString(); //$NON-NLS-1$
		innerClass.addAnnotation(getGeneratedAnnotation(comment));
	}

	private String getGeneratedAnnotation(String comment) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("@Generated("); //$NON-NLS-1$
		if (suppressAllComments) {
			buffer.append('\"');
		} else {
			buffer.append("value=\""); //$NON-NLS-1$
		}

		buffer.append(MyBatisGenerator.class.getName());
		buffer.append('\"');

		if (!suppressDate && !suppressAllComments) {
			buffer.append(", date=\""); //$NON-NLS-1$
			buffer.append(DatatypeConverter.printDateTime(Calendar.getInstance()));
			buffer.append('\"');
		}

		if (!suppressAllComments) {
			buffer.append(", comments=\""); //$NON-NLS-1$
			buffer.append(comment);
			buffer.append('\"');
		}

		buffer.append(')');
		return buffer.toString();
	}

	// -------------------------------------------------------------------------------------------//

	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		// if (suppressAllComments) {
		// return;
		// }
		// StringBuilder sb = new StringBuilder();
		// innerEnum.addJavaDocLine("/**");
		// sb.append(" * ");
		// sb.append(introspectedTable.getFullyQualifiedTable());
		// innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
		// innerEnum.addJavaDocLine(" */");
	}

	/**
	 * 实体类，变量注解
	 * */
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// log.info("生成:" + field.getName() + "的注解");
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		field.addJavaDocLine(sb.toString().replace("\n", " "));
		field.addJavaDocLine(" */");
	}

	/**
	 * Example对象，变量注解
	 * */
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		String name = field.getName();
		// log.info("生成Example对象:" + name + "的注解");
		if (suppressAllComments) {
			return;
		}
		ExampleProperty p = ExampleProperty.get(name);
		if (p != null) {
			StringBuilder sb = new StringBuilder();
			field.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(p.getDes());
			field.addJavaDocLine(sb.toString().replace("\n", " "));
			field.addJavaDocLine(" */");
		}
	}

	/**
	 * 生成Mapper对象方法的注解
	 * */
	private static final String xh = " * ";

	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		String name = method.getName();
		// log.info("生成Mapper对象方法:" + name + "的注解");
		if (suppressAllComments) {
			return;
		}
		String[] descs = BICommentConstant.getComments(name);
		method.addJavaDocLine("/**");
		for (String des : descs) {
			method.addJavaDocLine(xh.concat(des.toString().replace("\n", " ")));
		}
		method.addJavaDocLine(" */");
	}

	/**
	 * 实体类，get方法注解
	 * */
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(method.getName() + ": 获取" + introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());
		sb.append(" ");
		// sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		method.addJavaDocLine(" */");
	}

	/**
	 * 实体类，set方法注解
	 * */
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(method.getName() + ": 设置" + introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		Parameter parm = method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		sb.append(" ");
		// sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		method.addJavaDocLine(" */");
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		// if (suppressAllComments) {
		// return;
		// }
		// StringBuilder sb = new StringBuilder();
		// innerClass.addJavaDocLine("/**");
		// sb.append(" * ");
		// sb.append(introspectedTable.getFullyQualifiedTable());
		// sb.append(" ");
		// sb.append(getDateString());
		// innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		// innerClass.addJavaDocLine(" */");
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable,
			boolean markAsDoNotDelete) {
		//
		// if (suppressAllComments) {
		// return;
		// }
		// StringBuilder sb = new StringBuilder();
		// innerClass.addJavaDocLine("/**");
		// sb.append(" * ");
		// sb.append(introspectedTable.getFullyQualifiedTable());
		// innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		// sb.setLength(0);
		// sb.append(" * @author:  MBG(mybatis generator)");
		// sb.append(" ");
		// sb.append(currentDateStr);
		// innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addModelClassComment(TopLevelClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * 类描述: ");
		sb.append(introspectedTable.getFullyQualifiedTable() + "表的实体类");
		innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * 创建者: MBG(mybatis generator ex)");
		innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * 创建时间: ");
		sb.append(currentDateStr);
		innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * @version ");
		innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		innerClass.addJavaDocLine(" */");
	}
}
