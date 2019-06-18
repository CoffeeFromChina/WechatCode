package com.xuuuuu.jpademo.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity     //声明这个类对应了一个数据库表
@Table(name = "category")   //实体类对应的数据库表格名
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category implements Serializable {

	@Id //声明了实体唯一标识对应的属性。
	//https://blog.csdn.net/u012493207/article/details/50846616
	@GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略--自增长
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

}
