/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pd08548_tranxuanchien_java2ass2;

import ass1_Model.nhanVien;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class ass1 extends javax.swing.JFrame {

    DefaultTableModel tblmodel;
    public List<nhanVien> list = new ArrayList<>();
    private int index = -1;
    
    public static final String p_EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    /**
     * Creates new form ass1
     */
    public ass1() {
        initComponents();
        setTitle("Staff manager");
        setLocationRelativeTo(null);
    }

    public void initTable() {
        tblmodel = (DefaultTableModel) tbStaff.getModel();
        tblmodel.setColumnIdentifiers(new String[]{"ID", "name", "Age", "Email", "Salary"});
    }

    public void fillTable() {
        tblmodel.setRowCount(0);
        for (nhanVien nv : list) {
            tblmodel.addRow(new Object[]{nv.getStaffID(), nv.getName(), nv.getAge(), nv.getEmail(), nv.getSalary()});
        }
    }

    public nhanVien readForm() {
        return new nhanVien(txtStaffID.getText(), txtName.getText(), Integer.valueOf(txtAge.getText()), txtEmail.getText(), Double.parseDouble(txtSalary.getText()));
    }

    public void addNV() {
        if (validateform()) {
            
        if (index == -1) {
            list.add(readForm());
            fillTable();
            JOptionPane.showMessageDialog(this, "da them nhan vien");
        } else {
            capNhat(readForm());
            fillTable();
            JOptionPane.showMessageDialog(this, "da cap nhat");
        }
        }
    }

    public nhanVien timTheoID(String ID) {
        for (nhanVien nv : list) {
            if (nv.getStaffID().equalsIgnoreCase(ID)) {
                return nv;
            }
        }
        return null;
    }

    public void capNhat(nhanVien newnv) {
        if (validateform()) {
             nhanVien nv1 = timTheoID(newnv.getStaffID());
        if (nv1 != null) {
            nv1.setStaffID(newnv.getStaffID());
            nv1.setName(newnv.getName());
            nv1.setAge(newnv.getAge());
            nv1.setEmail(newnv.getEmail());
            nv1.setSalary(newnv.getSalary());
        }
      }
    }
    public boolean validateform(){
        if (txtStaffID.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap  ma");
            return false;
        }
        if (txtName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap  ten");
            return false;
        }
        if (txtAge.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap tuoi");
            return false;
        }
        try {
        Integer.parseInt(txtAge.getText());            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "tuoi la so");
           return false;
        }

        if (txtEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap email");
            return false;
        }
        Matcher matcher = Pattern.compile(p_EMAIL).matcher(txtEmail.getText());
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "sai email");
            return false;
        }
        if (txtSalary.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap luong");
            return false;
        }
        try {
        Double.parseDouble(txtSalary.getText());            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "luong la so");
           return false;
        }
        return true;
    }
    
        public void readfile(){
        try {
            list = (List<nhanVien>) XFile.readObject("listnv.dat");
            fillTable();
        } catch (Exception e) {
            e.printStackTrace();        }
        
    }
    public void savefile(){
        try {
            XFile.writeObject("listnv.dat",list);
            JOptionPane.showMessageDialog(this, "thanh cong");

        } catch (Exception e) {
                e.printStackTrace();
        }
        
    }
    

    public String layTHongTin() {
        return "recotd: " + (index + 1) + "of " + list.size();
    }

    public void showNV(int index) {
        txtStaffID.setText(list.get(index).getStaffID());
        txtName.setText(list.get(index).getName());
        txtAge.setText(String.valueOf(list.get(index).getAge()));
        txtEmail.setText(list.get(index).getEmail());
        txtSalary.setText(String.valueOf(list.get(index).getSalary()));
    }

    public void showNV(nhanVien nv) {
        txtStaffID.setText(nv.getStaffID());
        txtName.setText(nv.getName());
        txtAge.setText(String.valueOf(nv.getAge()));
        txtEmail.setText(nv.getEmail());
        txtSalary.setText(String.valueOf(nv.getSalary()));
    }

    public void xoaNV() {
        try {
            list.remove(index);
            fillTable();
            clear();
            JOptionPane.showMessageDialog(this, "xoa thanh cong");
        } catch (Exception e) {

        }
    }

    public void fistNV() {
        if (list.size() !=0) {
        index = 0;
        tbStaff.setRowSelectionInterval(index, index);
        showNV(index);
        lblBanGhi.setText(layTHongTin());
        }
    }

    public void lastNV() {
        if (list.size() !=0) {
        index = list.size() - 1;
        tbStaff.setRowSelectionInterval(index, index);
        showNV(index);
        lblBanGhi.setText(layTHongTin());
        }
    }

    public void preNV() {
        if (list.size() !=0) {
        if (index == 0) {
            lastNV();
        } else {
            index--;
            tbStaff.setRowSelectionInterval(index, index);
            showNV(index);
            lblBanGhi.setText(layTHongTin());
        }
        }
    
    }

    public void nextNV() {
        if (list.size() !=0) {
        if (index == list.size()-1) {
            fistNV();
        } else {
            index++;
            tbStaff.setRowSelectionInterval(index, index);
            showNV(index);
            lblBanGhi.setText(layTHongTin());
        }
      }
    }

    public void clear() {
        txtAge.setText("");
        txtEmail.setText("");
        txtName.setText("");
        txtSalary.setText("");
        txtStaffID.setText("");
        index = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtStaffID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSalary = new javax.swing.JTextField();
        btnFist = new javax.swing.JButton();
        btnpre = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnlast = new javax.swing.JButton();
        txtclock = new javax.swing.JLabel();
        lblBanGhi = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnOpen = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbStaff = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("QUẢN LÍ NHÂN VIÊN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên Nhân Viên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tuổi");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Lương");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnFist.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFist.setText("|<");
        btnFist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFistActionPerformed(evt);
            }
        });

        btnpre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnpre.setText("<<");
        btnpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreActionPerformed(evt);
            }
        });

        btnnext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnnext.setText(">>");
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btnlast.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnlast.setText(">|");
        btnlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlastActionPerformed(evt);
            }
        });

        txtclock.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtclock.setForeground(new java.awt.Color(255, 51, 51));
        txtclock.setText("00:00 am");

        lblBanGhi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBanGhi.setForeground(new java.awt.Color(255, 0, 0));
        lblBanGhi.setText("reccod 1 of 10");

        jPanel1.setForeground(new java.awt.Color(255, 0, 0));

        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit)
                    .addComponent(btnFind)
                    .addComponent(btnDelete)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tbStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Age", "Email", "Salary"
            }
        ));
        tbStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStaffMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbStaff);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addComponent(btnFist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnpre, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnlast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblBanGhi))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAge, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                        .addComponent(txtStaffID)
                                        .addComponent(txtName)
                                        .addComponent(txtEmail)
                                        .addComponent(txtSalary)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(190, 190, 190)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtclock, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(4, 4, 4)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtclock))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtStaffID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBanGhi)
                    .addComponent(btnlast)
                    .addComponent(btnnext)
                    .addComponent(btnpre)
                    .addComponent(btnFist))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jLabel2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlastActionPerformed
        // TODO add your handling code here:
        lastNV();
    }//GEN-LAST:event_btnlastActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {  
        clock clock = new clock(txtclock);
        Thread t1 = new Thread(clock);
        t1.start();
