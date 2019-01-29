head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.13.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5d04d8867b00a28;
filename	MutualFundTypeEnum.java;


desc
@@


1.1
log
@lo
@
text
@/**
 * $History: MutualFundTypeEnum.java $
 * 
 * *****************  Version 1  *****************
 * User: Warlu        Date: 5/10/04    Time: 5:10p
 * Created in $/xtrade3.8-tc/xb-mf-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbmf
 * 
 * *****************  Version 1  *****************
 * User: Warlu        Date: 1/27/04    Time: 1:05p
 * Created in $/xtrade3.8-tc/xb-mf-base/java/com/fitechlabs/xtrade/plugin/tc/xbmf
 * 
 * *****************  Version 3  *****************
 * User: Li           Date: 5/28/03    Time: 11:34a
 * Updated in $/xtrade3.7-tc/xb-mf-base/java/com/fitechlabs/xtrade/plugin/tc/xbmf
 * 
 * *****************  Version 2  *****************
 * User: Li           Date: 5/08/03    Time: 5:05p
 * Updated in $/xtrade3.7-tc/xb-mf-base/java/com/fitechlabs/xtrade/plugin/tc/xbmf
 * 
 * *****************  Version 1  *****************
 * User: Li           Date: 5/08/03    Time: 4:58p
 * Created in $/xtrade3.7-tc/xb-mf-base/java/com/fitechlabs/xtrade/plugin/tc/xbmf
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
package com.fitechlabs.xtrade.plugin.tc.xbmf;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * <p>
 * Define Mutual Fund Product Type enum.
 * </p>
 * @@author Y.Li
 * @@version 1.0
 */
public class MutualFundTypeEnum extends Enumerated {

	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		/** The other mutual fund besides domestic and foreign mutual fund product. */
		public static final int OTHER = 0;

		/** Domestic mutual fund product. */
		public static final int DOMESTIC = 1;

		/** Foreign mutual fund product. */
		public static final int FOREIGN = 2;

        /** Foreign mmf. */
        public static final int FOREIGN_MMF = 3;

	}

	/** The other mutual fund type besides domestic and foreign type */
	public static final MutualFundTypeEnum OTHER = new MutualFundTypeEnum(IntValues.OTHER, "OTHER");

	/** The domestic mutual fund product type */
	public static final MutualFundTypeEnum DOMESTIC = new MutualFundTypeEnum(IntValues.DOMESTIC, "DOMESTIC");

	/** The foreign mutual fund type */
	public static final MutualFundTypeEnum FOREIGN = new MutualFundTypeEnum(IntValues.FOREIGN, "FOREIGN");

    /** Foreign mmf. */
    public static final MutualFundTypeEnum FOREIGN_MMF = new MutualFundTypeEnum(IntValues.FOREIGN_MMF, "FOREIGN_MMF");

	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public MutualFundTypeEnum(int i, String s) {
		super(i, s);
	}

}
@
