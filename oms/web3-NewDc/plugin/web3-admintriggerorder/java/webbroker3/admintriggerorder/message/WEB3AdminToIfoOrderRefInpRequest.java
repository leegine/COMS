head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderRefInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会入力リクエスト(WEB3AdminToIfoOrderRefInpRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16　@余新敏(中訊) 新規作成
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
 * (トリガー注文管理者・先物OP注文照会入力リクエスト)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefInpRequest extends WEB3GenRequest
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToIfoOrderRefInpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_ifo_order_ref_inp";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
        
    /**
     * (部店コード)<BR>
     * 部店コードの配列 <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している <BR>
     * 　@取扱可能部店コード一覧がセットされる。<BR>
     */
    public String[] branchCode;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F1B3C700AB
     */
    public WEB3AdminToIfoOrderRefInpRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）部店コードチェック <BR>
     * 　@１－１）this.部店コード == nullの場合、 <BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。 <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02174<BR>
     * <BR>
     * 　@１－２）this.部店コード.length == 0の場合、 <BR>
     * 　@　@　@　@　@「部店コードの要素数が0」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_02175<BR>
     * 　@１－３）this.部店コードの要素数分以下の処理を行う。  <BR>
     * 　@　@１－３－１）this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・部店コード≠数字<BR>
     * 　@　@　@　@　@・部店コード.length≠3<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_00779<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43DF0A65013E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コードチェック 
        //　@１－１）this.部店コード == nullの場合、 
        //　@　@　@「部店コードがnull」の例外をスローする。 
        if (this.branchCode == null)
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        
        //　@１－２）this.部店コード.length == 0の場合、  
        //　@　@　@「部店コードの要素数が0」の例外をスローする。
        if (this.branchCode.length == 0)
        {
            log.debug("部店コードの要素数が0です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が0です。");
        }

        //　@１－３）this.部店コードの要素数分以下の処理を行う。
        //　@　@　@１－３－１）this.部店コードが以下の条件に該当する場合、
        //　@　@　@　@　@「部店コードエラー」の例外をスローする。
        //　@　@　@　@　@・部店コード≠数字
        //　@　@　@　@　@・部店コード.length≠3
        int l_intLen = this.branchCode.length;
        for (int i = 0; i < l_intLen; i++)
        {
            if (!WEB3StringTypeUtility.isInteger(this.branchCode[i])
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
     * トリガー注文管理者・先物OP注文照会入力レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToIfoOrderRefInpResponse(this);
    }
}
@
