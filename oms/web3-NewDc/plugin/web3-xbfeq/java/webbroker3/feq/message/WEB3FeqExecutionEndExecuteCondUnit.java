head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionEndExecuteCondUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来終了実施条件(WEB3FeqExecutionEndExecuteCondUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (外国株式出来終了実施条件)<BR>
 * 外国株式出来終了実施条件クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqExecutionEndExecuteCondUnit extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExecutionEndExecuteCondUnit.class);
        
    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (発注日)<BR>
     * 画面にて入力された発注日
     */
    public Date orderBizDate;
    
    /**
     * (外国株式出来終了実施条件)<BR>
     * コンストラクタ
     * @@roseuid 420208EC01F3
     */
    public WEB3FeqExecutionEndExecuteCondUnit() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@市場コードチェック<BR>
     * 　@this.市場コード == nullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02045<BR>
     * <BR>
     * ２）　@発注日チェック<BR>
     * 　@２－１）　@this.発注日 == nullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00406<BR>
     * 　@２－２）　@this.発注日が営業日でない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02019<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF022017D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@市場コードチェック
        //this.市場コード == nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02045,
                this.getClass().getName() + STR_METHOD_NAME,
                " 市場コードが未指定です。"); 
        }
        
        //２）　@発注日チェック
        //２－１）　@this.発注日 == nullの場合、例外をスローする。
        if (this.orderBizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + STR_METHOD_NAME,
                " 発注日が未指定です。"); 
        }

        //２－２）　@this.発注日が営業日でない場合、例外をスローする。
        //WEB3-XBFEQ-A-CD-0028
        String l_strFlag1 = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(this.orderBizDate.getTime()));
        String l_strFlag2 = WEB3GentradeTradingTimeManagement.getFeqBizDateType(
            new Timestamp(this.orderBizDate.getTime()));

        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag1) || 
            WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag2))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                " 発注日は営業日ではありません。" + this.orderBizDate); 
        }
        
        log.exiting(STR_METHOD_NAME);  
    }
}
@
