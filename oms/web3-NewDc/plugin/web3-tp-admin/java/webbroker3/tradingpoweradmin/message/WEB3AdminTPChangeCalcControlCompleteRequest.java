head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更完了リクエストクラス(WEB3AdminTPChangeCalcControlCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力制御機@能変更完了リクエストクラス
 */
public class WEB3AdminTPChangeCalcControlCompleteRequest extends WEB3AdminTPChangeCalcControlCommonRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_complete";

     /**
     * 暗証番号
     */
    public String adminPassword;

    /**
    * @@roseuid 41DBC92900CD
    */
   public WEB3AdminTPChangeCalcControlCompleteRequest()
   {
   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * １）変更内容チェック
    * 親クラス.validate()を呼ぶ。
    *
    * ２）管理者パスワードのチェック
    * 以下に該当する場合「暗証番号が未指定です。」の例外をスローする。
    * ・this.管理者パスワード == null
    * @@roseuid 41B9073202B9
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";
       super.validate();
  		if(adminPassword == null)
  		{
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01090, METHOD_NAME);
  		}
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeCalcControlCompleteResponse();
   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {
       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeCalcControlCompleteRequest={");
//       l_sb.append(super.toString());
       l_sb.append("calcConditionId=").append(this.calcConditionId);
       l_sb.append(",tradingStop=").append(this.tradingStop);
       l_sb.append(",marginOpenPositionStop=").append(this.marginOpenPositionStop);
       l_sb.append(",ifoOpenPositionStop=").append(this.ifoOpenPositionStop);
       l_sb.append(",paymentStop=").append(this.paymentStop);
       l_sb.append(",otherTradingStop=").append(this.otherTradingStop);
       l_sb.append(",adminPassword=").append(this.adminPassword);
       l_sb.append("}");

       return l_sb.toString();

   }

}
@
