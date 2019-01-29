head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExchangeRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式為替登録完了リクエスト(WEB3AdminFeqExchangeRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者外国株式為替登録完了リクエスト)<BR>
 * 管理者外国株式為替登録完了リクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExchangeRegistCompleteRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExchangeRegistCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_exchangeRegistComplete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;   
    
    /**
     * (為替情報一覧)<BR>
     * 外国株式為替情報の配列<BR>
     * <BR>
     * ※入力があった通貨・レートについての情報のみがセットされる。
     */
    public WEB3FeqExchangeUnit[] exchangeList;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 42CE3A00000F
     */
    public WEB3AdminFeqExchangeRegistCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@為替情報一覧[] == null or 為替情報一覧[].length == 0の場合、例外をスローする。<BR>
     * 　@    class: WEB3BusinessLayerException<BR>
     * 　@    tag:   BUSINESS_ERROR_02195<BR>
     * <BR>
     * ２）　@為替情報一覧[]の各要素毎に、為替情報一覧.validate()をコールする。<BR>
     * 為替情報一覧.validate()をコールする。
     * @@throws WEB3BaseException
     * @@roseuid 42BA5DEC001E
     */
    public void validate() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@為替情報一覧[] == null or 為替情報一覧[].length == 0の場合、例外をスローする。
        if (this.exchangeList == null || this.exchangeList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02195,
                getClass().getName() + STR_METHOD_NAME,
                "為替情報一覧[] == null or 為替情報一覧[].length == 0");
        }
        
        int l_intCnt = this.exchangeList.length;
        
        for (int i = 0; i < l_intCnt; i++) 
        {
            //２）　@為替情報一覧[]の各要素毎に、為替情報一覧.validate()をコールする。
            this.exchangeList[i].validate();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqExchangeRegistCompleteResponse(this);
    }
}
@
