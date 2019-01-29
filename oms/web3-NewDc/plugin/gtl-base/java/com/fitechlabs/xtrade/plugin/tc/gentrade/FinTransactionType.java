head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	FinTransactionType.java;


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
 * $History: FinTransactionType.java $
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
 * *****************  Version 7  *****************
 * User: X-zhang      Date: 03/05/28   Time: 18:40
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 6  *****************
 * User: X-zhang      Date: 03/05/23   Time: 14:57
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 4  *****************
 * User: X-zhang      Date: 03/05/12   Time: 14:27
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * formatting using eclipse
 *
 * *****************  Version 3  *****************
 * User: X-zhang      Date: 03/05/12   Time: 14:19
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 5/05/03    Time: 10:45a
 * Updated in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * added mini and MF related enums. Added toFinTransactionCateg method.
 *
 * *****************  Version 1  *****************
 * User: Li           Date: 4/28/03    Time: 1:18p
 * Created in $/xtrade3.7-tc/gtl-base/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 1  *****************
 * User: Li           Date: 4/25/03    Time: 4:45p
 * Created in $/xtrade3.7-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 5  *****************
 * User: Tmatsu       Date: 1/20/03    Time: 7:43a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Added a new enum value FEE.
 *
 * *****************  Version 4  *****************
 * User: X-zhang      Date: 03/01/14   Time: 15:44
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 3  *****************
 * User: X-zhang      Date: 03/01/08   Time: 15:27
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 *
 * *****************  Version 2  *****************
 * User: Warlu        Date: 1/08/03    Time: 12:32a
 * Updated in $/xtrade3.6-tc/gtl/java/com/fitechlabs/xtrade/plugin/tc/gentrade
 * Defined all possible transaction types.
 */
/**
 * <p>
 * Defines FinTransaction Enums.
 * </p>
 *
 * @@author Warlu
 * @@version 1.0
 */
public class FinTransactionType extends Enumerated {

    /**
     * Constant for represent misc/other transaction that can not be represented
     * with other enums.
     */
    public static final FinTransactionType OTHER = new FinTransactionType(IntValues.OTHER, "OTHER");

    /** Constant for cash deposit transaction. */
    public static final FinTransactionType CREDIT = new FinTransactionType(IntValues.CREDIT, "CREDIT");

    /** Constant for cash withdrawal transaction. */
    public static final FinTransactionType DEBIT = new FinTransactionType(IntValues.DEBIT, "DEBIT");

    /** Constant for commission charged transaction. */
    public static final FinTransactionType COMMISSION_CHARGED =
        new FinTransactionType(IntValues.COMMISSION_CHARGED, "COMMISSION_CHARGED");

    /** Constant for cash balance adjustment transaction. */
    public static final FinTransactionType CASH_BALANCE_ADJUSTMENT =
        new FinTransactionType(IntValues.CASH_BALANCE_ADJUSTMENT, "CASH_BALANCE_ADJUSTMENT");

    /** Constant for stock exchange or market fee charge transaction. */
    public static final FinTransactionType EXCHANGE_FEE =
        new FinTransactionType(IntValues.EXCHANGE_FEE, "EXCHANGE_FEE");

    /** Constant for EqType equity buy transaction. */
    public static final FinTransactionType EQTYPE_EQUITY_BUY =
        new FinTransactionType(IntValues.EQTYPE_EQUITY_BUY, "EQTYPE_EQUITY_BUY");

    /** Constant for EqType equity sell transaction. */
    public static final FinTransactionType EQTYPE_EQUITY_SELL =
        new FinTransactionType(IntValues.EQTYPE_EQUITY_SELL, "EQTYPE_EQUITY_SELL");

    /** Constant for EqType margin long transaction. */
    public static final FinTransactionType EQTYPE_MARGIN_LONG =
        new FinTransactionType(IntValues.EQTYPE_MARGIN_LONG, "EQTYPE_MARGIN_LONG");

