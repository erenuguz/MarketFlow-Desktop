package kasiyerUygulamasi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class RaporDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	DefaultTableModel raporModel = new DefaultTableModel(new Object[][] {},
			new String[] { "İstatistik", "Miktar/Tutar" });
	private JTable tableRapor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RaporDialog dialog = new RaporDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RaporDialog() {
		setBounds(100, 100, 450, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 416, 403);
		contentPanel.add(scrollPane);

		tableRapor = new JTable(raporModel);
		tableRapor.setRowHeight(30);
		scrollPane.setColumnHeaderView(tableRapor);
		
		raporuGoster();

	}

	public void raporuGoster() {

		try (Connection conn = DBConnection.getConnection()) {

			String sqlCiro = "SELECT SUM(toplam_tutar), CURDATE() FROM satislar WHERE DATE(tarih) = CURRENT_DATE";

			Statement st = conn.createStatement();
			ResultSet rs1 = st.executeQuery(sqlCiro);

			if (rs1.next()) {

				String veritabaniTarihi = rs1.getString(2);

				raporModel.addRow(
						new Object[] { "GÜNLÜK TOPLAM CİRO (" + veritabaniTarihi + ")", rs1.getDouble(1) + " TL" });
			}

			String sqlNakit = "SELECT SUM(toplam_tutar) FROM satislar WHERE odeme_turu='Nakit' AND DATE(tarih) = CURRENT_DATE";
			ResultSet rs2 = st.executeQuery(sqlNakit);
			if (rs2.next()) {
				raporModel.addRow(new Object[] { "NAKİT TAHSİLAT", rs2.getDouble(1) + " TL" });
			}

			String sqlPos = "SELECT SUM(toplam_tutar) FROM satislar WHERE odeme_turu='POS' AND DATE(tarih) = CURRENT_DATE";
			ResultSet rs3 = st.executeQuery(sqlPos);
			if (rs3.next()) {
				raporModel.addRow(new Object[] { "POS (KART) TAHSİLAT", rs3.getDouble(1) + " TL" });
			}

			String sqlMusteri = "SELECT COUNT(id) FROM satislar WHERE DATE(tarih) = CURRENT_DATE";
			ResultSet rs4 = st.executeQuery(sqlMusteri);
			if (rs4.next()) {
				raporModel.addRow(new Object[] { "TOPLAM SATIŞ (FİŞ) ADEDİ", rs4.getInt(1) });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
