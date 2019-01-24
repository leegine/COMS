package jessx.gclient.gui;

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

import org.jdom.*;
import jessx.business.*;
import jessx.client.*;
import jessx.client.event.*;


import jessx.utils.*;
import jessx.utils.gui.JLabelRenderer;
import java.util.Hashtable;


/***************************************************************/
/*           Market Properties CLASS SECTION                 */
/***************************************************************/
/**
 * <p>Title : Market Properties</p>
 * <p>Description : </p>
 * @author Rémi Quilliet
 * @version 1.0
 */


public class MarketProperties extends JPanel
    implements Constants, OperatorPlayedListener,
    ExperimentDeveloppmentListener, NetworkListener, ConnectionListener {

   //JPanel PaneMarketProperties = new JPanel();
   //private static Hashtable evolutionGraphFactories = new Hashtable();
   MarketEvolutionGraph evolutionGraph = new MarketEvolutionGraph();
   TitledBorder titledBorder11;
   public MarketProperties() {
      jbInit();
  }

   public void jbInit() {
    titledBorder11 = new TitledBorder(BorderFactory.createEmptyBorder(6,0,0,0),"Market properties",
                                      TitledBorder.DEFAULT_JUSTIFICATION,
                                      TitledBorder.DEFAULT_POSITION,FONT_CLIENT_TITLE_BORDER);
    this.setBorder(titledBorder11);
    this.setMinimumSize(new Dimension(16, 10));
    this.setPreferredSize(new Dimension(291, 169));
    this.add(evolutionGraph);
   }
   public void addDeal(Deal deal) {
     evolutionGraph.addDeal(deal);
   }
   public void initGraphic() {
     evolutionGraph.initGraphic();
   }

    public void addAssetEvolution(String institutionName) {
      evolutionGraph.addAssetEvolution(institutionName);
    }

  public void newOperator(Operator op) {
  }

  public void experimentBegins() {
  }

  public void experimentFinished() {
  }

  public void periodBegins() {
  }

  public void periodFinished() {
  }

  public void connectionStateChanged(int newState) {
  }

  public void objectReceived(Document xmlObject) {
  }

}
