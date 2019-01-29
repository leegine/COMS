head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.32.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXAccOpenConnection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設接続(WEB3FXAccOpenConnection.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/16 張騰宇 (中訊) 新規作成 仕様変更モデル1195
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (口座開設接続)<BR>
 * 口座開設接続インターフェイス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3FXAccOpenConnection extends Service
{
    /**
     * (do口座開設実行)<BR>
     * 口座開設実行を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「do口座開設実行」参照<BR>
     * @@param l_compFxConditionParams  - (会社別FXシステム条件Params)<BR>
     * 会社別FXシステム条件Params<BR>
     * @@param l_compFxConditionParams - (GFT依頼電文明細)<BR>
     * GFT依頼電文明細<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXGftResultNoticeTelegramUnit doAccountOpen(
        CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
        throws WEB3BaseException;

    /**
     * (updateGFT口座開設状況)<BR>
     * 受信結果をGFT口座開設状況テーブルのデータに反映する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strResultCode - (受付結果コード)<BR>
     * 受付結果コード<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTAccountOpenStatus(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
        throws WEB3BaseException;
}
@