    /** Constant for EqType margin short transaction. */
    public static final FinTransactionType EQTYPE_MARGIN_SHORT =
        new FinTransactionType(IntValues.EQTYPE_MARGIN_SHORT, "EQTYPE_MARGIN_SHORT");

    /** Constant for EqType margin long position close transaction. */
    public static final FinTransactionType EQTYPE_CLOSE_MARGIN_LONG =
        new FinTransactionType(IntValues.EQTYPE_CLOSE_MARGIN_LONG, "EQTYPE_CLOSE_MARGIN_LONG");

    /** Constant for EqType margin short position transaction. */
    public static final FinTransactionType EQTYPE_CLOSE_MARGIN_SHORT =
        new FinTransactionType(IntValues.EQTYPE_CLOSE_MARGIN_SHORT, "EQTYPE_CLOSE_MARGIN_SHORT");

    /** Constant for EqType margin long position swap transaction. */
    public static final FinTransactionType EQTYPE_SWAP_MARGIN_LONG =
        new FinTransactionType(IntValues.EQTYPE_SWAP_MARGIN_LONG, "EQTYPE_SWAP_MARGIN_LONG");

    /** Constant for EqType margin short position transaction. */
    public static final FinTransactionType EQTYPE_SWAP_MARGIN_SHORT =
        new FinTransactionType(IntValues.EQTYPE_SWAP_MARGIN_SHORT, "EQTYPE_SWAP_MARGIN_SHORT");

    /** Constant for EqType dividend payment  transaction. */
    public static final FinTransactionType EQTYPE_DIVIDEND =
        new FinTransactionType(IntValues.EQTYPE_DIVIDEND, "EQTYPE_DIVIDEND");

    /** Constant for EqType margin interest paid transaction. */
    public static final FinTransactionType EQTYPE_MARGIN_INTEREST_PAID =
        new FinTransactionType(IntValues.EQTYPE_MARGIN_INTEREST_PAID, "EQTYPE_MARGIN_INTEREST_PAID");

    /** Constant for EqType margin interest received transaction. */
    public static final FinTransactionType EQTYPE_MARGIN_INTEREST_RECEIVED =
        new FinTransactionType(IntValues.EQTYPE_MARGIN_INTEREST_RECEIVED, "EQTYPE_MARGIN_INTEREST_RECEIVED");

    /** Constant for Mini stock buy transaction.
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_MINI_STOCK_BUY =
        new FinTransactionType(IntValues.EQTYPE_MINI_STOCK_BUY, "EQTYPE_MINI_STOCK_BUY");

    /** Constant for Mini stock sell  transaction.
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_MINI_STOCK_SELL =
        new FinTransactionType(IntValues.EQTYPE_MINI_STOCK_SELL, "EQTYPE_MINI_STOCK_SELL");

    /** Constant for MF buy transaction.
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_MF_BUY =
        new FinTransactionType(IntValues.EQTYPE_MF_BUY, "EQTYPE_MF_BUY");

    /** Constant for MF sell transaction.
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_MF_SELL =
        new FinTransactionType(IntValues.EQTYPE_MF_SELL, "EQTYPE_MF_SELL");

    /** Constant for MF recruit transaction.
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_MF_RECRUIT =
        new FinTransactionType(IntValues.EQTYPE_MF_RECRUIT, "EQTYPE_MF_RECRUIT");

    /** Constant for MF switching transaction.
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_MF_SWITCHING =
        new FinTransactionType(IntValues.EQTYPE_MF_SWITCHING, "EQTYPE_MF_SWITCHING");

    /** Constant for bond buy transaction
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_BOND_BUY =
        new FinTransactionType(IntValues.EQTYPE_BOND_BUY, "EQTYPE_BOND_BUY");

    /** Constant for bond sell transaction
     *
     * @@since 3.7
     */
    public static final FinTransactionType EQTYPE_BOND_SELL =
        new FinTransactionType(IntValues.EQTYPE_BOND_SELL, "EQTYPE_BOND_SELL");

