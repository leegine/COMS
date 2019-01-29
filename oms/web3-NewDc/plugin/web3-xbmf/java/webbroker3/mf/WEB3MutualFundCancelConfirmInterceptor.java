head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundCancelConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消確定インタセプタクラス(WEB3MutualFundCancelConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 周勇 (中訊) 新規作成
                   2004/08/20 黄建 (中訊) レビュー
*/
package webbroker3.mf;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託取消確定インタセプタクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundCancelConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundCancelConfirmInterceptor.class); 
                 
    /**
     * (投信取消確定インタセプタ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40AAF3F50241ss
     */
    public WEB3MutualFundCancelConfirmInterceptor()
    {
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateのオーバーライド）<BR>
     * <BR>
     * 引数で与えられた投信注文履歴Paramsに値を設定し、投信注文履歴Paramsを返す。<BR>
     * <BR>
     * １）　@注文エラー理由コードの設定を行う。<BR>
     * 　@－投信注文履歴Params.set注文エラー理由コード()をコールし、注文エラー理由コードの設定を行う。<BR>
     * 　@　@［set注文エラー理由コードに渡すパラメタ］<BR>
     * 　@　@　@注文エラー理由コード： null<BR>
     * <BR>
     * ２）　@引数で与えられた投信注文履歴Paramsを返す。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など<BR>
     * @@param l_mutualFundOrderActionParams - (投信注文履歴Params)<BR>
     * 永続化前の投信注文履歴Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF3F50231
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {

        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderActionParams l_mutualFundOrderActionParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderActionParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当パラメータはNullである");
        }        
        
        //１）　@注文エラー理由コードの設定を行う
        l_mutualFundOrderActionParams.setErrorReasonCode(null);
        
        log.exiting(STR_METHOD_NAME);
        //２）　@引数で与えられた投信注文履歴Paramsを返す
        return l_mutualFundOrderActionParams;
    }
}
@
