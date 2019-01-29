head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoEleDeliveryInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付情報(WEB3AccInfoEleDeliveryInfo.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.277 280
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (電子交付情報)<BR>
 * 電子交付情報クラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccInfoEleDeliveryInfo extends Message
{
    /**
     * (書面５交付区分更新日時)<BR>
     * 書面５交付区分更新日時
     */
    public Date reportDivUpdateDate5;

    /**
     * (書面５申込区分)<BR>
     * 書面５申込区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了<BR>
     */
    public String reportRegDiv5;

    /**
     * (書面５交付区分)<BR>
     * 書面５交付区分 <BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String reportDiv5;

    /**
     * (書面４交付区分更新日時)<BR>
     * 書面４交付区分更新日時
     */
    public Date reportDivUpdateDate4;

    /**
     * (書面４申込区分)<BR>
     * 書面４申込区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了<BR>
     */
    public String reportRegDiv4;

    /**
     * (書面４交付区分)<BR>
     * 書面４交付区分 <BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String reportDiv4;

    /**
     * (書面３交付区分更新日時)<BR>
     * 書面３交付区分更新日時
     */
    public Date reportDivUpdateDate3;

    /**
     * (書面３申込区分)<BR>
     * 書面３申込区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了<BR>
     */
    public String reportRegDiv3;

    /**
     * (書面３交付区分)<BR>
     * 書面３交付区分 <BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String reportDiv3;
    
    /**
     * (書面２交付区分更新日時)<BR>
     * 書面２交付区分更新日時
     */
    public Date reportDivUpdateDate2;

    /**
     * (書面２申込区分)<BR>
     * 書面２申込区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了<BR>
     */
    public String reportRegDiv2;

    /**
     * (書面２交付区分)<BR>
     * 書面２交付区分 <BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String reportDiv2;

    /**
     * (書面１交付区分更新日時)<BR>
     * 書面１交付区分更新日時
     */
    public Date reportDivUpdateDate1;

    /**
     * (書面１申込区分)<BR>
     * 書面１申込区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了<BR>
     */
    public String reportRegDiv1;

    /**
     * (書面１交付区分)<BR>
     * 書面１交付区分 <BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String reportDiv1;

    /**
     * (約款・規定集報告書申込状態区分)<BR>
     * 約款・規定集報告書申込状態区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了<BR>
     */
    public String ordRulRepRegDiv;

    /**
     * (約款・規定集報告書交付状態区分更新日時)<BR>
     * 約款・規定集報告書交付状態区分更新日時<BR>
     */
    public Date ordRulReportDivUpdateDate;

    /**
     * (約款・規定集報告書交付状態区分)<BR>
     * 約款・規定集報告書交付状態区分 <BR>
     * <BR>
     * 0：　@郵便配布<BR>
     * 1：　@電子配布<BR>
     */
    public String ordRulReportDiv;

    /**
     * (取引報告書申込状態区分)<BR>
     * 取引報告書申込状態区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了 <BR>
     */
    public String tradingReportRegDiv;

    /**
     * (取引報告書交付状態区分更新日時)<BR>
     * 取引報告書交付状態区分更新日時<BR>
     */
    public Date tradingReportDivUpdateDate;

    /**
     * (取引残高報告書申込状態区分)<BR>
     * 取引残高報告書申込状態区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了 <BR>
     */
    public String positionReportRegDiv;

    /**
     * (取引残高報告書交付状態区分更新日時)<BR>
     * 取引残高報告書交付状態区分更新日時<BR>
     */
    public Date positionReportDivUpdateDate;

    /**
     * (運用報告書申込状態区分)<BR>
     * 運用報告書申込状態区分 <BR>
     * <BR>
     * 0：　@申込中 <BR>
     * 1：　@申込完了 <BR>
     */
    public String opeReportRegDiv;

    /**
     * (運用報告書交付状態区分更新日時)<BR>
     * 運用報告書交付状態区分更新日時<BR>
     */
    public Date opeReportDivUpdateDate;

    /**
     * (運用報告書交付状態区分)<BR>
     * 運用報告書交付状態区分 <BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String opeReportDiv;

    /**
     * (取引報告書交付状態区分)<BR>
     * 取引報告書交付状態区分<BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String tradingReportDiv;

    /**
     * (取引残高報告書交付状態区分)<BR>
     * 取引残高報告書交付状態区分<BR>
     * <BR>
     * 0：　@郵便配布 <BR>
     * 1：　@電子配布<BR>
     */
    public String positionReportDiv;

    /**
     * コンストラクタ。<BR>
     */
    public WEB3AccInfoEleDeliveryInfo()
    {

    }
}
@
