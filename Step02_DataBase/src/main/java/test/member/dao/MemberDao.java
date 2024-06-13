package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
	//2.
	private static MemberDao dao;
	
	//1. 
	private MemberDao() {}
	
	//3.
	public static MemberDao getInstance() {
		//서버 시작후 최초 요청이라면
		if(dao==null) {
			//객체를 생성해서 static 필드에 저장해 놓는다.
			dao=new MemberDao();
		}
		//필드에 저장된 참조값 리턴해주기
		return dao;
	}
	
	//회원 한명의 정보를 수정하고 성공 여부를 리턴하는 메소드
	public boolean update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = "update member"
					+ " set name=?, addr=?"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
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
	public MemberDto getData(int num) {
		MemberDto dto=null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성 
			String sql = "select name, addr"
					+ " from member"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto=new MemberDto();
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
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
	
	//회원정보를 삭제하고 성공여부를 리턴하는 메소드
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = "delete from member"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setInt(1, num);
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
	
	
	//회원 정보를 DB 에 저장하고 작업 성공 여부를 리턴하는 메소드
	public boolean insert(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			//Connection Pool 로 부터 Connection 객체 하나 가져오기 
			conn = new DbcpBean().getConn();
			//실행할 sql 문 작성
			String sql = "insert into member"
					+ " (num, name, addr)"
					+ " values(member_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할 내용이 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
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

	
	
	
	//회원 목록을 리턴하는 메소드 
	public List<MemberDto> getList(){
		//회원정보를 누적할 ArrayList 객체 생성
		List<MemberDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//Connection 객체의 참조값 
			conn=new DbcpBean().getConn();
			String sql="SELECT num, name, addr"
					+ " FROM member "
					+ " ORDER BY num ASC";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				//select 된 회원 정보를 MemberDto 객체에 담고 
				MemberDto dto=new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
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
}











