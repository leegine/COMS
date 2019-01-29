head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleConstants.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleConstantsクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 呉 新規作成
 */
package webbroker3.slebase.utils;

/**
 * SLEマーケットアダプタにて使用するSLE API関連の定数
 */
public class SleConstants {

  /**
   * SLEコネクタ関連の定数です。
   */

  /**
   * 市場関連の定数です。
   */
  public static class Markets {

    /***********************************************************************************************
     * 香港株関連の定数です。
     **********************************************************************************************/
    public static class SEHK {
      
      /** 香港市場名。 */
      public final static String MARKET_CODE = "N1";
      
	  /** ブローカ名。 */
	  public final static String BROKER_NAME = "HK SMBC";
      
           
      /** 香港市場のGLIDです。 */
      public final static String GLID        = "000800000000";


      /** 香港市場へリクエストID**/
      public static final String F_GL_REQ_ID = "2000SEHK";
      
      /** 大和香港に割り当てされるGL ユーザNo */
      public static final String DIR_HK_GL_NO = "101";
      
      /**
       * 注文カテゴリのSLEメッセージです。
       */
      public static class OrderCategory {

        public final static String SINGLE = "O";

        public final static String CARE   = "S";

        public final static String STOP   = "M";
      }

      /**
       * SLEの2019レスポンスメッセージの売買区分です。
       */
      public static class Side {

        /** 買い */
        public final static int BUY  = 0;

        /** 売り */
        public final static int SELL = 1;
      }

      /**
       * 取引実行時期及び執行条件関連の定数です。
       */
      public static class TradingPhase {

        /** 執行条件なし */
        public final static String UNCONDITIONAL     = " ";

        /** 不成り */
        public final static String FUNARI            = "C";

        /** 注文変更時に執行条件をクリアする際に使用 */
        public final static String CLEAR_TO_NULL     = "E"; // reset during modification as per GL

        // API spec for TSE.

        /** 寄り(TSE) */
        public final static String AT_OPEN_ONLY      = "0";

        /** 引け(TSE) */
        public final static String AT_CLOSE_ONLY     = "K";

        /** 寄り(FEQ) */
        public final static String AT_OPEN_ONLY_FEQ  = "HB";

        /** 引け(FEQ) */
        public final static String AT_CLOSE_ONLY_FEQ = "HE";
      }

      /**
       * SLEの2019レスポンスメッセージのプライスタイプです。
       */
      public static class Modality {

        /** 成行 */
        public final static String ANY_PRICE              = "B";

        /** 指値 */
        public final static String LIMIT_PRICE            = "L";

        /** 現在値指値 */
        public final static String LAST_PRICE             = "A";

        /** 優先指値 */
        public final static String BEST_PRICE             = "P";

        /** 成行残数指値 */
        public final static String MARKET_REMAINING_LIMIT = "M";
      }

      /**
       * Validity of an order.
       */
      public static class Validity {

        /**
         * 本日中 注意： メッセージのMFDファ@イルにてディフォルトとして設定している為送信側で特に設定していない。
         */
        public final static String ALL_DAY = "J";

        /**
         * 値段条件が成行残数取消の時に使用。
         */
        public final static String FAK     = "E";
      }

      /**
       * 香港株の取引所メッセージタイプ関連のエナムです。
       */
      public static class ExchangeMessageType {

        /**
         * マーケットステータスに変更があることを示します。
         */
        public final static String MARKET_STATUS_MESSAGE = "1";

        /**
         * 市場にリンクステータスの変更があることを示します。
         */
        public final static String LINK_STATUS_MESSAGE   = "5";
        
        /**
         * 市場取引ステータス変更があることを示します。
         */
        public final static String EXCHANGE_STATUS_MESSAGE = "2";
        
        /**
         * 全体市場ステータス変更があることを示します。
         */
        public final static String ALL_STATUS_MESSAGE = "6";
        
        /**
         * 特定銘柄ステータス変更があることを示します。
         */
        public final static String BRAND_STATUS_MESSAGE = "8";
        
        
      }

      /**
       * 香港株の取引所メッセージコード関連のエナムです。
       */
      public static class ExchangeMessageCode {

        /**
         * 開始前を示します。
         */
        public final static String PRE_OPEN             = "P";

        /**
         * クローズ・終了を示します。
         */
        public final static String CLOSED               = "E";

        /**
         * リンクが完全に失われたことを示します。
         */
        public final static String LINK_COMPLETELY_LOST = "Z";