    /** Constant for bond redemption
     *
     * @@since 3.7
     */
    public static final FinTransactionType BOND_REDEMPTION =
        new FinTransactionType(IntValues.BOND_REDEMPTION, "BOND_REDEMPTION");

    /** Constant for CB conversion
     *
     * @@since 3.7
     */
    public static final FinTransactionType CB_CONVERSION =
        new FinTransactionType(IntValues.CB_CONVERSION, "CB_CONVERSION");

    /** Constant for Asset Debit
     *
     * @@since 3.7
     */
    public static final FinTransactionType ASSET_OUT =
        new FinTransactionType(IntValues.ASSET_OUT, "ASSET_OUT");

    /** Constant for Asset Credit
     *
     * @@since 3.7
     */
    public static final FinTransactionType ASSET_IN =
        new FinTransactionType(IntValues.ASSET_IN, "ASSET_IN");

    /**
     * Constant for Ruito  buy transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_RUITO_BUY =
        new FinTransactionType(IntValues.EQTYPE_RUITO_BUY, "EQTYPE_RUITO_BUY");

    /**
     * Constant for Ruito sell transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_RUITO_SELL =
        new FinTransactionType(IntValues.EQTYPE_RUITO_SELL, "EQTYPE_RUITO_SELL");

    /**
     * Constant for index futures buy-to-open transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_FUTURES_BUY_TO_OPEN =
        new FinTransactionType(IntValues.EQTYPE_IDX_FUTURES_BUY_TO_OPEN, "EQTYPE_IDX_FUTURES_BUY_TO_OPEN");


    /**
     * Constant for index futures sell-to-open transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_FUTURES_SELL_TO_OPEN =
        new FinTransactionType(IntValues.EQTYPE_IDX_FUTURES_SELL_TO_OPEN, "EQTYPE_IDX_FUTURES_SELL_TO_OPEN");

    /**
     * Constant for index futures buy-to-close transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_FUTURES_BUY_TO_CLOSE =
        new FinTransactionType(IntValues.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE, "EQTYPE_IDX_FUTURES_BUY_TO_CLOSE");

    /**
     * Constant for index futures sell-to-close transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_FUTURES_SELL_TO_CLOSE =
        new FinTransactionType(IntValues.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE, "EQTYPE_IDX_FUTURES_SELL_TO_CLOSE");

    /**
     * Constant for index options buy-to-open transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_OPTIONS_BUY_TO_OPEN =
        new FinTransactionType(IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN, "EQTYPE_IDX_OPTIONS_BUY_TO_OPEN");


    /**
     * Constant for index options sell-to-open transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_OPTIONS_SELL_TO_OPEN =
        new FinTransactionType(IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_OPEN, "EQTYPE_IDX_OPTIONS_SELL_TO_OPEN");

    /**
     * Constant for index options buy-to-close transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE =
        new FinTransactionType(IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE, "EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE");

    /**
     * Constant for index options sell-to-close transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE =
        new FinTransactionType(IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE, "EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE");

    /**
     * Constant for Foreign Equity buy transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_FEQ_BUY =
        new FinTransactionType(IntValues.EQTYPE_FEQ_BUY, "EQTYPE_FEQ_BUY");

    /**
     * Constant for Foreign Equity sell transaction.
     *
     * @@since 3.8
     */
    public static final FinTransactionType EQTYPE_FEQ_SELL =
        new FinTransactionType(IntValues.EQTYPE_FEQ_SELL, "EQTYPE_FEQ_SELL");

    /** Value for from deposit amount margin guarantee
    
     * @@since 3.7
     */     
    public static final FinTransactionType FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE =
        new FinTransactionType(IntValues.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE, 
            "FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE");    
            
    /** Value for margin guarantee from deposit amount 
     * @@since 3.7
     */
    public static final FinTransactionType MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT =
                new FinTransactionType(IntValues.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
                "MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT");    
                
