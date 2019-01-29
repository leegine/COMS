head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	MainAccountTypeEnum.java;


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
 * $History: MainAccountTypeEnum.java $
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
 * *****************  Version 3  *****************
 * User: Warlu        Date: 1/12/03    Time: 1:12p
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Cleaned redundant javadoc comments.
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 1/08/03    Time: 1:35a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Redefined Enums, cleaned javadoc.
 */
/**
 * <p>
 * Define Generic Main Account Type enum.
 * </p>
 *
 * @@author Ying Li
 * @@version 1.0
 */
public class MainAccountTypeEnum extends Enumerated {

	/** Constant for other type of account. */
	public static final MainAccountTypeEnum OTHER = new MainAccountTypeEnum(IntValues.OTHER,
		  "OTHER");

	/** Constant for individual type of  account. */
	public static final MainAccountTypeEnum INDIVIDUAL_ACCOUNT = new MainAccountTypeEnum(IntValues.INDIVIDUAL_ACCOUNT,
		  "INDIVIDUAL_ACCOUNT");

	/** Constant for joint ownership account or joint account. */
	public static final MainAccountTypeEnum JOINT_OWNERSHIP = new MainAccountTypeEnum(IntValues.JOINT_OWNERSHIP,
		  "JOINT_OWNERSHIP");

	/** Constant for corporate type  of  account. */
	public static final MainAccountTypeEnum CORPORATE_ACCOUNT = new MainAccountTypeEnum(IntValues.CORPORATE_ACCOUNT,
		  "CORPORATE_ACCOUNT");

	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public MainAccountTypeEnum(int i, String s) {
		super(i, s);
	}

	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		//~ Static fields/initializers ---------------------------------------------

		/**
		 * Indicates other type of account that cant be classified in to account
		 * types specifically defined here.
		 */
		public static final int OTHER = 0;

		/** Indicates individual account type. */
		public static final int INDIVIDUAL_ACCOUNT = 1;

		/** Indicates joint account. */
		public static final int JOINT_OWNERSHIP = 2;

		/** Indicates corporate account. */
		public static final int CORPORATE_ACCOUNT = 3;
	}
}
@
