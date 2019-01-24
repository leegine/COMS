package jessx.business.institutions;

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

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.jdom.*;
import jessx.business.*;
import jessx.business.operations.*;
import jessx.client.*;
import jessx.client.event.*;
import jessx.net.*;
import jessx.utils.*;
import jessx.utils.gui.*;

/***************************************************************/
/*         OrderMarketClientPanel CLASS SECTION                */
/***************************************************************/

/**
 * <p>Title : OrderMarketClientPanel</p>
 * <p>Description : </p>
 * @author Thierry Curtil, Rémi Quilliet
 * @version 1.0
 */

public class OrderMarketClientPanel
    extends JPanel
    implements NetworkListener {
  public OrderMarketClientPanel() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private JTable jTableWaitingOrders;
  private JTable jTableBid;
  private JTable jTableAsk;

  private CellFading cellFading;

  private Operator operator;

  private long lastTimeStamp;

  public OrderMarketClientPanel(Operator operator) {

    this.operator = operator;

    this.panelInit();

    Utils.logger.debug("Launching cell fading capabilities...");
    cellFading = new CellFading(jTableAsk, jTableBid);
    cellFading.start();

    Utils.logger.debug(
        "Registering the panel as an OrderBook object listener...");
    jessx.client.ClientCore.addNetworkListener(this, "OrderBook");

    Utils.logger.debug("Ordermarket panel init finished successfully.");
  }

  private void panelInit() {
    Utils.logger.debug("Initing the orderbook panel...");
    GridBagLayout gridBagLayoutWaitingOrders = new GridBagLayout();
    JSplitPane jSplitPane1 = new JSplitPane();
    JPanel jPanelWaitingOrders = new JPanel();
    JScrollPane jScrollPaneWaitingOrders = new JScrollPane();
    JPanel jPanelPortfolio = new JPanel();
    GridBagLayout gridBagLayout4 = new GridBagLayout();
    JScrollPane jScrollPaneBid = new JScrollPane();
    JScrollPane jScrollPaneAsk = new JScrollPane();
    TitledBorder titledBorder6;
    TitledBorder titledBorder13;
    //String[] enteteWaitingOrdersTable = {
    //    "Side", "Price (" + Constants.CURRENCY + ")", "Quantity", "Delete"};
    TWaitingOrdersModel waitingOrdersTableModel = new TWaitingOrdersModel(0, operator);
    jTableWaitingOrders = new JTable(waitingOrdersTableModel);
    String[] enteteBidTable = {
        "Bid Price", "Bid Quantity"};
    DefaultTableModel tableBidTableModel = new TOrderbookTableModel(
        enteteBidTable, operator.getOrderBookVisibility());
    jTableBid = new JTable(tableBidTableModel);
    String[] enteteAskTable1Table = {
        "Ask Price", "Ask Quantity"};
    DefaultTableModel tableAskTableModel = new TOrderbookTableModel(
        enteteAskTable1Table, operator.getOrderBookVisibility());
    jTableAsk = new JTable(tableAskTableModel);
    titledBorder6 = new TitledBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0),
                                     "My Pending Orders",
                                     TitledBorder.DEFAULT_JUSTIFICATION,
                                     TitledBorder.DEFAULT_POSITION,
                                     Constants.FONT_CLIENT_TITLE_BORDER);
    titledBorder13 = new TitledBorder(BorderFactory.createEmptyBorder(6, 0, 0,
        0), "Market Orderbook", TitledBorder.DEFAULT_JUSTIFICATION,
                                      TitledBorder.DEFAULT_POSITION,
                                      Constants.FONT_CLIENT_TITLE_BORDER);
    jPanelPortfolio.setBorder(titledBorder13);
    jPanelPortfolio.setLayout(gridBagLayout4);
    jPanelPortfolio.add(jScrollPaneBid,
                        new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.BOTH,
                                               new Insets(0, 0, 0, 0), 0, 0));
    jPanelPortfolio.add(jScrollPaneAsk,
                        new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
                                               , GridBagConstraints.EAST,
                                               GridBagConstraints.BOTH,
                                               new Insets(0, 0, 0, 0), 0, 0));
    jPanelWaitingOrders.setLayout(gridBagLayoutWaitingOrders);
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.setPreferredSize(new Dimension(500, 300));
    jSplitPane1.setBottomComponent(jPanelWaitingOrders);
    jSplitPane1.add(jPanelWaitingOrders, JSplitPane.BOTTOM);
    jPanelWaitingOrders.add(jScrollPaneWaitingOrders,
                            new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), -295, -391));
    jSplitPane1.add(jPanelPortfolio, JSplitPane.TOP);
    jSplitPane1.setDividerLocation(200);
    jPanelWaitingOrders.setBorder(titledBorder6);
    jPanelWaitingOrders.setMaximumSize(new Dimension(2048, 1536));
    jPanelWaitingOrders.setMinimumSize(new Dimension(100, 100));
    jPanelWaitingOrders.setPreferredSize(new Dimension(100, 150));
    jScrollPaneWaitingOrders.setVerticalScrollBarPolicy(JScrollPane.
        VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPaneWaitingOrders.setMinimumSize(new Dimension(24, 90));
    jScrollPaneWaitingOrders.setPreferredSize(new Dimension(454, 150));
    jScrollPaneWaitingOrders.getViewport().add(jTableWaitingOrders, null);
    //jTableWaitingOrders.setBackground(SystemColor.inactiveCaptionText);
    jTableWaitingOrders.setMinimumSize(new Dimension(0, 0));
    jTableWaitingOrders.setCellSelectionEnabled(true);
    jScrollPaneAsk.setVerticalScrollBarPolicy(JScrollPane.
                                              VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPaneBid.setVerticalScrollBarPolicy(JScrollPane.
                                              VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPaneBid.setPreferredSize(new Dimension(454, 400));
    jScrollPaneAsk.getViewport().add(jTableAsk, null);
    jScrollPaneBid.getViewport().add(jTableBid, null);
    jTableBid.setBackground(Constants.CLIENT_BUY_INACTIVE);
    jTableBid.setForeground(SystemColor.BLACK);
    jTableBid.setGridColor(SystemColor.BLACK);
    jTableBid.setMinimumSize(new Dimension(30, 80));
    jTableBid.setCellSelectionEnabled(false);
    jTableAsk.setBackground(Constants.CLIENT_SELL_INACTIVE);
    jTableAsk.setForeground(SystemColor.black);
    jTableAsk.setGridColor(SystemColor.black);
    jTableAsk.setColumnSelectionAllowed(false);
    jTableAsk.setRowSelectionAllowed(false);

    jTableAsk.getColumnModel().getColumn(0).setCellRenderer(new JLabelRenderer());
    jTableAsk.getColumnModel().getColumn(1).setCellRenderer(new JLabelRenderer());
    jTableBid.getColumnModel().getColumn(0).setCellRenderer(new JLabelRenderer());
    jTableBid.getColumnModel().getColumn(1).setCellRenderer(new JLabelRenderer());

    jTableWaitingOrders.getColumnModel().getColumn(0).setCellRenderer(new
        JLabelRenderer());
    jTableWaitingOrders.getColumnModel().getColumn(1).setCellRenderer(new
        JLabelRenderer());
    jTableWaitingOrders.getColumnModel().getColumn(2).setCellRenderer(new
        JLabelRenderer());

    if (operator.isGrantedTo((new DeleteOrder()).getOperationName())) {
      jTableWaitingOrders.getColumnModel().getColumn(3).setCellRenderer(new
          JButtonRenderer());
      jTableWaitingOrders.getColumnModel().getColumn(3).setCellEditor(new
          JButtonEditor());
    }

    jTableBid.setRowHeight(20);
    jTableAsk.setRowHeight(jTableBid.getRowHeight());
    jTableWaitingOrders.setRowHeight(18);

    jTableBid.setBackground(UIManager.getColor("Button.background"));
    jTableAsk.setBackground(UIManager.getColor("Button.background"));

    this.setLayout(new GridBagLayout());
    this.add(jSplitPane1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
                                                 GridBagConstraints.CENTER,
                                                 GridBagConstraints.BOTH,
                                                 new Insets(0, 0, 0, 0), 0, 0));
    Utils.logger.debug("Init finished.");

  }

  private long updateObTable(Vector sideOrders, JTable table, Color iColor,
                             Color interColor, Color fColor, int side,
                             long tempLastTS) {

    int indiceOb = 0, indiceTable = 0;
    boolean isOrderOurs = false;
    int qttySum = 0;
    long tempLastTimeStamp = tempLastTS;

    Utils.logger.debug("Updating " + ( (side == Order.ASK) ? "ask" : "bid") +
                       " side...");

    while ( (indiceOb < sideOrders.size()) &&
           (indiceTable < this.operator.getOrderBookVisibility())) {

      if ( (indiceOb < sideOrders.size() - 1) &&
          ( ( (Order) sideOrders.elementAt(indiceOb)).getOrderPrice(side) ==
           ( (Order) sideOrders.elementAt(indiceOb + 1)).getOrderPrice(side))) {
        qttySum += ( (Order) (sideOrders.elementAt(indiceOb))).getMaxQtty();

        if ( ( (Order) sideOrders.elementAt(indiceOb)).getEmitter().equals(
            jessx.client.ClientCore.getLogin())) {
          isOrderOurs = true;
        }
      }
      else {

        if ( ( (Order) sideOrders.elementAt(indiceOb)).getEmitter().equals(
            jessx.client.ClientCore.getLogin())) {
          isOrderOurs = true;
        }

        if ( ( (Order) sideOrders.elementAt(indiceOb)).getTimestamp() >
            tempLastTimeStamp) {
          tempLastTimeStamp = ( (Order) sideOrders.elementAt(indiceOb)).
              getTimestamp();
        }

        if ( ( (Order) sideOrders.elementAt(indiceOb)).getTimestamp() >
            this.lastTimeStamp) {
          table.setValueAt(Utils.createColoredLabel( (isOrderOurs ? "" : "") +
              (new
               Float( ( (Order) (sideOrders.elementAt(indiceOb))).
                     getOrderPrice(side))).toString() + (isOrderOurs ? "" : ""),
                                                Constants.FONT_DEFAULT_LABEL,
                                                iColor), indiceTable, 0);
          table.setValueAt(Utils.createColoredLabel( (isOrderOurs ? "" : "") +
              (new
               Integer( ( (Order) (sideOrders.elementAt(indiceOb))).getMaxQtty() +
                       qttySum).toString() + (isOrderOurs ? "" : "")),
                                                Constants.FONT_DEFAULT_LABEL,
                                                iColor), indiceTable, 1);

          this.cellFading.registerCell(side, 0, indiceTable, iColor,
                                       isOrderOurs ? interColor : fColor);
          this.cellFading.registerCell(side, 1, indiceTable, iColor,
                                       isOrderOurs ? interColor : fColor);
        }
        else {
          Utils.logger.debug("displaying value: qtty=" + qttySum);

          table.setValueAt(Utils.createColoredLabel( (isOrderOurs ? "" : "") +
              (new
               Float( ( (Order) (sideOrders.elementAt(indiceOb))).
                     getOrderPrice(side))).toString() + (isOrderOurs ? "" : ""),
                                                Constants.FONT_DEFAULT_LABEL,
                                                isOrderOurs ? interColor :
                                                fColor), indiceTable, 0);
          table.setValueAt(Utils.createColoredLabel( (isOrderOurs ? "" : "") +
              (new
               Integer( ( (Order) (sideOrders.elementAt(indiceOb))).getMaxQtty() +
                       qttySum).toString() + (isOrderOurs ? "" : "")),
                                                Constants.FONT_DEFAULT_LABEL,
                                                isOrderOurs ? interColor :
                                                fColor), indiceTable, 1);
        }

        isOrderOurs = false;
        indiceTable++;
        qttySum = 0;
      }
      indiceOb++;
    }

    for (int i = indiceTable; i < table.getRowCount(); i++) {
      table.setValueAt(Utils.createColoredLabel("",
                                                Constants.FONT_DEFAULT_LABEL,
                                                fColor), i, 0);
      table.setValueAt(Utils.createColoredLabel("",
                                                Constants.FONT_DEFAULT_LABEL,
                                                fColor), i, 1);
    }
    table.tableChanged(new TableModelEvent(table.getModel()));

    return tempLastTimeStamp;
  }

  public void updateClientInterface(OrderBook orderBook) {

    long tempLastTimeStamp = this.updateObTable(orderBook.getBid(),
                                                this.jTableBid,
                                                Constants.CLIENT_BUY_HL,
                                                Constants.
                                                CLIENT_BUY_INTERMEDIARY,
                                                Constants.CLIENT_BUY_INACTIVE,
                                                Order.BID, 0);
    tempLastTimeStamp = this.updateObTable(orderBook.getAsk(), this.jTableAsk,
                                           Constants.CLIENT_SELL_HL,
                                           Constants.CLIENT_SELL_INTERMEDIARY,
                                           Constants.CLIENT_SELL_INACTIVE,
                                           Order.ASK, tempLastTimeStamp);

    this.lastTimeStamp = tempLastTimeStamp;

    // updating the table where the client can see all its orders
    //logger.debug("Displaying our order in the 'waiting orders' table...");
    int i = 0; // line where we are going to display our orders info
    //logger.debug("ask");
    for (int j = 0; j < orderBook.getAsk().size(); j++) {
      if ( ( (Order) orderBook.getAsk().elementAt(j)).getEmitter().equals(jessx.
          client.ClientCore.getLogin())) {
        // it's our order, display that: assetname, side, price, qtty
        Order tempOrder = (Order) orderBook.getAsk().elementAt(j);
        this.jTableWaitingOrders.setValueAt("Ask", i, 0);
        this.jTableWaitingOrders.setValueAt(new Float(tempOrder.getOrderPrice(
            Order.ASK)), i, 1);
        this.jTableWaitingOrders.setValueAt(new Integer(tempOrder.getMaxQtty()),
                                            i, 2);

        if (operator.isGrantedTo((new DeleteOrder()).getOperationName())) {
          this.jTableWaitingOrders.setValueAt(new JOrderButton(tempOrder), i, 3);
        }

        i++; // next line
      }
    }
    //logger.debug("bid");
    for (int j = 0; j < orderBook.getBid().size(); j++) {
      if ( ( (Order) orderBook.getBid().elementAt(j)).getEmitter().equals(jessx.
          client.ClientCore.getLogin())) {
        // it's our order, display that: assetname, side, price, qtty
        Order tempOrder = (Order) orderBook.getBid().elementAt(j);
        this.jTableWaitingOrders.setValueAt("Bid", i, 0);
        this.jTableWaitingOrders.setValueAt(new Float(tempOrder.getOrderPrice(
            Order.BID)), i, 1);
        this.jTableWaitingOrders.setValueAt(new Integer(tempOrder.getMaxQtty()),
                                            i, 2);
        if (operator.isGrantedTo((new DeleteOrder()).getOperationName())) {
          this.jTableWaitingOrders.setValueAt(new JOrderButton(tempOrder), i, 3);
        }
        i++; // next line
      }
    }

    // Then we blank the following lines

    for (int k = this.jTableWaitingOrders.getRowCount() - 1; k >= i; k--) {
      this.jTableWaitingOrders.setValueAt(null, k, 0);
    }

    this.jTableWaitingOrders.tableChanged(new TableModelEvent(this.
        jTableWaitingOrders.getModel()));
  }

  public void objectReceived(Document root) {
    if (root.getRootElement().getName().equals("OrderBook")) {
      OrderBook ob = new OrderBook();
      Utils.logger.debug("Reading orderbook...");
      ob.initFromNetworkInput(root.getRootElement());
      Utils.logger.debug("This is the orderbook of the institution: " +
                         ob.getInstitution());
      Utils.logger.debug(
          "This panel is displaying the orderbook of the institution: " +
          this.operator.getInstitution());
      if (ob.getInstitution().equals(operator.getInstitution())) {

        /* try {
          (new XMLOutputter(Format.getPrettyFormat())).output(root.
              getRootElement(),
              System.out);
                 }
                 catch (IOException ex) {
                 } */

        Utils.logger.debug("Updating panels...");
        this.updateClientInterface(ob);
      }
    }
  }

  private void jbInit() throws Exception {
  }

}

