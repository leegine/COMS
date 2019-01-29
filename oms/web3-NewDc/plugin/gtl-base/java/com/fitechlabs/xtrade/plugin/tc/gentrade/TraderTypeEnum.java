head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	TraderTypeEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * $History: TraderTypeEnum.java $
 * 
 * *****************  Version 2  *****************
 * User: Yoshi        Date: 04/04/15   Time: 18:28
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
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
 * *****************  Version 2  *****************
 * User: Li           Date: 1/06/03    Time: 6:36p
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 1/06/03    Time: 6:34p
 * Created in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * 
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
 * <p>
 *  Define Trader Type enum.
 * </p>
 *
 * @@author Ying Li
 * @@version 1.0
 */ 

public class TraderTypeEnum extends Enumerated {

	/** Optional Inner class to define values of integers used.
    * In this way the class can be easily used in switch statements 
    */
	public static class IntValues {
		public static final int UNDEFINED = 0;		
		public static final int TRADER_TYPE1 = 1;
		public static final int TRADER_TYPE2  = 2;
	}
	
	/** Mandatory predefined values, one for each possibility.
	*   In this example, they make use of the IntValues constants 
	*   defined previously
   	*/
	public static final TraderTypeEnum UNDEFINED  = new TraderTypeEnum( IntValues.UNDEFINED, "UNDEFINED" );
	public static final TraderTypeEnum TRADER_TYPE1  = new TraderTypeEnum( IntValues.TRADER_TYPE1, "TRADER_TYPE1" );
	public static final TraderTypeEnum TRADER_TYPE2 = new TraderTypeEnum( IntValues.TRADER_TYPE2, "TRADER_TYPE2" );
	

	/** Mandatory constructor override of superclass constructor */
	public TraderTypeEnum(int i, String s) {
		super(i, s);
	}
}
@
