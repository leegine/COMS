head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeDocumentSystemConnectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ドキュメントシステム接続サービス(WEB3GentradeDocumentSystemConnectService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 鄒 政 (中訊) 作成 
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (ドキュメントシステム接続サービス)<BR>
 * ドキュメント管理システム（電子鳩）インタフェイス
 */
public interface WEB3GentradeDocumentSystemConnectService extends Service
{

    /**
     * (is目論見書既読)<BR>
     * 目論見書電子交付済かを判定する。<BR>
     * @@param l_genMainAccount - 顧客オブジェクト
     * @@param l_productType - 銘柄タイプ
     * @@param l_strProductCode - 銘柄コード
     * @@return boolean
     * @@roseuid 40F751F30180
     */
    public boolean isProspectusAccept(
        WEB3GentradeMainAccount l_genMainAccount,
        ProductTypeEnum l_productType,
        String l_strProductCode);

    /**
     * (is停止中) <BR>
     * 電子鳩システムが停止中かを判定する。 <BR>
     * @@return boolean
     * @@roseuid 40F751EF0132
     */
    public boolean isSystemStop();

    /**
     * (is実施会社) <BR>
     * 電子鳩システム実施会社かを判定する。<BR>
     * @@param l_genInstitution - 証券会社
     * @@return boolean
     */
    public boolean isEnforcementedInstitution(WEB3GentradeInstitution l_genInstitution);

    /**
     * (is取報実施) <BR>
     * 取引報告書実施かを判定する。。<BR>
     * @@param l_genMainAccount - 顧客オブジェクト
     * @@return boolean
     */
    public boolean isTradingReportEnforcemented(WEB3GentradeMainAccount l_genMainAccount);
}
@