        /**
         * リンクが部分的に失われたことを示します。
         */
        public final static String LINK_PARTIALLY_LOST  = "A";

        /**
         * リンクが修復されたことを示します。
         */
        public final static String LINK_RESTORED        = "N";
      }
      
      /**
       * Market Trading Status
       */
      public static class MarketTradingStatus {
          
          /**
           * 封鎖中
           */
          public final static String BLOCK = "FE";
          /**
           * クローズ(全体)
           */
          public final static String CLOSE_GENERAL = "HE";
          /**
           * 取引中
           */
          public final static String EXCHANGGING = "IB";
          /**
           * 市場未オープン
           */
          public final static String NOT_OPEN = "JB";
          /**
           * 注文取消し
           */          
          public final static String ORDER_CANCEL = "BB";
          /**
           * 注文入力
           */
          public final static String ORDER_INPUT = "FB";
          /**
           * 取消/訂正不可
           */
          public final static String CAN_NOT_MODIFY_AND_CANCEL = "FB";
          /**
           * 市場介在
           */
          public final static String MARKET_INTERVENTION = "BB";
          /**
           * クローズ
           */
          public final static String DAY_CLOSE = "DB";
          /**
           * 凍結中
           */
          public final static String FREEZE = "BB";
      }

      /**
       * マーケットステータスの変更通知に関連する取引所からのメッセージの証券コードに設定される値です。
       */
      public final static String STOCK_CODE_FOR_EXCHANGE_MESSAGES = "SZEQ";

    }
    
    /***********************************************************************************************
     * 深セン市場関連の定数。
     **********************************************************************************************/
    public static class  SESZ{
      
      /** 深セン市場名。 */
      public final static String MARKET_CODE = "N2";
           
      /** 深セン市場のGLIDです。 */
      public final static String GLID        = "012000000000";

      /** 深セン市場へリクエストID**/
      public static final String F_GL_REQ_ID = "2000SESZ";
      
      /**
       * 注文カテゴリのSLEメッセージです。
       */
      public static class OrderCategory {

        public final static String SINGLE = "O";

        public final static String CARE   = "S";

        public final static String STOP   = "M";
      }

      /**
       * SLEの2019レスポンスメッセージの売買区分です。
       */
      public static class Side {

        /** 買い */
        public final static int BUY  = 0;

        /** 売り */
        public final static int SELL = 1;
      }

      /**
       * 取引実行時期及び執行条件関連の定数です。
       */
      public static class TradingPhase {

        /** 執行条件なし */
        public final static String UNCONDITIONAL     = " ";

        /** 不成り */
        public final static String FUNARI            = "C";

        /** 注文変更時に執行条件をクリアする際に使用 */
        public final static String CLEAR_TO_NULL     = "E"; // reset during modification as per GL

        // API spec for TSE.

        /** 寄り(TSE) */
        public final static String AT_OPEN_ONLY      = "0";

        /** 引け(TSE) */
        public final static String AT_CLOSE_ONLY     = "K";

        /** 寄り(FEQ) */
        public final static String AT_OPEN_ONLY_FEQ  = "HB";

        /** 引け(FEQ) */
        public final static String AT_CLOSE_ONLY_FEQ = "HE";
      }

      /**
       * SLEの2019レスポンスメッセージのプライスタイプです。
       */
      public static class Modality {

        /** 成行 */
        public final static String ANY_PRICE              = "B";

        /** 指値 */
        public final static String LIMIT_PRICE            = "L";

        /** 現在値指値 */
        public final static String LAST_PRICE             = "A";

        /** 優先指値 */
        public final static String BEST_PRICE             = "P";

        /** 成行残数指値 */
        public final static String MARKET_REMAINING_LIMIT = "M";
      }

      /**
       * Validity of an order.
       */
      public static class Validity {

        /**
         * 本日中 注意： メッセージのMFDファ@イルにてディフォルトとして設定している為送信側で特に設定していない。
         */
        public final static String ALL_DAY = "J";

        /**
         * 値段条件が成行残数取消の時に使用。
         */
        public final static String FAK     = "E";
      }

      /**
       * 中国株の取引所メッセージタイプ関連のエナムです。
       */
      public static class ExchangeMessageType {

        /**
         * マーケットステータスに変更があることを示します。
         */
        public final static String MARKET_STATUS_MESSAGE = "1";

        /**
         * 市場にリンクステータスの変更があることを示します。
         */
        public final static String LINK_STATUS_MESSAGE   = "5";
        
        /**
         * 市場取引ステータス変更があることを示します。
         */
        public final static String EXCHANGE_STATUS_MESSAGE = "2";
        