    /** Value for from deposit amount margin
     *
     * @@since 3.7
     */      
    public static final FinTransactionType FROM_DEPOSIT_AMOUNT_MARGIN =
                new FinTransactionType(IntValues.FROM_DEPOSIT_AMOUNT_MARGIN, 
                "FROM_DEPOSIT_AMOUNT_MARGIN");  
    /** Value for margin from deposit amount 
     * @@since 3.7
     */         
    public static final FinTransactionType MARGIN_FROM_DEPOSIT_AMOUNT =
                new FinTransactionType(IntValues.MARGIN_FROM_DEPOSIT_AMOUNT, 
                "MARGIN_FROM_DEPOSIT_AMOUNT");       
                                
    /** Value for from safe deposit collateral securities
     * @@since 3.7
     */ 
    public static final FinTransactionType FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES =                     
                new FinTransactionType(IntValues.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES, 
                "FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES");
                 
    /** Value for collateral securities from safe deposit 
     * @@since 3.7
     */ 
    public static final FinTransactionType COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT = 
                new FinTransactionType(IntValues.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT, 
                "COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT"); 
    
    
    /** Value for fx guarantee from deposit amount 
     * @@since 3.8
     */ 
    public static final FinTransactionType FX_GUARANTEE_FROM_DEPOSIT_AMOUNT =  
        new FinTransactionType(IntValues.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
        "FX_GUARANTEE_FROM_DEPOSIT_AMOUNT"); 

    /** Value for deposit amount from fx guarantee
     * @@since 3.8
     */ 
    public static final FinTransactionType DEPOSIT_AMOUNT_FROM_FX_GUARANTEE =
        new FinTransactionType(IntValues.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE, 
        "DEPOSIT_AMOUNT_FROM_FX_GUARANTEE"); 
    
    /** Value for transfering to foreign equity 
     * @@since 3.9
     */ 
    public static final FinTransactionType TRANSFER_TO_FEQ =  
        new FinTransactionType(IntValues.TRANSFER_TO_FEQ, 
        "TRANSFER_TO_FEQ"); 

    /** Value for transfering from foreign equity
     * @@since 3.9
     */ 
    public static final FinTransactionType TRANSFER_FROM_FEQ =  
        new FinTransactionType(IntValues.TRANSFER_FROM_FEQ, 
        "TRANSFER_FROM_FEQ"); 
    
    /** Value for transfering from special account to general account
     * @@since 3.9
     */ 
    public static final FinTransactionType TRANSFER_FROM_SPECIAL_ACCOUNT =
        new FinTransactionType(IntValues.TRANSFER_FROM_SPECIAL_ACCOUNT, 
        "TRANSFER_FROM_SPECIAL_ACCOUNT"); 
    
    /** Value for transfering from general account to special account
     * @@since 3.9
     */ 
    public static final FinTransactionType TRANSFER_TO_SPECIAL_ACCOUNT =  
        new FinTransactionType(IntValues.TRANSFER_TO_SPECIAL_ACCOUNT, 
        "TRANSFER_TO_SPECIAL_ACCOUNT"); 

    /** Value for transfering to other order
     * @@since 4.0
     */ 
    public static final FinTransactionType  TO_OTHER_TRANSFER =  
        new FinTransactionType(IntValues.TO_OTHER_TRANSFER, 
        "TO_OTHER_TRANSFER"); 
    
    /** Value for transfering from other order
     * @@since 4.0
     */ 
    public static final FinTransactionType FROM_OTHER_TRANSFER = 
        new FinTransactionType(IntValues.FROM_OTHER_TRANSFER, 
        "FROM_OTHER_TRANSFER"); 
    
    /** Value for transfering from other order
     * @@since 4.0
     */ 
    public static final FinTransactionType FROM_DEPOSIT_AMOUNT_DSK = 
        new FinTransactionType(IntValues.FROM_DEPOSIT_AMOUNT_DSK, 
        "FROM_DEPOSIT_AMOUNT_DSK"); 

