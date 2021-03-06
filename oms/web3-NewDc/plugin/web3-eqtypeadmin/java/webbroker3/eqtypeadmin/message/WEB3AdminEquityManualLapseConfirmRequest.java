head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualLapseConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式手動失効確認リクエスト(WEB3AdminEquityManualLapseConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 新規作成
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・株式手動失効確認リクエスト)<BR>
 * 管理者・株式手動失効確認リクエストクラス<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */

public class WEB3AdminEquityManualLapseConfirmRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityManualLapseConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminEquity_manualLapseConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605291315L;
    
    /**
     * (失効対象注文条件)
     */
    public WEB3AdminEquityLapseTargetOrderCondition equityLapseTargetOrderCondition;
    
    /**
     * @@roseuid 447AB8F300DA
     */
    public WEB3AdminEquityManualLapseConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@失効対象注文条件のチェック<BR>
     * 　@１−１）　@this.失効対象注文条件 == nullの場合、<BR>
     * 　@　@「失効対象注文条件が未入力」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02420<BR>
     * <BR>
     * 　@１−２）　@this.失効対象注文条件.validate()をコールする。<BR>
     *<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 4469235C0167
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@失効対象注文条件のチェック 
        //　@１−１）　@this.失効対象注文条件 == nullの場合、 
        //　@　@「失効対象注文条件が未入力」の例外をスローする。 
        if (this.equityLapseTargetOrderCondition == null) 
        {
            log.debug("失効対象注文条件が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "失効対象注文条件が未入力です。");
        }
        
        //　@１−２）　@this.失効対象注文条件.validate()をコールする。
        this.equityLapseTargetOrderCondition.validate();
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
        return new WEB3AdminEquityManualLapseConfirmResponse(this);
    }
}
@
