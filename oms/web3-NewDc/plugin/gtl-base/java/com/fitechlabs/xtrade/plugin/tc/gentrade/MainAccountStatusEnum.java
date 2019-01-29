head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MainAccountStatusEnum.java;


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
package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;


/**
 * $History: MainAccountStatusEnum.java $
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
 * *****************  Version 4  *****************
 * User: Warlu        Date: 1/12/03    Time: 1:12p
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Cleaned redundant javadoc comments.
 *
 * *****************  Version 3  *****************
 * User: Warlu        Date: 1/08/03    Time: 1:35a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Redefined Enums, cleaned javadoc.
 */
/**
 * <p>
 * Define Generic Main Account Status enum.
 * </p>
 *
 * @@author Ying Li
 * @@version 1.0
 */
public class MainAccountStatusEnum extends Enumerated {

  /**
   * Enum constant representing other than the ones specifically mentioned such
   * as ACTIVE etc.
   */
  public static final MainAccountStatusEnum OTHER = new MainAccountStatusEnum(IntValues.OTHER,
      "OTHER");

  /** Enum constant representing active account. */
  public static final MainAccountStatusEnum ACTIVE = new MainAccountStatusEnum(IntValues.ACTIVE,
      "ACTIVE");

  /** Enum constant representing inactive account. */
  public static final MainAccountStatusEnum INACTIVE = new MainAccountStatusEnum(IntValues.INACTIVE,
      "INACTIVE");

  /** Enum constant representing locked account. */
  public static final MainAccountStatusEnum LOCKED = new MainAccountStatusEnum(IntValues.LOCKED,
      "LOCKED");

  /** Enum constant representing closed account. */
  public static final MainAccountStatusEnum CLOSED = new MainAccountStatusEnum(IntValues.CLOSED,
      "CLOSED");

  /**
   * Mandatory constructor override of superclass constructor
   *
   * @@param i Int Value.
   * @@param s String representation.
   */
  public MainAccountStatusEnum(int i, String s) {
    super(i, s);
  }

  /**
   * Optional Inner class to define values of integers used. In this way the
   * class can be easily used in switch statements
   */
  public static class IntValues {

    //~ Static fields/initializers ---------------------------------------------

    /**
     * Int value indicating status other than the ones specifically defined
     * such as ACTIVE etc.
     */
    public static final int OTHER = 0;

    /** Int value indicating active account. */
    public static final int ACTIVE = 1;

    /** Int value indicating inactive account. */
    public static final int INACTIVE = 2;

    /** Int value indicating locked account. */
    public static final int LOCKED = 3;

    /** Int value indicating closed account. */
    public static final int CLOSED = 4;
  }
}
@
