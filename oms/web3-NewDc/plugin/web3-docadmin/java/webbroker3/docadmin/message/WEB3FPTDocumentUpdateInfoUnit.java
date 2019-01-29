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
filename	WEB3FPTDocumentUpdateInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面更新情報(WEB3FPTDocumentUpdateInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.037
*/
package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (書面更新情報)<BR>
 * 書面更新情報クラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3FPTDocumentUpdateInfoUnit extends Message
{

    /**
     * (書面区分)<BR>
     * 書面区分<BR>
     */
    public String documentDiv;

    /**
     * (書面種類コード)<BR>
     * 書面種類コード<BR>
     */
    public String documentCategory;

    /**
     * (書面通番)<BR>
     * 書面通番<BR>
     */
    public String documentNumber;

    /**
     * (有効区分)<BR>
     * 有効区分<BR>
     * <BR>
     * 0：valid<BR>
     * 1：invalid<BR>
     */
    public String validFlag;

    /**
     * (摘要)<BR>
     * 摘要<BR>
     */
    public String remarks;

    /**
     * (書面種類名称)<BR>
     * 書面種類名称<BR>
     */
    public String documentCategoryName;

    /**
     * (電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード<BR>
     */
    public String batoProductCode;

    /**
     * @@roseuid 47CBC5AD030D
     */
    public WEB3FPTDocumentUpdateInfoUnit()
    {

    }
}
@
