head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionsOrderCarryOverRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文繰越リクエスト(WEB3OptionsOrderCarryOverRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 鄒鋭 (中訊) 新規作成
Revesion History : 2006/06/21 孟亜南 (中訊) 仕様変更 モデル 670
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (株価指数オプション注文繰越リクエスト)<BR>
 * 株価指数オプション注文繰越リクエストクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsOrderCarryOverRequest  extends WEB3IfoOrderCarryOverMainRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_orderCarryOver";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111045L;
    
    /**
     * @@roseuid 40C0AE5202CE
     */
    public WEB3OptionsOrderCarryOverRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * super.validate()をコールする。 <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3OptionsOrderCarryOverResponse(this);
    }
}
@
