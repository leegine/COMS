head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更共通リクエストクラス(WEB3AdminTPChangeCalcControlCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
Revision History : 2007/07/26 趙林鵬 (中訊) モデル：No.006
Revision History : 2007/09/12 孟亞南 (中訊) モデル：No.015
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * 余力制御機@能変更共通リクエストクラス
 */
public class WEB3AdminTPChangeCalcControlCommonRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPChangeCalcControlCommonRequest.class);

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_common";

    /**
    * 顧客余力条件ID
    */
   public String calcConditionId;

   /**
    * 取引停止区分
    */
   public String tradingStop;

   /**
    * (信用新規建余力区分)
    */
   public String marginOpenPositionStop;

   /**
    * (先物OP新規建余力区分)
    */
   public String ifoOpenPositionStop;

   /**
    * (出金余力区分)
    */
   public String paymentStop;

   /**
    * (その他商品買付余力区分)
    */
   public String otherTradingStop;

   /**
    * (追証未入金区分)<BR>
    * 0:追証未入金なし 1:追証未入金あり<BR>
    */
   public String additionalDepositStop;

   /**
    * @@roseuid 41DBC928009E
    */
   public WEB3AdminTPChangeCalcControlCommonRequest()
   {

   }

   /**
    * (validate)<BR>
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
    * <BR>
    * １）顧客余力条件IDのチェック<BR>
    * 以下のチェックを行う。<BR>
    * 顧客余力条件IDが以下の条件に該当する場合、<BR>
    * 「顧客余力条件IDエラー」の例外をスローする。<BR>
    * ・顧客余力条件ID == null<BR>
    * ・顧客余力条件ID != 数値<BR>
    *
    * ２）出金余力区分のチェック<BR>
    * 以下のチェックを行う。<BR>
    * 出金余力区分が以下の条件に該当する場合、<BR>
    * 「出金余力区分がnull」の例外をスローする。<BR>
    * ・出金余力区分 == null<BR>
    * <BR>
    * ３）その他商品買付余力区分のチェック<BR>
    * 以下のチェックを行う。<BR>
    * その他商品買付余力区分が以下の条件に該当する場合、<BR>
    * 「その他商品買付余力区分がnull」の例外をスローする。<BR>
    * ・その他商品買付余力区分 == null<BR>
    * <BR>
    * ４）取引停止区分のチェック<BR>
    * 以下のチェックを行う。<BR>
    * 取引停止区分が以下の条件に該当する場合、<BR>
    * 「取引停止区分がnull」の例外をスローする。<BR>
    * ・取引停止区分 == null<BR>
    * <BR>
    * @@throws WEB3BusinessLayerException
    * @@roseuid 41C6B52B0086
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

       if(this.calcConditionId == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01893, METHOD_NAME);
       }
       //追加
       if(this.tradingStop == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01894, METHOD_NAME);
       }
       if(this.paymentStop == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01897, METHOD_NAME);
       }
       if(this.otherTradingStop == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01898, METHOD_NAME);
       }
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC92800EC
    */
   public WEB3GenResponse createResponse()
   {
    return null;
   }
}
@
