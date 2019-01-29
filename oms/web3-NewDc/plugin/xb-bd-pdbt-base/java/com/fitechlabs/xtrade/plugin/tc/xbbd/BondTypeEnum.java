head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.09.00.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	62c4d88646f7f87;
filename	BondTypeEnum.java;


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
 * BondTypeEnum define different bond types. 
 * @@author x-zhang
 * @@since 3.7
 */
public class BondTypeEnum extends Enumerated {
	
	/**
	 * Optional Inner class to define values of integers used. In this way the
	 * class can be easily used in switch statements
	 */
	public static class IntValues {

		/** Japanese Government Bond. */
		public static final int JGB = 0;

		/** Foreign Bond. */
		public static final int FOREIGN_BOND = 4;

		/** Warrant Bond */
		public static final int WB = 8;
		
		/** Convertible Bond */
		public static final int CB = 9;		

        /** Domestic Bond */
        public static final int DOMESTIC_BOND = 10;

        /** Individual Government Bond */
        public static final int INDIVIDUAL_GOVERNMENT_BOND = 11;

        /** Corporate Bond */
        public static final int CORPORATE_BOND = 12;

	}

	/** Japanese Government Bond. */
	public static final BondTypeEnum JGB = new BondTypeEnum(IntValues.JGB, "JGB");

	/** Foreign Bond. */
	public static final BondTypeEnum FOREIGN_BOND = new BondTypeEnum(IntValues.FOREIGN_BOND, "FOREIGN_BOND");

	/** Warrant Bond */
	public static final BondTypeEnum WB = new BondTypeEnum(IntValues.WB, "WB");
	
	/** Convertiable Bond */
	public static final BondTypeEnum CB = new BondTypeEnum(IntValues.CB, "CB");	

    /** Domestic Bond */
    public static final BondTypeEnum DOMESTIC_BOND = new BondTypeEnum(IntValues.DOMESTIC_BOND, "DOMESTIC_BOND");

    /** Individual Government Bond */
    public static final BondTypeEnum INDIVIDUAL_GOVERNMENT_BOND = new BondTypeEnum(IntValues.INDIVIDUAL_GOVERNMENT_BOND, "INDIVIDUAL_GOVERNMENT_BOND");

    /** Corporate Bond */
    public static final BondTypeEnum CORPORATE_BOND = new BondTypeEnum(IntValues.CORPORATE_BOND, "CORPORATE_BOND");

	/**
	 * Mandatory constructor override of superclass constructor
	 *
	 * @@param i Int value.
	 * @@param s String representation.
	 */
	public BondTypeEnum(int i, String s) {
		super(i, s);
	}	

}
@
