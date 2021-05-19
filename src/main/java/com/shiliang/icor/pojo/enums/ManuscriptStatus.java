package com.shiliang.icor.pojo.enums;


/**
 * 稿件状态枚举(
 *
 * @author gaopengf
 * @date 2018-04-20 09:19:32
 */
public enum ManuscriptStatus  {
	//状态
	Saved(1, "待处理", "稿件状态"),
	Committed(2, "已提交", "稿件状态"),
	Approving(3, "审批中", "稿件状态"),
	Approved(4, "审批通过", "稿件状态"),
	Disapproved(5, "审批不通过", "稿件状态"),
	Rejected(6, "已驳回", "稿件状态");
//	Finished("07", "已完成", "稿件状态");

	private Integer code;
	private String name;
	private String description;

	ManuscriptStatus(Integer code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}


	public Integer getCode() {
		return code;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}
}