    /** Value for transfering from other order
     * @@since 4.0
     */ 
    public static final FinTransactionType TO_ORIX_CREDIT = 
        new FinTransactionType(IntValues.TO_ORIX_CREDIT, 
        "TO_ORIX_CREDIT"); 

    /** Value for cfd from deposit amount
     * @@since 4.0
     */ 
    public static final FinTransactionType CFD_FROM_DEPOSIT_AMOUNT =
        new FinTransactionType(IntValues.CFD_FROM_DEPOSIT_AMOUNT,
        "CFD_FROM_DEPOSIT_AMOUNT");

    /** Value for deposit amount from cfd
     * @@since 4.0
     */
    public static final FinTransactionType DEPOSIT_AMOUNT_FROM_CFD =
        new FinTransactionType(IntValues.DEPOSIT_AMOUNT_FROM_CFD,
        "DEPOSIT_AMOUNT_FROM_CFD");



    /**
     * Creates FinTransactionType object.
     *
     * @@param i Int value of the Enum.
     * @@param s String representation of the Enum.
     */
    public FinTransactionType(int i, String s) {
        super(i, s);
    }

    /**
     * Convert to FinTransactionCateg object.
     *
     * @@return the FinTransactionCateg object corresponding to this transaction object.
     *
     * @@deprecated use toFinTransactionCateg instead.
     */
    public FinTransactionCateg getCateg() {

        return toFinTransactionCateg();
    }

    /**
     * Returns the equivalent FinTrasacntionCateg for this fin transaction type.
     *
     * @@return Returns equivalent fin transaction categ.
     * @@since 3.7
     */
    public FinTransactionCateg toFinTransactionCateg() {

        switch (this.intValue()) {

            case IntValues.OTHER :
            case IntValues.CASH_BALANCE_ADJUSTMENT :
            case IntValues.EQTYPE_DIVIDEND :
            case IntValues.BOND_REDEMPTION :
            case IntValues.CB_CONVERSION :
                return FinTransactionCateg.OTHER;

            case IntValues.CREDIT :
            case IntValues.DEBIT :
                return FinTransactionCateg.CASH_IN_OUT;

            case IntValues.ASSET_IN :
            case IntValues.ASSET_OUT :
                return FinTransactionCateg.ASSET_IN_OUT;

            case IntValues.COMMISSION_CHARGED :
            case IntValues.EXCHANGE_FEE :
            case IntValues.EQTYPE_MARGIN_INTEREST_PAID :
            case IntValues.EQTYPE_MARGIN_INTEREST_RECEIVED :
                return FinTransactionCateg.FEE;

            case IntValues.EQTYPE_EQUITY_BUY :
            case IntValues.EQTYPE_EQUITY_SELL :
            case IntValues.EQTYPE_MINI_STOCK_BUY :
            case IntValues.EQTYPE_MINI_STOCK_SELL :
            case IntValues.EQTYPE_MF_BUY :
            case IntValues.EQTYPE_MF_SELL :
            case IntValues.EQTYPE_RUITO_BUY :
            case IntValues.EQTYPE_RUITO_SELL :
            case IntValues.EQTYPE_BOND_BUY :
            case IntValues.EQTYPE_BOND_SELL :
            case IntValues.EQTYPE_FEQ_BUY :
            case IntValues.EQTYPE_FEQ_SELL :
                return FinTransactionCateg.EQTYPE_ASSET;

            case IntValues.EQTYPE_MARGIN_LONG :
            case IntValues.EQTYPE_MARGIN_SHORT :
                return FinTransactionCateg.EQTYPE_OPEN_MARGIN;

            case IntValues.EQTYPE_CLOSE_MARGIN_LONG :
            case IntValues.EQTYPE_CLOSE_MARGIN_SHORT :
                return FinTransactionCateg.EQTYPE_CLOSE_MARGIN;

            case IntValues.EQTYPE_SWAP_MARGIN_LONG :
            case IntValues.EQTYPE_SWAP_MARGIN_SHORT :
                return FinTransactionCateg.EQTYPE_SWAP_MARGIN;

            case IntValues.EQTYPE_IDX_FUTURES_BUY_TO_OPEN:
            case IntValues.EQTYPE_IDX_FUTURES_SELL_TO_OPEN:
                return FinTransactionCateg.EQTYPE_IDX_FUTURES_OPEN;

            case IntValues.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE:
            case IntValues.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE:
                return FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;

            case IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN:
            case IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_OPEN:
                return FinTransactionCateg.EQTYPE_IDX_OPTIONS_OPEN;

            case IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE:
            case IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE:
                return FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE;
                
            case IntValues.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE:                
            case IntValues.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT:                
            case IntValues.FROM_DEPOSIT_AMOUNT_MARGIN:              
            case IntValues.MARGIN_FROM_DEPOSIT_AMOUNT:
            case IntValues.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES:
            case IntValues.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT:
                return FinTransactionCateg.CASH_TRANSFER;              

            default :
                return FinTransactionCateg.OTHER;
        }
    }

