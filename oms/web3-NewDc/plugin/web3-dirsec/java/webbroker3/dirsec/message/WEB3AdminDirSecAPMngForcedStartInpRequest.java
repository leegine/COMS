head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・下り処理強制起動入力リクエスト(WEB3AdminDirSecAPMngForcedStartInpRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/21 楊夫志(中訊) 新規作成 モデル 132
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・下り処理強制起動入力リクエスト)<BR>
 * 管理者・下り処理強制起動入力リクエスト。<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartInpRequest extends WEB3AdminDirSecAPMngForcedStartCommonRequest
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecAPMngForcedStartInpRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200807211622L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_apmng_forced_start_inp";

    /**
     * @@roseuid 488437FE019E
     */
    public WEB3AdminDirSecAPMngForcedStartInpRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）スーパークラスのvalidate()をコールする。<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 487D5A8801CC
     */
    public void validate() throws WEB3BaseException
    {
        //１）スーパークラスのvalidate()をコールする。
        super.validate();
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAPMngForcedStartInpResponse(this);
    }
}
@
