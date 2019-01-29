head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.32.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	OrderTypeEnum.java;


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
 * $History: OrderTypeEnum.java $
 * 
 * *****************  Version 4  *****************
 * User: Warlu        Date: 6/09/04    Time: 8:11a
 * Updated in $/xtrade3.8-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 3  *****************
 * User: X-zhang      Date: 04/04/30   Time: 17:21
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
 * *****************  Version 4  *****************
 * User: X-zhang      Date: 03/05/28   Time: 18:40
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 3  *****************
 * User: X-zhang      Date: 03/05/12   Time: 14:27
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * formatting using eclipse
 *
 * *****************  Version 2  *****************
 * User: X-zhang      Date: 03/05/12   Time: 14:26
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 1  *****************
 * User: Warlu        Date: 5/08/03    Time: 8:58p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 1  *****************
 * User: Warlu        Date: 5/08/03    Time: 5:34p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Moved from eqtype
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 5/05/03    Time: 10:44a
 * Updated in $/xtrade3.7-tc/eqtype-base/java/com/fitechlabs/xtrade/plugin/tc/eqtype
 * added mini stock and MF related Enums.
 */

/**
 * <p>
 * Define Order Type enum.
 * </p>
 * @@author x-zhang
 * @@version 1.0
 */
public class OrderTypeEnum extends Enumerated {

    /**
     * Inner class containing  integer value definitionss for each enum.
     */
    public static class IntValues {
        public static final int UNDEFINED = 0;

        /** order type for equity buy order. */
        public static final int EQUITY_BUY = 1;

        /** order type for equity sell orders */
        public static final int EQUITY_SELL = 2;

        /** order type for margin  long orders */
        public static final int MARGIN_LONG = 3;

        /** order type for margin short   orders */
        public static final int MARGIN_SHORT = 4;

        /** order type for close margin long contract orders. */
        public static final int CLOSE_MARGIN_LONG = 5;

        /** order type for close margin short contract orders. */
        public static final int CLOSE_MARGIN_SHORT = 6;

        /** order type for swap margin long contracts  orders */
        public static final int SWAP_MARGIN_LONG = 7;

        /** order type for swap margin short contracts.
         *
         * @@since 3.7
         */
        public static final int SWAP_MARGIN_SHORT = 8;

        //~~~~~~~~~~~~~~~~~~~~~~~~ Mini Stock order types
        /** order type for mini stock buy orders.
         *
         * @@since 3.7
         */
        public static final int MINI_STOCK_BUY = 101;

        /** order type for mini stock sell orders
         *
         * @@since 3.7
         */
        public static final int MINI_STOCK_SELL = 102;

        //~~~~~~~~~~~~~~~~~~~~~~~~ Mutual Fund order types.
        /** order type for mutual fund buy orders.
         *
         * @@since 3.7
         */
        public static final int MF_BUY = 201;

        /** order type for mutual fund sell orders.
         *
         * @@since 3.7
         */
        public static final int MF_SELL = 202;

        /** order type for mutual fund recruit orders.
         *
         */
        public static final int MF_RECRUIT = 203;

        /** order type for mutual fund switching orders.
         *
         */
        public static final int MF_SWITCHING = 204;

        //~~~~~~~~~~~~~~~  Bond related
        /** Order type for bond buy orders
         * @@since 3.7
         */
        public static final int BOND_BUY = 401;

        /** Order type for bond sell orders
         * @@since 3.7
         */
        public static final int BOND_SELL = 402;

        /** Order type for domestic bond recruit orders
         * @@since 3.7
         */
        public static final int DOMESTIC_BOND_RECRUIT = 403;

        /** Order type for domestic bond buy orders
         * @@since 3.7
         */
        public static final int DOMESTIC_BOND_BUY = 404;

        /** Order type for domestic bond sell orders
         * @@since 3.7
         */
        public static final int DOMESTIC_BOND_SELL = 405;

        //~~~~~~~~~~~~~~~~~~~~~~~~ Ruito order types.
        /**
         * order type for ruito buy orders.
         *
         * @@since 3.8
         */
        public static final int RUITO_BUY = 501;

        /**
         * order type for ruito sell orders.
         *
         * @@since 3.8
         */
        public static final int RUITO_SELL = 502;

