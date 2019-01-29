head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更入力ﾘｸｴｽﾄ(WEB3AdminAccInfoCampaignIndiviInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
Revision History : 2007/2/1  モデルNo.165
Revision History : 2007/2/2  モデルNo.175
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更入力ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料割引キャンペーン個別顧客指定変更入力リクエスト<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviInputRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignIndiviInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312039L;
    
    /**
     * (手数料割引ｷｬﾝﾍﾟｰﾝ条件ID)<BR>
     * 手数料割引ｷｬﾝﾍﾟｰﾝ条件ID<BR>
     */
    public String campaignId;
    
    /**
     * (更新処理フラグ)<BR>
     * 更新処理フラグ<BR>
     * <BR>
     * 0：登録処理<BR>
     * 1：更新処理<BR>
     * 2：削除処理<BR>
     */
    public String updateFlag;
    
    /**
     * @@roseuid 45C087610247
     */
    public WEB3AdminAccInfoCampaignIndiviInputRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignIndiviInputResponse(this);
    }
    
    /**
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １） 更新処理フラグのチェック<BR>
     *   １-１） 更新処理フラグが '0' か '1' 以外の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02721<BR>
     * <BR>
     * ２） 更新フラグが 1（変更） の場合、以下の処理を行う。<BR>
     *   ２-１） 手数料割引キャンペーン条件IDのチェック<BR>
     *     ２-１-１） 手数料割引キャンペーン条件IDがnullの場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02716<BR>
     * <BR>
     * @@roseuid 45B5BE430370
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１） 更新処理フラグのチェック 
        //  １-１） 更新処理フラグが '0' か '1' 以外の場合、例外をスローする。
        if (this.updateFlag != null)
        {
            if (!(WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag)))
            {
                log.debug("更新処理フラグが'0' か '1'以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02721,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "更新処理フラグが'0' か '1'以外の値です。");     
            }
        }

        //２） 更新フラグが 1（変更） の場合、以下の処理を行う。 
        if (this.updateFlag != null)
        {
            if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag))
            {
                //  ２-１） 手数料割引キャンペーン条件IDのチェック 
                //    ２-１-１） 手数料割引キャンペーン条件IDがnullの場合、例外をスローする。
                if (this.campaignId == null)
                {
                    log.debug("手数料割引キャンペーン条件IDが未指定です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02716,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "手数料割引キャンペーン条件IDが未指定です。");     
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
