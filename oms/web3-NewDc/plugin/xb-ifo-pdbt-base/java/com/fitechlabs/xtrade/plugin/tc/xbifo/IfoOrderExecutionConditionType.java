head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.09.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoOrderExecutionConditionType.java;


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

package com.fitechlabs.xtrade.plugin.tc.xbifo;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * $History: IfoOrderExecutionConditionType.java $
 * 
 * *****************  Version 2  *****************
 * User: Warlu        Date: 5/10/04    Time: 12:07p
 * Updated in $/xtrade3.8-tc/xb-ifo-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbifo
 *
 * *****************  Version 1  *****************
 * User: X-zhang      Date: 04/04/30   Time: 12:17
 * Created in $/xtrade3.8-tc/xb-ifo-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbifo
 */

/**
 * <p>
 * Define Future/Option order execution condition Type enum.
 * </p>
 * @@author x-zhang
 * @@since 3.8
 */
public class IfoOrderExecutionConditionType extends Enumerated {

	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		/** Not Defined/Specified */
		public static final int UNDEFINED = 0;

		/** No Condition */
		public static final int NONE = 1;

		/** At market open */
		public static final int AT_MARKET_OPEN = 2;

		/** At market close */
		public static final int AT_MARKET_CLOSE = 3;

		/** limit price */
		public static final int LIMIT_PRICE = 4;

		/** market price. */
		public static final int MARKET_PRICE = 5;

        /** 不出来引け成行 */
        public static final int AT_MARKET_CLOSE_NOT_EXECUTED = 6;
        
		/** Immediate-or-cancel */
		public static final int IOC = 7;

		/** Fill-or-Kill */
		public static final int FOK = 8;
	}

	/** Undefined/not specified */
	public static final IfoOrderExecutionConditionType UNDEFINED = new IfoOrderExecutionConditionType(IntValues.UNDEFINED, "UNDEFINED");

	/** no condition. */
	public static final IfoOrderExecutionConditionType NONE = new IfoOrderExecutionConditionType(IntValues.NONE, "NONE");

	/** at market open */
	public static final IfoOrderExecutionConditionType AT_MARKET_OPEN = new IfoOrderExecutionConditionType(IntValues.AT_MARKET_OPEN, "AT_MARKET_OPEN");

	/** at market close */
	public static final IfoOrderExecutionConditionType AT_MARKET_CLOSE = new IfoOrderExecutionConditionType(IntValues.AT_MARKET_CLOSE, "AT_MARKET_CLOSE");

	/** limit price */
	public static final IfoOrderExecutionConditionType LIMIT_PRICE = new IfoOrderExecutionConditionType(IntValues.LIMIT_PRICE, "LIMIT_PRICE");

	/** market price. */
	public static final IfoOrderExecutionConditionType MARKET_PRICE = new IfoOrderExecutionConditionType(IntValues.MARKET_PRICE, "MARKET_PRICE");

    /** 不出来引け成行 */
    public static final IfoOrderExecutionConditionType AT_MARKET_CLOSE_NOT_EXECUTED = new IfoOrderExecutionConditionType(IntValues.AT_MARKET_CLOSE_NOT_EXECUTED, "AT_MARKET_CLOSE_NOT_EXECUTED");

	/** Immediate-or-cancel */
	public static final IfoOrderExecutionConditionType IOC = new IfoOrderExecutionConditionType(IntValues.IOC, "IOC");

	/** Fill-or-Kill */
	public static final IfoOrderExecutionConditionType FOK = new IfoOrderExecutionConditionType(IntValues.FOK, "FOK");
        
	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public IfoOrderExecutionConditionType(int i, String s) {
		super(i, s);
	}

}
@
