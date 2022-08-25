package mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	//Mybatis의 SqlSession객체 생성
	private static SqlSessionFactory instance;
	
	private MybatisManager() {
		
	}
	/* 생성자는 보통 public인데 여기선 private처리 했다. 따라서 외부에서 접근이 안됨
	 * 웹프로그램 : 웹은 불특정 다수가 접속해서 서비스받음
	 * MybatisManager m = new MybatisManager();  -> 한사람 요청시 생성,
	 * 또 한사람이 요청하면 계속 만들어야 한다. 
	 * 그렇게 되면 메모리가 늘어나고 결국은 서버가 다운되는 현상이 일어날 수 있다.
	 * 다수의 인스턴스 생성을 막고 하나의 인스턴스만 생성시켜 처리한다.
	 * 즉, 이러한 프로그래밍 기법을 싱글톤 패턴 기법이라 한다.
	 * 
	 * new해서 접근 안되기 때문에 getInstance()메소드를 통해 우회 접근한다.
	 *  
	 */

	public static SqlSessionFactory getInstance() {
		Reader reader = null;
		try {
			//getResourceAsReader()는 Java Resource의 src의 xml을 읽어들이는 메소드
			reader = Resources.getResourceAsReader("config/sqlMapConfig.xml");
			instance = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return instance;
	}

}
