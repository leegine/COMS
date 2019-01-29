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
filename	WEB3FPTDocumentDivAdminInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面区分管理情報(WEB3FPTDocumentDivAdminInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
Revision History : 2008/01/25 周墨洋 (中訊) 仕様変更・モデルNo.022
*/

package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (書面区分管理情報)<BR>
 * 書面区分管理情報<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3FPTDocumentDivAdminInfoUnit extends Message
{

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
     * (書面チェック区分)<BR>
     * 書面チェック区分<BR>
     * <BR>
     * 1：IPO<BR>
     * 2：投信<BR>
     * 3：金商法@<BR>
     */
    public String docuCheckDiv;

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
     * @@roseuid 46FDDD3D02AF
     */
    public WEB3FPTDocumentDivAdminInfoUnit()
    {

    }
}
@
