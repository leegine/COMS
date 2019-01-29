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
filename	WEB3MutualFundAcceptConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託受付確定インタセプタクラス(WEB3MutualFundAcceptConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 韋念瓊 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

/**
 * 投資信託受付確定インタセプタクラス<BR>
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundAcceptConfirmInterceptor 
        extends WEB3DefaultMutualFundOrderConfirmInterceptor 
{
    
    /**
     * 注文エラー理由コード<BR>
     */
    private String orderErrorReasonCode;
    
    /**
     * (投信受付確定インタセプタ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40AAF523005D
     */
    public WEB3MutualFundAcceptConfirmInterceptor() 
    {
     
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文履歴データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * １） this.注文エラー理由コードを、引数.投信注文履歴Params.注文エラー理由コードに設定する。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderActionParams - (投信注文履歴Params)<BR>
     * 永続化前の投信注文履歴Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF52203E7
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        MutualFundOrderActionParams l_mutualFundOrderActionParams) 
    {
        l_mutualFundOrderActionParams.setErrorReasonCode(this.orderErrorReasonCode);
        return l_mutualFundOrderActionParams;
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文単位データの作成・更新時に呼ばれる。<BR>
     * <BR>
     * １） this.注文エラー理由コードを、引数.投信注文単位Params.注文エラー理由コードに設定する。<BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderUnitParams - (投信注文単位Params)<BR>
     * 永続化前の投信注文単位Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AAF523001E
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext, 
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams) 
    {
        l_mutualFundOrderUnitParams.setErrorReasonCode(this.orderErrorReasonCode);
        return l_mutualFundOrderUnitParams;
    }
    
    /**
     * (set注文エラー理由コード)<BR>
     * 注文エラー理由コードの設定を行う。<BR>
     * @@param l_strErrorCode - エラーコード
     * @@roseuid 40AAF523003D
     */
    public void setOrderErrorReasonCode(String l_strErrorCode) 
    {
        this.orderErrorReasonCode = l_strErrorCode;
    }
    
    /**
     * (get注文エラー理由コード)<BR>
     * this.注文エラー理由コードを返す。<BR>
     * @@return String
     * @@roseuid 40AAF523004D
     */
    public String getOrderErrorReasonCode() 
    {
        return this.orderErrorReasonCode;
    }
}
@