        //~~~~~~~~~~~~~~~ Ifo related
        /**
         * order type for index futures buy-to-open transaction
         *
         * @@since 3.8
         */
        public static final int IDX_FUTURES_BUY_TO_OPEN  = 601;

        /**
         * order type for index futures sell-to-open transaction
         *
         * @@since 3.8
         */
        public static final int IDX_FUTURES_SELL_TO_OPEN  = 602;

        /**
         * order type for index futures buy-to-close transaction
         *
         * @@since 3.8
         */
        public static final int IDX_FUTURES_BUY_TO_CLOSE  = 603;

        /**
         * order type for index futures sell-to-close transaction
         *
         * @@since 3.8
         */
        public static final int IDX_FUTURES_SELL_TO_CLOSE  = 604;

        /**
         * order type for index options buy-to-open transaction
         *
         * @@since 3.8
         */
        public static final int IDX_OPTIONS_BUY_TO_OPEN  = 605;

        /**
         * order type for index options sell-to-open transaction
         *
         * @@since 3.8
         */
        public static final int IDX_OPTIONS_SELL_TO_OPEN  = 606;

        /**
         * order type for index options buy-to-close transaction
         *
         * @@since 3.8
         */
        public static final int IDX_OPTIONS_BUY_TO_CLOSE  = 607;

        /**
         * order type for index options sell-to-close transaction
         *
         * @@since 3.8
         */
        public static final int IDX_OPTIONS_SELL_TO_CLOSE  = 608;

        /**
         * Foreign Equity Buy order.
         *
         * @@since 3.8
         */
        public static final int FEQ_BUY = 701;

        /**
         * Foreign Equity Sell order.
         *
         * @@since 3.8
         */
        public static final int FEQ_SELL = 702;

        //~~~~~~~~~~~~~~~ Cash transfer related
        /** Order type for cash debit
         * @@since 3.7
         */
        public static final int CASH_OUT = 1001;

        /** Order type for cash credit
         * @@since 3.7
         */
        public static final int CASH_IN = 1002;


        //~~~~~~~~~~~~~~~ Asset transfer related
        /** Order type for asset debit
         * @@since 3.7
         */
        public static final int ASSET_OUT = 1003;

        /** Order type for asset credit
         * @@since 3.7
         */
        public static final int ASSET_IN = 1004;
        /** Value for from deposit amount margin guarantee
         * @@since 3.7
         */
        public static final int FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE = 1005;
        
        /** Value for margin guarantee from deposit amount 
         * @@since 3.7
         */
        public static final int MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT = 1006;
        
        /** Value for from deposit amount margin
         * @@since 3.7
         */
        public static final int FROM_DEPOSIT_AMOUNT_MARGIN = 1007;
            
        /** Value for margin from deposit amount 
         * @@since 3.7
         */
        public static final int MARGIN_FROM_DEPOSIT_AMOUNT = 1008;  
        
        /** Value for from safe deposit collateral securities
         * @@since 3.7
         */ 
        public static final int FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES = 1009; 
        
        /** Value for collateral securities from safe deposit 
         * @@since 3.7
         */ 
        public static final int COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT = 1010; 


        
        /** Value for fx guarantee from deposit amount 
         * @@since 3.8
         */ 
        public static final int FX_GUARANTEE_FROM_DEPOSIT_AMOUNT = 1011; 

        /** Value for deposit amount from fx guarantee
         * @@since 3.8
         */ 
        public static final int DEPOSIT_AMOUNT_FROM_FX_GUARANTEE = 1012; 

    
        /** Value for transfering to foreign equity 
         * @@since 3.9
         */ 
        public static final int TRANSFER_TO_FEQ = 1013; 

        /** Value for transfering from foreign equity
         * @@since 3.9
         */ 
        public static final int TRANSFER_FROM_FEQ = 1014; 
        
        /** Value for transfering from special account to general account
         * @@since 3.9
         */ 
        public static final int TRANSFER_FROM_SPECIAL_ACCOUNT = 1015; 
        
        /** Value for transfering from general account to special account
         * @@since 3.9
         */ 
        public static final int TRANSFER_TO_SPECIAL_ACCOUNT = 1016; 
        
