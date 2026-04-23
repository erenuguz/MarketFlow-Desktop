package kasiyerUygulamasi;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import com.mysql.cj.xdevapi.Statement;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class NakitOdemeDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final double gelenTutar;
	private final String odemeYontemi;
	private JTextField textFieldOdemeTutarı;
	private JLabel labelToplamTutar, labelToplamOdeme, labelToplamKalan;
	private JButton butonOdemeEkle, butonKaydet, butonIptal;

	public NakitOdemeDialog(JFrame parent, double tutar, String odemeYontemi) {
		super(parent, true);
		this.gelenTutar = tutar;
		this.odemeYontemi = odemeYontemi;
		this.setTitle(odemeYontemi);
		setBounds(100, 100, 350, 400);
		getContentPane().setLayout(null);

		JPanel panelAna = new JPanel();
		panelAna.setBounds(0, 0, 336, 363);
		panelAna.setLayout(null);
		getContentPane().add(panelAna);

		// --- ÜST PANEL: ÖDEME GİRİŞİ --- //
		JPanel panelGiris = new JPanel();
		panelGiris.setBackground(SystemColor.info);
		panelGiris.setBounds(10, 10, 316, 90);
		panelGiris.setLayout(null);
		panelAna.add(panelGiris);

		JLabel lblOdemeTutari = new JLabel("Ödeme Tutarı:");
		lblOdemeTutari.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOdemeTutari.setBounds(10, 10, 130, 30);
		panelGiris.add(lblOdemeTutari);

		textFieldOdemeTutarı = new JTextField("");
		textFieldOdemeTutarı.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldOdemeTutarı.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldOdemeTutarı.setBounds(156, 11, 150, 30);
		panelGiris.add(textFieldOdemeTutarı);

		butonOdemeEkle = new JButton("Ödeme Ekle");
		butonOdemeEkle.setFont(new Font("Tahoma", Font.BOLD, 15));
		butonOdemeEkle.setBounds(156, 51, 150, 30);
		panelGiris.add(butonOdemeEkle);

		JPanel panelOzet = new JPanel();
		panelOzet.setBackground(SystemColor.info);
		panelOzet.setBounds(10, 133, 316, 160);
		panelOzet.setLayout(null);
		panelAna.add(panelOzet);

		JLabel lblBorc = new JLabel("Toplam Tutar:");
		lblBorc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBorc.setBounds(10, 10, 130, 40);
		panelOzet.add(lblBorc);

		labelToplamTutar = new JLabel(String.valueOf(gelenTutar));
		labelToplamTutar.setBorder(new LineBorder(Color.BLACK, 1));
		labelToplamTutar.setOpaque(true);
		labelToplamTutar.setBackground(Color.YELLOW);
		labelToplamTutar.setHorizontalAlignment(SwingConstants.RIGHT);
		labelToplamTutar.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelToplamTutar.setBounds(156, 15, 150, 30);
		panelOzet.add(labelToplamTutar);

		JLabel lblOdeme = new JLabel("Toplam Ödeme:");
		lblOdeme.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOdeme.setBounds(10, 60, 130, 40);
		panelOzet.add(lblOdeme);

		labelToplamOdeme = new JLabel("0");
		labelToplamOdeme.setBorder(new LineBorder(Color.BLACK, 1));
		labelToplamOdeme.setBackground(Color.YELLOW);
		labelToplamOdeme.setOpaque(true);
		labelToplamOdeme.setHorizontalAlignment(SwingConstants.RIGHT);
		labelToplamOdeme.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelToplamOdeme.setBounds(156, 65, 150, 30);
		panelOzet.add(labelToplamOdeme);

		JLabel lblKalan = new JLabel("Para Üstü:");
		lblKalan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKalan.setBounds(10, 110, 130, 40);
		panelOzet.add(lblKalan);

		labelToplamKalan = new JLabel("0");
		labelToplamKalan.setBorder(new LineBorder(Color.BLACK, 1));
		labelToplamKalan.setBackground(Color.WHITE);
		labelToplamKalan.setOpaque(true);
		labelToplamKalan.setHorizontalAlignment(SwingConstants.RIGHT);
		labelToplamKalan.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelToplamKalan.setBounds(156, 115, 150, 30);
		panelOzet.add(labelToplamKalan);

		JPanel panelButonlar = new JPanel();
		panelButonlar.setBackground(SystemColor.info);
		panelButonlar.setBounds(10, 303, 316, 50);
		panelButonlar.setLayout(null);
		panelAna.add(panelButonlar);

		butonKaydet = new JButton("Kaydet");
		butonKaydet.setBackground(Color.GREEN);
		butonKaydet.setFont(new Font("Tahoma", Font.BOLD, 15));
		butonKaydet.setBounds(214, 10, 92, 30);
		panelButonlar.add(butonKaydet);

		butonIptal = new JButton("İptal");
		butonIptal.setBackground(Color.RED);
		butonIptal.setFont(new Font("Tahoma", Font.BOLD, 15));
		butonIptal.setBounds(112, 10, 92, 30);
		panelButonlar.add(butonIptal);

		// --- EVENT LISTENERS --- //

		if (odemeYontemi.equals("POS")) {
			labelToplamKalan.setVisible(false);
			lblKalan.setVisible(false);
			textFieldOdemeTutarı.setEditable(false);
			textFieldOdemeTutarı.setText("" + gelenTutar);
		}

		butonOdemeEkle.addActionListener(e -> {
			try {
				double odemeTutari = Double.parseDouble(textFieldOdemeTutarı.getText());
				double fark = odemeTutari - gelenTutar;

				labelToplamOdeme.setText(String.format("%.2f", odemeTutari));
				labelToplamKalan.setText(String.format("%.2f", fark));

				labelToplamKalan.setForeground(fark >= 0 ? new Color(0, 100, 0) : Color.RED);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Lütfen geçerli bir tutar girin!");
			}
		});

		textFieldOdemeTutarı.addActionListener(e -> {
			butonOdemeEkle.doClick();
			panelButonlar.requestFocus();
		});

		butonKaydet.addActionListener(e -> {
			MainFrame anaPencere = (MainFrame) getOwner();
			Connection conn = null;

			try {

				conn = DBConnection.getConnection();
				conn.setAutoCommit(false);

				String sqlSatis = "INSERT INTO satislar (toplam_tutar, odeme_turu) VALUES (?, ?)";
				PreparedStatement psSatis = conn.prepareStatement(sqlSatis, java.sql.Statement.RETURN_GENERATED_KEYS);
				psSatis.setDouble(1, gelenTutar);
				psSatis.setString(2, odemeYontemi);
				psSatis.executeUpdate();

				ResultSet rs = psSatis.getGeneratedKeys();
				int satisId = 0;
				if (rs.next()) {
					satisId = rs.getInt(1);
				}

				String sqlDetay = "INSERT INTO satis_detaylari (satis_id, urun_adi, adet, fiyat, satir_toplam) VALUES (?, ?, ?, ?,?)";
				String sqlStokGuncelle = "UPDATE urunler SET stok_miktari = stok_miktari - ? WHERE urun_adi = ?";

				PreparedStatement psDetay = conn.prepareStatement(sqlDetay);
				PreparedStatement psStok = conn.prepareStatement(sqlStokGuncelle);

				for (int i = 0; i < anaPencere.model.getRowCount(); i++) {
					String urunAdi = anaPencere.model.getValueAt(i, 0).toString();
					double fiyat = Double.parseDouble(anaPencere.model.getValueAt(i, 1).toString());
					int adet = Integer.parseInt(anaPencere.model.getValueAt(i, 2).toString());
					double satirToplam = fiyat * adet;

					psDetay.setInt(1, satisId);
					psDetay.setString(2, urunAdi);
					psDetay.setInt(3, adet);
					psDetay.setDouble(4, fiyat);
					psDetay.setDouble(5, satirToplam);
					psDetay.addBatch();

					psStok.setInt(1, adet);
					psStok.setString(2, urunAdi);
					psStok.addBatch();
				}

				psDetay.executeBatch();
				psStok.executeBatch();

				conn.commit();
				JOptionPane.showMessageDialog(this, "Satış ve Stoklar Başarıyla Kaydedildi!");

				anaPencere.model.setRowCount(0);
				anaPencere.toplamHesapla();
				dispose();

			} catch (Exception ex) {
				try {
					if (conn != null)
						conn.rollback();
				} catch (Exception rex) {
				}
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Hata: Kayıt tamamlanamadı!");
			}
		});

		butonIptal.addActionListener(e -> dispose());

		this.getRootPane().setDefaultButton(butonKaydet);
	}
}