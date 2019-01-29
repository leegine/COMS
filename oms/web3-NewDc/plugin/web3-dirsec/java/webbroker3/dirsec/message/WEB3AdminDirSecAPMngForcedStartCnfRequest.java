head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartCnfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・下り処理強制起動確認リクエスト(WEB3AdminDirSecAPMngForcedStartCnfRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/21 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・下り処理強制起動確認リクエスト)<BR>
 * 管理者・下り処理強制起動確認リクエストクラス。<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartCnfRequest extends WEB3AdminDirSecAPMngForcedStartCommonRequest
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200807211715L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_apmng_forced_start_cnf";

	/**
	 * 当リクエストデータの整合性チェックを行う。<BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
	 * <BR>
	 * １）スーパークラスのvalidate()をコールする。<BR>
	 * @@throws WEB3BaseException 
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
        return new WEB3AdminDirSecAPMngForcedStartCnfResponse(this);
    }
}
@
