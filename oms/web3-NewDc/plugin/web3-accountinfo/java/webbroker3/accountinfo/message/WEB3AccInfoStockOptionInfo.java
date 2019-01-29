head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStockOptionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ストックオプション銘柄情報(WEB3AccInfoStockOptionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 車進 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ストックオプション銘柄情報)
 * ストックオプション銘柄情報
 * 
 * @@author 車進
 * @@version 1.0
 */
public class WEB3AccInfoStockOptionInfo extends Message
{

    /**
     * (銘柄コード)
     * 銘柄コード
     */
    public String productCode;

    /**
     * (銘柄名）
     * 銘柄名
     */
    public String productName;

    /**
     * @@roseuid 44FEAB0D01E4
     */
    public WEB3AccInfoStockOptionInfo() 
    {

    }
}@
