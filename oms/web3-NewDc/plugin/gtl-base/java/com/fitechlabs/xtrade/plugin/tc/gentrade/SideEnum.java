head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	SideEnum.java;


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
 * $History: SideEnum.java $
 * 
 * *****************  Version 5  *****************
 * User: Warlu        Date: 6/09/04    Time: 8:11a
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 4  *****************
 * User: X-zhang      Date: 04/04/30   Time: 17:21
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 3  *****************
 * User: X-zhang      Date: 04/04/15   Time: 17:06
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: X-zhang      Date: 04/04/07   Time: 14:16
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 1  *****************
 * User: Warlu        Date: 1/22/04    Time: 3:41p
 * Created in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 5/21/03    Time: 8:09a
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * added BOND_BUY and BOND_SELL
 *
 * *****************  Version 1  *****************
 * User: Warlu        Date: 5/08/03    Time: 8:58p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 1  *****************
 * User: Warlu        Date: 5/08/03    Time: 5:34p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Moved from eqtype
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 5/06/03    Time: 12:43p
 * Updated in $/xtrade3.7-tc/eqtype-base/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * added mini stock and MF order types.
 *
 * *****************  Version 1  *****************
 * User: Li           Date: 4/28/03    Time: 2:37p
 * Created in $/xtrade3.7-tc/eqtype-base/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 *
 * *****************  Version 1  *****************
 * User: Li           Date: 4/25/03    Time: 4:52p
 * Created in $/xtrade3.7-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 *
 * *****************  Version 6  *****************
 * User: Warlu        Date: 3/13/03    Time: 10:14a
 * Updated in $/xtrade3.6-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * fixed a bug related to SWAP orders. for SWAP_MARGIN_SHORT the side
 * should be  SELL, SWAP_MARGIN_BUY the side should be BUY.
 */

/**
 * <p>
 * Define Side enum.
 * </p>
 * @@author x-zhang
 * @@version 1.0
 */
public class SideEnum extends Enumerated {

	/** Optional Inner class to define values of integers used.
    * In this way the class can be easily used in switch statements
    */
	public static class IntValues {
		public static final int UNDEFINED = 0;
		public static final int BUY = 1;
		public static final int SELL = 2;
	}

	/** Undefined side */
	public static final SideEnum UNDEFINED  = new SideEnum( IntValues.UNDEFINED, "UNDEFINED" );

	/** Buy side */
	public static final SideEnum BUY  = new SideEnum( IntValues.BUY, "BUY" );

	/** Sell side */
	public static final SideEnum SELL  = new SideEnum( IntValues.SELL, "SELL" );


    /**
     * Returns the cash flow direction with respect to the balance of an account
     *
     * @@return 1 (if BUY), -1 (SELL), 0 (otherwise, this should not happen)
     */
    public int cashFlowDir() {
    	if (this.intValue() == BUY.intValue())
    	  return -1;
    	else if(this.intValue() == SELL.intValue())
    	  return 1;
    	else
    	  return 0;
    }

	/** Mandatory constructor override of superclass constructor */
	public SideEnum(int i, String s) {
		super(i, s);
	}

	/**
	 * Return the EqTypeSide enum based on an EqTypeOrderType
	 * @@param order_type input EqTypeOrderType
	 * @@return EqTypeSide associated with the passed EqTypeOrderType
	 */
	public static SideEnum getSide(OrderTypeEnum order_type){
		switch(order_type.intValue()){
			case OrderTypeEnum.IntValues.EQUITY_BUY:
			case OrderTypeEnum.IntValues.MARGIN_LONG:
			case OrderTypeEnum.IntValues.CLOSE_MARGIN_SHORT:
			case OrderTypeEnum.IntValues.SWAP_MARGIN_LONG:
			case OrderTypeEnum.IntValues.MINI_STOCK_BUY:
			case OrderTypeEnum.IntValues.MF_BUY:
			case OrderTypeEnum.IntValues.BOND_BUY:
			case OrderTypeEnum.IntValues.RUITO_BUY:
			case OrderTypeEnum.IntValues.FEQ_BUY:
			case OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_OPEN:
			case OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_CLOSE:
			case OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_OPEN:
			case OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_CLOSE:
				return SideEnum.BUY;

			case OrderTypeEnum.IntValues.EQUITY_SELL:
			case OrderTypeEnum.IntValues.MARGIN_SHORT:
			case OrderTypeEnum.IntValues.CLOSE_MARGIN_LONG:
			case OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT:
			case OrderTypeEnum.IntValues.MINI_STOCK_SELL:
			case OrderTypeEnum.IntValues.MF_SELL:
			case OrderTypeEnum.IntValues.BOND_SELL:
			case OrderTypeEnum.IntValues.RUITO_SELL:
			case OrderTypeEnum.IntValues.FEQ_SELL:
			case OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_OPEN:
			case OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_CLOSE:
			case OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_OPEN:
			case OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_CLOSE:
				return SideEnum.SELL;

			default:
			  return SideEnum.UNDEFINED;
		}
	}

	/**
	 * Return the EqTypeSide enum based on an FinTransactionType
	 * @@param trans_type input FinTransactionType
	 * @@return SideEnum associated with the passed FinTransactionType
	 */
	public static SideEnum getSide(FinTransactionType trans_type){
		return getSide(trans_type.toOrderTypeEnum());
	}


}

@
