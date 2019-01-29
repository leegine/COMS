head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.14.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferManagementUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替管理UnitServiceインターフェイス(WEB3AdminFXTransferManagementUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;

/**
 * (FX振替管理UnitService) <BR>
 * FX振替管理UnitServiceインターフェイス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public interface WEB3AdminFXTransferManagementUnitService extends Service
{
    
    /**
     * (submit取消) <BR>
     * DBに対して振替注文の取消処理を行う。
     * 
     * @@param l_gftTransferStatusParam - GFT振替状況Paramsオブジェクト
     * @@param l_administrator - 管理者オブジェクト
     * @@param l_strPassword - パスワード
     * @@throws WEB3BaseException
     * @@roseuid 41C68F0702E3
     */
    public void submitCancel(GftTransferStatusParams l_gftTransferStatusParam,
        WEB3Administrator l_administrator, String l_strPassword) throws WEB3BaseException;
}@