//        list.add(new nhanVien("nv01", "chien", 19, "chien@gmail.com", 129039));
//        list.add(new nhanVien("nv02", "dai", 20, "dai@gmail.com", 12939));
//        list.add(new nhanVien("nv03", "dung", 21, "dung@gmail.com", 1299));
//        list.add(new nhanVien("nv04", "long", 22, "long@gmail.com", 1209));
        initTable();
//        fillTable();
//        lblBanGhi.setText(layTHongTin());
    } 
    
    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        // TODO add your handling code here:
        nextNV();
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpreActionPerformed
        // TODO add your handling code here:
        preNV();
    }//GEN-LAST:event_btnpreActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here
        readfile();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        xoaNV();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        if (timTheoID(txtStaffID.getText()) == null) {
            JOptionPane.showMessageDialog(this, "khong tim thay");
        } else {
            showNV(timTheoID(txtStaffID.getText()));
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here
        savefile();
        System.exit(0);

    }//GEN-LAST:event_btnExitActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        addNV();
    }//GEN-LAST:event_btnSaveActionPerformed
/*
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened
*/
    private void tbStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStaffMouseClicked
        // TODO add your handling code here:
        index = tbStaff.getSelectedRow();
        showNV(index);
        lblBanGhi.setText(layTHongTin());
    }//GEN-LAST:event_tbStaffMouseClicked

    private void btnFistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFistActionPerformed
        // TODO add your handling code here:
        fistNV();
    }//GEN-LAST:event_btnFistActionPerformed

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
            java.util.logging.Logger.getLogger(ass1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ass1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ass1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ass1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ass1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFist;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnlast;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnpre;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBanGhi;
    private javax.swing.JTable tbStaff;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtStaffID;
    private javax.swing.JLabel txtclock;
    // End of variables declaration//GEN-END:variables
}