        /** Value for transfering to other order
         * @@since 4.0
         */ 
        public static final int TO_OTHER_TRANSFER = 1017; 
        
        /** Value for transfering from other order
         * @@since 4.0
         */ 
        public static final int FROM_OTHER_TRANSFER = 1018; 
        
        /** Value for transfering from deposit amount dsk order
         * @@since 4.0
         */ 
        public static final int FROM_DEPOSIT_AMOUNT_DSK = 1019;
        
        /** Value for transfering from deposit amount dsk to orix credit order
         * @@since 4.0
         */ 
        public static final int TO_ORIX_CREDIT = 1020;

        /** Value for cfd from deposit amount
         * @@since 4.0
         */
        public static final int CFD_FROM_DEPOSIT_AMOUNT = 1021;

        /** Value for deposit amount from cfd
         * @@since 4.0
         */
        public static final int DEPOSIT_AMOUNT_FROM_CFD = 1022;

    }

    /** Undefined order type */
    public static final OrderTypeEnum UNDEFINED = new OrderTypeEnum(IntValues.UNDEFINED, "UNDEFINED");

    /** Order for buying equity assets */
    public static final OrderTypeEnum EQUITY_BUY = new OrderTypeEnum(IntValues.EQUITY_BUY, "EQUITY_BUY");

    /** Order for selling equity assets */
    public static final OrderTypeEnum EQUITY_SELL = new OrderTypeEnum(IntValues.EQUITY_SELL, "EQUITY_SELL");

    /** Order for opening long contract */
    public static final OrderTypeEnum MARGIN_LONG = new OrderTypeEnum(IntValues.MARGIN_LONG, "MARGIN_LONG");

    /** Order for opening short contract */
    public static final OrderTypeEnum MARGIN_SHORT = new OrderTypeEnum(IntValues.MARGIN_SHORT, "MARGIN_SHORT");

    /** Order for closing long contract by selling at market */
    public static final OrderTypeEnum CLOSE_MARGIN_LONG =
        new OrderTypeEnum(IntValues.CLOSE_MARGIN_LONG, "CLOSE_MARGIN_LONG");

    /** Order for closing short contract by buying at market */
    public static final OrderTypeEnum CLOSE_MARGIN_SHORT =
        new OrderTypeEnum(IntValues.CLOSE_MARGIN_SHORT, "CLOSE_MARGIN_SHORT");

    /** Order for closing long contract by swapping cash */
    public static final OrderTypeEnum SWAP_MARGIN_LONG =
        new OrderTypeEnum(IntValues.SWAP_MARGIN_LONG, "SWAP_MARGIN_LONG");

    /** Order for closing short contract by swapping asset */
    public static final OrderTypeEnum SWAP_MARGIN_SHORT =
        new OrderTypeEnum(IntValues.SWAP_MARGIN_SHORT, "SWAP_MARGIN_SHORT");

    /** Order type for mini stock buy orders.
     *  @@since 3.7
     */
    public static final OrderTypeEnum MINI_STOCK_BUY = new OrderTypeEnum(IntValues.MINI_STOCK_BUY, "MINI_STOCK_BUY");

    /** Order type for mini stock sell orders.
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum MINI_STOCK_SELL = new OrderTypeEnum(IntValues.MINI_STOCK_SELL, "MINI_STOCK_SELL");

    /** Order type for mutual fund buy orders.
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum MF_BUY = new OrderTypeEnum(IntValues.MF_BUY, "MF_BUY");

    /** Order type for mutual fund sell orders.
     *
     *  @@since 3.7
     */
    public static final OrderTypeEnum MF_SELL = new OrderTypeEnum(IntValues.MF_SELL, "MF_SELL");

    /** Order type for mutual fund recruit orders.
     *
     *  @@since 3.7
     */
    public static final OrderTypeEnum MF_RECRUIT = new OrderTypeEnum(IntValues.MF_RECRUIT, "MF_RECRUIT");

    /** Order type for mutual fund switching orders.
     *
     *  @@since 3.7
     */
    public static final OrderTypeEnum MF_SWITCHING = new OrderTypeEnum(IntValues.MF_SWITCHING, "MF_SWITCHING");

