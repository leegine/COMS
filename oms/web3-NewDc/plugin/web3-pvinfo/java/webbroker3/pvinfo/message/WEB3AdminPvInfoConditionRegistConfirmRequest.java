head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・表示設定登録確認リクエスト(WEB3AdminPvInfoConditionRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/26 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・表示設定登録確認リクエスト)<BR>
 * 管理者・表示設定登録確認リクエストクラス
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionRegistConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionRegistConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_conditionRegistConfirmRequest";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (表示内容情報)<BR>
     */
    public WEB3PvInfoDisplayContentsUnit displayContentsUnit;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）入力内容のチェック<BR>
     * 　@１－１）this.表示内容情報.validate()をコールする。<BR>
     * @@roseuid 415C024901D0
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        String STR_METHOD_NAME = " validate()";        
        log.entering(STR_METHOD_NAME);
        
        //１）入力内容のチェック
        this.displayContentsUnit.validate();
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 417327BF0186
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPvInfoConditionRegistConfirmResponse(this);
    }
}
@
