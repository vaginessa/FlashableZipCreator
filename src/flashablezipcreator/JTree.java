/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashablezipcreator;

import flashablezipcreator.Core.MyTreeModelListener;
import flashablezipcreator.Core.NodeRenderer;
import flashablezipcreator.Core.ProjectItemNode;
import flashablezipcreator.Core.ProjectMouseAdapter;
import flashablezipcreator.Core.ProjectTreeBuilder;
import flashablezipcreator.DiskOperations.Read;
import flashablezipcreator.Operations.ProjectOperations;
import flashablezipcreator.Operations.TreeOperations;
import flashablezipcreator.Operations.MyFileFilter;
import flashablezipcreator.Operations.UpdaterScriptOperations;
import flashablezipcreator.Protocols.Device;
import flashablezipcreator.Protocols.Export;
import flashablezipcreator.Protocols.Import;
import flashablezipcreator.Protocols.Jar;
import flashablezipcreator.Protocols.Xml;
import flashablezipcreator.UserInterface.AddDevice;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author Nikhil
 */
public class JTree extends JFrame implements TreeSelectionListener {

    /**
     * Creates new form JTreeDemo
     */
    DefaultTreeModel model;
    TreeOperations to;
    UpdaterScriptOperations uso;
    ProjectOperations po = new ProjectOperations();
    ProjectItemNode rootNode;
    MyFileFilter uio = new MyFileFilter();

    public JTree() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() throws IOException, ParserConfigurationException, TransformerException, SAXException {

        SP_tree = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        btnCreateZip = new javax.swing.JButton();
        btnImportZip = new javax.swing.JButton();
        panel_logo = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();

        //tree configurations
        tree = ProjectTreeBuilder.buildTree();
        model = ProjectTreeBuilder.buildModel();
        model.addTreeModelListener(new MyTreeModelListener());
        SP_tree = ProjectTreeBuilder.buildScrollPane();
        tree.addTreeSelectionListener(this);
        rootNode = ProjectTreeBuilder.rootNode;
        this.to = new TreeOperations(rootNode);
        uso = new UpdaterScriptOperations();

        tree.setEditable(true);
        tree.setInvokesStopCellEditing(true);
        //tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        //tree.setShowsRootHandles(true);

        //mouse click configurations
        ProjectMouseAdapter ma = new ProjectMouseAdapter();
        tree.addMouseListener(ma);

        Read r = new Read();
        //comment following lines if running from netbeans.
        if (Jar.isExecutingThrough()) {
            Jar.addThemesToTree(rootNode, model);
            Device.loadDeviceList();
        } else {
            Xml.file_details_path = "dist/" + Xml.file_details_path;
        }
        
        File f = new File(Xml.file_details_path);
        if (f.exists()) {
            Xml.fileDetailsData = r.getFileString(Xml.file_details_path);
            //JOptionPane.showMessageDialog(this, Xml.fileDetailsData);
            Xml.initializeProjectDetails(Xml.fileDetailsData);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnCreateZip.setText("Create Zip");
        btnCreateZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnCreateZipActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(JTree.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(JTree.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(JTree.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnImportZip.setText("Import Zip");
        btnImportZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnImportZipActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(JTree.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(JTree.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(JTree.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(JTree.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        panel_logo.setBackground(new java.awt.Color(120, 144, 156));
        panel_logo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHeader.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setText("   Android Flashable Zip Creator");

        javax.swing.GroupLayout panel_logoLayout = new javax.swing.GroupLayout(panel_logo);
        panel_logo.setLayout(panel_logoLayout);
        panel_logoLayout.setHorizontalGroup(
            panel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_logoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        panel_logoLayout.setVerticalGroup(
            panel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_logoLayout.createSequentialGroup()
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnImportZip, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateZip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_tree, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(SP_tree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateZip, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImportZip, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnImportZipActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        try {
            Import.fromZip(uio.browseZipDestination(), rootNode, model);
        } catch (NullPointerException npe) {
            //JOptionPane.showMessageDialog(this, "cancelled by user");
        }
    }

    private void btnCreateZipActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ParserConfigurationException, TransformerException {
        ProjectItemNode node = (ProjectItemNode) tree.getLastSelectedPathComponent();
        System.out.println();
        Device.selected = "NonNeon";
        Export.zip(rootNode);
    }

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ParserConfigurationException, TransformerException {
        ProjectItemNode node = (ProjectItemNode) tree.getLastSelectedPathComponent();
        Device.selected = "Neon";
        Export.zip(rootNode);
    }

    private javax.swing.JButton btnCreateZip;
    private javax.swing.JButton btnImportZip;
    private javax.swing.JScrollPane SP_tree;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JPanel panel_logo;
    private javax.swing.JTree tree;

    @Override
    public void valueChanged(TreeSelectionEvent tse) {
    }

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("Mouse clicked");
    }
}
