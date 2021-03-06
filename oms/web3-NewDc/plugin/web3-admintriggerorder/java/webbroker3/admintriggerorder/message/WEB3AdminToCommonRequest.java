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
filename	WEB3AdminToCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者共通リクエスト(WEB3AdminToCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 張　@芳(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者共通リクエスト)<BR>
 * トリガー注文管理者共通リクエストクラス<BR>
 * <BR>
 * ※部店権限のチェックを行う必要がある場合などは、<BR>
 * 　@このリクエストクラスを継承する。<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToCommonRequest.class);
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列 <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している <BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * @@roseuid 4430D2BF03C8
     */
    public WEB3AdminToCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）部店コードチェック <BR>
     * 　@１−１）　@this.部店コード == nullの場合、 <BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@１−２）　@this.部店コードの要素数分以下の処理を行う。 <BR>
     * 　@　@１−２−１）　@this.部店コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@　@　@　@　@「部店コードエラー」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@　@・部店コード != 数字 <BR>
     * 　@　@　@　@　@　@　@・部店コード.length != 3 <BR>
     *          class : WEB3BusinessLayerException<BR>
     *          tag : BUSINESS_ERROR_00779<BR>
     * @@throws WEB3BaseException
     * @@roseuid 440E78630017
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）部店コードチェック 
        // 　@１−１）　@this.部店コード == nullの場合、「部店コードがnull」の例外をスローする。 
        //          class : WEB3BusinessLayerException
        //         tag : BUSINESS_ERROR_02174
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        
        // １−２）　@this.部店コードの要素数分以下の処理を行う。 
        // 　@　@１−２−１）　@this.部店コードが以下の条件に該当する場合、「部店コードエラー」の例外をスローする。 
        // 　@　@　@　@　@　@　@・部店コード != 数字 
        // 　@　@　@　@　@　@　@・部店コード.length != 3 
        //          class : WEB3BusinessLayerException
        //          tag : BUSINESS_ERROR_00779
        int l_intLen = this.branchCode.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("部店コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
            }
        }

        log.exiting(STR_METHOD_NAME);     
    }
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * nullを返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }   
}
@
