package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

// DAO(Data Access Object). 데이터베이스 CRUD.
public enum UserDao {
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(UserDao.class);
	
	private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();
	
	// users 테이블에 insert SQL 문장 & 메서드
	private static final String SQL_INSERT = 
			"insert into users (userid, password, email) values (?, ?, ?)";
	
	public int insert(User user) {
		log.debug("insert({}), user");
		log.debug(SQL_INSERT);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, user.getUserid());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		
		return result;
		
	}
	
	// select() 메서드에서 실행할 SQL:
	private static final String SQL_SELECT_ALL = "select * from users order by id desc";
	
	public List<User> select() {
		log.debug("select()");
        log.debug(SQL_SELECT_ALL);
        
        List<User> list = new ArrayList<>(); // SELECT 결과를 저장할 리스트.
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = fromResultSetToUser(rs);
                list.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return list;
	}
	
	// users 테이블에서 id(PK)로 검색하는 SQL:
		private static final String SQL_SELECT_BY_USERID = "select * from users where userid = ?";
		
		public User select(String userid) {
			log.debug("select()");
	        log.debug(SQL_SELECT_BY_USERID);
	       
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        User user = null;
	        try {
	            conn = ds.getConnection();
	            stmt = conn.prepareStatement(SQL_SELECT_BY_USERID);
	            stmt.setString(1, userid);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                user = fromResultSetToUser(rs);
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            closeResources(conn, stmt, rs);
	        }
	        
	        return user;
		}

	private static final String SQL_SIGN_IN = 
			"select * from users where userid = ? and password = ?";
	
	/**
	 * 로그인할 때 필요한 메서드.
	 * @param user 로그인을 시도한 userid, password를 저장한 객체.
	 * @return 데이터베이스의 users 테이블에서 userid와 password가 일치하는 레코드가 있으면
	 * null이 아닌 User 타입 객체를 리턴. userid 또는 password가 일치하지 않으면 null을 리턴.
	 */
	public User selectByUseridAndPassword(User user) {
		log.debug("selectByUseridAndPassword({})", user);
		log.debug(SQL_SIGN_IN);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User result = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_SIGN_IN);
			stmt.setString(1, user.getUserid());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = fromResultSetToUser(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return result;
	}
	
	// SQL 문자열, 메서드 추가(USERS.POINTS 컬럼 업데이트)
	private static final String SQL_UPDATE_POINTS = 
			"update users set points = points + ? "
			+ "where userid = ?";
	
	public int updatePoints(String userid, int points) {
		log.debug("updatePoints(userid = {}, points = {})", userid, points);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE_POINTS);
			stmt.setInt(1, points);
			stmt.setString(2, userid);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);			
		}
		return result;
	}
	
	private User fromResultSetToUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String userid = rs.getString("userid");
		String password = rs.getString("password");
		String email = rs.getString("email");
		int points = rs.getInt("points");
		
		return User.builder()
				.id(id).userid(userid).password(password).email(email).points(points)
				.build();
	}
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        // DB 자원들을 해제하는 순서: 생성된 순서의 반대로. rs -> stmt -> conn
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }

}
