/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import pd08548_tranxuanchien_java2ass2.NhanVien;
import pd08548_tranxuanchien_java2ass2.Clock;
import pd08548_tranxuanchien_java2ass2.XFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author namnt
 */
public class QLNV extends javax.swing.JFrame {

    DefaultTableModel tblModel;
    List<NhanVien> list = new ArrayList<>();
    private int index = -1;
    private static final String P_EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";

    /**
     * Creates new form MainView
     */
    public QLNV() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblNhanVien.getModel();
        tblModel.setColumnIdentifiers(new String[]{"Mã", "Họ và tên", "Tuổi", "Email", "Lương"});
    }

    public void fillTable() {
        tblModel.setRowCount(0);
        for (NhanVien nv : list) {
            tblModel.addRow(new Object[]{nv.getMa(), nv.getHoTen(), nv.getTuoi(), nv.getEmail(), nv.getLuong()});
        }
    }

    public void clearForm() {
        txtHoTen.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        txtMaNv.setText("");
        txtTuoi.setText("");
        index = -1;
    }

    public NhanVien readForm() {
        return new NhanVien(txtMaNv.getText(), txtHoTen.getText(), Integer.valueOf(txtTuoi.getText()), txtEmail.getText(), Double.parseDouble(txtLuong.getText()));
    }

    public void themNV() {
        if (validateForm()) {

            if (index == -1) {
                list.add(readForm());
                fillTable();
                JOptionPane.showMessageDialog(this, "Đã thêm");
            } else {
                capNhat(readForm());
                fillTable();
                JOptionPane.showMessageDialog(this, "Đã cập nhật");
            }
        }
    }

    public NhanVien timTheoMa(String ID) {
        for (NhanVien nv : list) {
            if (nv.getMa().equalsIgnoreCase(ID)) {
                return nv;
            }
        }
        return null;
    }

    public void capNhat(NhanVien newnv) {
        if (validateForm()) {
            NhanVien nv1 = timTheoMa(newnv.getMa());
            if (nv1 != null) {
                nv1.setHoTen(newnv.getHoTen());
                nv1.setTuoi(newnv.getTuoi());
                nv1.setEmail(newnv.getEmail());
                nv1.setLuong(newnv.getLuong());
            }
        }

    }

    public String layThongTinBanGhi() {
        return "Record: " + (index + 1) + " of " + list.size();
    }

    public void fillNhanVienLenForm(int index) {
        txtMaNv.setText(list.get(index).getMa());
        txtHoTen.setText(list.get(index).getHoTen());
        txtEmail.setText(list.get(index).getEmail());
        txtTuoi.setText(String.valueOf(list.get(index).getTuoi()));
        txtLuong.setText(String.valueOf(list.get(index).getLuong()));
    }

    public void fillNhanVienLenForm(NhanVien nv) {
        txtMaNv.setText(nv.getMa());
        txtHoTen.setText(nv.getHoTen());
        txtEmail.setText(nv.getEmail());
        txtTuoi.setText(String.valueOf(nv.getTuoi()));
        txtLuong.setText(String.valueOf(nv.getLuong()));
    }

    public void xoaNv() {
        list.remove(index);
        fillTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Đã xoá");
    }

    public void firstNV() {
        if (list.size() != 0) {
            index = 0;
            updateInfo();
        }
    }

    public void lastNV() {
        if (list.size() != 0) {
            index = list.size() - 1;
            updateInfo();
        }
    }

    public void preNV() {
        if (list.size() != 0) {
            if (index == 0) {
                lastNV();
            } else {
                index--;
            }

            updateInfo();
        }
    }

    public void nextNV() {
        if (list.size() != 0) {
            if (index == list.size() - 1) {
                firstNV();
            } else {
                index++;
            }

            updateInfo();
        }
    }

    private void updateInfo() {
        tblNhanVien.setRowSelectionInterval(index, index);
        fillNhanVienLenForm(index);
        lblBanGhi.setText(layThongTinBanGhi());
    }

    public boolean validateForm() {
        if (txtMaNv.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã nhân viên");
            return false;
        }
        if (txtHoTen.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã họ tên");
            return false;

        }
        if (txtTuoi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tuổi");
            return false;
        }else {
                try {
                    if (Integer.parseInt(txtTuoi.getText()) < 16 || Integer.parseInt(txtTuoi.getText()) > 55) {
                        JOptionPane.showMessageDialog(this, "Tuổi phải từ 16-55");
                        return false;    
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui Lòng nhập đúng định dạng số", "Thông báo", 1);
                }
            }
        try {
            Integer.parseInt(txtTuoi.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là số");
            return false;
        }

        if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập email");
            return false;
        }
        Matcher matcher = Pattern.compile(P_EMAIL).matcher(txtEmail.getText());
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Email sai định dạng");
            return false;
        }
        if (txtLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập lương");
            return false;
        }else {
                try {
                    if (Double.parseDouble(txtLuong.getText()) < 50000) {
                        JOptionPane.showMessageDialog(this, "Lương phải lớn hơn 50000 VND", "Thông báo", 1);
                        return false;
                        
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui Lòng nhập đúng định dạng số", "Thông báo", 1);
                    return false;
                }
            }
        try {
            Double.parseDouble(txtLuong.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lương phải là số", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public void readFile() {
        try {
            list = (List<NhanVien>) XFile.readObj("listnv.dat");
            fillTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            XFile.writeObj("listnv.dat", list);
            JOptionPane.showMessageDialog(this, "Saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNv = new javax.swing.JTextField();
        lblTime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnMo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTuoi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblBanGhi = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ NHÂN VIÊN");
        setMinimumSize(new java.awt.Dimension(570, 472));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jLabel2.setText("MÃ NHÂN VIÊN");

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 0, 51));
        lblTime.setText("00:00 AM");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnReset.setText("New");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnLuu.setText("Save");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnTim.setText("Find");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnXoa.setText("Delete");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThoat.setText("Exit");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnMo.setText("Open");
        btnMo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa))
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("HỌ VÀ TÊN");

        jLabel5.setText("TUỔI");

        jLabel6.setText("EMAIL");

        jLabel7.setText("LƯƠNG");

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPre.setText("<<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        lblBanGhi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBanGhi.setForeground(new java.awt.Color(255, 0, 51));
        lblBanGhi.setText("Record: 1 of 10");

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(149, 149, 149)
                        .addComponent(lblTime))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(13, 13, 13)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMaNv)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTuoi, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLuong, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblBanGhi)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblTime))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLast)
                        .addComponent(btnNext)
                        .addComponent(btnPre)
                        .addComponent(btnFirst))
                    .addComponent(lblBanGhi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        preNV();
    }//GEN-LAST:event_btnPreActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Clock clockThread = new Clock(lblTime);
        Thread t1 = new Thread(clockThread);
        t1.start();

//        list.add(new NhanVien("NV01", "Son", 18, "sonhph23333@fpt.edu.vn", 42411));
//        list.add(new NhanVien("NV02", "Nam", 22, "namhopi11234@fpt.edu.vn", 61234));
//        list.add(new NhanVien("NV03", "Hung", 22, "hungnph23421@fpt.edu.vn", 44111));
//        list.add(new NhanVien("NV04", "Hai", 31, "hainth23211@fpt.edu.vn", 1223));
        initTable();
        //fillTable();
        //lblBanGhi.setText(layThongTinBanGhi());
    }//GEN-LAST:event_formWindowOpened

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        themNV();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        index = tblNhanVien.getSelectedRow();
        fillNhanVienLenForm(index);
        lblBanGhi.setText(layThongTinBanGhi());
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        xoaNv();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        saveFile();
        System.exit(0);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        if (timTheoMa(txtMaNv.getText()) == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
        } else {
            fillNhanVienLenForm(timTheoMa(txtMaNv.getText()));
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        firstNV();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        lastNV();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        nextNV();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnMoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoActionPerformed
        readFile();
    }//GEN-LAST:event_btnMoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnMo;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBanGhi;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNv;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}
