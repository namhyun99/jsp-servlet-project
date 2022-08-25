package config;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DB {
	public static Connection getConn() {
		// context.xml에 설정된 dbcp에서 커넥션을 가져옴
		DataSource ds = null; // javax.sql
		Connection conn = null;

		try {
			// context.xml을 분석하는 객체
			Context ctx = new InitialContext(); // javax.naming
			// context.xml의 Resource 태그 검색, name="oraDB"와 매칭
			// oraDB 와 context.xml에 있는 name과 동일하게 해야한다.
			ds = (DataSource) ctx.lookup("java:comp/env/oraDB");
			conn = ds.getConnection(); // 커넥션 할당받음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn; //커넥션 리턴
	}
}
