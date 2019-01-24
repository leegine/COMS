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

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/***************************************************************/
/*            JLabelRenderer INTERFACE SECTION                 */
/***************************************************************/
/**JLabelRenderer
 * <p>Title: JLabelRenderer</p>
 * <p>Description: </p>
 * @author T.E.A.M
 * @version 0.2
 */

public class JLabelRenderer extends JLabel implements TableCellRenderer {
    public JLabelRenderer() {
      setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object jLabel,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
      Color newColor;

      String newtext;
      Font newFont;
      if (jLabel != null) {
        newColor = ( (JLabel) jLabel).getBackground();
        newtext = ( (JLabel) jLabel).getText();
        newFont = ((JLabel)jLabel).getFont();
      }
      else {
        newColor = new Color(255,255,255);
        newtext = "";
        newFont = jessx.utils.Constants.FONT_DEFAULT_LABEL;
      }
      Color newColor2 = new Color(newColor.getRed(),newColor.getGreen(),newColor.getBlue(),128);
      setBackground(newColor2);
      setText(newtext);
      this.setHorizontalAlignment(this.CENTER);
      this.setVerticalAlignment(this.CENTER);
      this.setFont(newFont);
      return this;
    }
  }