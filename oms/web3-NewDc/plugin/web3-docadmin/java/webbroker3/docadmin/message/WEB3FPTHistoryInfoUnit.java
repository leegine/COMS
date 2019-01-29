head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTHistoryInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金商法@交付閲覧履歴情報(WEB3FPTHistoryInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
Revision History : 2008/01/25 周墨洋 (中訊) 仕様変更・モデルNo.022
Revision History : 2008/03/17 馮海濤 (中訊) 仕様変更・モデルNo.045
*/

package webbroker3.docadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (金商法@交付閲覧履歴情報)<BR>
 * 金商法@交付閲覧履歴情報クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3FPTHistoryInfoUnit extends Message
{

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String acceptCode;

    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String acceptName;

    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * (書面区分)<BR>
     * 書面区分<BR>
     */
    public String documentDiv;

    /**
     * (書面名称)<BR>
     * 書面名称<BR>
     */
    public String documentNames;

    /**
     * (電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード<BR>
     */
    public String batoProductCode;

    /**
     * (書面交付日)<BR>
     * 書面交付日<BR>
     */
    public Date docuDeliDate;

    /**
     * (書面種類コード)<BR>
     * 書面種類コード<BR>
     */
    public String documentCategory;

    /**
     * (書面種類名称)<BR>
     * 書面種類名称<BR>
     */
    public String documentCategoryName;

    /**
     * (書面種類通番)<BR>
     * 書面種類通番<BR>
     */
    public String documentCategoryNumber;

    /**
     * (削除フラグ)<BR>
     * 削除フラグ<BR>
     */
    public String deleteFlg;

    /**
     * (みなし交付日)<BR>
     * みなし交付日<BR>
     */
    public Date deemedDeliveryDate;

    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;

    /**
     * (作成日付)<BR>
     * 作成日付<BR>
     */
    public Date createDate;

    /**
     * (更新日付)<BR>
     * 更新日付<BR>
     */
    public Date updateTimeStamp;

    /**
     * @@roseuid 46FDDD3D00EA
     */
    public WEB3FPTHistoryInfoUnit()
    {

    }
}
@
