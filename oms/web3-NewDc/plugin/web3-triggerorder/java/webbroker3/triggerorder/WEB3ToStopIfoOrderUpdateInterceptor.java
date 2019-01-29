head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopIfoOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文先物OP発注更新インタセプタクラス(WEB3ToStopIfoOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/2/17 寺田 (SRA) 新規作成
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.WEB3IfoUpdateInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * (逆指値注文先物OP発注更新インタセプタ)<BR>
 * 逆指値注文先物OP発注更新インタセプタクラス<BR>
 * @@author  寺田
 * @@version 1.0
 */
public class WEB3ToStopIfoOrderUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3ToStopIfoOrderUpdateInterceptor.class);

    /**
     * 注文エラー理由コード
     */
    private String l_errorReasonCode;

    /**
     * (逆指値注文先物OP発注更新インタセプタ)<BR>
     * <BR>
     * コンストラクタ<BR>
     * インスタンスを生成し、引数を自身のプロパティにセットする。<BR>
     * @@param l_errorReasonCode - (注文エラー理由コード)<BR>
     * 注文単位テーブル仕様.xls<BR> 
     * 「（注文単位テーブル補足）注文エラー理由コード」シート参照。<BR> 
     * @@return webbroker3.triggerorder.WEB3ToStopIfoOrderUpdateInterceptor
     */
    public WEB3ToStopIfoOrderUpdateInterceptor(String l_errorReasonCode) 
    {
        this.l_errorReasonCode = l_errorReasonCode;
    }

    /**
     * (更新値設定)<BR>
     * <BR>
     * （mutateメソッドの実装）<BR>
     * 引数の注文単位Paramsに拡張項目(*)を設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * １） 拡張項目セット<BR>
     * 更新内容は、「逆指値注文発注(NG)_先物OP注文単位テーブル.xls」参照。<BR>
     * @@param l_orderManagerPersistenceType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * （OrderManagerPersistenceTypeにて定数定義）<BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_ifoOrderUnitParams - (注文単位行オブジェクト)<BR>
     * 先物OP注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        IfoOrderUnitParams l_ifoOrderUnitParams) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType, " +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "IfoOrderUnitParams l_ifoOrderUnitParams)";

        log.entering(STR_METHOD_NAME);
        if(l_ifoOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        //注文単位.リクエストタイプへ（8:発注失敗）を設定
        l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.ORDER_FAILURE);

        // 注文エラー理由コードを設定
        l_ifoOrderUnitParams.setErrorReasonCode(this.getErrorReasonCode());

        log.exiting(STR_METHOD_NAME);

        return l_ifoOrderUnitParams;
    }

    /**
     * (get注文エラー理由コード)<BR>
     * <BR>
     * this.注文エラー理由コードを返却する。<BR>
     * @@return String
     */
    private String getErrorReasonCode()
    {
        return this.l_errorReasonCode;
    }
}
@
