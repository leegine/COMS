package jessx.automate.generator;

/**
 * <p>Titre : </p>
 *
 * <p>Description : </p>
 *
 * <p>Copyright : Copyright (c) 2005, Licence GPL, </p>
 *
 * <p>Société : Ec-lille, USTL</p>
 *
 * @author Guillaume Tromp
 * @version 1.0
 */

/***************************************************************/
/*                       IMPORT SECTION                        */
/***************************************************************/

import java.io.*;

import java.awt.*;
import javax.swing.*;

import org.apache.log4j.*;
import jessx.business.*;
import jessx.server.gui.*;
import jessx.utils.*;
import org.jdom.Element;

/***************************************************************/
/*                   Automate CLASS SECTION                    */
/***************************************************************/

//Doesn't work for the moment, but not a priority

public abstract class AssetActivity extends Asset {
  private String activity;

  public void setAssetActivity (String assetactivity) {
    this.activity = assetactivity;
  }

  public String getAssetActivity () {
    return this.activity;
  }

}

