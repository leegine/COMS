head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更入力画面リクエストクラス(WEB3AdminTPChangeCalcControlInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力制御機@能変更入力画面リクエストクラス
 */
public class WEB3AdminTPChangeCalcControlInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_input";

   /**
    * 顧客余力条件ID
    */
   public String calcConditionId;

   /**
    * @@roseuid 41DBC927036C
    */
   public WEB3AdminTPChangeCalcControlInputRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * １）顧客余力条件IDのチェック
    * 以下のチェックを行う。
    * 顧客余力条件IDが以下の条件に該当する場合、
    * 「顧客余力条件IDがnull」の例外をスローする。
    * ・顧客余力条件ID == null
    * @@roseuid 41CFE5690372
    */
   public void validate() throws WEB3BusinessLayerException
   {
  		final String METHOD_NAME = "validate()";

   		//２）預り証券評価制区分のチェック
   		if(this.calcConditionId == null)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01893, METHOD_NAME);
   		}
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC92703AB
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeCalcControlInputResponse();
   }


}
@
