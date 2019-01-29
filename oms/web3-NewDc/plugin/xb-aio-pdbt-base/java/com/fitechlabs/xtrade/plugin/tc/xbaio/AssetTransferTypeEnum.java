head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.57.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	35c4d88640f7e4e;
filename	AssetTransferTypeEnum.java;


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
package com.fitechlabs.xtrade.plugin.tc.xbaio;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * $History: AssetTransferTypeEnum.java $
 * 
 * *****************  Version 1  *****************
 * User: Warlu        Date: 5/21/04    Time: 12:25p
 * Created in $/xtrade3.8-tc/xb-aio-pdbt-base/java/com/fitechlabs/xtrade/plugin/tc/xbaio
 * 
 */

/**
 * <p>
 * 	Define Asset Transfer Type enum.
 * </p>
 * @@author Warlu
 * @@version 1.0
 */
public class AssetTransferTypeEnum	extends Enumerated {

	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		/** CASH Transfer in */
		public static final int CASH_IN		= 1;

		/** CASH Transfer out */
		public static final int CASH_OUT	= 2;
	}

	/** Cash Transfer in	*/
	public static final AssetTransferTypeEnum 	CASH_IN 	= new AssetTransferTypeEnum(IntValues.CASH_IN, "CASH_IN");

	/** Cash Transfer out 	*/
	public static final AssetTransferTypeEnum	CASH_OUT 	= new AssetTransferTypeEnum(IntValues.CASH_OUT, "CASH_OUT");

	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public AssetTransferTypeEnum(int i, String s) {
		super(i, s);
	}

}
@