    /**
     * Returns the equivalent orderType for this FinTransactionType
     * Returns null if there is no mapping for this FinTransactionType
     *
     * @@return Returns equivalent order type.
     *
     * @@since 3.7
     */
    public OrderTypeEnum toOrderTypeEnum() {

        switch (this.intValue()) {

            case IntValues.EQTYPE_EQUITY_BUY:
                return OrderTypeEnum.EQUITY_BUY;

            case IntValues.EQTYPE_EQUITY_SELL:
                return OrderTypeEnum.EQUITY_SELL;

            case IntValues.EQTYPE_MARGIN_LONG:
                return OrderTypeEnum.MARGIN_LONG;

            case IntValues.EQTYPE_MARGIN_SHORT:
                return OrderTypeEnum.MARGIN_SHORT;

            case IntValues.EQTYPE_CLOSE_MARGIN_LONG:
                return OrderTypeEnum.CLOSE_MARGIN_LONG;

            case IntValues.EQTYPE_CLOSE_MARGIN_SHORT:
                return OrderTypeEnum.CLOSE_MARGIN_SHORT;

            case IntValues.EQTYPE_SWAP_MARGIN_LONG:
                return OrderTypeEnum.SWAP_MARGIN_LONG;

            case IntValues.EQTYPE_SWAP_MARGIN_SHORT:
                return OrderTypeEnum.SWAP_MARGIN_SHORT;

            case IntValues.EQTYPE_MINI_STOCK_BUY:
                return OrderTypeEnum.MINI_STOCK_BUY;

            case IntValues.EQTYPE_MINI_STOCK_SELL:
                return OrderTypeEnum.MINI_STOCK_SELL;

            case IntValues.EQTYPE_MF_BUY:
                return OrderTypeEnum.MF_BUY;

            case IntValues.EQTYPE_MF_SELL:
                return OrderTypeEnum.MF_SELL;

            case IntValues.EQTYPE_RUITO_BUY:
                return OrderTypeEnum.RUITO_BUY;

            case IntValues.EQTYPE_RUITO_SELL:
                return OrderTypeEnum.RUITO_SELL;

            case IntValues.EQTYPE_FEQ_BUY:
                return OrderTypeEnum.FEQ_BUY;

            case IntValues.EQTYPE_FEQ_SELL:
                return OrderTypeEnum.FEQ_SELL;

            case IntValues.EQTYPE_BOND_BUY:
                return OrderTypeEnum.BOND_BUY;

            case IntValues.EQTYPE_BOND_SELL:
                return OrderTypeEnum.BOND_SELL;

            case IntValues.EQTYPE_IDX_FUTURES_BUY_TO_OPEN:
                return OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;

            case IntValues.EQTYPE_IDX_FUTURES_SELL_TO_OPEN:
                return OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;

            case IntValues.EQTYPE_IDX_FUTURES_BUY_TO_CLOSE:
                return OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE;

            case IntValues.EQTYPE_IDX_FUTURES_SELL_TO_CLOSE:
                return OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE;

            case IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN:
                return OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;

            case IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_OPEN:
                return OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;

            case IntValues.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE:
                return OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE;

            case IntValues.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE:
                return OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE;

            case IntValues.DEBIT:
                return OrderTypeEnum.CASH_OUT;

            case IntValues.CREDIT:
                return OrderTypeEnum.CASH_IN;

            case IntValues.ASSET_OUT:
                return OrderTypeEnum.ASSET_OUT;

            case IntValues.ASSET_IN:
                return OrderTypeEnum.ASSET_IN;
                
            case IntValues.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE:
                return OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE;  
                
            case IntValues.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT:
                return OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT;  
                
            case IntValues.FROM_DEPOSIT_AMOUNT_MARGIN:
                return OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;    
                
            case IntValues.MARGIN_FROM_DEPOSIT_AMOUNT:
                return OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT;                      
                
            case IntValues.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES:
                return OrderTypeEnum.FROM_SAFE_DEPOSIT_COLLATERAL_SECURITIES;  
                
            case IntValues.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT:
                return OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT;                                


            case IntValues.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT:
                return OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;                                
            
            case IntValues.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE:
                return OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;                                

            
            case IntValues.TRANSFER_TO_FEQ:
                return OrderTypeEnum.TRANSFER_TO_FEQ;     
            
            case IntValues.TRANSFER_FROM_FEQ:
                return OrderTypeEnum.TRANSFER_FROM_FEQ;                                
            
            case IntValues.TRANSFER_FROM_SPECIAL_ACCOUNT:
                return OrderTypeEnum.TRANSFER_FROM_SPECIAL_ACCOUNT;                                
            
            case IntValues.TRANSFER_TO_SPECIAL_ACCOUNT:
                return OrderTypeEnum.TRANSFER_TO_SPECIAL_ACCOUNT;                                

            case IntValues.TO_OTHER_TRANSFER:
                return OrderTypeEnum.TO_OTHER_TRANSFER;                                
            
            case IntValues.FROM_OTHER_TRANSFER:
                return OrderTypeEnum.FROM_OTHER_TRANSFER;                                
                
            case IntValues.FROM_DEPOSIT_AMOUNT_DSK:
                return OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK;                                

            case IntValues.TO_ORIX_CREDIT:
                return OrderTypeEnum.TO_ORIX_CREDIT;                                

            case IntValues.CFD_FROM_DEPOSIT_AMOUNT:
                return OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;

            case IntValues.DEPOSIT_AMOUNT_FROM_CFD:
                return OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD;

            default :
                return null;
        }

    }