        /**
         * 全体市場ステータス変更があることを示します。
         */
        public final static String ALL_STATUS_MESSAGE = "6";
        
        /**
         * 特定銘柄ステータス変更があることを示します。
         */
        public final static String BRAND_STATUS_MESSAGE = "8";
        
        
      }

      /**
       * 中国株の取引所メッセージコード関連のエナムです。
       */
      public static class ExchangeMessageCode {

        /**
         * 開始前を示します。
         */
        public final static String PRE_OPEN             = "P";

        /**
         * クローズ・終了を示します。
         */
        public final static String CLOSED               = "E";

        /**
         * リンクが完全に失われたことを示します。
         */
        public final static String LINK_COMPLETELY_LOST = "Z";

        /**
         * リンクが部分的に失われたことを示します。
         */
        public final static String LINK_PARTIALLY_LOST  = "A";

        /**
         * リンクが修復されたことを示します。
         */
        public final static String LINK_RESTORED        = "N";
      }
      
      /**
       * Market Trading Status
       */
      public static class MarketTradingStatus {
          
          /**
           * 封鎖中
           */
          public final static String BLOCK = "FE";
          /**
           * クローズ(全体)
           */
          public final static String CLOSE_GENERAL = "HE";
          /**
           * 取引中
           */
          public final static String EXCHANGGING = "IB";
          /**
           * 市場未オープン
           */
          public final static String NOT_OPEN = "JB";
          /**
           * 注文取消し
           */          
          public final static String ORDER_CANCEL = "BB";
          /**
           * 注文入力
           */
          public final static String ORDER_INPUT = "FB";
          /**
           * 取消/訂正不可
           */
          public final static String CAN_NOT_MODIFY_AND_CANCEL = "FB";
          /**
           * 市場介在
           */
          public final static String MARKET_INTERVENTION = "BB";
          /**
           * クローズ
           */
          public final static String DAY_CLOSE = "DB";
          /**
           * 凍結中
           */
          public final static String FREEZE = "BB";
      }
      
    }
    
    /***********************************************************************************************
     * 上海市場関連の定数。
     **********************************************************************************************/
    public static class SESH extends SESZ
    {
      
      /** 上海市場名。 */
      public final static String MARKET_CODE = "X1";
      
      /** 上海市場のGLIDです。 */
      public final static String GLID        = "011900000000";

      /** 上海市場へリクエストID**/
      public static final String F_GL_REQ_ID = "2000SESH";
    }
    
  }


  /**
   * SLEの2019レスポンスメッセージの応答タイプです。
   */
  public static class RepliesType {

    /** 新規、変更、取消の応答 */
    public final static String ACK             = "A";

    /** GL側のリジェクト */
    public final static String GL_REJECT       = "G";

    /** 市場からのリジェクト */
    public final static String EXCHANGE_REJECT = "C";

    /** 約定 */
    public final static String TRADE_EXEC      = "R";

    /** 市場からの管理電文 */
    public final static String EXCHANGE_MSG    = "J";
    
    /** 市場からのAlert電文 */
	public final static String ALERT_MSG    = "L";
  }

  /**
   * Ackタイプです。
   */
  public static class AckType {

    /** 取消済み */
    public final static int CANCELLED        = 1;

    /** 失効済み */
    public final static int ORDER_ELIMINATED = 2;

    /** 受付済み */
    public final static int ORDER_ACCEPTED   = 4;
  }

  /**
   * Ackコマンドのタイプです。
   */
  public static class AckdCommand {

    /** 新規注文のAck */
    public final static int NEW_ORDER_ACK = 0;

    /** 取消のAck */
    public final static int CANCEL_ACK    = 1;

    /** 変更のAck */
    public final static int MODIFY_ACK    = 2;
  }

  /**
   * Rejectコマンドのタイプです。
   */
  public static class RejectedCommandType {

    /** 新規注文のリジェクト */
    public final static int REJECT_NEW_ORDER = 0;

    /** 取消のリジェクト */
    public final static int REJECT_CANCEL    = 1;

    /** 変更のリジェクト */
    public final static int REJECT_MODIFY    = 2;
  }

  /**
   * SubStatusのタイプです。
   */
  public static class SubStatusType {

	/** 市場における注文状態 */
	public final static String BRANK = " ";

	/** SLEにおける注文状態 */
	public final static String STATUS_AT_SLE    = "Q";

	/** OGキューにおける注文状態 */
	public final static String STATUS_AT_OG    = "E";
  }