/***************************************************************/
/*              JButtonEditor Classes SECTION                  */
/***************************************************************/
class JButtonEditor
    extends AbstractCellEditor
    implements TableCellEditor,
    ActionListener {
  JOrderButton button;
  JLabel componentDisplayed;

  protected static final String EDIT = "edit";

  public JButtonEditor() {
    button = new JOrderButton(null);
  }

  public void actionPerformed(ActionEvent e) {
    Utils.logger.debug("Clic! Bouton delete");
    if (EDIT.equals(e.getActionCommand())) {
      // The user has clicked the cell
      fireEditingStopped();

      try {
        Order order = button.getOrder();
          DeleteOrder deleteOrder = new DeleteOrder(order.getId());
          deleteOrder.setEmitter(ClientCore.getLogin());
          deleteOrder.setInstitutionName(order.getInstitutionName());
          ClientCore.send(deleteOrder);
      }
      catch ( /*OperationNotCreated*/Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  //Implement the one CellEditor method that AbstractCellEditor doesn't.
  public Object getCellEditorValue() {
    return button;
  }

  //Implement the one method defined by TableCellEditor.
  public Component getTableCellEditorComponent(JTable table,
                                               Object value,
                                               boolean isSelected,
                                               int row,
                                               int column) {

    button = (JOrderButton) value;

    if (button != null) {
      button.removeActionListener(this);
      button.setActionCommand(EDIT);
      button.addActionListener(this);
      button.setBorderPainted(false);
    }

    return button;
  }
}

class JOrderButton extends JButton {

  private Order order;

  public Order getOrder() {
    return this.order;
  }

  JOrderButton(Order order) {
    super();
    this.order = order;
    this.setText("Delete");
  }
}

class CellFading
    extends Thread {

  private Vector cells = new Vector();

  private JTable jTableAsk;
  private JTable jTableBid;

  static int STATE_NUM = 10; // 10 transition colors -> each stay 125.3 ms (example)
  static int DURATION = 500; // in ms

  public CellFading(JTable ask, JTable bid) {
    this.jTableAsk = ask;
    this.jTableBid = bid;
  }

  public void run() {

    while (true) {
      try {
        sleep(DURATION / STATE_NUM);
      }
      catch (InterruptedException ex) {
      }

      for (int i = 0; i < cells.size(); i++) {

        ColoredCell tempCell = (ColoredCell)this.cells.elementAt(i);

        tempCell.nextColor();

        if (tempCell.isBidSide()) {
          ( (JLabel)this.jTableBid.getValueAt(tempCell.getRow(),
                                              tempCell.getCol())).setBackground(
              tempCell.getCurrentColor());
        }
        else {
          ( (JLabel)this.jTableAsk.getValueAt(tempCell.getRow(),
                                              tempCell.getCol())).setBackground(
              tempCell.getCurrentColor());
        }

        if (tempCell.getStep() >= STATE_NUM) {
          this.cells.removeElementAt(i);
          i--;
        }
      }
      jTableAsk.tableChanged(new TableModelEvent(jTableAsk.getModel()));
      jTableBid.tableChanged(new TableModelEvent(jTableBid.getModel()));
    }
  }

  public void registerCell(int side, int col, int row, Color iColor,
                           Color fColor) {
    this.cells.add(new ColoredCell(side, col, row, iColor, fColor, STATE_NUM));
  }
}

class ColoredCell {
  private int side;
  private int col;
  private int row;
  private int iRed;
  private int iGreen;
  private int iBlue;
  private int fRed;
  private int fGreen;
  private int fBlue;

  private int stateNum;

  private int step = 0;

  private Color cColor;

  public boolean isBidSide() {
    return side == Order.BID;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public int getStep() {
    return this.step;
  }

  public Color getCurrentColor() {
    return this.cColor;
  }

  public ColoredCell(int side, int col, int row, Color iColor, Color fColor,
                     int stateNum) {
    this.side = side;
    this.col = col;
    this.row = row;
    this.stateNum = stateNum;
    iRed = iColor.getRed();
    iGreen = iColor.getGreen();
    iBlue = iColor.getBlue();
    fRed = fColor.getRed();
    fGreen = fColor.getGreen();
    fBlue = fColor.getBlue();

    this.cColor = iColor;
  }

  public void nextColor() {
    int cBlue = cColor.getBlue();
    int cRed = cColor.getRed();
    int cGreen = cColor.getGreen();
    cBlue = iBlue + (fBlue - iBlue) * step / this.stateNum;
    cRed = iRed + (fRed - iRed) * step / this.stateNum;
    cGreen = iGreen + (fGreen - iGreen) * step / this.stateNum;
    cColor = new Color(cRed, cGreen, cBlue);
    step++;
  }
}

/**
  private long updateObTable(Vector sideOrders, JTable table, Color iColor, Color interColor, Color fColor, int clientNum, boolean bidSide, long tempLastTS) {
    int indiceOb = 0, indiceTable = 0;
    boolean isOrderOurs = false;
    int qttySum = 0;
    long tempLastTimeStamp = tempLastTS;

    while ((indiceOb < sideOrders.size()) && (indiceTable < 5)) {

      if ((indiceOb < sideOrders.size() - 1) &&
          (((Order) sideOrders.elementAt(indiceOb)).price == ((Order) sideOrders.elementAt(indiceOb + 1)).price)) {
        qttySum += ( (Order) (sideOrders.elementAt(indiceOb))).quantity;

        if (((Order)sideOrders.elementAt(indiceOb)).idClient == clientNum) {
          isOrderOurs = true;
        }
      }
      else {

        if (((Order)sideOrders.elementAt(indiceOb)).idClient == clientNum) {
          isOrderOurs = true;
        }

 if (((Order)sideOrders.elementAt(indiceOb)).sentTime > tempLastTimeStamp) {
          tempLastTimeStamp = ((Order)sideOrders.elementAt(indiceOb)).sentTime;
        }

 if (((Order)sideOrders.elementAt(indiceOb)).sentTime > this.lastTimeStamp) {
          table.setValueAt(Utils.createColoredLabel((isOrderOurs ? "" : "") + (new Float(((Order)(sideOrders.elementAt(indiceOb))).price)).toString() + (isOrderOurs ? "" : ""),Constants.FONT_DEFAUT_LABEL , iColor),indiceTable,0);
          table.setValueAt(Utils.createColoredLabel((isOrderOurs ? "" : "") + (new Integer(((Order)(sideOrders.elementAt(indiceOb))).quantity + qttySum).toString() + (isOrderOurs ? "" : "")),Constants.FONT_DEFAUT_LABEL,iColor), indiceTable, 1);

          this.cellFading.registerCell(bidSide, 0, indiceTable, iColor,isOrderOurs ? interColor : fColor);
          this.cellFading.registerCell(bidSide, 1, indiceTable, iColor,isOrderOurs ? interColor : fColor);
        }
        else {
          table.setValueAt(Utils.createColoredLabel((isOrderOurs ? "" : "") + (new Float(((Order)(sideOrders.elementAt(indiceOb))).price)).toString() + (isOrderOurs ? "" : ""),Constants.FONT_DEFAUT_LABEL , isOrderOurs ? interColor : fColor),indiceTable,0);
          table.setValueAt(Utils.createColoredLabel((isOrderOurs ? "" : "") + (new Integer(((Order)(sideOrders.elementAt(indiceOb))).quantity + qttySum).toString() + (isOrderOurs ? "" : "")),Constants.FONT_DEFAUT_LABEL,isOrderOurs ? interColor : fColor), indiceTable, 1);
        }


        isOrderOurs = false;
        indiceTable++;
        qttySum = 0;
      }
      indiceOb++;
    }

    for (int i = indiceTable; i < 5; i++) {
      table.setValueAt(Utils.createColoredLabel("",Constants.FONT_DEFAUT_LABEL,fColor), i, 0);
      table.setValueAt(Utils.createColoredLabel("",Constants.FONT_DEFAUT_LABEL,fColor), i, 1);
    }
    table.tableChanged(new TableModelEvent(table.getModel()));

    return tempLastTimeStamp;
  }

  public void updateClientInterface(OrderBook orderBook, int clientNum) {

    long tempLastTimeStamp = this.updateObTable(orderBook.bid,this.jTableBid,Constants.CLIENT_BUY_HL,Constants.CLIENT_BUY_INTERMEDIARY,Constants.CLIENT_BUY_INACTIVE,clientNum,true, 0);
    tempLastTimeStamp = this.updateObTable(orderBook.ask,this.jTableAsk,Constants.CLIENT_SELL_HL,Constants.CLIENT_SELL_INTERMEDIARY,Constants.CLIENT_SELL_INACTIVE,clientNum,false, tempLastTimeStamp);

    this.lastTimeStamp = tempLastTimeStamp;

    // updating the table where the client can see all its orders
    //logger.debug("Displaying our order in the 'waiting orders' table...");
    int i = 0; // line where we are going to display our orders info
    //logger.debug("ask");
    for (int j = 0; j < orderBook.ask.size(); j++) {
      if ( ( (Order) orderBook.ask.elementAt(j)).idClient == clientNum) {
        // it's our order, display that: assetname, side, price, qtty
        Order tempOrder = (Order) orderBook.ask.elementAt(j);
        this.jTableWaitingOrders.setValueAt("Ask", i, 0);
        this.jTableWaitingOrders.setValueAt(new Float(tempOrder.price), i, 1);
 this.jTableWaitingOrders.setValueAt(new Integer(tempOrder.quantity), i, 2);
        this.jTableWaitingOrders.setValueAt(new JOrderButton(tempOrder), i, 3);

        i++; // next line
      }
    }
    //logger.debug("bid");
    for (int j = 0; j < orderBook.bid.size(); j++) {
      if ( ( (Order) orderBook.bid.elementAt(j)).idClient == clientNum) {
        // it's our order, display that: assetname, side, price, qtty
        Order tempOrder = (Order) orderBook.bid.elementAt(j);
        this.jTableWaitingOrders.setValueAt("Bid", i, 0);
        this.jTableWaitingOrders.setValueAt(new Float(tempOrder.price), i, 1);
 this.jTableWaitingOrders.setValueAt(new Integer(tempOrder.quantity), i, 2);
        this.jTableWaitingOrders.setValueAt(new JOrderButton(tempOrder), i, 3);
        i++; // next line
      }
    }

    // Then we blank the following lines

    for (int k = this.jTableWaitingOrders.getRowCount() - 1; k >= i ; k--) {
      this.jTableWaitingOrders.setValueAt(null, k, 0);
    }

    this.jTableWaitingOrders.tableChanged(new TableModelEvent(this.jTableWaitingOrders.getModel()));
  }

  public void scenarioChanged(Scenario newScenario) {

  }

  public float costPerPeriod(int period) {
    return 0;
  }

  public float buyingCost(int period) {
    return 0;
  }

  public float sellingCost(int period) {
    return 0;
  }

  public float dividend(int period) {
    return aDividend(period);
  }

  public float aDividend(int session) {
    return ((Dividend)this.dividends.elementAt(session)).aDividend();
  }


  /**
  *
  * @param session int : the session number betwwen 0 and the numberOfLastSession - 1
  * @return String : the properties as thez should appear on the screen of a
  * client.
  * @todo rename session <=> period in variables
  */
 /*
    public void updateAssetProperties(int session) {
      String s = "";
      if (session < this.dividends.size()) {
        s += "Mean dividends for this period: "+CURRENCY + ((Dividend)this.dividends.elementAt(session)).getMean()+"\n";
        s += "Variance for this period: " +CURRENCY+ ((Dividend)this.dividends.elementAt(session)).getVariance()+"\n\n";
        s += "Holding value: "+CURRENCY;
        float sum = this.terminalValue;
        for( int i = session; i < this.dividends.size(); i++) {
   sum += ((Dividend)this.dividends.elementAt(i)).getMean();
        }
        s += Float.toString(sum)+"\n";
      }
      super.getAssetDescriptionField().setText(s);
    }

    public void initAssetForExperiment() {
      this.extractDividendsFromTable();
    }

    public synchronized void performAction(Market market, Action action) {
      System.out.println("Stock - Receiving action... Treating it.");
      if (action instanceof Order) {

        // timestamping the order
        ((Order)action).sentTime = market.getTimeInSession();

        // saving the order to log.
        market.saveToLog((Order)action);

        // inserting the order into the order book
  super.getOrderBook().insertOrder((Order)action, market.getClients(), market);

        // logging the order book
        market.saveToLog(super.getOrderBook());

        // forwarding to client the orderbook
        market.send(super.getOrderBook());
      } else if (action instanceof DeleteOrder) {
        // deleting order from OrderBook
        super.getOrderBook().deleteOrder(((DeleteOrder)action).getOrder());
        market.send(super.getOrderBook());
      }
    }


    ///////////////////////////////////
    // protected and private methods

    /**
   * will extrat the dividends from the table.
   * Calls that function when you're sure the user won't modify the dividends
   * anymore.
   */
  /*
     protected void extractDividendsFromTable() {
       for(int i = 0; i < this.jTable_AssetsProperties.getRowCount() -1; i++) {
   this.dividends.addElement(new Dividend(Integer.parseInt((String)this.jTable_AssetsProperties.getValueAt(i,0)),
   Float.parseFloat((String)this.jTable_AssetsProperties.getValueAt(i,2)),
   Float.parseFloat((String)this.jTable_AssetsProperties.getValueAt(i,3))));

       }

       // we won't need those from this point on... (because the experiment is starting)
       // as we are going to write this object on the network, it's faster not to
       // send the interface at the same time ^^
       this.jTable_AssetsProperties = null;
       this.properties = null;
     }

     public String getAssetName() {
       return super.getAssetName();
     }

     public OrderBook getOrderBook() {
       return super.getOrderBook();
     }

   */
