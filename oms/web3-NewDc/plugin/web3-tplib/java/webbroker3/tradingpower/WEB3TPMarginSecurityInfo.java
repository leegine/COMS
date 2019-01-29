head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginSecurityInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 二階建チェックエラー銘柄情報クラス(WEB3TPMarginSecurityInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/28 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;


/**
 * （二階建チェックエラー銘柄情報）
 */
public class WEB3TPMarginSecurityInfo
{

    /**
     * （二階建対象銘柄ID）<BR>
     */
    public long marginSecProductId;

    /**
     * （二階建占有率）
     */
    public double marginSecRate;

    /**
     * (コンストラクタ)
     */
    public WEB3TPMarginSecurityInfo()
    {

    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("marginSecProductId", this.marginSecProductId);
        l_builder.append("marginSecRate", this.marginSecRate);

        return l_builder.toString();
    }
}
@
