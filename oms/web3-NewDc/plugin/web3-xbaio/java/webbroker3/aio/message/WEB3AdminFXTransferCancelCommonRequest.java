head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferCancelCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替取消共通リクエスト(WEB3AdminFXTransferCancelCommonRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・FX振替取消共通リクエスト) <BR>
 * 管理者・FX振替取消共通リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferCancelCommonRequest extends WEB3GenRequest
{
    /**
     * (FX検索条件一覧) <BR>
     * FX検索条件の一覧
     */
    public WEB3FXSearchConditionUnit[] fxSearchConditionList;

    /**
     * @@roseuid 41E7901F037A
     */
    public WEB3AdminFXTransferCancelCommonRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferCancelCommonRequest.class);
        
    /**
     * (validate) <BR>
     * リクエストデータのチェックを行う。 <BR>
     * <BR>
     * １）FX検索条件一覧 <BR>
     * <BR>
     * １－１） <BR>
     * this.FX検索条件一覧 == null or <BR>
     * this.FX検索条件一覧.length() == 0 <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01774 <BR>
     * <BR>
     * １－２）FX検索条件一覧の各要素（FX検索条件）について <BR>
     * <BR>
     * FX検索条件.validate()メソッドをコールする。
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）FX検索条件一覧 
        // １－１） 
        //   this.FX検索条件一覧 == null or 
        //   this.FX検索条件一覧.length() == 0 
        //   の場合、例外をスローする。 
        if (this.fxSearchConditionList == null 
            || this.fxSearchConditionList.length == 0)
        {
            log.debug("FX検索条件一覧が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX検索条件一覧が未指定です。");
        }
        
        //  １－２）FX検索条件一覧の各要素（FX検索条件）について 
        //   FX検索条件.validate()メソッドをコールする。 
        for (int i = 0; i < this.fxSearchConditionList.length; i++)
        {
            WEB3FXSearchConditionUnit l_fxSearchConditionUnit = 
                this.fxSearchConditionList[i];
            l_fxSearchConditionUnit.validate();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX振替取消共通レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E790200000
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferCancelCommonResponse(this);
    }
}@