    /**
     * Inner class containing  integer value definitionss for each enum.
     */
    public static class IntValues {

        //~ Static fields/initializers ---------------------------------------------

        /** Int value of other transaction type. */
        public static final int OTHER = 0;

        /** Int value for cash deposit transaction. */
        public static final int CREDIT = 10;

        /** Int value for cash deposit transaction. */
        public static final int DEBIT = 20;

        /** Int value for commission transaction. */
        public static final int COMMISSION_CHARGED = 30;

        /** Int value for cash balance adjustment transaction. */
        public static final int CASH_BALANCE_ADJUSTMENT = 40;

        /** Int value for exchange fee  transaction. */
        public static final int EXCHANGE_FEE = 60;

        /** Int value for Equity buy transaction */
        public static final int EQTYPE_EQUITY_BUY = 70;

        /** Int value for Equity sell transaction */
        public static final int EQTYPE_EQUITY_SELL = 80;

        /** Int value for Equity margin long transaction. */
        public static final int EQTYPE_MARGIN_LONG = 90;

        /** Int value for Equity margin short. */
        public static final int EQTYPE_MARGIN_SHORT = 100;

        /** Int value for equity margin long position close transaction. */
        public static final int EQTYPE_CLOSE_MARGIN_LONG = 110;

        /** Int value for equity margin short position close transaction. */
        public static final int EQTYPE_CLOSE_MARGIN_SHORT = 120;

