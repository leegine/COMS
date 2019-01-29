head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderCategEnum.java;


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
 * <p>
 * Define Order Category enum.
 * </p> 
 * @@author x-zhang
 * @@version 1.0
 */
public class OrderCategEnum extends Enumerated {

    /** Optional Inner class to define values of integers used.
    * In this way the class can be easily used in switch statements 
    */
    public static class IntValues {
        
        /** Unknown order category status */
        public static final int UNDEFINED = 0;      
        
        /** Order for buying/selling equity assets */
        public static final int ASSET = 1;
        
        /** Order for opening long/short contract */
        public static final int OPEN_MARGIN  = 3;
        
        /** Order for closing long/short contracts by reverse buying/selling */
        public static final int CLOSE_MARGIN  = 5;
        
        /** Order for closing long/short contracts by swap cash/assets */
        public static final int SWAP_MARGIN  = 7;
        
        /** Order for transferring cash */
        public static final int CASH_IN_OUT = 9;

        /** Order for transferring assets */
        public static final int ASSET_IN_OUT = 11;        
        
        /** Order for transfer */
        public static final int CASH_TRANSFER = 13;
        
        /** Order for securities transfer */
        public static final int ASSET_TRANSFER = 14;       
        
        /** Order for securities fx */
        public static final int FX_TRANSFER = 15;  
                
        /** Order for foreign equity transfer */
        public static final int FEQ_TRANSFER = 16;  

        /** Order for index futures contract open transaction. */
        public static final int IDX_FUTURES_OPEN = 91;
        
        /** Order for index futures contract close transaction. */
        public static final int IDX_FUTURES_CLOSE = 92;
        
        /** Order for index options contract open transaction. */
        public static final int IDX_OPTIONS_OPEN = 93;
        
        /** Order for index options contract close transaction. */
        public static final int IDX_OPTIONS_CLOSE = 94;
                
    }
    
    /** Unknown order category status */
    public static final OrderCategEnum UNDEFINED  = 
        new OrderCategEnum( IntValues.UNDEFINED, "UNDEFINED" );

    /** Order for buying/selling equity assets */
    public static final OrderCategEnum ASSET  = 
        new OrderCategEnum( IntValues.ASSET, "ASSET" );

    /** Order for opening long/short contract */
    public static final OrderCategEnum OPEN_MARGIN  = 
        new OrderCategEnum( IntValues.OPEN_MARGIN, "OPEN_MARGIN" );

    /** Order for closing long/short contracts by reverse buying/selling */
    public static final OrderCategEnum CLOSE_MARGIN  = 
        new OrderCategEnum( IntValues.CLOSE_MARGIN, "CLOSE_MARGIN" );
    
    /** Order for closing long/short contracts by swap cash/assets */
    public static final OrderCategEnum SWAP_MARGIN  = 
        new OrderCategEnum( IntValues.SWAP_MARGIN, "SWAP_MARGIN" );

    /** Order for transferring cash */
    public static final OrderCategEnum CASH_IN_OUT  = 
        new OrderCategEnum( IntValues.CASH_IN_OUT, "CASH_IN_OUT" );
    
    /** Order for transferring assets */
    public static final OrderCategEnum ASSET_IN_OUT  = 
        new OrderCategEnum( IntValues.ASSET_IN_OUT, "ASSET_IN_OUT" );   
        
    /** Order for index futures contract open transaction. */
    public static final OrderCategEnum IDX_FUTURES_OPEN = 
        new OrderCategEnum(IntValues.IDX_FUTURES_OPEN, "IDX_FUTURES_OPEN"); 
        
    /** Order for index futures contract close transaction. */
    public static final OrderCategEnum IDX_FUTURES_CLOSE = 
        new OrderCategEnum(IntValues.IDX_FUTURES_CLOSE, "IDX_FUTURES_CLOSE");  
        
    /** Order for index options contract open transaction. */
    public static final OrderCategEnum IDX_OPTIONS_OPEN = 
        new OrderCategEnum(IntValues.IDX_OPTIONS_OPEN, "IDX_OPTIONS_OPEN"); 
        
    /** Order for index options contract close transaction. */
    public static final OrderCategEnum IDX_OPTIONS_CLOSE = 
        new OrderCategEnum(IntValues.IDX_OPTIONS_CLOSE, "IDX_OPTIONS_CLOSE");
        
    /** Order for transfer */
    public static final OrderCategEnum CASH_TRANSFER = 
        new OrderCategEnum(IntValues.CASH_TRANSFER, "CASH_TRANSFER");
        
    /** Order for securities transfer */    
    public static final OrderCategEnum ASSET_TRANSFER = 
        new OrderCategEnum(IntValues.ASSET_TRANSFER, "ASSET_TRANSFER");       
    
    /** Order for fx transfer */
    public static final OrderCategEnum FX = 
        new OrderCategEnum(IntValues.FX_TRANSFER, "FX_TRANSFER");
    
    /** Order for foreign equity transfer */
    public static final OrderCategEnum FEQ_TRANSFER =   
        new OrderCategEnum(IntValues.FEQ_TRANSFER, "FEQ_TRANSFER");
    
    /** Mandatory constructor override of superclass constructor */
    public OrderCategEnum(int i, String s) {
        super(i, s);
    }
}@
