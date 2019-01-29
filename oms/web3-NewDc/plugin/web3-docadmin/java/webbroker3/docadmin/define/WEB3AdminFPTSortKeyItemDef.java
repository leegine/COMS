head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTSortKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@ソートキー定数定義インタフェイス(WEB3AdminFPTSortKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 何文敏 (中訊) 新規作成
Revision History : 2008/01/28 柴双紅 (中訊) モデルNo.025
Revision History : 2008/03/17 馮海濤 (中訊) 仕様変更・モデルNo.037 モデルNo.047
*/
package webbroker3.docadmin.define;

/**
 * 管理者金商法@ソートキー定数定義インタフェイス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AdminFPTSortKeyItemDef
{
    /**
     * 部店コード
     */
    public static String BRANCH_CODE = "branchCode";

    /**
     * 顧客コード
     */
    public static String ACCEPT_CODE = "acceptCode";

    /**
     * 書面交付日
     */
    public static String DOCU_DELI_DATE = "docuDeliDate";

    /**
     * 書面種類
     */
    public static String DOCUMENT_CATEGORY = "documentCategory";

    /**
     * 電子鳩銘柄コード
     */
    public static String PRODUCT_CODE = "batoProductCode";

    /**
     * 有効区分
     */
    public static String VALID_FLAG = "validFlag";

    /**
     * みなし交付日 
     */
    public static String DEEMED_DELIVERY_DATE = "deemedDeliveryDate";
    
}
@
