head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報通知一件サービス(WEB3AdminEquityAttentionInfoNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 張少傑(中訊) モデルNo.219
Revision History : 2009/01/21 孟亞南(中訊) モデルNo.232
Revision History : 2009/02/12 孟亞南(中訊) 仕様変更モデルNo.236
*/

package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;

/**
 * (注意情報通知一件サービス)<BR>
 * 注意情報通知一件サービスインターフェイス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public interface WEB3AdminEquityAttentionInfoNotifyUnitService extends Service
{
    /**
     * (notify制限値幅情報)<BR>
     * 注意情報（制限値幅情報）処理を行なう。<BR>
     * @@param l_hostAttentionInfoNotifyParams - (注意情報通知キューテーブル)<BR>
     * 注意情報通知キューテーブル<BR>
     * @@param l_eqtypeTradedProductRow - (株式取引銘柄)<BR>
     * 株式取引銘柄オブジェクト<BR>
     * @@param l_eqtypeTradedProductUpdqRow - (株式取引銘柄updq)<BR>
     * 株式取引銘柄updq<BR>
     * @@param l_productRow - (銘柄)<BR>
     * 銘柄<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4938727A00D4
     */
    public String notifyLimitRangeInfo(
        HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams,
        EqtypeTradedProductRow l_eqtypeTradedProductRow,
        EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow,
        ProductRow l_productRow) throws WEB3BaseException;
}
@
