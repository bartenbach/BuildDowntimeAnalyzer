
package mil.af.weather;

import mil.af.weather.ui.CredentialsHelper;

public class Main {
    
    /**
     * Application entry point.
     * @param args - command line arguments.
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CredentialsHelper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            CredentialsHelper credentialsHelper = new CredentialsHelper();
            credentialsHelper.setLocationRelativeTo(null);
            credentialsHelper.setVisible(true);
        });
    }
}