    /** Order type for bond buy orders
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum BOND_BUY = new OrderTypeEnum(IntValues.BOND_BUY, "BOND_BUY");

    /** Order type for bond sell orders
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum BOND_SELL = new OrderTypeEnum(IntValues.BOND_SELL, "BOND_SELL");

    /** Order type for domestic bond recruit orders
     * 
     * @@since 3.7
     */
    public static final OrderTypeEnum DOMESTIC_BOND_RECRUIT = new OrderTypeEnum(IntValues.DOMESTIC_BOND_RECRUIT, "DOMESTIC_BOND_RECRUIT");

    /** Order type for domestic bond buy orders
     * 
     * @@since 3.7
     */
    public static final OrderTypeEnum DOMESTIC_BOND_BUY = new OrderTypeEnum(IntValues.DOMESTIC_BOND_BUY, "DOMESTIC_BOND_BUY");

    /** Order type for domestic bond sell orders
     * 
     * @@since 3.7
     */
    public static final OrderTypeEnum DOMESTIC_BOND_SELL = new OrderTypeEnum(IntValues.DOMESTIC_BOND_SELL, "DOMESTIC_BOND_SELL");

    /** Order type for cash debit
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum CASH_OUT = new OrderTypeEnum(IntValues.CASH_OUT, "CASH_OUT");

    /** Order type for cash credit
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum CASH_IN = new OrderTypeEnum(IntValues.CASH_IN, "CASH_IN");

    /** Order type for asset debit
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum ASSET_OUT = new OrderTypeEnum(IntValues.ASSET_OUT, "ASSET_OUT");

    /** Order type for asset credit
     *
     * @@since 3.7
     */
    public static final OrderTypeEnum ASSET_IN = new OrderTypeEnum(IntValues.ASSET_IN, "ASSET_IN");

    /**
     * Order type for ruito  buy orders.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum RUITO_BUY = new OrderTypeEnum(IntValues.RUITO_BUY, "RUITO_BUY");

    /**
     * Order type for Ruito sell orders.
     *
     *  @@since 3.8
     */
    public static final OrderTypeEnum RUITO_SELL = new OrderTypeEnum(IntValues.RUITO_SELL, "RUITO_SELL");

    /**
     * order type for index futures buy-to-open order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_FUTURES_BUY_TO_OPEN =
        new OrderTypeEnum(IntValues.IDX_FUTURES_BUY_TO_OPEN, "IDX_FUTURES_BUY_TO_OPEN");


    /**
     * order type for index futures sell-to-open order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_FUTURES_SELL_TO_OPEN =
        new OrderTypeEnum(IntValues.IDX_FUTURES_SELL_TO_OPEN, "IDX_FUTURES_SELL_TO_OPEN");

    /**
     * order type for index futures buy-to-close order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_FUTURES_BUY_TO_CLOSE =
        new OrderTypeEnum(IntValues.IDX_FUTURES_BUY_TO_CLOSE, "IDX_FUTURES_BUY_TO_CLOSE");

    /**
     * order type for index futures sell-to-close order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_FUTURES_SELL_TO_CLOSE =
        new OrderTypeEnum(IntValues.IDX_FUTURES_SELL_TO_CLOSE, "IDX_FUTURES_SELL_TO_CLOSE");

    /**
     * order type for index options buy-to-open order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_OPTIONS_BUY_TO_OPEN =
        new OrderTypeEnum(IntValues.IDX_OPTIONS_BUY_TO_OPEN, "IDX_OPTIONS_BUY_TO_OPEN");


    /**
     * order type for index options sell-to-open order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_OPTIONS_SELL_TO_OPEN =
        new OrderTypeEnum(IntValues.IDX_OPTIONS_SELL_TO_OPEN, "IDX_OPTIONS_SELL_TO_OPEN");

    /**
     * order type for index options buy-to-close order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_OPTIONS_BUY_TO_CLOSE =
        new OrderTypeEnum(IntValues.IDX_OPTIONS_BUY_TO_CLOSE, "IDX_OPTIONS_BUY_TO_CLOSE");

    /**
     * order type for index options sell-to-close order.
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum IDX_OPTIONS_SELL_TO_CLOSE =
        new OrderTypeEnum(IntValues.IDX_OPTIONS_SELL_TO_CLOSE, "IDX_OPTIONS_SELL_TO_CLOSE");

    /**
     * order type for foreign equity buy,
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum FEQ_BUY = new OrderTypeEnum(IntValues.FEQ_BUY, "FEQ_BUY");

    /**
     * order type for foreign equity sell,
     *
     * @@since 3.8
     */
    public static final OrderTypeEnum FEQ_SELL = new OrderTypeEnum(IntValues.FEQ_SELL, "FEQ_SELL");
    
