/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashablezipcreator;

import static flashablezipcreator.AFZC.Protocols.show;
import flashablezipcreator.Core.FileNode;
import flashablezipcreator.Core.GroupNode;
import flashablezipcreator.Core.MyCellEditor;
import flashablezipcreator.Core.MyTreeModelListener;
import flashablezipcreator.Core.ProjectItemNode;
import flashablezipcreator.Core.ProjectMouseAdapter;
import flashablezipcreator.Core.ProjectNode;
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
import flashablezipcreator.Protocols.Project;
import flashablezipcreator.Protocols.Xml;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
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
        lbl_logo = new javax.swing.JLabel();

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

        panel_logo.setBackground(new java.awt.Color(255, 255, 255));
        panel_logo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flashablezipcreator/res/logo4.png"))); // NOI18N

        javax.swing.GroupLayout panel_logoLayout = new javax.swing.GroupLayout(panel_logo);
        panel_logo.setLayout(panel_logoLayout);
        panel_logoLayout.setHorizontalGroup(
                panel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbl_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_logoLayout.setVerticalGroup(
                panel_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lbl_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panel_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnImportZip, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCreateZip, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(SP_tree))
                        .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SP_tree, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCreateZip, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImportZip, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnImportZipActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        //Import.fromZip("gapps-jb-20130813-signed.zip", rootNode, ProjectNode.PROJECT_GAPPS, model);
        //String fileName = textField.getText().toString();
        //int projectType = ProjectNode.PROJECT_AROMA;
//        if (!fileName.equals("")) {
//            if (!fileName.endsWith(".zip")) {
//                if (fileName.endsWith("_r")) {
//                    //projectType = ProjectNode.PROJECT_ROM;
//                    fileName = fileName.substring(0, fileName.lastIndexOf("_"));
//                } else if (fileName.endsWith("_g")) {
//                    //projectType = ProjectNode.PROJECT_GAPPS;
//                    fileName = fileName.substring(0, fileName.lastIndexOf("_"));
//                } else if (fileName.endsWith("_a")) {
//                    //projectType = ProjectNode.PROJECT_AROMA;
//                    fileName = fileName.substring(0, fileName.lastIndexOf("_"));
//                }
//                fileName += ".zip";
//            }
        //fileName = "Flash.zip";
        try {
            Import.fromZip(uio.browseZipDestination(), rootNode, model);
        } catch (NullPointerException npe) {
            //JOptionPane.showMessageDialog(this, "cancelled by user");
        }
        //JOptionPane.showMessageDialog(this, "Successfully Imported");
        //tree.expandRow(1);
//        } else {
//            JOptionPane.showMessageDialog(this, "Enter name first");
//        }
    }

    private void btnCreateZipActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ParserConfigurationException, TransformerException {
        ProjectItemNode node = (ProjectItemNode) tree.getLastSelectedPathComponent();
        System.out.println();
        Device.selected = "NonNeon";
//        String fileName = textField.getText().toString();
//        if (!fileName.equals("")) {
//            if (!fileName.endsWith(".zip")) {
//                if (fileName.endsWith("_r") || fileName.endsWith("_g") || fileName.endsWith("_a")) {
//                    fileName = fileName.substring(0, fileName.lastIndexOf("_"));
//                }
//                //fileName += ".zip";
//            }
//            Project.outputPath = "Output.zip";
//        } else {
//            Project.outputPath = "NonNeon.zip";
//        }

        //File f = new File(fileName + ".zip");
        Export.zip(rootNode);
//        if (f.exists()) {
//            Export.zip(rootNode);
//        } else {
//            JOptionPane.showMessageDialog(this, "File Doesn't Exist");
//        }

//        switch (node.type) {
//            case ProjectItemNode.NODE_GROUP:
//                //to add Group Node
//                to.addChildTo(node, textField.getText(), GroupNode.GROUP_SYSTEM_APK, model);
//                break;
//            case ProjectItemNode.NODE_SUBGROUP:
//                //to add SubGroup Node
//                to.addChildTo(node, textField.getText(), SubGroupNode.TYPE_SYSTEM_MEDIA, model);
//        }
        //to add File Node
        //to.addChildTo(node, textField.getText(), ProjectNode.PROJECT_AROMA, model);
//        this.tree = ProjectTreeBuilder.buildTree();
//        this.model = ProjectTreeBuilder.buildModel();
//        this.jScrollPane1 = ProjectTreeBuilder.buildScrollPane();
//        model.reload();
        //model.reload(node);
        //to.expandDirectories(tree);
    }

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException, ParserConfigurationException, TransformerException {
        ProjectItemNode node = (ProjectItemNode) tree.getLastSelectedPathComponent();
        //node.removeChild(node, model);
        //to.expandDirectories(tree);
        //JOptionPane.showMessageDialog(null, JarOperations.getJarFileName());
//        Export e = new Export();
//        e.zip(rootNode);
        Device.selected = "Neon";
//        String fileName = textField.getText();
//        if (!fileName.equals("")) {
//            if (!fileName.endsWith(".zip")) {
//                if (fileName.endsWith("_r") || fileName.endsWith("_g") || fileName.endsWith("_a")) {
//                    fileName = fileName.substring(0, fileName.lastIndexOf("_"));
//                }
//                //fileName += ".zip";
//            }
//            Project.outputPath = fileName + "_neon_aroma.zip";
//        } else {
//            Project.outputPath = "Neon.zip";
//        }
        //File f = new File(fileName + ".zip");
        //if (f.exists()) {
        Export.zip(rootNode);
        //} else {
        //    JOptionPane.showMessageDialog(this, "File Doesn't Exist");
        //}
        //e.createZip(to.getNodeList(ProjectItemNode.NODE_FILE));
    }

    private javax.swing.JButton btnCreateZip;
    private javax.swing.JButton btnImportZip;
    private javax.swing.JScrollPane SP_tree;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JPanel panel_logo;
    private javax.swing.JTree tree;

    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        JTextField textField = new JTextField();

        //tree.setCellEditor(new MyCellEditor());
        //tree.setEditable(true);
