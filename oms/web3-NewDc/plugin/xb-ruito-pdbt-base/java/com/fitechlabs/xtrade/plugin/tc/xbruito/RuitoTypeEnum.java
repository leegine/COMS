head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.18.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4c04d8868c00da0;
filename	RuitoTypeEnum.java;


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
package com.fitechlabs.xtrade.plugin.tc.xbruito;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * $History: RuitoTypeEnum.java $
 * 
 * *****************  Version 2  *****************
 * User: Warlu        Date: 4/22/04    Time: 11:06a
 * Updated in $/xtrade3.8-tc/xb-ruito-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbruito
 *
 * *****************  Version 1  *****************
 * User: Warlu        Date: 4/14/04    Time: 10:37a
 * Created in $/xtrade3.8-tc/xb-ruito-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbruito
 *
 *
 */


/**
 * <p>
 * Define Ruito Type enum.
 * </p>
 * @@author Warlu
 * @@version 1.0
 */
public class RuitoTypeEnum extends Enumerated {

	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		/** The other ruito type besides MMF and CHUUKOKU. */
		public static final int OTHER = 0;

		/** MMF */
		public static final int MMF = 1;

		/** Government intermediate bond (CHUUKOKU Fund). */
		public static final int CHUUKOKU_FUND= 2;

		/** MRF */
		public static final int MRF = 3;
	}

	/** The other ruito type besides MMF and CHUUKOKU. */
	public static final RuitoTypeEnum OTHER = new RuitoTypeEnum(IntValues.OTHER, "OTHER");

	/** MMF */
	public static final RuitoTypeEnum MMF = new RuitoTypeEnum(IntValues.MMF, "MMF");

	/** Government intermediate bond (CHUUKOKU Fund). */
	public static final RuitoTypeEnum CHUUKOKU_FUND = new RuitoTypeEnum(IntValues.CHUUKOKU_FUND, "CHUUKOKU_FUND");

	/** MRF */
	public static final RuitoTypeEnum MRF = new RuitoTypeEnum(IntValues.MRF, "MRF");

	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public RuitoTypeEnum(int i, String s) {
		super(i, s);
	}

}
@
