head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  管理者・キューテーブル検索入力リクエストクラス(WEB3AdminDirSecHostTableSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  肖志偉(中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・キューテーブル検索入力リクエスト)<BR>
 * 管理者・キューテーブル検索入力リクエストクラス。
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableSearchInputRequest extends WEB3AdminDirSecHostTableUpdateCommonRequest 
{    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminDirSecHostTableSearchInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_search_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * @@roseuid 442A1C860251
     */
    public WEB3AdminDirSecHostTableSearchInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）スーパークラスのvalidate()をコールする。 <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 441656DA0223
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
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecHostTableSearchInputResponse(this);
    }

}
@