        /** Value for from deposit amount margin guarantee
    
     * @@since 3.7
     */     
    public static final OrderTypeEnum FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE =
        new OrderTypeEnum(IntValues.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE, 
            "FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE");    
            
    /** Value for margin guarantee from deposit amount 
     * @@since 3.7
     */
    public static final OrderTypeEnum MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT =
                new OrderTypeEnum(IntValues.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
                "MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT");    
                
    /** Value for from deposit amount margin
     *
     * @@since 3.7
     */      
    public static final OrderTypeEnum FROM_DEPOSIT_AMOUNT_MARGIN =
                new OrderTypeEnum(IntValues.FROM_DEPOSIT_AMOUNT_MARGIN, 
                "FROM_DEPOSIT_AMOUNT_MARGIN");  
    /** Value for margin from deposit amount 
     * @@since 3.7
     */         
    public static final OrderTypeEnum MARGIN_FROM_DEPOSIT_AMOUNT =
                new OrderTypeEnum(IntValues.MARGIN_FROM_DEPOSIT_AMOUNT, 
                "MARGIN_FROM_DEPOSIT_AMOUNT");  
                
    /** Value for from safe deposit collateral securities
     * @@since 3.7
     */ 
    public static final OrderTypeEnum FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES =                     
                new OrderTypeEnum(IntValues.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES, 
                "FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES");
                 
    /** Value for collateral securities from safe deposit 
     * @@since 3.7
     */ 
    public static final OrderTypeEnum COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT = 
                new OrderTypeEnum(IntValues.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT, 
                "COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT"); 

    
    /** Value for fx guarantee from deposit amount 
     * @@since 3.8
     */ 
    public static final OrderTypeEnum FX_GUARANTEE_FROM_DEPOSIT_AMOUNT =  
        new OrderTypeEnum(IntValues.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
        "FX_GUARANTEE_FROM_DEPOSIT_AMOUNT"); 

    /** Value for deposit amount from fx guarantee
     * @@since 3.8
     */ 
    public static final OrderTypeEnum DEPOSIT_AMOUNT_FROM_FX_GUARANTEE =
        new OrderTypeEnum(IntValues.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE, 
        "DEPOSIT_AMOUNT_FROM_FX_GUARANTEE"); 
    
                
    /** Value for transfering to foreign equity 
     * @@since 3.9
     */ 
    public static final OrderTypeEnum TRANSFER_TO_FEQ =  
        new OrderTypeEnum(IntValues.TRANSFER_TO_FEQ, 
        "TRANSFER_TO_FEQ"); 

    /** Value for transfering from foreign equity
     * @@since 3.9
     */ 
    public static final OrderTypeEnum TRANSFER_FROM_FEQ =  
        new OrderTypeEnum(IntValues.TRANSFER_FROM_FEQ, 
        "TRANSFER_FROM_FEQ"); 
    
    /** Value for transfering from special account to general account
     * @@since 3.9
     */ 
    public static final OrderTypeEnum TRANSFER_FROM_SPECIAL_ACCOUNT =
        new OrderTypeEnum(IntValues.TRANSFER_FROM_SPECIAL_ACCOUNT, 
        "TRANSFER_FROM_SPECIAL_ACCOUNT"); 
    
    /** Value for transfering from general account to special account
     * @@since 3.9
     */ 
    public static final OrderTypeEnum TRANSFER_TO_SPECIAL_ACCOUNT =  
        new OrderTypeEnum(IntValues.TRANSFER_TO_SPECIAL_ACCOUNT, 
        "TRANSFER_TO_SPECIAL_ACCOUNT"); 

