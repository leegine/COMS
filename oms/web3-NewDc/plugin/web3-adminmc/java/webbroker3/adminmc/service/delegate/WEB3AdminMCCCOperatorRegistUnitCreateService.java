head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.33.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnitCreateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : CCオペレータ登録情報作成サービス(WEB3AdminMCCCOperatorRegistUnitCreateService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeTrader;

/**
 * (CCオペレータ登録情報作成サービス)<BR>
 * CCオペレータ登録情報作成サービスインタフェイス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public interface WEB3AdminMCCCOperatorRegistUnitCreateService extends Service 
{
    /**
     * (createCCオペレータ登録情報)<BR>
     * 扱者オブジェクトより、CCオペレータ登録情報メッセージデータオブジェクトを作成する。<BR>
     * <BR>
     * <BR>
     * @@param l_trader - (扱者)<BR>
     * 管理者タイプ<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit
     * @@roseuid 417F713B00CF
     */
    public WEB3AdminMCCCOperatorRegistUnit createCCOperatorRegistUnit(WEB3GentradeTrader l_trader) throws WEB3BaseException;
    
    /**
     * (createCCオペレータ登録情報)<BR>
     * 扱者オブジェクトの配列より、CCオペレータ登録情報メッセージデータオブジェクトの配列を作成する。<BR>
     * <BR>
     * <BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクトの配列<BR>
     * 
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit[]
     * @@roseuid 417F713B00EE
     */
    public WEB3AdminMCCCOperatorRegistUnit[] createCCOperatorRegistUnit(WEB3GentradeTrader[] l_trader) throws WEB3BaseException;
}
@
