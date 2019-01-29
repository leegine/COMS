head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.09.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceMultiInstitutionService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 時価終値保存サービスインターフェース(共同系対応)(WEB3QuotePriceService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/10 劉(FLJ) 新規作成
 */

package webbroker3.quoteprice.service.delegate;

import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.common.service.delegate.*;

/**
 * （時価終値保存サービスインターフェース(共同系対応)。
 * @@version 1.0
 */
public interface WEB3QuotePriceMultiInstitutionService
    extends WEB3BusinessService
{
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
