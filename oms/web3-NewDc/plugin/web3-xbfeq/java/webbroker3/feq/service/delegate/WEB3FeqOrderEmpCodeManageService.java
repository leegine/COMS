head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderEmpCodeManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式運用コード採番サービス(WEB3FeqOrderEmpCodeManageService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
                   2005/07/26 王亞洲(中訊) レビュー
*/
package webbroker3.feq.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMarket;


/**
 * (外国株式運用コード採番サービス) <BR>
 * 外国株式運用コード採番サービスインタフェイス
 * @@author 艾興
 * @@version 1.0 
 */
public interface WEB3FeqOrderEmpCodeManageService extends Service 
{
    
    /**
     * (get新規運用コード) <BR>
     * 運用コードを自動採番する。
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strFeqOrderEmpDiv - (運用コード市場識別区分)
     * @@param l_datBizDate - (発注日)
     * @@return String
     * @@roseuid 42846824012B
     */
    public String getNewEmpCode(String l_strInstitutionCode, String l_strFeqOrderEmpDiv, Date l_datBizDate)
        throws WEB3BaseException;
    
    /**
     * (get新規運用コード) <BR>
     * 運用コードを自動採番する。
     * @@param l_market - (市場) <BR>
     * 市場オブジェクト
     * @@param l_datBizDate - (発注日)
     * @@return String
     * @@roseuid 42846824013B
     */
    public String getNewEmpCode(WEB3GentradeMarket l_market, Date l_datBizDate) throws WEB3BaseException;
}
@
