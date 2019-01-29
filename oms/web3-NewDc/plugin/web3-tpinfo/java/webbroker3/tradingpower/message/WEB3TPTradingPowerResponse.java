head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 取引余力画面表示レスポンス(WEB3TPTradingPowerResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (取引余力画面表示レスポンス) <BR>
 * 取引余力画面表示レスポンスクラス。 <BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPTradingPowerResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_tradingpower";

    /**
     * 余力計算結果ID
     */
    public String calcResultId;

    /**
     * 取引余力明細一覧
     */
    public WEB3TPTradingPowerUnit[] tradingPowerUnits;

    /**
     * 現物株式受渡日
     */
    public Date equityDeliveryDate;

    /**
     * 信用新規建基準日
     */
    public Date marginBaseDate;

    /**
     * ミニ株受渡日
     */
    public Date mstkDeliveryDate;

    /**
     * オプション受渡日
     */
    public Date optionsDeliveryDate;

    /**
     * 投信受渡日
     */
    public Date mutualDeliveryDate;

    /**
     * 累投受渡日
     */
    public Date ruitoDeliveryDate;

    /**
     * IPO基準日
     */
    public Date ipoBaseDate;

    /**
     * 中国株式受渡日
     */
    public Date feqDeliveryDate;

    /**
     * 出金受渡日
     */
    public Date aioDeliveryDate;

    /**
     * (コンストラクタ)
     * 
     * @@param l_request
     * @@roseuid 41B6993D034B
     */
    protected WEB3TPTradingPowerResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

    /**
     * (コンストラクタ)
     * 
     * @@roseuid 41B681F5036B
     */
    public WEB3TPTradingPowerResponse()
    {

    }

}@
