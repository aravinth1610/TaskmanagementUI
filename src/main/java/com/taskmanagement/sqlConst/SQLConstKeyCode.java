package com.taskmanagement.sqlConst;

public class SQLConstKeyCode {

	public static final String findAllByRole="SELECT u.name,u.gmail,v.role FROM tmuser u JOIN tmauthority v ON u.userid=v.roleid WHERE v.role=?1";

	public static final String listNameByRole="SELECT u.name FROM tmuser u JOIN tmauthority v ON u.userid=v.roleid WHERE v.role=?1";

	
}
