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

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import jessx.utils.*;

/***************************************************************/
/*           TStrippedLinesTableModel Class Section            */
/***************************************************************/
/**
 * <p>Title: TStrippedLinesTableModel</p>
 * <p>Description:</p>
 * @author Thierry Curtil, Julien Terrier
 * @version 1.0s
 */

public class TStrippedLinesTableModel extends DefaultTableModel implements Constants {
  public TStrippedLinesTableModel(Object[] columnNames, int rowCount) {
    super(columnNames, rowCount);
  }

  public void addRow(Object[] rowData) {
    if(rowData != null) {
      for(int i = 0 ; i < rowData.length ; i++) {
        if(!(rowData[i] instanceof JLabel)) {
          rowData[i] = convertToLabel(rowData[i], this.getRowCount());
        }
        else {
          ((JLabel)rowData[i]).setBackground(getRowColor(this.getRowCount()));
        }
      }
    }
    else {
      rowData = new Object[this.getColumnCount()];
      for(int i = 0; i < rowData.length ; i++) {
        rowData[i] = new JLabel();
        ((JLabel)rowData[i]).setText("");
        ((JLabel)rowData[i]).setBackground(getRowColor(this.getRowCount()));
      }
    }
    super.addRow(rowData);
  }

  public void setValueAt(Object object, int row, int col) {
    if(row == this.getRowCount()) {
      this.addRow((Object[])null);
    }

    if(object != null) {
      if (! (object instanceof JLabel)) {
        object = convertToLabel(object, row);
      }
    } else {
      object = new JLabel();
      ((JLabel)object).setBackground(getRowColor(this.getRowCount()));
    }

   super.setValueAt(object, row, col);

  }

  protected void setValueAtNoConversion(Object object, int row, int col) {
    super.setValueAt(object,row,col);
  }


  protected Color getRowColor(int row) {
    return row % 2 == 0 ? COLOR_EVEN_LINE : COLOR_ODD_LINE;
  }

  private JLabel convertToLabel(Object object, int row) {
    JLabel templabel = new JLabel(object.toString());
    templabel.setBackground(getRowColor(row));
    templabel.setFont(FONT_DEFAULT_LABEL);
    return templabel;
  }

  public boolean isCellEditable(int row, int col) {
    return false;
  }

}