    /** Value for transfering to other order
     * @@since 4.0
     */ 
    public static final OrderTypeEnum TO_OTHER_TRANSFER =  
        new OrderTypeEnum(IntValues.TO_OTHER_TRANSFER, 
        "TO_OTHER_TRANSFER"); 
    
    /** Value for transfering from other order
     * @@since 4.0
     */ 
    public static final OrderTypeEnum FROM_OTHER_TRANSFER =   
        new OrderTypeEnum(IntValues.FROM_OTHER_TRANSFER, 
        "FROM_OTHER_TRANSFER"); 
    
    /** Value for transfering from deposit amount dsk order
     * @@since 4.0
     */ 
    public static final OrderTypeEnum FROM_DEPOSIT_AMOUNT_DSK =   
        new OrderTypeEnum(IntValues.FROM_DEPOSIT_AMOUNT_DSK, 
        "FROM_DEPOSIT_AMOUNT_DSK");
    
    /** Value for transfering from deposit amount dsk to orix credit order
     * @@since 4.0
     */ 
    public static final OrderTypeEnum TO_ORIX_CREDIT =   
        new OrderTypeEnum(IntValues.TO_ORIX_CREDIT, 
        "TO_ORIX_CREDIT");

    /** Value for cfd from deposit amount
     * @@since 4.0
     */
    public static final OrderTypeEnum CFD_FROM_DEPOSIT_AMOUNT =
        new OrderTypeEnum(IntValues.CFD_FROM_DEPOSIT_AMOUNT,
        "CFD_FROM_DEPOSIT_AMOUNT");

    /** Value for deposit amount from cfd
     * @@since 4.0
     */
    public static final OrderTypeEnum DEPOSIT_AMOUNT_FROM_CFD =  
        new OrderTypeEnum(IntValues.DEPOSIT_AMOUNT_FROM_CFD,
        "DEPOSIT_AMOUNT_FROM_CFD");

    /**
     * Returns the corresponding FinTransactionType. The values of EqOrderType are subset
     * of the values of FinTransactionType.
     *
     * @@return the FinTransactionType with the same value of the EqOrderType
     *
     * @@deprecated Use toFinTransactionType instead.
     */
    public FinTransactionType getFinTransactionType() {

        return toFinTransactionType();
    }

