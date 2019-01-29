head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ProductTypeEnum.java;


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
 * $History: ProductTypeEnum.java $
 * 
 * *****************  Version 4  *****************
 * User: Warlu        Date: 5/27/04    Time: 6:29p
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 3  *****************
 * User: Warlu        Date: 4/29/04    Time: 9:10a
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 4/14/04    Time: 11:51a
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * added Ruito related stuff
 *
 * *****************  Version 1  *****************
 * User: Warlu        Date: 1/22/04    Time: 3:41p
 * Created in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 3  *****************
 * User: X-zhang      Date: 03/05/28   Time: 18:40
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 5/03/03    Time: 3:24p
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * deleted FOREIGN_BOND, FOREIGN_MUTUAL_FUND from ProductTypeEnum
 *
 * *****************  Version 1  *****************
 * User: Li           Date: 4/28/03    Time: 1:18p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 1  *****************
 * User: Li           Date: 4/25/03    Time: 4:45p
 * Created in $/xtrade3.7-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 2/14/03    Time: 2:55a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * added FOREIGN_EQUITY, FOREIGN_BOND, FOREIGN_MUTUAL_FUND
 *
 */

/**
 * <p>
 * Define Product Type enum.
 * </p>
 *
 * @@author x-zhang
 * @@version 1.0
 */
public class ProductTypeEnum extends Enumerated {

    /** Product type that doesnt belong to ones specifically defined here. */
    public static final ProductTypeEnum OTHER = new ProductTypeEnum(IntValues.OTHER, "OTHER");

    /** Product type of equity. */
    public static final ProductTypeEnum EQUITY = new ProductTypeEnum(IntValues.EQUITY, "EQUITY");

    /** Product type of bond. */
    public static final ProductTypeEnum BOND = new ProductTypeEnum(IntValues.BOND, "BOND");

    /** Product type of Mutual Fund. */
    public static final ProductTypeEnum MUTUAL_FUND = new ProductTypeEnum(IntValues.MUTUAL_FUND, "MUTUAL_FUND");

    /** Product type of Foreign Equity. */
    public static final ProductTypeEnum FOREIGN_EQUITY = new ProductTypeEnum(IntValues.FOREIGN_EQUITY, "FOREIGN_EQUITY");

    /** Product type of Cash. */
    public static final ProductTypeEnum CASH = new ProductTypeEnum(IntValues.CASH, "CASH");

    /** Product type of Ruito. */
    public static final ProductTypeEnum RUITO = new ProductTypeEnum(IntValues.RUITO, "RUITO");

    /** Index Futures and Options type of product. */
    public static final ProductTypeEnum IFO = new ProductTypeEnum(IntValues.IFO, "IFO");

    /** Product type for asset transfers. */
    public static final ProductTypeEnum AIO = new ProductTypeEnum(IntValues.AIO, "AIO");
    
    /** IPO type of product. */
    public static final ProductTypeEnum IPO = new ProductTypeEnum(IntValues.IPO, "IPO");

    /**
     * Mandatory constructor override of superclass constructor
     *
     * @@param i Int value.
     * @@param s String representation.
     */
    public ProductTypeEnum(int i, String s) {
        super(i, s);
    }

    /**
     * Optional Inner class to define values of integers used. In this way the
     * class can be easily used in switch statements
     */
    public static class IntValues {

        /**
         * Product indicating other, meaning it doesn belong any of the others defined here.
         */
        public static final int OTHER = 0;

        /**
         * (Domestic) Equity product type.
         */
        public static final int EQUITY = 1;

        /**
         * (Domestic) Bond product type.
         */
        public static final int BOND = 2;

        /**
         * Domestic(Mutual fund) product type.
         */
        public static final int MUTUAL_FUND = 3;

        /**
         * (Foreign)equity product type.
         */
        public static final int FOREIGN_EQUITY = 4;

        /**
         * cash product type.
         */
        public static final int CASH = 5;

        /**
         * Index Futures and Options type of product.
         */
        public static final int IFO = 6;

        /**
         * Ruito type of product.
         */
        public static final int RUITO = 7;

        /**
         * Product type for asset transfers.
         */
        public static final int AIO  = 8;
        
        /**
         * IPO type of product.
         */
        public static final int IPO = 10;
    }
}@
