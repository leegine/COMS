head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualLapseConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効確認リクエスト (WEB3AdminIfoManualLapseConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効確認リクエスト )<BR>
 * 管理者・先物OP手動失効確認リクエスト クラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */

public class WEB3AdminIfoManualLapseConfirmRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualLapseConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminIfo_manualLapseConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200701311315L;
    
    /**
     * (失効対象注文条件)
     */
    public WEB3AdminIfoLapseTargetOrderCondition ifoLapseTargetOrderCondition;
    
    /**
     * @@roseuid 447AB8F300DA
     */
    public WEB3AdminIfoManualLapseConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@失効対象注文条件のチェック<BR>
     * 　@１－１）　@this.失効対象注文条件 == nullの場合、<BR>
     * 　@　@「失効対象注文条件が未入力」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02420<BR>
     * <BR>
     * 　@１－２）　@this.失効対象注文条件.validate()をコールする。<BR>
     *<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 4469235C0167
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@失効対象注文条件のチェック 
        //　@１－１）　@this.失効対象注文条件 == nullの場合、 
        //　@　@「失効対象注文条件が未入力」の例外をスローする。 
        if (this.ifoLapseTargetOrderCondition == null) 
        {
            log.debug("失効対象注文条件が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "失効対象注文条件が未入力です。");
        }
        
        //　@１－２）　@this.失効対象注文条件.validate()をコールする。
        this.ifoLapseTargetOrderCondition.validate();
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 管理者・株式手動失効確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6301A2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIfoManualLapseConfirmResponse(this);
    }
}
@
