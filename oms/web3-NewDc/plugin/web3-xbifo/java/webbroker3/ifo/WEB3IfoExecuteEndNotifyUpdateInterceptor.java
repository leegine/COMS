head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知更新インタセプタクラス(WEB3IfoExecuteEndNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/2/27 寺田 (SRA) 新規作成
				 : 2006/7/19 李　@俊 (中訊) 仕様変更　@モデル525
                 : 2006/8/10 肖志偉 (中訊) 仕様変更  モデル543
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP出来終了通知更新インタセプタ)<BR>
 * 先物OP出来終了通知更新インタセプタクラス<BR>
 * @@author  寺田
 * @@version 1.0
 */
public class WEB3IfoExecuteEndNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteEndNotifyUpdateInterceptor.class);

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR> 
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@super.mutate(IfoOrderActionParams)をコールする。<BR>
     * <BR>
     * ２）　@拡張項目セット<BR>
     * 　@○注文エラー理由コード<BR>
     * 　@　@パラメータ.注文履歴Params.注文エラー理由コードに”0000:正常”をセットする。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * (OrderManagerPersistenceTypeにて定数定義)<BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * (OrderManagerPersistenceContextにて定数定義)<BR>
     * @@param l_ifoOrderActionParams - (注文履歴Params)<BR>
     * 注文履歴が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderActionParams
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderActionParams l_ifoOrderActionParams) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "IfoOrderActionParams l_ifoOrderActionParams)";

        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderActionParams == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME));
            return l_ifoOrderActionParams;
        }

        // super.mutate(IfoOrderActionParams)をコールする。 
        super.mutate(l_orderManagerPersistenceType,
            l_orderManagerPersistenceContext, l_ifoOrderActionParams);
        
        //注文エラー理由コード
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }

    /**
     * (先物OP出来終了通知更新インタセプタクラス)<BR>
     * <BR>
     * コンストラクタ<BR>
     * @@return webbroker3.ifo.WEB3IfoExecuteEndNotifyUpdateInterceptor
     */
    public WEB3IfoExecuteEndNotifyUpdateInterceptor() 
    {

    }
}@
