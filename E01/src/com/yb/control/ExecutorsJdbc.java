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
	private static final String dbUsername = "root"; // ���ݿ��û���
	private static final String dbPassword = "tiger";// ���ݿ��û�����
	*/
	private static final String dbUrl = "jdbc:sqlserver://192.168.43.211;DatabaseName=ESApp1;integratedSecurity=false";// ����URL
	private static final String dbUsername = "sa"; // ���ݿ��û���
	private static final String dbPassword = "123asd,./";// ���ݿ��û�����
	
	private static Connection con = getConnection();
	private static Set<Entity> ckl = new HashSet<Entity>();
	private static List<ProfileInventoryStatistics> data = new ArrayList<ProfileInventoryStatistics>();// ������ݴ��
	private static List<ProfileMaterialRemainingStock> data1 = new ArrayList<ProfileMaterialRemainingStock>();// ���Ͽ�����ݴ��
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
			System.out.println("------��ȡ����ʧ��-------" + e.getMessage());
		}
		return conn;
	}

	public static List<CoreProfile> getProfileOptimizationListCore() {
		List<CoreProfile> list = new ArrayList<CoreProfile>();
		try {
			ResultSet rs = stmt.executeQuery("select * from dbo.�Ͳ��Ż��嵥_����");
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
			String ExcelServerRCID) throws Exception {// ��ѯ�Ͳ��Ż��嵥_��ϸ
		ResultSet rs = stmt
				.executeQuery("select * from dbo.�Ͳ��Ż��嵥_��ϸ  where ExcelServerRCID='"
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

	public static Map<String,String> getModelAll(String ExcelServerRCID) {//��ȡ��ǰ�����������ͺ�
		/*String sql = "select a.�ͺ�,a.��ɫ from  (select �ͺ�,��ɫ from ����֮�Ͳ��嵥_��ϸ where  ExcelServerRCID="
				+ Utils.getSqlValue(ExcelServerRCID) + ") a group by a.�ͺ� ";*/
		String sql="select �ͺ�,��ɫ from ����֮�Ͳ��嵥_��ϸ where �ͺ� in (select �ͺ� from ����֮�Ͳ��嵥_��ϸ group by �ͺ�) and ExcelServerRCID='"+ExcelServerRCID+"'";
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
	public static List<IntAll> getLengthAll(String ExcelServerRCID,String model){//��ȡ�����ڵ�һ���ͺŵ����г���
		String sql = "select ��,���� from ����֮�Ͳ��嵥_��ϸ where ExcelServerRCID='"+ExcelServerRCID+"' and �ͺ�='"+model+"' order by �� desc;";
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
	public static List<ProfileInventoryStatistics> getProfileInventoryStatisticsData(// ��ѯ�ͲĿ��ͳ�Ʊ�17��_��ϸ-����
			String model, String color, String front) {
		List<ProfileInventoryStatistics> list = new ArrayList<ProfileInventoryStatistics>();
		try {
			String sql = "select * from dbo.�ͲĿ��ͳ�Ʊ�17��_��ϸ where ��ɫ like '%"
					+ color + "%' and  �ͺ�=" + model;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				double i = 0.0;
				if (rs.getString(7).length() != 0
						&& !"".equals(rs.getString(7))) {
					String[] str = rs.getString(7).split("��");
					i = Double.parseDouble(str[0]) * 1000;
				}
				if ("�ڿ�".equals(rs.getString(6))
						|| "��ͭ��".equals(rs.getString(6))) {
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

	public static List<ProfileInventoryStatistics> getProfileInventoryStatisticsData(String model,String color) {// �ͲĿ��ͳ�Ʊ�17��_��ϸ
		model=Utils.getNumbers(model);
		List<ProfileInventoryStatistics> list = new ArrayList<ProfileInventoryStatistics>();
		try {
			String sql = "select * from �ͲĿ��ͳ�Ʊ�17��_��ϸ  where ������>=1 and �ͺ�='"+model+"' and ��ɫ='"+color+"' order by �ͲĹ�� desc";
			//System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				double i = 0.0;
				if (rs.getString(7).length() != 0
						&& !"".equals(rs.getString(7))) {
					String[] str = rs.getString(7).split("��");
					i = Double.parseDouble(str[0]) * 1000;
				}
				if ("�ڿ�".equals(rs.getString(6))
						|| "��ͭ��".equals(rs.getString(6))) {
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


	public static List<ProfileMaterialRemainingStock> getProfileMaterialRemainingStockData(String model,String color) {// ��ѯ�Ͳ����Ͽ���_��ϸ
		// ����
		model=Utils.getNumbers(model);
		List<ProfileMaterialRemainingStock> list = new ArrayList<ProfileMaterialRemainingStock>();
		try {
			String sql = "SELECT * from �Ͳ����Ͽ���_��ϸ   where �������>=1 and �ͺ�   like '%"+model+"%'  and ��ɫ='"+color+"' order by ���Ⱥ��� desc";
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

	public static List<Material> getMaterial(String model,String color) {// ��ȡ�������Ͽ��
		mdata.clear();
		ExecutorsJdbc.getProfileInventoryStatisticsData(model,color);// ���Ͽ��
		ExecutorsJdbc.getProfileMaterialRemainingStockData(model,color);// ���Ͽ��
		return mdata;
	}

	public static List<CoreProfile> getPaginationData(int page,
			String customer, String order) {
		List<CoreProfile> list = new ArrayList<CoreProfile>();
		int count = ExecutorsJdbc.getCountData();// ������
		int row = 10;// ��ʾ����
		// System.out.println(page);
		// page=(page*row)>count?page-1:page;//�����ʾ�����������ݿ����� �ͼ���һҳ
		int startwith = page != 1 ? (page * row) : 1;// ���㿪ʼ������
		startwith = startwith > count ? startwith - row : startwith;// �����ʼ���ݴ������ݿ����ݾͼ���10��
		// ������ü��������
		int endwith = page != 1 ? startwith + row : startwith + row - 1;// ������ǵ�һҳ���ݾͼ�һҳ����
		// �ǵĻ��ʹӵ�һ�����ݿ�ʼ
		endwith = endwith > count ? count : endwith;// ���������ݴ������ݿ��
		// ��ʹ����������������ݿ�����
		// System.out.println("startwith-"+startwith+"--endwith:"+endwith+"--count:"+count+"--page:"+page+"--row:"+row+"----"+order);
		try {
			String sql = "Select * from (select *,row_number() over(order by ������) as id from dbo.�Ͳ��Ż��嵥_���� ) as t where t.id>=? and t.id<=?";
			if (customer.length() != 0 || order.length() != 0) {
				sql = "Select * from (select *,row_number() over(order by ������) as id from dbo.�Ͳ��Ż��嵥_���� ) as t where �ͻ���Ϣ like '%"
						+ customer + "%' and ������ like '%" + order + "%'";
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
					.executeQuery("select count(*) from dbo.�Ͳ��Ż��嵥_����");
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
			// ��������
			WritableSheet outsheet = outbook.createSheet("���", 0);
			// ����Ҫ��ʾ������,����һ����Ԫ�񣬵�һ������Ϊ�����꣬�ڶ�������Ϊ�����꣬����������Ϊ����
			Label xh = new Label(0, 0, "�ͺ�");
			outsheet.addCell(xh);
			Label cz = new Label(1, 0, "����");
			outsheet.addCell(cz);
			Label ljmc = new Label(2, 0, "�������");
			outsheet.addCell(ljmc);
			Label cd = new Label(3, 0, "����(mm)");
			outsheet.addCell(cd);
			Label sl = new Label(4, 0, "����");
			outsheet.addCell(sl);
			// ����ԭ����
			WritableSheet excesssheet = outbook.createSheet("ԭ����", 2);
			Label excexh = new Label(0, 0, "�ͺ�");
			excesssheet.addCell(excexh);
			Label excecz = new Label(1, 0, "����");
			excesssheet.addCell(excecz);
			Label excecd = new Label(2, 0, "����(mm)");
			excesssheet.addCell(excecd);
			Label excesl = new Label(3, 0, "����");
			excesssheet.addCell(excesl);
			Label excesy = new Label(4, 0, "���ȼ�");// ���ȼ� ָ���ȴ���
			excesssheet.addCell(excesy);
			int row = 1;// �������
			int row1 = 1;// ԭ�ϼ���
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
			for (ProfileInventoryStatistics pis : data) {// �Ͳ�
				// System.out.println(pis.getBalance()+"----"+("0".equals(pis.getBalance())?"100":pis.getBalance()));
				Label a1 = new Label(0, row1, pis.getModel());
				excesssheet.addCell(a1);
				Label a2 = new Label(1, row1, pis.getTextuer());
				excesssheet.addCell(a2);
				Label a3 = new Label(2, row1, pis.getLength() + "");
				excesssheet.addCell(a3);
				Label a4 = new Label(3, row1, "100");// ����
				excesssheet.addCell(a4);
				Label a5 = new Label(4, row1, "1");// ���ȼ� ָ���ȴ���
				excesssheet.addCell(a5);
				row1++;
			}
			for (ProfileMaterialRemainingStock pmrs : data1) {// ����
				Label a1 = new Label(0, row1, pmrs.getModel());
				excesssheet.addCell(a1);
				Label a2 = new Label(1, row1, pmrs.getTextuer());
				excesssheet.addCell(a2);
				Label a3 = new Label(2, row1, pmrs.getLength() + "");
				excesssheet.addCell(a3);
				Label a4 = new Label(3, row1, pmrs.getBalance() + "");
				excesssheet.addCell(a4);
				Label a5 = new Label(4, row1, "2");// ���ȼ� ָ���ȴ���
				excesssheet.addCell(a5);
				row1++;
			}
			outbook.write();
			outbook.close();
			flag = true;
		} catch (Exception e) {
			System.out.println("�ļ�����ʧ��" + e.getMessage());
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

	public static List<PartsMaster> getPartsMasters() {// ����֮�Ͳ��嵥_����
		List<PartsMaster> list = new ArrayList<PartsMaster>();
		try {
			String sql = "SELECT * from ����֮�Ͳ��嵥_���� ";
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

	public static List<PartsSlave> getPartsSlave(String ExcelServerRCID) {// ����֮�Ͳ��嵥_��ϸ
		List<PartsSlave> list = new ArrayList<PartsSlave>();
		try {
			String sql = "SELECT * from ����֮�Ͳ��嵥_��ϸ  where ExcelServerRCID="
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
				// System.out.println(model + "��������");
				String front = model.substring(1, 4);
				String back = model.substring(4, model.length());
				List<ProfileInventoryStatistics> list2 = getProfileInventoryStatisticsData(
						"'" + back, e.getColor().replaceAll("'", ""), front);
				for (ProfileInventoryStatistics pis : list2) {
					// System.out.println(pis.toString());
					data.add(pis);
				}

			} else {
				// System.out.println(model + "������");
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
				// ��������
				WritableSheet outsheet = outbook.createSheet("���", 0);
				// ����Ҫ��ʾ������,����һ����Ԫ�񣬵�һ������Ϊ�����꣬�ڶ�������Ϊ�����꣬����������Ϊ����
				Label xh = new Label(0, 0, "�ͺ�");
				outsheet.addCell(xh);
				Label cz = new Label(1, 0, "����");
				outsheet.addCell(cz);
				Label ljmc = new Label(2, 0, "�������");
				outsheet.addCell(ljmc);
				Label cd = new Label(3, 0, "����(mm)");
				outsheet.addCell(cd);
				Label sl = new Label(4, 0, "����");
				outsheet.addCell(sl);
				// ����ԭ����
				WritableSheet excesssheet = outbook.createSheet("ԭ����", 2);
				Label excexh = new Label(0, 0, "�ͺ�");
				excesssheet.addCell(excexh);
				Label excecz = new Label(1, 0, "����");
				excesssheet.addCell(excecz);
				Label excecd = new Label(2, 0, "����(mm)");
				excesssheet.addCell(excecd);
				Label excesl = new Label(3, 0, "����");
				excesssheet.addCell(excesl);
				Label yxj = new Label(3, 0, "���ȼ�");
				excesssheet.addCell(yxj);
				int row = 1;// �������
				int row1 = 1;// ԭ�ϼ���
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
				throw new Exception("������ռ�ã�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// �ж�һ���ַ��Ƿ�������
	public static boolean isChinese(char c) {
		return c >= 0x4E00 && c <= 0x9FA5;// �����ֽ����ж�
	}

	// �ж�һ���ַ����Ƿ�������
	public static boolean isChinese(String str) {
		if (str == null)
			return false;
		for (char c : str.toCharArray()) {
			if (isChinese(c))
				return true;// ��һ�������ַ��ͷ���
		}
		return false;
	}
}
