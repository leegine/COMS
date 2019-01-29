head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効確認リクエスト(WEB3AdminToManualLapseConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・手動失効確認リクエスト)<BR>
 * トリガー注文管理者・手動失効確認リクエスト<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToManualLapseConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToManualLapseConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;
    
    /**
     * (失効対象注文条件)<BR>
     */
    public WEB3AdminToLapseTargetOrderCondition lapseTargetOrderCondition;
    
    /**
     * @@roseuid 44192EED007D
     */
    public WEB3AdminToManualLapseConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@失効対象注文条件のチェック<BR>
     * 　@１－１）　@this.失効対象注文条件 == nullの場合、<BR>
     * 　@　@「失効対象注文条件が未入力」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02420<BR>
     * <BR>
     * 　@１－２）　@this.失効対象注文条件.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44051D140399
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@失効対象注文条件のチェック
        //　@１－１）　@this.失効対象注文条件 == nullの場合、
        //　@　@「失効対象注文条件が未入力」の例外をスローする。
        if (this.lapseTargetOrderCondition == null)
        {
            log.debug("失効対象注文条件が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "失効対象注文条件が未入力です。");
        }
        
        //１－２）　@this.失効対象注文条件.validate()をコールする。
        this.lapseTargetOrderCondition.validate();

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * トリガー注文管理者・手動失効確認レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToManualLapseConfirmResponse(this);
    }
}
@
