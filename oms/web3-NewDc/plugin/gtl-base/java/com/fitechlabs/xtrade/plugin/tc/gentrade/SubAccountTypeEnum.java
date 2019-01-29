head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SubAccountTypeEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 *
 * Copyright 2000-2002 Fitech Laboratories, Inc. All Rights Reserved.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FITECH LABORATORIES AND ITS LICENSORS
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL
 * FITECH LABORATORIES OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT
 * OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT
 * OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF FITECH LABORATORIES HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 *
 */


/**
 * $History: SubAccountTypeEnum.java $ Version 1   User: Li           Date:
 * 
 * *****************  Version 1  *****************
 * User: Warlu        Date: 1/22/04    Time: 3:41p
 * Created in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 4/28/03    Time: 1:18p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 4/25/03    Time: 4:45p
 * Created in $/xtrade3.7-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 3  *****************
 * User: Warlu        Date: 1/15/03    Time: 6:46p
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 1/08/03    Time: 1:35a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Redefined Enums, cleaned javadoc.
 * 1/06/03    Time: 4:27p Created in
 * $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 */

/*
 *
 * Copyright 2000-2002 Fitech Laboratories, Inc. All Rights Reserved.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FITECH LABORATORIES AND ITS LICENSORS
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL
 * FITECH LABORATORIES OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT
 * OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT
 * OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF FITECH LABORATORIES HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 *
 */
package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

import java.io.*;

import java.util.*;


/**
 * $History: SubAccountTypeEnum.java $
 * 
 * *****************  Version 1  *****************
 * User: Warlu        Date: 1/22/04    Time: 3:41p
 * Created in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 4/28/03    Time: 1:18p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 4/25/03    Time: 4:45p
 * Created in $/xtrade3.7-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 3  *****************
 * User: Warlu        Date: 1/15/03    Time: 6:46p
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 1/08/03    Time: 1:35a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Redefined Enums, cleaned javadoc.
 */
/**
 * <p>
 * Define Sub Account Type enum.
 * </p>
 *
 * @@author Ying Li
 * @@version 1.0
 */
public class SubAccountTypeEnum extends Enumerated {

  /**
   * SubAccount type that doesnt belong to any of the specifically mentioned
   * SubAccount types.
   */
  public static final SubAccountTypeEnum OTHER = new SubAccountTypeEnum(IntValues.OTHER,
      "OTHER ");

  /** Equity subaccount indicating equity cash based trading. */
  public static final SubAccountTypeEnum EQUITY_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.EQUITY_SUB_ACCOUNT,
      "EQUITY_SUB_ACCOUNT ");

  /** Equity subaccount indicating equity margin based trading. */
  public static final SubAccountTypeEnum EQUITY_MARGIN_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.EQUITY_MARGIN_SUB_ACCOUNT,
      "EQUITY_MARGIN_SUB_ACCOUNT ");

  /** FX subaccount indicating equity margin based trading. */
  public static final SubAccountTypeEnum FX_MARGIN_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.FX_MARGIN_SUB_ACCOUNT,
      "FX_MARGIN_SUB_ACCOUNT ");

  /** FX spot trading sub account */
  public static final SubAccountTypeEnum FX_SPOT_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.FX_SPOT_SUB_ACCOUNT,
      "FX_SPOT_SUB_ACCOUNT ");

  /** Generic Futures sub account. */
  public static final SubAccountTypeEnum FUTURES_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.FUTURES_SUB_ACCOUNT,
      "FUTURES_SUB_ACCOUNT ");

  /** Index futures sub account. */
  public static final SubAccountTypeEnum INDEX_FUTURES_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.INDEX_FUTURES_SUB_ACCOUNT,
      "INDEX_FUTURES_SUB_ACCOUNT ");

  /** Equity Options Sub Accoount. */
  public static final SubAccountTypeEnum EQUITY_OPTIONS_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.EQUITY_OPTIONS_SUB_ACCOUNT,
      "EQUITY_OPTIONS_SUB_ACCOUNT ");

  /** Index options sub account. */
  public static final SubAccountTypeEnum INDEX_OPTIONS_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.INDEX_OPTIONS_SUB_ACCOUNT,
      "INDEX_OPTIONS_SUB_ACCOUNT ");

  /** Bond trading sub account */
  public static final SubAccountTypeEnum BOND_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.BOND_SUB_ACCOUNT,
      "BOND_SUB_ACCOUNT ");

  /** Mutual Fund Trading Sub Account. */
  public static final SubAccountTypeEnum MUTUAL_FUND_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.MUTUAL_FUND_SUB_ACCOUNT,
      "MUTUAL_FUND_SUB_ACCOUNT ");

  /** MMF Sub account */
  public static final SubAccountTypeEnum MMF_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.MMF_SUB_ACCOUNT,
      "MMF_SUB_ACCOUNT ");

  /** MRF Sub Account */
  public static final SubAccountTypeEnum MRF_SUB_ACCOUNT = new SubAccountTypeEnum(IntValues.MRF_SUB_ACCOUNT,
      "MRF_SUB_ACCOUNT ");

  /**
   * All in one sub account, typically used for representing all sub accounts
   * in one where individual distinction is not required..
   */
  public static final SubAccountTypeEnum ALL_IN_ONE_ACCOUNT = new SubAccountTypeEnum(IntValues.ALL_IN_ONE_ACCOUNT,
      "ALL_IN_ONE_ACCOUNT ");

  /**
   * Mandatory constructor override of superclass constructor
   *
   * @@param i Int value.
   * @@param s String representation.
   */
  public SubAccountTypeEnum(int i, String s) {
    super(i, s);
  }

  /**
   * Inner class to define values of integers used. In this way the class can
   * be easily used in switch statements
   */
  public static class IntValues {

    //~ Static fields/initializers ---------------------------------------------

    /**
     * SubAccount type that doesnt belong to any of the specifically mentioned
     * SubAccount types.
     */
    public static final int OTHER = 0;

    /** Equity subaccount indicating equity cash based trading. */
    public static final int EQUITY_SUB_ACCOUNT = 1;

    /** Equity Margin sub account indicating margin based trading. */
    public static final int EQUITY_MARGIN_SUB_ACCOUNT = 2;

    /** Fx  Margin sub account indicating margin based trading. */
    public static final int FX_MARGIN_SUB_ACCOUNT = 3;

    /** Fx  Spot sub account indicating FX spot  trading. */
    public static final int FX_SPOT_SUB_ACCOUNT = 4;

    /** Futures  sub account . */
    public static final int FUTURES_SUB_ACCOUNT = 5;

    /** Index Futures  sub account . */
    public static final int INDEX_FUTURES_SUB_ACCOUNT = 6;

    /** Equity Options sub account . */
    public static final int EQUITY_OPTIONS_SUB_ACCOUNT = 7;

    /** Index Options sub account i. */
    public static final int INDEX_OPTIONS_SUB_ACCOUNT = 8;

    /** Bond  sub account . */
    public static final int BOND_SUB_ACCOUNT = 9;

    /** Mutual Fund sub account . */
    public static final int MUTUAL_FUND_SUB_ACCOUNT = 10;

    /** MMF  sub account . */
    public static final int MMF_SUB_ACCOUNT = 11;

    /** MRF  sub account . */
    public static final int MRF_SUB_ACCOUNT = 12;

    /** All in one sub account where sub account level distinction is not required.*/
    public static final int ALL_IN_ONE_ACCOUNT = 99;
  }
}
@
