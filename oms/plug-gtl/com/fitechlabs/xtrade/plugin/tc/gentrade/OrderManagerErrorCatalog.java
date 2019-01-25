// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderManagerErrorCatalog.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

public class OrderManagerErrorCatalog
{

    public OrderManagerErrorCatalog()
    {
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static ErrorManager m_errorMgr;
    public static final ErrorInfo INVALID_QTY;
    public static final ErrorInfo INVALID_PRODUCT_CODE;
    public static final ErrorInfo INVALID_MARKET_CODE;
    public static final ErrorInfo INVALID_TRADED_PRODUCT;
    public static final ErrorInfo TRADING_TEMPORARILY_SUSPENDED;
    public static final ErrorInfo INVALID_QTY_LOT_SIZE_ERROR;
    public static final ErrorInfo INVALID_LIMIT_PRICE;
    public static final ErrorInfo NO_MARKET_ORDERS;
    public static final ErrorInfo NO_LIMIT_ORDERS;
    public static final ErrorInfo OUT_OF_RANGE_LIMIT_PRICE;
    public static final ErrorInfo INVALID_LIMIT_PRICE_NOT_AS_PER_TICK_VALUE;
    public static final ErrorInfo QTY_EXCEEDED_PER_ORDER_LIMIT;
    public static final ErrorInfo AMOUNT_EXCEEDED_PER_ORDER_LIMIT;
    public static final ErrorInfo INVALID_ORDER_EXP_DATE;
    public static final ErrorInfo INVALID_ORDER_EXEC_COND_TYPE;
    public static final ErrorInfo ASSET_NOT_AVAILABLE_FOR_SELL;
    public static final ErrorInfo ASSET_NOT_AVAILABLE_FOR_SWAP;
    public static final ErrorInfo INSUFFICIENT_ASSET_QUANTITY_FOR_SELL;
    public static final ErrorInfo INSUFFICIENT_ASSET_QUANTITY_FOR_SWAP;
    public static final ErrorInfo INVALID_ORDER;
    public static final ErrorInfo INVALID_ORDER_UNIT_ID;
    public static final ErrorInfo CANCEL_FAILED_ORDER_ALREADY_CLOSED;
    public static final ErrorInfo CHANGE_FAILED_ORDER_ALREADY_CLOSED;
    public static final ErrorInfo CANCEL_FAILED_ORDER_NOT_CANCELLABLE_NOW;
    public static final ErrorInfo CHANGE_FAILED_ORDER_NOT_CHANGEABLE_NOW;
    public static final ErrorInfo INVALID_ORDER_UNIT_ID_IN_CHANGE_ORDER;
    public static final ErrorInfo NO_CHANGES_FOUND;
    public static final ErrorInfo AFTER_CHANGE_QTY_EXCEEDS_ORIG_QTY;
    public static final ErrorInfo AFTER_CHANGE_QTY_BELOW_EXECD_QTY;
    public static final ErrorInfo TRADED_PRODUCT_NOT_LISTED;
    public static final ErrorInfo TRADED_PRODUCT_NOT_LONG_MARGINABLE;
    public static final ErrorInfo TRADED_PRODUCT_NOT_SHORT_MARGINABLE;
    public static final ErrorInfo SETTLE_CONTRACT_ORDER_INVALID_CONTRACT_ID;
    public static final ErrorInfo SETTLE_CONTRACT_ORDER_INVALID_QUANTITY;
    public static final ErrorInfo MIXED_SETTLE_CONTRACT_ENTRIES;
    public static final ErrorInfo NO_SETTLE_CONTRACT_ENTRIES_SPECIFIED;
    public static final ErrorInfo MIXED_PRODUCTS_IN_SETTLE_CONTRACT_ENTRIES;
    public static final ErrorInfo INVALID_SETTLE_CONTRACT_CHANGE_SPEC;
    public static final ErrorInfo INVALID_SWAP_CONTRACT_CHANGE_SPEC;
    public static final ErrorInfo INVALID_QTY_EXCEEDS_PER_ORDER_MAX_LOTS;
    public static ErrorInfo SYSTEM_NOT_ACCEPTING_ORDERS;
    public static ErrorInfo NOT_ACCEPTING_ORDERS_FOR_MARKET;
    public static ErrorInfo DUPLICATE_ORDER_SUBMISSION;
    public static ErrorInfo FAILED_TO_ACCEPT_ORDER;
    public static ErrorInfo FAILED_TO_ACCEPT_CANCEL_ORDER;
    public static ErrorInfo FAILED_TO_ACCEPT_CHANGE_ORDER;
    public static ErrorInfo XB_MF_INVALID_PRODUCT_CODE_ISSUE_CODE;
    public static ErrorInfo XB_MF_INVALID_QTY_LESS_THAN_MIN;
    public static ErrorInfo XB_MF_ALREADY_ORDERED_ERROR;
    public static ErrorInfo XB_MINI_STOCK_TRADING_NOT_ALLOWED;
    public static ErrorInfo XB_MINI_STOCK_MAX_QTY_EXCEEDED;
    public static ErrorInfo XB_MINI_STOCK_LOT_SIZE_ERROR;
    public static ErrorInfo XB_BD_INVALID_PRODUCT_CODE_ISSUE_CODE;
    public static ErrorInfo XB_BD_INVALID_QTY_LESS_THAN_MIN;
    public static ErrorInfo XB_EXPIRE_ORDER_NOT_OPEN;
    public static ErrorInfo XB_EXPIRE_SUB_ACCOUNT_BUSY;
    public static ErrorInfo XB_RUITO_INVALID_PRODUCT_CODE_ISSUE_CODE;
    public static ErrorInfo XB_RUITO_INVALID_QTY_LESS_THAN_MIN;
    public static ErrorInfo XB_RUITO_ALREADY_ORDERED_ERROR;
    public static ErrorInfo XB_IFO_NO_PRODUCT_FOUND;
    public static ErrorInfo XB_IFO_START_TRADING_DATE_ERROR;
    public static ErrorInfo XB_IFO_LAST_TRADING_DATE_ERROR;
    public static ErrorInfo XB_IFO_TRADING_STOPPED;
    public static ErrorInfo XB_IFO_BUY_TO_OPEN_TRADING_STOPPED;
    public static ErrorInfo XB_IFO_SELL_TO_OPEN_TRADING_STOPPED;
    public static ErrorInfo XB_IFO_SELL_TO_CLOSE_TRADING_STOPPED;
    public static ErrorInfo XB_IFO_BUY_TO_CLOSE_TRADING_STOPPED;
    public static ErrorInfo XB_AIO_INVALID_PRODUCT_ID;
    public static ErrorInfo XB_FEQ_NO_PRODUCT_FOUND;
    public static ErrorInfo XB_QTY_LESS_THAN_MIN_BUY_QTY;
    public static ErrorInfo XB_QTY_LESS_THAN_MIN_SELL_QTY;

    static 
    {
        m_errorMgr = ErrorManager.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager.class);
        INVALID_QTY = m_errorMgr.defineErrorInfo("XB_INVALID_QTY", "Quantity should be an integer ");
        INVALID_PRODUCT_CODE = m_errorMgr.defineErrorInfo("XB_INVALID_PRODUCT_CODE", "Product code invalid or no product found.");
        INVALID_MARKET_CODE = m_errorMgr.defineErrorInfo("XB_INVALID_MARKET_CODE", "market code invalid or no market found.");
        INVALID_TRADED_PRODUCT = m_errorMgr.defineErrorInfo("XB_INVALID_TRADED_PRODUCT", "product and market combination invalid. Perhaps product is not traded on the specified market.");
        TRADING_TEMPORARILY_SUSPENDED = m_errorMgr.defineErrorInfo("XT_TRADING_TEMPORARILY_SUSPENDED", "Trading temporarily suspended for the specified product.");
        INVALID_QTY_LOT_SIZE_ERROR = m_errorMgr.defineErrorInfo("XB_INVALID_QTY_LOT_SIZE_ERROR", "Quantity is not in multiples of lot size.");
        INVALID_LIMIT_PRICE = m_errorMgr.defineErrorInfo("XB_INVALID_LIMIT_PRICE", "Limit price can not be negative");
        NO_MARKET_ORDERS = m_errorMgr.defineErrorInfo("XB_MARKET_ORDERS_NOT_ALLOWED", "Market doesnt accept market orders");
        NO_LIMIT_ORDERS = m_errorMgr.defineErrorInfo("XB_LIMIT_ORDERS_NOT_ALLOWED", "Market doesnt accept limit orders");
        OUT_OF_RANGE_LIMIT_PRICE = m_errorMgr.defineErrorInfo("XB_OUT_OF_RANGE_LIMIT_PRICE", "Limit price is not within the range of daily stop high and low.");
        INVALID_LIMIT_PRICE_NOT_AS_PER_TICK_VALUE = m_errorMgr.defineErrorInfo("XB_LIMIT_PRICE_NOT_AS_PER_TICK_VALUE", "Limit price is not according to the TICK value.");
        QTY_EXCEEDED_PER_ORDER_LIMIT = m_errorMgr.defineErrorInfo("XB_QTY_EXCEEDED_PER_ORDER_LIMIT", "Quantity exceeded per order limit.");
        AMOUNT_EXCEEDED_PER_ORDER_LIMIT = m_errorMgr.defineErrorInfo("XB_AMOUNT_EXCEEDED_PER_ORDER_LIMIT", "Amount exceeded per order limit.");
        INVALID_ORDER_EXP_DATE = m_errorMgr.defineErrorInfo("XB_INVALID_ORDER_EXP_DATE", "Invalid order expiration date.");
        INVALID_ORDER_EXEC_COND_TYPE = m_errorMgr.defineErrorInfo("XB_INVALID_ORDER_EXEC_COND_TYPE", "Invalid order execution condition type.");
        ASSET_NOT_AVAILABLE_FOR_SELL = m_errorMgr.defineErrorInfo("XB_ASSET_NOT_AVAILABLE_FOR_SELL", "Asset trying to sell is not available.");
        ASSET_NOT_AVAILABLE_FOR_SWAP = m_errorMgr.defineErrorInfo("XB_ASSET_NOT_AVAILABLE_FOR_SWAP", "Asset trying to SWAP is not available.");
        INSUFFICIENT_ASSET_QUANTITY_FOR_SELL = m_errorMgr.defineErrorInfo("XB_INSUFFICIENT_ASSET_QUANTITY_FOR_SELL", "Asset quantity trying to sell is not available.");
        INSUFFICIENT_ASSET_QUANTITY_FOR_SWAP = m_errorMgr.defineErrorInfo("XB_INSUFFICIENT_ASSET_QUANTITY_FOR_SWAP", "Asset quantity trying to swap is not available.");
        INVALID_ORDER = m_errorMgr.defineErrorInfo("XB_INVALID_ORDER_ID", "Invalid order id (corresponding order not found).");
        INVALID_ORDER_UNIT_ID = m_errorMgr.defineErrorInfo("XB_INVALID_ORDER_UNIT_ID", "Invalid order unit id (corresponding order unit id not found).");
        CANCEL_FAILED_ORDER_ALREADY_CLOSED = m_errorMgr.defineErrorInfo("XB_CANCEL_FAILED_ORDER_ALREADY_CLOSED", "One or more order units are already in closed state and hence can not be cancelled.");
        CHANGE_FAILED_ORDER_ALREADY_CLOSED = m_errorMgr.defineErrorInfo("XB_CHANGE_FAILED_ORDER_ALREADY_CLOSED", "One or more order units are already in closed state and hence can not be changed.");
        CANCEL_FAILED_ORDER_NOT_CANCELLABLE_NOW = m_errorMgr.defineErrorInfo("XB_CANCEL_FAILED_ORDER_NOT_CANCELLABLE_NOW", "One or more order unit's state is not in cancellable state.");
        CHANGE_FAILED_ORDER_NOT_CHANGEABLE_NOW = m_errorMgr.defineErrorInfo("XB_CHANGE_FAILED_ORDER_NOT_CHANGEABLE_NOW", "One or more order unit's state is not in changeable state.");
        INVALID_ORDER_UNIT_ID_IN_CHANGE_ORDER = m_errorMgr.defineErrorInfo("XB_INVALID_ORDER_UNIT_ID_IN_CHANGE_ORDER", "Order unit id specified in the change order spec is not valid.");
        NO_CHANGES_FOUND = m_errorMgr.defineErrorInfo("XB_NO_CHANGES_FOUND", "change order spec doesnt contain any changes.same as the original order.");
        AFTER_CHANGE_QTY_EXCEEDS_ORIG_QTY = m_errorMgr.defineErrorInfo("XB_AFTER_CHANGE_QTY_EXCEEDS_ORIG_QTY", "After change quantity should be less than the original quantity specified.");
        AFTER_CHANGE_QTY_BELOW_EXECD_QTY = m_errorMgr.defineErrorInfo("XB_AFTER_CHANGE_QTY_BELOW_EXECD_QTY", "After change quantity should be more than already executed qty.");
        TRADED_PRODUCT_NOT_LISTED = m_errorMgr.defineErrorInfo("XB_TRADED_PRODUCT_NOT_LISTED", "Specified traded product is not listed.");
        TRADED_PRODUCT_NOT_LONG_MARGINABLE = m_errorMgr.defineErrorInfo("XB_TRADED_PRODUCT_NOT_LONG_MARGINABLE", "Specified traded product can not be bought in margin.");
        TRADED_PRODUCT_NOT_SHORT_MARGINABLE = m_errorMgr.defineErrorInfo("XB_TRADED_PRODUCT_NOT_SHORT_MARGINABLE", "Specified traded product can not be sold short.");
        SETTLE_CONTRACT_ORDER_INVALID_CONTRACT_ID = m_errorMgr.defineErrorInfo("XB_SETTLE_CONTRACT_ORDER_INVALID_CONTRACT_ID", "Specified contract id in the settle contract order is not valid.");
        SETTLE_CONTRACT_ORDER_INVALID_QUANTITY = m_errorMgr.defineErrorInfo("XB_SETTLE_CONTRACT_ORDER_INVALID_QUANTITY", "Specified quantity in the settle contract order is not valid or exceeds available quantity.");
        MIXED_SETTLE_CONTRACT_ENTRIES = m_errorMgr.defineErrorInfo("XB_MIXED_SETTLE_CONTRACT_ENTRIES", "Specified settle contract order entry contains a mix of both long and short contracts. should contain any one type only.");
        NO_SETTLE_CONTRACT_ENTRIES_SPECIFIED = m_errorMgr.defineErrorInfo("XB_NO_SETTLE_CONTRACT_ENTRIES_SPECIFIED", "Specified settle contract order entry contains zero elements.");
        MIXED_PRODUCTS_IN_SETTLE_CONTRACT_ENTRIES = m_errorMgr.defineErrorInfo("XB_MIXED_PRODUCTS_IN_SETTLE_CONTRACT_ENTRIES", "Specified settle contract order entry contains a mixed products. should contain only product.");
        INVALID_SETTLE_CONTRACT_CHANGE_SPEC = m_errorMgr.defineErrorInfo("XB_INVALID_SETTLE_CONTRACT_CHANGE_SPEC", "EqTypeChangeSettleContractOrderSpec. EqTypeSettleContractEntry contains invalid entries. It should contain the same contract_id's specified in the new order");
        INVALID_SWAP_CONTRACT_CHANGE_SPEC = m_errorMgr.defineErrorInfo("XB_INVALID_SWAP_CONTRACT_CHANGE_SPEC", "EqTypeChangeSwapContractOrderSpec. EqTypeSwapContractEntry contains invalid entries. It should contain the same contract_id's specified in the new order");
        INVALID_QTY_EXCEEDS_PER_ORDER_MAX_LOTS = m_errorMgr.defineErrorInfo("XB_INVALID_QTY_EXCEEDS_PER_ORDER_MAX_LOTS", "Per order MAX allowed number of lots exceeded.");
        SYSTEM_NOT_ACCEPTING_ORDERS = m_errorMgr.defineErrorInfo("XB_SYSTEM_NOT_ACCEPTING_ORDERS", "System is currently not accepting orders.");
        NOT_ACCEPTING_ORDERS_FOR_MARKET = m_errorMgr.defineErrorInfo("XB_NOT_ACCEPTING_ORDERS_FOR_MARKET", "Market speicified is not currently accepting orders.");
        DUPLICATE_ORDER_SUBMISSION = m_errorMgr.defineErrorInfo("XB_DUPLICATE_ORDER_SUBMISSION", "Order already submitted.");
        FAILED_TO_ACCEPT_ORDER = m_errorMgr.defineErrorInfo("XB_FAILED_TO_ACCEPT_ORDER", "Failed to accept order.");
        FAILED_TO_ACCEPT_CANCEL_ORDER = m_errorMgr.defineErrorInfo("XB_FAILED_TO_ACCEPT_CANCEL_ORDER", "Failed to accept cancel order request.");
        FAILED_TO_ACCEPT_CHANGE_ORDER = m_errorMgr.defineErrorInfo("XB_FAILED_TO_ACCEPT_CHANGE_ORDER", "Failed to accept change order request.");
        XB_MF_INVALID_PRODUCT_CODE_ISSUE_CODE = m_errorMgr.defineErrorInfo("XB_MF_INVALID_PRODUCT_ISSUE_CODE", "Specified Product and issue code is not valid.");
        XB_MF_INVALID_QTY_LESS_THAN_MIN = m_errorMgr.defineErrorInfo("XB_MF_INVALID_QTY_LESS_THAN_MIN", "Specified quantity is less than minimum required initial purchase qty.");
        XB_MF_ALREADY_ORDERED_ERROR = m_errorMgr.defineErrorInfo("XB_MF_ALREADY_ORDERED_ERROR", "An open mutual fund order exists with the same product id, market id, order type and biz date.");
        XB_MINI_STOCK_TRADING_NOT_ALLOWED = m_errorMgr.defineErrorInfo("XB_MINI_STOCK_TRADING_NOT_ALLOWED", "Mini stock trading is not allowed.");
        XB_MINI_STOCK_MAX_QTY_EXCEEDED = m_errorMgr.defineErrorInfo("XB_MINI_STOCK_MAX_QTY_EXCEEDED", "Specified quantity exceeded max allowed quantity for mini stock order.");
        XB_MINI_STOCK_LOT_SIZE_ERROR = m_errorMgr.defineErrorInfo("XB_MINI_STOCK_LOT_SIZE_ERROR", "Specified mini stock order quantity is not as per mini stock lot size.");
        XB_BD_INVALID_PRODUCT_CODE_ISSUE_CODE = m_errorMgr.defineErrorInfo("XB_BD_INVALID_PRODUCT_CODE_ISSUE_CODE", "Invalid bond product and issue code specified.");
        XB_BD_INVALID_QTY_LESS_THAN_MIN = m_errorMgr.defineErrorInfo("XB_BD_INVALID_QTY_LESS_THAN_MIN", "Specified quantity in bond order is less than minimum required initial purchase qty.");
        XB_EXPIRE_ORDER_NOT_OPEN = m_errorMgr.defineErrorInfo("XB_EXPIRE_ORDER_NOT_OPEN", "The specified order is not in open state thus can't be expired.");
        XB_EXPIRE_SUB_ACCOUNT_BUSY = m_errorMgr.defineErrorInfo("XB_EXPIRE_SUB_ACCOUNT_BUSY", "The specified order's SubAccount is busy, hence a serialization lock can't be acquired.");
        XB_RUITO_INVALID_PRODUCT_CODE_ISSUE_CODE = m_errorMgr.defineErrorInfo("XB_RUITO_INVALID_PRODUCT_ISSUE_CODE", "Specified Ruito Product and issue code is not valid.");
        XB_RUITO_INVALID_QTY_LESS_THAN_MIN = m_errorMgr.defineErrorInfo("XB_RUITO_INVALID_QTY_LESS_THAN_MIN", "Specified Ruito quantity is less than minimum required initial purchase qty.");
        XB_RUITO_ALREADY_ORDERED_ERROR = m_errorMgr.defineErrorInfo("XB_RUITO_ALREADY_ORDERED_ERROR", "An open Ruito order exists with the same product id, market id, order type and biz date.");
        XB_IFO_NO_PRODUCT_FOUND = m_errorMgr.defineErrorInfo("XB_IFO_NO_PRODUCT_FOUND", "No Index futures/options product is found with the specified underlying product code, derivative type, month of delivery and strike price.");
        XB_IFO_START_TRADING_DATE_ERROR = m_errorMgr.defineErrorInfo("XB_IFO_START_TRADING_DATE_ERROR", "The start_trading_date for the specified IfoTradedProduct is greater than the biz date for the specified traded product.");
        XB_IFO_LAST_TRADING_DATE_ERROR = m_errorMgr.defineErrorInfo("XB_IFO_LAST_TRADING_DATE_ERROR", "The last_trading_date for the specified IfoTradedProduct is less than the biz date for the specified traded product.");
        XB_IFO_TRADING_STOPPED = m_errorMgr.defineErrorInfo("XB_IFO_TRADING_STOPPED", "trade_stop_flag is on for the specified IfoTradedProduct.");
        XB_IFO_BUY_TO_OPEN_TRADING_STOPPED = m_errorMgr.defineErrorInfo("XB_IFO_BUY_TO_OPEN_TRADING_STOPPED", "buy_to_open_stop_flag is on for the specified IfoTradedProduct.");
        XB_IFO_SELL_TO_OPEN_TRADING_STOPPED = m_errorMgr.defineErrorInfo("XB_IFO_SELL_TO_OPEN_TRADING_STOPPED", "sell_to_open_stop_flag is on for the specified IfoTradedProduct.");
        XB_IFO_SELL_TO_CLOSE_TRADING_STOPPED = m_errorMgr.defineErrorInfo("XB_IFO_SELL_TO_CLOSE_TRADING_STOPPED", "sell_to_close_stop_flag is on for the specified IfoTradedProduct.");
        XB_IFO_BUY_TO_CLOSE_TRADING_STOPPED = m_errorMgr.defineErrorInfo("XB_IFO_BUY_TO_CLOSE_TRADING_STOPPED", "buy_to_close_stop_flag is on for the specified IfoTradedProduct.");
        XB_AIO_INVALID_PRODUCT_ID = m_errorMgr.defineErrorInfo("XB_AIO_INVALID_PRODUCT_ID", "Invalid product id in AIO order spec.");
        XB_FEQ_NO_PRODUCT_FOUND = m_errorMgr.defineErrorInfo("XB_FEQ_NO_PRODUCT_FOUND", "No Foreign Equity Product is found with the specified product code.");
        XB_QTY_LESS_THAN_MIN_BUY_QTY = m_errorMgr.defineErrorInfo("XB_QTY_LESS_THAN_MIN_BUY_QTY", "The quantity specified is less than minimumm required buy order quantity.");
        XB_QTY_LESS_THAN_MIN_SELL_QTY = m_errorMgr.defineErrorInfo("XB_QTY_LESS_THAN_MIN_SELL_QTY", "The quantity specified is less than minimumm required sell order quantity.");
    }
}
