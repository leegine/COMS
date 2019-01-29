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
filename	WEB3HostReqOrderNumberManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 識別番号採番インターフェース(WEB3HostReqOrderNumberManageService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/05/17 孟 東 (中訊) 引数を変更
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;

/**
 * （識別番号採番インターフェース）<BR>
 * <BR>
 * 注文識別番号を採番するサービスインターフェース<BR>
 */
public interface WEB3HostReqOrderNumberManageService extends Service
{

    /**
     * 注文識別番号を取得する。<BR>
     * <BR>
     * @@param l_strInstitutionCode　@証券会社コード
     * @@param l_strBranchCode　@部店コード
     * @@param l_product_type　@銘柄タイプ
     * @@return　@注文識別番号
     * @@roseuid 40349664021C
     */
    public String getNewNumber(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_product_type) throws WEB3BaseException;
}
@