        /** Int value for equity margin long position swap transaction. */
        public static final int EQTYPE_SWAP_MARGIN_LONG = 130;

        /** Int value for equity margin short position swap transaction. */
        public static final int EQTYPE_SWAP_MARGIN_SHORT = 140;

        /** Int value for equity dividend payment transaction . */
        public static final int EQTYPE_DIVIDEND = 150;

        /** Int value for equity margin interest paid transaction . */
        public static final int EQTYPE_MARGIN_INTEREST_PAID = 160;

        /** Int value for equity margin interest received transaction . */
        public static final int EQTYPE_MARGIN_INTEREST_RECEIVED = 170;

        //~~~~~~~~~~~~~~~  MINI stock related
        /** Value for mini stock buy transaction
         * @@since 3.7
         */
        public static final int EQTYPE_MINI_STOCK_BUY = 201;

        /** Value for mini stock sell transaction
         *
         * @@since 3.7
         */
        public static final int EQTYPE_MINI_STOCK_SELL = 202;

        //~~~~~~~~~~~~~~~ MF related
        /**
         * Value for MF buy transaction
         *
         * @@since 3.7
         */
        public static final int EQTYPE_MF_BUY = 301;

        /** Value for MF sell transaction
         *
         * @@since 3.7
         */
        public static final int EQTYPE_MF_SELL = 302;

        /** Value for MF recruit transaction
         *
         * @@since 3.7
         */
        public static final int EQTYPE_MF_RECRUIT = 303;

        /** Value for MF switching transaction
         *
         * @@since 3.7
         */
        public static final int EQTYPE_MF_SWITCHING = 304;

        //~~~~~~~~~~~~~~~  Bond related
        /** Value for bond buy transaction
         * @@since 3.7
         */
        public static final int EQTYPE_BOND_BUY = 401;

        /** Value for bond sell transaction
         * @@since 3.7
         */
        public static final int EQTYPE_BOND_SELL = 402;

        /** Value for bond redemption
         * @@since 3.7
         */
        public static final int BOND_REDEMPTION = 403;

        /** Value for CB conversion
         * @@since 3.7
         */
        public static final int CB_CONVERSION = 404;

        //~~~~~~~~~~~~~~~ Ruito related
        /**
         * Value for Ruito buy transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_RUITO_BUY = 501;

        /**
         * Value for Ruito sell transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_RUITO_SELL = 502;

        //~~~~~~~~~~~~~~~ Ifo related
        /**
         * Value for index futures buy-to-open transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_FUTURES_BUY_TO_OPEN  = 601;

        /**
         * Value for index futures sell-to-open transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_FUTURES_SELL_TO_OPEN  = 602;

        /**
         * Value for index futures buy-to-close transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_FUTURES_BUY_TO_CLOSE  = 603;

        /**
         * Value for index futures sell-to-close transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_FUTURES_SELL_TO_CLOSE  = 604;

        /**
         * Value for index options buy-to-open transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_OPTIONS_BUY_TO_OPEN  = 605;

        /**
         * Value for index options sell-to-open transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_OPTIONS_SELL_TO_OPEN  = 606;

        /**
         * Value for index options buy-to-close transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE  = 607;

        /**
         * Value for index options sell-to-close transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE  = 608;

        //~~~~~~~~~~~~~~~ Foreign Equity  related
        /**
         * Value for Foreign Equity buy transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_FEQ_BUY = 701;

        /**
         * Value for Foreign Equity sell transaction
         *
         * @@since 3.8
         */
        public static final int EQTYPE_FEQ_SELL = 702;


        //~~~~~~~~~~~~~~~  Asset transfer related
        /** Value for Asset Debit
         * @@since 3.7
         */
        public static final int ASSET_OUT = 1003;

        /** Value for Asset Credit
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
}
@
