head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.09.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceIfoService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP時価終値保存サービスインターフェース(WEB3QuotePriceService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/5/18 毛(FLJ) 新規作成
 */

package webbroker3.quoteprice.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.*;
import webbroker3.common.*;
import webbroker3.quoteprice.message.*;

/**
 * （先物OP時価終値保存サービスインターフェース）。
 * @@version 1.0
 */
public interface WEB3QuotePriceIfoService
    extends Service
{
    public WEB3QuotePriceSaveResult execute(WEB3QuotePriceSaveRequest l_request) throws
        WEB3BaseException;
}
@