//        TreePath currentSelection = tree.getSelectionPath(); 
//        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection .getLastPathComponent());
//        tree.setEditable(true);
//        tree.startEditingAtPath(currentSelection);
//        
//        
//        currentNode.setUserObject(currentNode.getUserObject());
        //tree.setInvokesStopCellEditing(true);
//        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
//                           tree.getLastSelectedPathComponent();
//
//    /* if nothing is selected */ 
//        if (node == null) return;
//
//    /* retrieve the node that was selected */ 
//        Object nodeInfo = node.getUserObject();
        //JOptionPane.showMessageDialog(this, "a");
//        ProjectItemNode node = (ProjectItemNode) tree.getLastSelectedPathComponent();
//        if (node == null) {
//            return;
//        }
//        if (node instanceof FileNode) {
//            System.out.println(((FileNode) node).installLocation);
//        }
//        if (node instanceof GroupNode) {
//            System.out.println(((GroupNode) node).location);
//        }
        //System.out.println(((FileNode)node).filePermission);
        //JOptionPane.showMessageDialog(this, "You have selected: " + node);
    }

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println("Mouse clicked");
    }

//        public String browseZipDestination() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
//        FileFilter filter = new FileNameExtensionFilter(".zip", "zip");
//        fileChooser.addChoosableFileFilter(filter);
//        int returnVal = fileChooser.showOpenDialog(importButton);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooser.getSelectedFile();
//            System.out.println("Zip File Destination Location : " + file.getAbsolutePath());
//            if (!file.getAbsolutePath().endsWith(".zip")) {
//                textField.setText(file.getAbsolutePath() + ".zip");
//                return file.getAbsolutePath() + ".zip";
//            } else {
//                textField.setText(file.getAbsolutePath());
//                return file.getAbsolutePath();
//            }
//        } else {
//            System.out.println("File access cancelled by user.");
//        }
//        return null;
//    }
}