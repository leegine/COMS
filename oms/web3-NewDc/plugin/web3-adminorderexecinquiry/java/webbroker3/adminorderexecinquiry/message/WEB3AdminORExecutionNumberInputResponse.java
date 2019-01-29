head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORExecutionNumberInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文約定件数照会入力レスポンス (WEB3AdminORExecutionNumberInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 齊珂 (中訊) 仕様変更No.55修正
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・注文約定件数照会入力レスポンス)<BR>
 * <BR>
 * 管理者・注文約定件数照会入力レスポンスクラス<BR>
 * <BR>
 * WEB3AdminORExecutionNumberInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORExecutionNumberInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_execution_number_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (年月一覧)<BR>
     * <BR>
     * 年月一覧<BR>
     * (YYYYMM)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * searchYmList<BR>
     * (YYYYMM)<BR>
     * <BR>
     */
    public String[] searchYmList;

    /**
     * (信用実施フラグ)<BR>
     * <BR>
     * 信用実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * marginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marginFlag;

    /**
     * (ミニ株実施フラグ)<BR>
     * <BR>
     * ミニ株実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * miniFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean miniFlag;

    /**
     * (外株実施フラグ)<BR>
     * <BR>
     * 外株実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * foreignFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean foreignFlag;

    /**
     * (オプション実施フラグ)<BR>
     * <BR>
     * オプション実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * optionFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean optionFlag;

    /**
     * (先物実施フラグ)<BR>
     * <BR>
     * 先物実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * futureFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean futureFlag;

    /**
     * (投信実施フラグ)<BR>
     * <BR>
     * 投信実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * mutualFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean mutualFlag;

    /**
     * 累投実施フラグ
     *
     * false：　@未実施
     * true：　@実施
     *
     * (累投実施フラグ)<BR>
     * <BR>
     * 累投実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * ruitoFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean ruitoFlag;
    
    /**
     * (債券実施フラグ)<BR>
     * <BR>
     * 債券実施フラグ<BR>
     * <BR>
     * false：　@未実施<BR>
     * true：　@実施<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * bondFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean bondFlag;


    /**
     * @@roseuid 4212FD0C03D0
     */
    public WEB3AdminORExecutionNumberInputResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminORExecutionNumberInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
