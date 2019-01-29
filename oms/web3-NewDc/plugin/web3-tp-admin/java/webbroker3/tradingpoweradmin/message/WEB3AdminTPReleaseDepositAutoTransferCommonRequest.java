head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReleaseDepositAutoTransferCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止解除共通リクエストクラス(WEB3AdminTPReleaseDepositAutoTransferCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金自動振替停止解除共通リクエストクラス
 */
public class WEB3AdminTPReleaseDepositAutoTransferCommonRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_release_depositautotransfer_common";

   /**
    * 保証金自動振替停止ID一覧
    */
   public String[] autoTransferStopIds;

   /**
    * @@roseuid 41DBC97902B1
    */
   public WEB3AdminTPReleaseDepositAutoTransferCommonRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * １）保証金自動振替停止IDのチェック
    * 以下に該当する場合「保証金自動振替停止IDがnull」の例外をスローする。
    * ・保証金自動振替停止ID[] == null
    * ・保証金自動振替停止ID[i] == null
    * (iは0から配列の件数分)
    * @@roseuid 41D123D00360
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

       if(this.autoTransferStopIds == null)
       {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01903, METHOD_NAME);
       }
       for(int i = 0; i < this.autoTransferStopIds.length; i++)
       {
           if(this.autoTransferStopIds[i] == null)
           {
      			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01903, METHOD_NAME);
           }
       }

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97902FF
    */
   public WEB3GenResponse createResponse()
   {
    return null;
   }
}
@
