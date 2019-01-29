head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondExecutionConditionType.java;


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
package com.fitechlabs.xtrade.plugin.tc.xbbd;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * $History: BondExecutionConditionType.java $
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 05/02/11   Time: 13:22
 * Created in $/xtrade3.8-tc/xb-bd-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbbd
 * 
 * *****************  Version 1  *****************
 * User: Warlu        Date: 1/22/04    Time: 3:37p
 * Created in $/xtrade3.8-tc/eqtype-base/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 4/28/03    Time: 2:37p
 * Created in $/xtrade3.7-tc/eqtype-base/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 4/25/03    Time: 4:52p
 * Created in $/xtrade3.7-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * 
 * *****************  Version 5  *****************
 * User: Warlu        Date: 2/11/03    Time: 8:39a
 * Updated in $/xtrade3.6-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * added LIMIT_PRICE and MARKET_PRICE
 *
 */

/**
 * <p>
 * Define Execution Condition enum for an order unit.
 * </p>
 * @@author x-zhang
 * @@version 1.0
 */
public class BondExecutionConditionType extends Enumerated {

	/** Optional Inner class to define values of integers used.
    * In this way the class can be easily used in switch statements
    */
	public static class IntValues {
		public static final int UNDEFINED = 0;
		public static final int NONE = 1;
		public static final int AT_MARKET_OPEN = 2;
		public static final int AT_MARKET_CLOSE = 3;

		/** Indicates limit price */
		public static final int LIMIT_PRICE     = 4;

		/** Indicates market price */
		public static final int MARKET_PRICE     = 5;
	}

	/** Undefined Execution Condition Type */
	public static final BondExecutionConditionType UNDEFINED  = new BondExecutionConditionType( IntValues.UNDEFINED, "UNDEFINED" );

	/** Normal Execution Condition */
	public static final BondExecutionConditionType NONE  = new BondExecutionConditionType( IntValues.NONE, "NONE" );

	/** Applied only at market open time */
	public static final BondExecutionConditionType AT_MARKET_OPEN  = new BondExecutionConditionType( IntValues.AT_MARKET_OPEN, "AT_MARKET_OPEN" );

	/** Applied only at market closing time */
	public static final BondExecutionConditionType AT_MARKET_CLOSE  = new BondExecutionConditionType( IntValues.AT_MARKET_CLOSE, "AT_MARKET_CLOSE" );


	/** Execution condition type indicating limit price. */
	public static final BondExecutionConditionType LIMIT_PRICE  =
		    new BondExecutionConditionType( IntValues.LIMIT_PRICE, "LIMIT_PRICE" );

	/** Execution condition type indicating market price. */
	public static final BondExecutionConditionType MARKET_PRICE  =
		    new BondExecutionConditionType( IntValues.MARKET_PRICE, "MARKET_PRICE" );

	/** Mandatory constructor override of superclass constructor */
	public BondExecutionConditionType(int i, String s) {
		super(i, s);
	}
}
@
