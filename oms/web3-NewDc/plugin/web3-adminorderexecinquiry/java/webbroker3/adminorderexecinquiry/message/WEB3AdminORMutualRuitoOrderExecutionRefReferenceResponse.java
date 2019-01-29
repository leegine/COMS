head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・投信累投注文約定照会レスポンス
                        (WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・投信累投注文約定照会レスポンス)<BR>
 * <BR>
 * 管理者・投信累投注文約定照会レスポンスクラス<BR>
 * <BR>
 * WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_mutual_ruito_order_execution_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502011606L;

    /**
     * (総ページ数)<BR>
     * <BR>
     * 総ページ数<BR>
     * <BR>
     * totalPages<BR>
     * <BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * <BR>
     * 総レコード数<BR>
     * <BR>
     * totalRecords<BR>
     * <BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * <BR>
     * 表示ページ番号<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * （管理者投信累投注文約定照会Unit一覧）<BR>
     * <BR>
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefUnit[] mutualRuitoOrderExecutionRefList;

    /**
     * @@roseuid 4212FC2501BD
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse()
    {

    }

    /**
    *
    * @@param l_request WEB3GenRequest
    */
    public WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
