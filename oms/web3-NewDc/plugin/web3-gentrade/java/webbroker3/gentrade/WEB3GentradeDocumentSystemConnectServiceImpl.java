head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeDocumentSystemConnectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ドキュメントシステム接続サービスImpl(WEB3GentradeDocumentSystemConnectServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 鄒 政 (中訊) 作成 
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (ドキュメントシステム接続サービスImpl)<BR>
 * ドキュメント管理システム（電子鳩）インタフェイスクラス<BR>
 */
public class WEB3GentradeDocumentSystemConnectServiceImpl
    implements WEB3GentradeDocumentSystemConnectService
{

    /**
     * @@roseuid 41075F4F0038
     */
    public WEB3GentradeDocumentSystemConnectServiceImpl()
    {

    }

    /**
     * (is目論見書既読)<BR>
     * 目論見書電子交付済かを判定する。<BR>
     * 目論見書電子交付済であればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * （電子鳩I/F不明のため、詳細未定）<BR>
     * @@param l_genAccount - 顧客オブジェクト
     * @@param l_productType - 銘柄タイプ
     * @@param l_strProductCode - 銘柄コード
     * @@return boolean
     * @@roseuid 40D7B7550049
     */
    public boolean isProspectusAccept(
        WEB3GentradeMainAccount l_genAccount,
        ProductTypeEnum l_productType,
        String l_strProductCode)
    {
        return true;
    }

    /**
     * (is停止中)<BR>
     * 電子鳩システムが停止中かを判定する。<BR>
     * 電子鳩システムが停止中であればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * （停止オペレーション未定のため、詳細Pending）<BR>
     * @@return boolean
     * @@roseuid 40F72B0E0122
     */
    public boolean isSystemStop()
    {
        return true;
    }
    
    /**
     * (is実施会社) <BR>
     * 電子鳩システム実施会社かを判定する。<BR>
     * 電子鳩実施会社であればtrue、以外は <BR>
     * falseを返却する。<BR>
     * <BR>
     * (詳細Pending)<BR>
     * <BR>
     * @@param l_genInstitution - 証券会社
     * @@return boolean
     */
    public boolean isEnforcementedInstitution(WEB3GentradeInstitution l_genInstitution)
    {
        return true;
    }

    /**
     * (is取報実施) <BR>
     * 取引報告書実施かを判定する。<BR>
     * <BR>
     * 顧客が取引報告書を実施していれば<BR>
     * true、以外はfalseを返却する。<BR>
     * <BR>
     * @@param l_genMainAccount - 顧客オブジェクト
     * @@return boolean
     */
    public boolean isTradingReportEnforcemented(WEB3GentradeMainAccount l_genMainAccount)
    {
        return true;
    }
}
@
