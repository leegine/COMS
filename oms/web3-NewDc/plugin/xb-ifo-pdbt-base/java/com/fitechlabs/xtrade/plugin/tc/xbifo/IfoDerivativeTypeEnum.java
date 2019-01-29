head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.09.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88667f0644;
filename	IfoDerivativeTypeEnum.java;


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
 * $History: IfoDerivativeTypeEnum.java $
 * 
 * *****************  Version 3  *****************
 * User: X-zhang      Date: 04/04/30   Time: 15:38
 * Updated in $/xtrade3.8-tc/xb-ifo-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbifo
 * 
 * *****************  Version 2  *****************
 * User: Warlu        Date: 4/30/04    Time: 1:39p
 * Updated in $/xtrade3.8-tc/xb-ifo-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbifo
 * 
 * *****************  Version 1  *****************
 * User: X-zhang      Date: 04/04/30   Time: 11:48
 * Created in $/xtrade3.8-tc/xb-ifo-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbifo
 */

/**
 * <p>
 * Define Derivative Type enum.
 * </p>
 * @@author x-zhang
 * @@since 3.8
 */
public class IfoDerivativeTypeEnum extends Enumerated {

	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		/** Other derivative type. */
		public static final int OTHER = 0;

		/** FUTURE */
		public static final int FUTURES = 1;

		/** CALL_OPTION. */
		public static final int CALL_OPTIONS= 2;

		/** PUT_OPTION */
		public static final int PUT_OPTIONS = 3;
		
	}

	/** Other derivative type. */
	public static final IfoDerivativeTypeEnum OTHER = new IfoDerivativeTypeEnum(IntValues.OTHER, "OTHER");

	/** FUTURES */
	public static final IfoDerivativeTypeEnum FUTURES = new IfoDerivativeTypeEnum(IntValues.FUTURES, "FUTURES");

	/** CALL_OPTIONS */
	public static final IfoDerivativeTypeEnum CALL_OPTIONS = new IfoDerivativeTypeEnum(IntValues.CALL_OPTIONS, "CALL_OPTIONS");

	/** PUT_OPTIONS */
	public static final IfoDerivativeTypeEnum PUT_OPTIONS = new IfoDerivativeTypeEnum(IntValues.PUT_OPTIONS, "PUT_OPTIONS");

	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public IfoDerivativeTypeEnum(int i, String s) {
		super(i, s);
	}

}
@
