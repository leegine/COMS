head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  (信用取引注文通知一件サービス)<BR>
                 :  信用取引注文通知一件サービスインタフェース
                 :  (WEB3MarginOrderNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李松峰 (中訊) 新規作成
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;


/**
 * （信用取引注文通知一件サービス）。<BR>
 * <BR>
 * 信用取引注文通知一件サービスインタフェース<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3MarginOrderNotifyUnitService extends Service 
{
    
    /**
     * (notify新規建注文)<BR>
     * 新規建注文通知処理を実施する。
     * @@param l_marginOrderInputNotifyAdapter - (信用取引注文入力通知データアダプタ)<BR>
     * 信用取引注文入力通知データアダプタオブジェクト
     * @@roseuid 40EA5AA003CF
     */
    public void notifyOpenMarginOrder(
        WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)
        throws WEB3BaseException;
    
    /**
     * (notify返済注文)<BR>
     * 返済注文通知処理を実施する。
     * @@param l_marginOrderInputNotifyAdapter - 信用取引注文入力通知データアダプタオブジェクト
     * @@roseuid 40EA5AB1012F
     */
    public void notifyCloseMarginOrder(
        WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter)
        throws WEB3BaseException;
}
@
