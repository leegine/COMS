head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・表示設定登録完了リクエスト(WEB3AdminPvInfoConditionRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/26 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・表示設定登録完了リクエスト)<BR>
 * 管理者・表示設定登録完了リクエストクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionRegistCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionRegistCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionRegistComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (暗証番号)<BR>
     */
    public String password;
    
    /**
     * (表示内容情報)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit displayContentsUnit;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）入力内容のチェック<BR>
     * 　@１－１）this.表示内容情報.表示内容ID == nullの場合、<BR>
     * 　@　@　@　@　@「表示内容IDがnull」の例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_01040<BR>
     * <BR>
     * 　@１－２）this.表示内容情報.validate()をコールする。<BR>
     * @@roseuid 415C10290339
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        String STR_METHOD_NAME = " validate()";        
        log.entering(STR_METHOD_NAME);
               
        //１）入力内容のチェック
        //１－１）this.表示内容情報.表示内容ID == nullの場合、「表示内容IDがnull」の例外をスローする。
        if (this.displayContentsUnit.displayContentsId == null)
        {
            log.error("表示内容IDがnull");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01040,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //１－２）this.表示内容情報.validate()をコールする。
        this.displayContentsUnit.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 417327BF02AF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPvInfoConditionRegistCompleteResponse(this);
    }
}
@
