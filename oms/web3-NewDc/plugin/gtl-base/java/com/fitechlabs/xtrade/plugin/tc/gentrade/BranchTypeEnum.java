head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BranchTypeEnum.java;


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
 * $History: BranchTypeEnum.java $ Version 1   User: Li           Date: 1/06/03
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
 * *****************  Version 5  *****************
 * User: X-zhang      Date: 03/01/08   Time: 15:30
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 4  *****************
 * User: Warlu        Date: 1/08/03    Time: 1:35a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Redefined Enums, cleaned javadoc.
 *
 * *****************  Version 3  *****************
 * User: Warlu        Date: 1/08/03    Time: 12:31a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * added real enums
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 1/07/03    Time: 9:40p
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Refined BranchType enum definitons.
 * Time: 6:34p Created in
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
 * $History: BranchTypeEnum.java $
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
 * *****************  Version 5  *****************
 * User: X-zhang      Date: 03/01/08   Time: 15:30
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 4  *****************
 * User: Warlu        Date: 1/08/03    Time: 1:35a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Redefined Enums, cleaned javadoc.
 *
 * *****************  Version 3  *****************
 * User: Warlu        Date: 1/08/03    Time: 12:31a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * added real enums
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 1/07/03    Time: 9:40p
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Refined BranchType enum definitons.
 */
/**
 * <p>
 * Define Branch Type enum.
 * </p>
 *
 * @@author Ying Li
 * @@version 1.0
 */
public class BranchTypeEnum extends Enumerated {

  /** Undefined Branch. */
  public static final BranchTypeEnum OTHER_BRANCH = new BranchTypeEnum(IntValues.OTHER_BRANCH,
      "OTHER_BRANCH");

  /** Main Branch. */
  public static final BranchTypeEnum MAIN_BRANCH = new BranchTypeEnum(IntValues.MAIN_BRANCH,
      "MAIN_BRANCH");

  /** Web Branch typically used for associating online customers. */
  public static final BranchTypeEnum WEB_BRANCH = new BranchTypeEnum(IntValues.WEB_BRANCH,
      "WEB_BRANCH");

  /** Call center branch constant, typically used to associate operators.*/
  public static final BranchTypeEnum CALL_CENTER_BRANCH = new BranchTypeEnum(IntValues.CALL_CENTER_BRANCH,
      "CALL_CENTER_BRANCH");

  /**
   * Regular local branch constant, typically used to associate branch offices that are local to the country
   *  and have some physical office presence.*/
  public static final BranchTypeEnum REGULAR_LOCAL_BRANCH = new BranchTypeEnum(IntValues.REGULAR_LOCAL_BRANCH,
      "REGULAR_LOCAL_BRANCH");

  /**
   * Regular foreign branch constant, typically used to associate branch offices that are located out side the country
   *  and have some physical office presence.*/
  public static final BranchTypeEnum REGULAR_FOREIGN_BRANCH = new BranchTypeEnum(IntValues.REGULAR_FOREIGN_BRANCH,
      "REGULAR_FOREIGN_BRANCH");

  /**
   * Creates BranchTypeEnum object.
   *
   * @@param i Int value.
   * @@param s Corresponding String representation.
   */
  public BranchTypeEnum(int i, String s) {
    super(i, s);
  }

  /**
   * Optional Inner class to define values of integers used. In this way the
   * class can be easily used in switch statements
   */
  public static class IntValues {

    //~ Static fields/initializers ---------------------------------------------

	/** BranchType that is not known or defined. */
	public  static  final int OTHER_BRANCH = 0;

    /** Indicates main branch. */
    public static final int MAIN_BRANCH = 1;

    /**
     * Indicates web branch, typically setup for customers who has online
     * accounts.
     */
    public static final int WEB_BRANCH = 2;

    /** Indicates call center branch, typically set for operators. */
    public static final int CALL_CENTER_BRANCH = 3;

    /** Indicates regular local branch office. */
    public static final int REGULAR_LOCAL_BRANCH = 4;

    /** Indicates regular foreign branch. */
    public static final int REGULAR_FOREIGN_BRANCH = 5;
  }
}
@