    /**
     * Returns the equivalent FinTransactionType for this order type.
     *
     * @@return Returns equivalent fin transaction type.
     *
     * @@since 3.7
     */
    public FinTransactionType toFinTransactionType() {

        switch (this.intValue()) {

            case IntValues.EQUITY_BUY :
                return FinTransactionType.EQTYPE_EQUITY_BUY;

            case IntValues.EQUITY_SELL :
                return FinTransactionType.EQTYPE_EQUITY_SELL;

            case IntValues.MARGIN_LONG :
                return FinTransactionType.EQTYPE_MARGIN_LONG;

            case IntValues.MARGIN_SHORT :
                return FinTransactionType.EQTYPE_MARGIN_SHORT;

            case IntValues.CLOSE_MARGIN_LONG :
                return FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG;

            case IntValues.CLOSE_MARGIN_SHORT :
                return FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT;

            case IntValues.SWAP_MARGIN_LONG :
                return FinTransactionType.EQTYPE_SWAP_MARGIN_LONG;

            case IntValues.SWAP_MARGIN_SHORT :
                return FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT;

            case IntValues.MINI_STOCK_BUY :
                return FinTransactionType.EQTYPE_MINI_STOCK_BUY;

            case IntValues.MINI_STOCK_SELL :
                return FinTransactionType.EQTYPE_MINI_STOCK_SELL;

            case IntValues.MF_BUY :
                return FinTransactionType.EQTYPE_MF_BUY;

            case IntValues.MF_SELL :
                return FinTransactionType.EQTYPE_MF_SELL;

            case IntValues.MF_RECRUIT :
                return FinTransactionType.EQTYPE_MF_RECRUIT;

            case IntValues.MF_SWITCHING :
                return FinTransactionType.EQTYPE_MF_SWITCHING;

            case IntValues.FEQ_BUY :
                return FinTransactionType.EQTYPE_FEQ_BUY;

            case IntValues.FEQ_SELL :
                return FinTransactionType.EQTYPE_FEQ_SELL;

            case IntValues.RUITO_BUY :
                return FinTransactionType.EQTYPE_RUITO_BUY;

            case IntValues.RUITO_SELL :
                return FinTransactionType.EQTYPE_RUITO_SELL;

            case IntValues.IDX_FUTURES_BUY_TO_OPEN :
                return FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_OPEN;

            case IntValues.IDX_FUTURES_SELL_TO_OPEN :
                return FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_OPEN;

            case IntValues.IDX_FUTURES_BUY_TO_CLOSE :
                return FinTransactionType.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE;

            case IntValues.IDX_FUTURES_SELL_TO_CLOSE :
                return FinTransactionType.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE;

            case IntValues.IDX_OPTIONS_BUY_TO_OPEN :
                return FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN;

            case IntValues.IDX_OPTIONS_SELL_TO_OPEN :
                return FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_OPEN;

            case IntValues.IDX_OPTIONS_BUY_TO_CLOSE :
                return FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE;

            case IntValues.IDX_OPTIONS_SELL_TO_CLOSE :
                return FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE;

            case IntValues.BOND_BUY :
                return FinTransactionType.EQTYPE_BOND_BUY;

            case IntValues.BOND_SELL :
                return FinTransactionType.EQTYPE_BOND_SELL;

            case IntValues.CASH_OUT :
                return FinTransactionType.DEBIT;

            case IntValues.CASH_IN :
                return FinTransactionType.CREDIT;

            case IntValues.ASSET_OUT :
                return FinTransactionType.ASSET_OUT;

            case IntValues.ASSET_IN :
                return FinTransactionType.ASSET_IN;
                
            case IntValues.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE:
                return FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE; 
                
            case IntValues.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT:
                return FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT; 
                
            case IntValues.FROM_DEPOSIT_AMOUNT_MARGIN:
                return FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN;   
                
            case IntValues.MARGIN_FROM_DEPOSIT_AMOUNT:
                return FinTransactionType.MARGIN_FROM_DEPOSIT_AMOUNT;       
                
            case IntValues.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES:
                return FinTransactionType.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;   
                
            case IntValues.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT:
                return FinTransactionType.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;                                                       
                
            /** Value for fx guarantee from deposit amount 
             * @@since 3.8
             */ 
            case IntValues.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT: 
                return FinTransactionType.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;                                                       

            /** Value for deposit amount from fx guarantee
             * @@since 3.8
             */ 
            case IntValues.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE:
                return FinTransactionType.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;                                                       

            
            case IntValues.TRANSFER_TO_FEQ:
                return FinTransactionType.TRANSFER_TO_FEQ;     
            
            case IntValues.TRANSFER_FROM_FEQ:
                return FinTransactionType.TRANSFER_FROM_FEQ;                                
            
            case IntValues.TRANSFER_FROM_SPECIAL_ACCOUNT:
                return FinTransactionType.TRANSFER_FROM_SPECIAL_ACCOUNT;                                
            
            case IntValues.TRANSFER_TO_SPECIAL_ACCOUNT:
                return FinTransactionType.TRANSFER_TO_SPECIAL_ACCOUNT;                                

            case IntValues.TO_OTHER_TRANSFER:
                return FinTransactionType.TO_OTHER_TRANSFER;                                
            
            case IntValues.FROM_OTHER_TRANSFER:
                return FinTransactionType.FROM_OTHER_TRANSFER;                                
                
            case IntValues.FROM_DEPOSIT_AMOUNT_DSK:
                return FinTransactionType.FROM_DEPOSIT_AMOUNT_DSK;                                

            case IntValues.TO_ORIX_CREDIT:
                return FinTransactionType.TO_ORIX_CREDIT;
                
            case IntValues.CFD_FROM_DEPOSIT_AMOUNT:
                return FinTransactionType.CFD_FROM_DEPOSIT_AMOUNT;
                
            case IntValues.DEPOSIT_AMOUNT_FROM_CFD:
                return FinTransactionType.DEPOSIT_AMOUNT_FROM_CFD;

            default :
                return FinTransactionType.OTHER;
        }

    }

    /** Mandatory constructor override of superclass constructor */
    public OrderTypeEnum(int i, String s) {
        super(i, s);
    }
}

@
