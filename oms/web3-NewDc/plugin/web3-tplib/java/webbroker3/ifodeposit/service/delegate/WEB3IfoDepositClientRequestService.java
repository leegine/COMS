head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金クライアントリクエストサービスクラス(WEB3IfoDepositClientRequestService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.ifodeposit.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金クライアントリクエストサービス)<BR>
 * 証拠金クライアントリクエストサービス(証拠金用)。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public abstract class WEB3IfoDepositClientRequestService
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositClientRequestService.class);

    /**
     * @@roseuid 4186F5FA0302
     */
    public WEB3IfoDepositClientRequestService()
    {

    }

    /**
     * (get補助口座)<BR>
     * （getSubAccountのオーバーロード）<BR>
     * <BR>
     * １）ログインセキュリティサービスより補助口座を取得する。<BR>
     * <BR>
     * ２）顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@補助口座.getMainAccount( )<BR>
     * <BR>
     * ３）補助口座タイプの判定<BR>
     * <BR>
     * 　@取得した顧客オブジェクトの以下の項目により、口座開設区分を判定する。<BR>
     * <BR>
     * ・先物OP口座開設区分<BR>
     * 　@顧客.先物OP口座開設区分(東証） <BR>
     * 　@顧客.先物OP口座開設区分(大証） <BR>
     * 　@顧客.先物OP口座開設区分(名証） <BR>
     * <BR>
     * 　@○先物OP口座未開設の場合<BR>
     * 　@　@(先物OP口座開設区分の3項目が全て”DEFAULT(口座なし)”)<BR>
     * <BR>
     * 　@　@「先物OP口座未開設エラー」の例外をthrowする。<BR>
     * <BR>
     * 　@○証拠金口座開設済の場合<BR>
     * 　@　@(先物OP口座開設区分の3項目の何れかが”先物OP口座開設”、または、”先物口座開
     * 設”)<BR>
     * <BR>
     * 　@　@アカウントマネージャ.getSubAccount( 
     * )にて、該当顧客の先物オプション取引用補助口座オブジェクトを取得し、リターンする・
     * <BR>
     * <BR>
     * 　@　@　@[getSubAccount引数]<BR>
     * 　@　@　@accountId：　@補助口座.口座ID<BR>
     * 　@　@　@subAccountType：　@SubAccountTypeEnum.株式取引口座(先物証拠金) <BR>
     * <BR>
     * 　@○証拠金口座未開設の場合(上記以外)<BR>
     * <BR>
     * 　@　@nullをリターンする。<BR>
     * @@return webbroker3.gentrade.WEB3GentradeSubAccount
     * @@roseuid 41452DF1018C
     */
    public WEB3GentradeSubAccount getSubAccount()
    {
        // OpLoginSecurityServiceからAccountIdを取得
        OpLoginSecurityService l_loginService =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngAccountId = l_loginService.getAccountId();
        
        try
        {
            // 取得したAccountIdよりMainAccountを取得
            AccountManager l_accMgr = GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            if (!isFuturesOptionAccountOpen(l_mainAccount))
            {
                // 先物OP口座未開設の場合
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01294,
                    "getSubAccount()");

            } else if (isIfoDepositAccountOpen(l_mainAccount))
            {
                // 証拠金口座開設済の場合
                return (WEB3GentradeSubAccount) l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            } else
            {
                // 証拠金口座未開設（上記以外）の場合
                return null;
            }
        } catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "getSubAccount()",
                nfe);
        }
    }

    /**
     * is先物OP口座開設済<BR>
     * 先物OP口座開設区分（東証）、先物OP口座開設区分（大証）、先物OP口座開設区分（名証）の
     * いずれかが先物OP口座開設済の場合<code>true</code>、
     * それ以外の場合<code>false</code>を返す。<BR>
     * 
     * @@param l_mainAccount 顧客
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isFuturesOptionAccountOpen(MainAccount l_mainAccount)
    {
        MainAccountRow l_maRow =
            (MainAccountRow) l_mainAccount.getDataSourceObject();
        if (isFuturesOptionAccountOpen(l_maRow.getIfoAccOpenDivTokyo())
            || isFuturesOptionAccountOpen(l_maRow.getIfoAccOpenDivOsaka())
            || isFuturesOptionAccountOpen(l_maRow.getIfoAccOpenDivNagoya()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * is先物OP口座開設済<BR>
     * 先物OP口座開設区分が「DEFAULT（口座なし）」の場合<code>false</code>、
     * それ以外の場合<code>true</code>を返す。<BR>
     * 
     * @@param l_strIfoAccountOpenDiv　@先物OP口座開設区分
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isFuturesOptionAccountOpen(String l_strIfoAccountOpenDiv)
    {
        if (WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccountOpenDiv))
        {
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * is証拠金口座開設済<BR>
     * 先物OP口座開設区分（東証）、先物OP口座開設区分（大証）、先物OP口座開設区分（名証）の
     * いずれかが証拠金口座開設済の場合<code>true</code>、
     * それ以外の場合<code>false</code>を返す。<BR>
     * 
     * @@param l_mainAccount 顧客
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isIfoDepositAccountOpen(MainAccount l_mainAccount)
    {
        MainAccountRow l_maRow =
            (MainAccountRow) l_mainAccount.getDataSourceObject();
        if (isIfoDepositAccountOpen(l_maRow.getIfoAccOpenDivTokyo())
            || isIfoDepositAccountOpen(l_maRow.getIfoAccOpenDivOsaka())
            || isIfoDepositAccountOpen(l_maRow.getIfoAccOpenDivNagoya()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * is証拠金口座開設済<BR>
     * 先物OP口座開設区分が「先物OP口座開設」または「先物口座開設」の場合<code>true</code>、
     * それ以外の場合<code>false</code>を返す。<BR>
     * 
     * @@param l_strIfoAccountOpenDiv　@先物OP口座開設区分
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isIfoDepositAccountOpen(String l_strIfoAccountOpenDiv)
    {
        if (WEB3FutureOpAccountDef
            .FUTURE_ACCOUNT_ESTABLISH
            .equals(l_strIfoAccountOpenDiv)
            || WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(
                l_strIfoAccountOpenDiv))
        {
            return true;
        } else
        {
            return false;
        }
    }

}
@
