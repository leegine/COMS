head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来終了共通リクエスト(WEB3AdminFeqExecutionEndCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者外国株式出来終了共通リクエスト)<BR>
 * 管理者外国株式出来終了共通リクエストクラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionEndCommonRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndCommonRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionEndCommon";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;   
    
    /**
     * (出来終了実施一覧)<BR>
     * 外国株式出来終了実施条件の配列
     */
    public WEB3FeqExecutionEndExecuteCondUnit[] executionEndExecuteCondList;
    
    /**
     * @@roseuid 42CE39FE0213
     */
    public WEB3AdminFeqExecutionEndCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@this.出来終了実施一覧 == nullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02031<BR>
     * <BR>
     * ２）　@this.出来終了実施一覧の要素数分、以下の処理を実施する。<BR>
     * 　@２－１）処理対象の要素.validate()をコールする。
     * @@throws WEB3BaseException
     * @@roseuid 42AFEFD002A7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@this.出来終了実施一覧 == nullの場合、例外をスローする。
        if (this.executionEndExecuteCondList == null || 
            this.executionEndExecuteCondList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02031,
                this.getClass().getName() + STR_METHOD_NAME,
                " 出来終了実施一覧が存在しません。"); 
        }
        
        //２）　@this.出来終了実施一覧の要素数分、以下の処理を実施する。
        //２－１）処理対象の要素.validate()をコールする。
        int l_intCnt = this.executionEndExecuteCondList.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3FeqExecutionEndExecuteCondUnit l_unit = executionEndExecuteCondList[i];
            if (l_unit != null)
            {
                l_unit.validate();
            }
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
        return null;
    }
}
@
