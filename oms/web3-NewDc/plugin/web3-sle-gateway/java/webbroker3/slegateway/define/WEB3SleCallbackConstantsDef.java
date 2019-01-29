head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleCallbackConstantsDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3SleCallbackConstantsDefクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/10/23 李(FTL) 新規作成
 */
package webbroker3.slegateway.define;

/**
 * SLEマーケットアダプタにて使用するSLE API関連の定数
 */
public class WEB3SleCallbackConstantsDef {

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
                 
      /** 香港市場のGLIDです。 */
      public final static String GLID        =  "000800000000";

    }
    
    /***********************************************************************************************
     * 深セン市場関連の定数です。
     **********************************************************************************************/
    public static class SESZ {
                 
      /** 深セン市場のGLIDです。 */
      public final static String GLID        =  "012000000000";

    }
    
    /***********************************************************************************************
     * 上海市場関連の定数です。
     **********************************************************************************************/
    public static class SESH {
                 
      /** 上海市場のGLIDです。 2009/1 時点まで未定*/
      public final static String GLID        =  "011900000000";
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
   * WEB3内で管理するオペレータタイプ
   * ↑elimination電文を解析するには、WEB3のオペレータタイプを参照する必要があるので
   * ⇒2006/11/2 追加
   */
  public static class OrderOpType {

    /** 新規注文のオペレータタイプ */
    public final static int NEW_ORDER_OP = 0;

    /** 訂正注文のオペレータタイプ */
    public final static int MODIFY_ORDER_OP    = 1;

    /** 取消注文のオペレータタイプ */
    public final static int CANCEL_ORDER_OP    = 2;
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
}


@
