head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.59.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	CouponTypeEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 *
 * Copyright 2000-2003 Fitech Laboratories, Inc. All Rights Reserved.
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
 * CouponTypeEnum defines different bond interest payment types. 
 * @@author x-zhang
 * @@since 3.7
 */
public class CouponTypeEnum extends Enumerated {

	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		/** Bond with fixed coupon. */
		public static final int COUPON = 0;

		/** Bond without cuopon, also called Discount Bond. */
		public static final int ZERO_COUPON = 1;

		/** Bond with coupon that is subject to change over time. */
		public static final int FLOATING_COUPON = 2;

	}

	/** Bond with fixed coupon. */
	public static final CouponTypeEnum COUPON = new CouponTypeEnum(IntValues.COUPON, "COUPON");

	/** Bond without cuopon, also called Discount Bond. */
	public static final CouponTypeEnum ZERO_COUPON = new CouponTypeEnum(IntValues.ZERO_COUPON, "ZERO_COUPON");

	/** Bond with coupon that is subject to change over time. */
	public static final CouponTypeEnum FLOATING_COUPON = new CouponTypeEnum(IntValues.FLOATING_COUPON, "FLOATING_COUPON");

	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public CouponTypeEnum(int i, String s) {
		super(i, s);
	}

}
@
