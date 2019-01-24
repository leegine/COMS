package jessx.server.gui;

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
/*                       IMPORT SECTION                        */
/***************************************************************/

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.*;

/***************************************************************/
/*              DividendInfoPanel CLASS SECTION                */
/***************************************************************/
/**
 * <p>Title : DividendInfoPanel</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class DividendInfoPanel extends JPanel implements DividendInfoListener, ChangeListener {

  private DividendLimitation divInfo;

  private JSpinner jSpinner1 = new JSpinner();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JLabel jLabel1 = new JLabel();
  private JPanel jPanel1 = new JPanel();
  private JRadioButton jRadioButton1 = new JRadioButton();
  private GridBagLayout gridBagLayout2 = new GridBagLayout();
  private JRadioButton jRadioButton2 = new JRadioButton();
  private JRadioButton jRadioButton3 = new JRadioButton();
  private JRadioButton jRadioButton0 = new JRadioButton();
  private int stateOfButtons0123=1;//1 if jRadioButton1 is selected,2 if jRadioButton2 and 3...
  private ButtonGroup buttonGroup1 = new ButtonGroup();
  private JCheckBox jCheckBox1 = new JCheckBox();
  private JCheckBox jCheckBox2 = new JCheckBox();
  private JCheckBox jCheckBox3 = new JCheckBox();
  private JCheckBox jCheckBox4 = new JCheckBox();
  private JCheckBox jCheckBox5 = new JCheckBox();
  private boolean first = true;
  private Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(148, 145, 140));
  private Border border2 = new TitledBorder(border1, "Dividend Detailled Information");

  public DividendInfoPanel(DividendLimitation divInfo) {

    this.divInfo = divInfo;

    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    this.divInfo.addListener(this);
    this.updateUIFromModel();
    this.jSpinner1.addChangeListener(this);
    this.jRadioButton1.addChangeListener(this);
    this.jRadioButton2.addChangeListener(this);
    this.jRadioButton3.addChangeListener(this);
    this.jRadioButton0.addChangeListener(this);
    this.jCheckBox1.addChangeListener(this);
    this.jCheckBox2.addChangeListener(this);
    this.jCheckBox3.addChangeListener(this);
    this.jCheckBox4.addChangeListener(this);
    this.jCheckBox5.addChangeListener(this);
  }

  public void dividendInfoModified(DividendInfoEvent e) {
    if (e.getEvent() == DividendInfoEvent.DIVIDEND_INFO_UPDATED) {
      this.updateUIFromModel();
    }
  }

  public void stateChanged(ChangeEvent e) {
    if (e.getSource() instanceof JSpinner) {
      Utils.logger.debug("jSpinner state changed. new value entered: " + ((Integer)jSpinner1.getValue()).intValue() + ". Updating model.");
      this.divInfo.setWindowSize(((Integer)jSpinner1.getValue()).intValue());
    }
    else if (e.getSource() instanceof JCheckBox) {

      if (((JCheckBox)e.getSource()).getName().equalsIgnoreCase(jCheckBox1.getName())) {
        if (this.jCheckBox1.isSelected() != this.divInfo.isDisplayingSessionLength()) {
          Utils.logger.debug("jCheckBox1 state changed. new value: " + this.jCheckBox1.isSelected() + ". Updating model.");
          this.divInfo.setDisplaySessionLength(this.jCheckBox1.isSelected());
        }
      }
      else if (((JCheckBox)e.getSource()).getName().equalsIgnoreCase(jCheckBox2.getName())) {
        if (this.jCheckBox2.isSelected() != this.divInfo.isDisplayingWindowSize()) {
          Utils.logger.debug("jCheckBox2 state changed. new value: " + this.jCheckBox2.isSelected() + ". Updating model.");
          this.divInfo.setDisplayWindowSize(this.jCheckBox2.isSelected());
        }
      }
      else if (((JCheckBox)e.getSource()).getName().equalsIgnoreCase(jCheckBox3.getName())) {
        if (this.jCheckBox3.isSelected() != this.divInfo.isDisplayingHoldingValueForExperiment()) {
          Utils.logger.debug("jCheckBox3 state changed. new value: " + this.jCheckBox3.isSelected() + ". Updating model.");
          this.divInfo.setDisplayHoldingValueForExperiment(this.jCheckBox3.isSelected());
        }
      }
      else if (((JCheckBox)e.getSource()).getName().equalsIgnoreCase(jCheckBox4.getName())) {
        if (this.jCheckBox4.isSelected() !=
            this.divInfo.isDisplayHoldingValueForWindow()) {
          Utils.logger.debug("jCheckBox4 state changed. new value: " +
                             this.jCheckBox4.isSelected() + ". Updating model.");
          this.divInfo.setDisplayHoldingValueForWindow(this.jCheckBox4.
              isSelected());
        }
      }
      else if (((JCheckBox)e.getSource()).getName().equalsIgnoreCase(jCheckBox5.getName())) {
        if (this.jCheckBox5.isSelected() != this.divInfo.isDisplayingOperationsCosts()) {
          Utils.logger.debug("jCheckBox5 state changed. new value: " + this.jCheckBox5.isSelected() + ". Updating model.");
          this.divInfo.setDisplayOperationsCosts(this.jCheckBox5.isSelected());
          Utils.logger.info("couts des operations " + this.jCheckBox5.isSelected());
        }
      }
    }
    else if (e.getSource() instanceof JRadioButton) {
      Utils.logger.debug("jRadioButton state changed.");
      int previousState=stateOfButtons0123;
      if (this.jRadioButton1.isSelected()) {
        stateOfButtons0123 = 1;
      }
      else if (this.jRadioButton2.isSelected()) {
        stateOfButtons0123 = 2;
      }
      else if (this.jRadioButton3.isSelected()) {
        stateOfButtons0123 = 3;
      }
      else if (this.jRadioButton0.isSelected()) {
        stateOfButtons0123 = 0;
      }
      if(previousState==stateOfButtons0123)
        this.divInfo.setDividendDetailledproperties(stateOfButtons0123);
    }
  }


  public void desactive() {
    this.jSpinner1.setEnabled(false);
    this.jRadioButton0.setEnabled(false);
    this.jRadioButton1.setEnabled(false);
    this.jRadioButton2.setEnabled(false);
    this.jRadioButton3.setEnabled(false);
    this.jCheckBox1.setEnabled(false);
    this.jCheckBox2.setEnabled(false);
    this.jCheckBox3.setEnabled(false);
    this.jCheckBox4.setEnabled(false);
    this.jCheckBox5.setEnabled(false);
  }

  public void active() {
    this.jSpinner1.setEnabled(true);
    this.jRadioButton0.setEnabled(true);
    this.jRadioButton1.setEnabled(true);
    this.jRadioButton2.setEnabled(true);
    this.jRadioButton3.setEnabled(true);
    this.jCheckBox1.setEnabled(true);
    this.jCheckBox2.setEnabled(true);
    this.jCheckBox3.setEnabled(true);
    this.jCheckBox4.setEnabled(true);
    this.jCheckBox5.setEnabled(true);
  }


  private void updateUIFromModel() {
    this.jSpinner1.setValue(new Integer(this.divInfo.getWindowSize()));
    if (first){
      first=false;
      if (1 == this.divInfo.getDividendDetailledproperties()) {
        this.jRadioButton1.setSelected(true);
      }
      else {
        if (2 == this.divInfo.getDividendDetailledproperties()) {
          this.jRadioButton2.setSelected(true);
        }
        else {
          if (3 == this.divInfo.getDividendDetailledproperties()) {
            this.jRadioButton3.setSelected(true);
          }
          else this.jRadioButton0.setSelected(true);
        }
      }
    }
    this.jCheckBox1.setSelected(this.divInfo.isDisplayingSessionLength());
    this.jCheckBox2.setSelected(this.divInfo.isDisplayingWindowSize());
    this.jCheckBox3.setSelected(this.divInfo.isDisplayingHoldingValueForExperiment());
    this.jCheckBox4.setSelected(this.divInfo.isDisplayHoldingValueForWindow());
    this.jCheckBox5.setSelected(this.divInfo.isDisplayingOperationsCosts());
  }

  /**
   * @throws Exception
   */
  private void jbInit() throws Exception {
    this.jSpinner1.setModel(new SpinnerNumberModel(1, 1, 100, 1));
    this.setLayout(gridBagLayout1);
    jLabel1.setText("Window size : ");
    jPanel1.setBorder(border2);
    jPanel1.setPreferredSize(new Dimension(200, 73));
    jPanel1.setInputVerifier(null);
    jPanel1.setLayout(gridBagLayout2);
    jRadioButton0.setMinimumSize(new Dimension(5, 23));
    jRadioButton0.setText("Show them for each period of the experiment");
    jRadioButton1.setMinimumSize(new Dimension(5, 23));
    jRadioButton1.setText("Show them only for the next period");
    jRadioButton2.setMinimumSize(new Dimension(5, 23));
    jRadioButton2.setText("Show them for each period of the window");
    jRadioButton3.setMinimumSize(new Dimension(5, 23));
    jRadioButton3.setText("Do not show anything");
    jCheckBox1.setText("Show the number of periods in the experiment");
    jCheckBox2.setText("Show the number of periods in the window");
    jCheckBox3.setText("Show the holding value for the experiment");
    jCheckBox4.setText("Show the holding value for the window");
    jCheckBox5.setText("Show the costs of the operations");
    jCheckBox1.setName("jCheckBox1");
    jCheckBox1.setMinimumSize(new Dimension(243, 23));
    jCheckBox2.setName("jCheckBox2");
    jCheckBox2.setMinimumSize(new Dimension(5, 23));
    jCheckBox3.setName("jCheckBox3");
    jCheckBox3.setMinimumSize(new Dimension(5, 23));
    jCheckBox4.setName("jCheckBox4");
    jCheckBox4.setMinimumSize(new Dimension(5, 23));
    jCheckBox5.setName("jCheckBox5");
    jCheckBox5.setMinimumSize(new Dimension(5, 23));
    jPanel1.add(jRadioButton0,         new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jRadioButton1,         new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jRadioButton2,     new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jRadioButton3, new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    buttonGroup1.add(jRadioButton0);
    buttonGroup1.add(jRadioButton1);
    buttonGroup1.add(jRadioButton2);
    buttonGroup1.add(jRadioButton3);
    this.add(jSpinner1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(6, 3, 3, 6), 40, 0));
    this.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.EAST,
                                             GridBagConstraints.NONE,
                                             new Insets(6, 6, 3, 3), 0, 0));
    this.add(jCheckBox1, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.NONE,
                                                new Insets(3, 6, 3, 6), 0, 0));
    this.add(jCheckBox2, new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(3, 6, 3, 6), 0, 0));
    this.add(jCheckBox3, new GridBagConstraints(0, 4, 2, 1, 1.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(3, 6, 3, 6), 0, 0));
    this.add(jCheckBox4, new GridBagConstraints(0, 5, 2, 1, 1.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(3, 6, 3, 6), 0, 0));
    this.add(jCheckBox5, new GridBagConstraints(0, 6, 3, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.HORIZONTAL,
                                                new Insets(3, 6, 3, 6), 185, 0));
    this.add(jPanel1, new GridBagConstraints(0, 3, 2, 1, 1.0, 0.0
                                             , GridBagConstraints.CENTER,
                                             GridBagConstraints.HORIZONTAL,
                                             new Insets(3, 6, 3, 6), 0, 47));
  }

}
