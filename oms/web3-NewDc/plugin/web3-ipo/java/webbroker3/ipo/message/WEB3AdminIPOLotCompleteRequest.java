head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOLotCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当完了リクエスト(WEB3AdminIPOLotCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 鄭徳懇 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO抽選割当完了リクエスト)<BR>
 *  管理者IPO抽選割当完了リクエストクラス。<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminIPOLotCompleteRequest extends WEB3IPOLotCommonRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIPOLotCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_lotComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512192100L;
    
    /**
     * (暗証番号)<BR>
     * 管理者暗証番号。
     */
    public String password;
    
    /**
     * 抽選シーケンス
     */
    public String lotSequence;
    
    /**
     * @@roseuid 4112DAD60041
     */
    public WEB3AdminIPOLotCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@スーパークラスのvalidate()をコールする。 <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()をコールする。
        super.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60055
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOLotCompleteResponse(this);
    }
}
@
