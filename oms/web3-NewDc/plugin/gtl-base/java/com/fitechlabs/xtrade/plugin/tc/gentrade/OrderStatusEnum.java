head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderStatusEnum.java;


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
 * $History: OrderStatusEnum.java $
 * 
 * *****************  Version 1  *****************
 * User: Warlu        Date: 1/22/04    Time: 3:41p
 * Created in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
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
 * *****************  Version 1  *****************
 * User: Li           Date: 4/28/03    Time: 2:37p
 * Created in $/xtrade3.7-tc/eqtype-base/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 4/25/03    Time: 4:52p
 * Created in $/xtrade3.7-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * 
 * *****************  Version 7  *****************
 * User: X-zhang      Date: 03/01/21   Time: 15:02
 * Updated in $/xtrade3.6-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * 
 * *****************  Version 6  *****************
 * User: X-zhang      Date: 03/01/19   Time: 12:29
 * Updated in $/xtrade3.6-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * add class definition
 * 
 * *****************  Version 5  *****************
 * User: Warlu        Date: 1/19/03    Time: 8:57a
 * Updated in $/xtrade3.6-tc/eqtype/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * corrected values to reflect latest order status values as discussed in
 * a meeting with Toshi, Li and Zhang.
 *
 */

/**
 * <p>
 * Define Order Status enum.
 * </p>  
 * @@author x-zhang
 * @@version 1.0
 */
public class OrderStatusEnum extends Enumerated {

	/** Optional Inner class to define values of integers used.
    * In this way the class can be easily used in switch statements
    */
	public static class IntValues {
		public static final int UNDEFINED = 0;
		public static final int ACCEPTED = 1;
		public static final int ORDERING = 2;
        public static final int ORDERED = 3;
		public static final int NOT_ORDERED = 6;
		public static final int MODIFY_ACCEPTED = 7;
		public static final int MODIFYING = 8;
		public static final int MODIFIED = 10;
		public static final int NOT_MODIFIED = 11;
		public static final int CANCEL_ACCEPTED = 12;
		public static final int CANCELLING = 13;
		public static final int CANCELLED = 14;
		public static final int NOT_CANCELLED = 15;
	}

	/** Undefined order status */
    public static final OrderStatusEnum  UNDEFINED  = new OrderStatusEnum(IntValues.UNDEFINED ,"UNDEFINED ");
    
    /** A new order is accepted by order manage and also passed to market adapter */
    public static final OrderStatusEnum  ACCEPTED  = new OrderStatusEnum(IntValues.ACCEPTED ,"ACCEPTED ");

	/** A new order is sent to market by market adapter */
    public static final OrderStatusEnum  ORDERING  = new OrderStatusEnum(IntValues.ORDERING ,"ORDERING ");

	/** A new order is confirmed by market */
    public static final OrderStatusEnum  ORDERED  = new OrderStatusEnum(IntValues.ORDERED ,"ORDERED ");

	/** A new order is rejected by market. This is a final status */
    public static final OrderStatusEnum  NOT_ORDERED  = new OrderStatusEnum(IntValues.NOT_ORDERED ,"NOT_ORDERED ");

	/** A change order is accepted by order manage and also passed to market adapter */	
    public static final OrderStatusEnum  MODIFY_ACCEPTED  = new OrderStatusEnum(IntValues.MODIFY_ACCEPTED ,"MODIFY_ACCEPTED ");

	/** A change order is sent to market by market adapter */
    public static final OrderStatusEnum  MODIFYING  = new OrderStatusEnum(IntValues.MODIFYING ,"MODIFYING ");

	/** A change order is confirmed by market */
    public static final OrderStatusEnum  MODIFIED  = new OrderStatusEnum(IntValues.MODIFIED ,"MODIFIED ");

	/** A new order is rejected by market. */	
    public static final OrderStatusEnum  NOT_MODIFIED  = new OrderStatusEnum(IntValues.NOT_MODIFIED ,"NOT_MODIFIED ");

	/** A cancel order is accepted by order manage and also passed to market adapter */	
    public static final OrderStatusEnum  CANCEL_ACCEPTED  = new OrderStatusEnum(IntValues.CANCEL_ACCEPTED ,"CANCEL_ACCEPTED ");

	/** A cancel order is sent to market by market adapter */
    public static final OrderStatusEnum  CANCELLING  = new OrderStatusEnum(IntValues.CANCELLING ,"CANCELLING ");

	/** A cancel order is confirmed by market. This is a final status */
    public static final OrderStatusEnum  CANCELLED  = new OrderStatusEnum(IntValues.CANCELLED ,"CANCELLED ");

	/** A cancel order is rejected by market. */
    public static final OrderStatusEnum  NOT_CANCELLED  = new OrderStatusEnum(IntValues.NOT_CANCELLED ,"NOT_CANCELLED ");

	/** Mandatory constructor override of superclass constructor */
	public OrderStatusEnum(int i, String s) {
		super(i, s);
	}

}
@
