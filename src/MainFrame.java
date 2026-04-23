package kasiyerUygulamasi;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTable tableSepet;
	private JSpinner spinnerAdet;
	private JLabel labelParcaSayisi, labelSatirSayisi, labelAraToplam, labelToplam;
	public DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "Ürün Adı", "Birim Fiyat", "Adet", "Toplam" });

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelGiris = new JPanel();
		panelGiris.setLayout(null);
		panelGiris.setBackground(SystemColor.info);
		panelGiris.setBounds(10, 8, 504, 30);
		contentPane.add(panelGiris);

		txtBarcode = new JTextField();
		txtBarcode.setEnabled(true);
		txtBarcode.setBackground(new Color(128, 255, 255));
		txtBarcode.setBounds(0, 0, 160, 30);
		panelGiris.add(txtBarcode);

		JLabel labelx = new JLabel("X");
		labelx.setHorizontalAlignment(SwingConstants.CENTER);
		labelx.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelx.setBounds(160, 0, 30, 30);
		panelGiris.add(labelx);

		spinnerAdet = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
		spinnerAdet.setFont(new Font("Tahoma", Font.PLAIN, 17));
		spinnerAdet.setBounds(190, 0, 55, 30);
		panelGiris.add(spinnerAdet);

		JButton buttonEkle = new JButton("EKLE");
		buttonEkle.setFont(new Font("Arial", Font.BOLD, 26));
		buttonEkle.setBackground(new Color(0, 128, 192));
		buttonEkle.setBounds(265, 0, 110, 30);
		panelGiris.add(buttonEkle);

		JButton silButton = new JButton("Seçili Satırı Sil");
		silButton.setFont(new Font("Arial", Font.BOLD, 10));
		silButton.setBackground(new Color(0, 128, 192));
		silButton.setBounds(394, 0, 110, 30);
		panelGiris.add(silButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 504, 434);
		contentPane.add(scrollPane);

		tableSepet = new JTable(model);
		scrollPane.setViewportView(tableSepet);

		JPanel panelOzet = new JPanel();
		panelOzet.setBackground(SystemColor.info);
		panelOzet.setBounds(524, 8, 252, 110);
		panelOzet.setLayout(null);
		contentPane.add(panelOzet);

		labelParcaSayisi = new JLabel("Parça Sayısı: 0");
		labelParcaSayisi.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelParcaSayisi.setBounds(10, 10, 110, 17);
		panelOzet.add(labelParcaSayisi);

		labelSatirSayisi = new JLabel("Satır Sayısı: 0");
		labelSatirSayisi.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelSatirSayisi.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSatirSayisi.setBounds(134, 10, 110, 17);
		panelOzet.add(labelSatirSayisi);

		JLabel lblAraText = new JLabel("Ara Toplam:");
		lblAraText.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAraText.setBounds(10, 30, 75, 17);
		panelOzet.add(lblAraText);

		labelAraToplam = new JLabel("0.00 TL");
		labelAraToplam.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelAraToplam.setHorizontalAlignment(SwingConstants.RIGHT);
		labelAraToplam.setBounds(134, 30, 110, 17);
		panelOzet.add(labelAraToplam);

		JLabel lblKdvText = new JLabel("KDV Toplamı:");
		lblKdvText.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblKdvText.setBounds(10, 50, 75, 17);
		panelOzet.add(lblKdvText);

		JLabel labelKdvToplam = new JLabel("0.00 TL");
		labelKdvToplam.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelKdvToplam.setHorizontalAlignment(SwingConstants.RIGHT);
		labelKdvToplam.setBounds(134, 50, 110, 17);
		panelOzet.add(labelKdvToplam);

		JLabel lblToplamText = new JLabel("Toplam:");
		lblToplamText.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblToplamText.setBounds(10, 70, 82, 30);
		panelOzet.add(lblToplamText);

		labelToplam = new JLabel("0.00 TL");
		labelToplam.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelToplam.setHorizontalAlignment(SwingConstants.RIGHT);
		labelToplam.setBounds(134, 70, 110, 30);
		panelOzet.add(labelToplam);

		JPanel panelAlt = new JPanel();
		panelAlt.setBackground(SystemColor.info);
		panelAlt.setBounds(10, 508, 766, 45);
		panelAlt.setLayout(null);
		contentPane.add(panelAlt);

		JButton butonNakit = new JButton("Nakit");
		butonNakit.setBounds(5, 5, 110, 35);
		panelAlt.add(butonNakit);

		JButton butonPos = new JButton("POS");
		butonPos.setBounds(120, 5, 110, 35);
		panelAlt.add(butonPos);

		JButton butonNakitPos = new JButton("Nakit & POS");
		butonNakitPos.setBounds(235, 5, 110, 35);
		panelAlt.add(butonNakitPos);

		JButton butonIskontolu = new JButton("İskontolu");
		butonIskontolu.setBounds(350, 5, 110, 35);
		panelAlt.add(butonIskontolu);

		JButton butonFisYazdir = new JButton("Fiş Yazdır");
		butonFisYazdir.setBounds(465, 5, 110, 35);
		panelAlt.add(butonFisYazdir);
		
		JButton btnRapor = new JButton("Rapor");
		btnRapor.setBounds(651, 5, 110, 35);
		panelAlt.add(btnRapor);
		
				btnRapor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RaporDialog dialog = new RaporDialog();
						dialog.setVisible(true);
						
					}
				});

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(524, 130, 252, 350);
		
		tabbedPane.addTab("Manav", createMenuPanel(new String[]{"Elma", "Armut", "Muz", "Domates", "Biber"}, new int[]{101, 102, 103, 104, 105}));
		tabbedPane.addTab("Unlu Mamul", createMenuPanel(new String[]{"Ekmek", "Simit", "Poğaça"}, new int[]{201, 202, 203}));

		contentPane.add(tabbedPane);
		
		// --- EVENT LISTENERS --- //
		txtBarcode.addActionListener(e -> buttonEkle.doClick());
		
		buttonEkle.addActionListener(e -> {
			try {
				int id = Integer.parseInt(txtBarcode.getText());
				int adet = (int) spinnerAdet.getValue();
				urunGetirVeEkle(id, adet);
				txtBarcode.setText("");
				spinnerAdet.setValue(1);
				txtBarcode.requestFocus();
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Lütfen geçerli bir Barkod giriniz!");
			}
		});

		silButton.addActionListener(e -> {
			int seciliSatir = tableSepet.getSelectedRow();
			if (seciliSatir != -1) {
				model.removeRow(seciliSatir);
				toplamHesapla();
				txtBarcode.requestFocus();
			} else {
				JOptionPane.showMessageDialog(null, "Silinecek ürünü seçin!");
			}
		});

		butonNakit.addActionListener(e -> {
			String tutarYazisi = labelToplam.getText().replace(" TL", "").replace(",", ".");
			double gonderilecekTutar = Double.parseDouble(tutarYazisi);
			String odemeYontemi = "Nakit";
			
			NakitOdemeDialog dialog = new NakitOdemeDialog(MainFrame.this, gonderilecekTutar,odemeYontemi);
			dialog.setVisible(true);
		});
		
		butonPos.addActionListener(e -> {
			String tutarYazisi = labelToplam.getText().replace(" TL", "").replace(",", ".");
			double gonderilecekTutar = Double.parseDouble(tutarYazisi);
			String odemeYontemi = "POS";
			
		    NakitOdemeDialog dialog = new NakitOdemeDialog(MainFrame.this, gonderilecekTutar,odemeYontemi);
		    dialog.setVisible(true);
		});
		
		butonFisYazdir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fisYazdir();
			}
		});
	}

	// --- YARDIMCI METOTLAR --- //

	public void urunGetirVeEkle(int id, int adet) {
		String sql = "SELECT * FROM urunler WHERE id = ?";
		try (java.sql.Connection conn = DBConnection.getConnection();
				java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			java.sql.ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String ad = rs.getString("urun_adi");
				double fiyat = rs.getDouble("fiyat");

				boolean urunVarmi = false;
				int varOlanSatirIndex = -1;

				for (int i = 0; i < model.getRowCount(); i++) {
					if (model.getValueAt(i, 0).toString().equals(ad)) {
						urunVarmi = true;
						varOlanSatirIndex = i;
						break;
					}
				}

				if (urunVarmi) {
					int eskiAdet = Integer.parseInt(model.getValueAt(varOlanSatirIndex, 2).toString());
					int yeniAdet = eskiAdet + adet;
					model.setValueAt(yeniAdet, varOlanSatirIndex, 2);
					model.setValueAt(yeniAdet * fiyat, varOlanSatirIndex, 3);
				} else {
					model.addRow(new Object[] { ad, fiyat, adet, fiyat * adet });
				}
				toplamHesapla();
			} else {
				JOptionPane.showMessageDialog(this, "Ürün bulunamadı!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toplamHesapla() {
		double araToplam = 0;
		double parcaSayisi = 0;

		for (int i = 0; i < model.getRowCount(); i++) {
			araToplam += (double) model.getValueAt(i, 3);
			parcaSayisi += Double.parseDouble(model.getValueAt(i, 2).toString());
		}

		labelParcaSayisi.setText("Parça Sayısı: " + parcaSayisi);
		labelSatirSayisi.setText("Satır Sayısı: " + model.getRowCount());
		labelAraToplam.setText(araToplam + " TL");
		labelToplam.setText(araToplam + " TL");
	}
	public void fisYazdir() {
	    StringBuilder fisMetni = new StringBuilder();
	    Connection conn = DBConnection.getConnection();

	    try {
	  
	        String sqlSatis = "SELECT * FROM satislar ORDER BY id DESC LIMIT 1";
	        Statement st = conn.createStatement();
	        ResultSet rsSatis = st.executeQuery(sqlSatis);

	        if (rsSatis.next()) {
	            int satisId = rsSatis.getInt("id");
	            String tarih = rsSatis.getString("tarih");
	            double toplam = rsSatis.getDouble("toplam_tutar");

	            fisMetni.append("      EREN MARKET\n");
	            fisMetni.append("---------------------------\n");
	            fisMetni.append("Fiş No: ").append(satisId).append("\n");
	            fisMetni.append("Tarih:  ").append(tarih).append("\n");
	            fisMetni.append("---------------------------\n");

	            String sqlDetay = "SELECT * FROM satis_detaylari WHERE satis_id = ?";
	            PreparedStatement psDetay = conn.prepareStatement(sqlDetay);
	            psDetay.setInt(1, satisId);
	            ResultSet rsDetay = psDetay.executeQuery();

	            while (rsDetay.next()) {
	                String ad = rsDetay.getString("urun_adi");
	                int adet = rsDetay.getInt("adet");
	                double fiyat = rsDetay.getDouble("fiyat");
	                
	                String formatliAd = String.format("%-15s", ad.length() > 15 ? ad.substring(0, 15) : ad);
	                fisMetni.append(formatliAd).append(adet).append(" x ").append(fiyat).append("\n");
	            }

	            fisMetni.append("---------------------------\n");
	            fisMetni.append("TOPLAM: ").append(toplam).append(" TL\n");
	            fisMetni.append("---------------------------\n");
	            fisMetni.append("   YINE BEKLERIZ...");
	        }

	        JOptionPane.showMessageDialog(null, new JTextArea(fisMetni.toString()), "Satış Fişi", JOptionPane.PLAIN_MESSAGE);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public JScrollPane createMenuPanel(String[] urunIsimleri, int[] urunIdleri) {
	    
	    JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5)); 
	    
	    for (int i = 0; i < urunIsimleri.length; i++) {
	        JButton btn = new JButton(urunIsimleri[i]);
	        final int id = urunIdleri[i]; 
	        
	        btn.addActionListener(e -> urunGetirVeEkle(id, 1));
	        btn.setPreferredSize(new Dimension(100, 60)); 
	        panel.add(btn);
	    }

	    JScrollPane scrollPane = new JScrollPane(panel);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
	    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	    
	    return scrollPane;
	}
}