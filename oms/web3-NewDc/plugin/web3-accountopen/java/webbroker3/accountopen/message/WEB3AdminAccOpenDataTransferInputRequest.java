head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設データ移管入力リクエスト(WEB3AdminAccOpenDataTransferInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 孟亞南(中訊) 新規作成 モデル 180
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者口座開設データ移管入力リクエスト)<BR>
 * 管理者口座開設データ移管入力リクエスト<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_data_transfer_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908181003L;

    /**
     * @@roseuid 4A8A083402CE
     */
    public WEB3AdminAccOpenDataTransferInputRequest()
    {     
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenDataTransferInputResponse(this);
    }
}
@
