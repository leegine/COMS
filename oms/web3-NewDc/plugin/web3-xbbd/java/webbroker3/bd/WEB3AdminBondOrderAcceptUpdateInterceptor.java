head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderAcceptUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文受付更新インタセプタ(WEB3AdminBondOrderAcceptUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (債券管理者注文受付更新インタセプタ)<BR>
 * 債券管理者注文受付更新インタセプタ<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondOrderAcceptUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    
    /**
     * (債券管理者注文受付更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44D84FAD015E
     */
    public WEB3AdminBondOrderAcceptUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装）<BR> 
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * １）拡張項目セット<BR>
     * 　@パラメータ.債券注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は 「債券注文受付_債券注文単位テーブルDB更新仕様.xls」参照。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D967A0038A
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderUnitParams l_params) 
    {
        return l_params;
    }
}
@
