package com.yb.control;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yb.bean.CoreProfile;
import com.yb.bean.Entity;
import com.yb.bean.IntAll;
import com.yb.bean.Material;
import com.yb.bean.PartsMaster;
import com.yb.bean.PartsSlave;
import com.yb.bean.ProfileInventoryStatistics;
import com.yb.bean.ProfileMaterialRemainingStock;
import com.yb.bean.ProfileOptimizationList;
import com.yb.utils.Utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExecutorsJdbc {
	/*private static final String dbUrl = "jdbc:mysql://127.0.0.1/esapp1";
	private static final String dbUsername = "root"; // 数据库用户名
	private static final String dbPassword = "tiger";// 数据库用户密码
	*/
	private static final String dbUrl = "jdbc:sqlserver://192.168.43.211;DatabaseName=ESApp1;integratedSecurity=false";// 数据URL
	private static final String dbUsername = "sa"; // 数据库用户名
	private static final String dbPassword = "123asd,./";// 数据库用户密码
	
	private static Connection con = getConnection();
	private static Set<Entity> ckl = new HashSet<Entity>();
	private static List<ProfileInventoryStatistics> data = new ArrayList<ProfileInventoryStatistics>();// 库存数据存放
	private static List<ProfileMaterialRemainingStock> data1 = new ArrayList<ProfileMaterialRemainingStock>();// 余料库存数据存放
	private static List<Material> mdata = new ArrayList<Material>();
	private static int q = 1;

	private static Statement stmt;
	private static Connection client;

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			stmt = con.createStatement();
			client = con;
		} catch (Exception e) {
			System.out.println("------获取连接失败-------" + e.getMessage());
		}
		return conn;
	}

	public static List<CoreProfile> getProfileOptimizationListCore() {
		List<CoreProfile> list = new ArrayList<CoreProfile>();
		try {
			ResultSet rs = stmt.executeQuery("select * from dbo.型材优化清单_主表");
			while (rs.next()) {
				CoreProfile cp = new CoreProfile(rs.getString(1), rs
						.getString(2), rs.getString(3));
				list.add(cp);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<ProfileOptimizationList> getProfileOptimizationListData(
			String ExcelServerRCID) throws Exception {// 查询型材优化清单_明细
		ResultSet rs = stmt
				.executeQuery("select * from dbo.型材优化清单_明细  where ExcelServerRCID='"
						+ ExcelServerRCID + "'");
		List<ProfileOptimizationList> list = new ArrayList<ProfileOptimizationList>();
		while (rs.next()) {
			ProfileOptimizationList pol = new ProfileOptimizationList(rs
					.getString(1), rs.getString(2), rs.getString(3), rs
					.getString(4), rs.getString(5));
			list.add(pol);
		}
		rs.close();
		return list;
	}

	public static Map<String,String> getModelAll(String ExcelServerRCID) {//获取当前订单的所有型号
		/*String sql = "select a.型号,a.颜色 from  (select 型号,颜色 from 部件之型材清单_明细 where  ExcelServerRCID="
				+ Utils.getSqlValue(ExcelServerRCID) + ") a group by a.型号 ";*/
		String sql="select 型号,颜色 from 部件之型材清单_明细 where 型号 in (select 型号 from 部件之型材清单_明细 group by 型号) and ExcelServerRCID='"+ExcelServerRCID+"'";
		System.out.println(ExcelServerRCID);
		Map<String,String> map=new HashMap<String, String>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	//Utils.getSqlValue(ExcelServerRCID)
	public static List<IntAll> getLengthAll(String ExcelServerRCID,String model){//获取订单内的一个型号的所有长度
		String sql = "select 长,数量 from 部件之型材清单_明细 where ExcelServerRCID='"+ExcelServerRCID+"' and 型号='"+model+"' order by 长 desc;";
		List<IntAll> lengthAll=new ArrayList<IntAll>();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				IntAll all=new IntAll(rs.getInt(1),rs.getInt(2));
				lengthAll.add(all);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lengthAll;
		
	}
	public static List<ProfileInventoryStatistics> getProfileInventoryStatisticsData(// 查询型材库存统计表17年_明细-正料
			String model, String color, String front) {
		List<ProfileInventoryStatistics> list = new ArrayList<ProfileInventoryStatistics>();
		try {
			String sql = "select * from dbo.型材库存统计表17年_明细 where 颜色 like '%"
					+ color + "%' and  型号=" + model;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				double i = 0.0;
				if (rs.getString(7).length() != 0
						&& !"".equals(rs.getString(7))) {
					String[] str = rs.getString(7).split("米");
					i = Double.parseDouble(str[0]) * 1000;
				}
				if ("黑咖".equals(rs.getString(6))
						|| "古铜黄".equals(rs.getString(6))) {
					i = i - 120;
				}
				ProfileInventoryStatistics pis = new ProfileInventoryStatistics(
						front + rs.getString(5), rs.getString(6), i, rs
								.getInt(10));
				list.add(pis);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<ProfileInventoryStatistics> getProfileInventoryStatisticsData(String model,String color) {// 型材库存统计表17年_明细
		model=Utils.getNumbers(model);
		List<ProfileInventoryStatistics> list = new ArrayList<ProfileInventoryStatistics>();
		try {
			String sql = "select * from 型材库存统计表17年_明细  where 库存结余>=1 and 型号='"+model+"' and 颜色='"+color+"' order by 型材规格 desc";
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				double i = 0.0;
				if (rs.getString(7).length() != 0
						&& !"".equals(rs.getString(7))) {
					String[] str = rs.getString(7).split("米");
					i = Double.parseDouble(str[0]) * 1000;
				}
				if ("黑咖".equals(rs.getString(6))
						|| "古铜黄".equals(rs.getString(6))) {
					i = i - 120;
				}
				Material m = new Material(rs.getString(5), rs.getString(6),
						i, rs.getInt(10),"Z");
				q++;
				mdata.add(m);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	public static List<ProfileMaterialRemainingStock> getProfileMaterialRemainingStockData(String model,String color) {// 查询型材余料库存表_明细
		// 余料
		model=Utils.getNumbers(model);
		List<ProfileMaterialRemainingStock> list = new ArrayList<ProfileMaterialRemainingStock>();
		try {
			String sql = "SELECT * from 型材余料库存表_明细   where 库存数量>=1 and 型号   like '%"+model+"%'  and 颜色='"+color+"' order by 长度毫米 desc";
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Material m = new Material(rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6),"Y");
				mdata.add(m);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Material> getMaterial(String model,String color) {// 获取正料余料库存
		mdata.clear();
		ExecutorsJdbc.getProfileInventoryStatisticsData(model,color);// 正料库存
		ExecutorsJdbc.getProfileMaterialRemainingStockData(model,color);// 余料库存
		return mdata;
	}

	public static List<CoreProfile> getPaginationData(int page,
			String customer, String order) {
		List<CoreProfile> list = new ArrayList<CoreProfile>();
		int count = ExecutorsJdbc.getCountData();// 总数量
		int row = 10;// 显示数量
		// System.out.println(page);
		// page=(page*row)>count?page-1:page;//如果显示数量大于数据库数量 就减少一页
		int startwith = page != 1 ? (page * row) : 1;// 计算开始的数据
		startwith = startwith > count ? startwith - row : startwith;// 如果开始数据大于数据库数据就减少10条
		// 否则就用计算出来的
		int endwith = page != 1 ? startwith + row : startwith + row - 1;// 如果不是第一页数据就加一页数据
		// 是的话就从第一条数据开始
		endwith = endwith > count ? count : endwith;// 如果最大数据大于数据库的
		// 就使用最大数据量的数据库数量
		// System.out.println("startwith-"+startwith+"--endwith:"+endwith+"--count:"+count+"--page:"+page+"--row:"+row+"----"+order);
		try {
			String sql = "Select * from (select *,row_number() over(order by 订单号) as id from dbo.型材优化清单_主表 ) as t where t.id>=? and t.id<=?";
			if (customer.length() != 0 || order.length() != 0) {
				sql = "Select * from (select *,row_number() over(order by 订单号) as id from dbo.型材优化清单_主表 ) as t where 客户信息 like '%"
						+ customer + "%' and 订单号 like '%" + order + "%'";
			}
			PreparedStatement pstm = null;
			if (customer.length() == 0 && order.length() == 0) {
				pstm = client.prepareStatement(sql);
				pstm.setInt(1, startwith);
				pstm.setInt(2, endwith);
			} else {
				pstm = client.prepareStatement(sql);
			}
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				CoreProfile pis = new CoreProfile(rs.getString(1), rs
						.getString(2), rs.getString(3));
				list.add(pis);
			}
			rs.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int getCountData() {
		int count = 0;
		try {
			ResultSet rs = stmt
					.executeQuery("select count(*) from dbo.型材优化清单_主表");
			while (rs.next()) {
				count = rs.getInt(1);

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void close() {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String exe(String ExcelServerRCID, String orderid, String path) {
		boolean flag = false;
		String filename = null;
		try {
			List<ProfileOptimizationList> all = getProfileOptimizationListData(ExcelServerRCID);
			filename = path;
			System.out.println(path);
			File file = new File(filename);
			WritableWorkbook outbook = Workbook.createWorkbook(file);
			// 创建正料
			WritableSheet outsheet = outbook.createSheet("零件", 0);
			// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
			Label xh = new Label(0, 0, "型号");
			outsheet.addCell(xh);
			Label cz = new Label(1, 0, "材质");
			outsheet.addCell(cz);
			Label ljmc = new Label(2, 0, "零件名称");
			outsheet.addCell(ljmc);
			Label cd = new Label(3, 0, "长度(mm)");
			outsheet.addCell(cd);
			Label sl = new Label(4, 0, "数量");
			outsheet.addCell(sl);
			// 创建原材料
			WritableSheet excesssheet = outbook.createSheet("原材料", 2);
			Label excexh = new Label(0, 0, "型号");
			excesssheet.addCell(excexh);
			Label excecz = new Label(1, 0, "材质");
			excesssheet.addCell(excecz);
			Label excecd = new Label(2, 0, "长度(mm)");
			excesssheet.addCell(excecd);
			Label excesl = new Label(3, 0, "数量");
			excesssheet.addCell(excesl);
			Label excesy = new Label(4, 0, "优先级");// 优先级 指优先处理
			excesssheet.addCell(excesy);
			int row = 1;// 零件计数
			int row1 = 1;// 原料计数
			ckl.clear();
			// System.out.println(ckl.size()+"--------------");
			for (ProfileOptimizationList pf : all) {
				if (!isChinese(pf.getModel())) {
					Entity t = new Entity();
					t.setModel("'" + pf.getModel() + "'");
					t.setColor("'" + pf.getTexture() + "'");
					ckl.add(t);
				}
				Label a1 = new Label(0, row, pf.getModel());
				outsheet.addCell(a1);
				Label a2 = new Label(1, row, pf.getTexture());
				outsheet.addCell(a2);
				Label a3 = new Label(2, row, pf.getPartname());
				outsheet.addCell(a3);
				Label a4 = new Label(3, row, pf.getLength());
				outsheet.addCell(a4);
				Label a5 = new Label(4, row, "0".equals(pf.getNumber()) ? "100"
						: pf.getNumber());
				outsheet.addCell(a5);
				row++;
			}
			HandleOther();
			for (ProfileInventoryStatistics pis : data) {// 型材
				// System.out.println(pis.getBalance()+"----"+("0".equals(pis.getBalance())?"100":pis.getBalance()));
				Label a1 = new Label(0, row1, pis.getModel());
				excesssheet.addCell(a1);
				Label a2 = new Label(1, row1, pis.getTextuer());
				excesssheet.addCell(a2);
				Label a3 = new Label(2, row1, pis.getLength() + "");
				excesssheet.addCell(a3);
				Label a4 = new Label(3, row1, "100");// 数量
				excesssheet.addCell(a4);
				Label a5 = new Label(4, row1, "1");// 优先级 指优先处理
				excesssheet.addCell(a5);
				row1++;
			}
			for (ProfileMaterialRemainingStock pmrs : data1) {// 余料
				Label a1 = new Label(0, row1, pmrs.getModel());
				excesssheet.addCell(a1);
				Label a2 = new Label(1, row1, pmrs.getTextuer());
				excesssheet.addCell(a2);
				Label a3 = new Label(2, row1, pmrs.getLength() + "");
				excesssheet.addCell(a3);
				Label a4 = new Label(3, row1, pmrs.getBalance() + "");
				excesssheet.addCell(a4);
				Label a5 = new Label(4, row1, "2");// 优先级 指优先处理
				excesssheet.addCell(a5);
				row1++;
			}
			outbook.write();
			outbook.close();
			flag = true;
		} catch (Exception e) {
			System.out.println("文件创建失败" + e.getMessage());
		}
		return flag ? filename : "";
	}

	public static void main(String[] args) throws SQLException {
		/**
		 * List<ProfileInventoryStatistics> list =
		 * getProfileInventoryStatisticsData(); for (ProfileInventoryStatistics
		 * s : list) { System.out.println(s.toString()); }
		 */
		/*
		 * List<ProfileMaterialRemainingStock> list =
		 * getProfileMaterialRemainingStockData(); for
		 * (ProfileMaterialRemainingStock pmrs : list) {
		 * System.out.println(pmrs.toString()); }
		 */
		/*
		 * List<PartsMaster> list = getPartsMasters(); for (PartsMaster p :
		 * list) { System.out.println(p.toString()); }
		 */
		/*
		 * List<PartsSlave> list = getPartsSlave(); for (PartsSlave ps : list) {
		 * System.out.println(ps.toString()); }
		 */

	}

	public static List<PartsMaster> getPartsMasters() {// 部件之型材清单_主表
		List<PartsMaster> list = new ArrayList<PartsMaster>();
		try {
			String sql = "SELECT * from 部件之型材清单_主表 ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PartsMaster pis = new PartsMaster(rs.getString(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getString(5), rs.getString(6), rs.getString(7), rs
						.getString(8), rs.getString(9), rs.getString(10), rs
						.getString(11), rs.getString(12), rs.getString(13), rs
						.getString(14));
				list.add(pis);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<PartsSlave> getPartsSlave(String ExcelServerRCID) {// 部件之型材清单_明细
		List<PartsSlave> list = new ArrayList<PartsSlave>();
		try {
			String sql = "SELECT * from 部件之型材清单_明细  where ExcelServerRCID="
					+ Utils.getSqlValue(ExcelServerRCID);
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PartsSlave pis = new PartsSlave(rs.getString(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getString(5), rs.getInt(6), rs.getInt(7),
						rs.getInt(8), rs.getString(9), rs.getInt(10), rs
								.getDouble(11), rs.getString(12), rs
								.getString(13), rs.getString(14), rs
								.getString(15), rs.getString(16), rs
								.getString(17), rs.getString(18), rs
								.getString(19), rs.getString(20), rs
								.getString(21));
				list.add(pis);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean iisNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void HandleOther() {

		for (Entity e : ckl) {
			// System.out.println(e.toString());
			String model = e.getModel();
			if (!iisNumeric(model.replaceAll("'", ""))) {
				// System.out.println(model + "不是数字");
				String front = model.substring(1, 4);
				String back = model.substring(4, model.length());
				List<ProfileInventoryStatistics> list2 = getProfileInventoryStatisticsData(
						"'" + back, e.getColor().replaceAll("'", ""), front);
				for (ProfileInventoryStatistics pis : list2) {
					// System.out.println(pis.toString());
					data.add(pis);
				}

			} else {
				// System.out.println(model + "是数字");
				List<ProfileInventoryStatistics> list2 = getProfileInventoryStatisticsData(
						model, e.getColor().replaceAll("'", ""), "");
				for (ProfileInventoryStatistics pis : list2) {
					data.add(pis);
				}
			}
			List<ProfileMaterialRemainingStock> list = getProfileMaterialRemainingStockData(
					e.getModel(), e.getColor());
			for (ProfileMaterialRemainingStock pmrs : list) {
				// System.out.println(pmrs.toString());
				data1.add(pmrs);
			}

		}

		// }

	}

	public void other() {
		try {
			List<ProfileOptimizationList> all = getProfileOptimizationListData("");
			File file = new File("F:\\S171019002.xls");
			if (file.renameTo(file)) {
				WritableWorkbook outbook = Workbook.createWorkbook(file);
				// 创建正料
				WritableSheet outsheet = outbook.createSheet("零件", 0);
				// 创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
				Label xh = new Label(0, 0, "型号");
				outsheet.addCell(xh);
				Label cz = new Label(1, 0, "材质");
				outsheet.addCell(cz);
				Label ljmc = new Label(2, 0, "零件名称");
				outsheet.addCell(ljmc);
				Label cd = new Label(3, 0, "长度(mm)");
				outsheet.addCell(cd);
				Label sl = new Label(4, 0, "数量");
				outsheet.addCell(sl);
				// 创建原材料
				WritableSheet excesssheet = outbook.createSheet("原材料", 2);
				Label excexh = new Label(0, 0, "型号");
				excesssheet.addCell(excexh);
				Label excecz = new Label(1, 0, "材质");
				excesssheet.addCell(excecz);
				Label excecd = new Label(2, 0, "长度(mm)");
				excesssheet.addCell(excecd);
				Label excesl = new Label(3, 0, "数量");
				excesssheet.addCell(excesl);
				Label yxj = new Label(3, 0, "优先级");
				excesssheet.addCell(yxj);
				int row = 1;// 零件计数
				int row1 = 1;// 原料计数
				for (ProfileOptimizationList pf : all) {
					if (!isChinese(pf.getModel())) {
						Entity t = new Entity();
						t.setModel("'" + pf.getModel() + "'");
						t.setColor("'" + pf.getTexture() + "'");
						ckl.add(t);
					}
					Label a1 = new Label(0, row, pf.getModel());
					outsheet.addCell(a1);
					Label a2 = new Label(1, row, pf.getTexture());
					outsheet.addCell(a2);
					Label a3 = new Label(2, row, pf.getPartname());
					outsheet.addCell(a3);
					Label a4 = new Label(3, row, pf.getLength());
					outsheet.addCell(a4);
					Label a5 = new Label(4, row, pf.getNumber());
					outsheet.addCell(a5);
					row++;
				}
				HandleOther();
				for (ProfileInventoryStatistics pis : data) {
					Label a1 = new Label(0, row1, pis.getModel());
					excesssheet.addCell(a1);
					Label a2 = new Label(1, row1, pis.getTextuer());
					excesssheet.addCell(a2);
					Label a3 = new Label(2, row1, pis.getLength() + "");
					excesssheet.addCell(a3);
					Label a4 = new Label(3, row1, pis.getBalance() + "");
					excesssheet.addCell(a4);
					Label a5 = new Label(4, row1, "1");
					excesssheet.addCell(a5);

					row1++;
				}
				for (ProfileMaterialRemainingStock pmrs : data1) {
					Label a1 = new Label(0, row1, pmrs.getModel());
					excesssheet.addCell(a1);
					Label a2 = new Label(1, row1, pmrs.getTextuer());
					excesssheet.addCell(a2);
					Label a3 = new Label(2, row1, pmrs.getLength() + "");
					excesssheet.addCell(a3);
					Label a4 = new Label(3, row1, pmrs.getBalance() + "");
					excesssheet.addCell(a4);
					Label a5 = new Label(4, row1, "2");
					excesssheet.addCell(a5);
					row1++;
				}
				for (Entity e : ckl) {
					// System.out.println(e.toString());
				}
				outbook.write();
				outbook.close();
			} else {
				throw new Exception("程序已占用！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 判断一个字符是否是中文
	public static boolean isChinese(char c) {
		return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
	}

	// 判断一个字符串是否含有中文
	public static boolean isChinese(String str) {
		if (str == null)
			return false;
		for (char c : str.toCharArray()) {
			if (isChinese(c))
				return true;// 有一个中文字符就返回
		}
		return false;
	}
}
