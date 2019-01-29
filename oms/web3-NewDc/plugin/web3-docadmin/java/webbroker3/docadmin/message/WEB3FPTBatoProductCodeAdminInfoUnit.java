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
filename	WEB3FPTBatoProductCodeAdminInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子鳩銘柄コード管理情報(WEB3FPTBatoProductCodeAdminInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/25 周墨洋 (中訊) 新規作成・モデルNo.022
*/
package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (電子鳩銘柄コード管理情報)<BR>
 * 電子鳩銘柄コード管理情報<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3FPTBatoProductCodeAdminInfoUnit extends Message
{

    /**
     * (電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード<BR>
     */
    public String batoProductCode;

    /**
     * (有効区分)<BR>
     * 有効区分<BR>
     */
    public String validFlag;

    /**
     * (摘要)<BR>
     * 摘要<BR>
     */
    public String remarks;

    /**
     * (書面種類通番)<BR>
     * 書面種類通番<BR>
     */
    public String documentCategoryNumber;

    /**
     * (電子鳩銘柄コード管理情報)<BR>
     * ディフォルトコンストラクタ<BR>
     */
    public WEB3FPTBatoProductCodeAdminInfoUnit()
    {

    }

}
@
