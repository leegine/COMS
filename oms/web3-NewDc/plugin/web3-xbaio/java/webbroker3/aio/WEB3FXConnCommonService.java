head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXConnCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 接続共通(WEB3FXConnCommonService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 柴双紅 (中訊) 新規作成 モデル1173
Revision History : 2009/08/14 柴双紅 (中訊) モデル1190
Revision History : 2009/09/16 張騰宇 (中訊) モデル1204 1205
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;

/**
 * (接続共通)<BR>
 * 接続共通インタフェース<BR>
 *
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3FXConnCommonService extends Service
{
    /**
     * (send外部接続依頼メッセージ)<BR>
     * 外部接続依頼メッセージの送付を行う。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_compFxConditionParams - (会社別FXシステム条件)<BR>
     * 会社別FXシステム条件<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分<BR>
     * @@return WEB3ExtConnection
     * @@throws WEB3BaseException
     */
    public WEB3ExtConnection sendExtConnAskingMessage(
        CompFxConditionParams l_compFxConditionParams,
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit) throws WEB3BaseException;

    /**
     * (SOAP接続用プロキシ)<BR>
     * SOAP接続用プロキシ設定を行う。<BR>
     * <BR>
     * @@param l_rpcParams - (外部システムSOAPプリファ@レンス（RPC形式）)<BR>
     * 外部システムSOAPプリファ@レンス（RPC形式）<BR>
     * @@throws WEB3BaseException
     */
    public void setSOAPConnectionProxy(SoapConnectPrefRpcParams l_rpcParams)
        throws WEB3BaseException;
    
    /**
     * (createGFT結果通知電文明細)<BR>
     * GFT結果通知電文明細を作成する。 <BR>
     * @@param l_fXGftAskingTelegramUnit - (GFT依頼電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@param l_fXAccInformationUnits - (FX口座情報一 覧)<BR>
     * FX口座情報一覧<BR>
     * @@param l_strResultCode - (受付結果コード)<BR>
     * 受付結果コード<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     */
    public WEB3FXGftResultNoticeTelegramUnit createGftResultNoticeTelegramUnit(
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit,
        WEB3FXAccInformationUnit[] l_fXAccInformationUnits,
        String l_strResultCode);
}@
