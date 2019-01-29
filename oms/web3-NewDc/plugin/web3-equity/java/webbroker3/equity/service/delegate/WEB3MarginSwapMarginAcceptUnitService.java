head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡受付一件サービス(WEB3MarginSwapMarginAcceptUnitService.java)
Author Name      : 2004/10/8 盧法@旭(中訊) 新規作成
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeSwapAcceptParams;

/**
 * （信用取引現引現渡受付一件サービス）。<BR>
 * <BR>
 * 信用取引現引現渡受付一件サービスインタフェース<BR>
 *（トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 法@旭
 * @@version 1.0
 */
public interface WEB3MarginSwapMarginAcceptUnitService extends Service  
{
    
    /**
     * (notify現引現渡受付)<BR>
     * 【現引現渡受付キューテーブル】キューデータ一件に対する処理を行う。
     * @@param l_swapMarginAcceptQueParams - (現引現渡受付キューParams)<BR>
     * @@throws WEB3BaseException
     * @@return void
     * 株式注文受付キューParams。
     * @@roseuid 4101101701D7
     */
    public void notifySwapMarginAccept(HostEqtypeSwapAcceptParams hostEqtypeSwapAcceptParams) throws WEB3BaseException;
}
@
