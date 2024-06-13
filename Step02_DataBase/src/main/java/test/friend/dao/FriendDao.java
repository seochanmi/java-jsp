package test.friend.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.friend.dto.FriendDto;
import test.friend.util.DBConnection;
import test.util.DbcpBean;

public class FriendDao {
	
	//회원 한명의 정보를 수정하고 성공 여부를 리턴하는 메소드
	public boolean update(FriendDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			
			conn = new DbcpBean().getConn();
			
			String sql = "update friends"
					+ " set name=?, phone=?"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setInt(3, dto.getNum());
			// update 문 실행하고 변화된 rowCount 를 리턴 받는다.
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	//회원 한명의 정보를 리턴하는 메소드
	public FriendDto getData(int num) {
		FriendDto dto=null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = new DbcpBean().getConn();
			
			String sql = "select name, phone"
					+ " from friends"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto=new FriendDto();
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return dto;
	}
	
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			String sql = "delete from friends"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public boolean insert(FriendDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			
			conn = new DbcpBean().getConn();
			String sql = "insert into friends"
					+ " (num, name, phone)"
					+ " values(member_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
		
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (rowCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
	public List<FriendDto> getList(){
		
		List<FriendDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		
			conn=new DbcpBean().getConn();
			String sql="SELECT num, name, phone"
					+ " FROM friends "
					+ " ORDER BY num ASC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				//select 된 회원 정보를 MemberDto 객체에 담고 
				FriendDto dto=new FriendDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				//MemberDto 객체를 List 에 누적 시킨다.
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		
		return list;
	}


    public List<FriendDto> getAllFriends() {
        List<FriendDto> friends = new ArrayList<>();
        String query = "SELECT * FROM friends";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                friends.add(new FriendDto(rs.getInt("num"), rs.getString("name"), rs.getString("phone")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friends;
    }
}











