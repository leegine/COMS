head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountStateChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設状況変更共通リクエスト(WEB3AdminFEqConAccountStateChangeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株口座開設状況変更共通リクエスト)<BR>
 * 外株口座開設状況変更共通リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountStateChangeCommonRequest extends WEB3GenRequest 
{

    /**
     * (顧客ID)<BR>
     * 外国株式顧客ID
     */
    public String accountId;
    
    /**
     * (更新後口座開設状況区分)<BR>
     * 更新後口座開設状況区分<BR>
     * <BR>
     * 1： 開設済<BR>
     * 9： 抹消
     */
    public String updatedAccountOpenStatusDiv;
    
    /**
     * @@roseuid 423554FF0167
     */
    public WEB3AdminFEqConAccountStateChangeCommonRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountStateChangeCommonRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）顧客ID<BR>
     * <BR>
     *   this.顧客ID == nullの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01952<BR>
     * <BR>
     * ２）更新後口座開設状況区分<BR>
     * <BR>
     * 以下の値以外の場合、例外をスローする。<BR>
     * <BR>
     *   ”開設済”<BR>
     *   ”抹消”
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01771<BR>
     * <BR>
     * @@roseuid 41E61233000E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）顧客ID
        //this.顧客ID == null
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.accountId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客IDが未指定です。");

        }
        
        //２）更新後口座開設状況区分
        //以下の値以外の場合、例外をスローする。
        //”開設済” 
        //”抹消” 
        if(!(WEB3AioAccountDivDef.OPEN_COMPLETE.equals(this.updatedAccountOpenStatusDiv)
            || WEB3AioAccountDivDef.DELETE.equals(this.updatedAccountOpenStatusDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01771,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "更新後口座開設状況区分[" + this.updatedAccountOpenStatusDiv + "]。");
            
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 423554FF01B5
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
