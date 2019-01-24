package jessx.utils.gui;

/***************************************************************/
/*                     SOFTWARE SECTION                        */
/***************************************************************/
/*
 * <p>Name: Jessx</p>
 * <p>Description: Financial Market Simulation Software</p>
 * <p>Licence: GNU General Public License</p>
 * <p>Organisation: EC Lille / USTL</p>
 * <p>Persons involved in the project : group T.E.A.M.</p>
 * <p>More details about this source code at :
 *    http://eleves.ec-lille.fr/~ecoxp03  </p>
 * <p>Current version: 1.0</p>
 */

/***************************************************************/
/*                      LICENCE SECTION                        */
/***************************************************************/
/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/***************************************************************/
/*                      IMPORT SECTION                         */
/***************************************************************/

import java.io.*;
import java.net.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import jessx.utils.*;

/***************************************************************/
/*               AbstractAboutBox CLASS SECTION                */
/***************************************************************/

/**
 * <p>Title: AbstractAboutBox</p>
 * <p>Description: This class implements an about box, that will typically be
 * displayed by the frame class when the user click on the about button from
 * the menu.
 * It gives some version info and a link to a tutorial for using / programming
 * new module.</p>
 * @author Thierry Curtil, Clement Plaignaud, Christophe Grosjean, Rémi Quilliet
 * @version 1.0
 */

