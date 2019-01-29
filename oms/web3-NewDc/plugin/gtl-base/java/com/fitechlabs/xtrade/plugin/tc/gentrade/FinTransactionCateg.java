head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	FinTransactionCateg.java;


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
import com.fitechlabs.xtrade.kernel.util.log.Logit;

import java.io.*;
import java.util.*;


/**
 * <p>
 * Defines FinTransactionCateg Enums.
 * </p>
 *
 * @@author Warlu
 * @@version 1.0
 */
public class FinTransactionCateg extends Enumerated {

    /**
     * Constant for represent misc/other transaction that can not be represented
     * with other enums.
     */
    public static final FinTransactionCateg OTHER = 
        new FinTransactionCateg(IntValues.OTHER, "OTHER");
    
    /** Constant for cash deposit/withdrawal transaction. */
    public static final FinTransactionCateg CASH_IN_OUT = 
        new FinTransactionCateg(IntValues.CASH_IN_OUT, "CASH_IN_OUT");
    
    /** Constant for fee/commission related transaction. */
    public static final FinTransactionCateg FEE = 
        new FinTransactionCateg(IntValues.FEE, "FEE");

    /** Constant for equity buy/sell transaction. */
    public static final FinTransactionCateg EQTYPE_ASSET =
        new FinTransactionCateg(IntValues.EQTYPE_ASSET, "EQTYPE_ASSET");

    /** Constant for contract opening transaction. */
    public static final FinTransactionCateg EQTYPE_OPEN_MARGIN = 
        new FinTransactionCateg(IntValues.EQTYPE_OPEN_MARGIN, "EQTYPE_OPEN_MARGIN");

    /** Constant for contract closing by reverse buying/selling transaction. */
    public static final FinTransactionCateg EQTYPE_CLOSE_MARGIN =
        new FinTransactionCateg(IntValues.EQTYPE_CLOSE_MARGIN, "EQTYPE_CLOSE_MARGIN");

    /** Constant for contract closing by asset/cash swapping transaction. */
    public static final FinTransactionCateg EQTYPE_SWAP_MARGIN = 
        new FinTransactionCateg(IntValues.EQTYPE_SWAP_MARGIN, "EQTYPE_SWAP_MARGIN");
         
    /** Constant for asset deposit/withdrawal transaction. */
    public static final FinTransactionCateg ASSET_IN_OUT = 
        new FinTransactionCateg(IntValues.ASSET_IN_OUT, "ASSET_IN_OUT"); 
        
    /** Constant for index futures contract open transaction. */
    public static final FinTransactionCateg EQTYPE_IDX_FUTURES_OPEN = 
        new FinTransactionCateg(IntValues.EQTYPE_IDX_FUTURES_OPEN, "EQTYPE_IDX_FUTURES_OPEN"); 
        
    /** Constant for index futures contract close transaction. */
    public static final FinTransactionCateg EQTYPE_IDX_FUTURES_CLOSE = 
        new FinTransactionCateg(IntValues.EQTYPE_IDX_FUTURES_CLOSE, "EQTYPE_IDX_FUTURES_CLOSE");  
        
    /** Constant for index options contract open transaction. */
    public static final FinTransactionCateg EQTYPE_IDX_OPTIONS_OPEN = 
        new FinTransactionCateg(IntValues.EQTYPE_IDX_OPTIONS_OPEN, "EQTYPE_IDX_OPTIONS_OPEN"); 
        
    /** Constant for index options contract close transaction. */
    public static final FinTransactionCateg EQTYPE_IDX_OPTIONS_CLOSE = 
        new FinTransactionCateg(IntValues.EQTYPE_IDX_OPTIONS_CLOSE, "EQTYPE_IDX_OPTIONS_CLOSE");  
        
    /** Constant for transfer transaction. */
    public static final FinTransactionCateg CASH_TRANSFER = 
        new FinTransactionCateg(IntValues.CASH_TRANSFER, "CASH_TRANSFER");                                             

    /** Constant for fx transfer transaction. */
    public static final FinTransactionCateg FX_TRANSFER = 
        new FinTransactionCateg(IntValues.FX_TRANSFER, "FX_TRANSFER");                                             

    /** Constant for feq transfer transaction. */
    public static final FinTransactionCateg FEQ_TRANSFER =
        new FinTransactionCateg(IntValues.FEQ_TRANSFER, "FEQ_TRANSFER");                                             


    /**
     * Creates FinTransactionCateg object.
     * @@param i Int value of the Enum.
     * @@param s String representation of the Enum.
     */
    public FinTransactionCateg(int i, String s) {
        super(i, s);
    }

    /**
     * Optional Inner class to define values of integers used. In this way the
     * class can be easily used in switch statements
     */
    public static class IntValues {

        /** Constant for other transaction type. */
        public static final int OTHER = 0;

        /** Constant for cash deposit/withdrawal transaction. */
        public static final int CASH_IN_OUT = 10;

        /** Constant for equity buy/sell transaction. */
        public static final int EQTYPE_ASSET = 20;

        /** Constant for contract opening transaction. */
        public static final int EQTYPE_OPEN_MARGIN = 30;

        /** Constant for contract closing by reverse buying/selling transaction. */
        public static final int EQTYPE_CLOSE_MARGIN = 40;

        /** Constant for contract closing by asset/cash swapping transaction. */
        public static final int EQTYPE_SWAP_MARGIN = 60;

        /** Constant for fee/commission related transaction. */
        public static final int FEE = 70;
        
        /** Constant for asset deposit/withdrawal transaction. */
        public static final int ASSET_IN_OUT = 80;
        
        /** Constant for index futures contract open transaction. */
        public static final int EQTYPE_IDX_FUTURES_OPEN = 91;
        
        /** Constant for index futures contract close transaction. */
        public static final int EQTYPE_IDX_FUTURES_CLOSE = 92;
        
        /** Constant for index options contract open transaction. */
        public static final int EQTYPE_IDX_OPTIONS_OPEN = 93;
        
        /** Constant for index options contract close transaction. */
        public static final int EQTYPE_IDX_OPTIONS_CLOSE = 94;
        
        /** Constant for transfer transaction. */
        public static final int CASH_TRANSFER = 100;

        /** Constant for fx transfer transaction. */
        public static final int FX_TRANSFER = 101;

        /** Constant for feq transfer transaction. */
        public static final int FEQ_TRANSFER = 102;

    }
}
@
