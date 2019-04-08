/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.af.weather;

import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Blake Bartenbach
 */
public class BuildDowntimeAnalyzer extends javax.swing.JFrame {
    
    /**
     * Global variable containing a map of all jobs on the Jenkins server.
     */
    private final Map<String,Job> jobMap;
    /**
     * Holds all builds that are currently in the buildList.
     */
    private List<BuildWithDetails> currentBuildListContents = new ArrayList<>();

    /**
     * Creates new form BuildDowntimeAnalyzer
     * @param jobMap - The map of all jobs on the Jenkins server
     */
    public BuildDowntimeAnalyzer(Map<String,Job> jobMap) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuildDowntimeAnalyzer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.jobMap = jobMap;
        initComponents();
        this.setLocationRelativeTo(null);
        listJenkinsJobs.setListData(jobMap.keySet().toArray(new String[0]));
        listJenkinsBuilds.setListData(new String[0]);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listJenkinsJobs = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textBuildDowntime = new javax.swing.JTextField();
        textBuildUptime = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        listJenkinsBuilds = new javax.swing.JList<>();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Build Downtime Analyzer");

        jLabel1.setText("Jobs");

        listJenkinsJobs.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listJenkinsJobs.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listJenkinsJobsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listJenkinsJobs);

        jLabel2.setText("Builds");

        jLabel3.setText("Total Build Downtime:");

        jLabel4.setText("Total Build Uptime:");

        listJenkinsBuilds.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listJenkinsBuilds.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listJenkinsBuilds.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listJenkinsBuildsValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(listJenkinsBuilds);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(150, 150, 150))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textBuildDowntime)
                            .addComponent(textBuildUptime, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textBuildDowntime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textBuildUptime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listJenkinsJobsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listJenkinsJobsValueChanged
        if (evt.getValueIsAdjusting()) {
            return;
        }
        List<String> selectedValues = listJenkinsJobs.getSelectedValuesList();
        List<String> buildsToDisplay = new ArrayList<>();
        //List<Build> builds = JenkinsHandler.getJenkinsBuilds(selectedValues);
        for (String x : selectedValues) {
            try {
                List<Build> builds = jobMap.get(x).details().getAllBuilds();
                for (int i = 0; i < builds.size(); i++) {
                    buildsToDisplay.add(DateFormat.getInstance().format(new Date(builds.get(i).details().getTimestamp())));
                    this.currentBuildListContents.add(builds.get(i).details());
                }
            } catch (IOException ex) {
                Logger.getLogger(BuildDowntimeAnalyzer.class.getName()).log(Level.SEVERE, "Failed to get all builds for job: {0}", x);
            }
        }
        // both these end up reversed, which messed with subtraction of dates (obviously)
        //Collections.reverse(this.currentBuildListContents);
        //Collections.reverse(buildsToDisplay);
        System.out.println("Adding " + buildsToDisplay.size() + " to build list");
        listJenkinsBuilds.setListData(buildsToDisplay.toArray(new String[0]));
    }//GEN-LAST:event_listJenkinsJobsValueChanged

    private void listJenkinsBuildsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listJenkinsBuildsValueChanged
        if (evt.getValueIsAdjusting()) {
            return;
        }
        if (listJenkinsBuilds.getSelectedValuesList().size() <= 1) {
            // not enough builds are selected
            textBuildDowntime.setText("00:00:00");
            textBuildUptime.setText("00:00:00");
            return;
        }
        if (currentBuildListContents.size() <= 1) {
            // show dialog that there's nothing to compare
            // TODO use error icon
            JOptionPane.showMessageDialog(null, "Not enough builds to compare.");
            return;
        }
        
        // get all builds the user selected
        int[] indices = listJenkinsBuilds.getSelectedIndices();
        System.out.println(Arrays.toString(indices));
        List<BuildWithDetails> selectedBuilds = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            selectedBuilds.add(this.currentBuildListContents.get(i));
        }
        
        System.out.println("Size of current selected builds: " + selectedBuilds.size());
        BuildWithDetails build = selectedBuilds.get(0);
        BuildWithDetails nextBuild = selectedBuilds.get(1);
        BuildMetrics metrics = new BuildMetrics();
        BuildResult status = nextBuild.getResult();
        switch(status) {
                 case FAILURE:
                    System.out.println("Build: " + String.valueOf(build.getTimestamp()));
                    System.out.println("Next Build: " + String.valueOf(nextBuild.getTimestamp()));
                    System.out.println("Adding Downtime: " + String.valueOf(build.getTimestamp() - nextBuild.getTimestamp()));
                    metrics.addDowntime(build.getTimestamp() - nextBuild.getTimestamp());
                    break;
                case SUCCESS:
                    System.out.println("Build: " + String.valueOf(build.getTimestamp()));
                    System.out.println("Next Build: " + String.valueOf(nextBuild.getTimestamp()));
                    System.out.println("Adding Uptime: " + String.valueOf(build.getTimestamp() - nextBuild.getTimestamp()));
                    metrics.addUptime(build.getTimestamp() - nextBuild.getTimestamp());
                    break; 
        }
        
        for (int i = 2; i < selectedBuilds.size(); i++) {
            build = nextBuild;
            nextBuild = selectedBuilds.get(i);
            System.out.println("Iteration: "+ i);
            if (build.isBuilding() || nextBuild.isBuilding()) {
                JOptionPane.showMessageDialog(null, "One of the selected builds is still building.");
                return;
            }
            status = nextBuild.getResult();
            switch(status) {
                case FAILURE:
                    System.out.println("Build: " + String.valueOf(build.getTimestamp()));
                    System.out.println("Next Build: " + String.valueOf(nextBuild.getTimestamp()));
                    System.out.println("Adding Downtime: " + String.valueOf(build.getTimestamp() - nextBuild.getTimestamp()));
                    metrics.addDowntime(build.getTimestamp() - nextBuild.getTimestamp());
                    break;
                case SUCCESS:
                    System.out.println("Build: " + String.valueOf(build.getTimestamp()));
                    System.out.println("Next Build: " + String.valueOf(nextBuild.getTimestamp()));
                    System.out.println("Adding Uptime: " + String.valueOf(build.getTimestamp() - nextBuild.getTimestamp()));
                    metrics.addUptime(build.getTimestamp() - nextBuild.getTimestamp());
                    break;
            }
        }
        System.out.println("Build Uptime: " + metrics.getUptime());
        String formattedDowntime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(metrics.getDowntime()),
            TimeUnit.MILLISECONDS.toMinutes(metrics.getDowntime()) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(metrics.getDowntime()) % TimeUnit.MINUTES.toSeconds(1));
        String formattedUptime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(metrics.getUptime()),
            TimeUnit.MILLISECONDS.toMinutes(metrics.getUptime()) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(metrics.getUptime()) % TimeUnit.MINUTES.toSeconds(1)); 
        textBuildDowntime.setText(formattedDowntime);
        textBuildUptime.setText(String.valueOf(formattedUptime));
    }//GEN-LAST:event_listJenkinsBuildsValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList<String> listJenkinsBuilds;
    private javax.swing.JList<String> listJenkinsJobs;
    private javax.swing.JTextField textBuildDowntime;
    private javax.swing.JTextField textBuildUptime;
    // End of variables declaration//GEN-END:variables
}