public abstract class AbstractAboutBox
    extends JDialog implements ActionListener, Constants {

  JTabbedPane jTabbedPane1 = new JTabbedPane();
  JPanel panelTeam = new JPanel();
  JPanel panelVersion = new JPanel();
  JPanel panelLicence = new JPanel();

  ImageIcon logoJessX = new ImageIcon();
  ImageIcon logoECLille = new ImageIcon();
  ImageIcon logoUSTL = new ImageIcon();
  ImageIcon logoEcoxp = new ImageIcon();
  ImageIcon logoTrading = new ImageIcon();
  ImageIcon logoMarket = new ImageIcon();

  JTextField jTextFieldProductName = new JTextField("JessX: java Experimental Simulated Stock Exchange");
  JTextField jTextFieldVersion = new JTextField("Version : "+Constants.VERSION);
  JTextField jTextFieldDate = new JTextField(Constants.DATE_OF_RELEASE);
  JTextField jTextFieldRessources = new JTextField("Ressources and tutorials at " +
                              Constants.WEB_PAGE);

 /* JTextField jTextFieldName6 = new JTextField("Market Team (Sept. 2006 - May 2007) : Etienne Broutin, Christophe Burlett, Roger Iss");
  JTextField jTextFieldName7 = new JTextField("Benoit Lebegue, Rémi Quilliet, Guillaume Tromp");


  JTextField jTextFieldName4 = new JTextField("Trading Team (Sept. 2004 - May 2006) : Charles Montez, Christophe Grosjean, Clement Plaignaud");

  JTextField jTextFieldName3 = new JTextField("Thierry Curtil (June 2005 - August 2005)");
  JTextField jTextFieldName1 = new JTextField("EcoXP Team (Sept. 2003 - May 2005) : David Kissenberger, Franck Lasry, Franck Perez");
  JTextField jTextFieldName2 = new JTextField("Imad Daoudi, Julien Terrier, Thierry Curtil");
  JTextField jTextFieldName8 = new JTextField("Researchers : Olivier Brandouy, Remi Bachelet");
  JTextField jTextFieldName5 = new JTextField("Jeremy Streque, Tian Xia, Mohamed Hamamouchi");*/
  JLabel jLabelTeam = new JLabel();


  JLabel jLabelLogoEcoXP = new JLabel();
  JLabel jLabelLogoEcLille = new JLabel();
  JLabel jLabelLogoTrading = new JLabel();
  JLabel jLabelLogoMarket = new JLabel();
  JLabel jLabelJessXLogo = new JLabel();
  JLabel jLabelLogoUSTL = new JLabel();

  JTextArea jTextAreaModulesAnalysis = new JTextArea();

  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();

  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();

  String fileSeparator = System.getProperty("file.separator");

  public AbstractAboutBox(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    jTextFieldProductName.setBorder(null);
    jTextFieldProductName.setOpaque(false);
    jTextFieldProductName.setEditable(false);
    jTextFieldProductName.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldVersion.setBorder(null);
    jTextFieldVersion.setOpaque(false);
    jTextFieldVersion.setEditable(false);
    jTextFieldVersion.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldDate.setBorder(null);
    jTextFieldDate.setOpaque(false);
    jTextFieldDate.setEditable(false);
    jTextFieldDate.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldRessources.setBorder(null);
    jTextFieldRessources.setOpaque(false);
    jTextFieldRessources.setEditable(false);
    jTextFieldRessources.setHorizontalAlignment(SwingConstants.CENTER);

    /*jTextFieldName1.setBorder(null);
    jTextFieldName1.setOpaque(false);
    jTextFieldName1.setEditable(false);
    jTextFieldName1.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldName2.setBorder(null);
    jTextFieldName2.setOpaque(false);
    jTextFieldName2.setEditable(false);
    jTextFieldName2.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldName3.setBorder(null);
    jTextFieldName3.setOpaque(false);
    jTextFieldName3.setEditable(false);
    jTextFieldName3.setHorizontalAlignment(SwingConstants.CENTER);



    jTextFieldName4.setBorder(null);
    jTextFieldName4.setOpaque(false);
    jTextFieldName4.setEditable(false);
    jTextFieldName4.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldName5.setBorder(null);
    jTextFieldName5.setOpaque(false);
    jTextFieldName5.setEditable(false);
    jTextFieldName5.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldName6.setBorder(null);
    jTextFieldName6.setOpaque(false);
    jTextFieldName6.setEditable(false);
    jTextFieldName6.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldName7.setBorder(null);
    jTextFieldName7.setOpaque(false);
    jTextFieldName7.setEditable(false);
    jTextFieldName7.setHorizontalAlignment(SwingConstants.CENTER);

    jTextFieldName8.setBorder(null);
    jTextFieldName8.setOpaque(false);
    jTextFieldName8.setEditable(false);
    jTextFieldName8.setHorizontalAlignment(SwingConstants.CENTER);*/



    jTabbedPane1.setMinimumSize(new Dimension(560, 500));
    jTabbedPane1.setPreferredSize(new Dimension(560, 500));
    panelLicence.setLayout(gridBagLayout3);
    panelVersion.setLayout(gridBagLayout4);

    jScrollPane2.getViewport().add(this.getLicense());

    //AnalysisToolsCore.logger.info("Displaying analyzer about box.");
    String imagesDir = System.getProperty("user.dir") + fileSeparator +
        "images" + fileSeparator;

    //AnalysisToolsCore.logger.debug("Getting images...");
    logoJessX = new ImageIcon(new URL("file:" + imagesDir +
                                      "logo_JessX_small.PNG"));
    //AnalysisToolsCore.logger.info("- file:" + imagesDir + "logo_JessX_small.PNG");

    logoECLille = new ImageIcon(new URL("file:" + imagesDir +
                                        "ecllogo_small.PNG"));
    //AnalysisToolsCore.logger.info("- file:" + imagesDir + "ecllogo_small.PNG");

    logoUSTL = new ImageIcon(new URL("file:" + imagesDir + "ustl_small.PNG"));
    //AnalysisToolsCore.logger.info("- file:" + imagesDir + "ustl_small.PNG");

    logoEcoxp = new ImageIcon(new URL("file:" + imagesDir + "ecoxp_small.PNG"));
    //AnalysisToolsCore.logger.info("- file:" + imagesDir + "ecoxp_small.PNG");

    logoTrading = new ImageIcon(new URL("file:" + imagesDir +
                                        "logo_trading.PNG"));
    //AnalysisToolsCore.logger.info("- file:" + imagesDir + "trading_small.PNG");
    logoMarket = new ImageIcon(new URL("file:" + imagesDir +
                                        "market_logo.PNG"));


    this.setTitle("About");
    this.getContentPane().setLayout(gridBagLayout2);
    panelTeam.setLayout(gridBagLayout1);

    jLabelJessXLogo.setIcon(logoJessX);



    jLabelTeam.setText("<html><body><p><h1>Researchers</h1>Olivier BRANDOUY, R&eacute;mi BACHELET</p><p><h1>Development Teams</h1><u>ECOXP Team (2003 - 2005)</u><br>Thierry CURTIL (also June 2005 - September 2005), Imad DAOUDI, David KISSENBERGER<br>Franck LASRY, Franck PEREZ, Julien TERRIER<br></p><p><u>TRADING Team (2004 - 2006)</u><br>Christophe GROSJEAN, Mohamed HAMAMOUCHI, Charles MONTEZ<br>Cl&eacute;ment PLAIGNAUD, J&eacute;r&eacute;my STREQUE, Tian XIA<br></p><p><u>MARKET Team (2005 - 2007)</u><br>Etienne BROUTIN, Christophe BURLETT, Roger ISS<br>Benoit LEBEGUE, R&eacute;mi QUILLIET, Guillaume TROMP</p></body></html>");
    jLabelTeam.setVerticalAlignment(SwingConstants.TOP);
    jLabelTeam.setHorizontalAlignment(SwingConstants.CENTER);
    jLabelLogoEcLille.setIcon(logoECLille);
    jLabelLogoUSTL.setIcon(logoUSTL);


    jLabelLogoEcoXP.setIcon(logoEcoxp);
    jLabelLogoTrading.setIcon(logoTrading);
    jLabelLogoMarket.setIcon(logoMarket);


    this.getContentPane().add(jTabbedPane1,
                              new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    jTabbedPane1.add(panelVersion,"Version");
    jTabbedPane1.add(panelTeam,"Team");
    jTabbedPane1.add(panelLicence,"License");
    panelLicence.add(jScrollPane2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    panelVersion.add(jTextFieldRessources,
                     new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                                            , GridBagConstraints.CENTER,
                                            GridBagConstraints.BOTH,
                                            new Insets(5, 0, 5, 0), 0, 0));
    panelVersion.add(jTextFieldDate, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(5, 0, 5, 0), 0, 0));
    panelVersion.add(jTextFieldVersion, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(5, 0, 5, 0), 0, 0));
    panelVersion.add(jTextFieldProductName,
                     new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                                            , GridBagConstraints.CENTER,
                                            GridBagConstraints.BOTH,
                                            new Insets(5, 0, 5, 0), 0, 0));
    panelVersion.add(jLabelJessXLogo,
                     new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0
                                            , GridBagConstraints.CENTER,
                                            GridBagConstraints.NONE,
                                            new Insets(5, 5, 5, 5), 0, 0));
    panelTeam.add(jLabelLogoEcoXP, new GridBagConstraints(0, 8, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 5), 0, 0));
    panelTeam.add(jLabelLogoTrading,
                  new GridBagConstraints(1, 8, 1, 1, 1.0, 1.0
                                         , GridBagConstraints.CENTER,
                                         GridBagConstraints.NONE,
                                         new Insets(0, 0, 0, 5), 0, 0));
    panelTeam.add(jLabelLogoMarket,
                  new GridBagConstraints(2, 8, 1, 1, 1.0, 1.0
                                         , GridBagConstraints.CENTER,
                                         GridBagConstraints.NONE,
                                         new Insets(0, 0, 0, 5), 0, 0));

    panelTeam.add(jLabelLogoEcLille,
                  new GridBagConstraints(3, 8, 1, 1, 1.0, 1.0
                                         , GridBagConstraints.CENTER,
                                         GridBagConstraints.NONE,
                                         new Insets(0, 0, 0, 5), 0, 0));
    panelTeam.add(jLabelLogoUSTL, new GridBagConstraints(4, 8, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
   /* panelTeam.add(jTextFieldName6, new GridBagConstraints(0, 5, 5, 1, 1.0, 0.1
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));
    panelTeam.add(jTextFieldName7, new GridBagConstraints(0, 6, 5, 1, 1.0, 0.1
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));
    panelTeam.add(jTextFieldName8, new GridBagConstraints(0, 7, 5, 1, 1.0, 0.1
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));*/

    panelTeam.add(jLabelTeam, new GridBagConstraints(0, 0, 5, 1, 1.0, 0.1
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));
    /*
    panelTeam.add(jTextFieldName2, new GridBagConstraints(0, 1, 5, 1, 1.0, 0.1
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));
    panelTeam.add(jTextFieldName3, new GridBagConstraints(0, 2, 5, 1, 1.0, 0.1
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));
    panelTeam.add(jTextFieldName4, new GridBagConstraints(0, 3, 5, 1, 1.0, 0.1
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));
     panelTeam.add(jTextFieldName5, new GridBagConstraints(0, 4, 5, 1, 1.0, 0.1
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 5, 0), 0, 0));*/
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
    dispose();
    }
    super.processWindowEvent(e);
  }

  /**
   * implement this function to load into the textarea loaded modules infos
   */
  public abstract void loadModulesInfo();

  //Close the dialog on a button event
  public void actionPerformed(ActionEvent e) {
    dispose();
  }

  private JEditorPane getLicense() {
    String directory = System.getProperty("user.dir");
    directory =directory.subSequence(0, directory.lastIndexOf(fileSeparator) + 1).toString();
    JEditorPane license=new JEditorPane();
    try {
      license = new JEditorPane(new URL("file:" + directory +
          "GNU GENERAL PUBLIC LICENSE.txt"));
      license.setEditable(false);
    }
    catch (IOException ex1) {
      Utils.logger.error("GNU GENERAL PUBLIC LICENSE.txt not found.");
      license.setText("No text found. The file GNU GENERAL PUBLIC LICENSE.txt is absent.\nWrite to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA to receive a copy of the GNU General Public License.");
    }
    return license;
  }

  public void Analysis_AboutBoxEnabled() {
    jTextAreaModulesAnalysis.setBackground(SystemColor.inactiveCaptionText);
    jTextAreaModulesAnalysis.setBackground(new Color(255, 255, 255));
    jTextAreaModulesAnalysis.setFont(new java.awt.Font("Lucida Console", 0, 12));
    jTextAreaModulesAnalysis.setEditable(false);
    jTextAreaModulesAnalysis.setLineWrap(true);
    jTextAreaModulesAnalysis.setWrapStyleWord(true);

    this.loadModulesInfo();
    panelTeam.add(jScrollPane1, new GridBagConstraints(0, 8, 4, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(5, 5, 5, 5), 0, 0));

    jScrollPane1.getViewport().add(jTextAreaModulesAnalysis);
  }

  public JTextArea getModulesInfoTextArea() {
    return this.jTextAreaModulesAnalysis;
  }

}