  /**
   * SLEの2019レスポンスメッセージの注文ステータスです。注文ステータスはフィールド番号41に対応します。
   */
  public static class OrderStatus41 {

    public final static String ORDER_WAITING                    = "000";

    public final static String ORDER_WAITING_PARTIALLY_EXEC     = "002";

    public final static String ORDER_ACKD                       = "060";

    public final static String ORDER_PARTIALLY_EXECD            = "062";

    public final static String ORDER_TOTALLY_EXEC               = "063";

    public final static String ORDER_CANCELLED                  = "160";

    public final static String ORDER_CANCELLED_PARTIALLY_EXECD  = "162";

    public final static String ORDER_REJECTED                   = "030";

    public final static String ORDER_REJECTED_PARTIALLY_EXECD   = "032";

    public final static String ORDER_CANCELLING                 = "660";

    public final static String ORDER_CANCELLING_PARTIALLY_EXECD = "662";

    public final static String ORDER_MODIFYING                  = "560";

    public final static String ORDER_MODIFIED_PARTIALLY_EXECD   = "462";

    public final static String ORDER_MODIFIED                   = "460";

    public final static String ORDER_MODIFYING_PARTIALLY_EXECD  = "562";

    /** 変更注文の接頭辞です。 */
    public final static String ORDER_MODIFYING_PREFIX           = "5";

    /** 取消注文の接頭辞です。 */
    public final static String ORDER_CANCELLING_PREFIX          = "6";

  }

  /**
   * SLEメッセージ（新規注文）のコマンドフィールドです。
   */
  public static class OrderCommand {

    /** 新規注文 */
    public final static int NEW           = 0;

    /** 取消 */
    public final static int CANCEL        = 1;

    /** 変更 */
    public final static int MODIFY        = 2;

    /** グローバルキャンセル（未使用） */
    public final static int GLOBAL_CANCEL = 5;
  }

  /**
   * SLE2000電文項目です。
   */
  public static class Order2000MsgItem {
      
    public static final String F_USER_NUMBER          = "user_number";
    
    public static final String F_CUSTOMER_ACCOUNT     = "customer_account";
    
    public static final String F_VALIDITY             = "validity";
    
    public static final String F_SIDE                 = "side";

    public static final String F_COUNTERPART          = "counterpart";

    public static final String F_MARKET_CODE          = "market_code";

    public static final String F_MEMO                 = "memo";

    public static final String F_EXCHANGE_REFERENCE   = "exchange_reference";

    public static final String F_INTERNAL_REFERENCE   = "internal_reference";

    public static final String F_GLID                 = "glid";

    public static final String F_STOCK_CODE           = "stock_code";

    public static final String F_ORDER_CATEGORY       = "order_category";

    public static final String F_PRICE                = "price";

    public static final String F_MODALITY             = "modality";

    public static final String F_QUANTITY             = "quantity";

    public static final String F_COMMAND              = "command";

    public static final String F_ORDER_TYPE           = "order_type";

    public static final String F_REMAINING_QUANTITY   = "remaining_quantity";

    public static final String F_TRIGGER_MARKET_PHASE = "trigger_market_phase";
    
    public static final String F_TRIGGER_PARAMETER = "trigger_parameter";
    
    public static final String F_LONG_NAME = "long_name";
    
    public static final String ROUTING_REFERENCE = "routing_reference";

  }

//  /**
//   * 香港ブローカ情報
//   */
//  public static class HKBroker {
//
//    public static final String BROKER_NAME   = "HK SBMC";
//
//    public static final String BROKER_MARKET = "N1";
//  }

  /**
   * 注文コメント
   */
  public static class GLDATACOMMENT {

    //新規
    public static final String ORDER_NEW    = "New order request";

    //取消
    public static final String ORDER_CANCEL = "Cancel order request";

    //訂正
    public static final String ORDER_CHG    = "Change order request";
  }

  /**
   * 市場ステータス管理テーブル
   */
  public static class SLEMARKETSTATUS {

    //1：無条件送信   
    public static final int SEND_OK        = 1;

    //2：無条件送信しない
    public static final int SEND_FALSE     = 2;

    //0：オフライン状態(市場関連)
    //1：オンライン状態(市場関連)
    //2：リンク復旧(コネクタ関連)
    //3：リンク完全失い(コネクタ関連)
    public static final int STATUS_OFFLINE  = 0;

    public static final int STATUS_ONLINE = 1;

    public static final int STATUS_LINK    = 2;

    public static final int STATUS_NOLINK  = 3;


  }

}


@
