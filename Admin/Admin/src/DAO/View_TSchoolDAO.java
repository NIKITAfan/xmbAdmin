package DAO;

import java.sql.*;
import java.util.ArrayList;
import Model.View_School;

import DBHelp.DBConnection;
public class View_TSchoolDAO {
	
		public ArrayList<View_School> getView_Schools(){
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<View_School> list = new ArrayList<View_School>(); 
	       try {
	           conn= DBConnection.connect();
	           String sql=
	        		  " select tschool, count(tid) as num from teacher group by tschool ;";
	           stmt=conn.prepareStatement(sql);
	           rs=stmt.executeQuery();
	      
				while(rs.next())
				{
					View_School view = new View_School();
					view.setnum(rs.getInt("num"));
					view.setschool(rs.getString("tschool"));
					list.add(view);
				}
				
				conn.close();
				return list; 
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally {
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		
		
}
