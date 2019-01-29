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
filename	WEB3FeqOrderEmpCodeGettingService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式運用コード取得サービス（WEB3FeqOrderEmpCodeGettingService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/03 武波 (中訊) 新規作成 モデルNo.501
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * (外国株式運用コード取得サービス) <BR>
 * 外国株式運用コード取得サービスインタフェイス<BR>
 * @@author 武波
 * @@version 1.0 
 */
public interface WEB3FeqOrderEmpCodeGettingService extends Service
{

    /**
     * (getPREFIX)<BR>
     * 運用コードの左2桁を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPREFIX(String l_strInstitutionCode) throws WEB3BaseException;

    /**
     * (get運用コード)<BR>
     * ７桁の「運用コード」文字列を取得し返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getEmpCode(String l_strInstitutionCode, String l_strEmpCode) throws WEB3BaseException;
}
@
