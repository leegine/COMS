head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashValuationAfterSell.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 売付後総資金(WEB3TPCashValuationAfterSell.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.updtpower.cash;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (売付後総資金)
 * ※総資金クラスの拡張クラス
 */
public class WEB3TPCashValuationAfterSell extends WEB3TPCashValuation
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPCashValuationAfterSell.class);

    /**
     * (デフォルトコンストラクタ)
     */
    public WEB3TPCashValuationAfterSell()
    {
        super();
    }

    /**
     * (staticメソッド)(create売付後総資金)
     * 
     * 売付後総資金オブジェクトを生成し返却する。
     * 
     * １）売付後総資金オブジェクトを生成する。
     * 　@-デフォルトコンストラクタをコール
     * 
     * ２）顧客条件をセット
     * 　@　@-売付後総資金オブジェクト.set顧客属性()をコール
     * 
     * 　@　@[引数]　@
     * 　@　@　@顧客属性：引数.顧客属性
     * 　@
     * ３）計算条件をセット
     * 　@　@-売付後総資金オブジェクト.set余力計算条件をコール
     * 
     * 　@　@[引数]
     * 　@　@　@余力計算条件：引数.余力計算条件
     * 
     * ４）現注文内容をセット
     * 　@　@-売付後総資金オブジェクト.set現注文内容()をコール
     * 
     * 　@　@[引数]
     * 　@　@　@現注文内容：引数.現注文内容
     * 
     * ５）預り金をセット
     * 　@５－１）預り金オブジェクトを生成する。
     * 　@　@-預り金.create預り金()をコール
     * 
     * 　@　@[引数]
     * 　@　@　@総資金：１）で生成した売付後総資金オブジェクト
     * 
     * 　@５－２）預り金をセットする
     * 　@　@-売付後総資金オブジェクトset預り金()をコール
     * 
     * 　@　@[引数]
     * 　@　@　@預り金：５－２）で生成した預り金オブジェクト
     * 
     * ６）取引代金をセット
     * 　@６－１）売付後取引代金オブジェクトを生成する。
     * 　@　@-売付後取引代金.create売付後取引代金()をコール
     * 　@
     * 　@　@[引数]
     * 　@　@　@総資金：１）で生成した売付後総資金オブジェクト
     * 　@　@　@注文銘柄ID：引数.注文銘柄ID
     * 
     * 　@６－２）取引代金をセットする。
     * 　@　@-売付後総資金オブジェクト.set取引代金()をコール
     * 　@
     * 　@　@[引数]
     * 　@　@　@取引代金：６－１）で生成した売付後取引代金オブジェクト
     * 
     * ７）拘束金をセット
     * 　@７－１）拘束金オブジェクトを生成する。
     * 　@　@-拘束金.create拘束金()をコール
     * 
     * 　@　@[引数]
     * 　@　@　@総資金：１）で生成した売付後総資金オブジェクト
     * 
     * 　@７－２）拘束金をセットする。
     * 　@　@-売付後総資金オブジェクト.set拘束金()をコール
     * 
     * 　@　@[引数]
     * 　@　@　@拘束金：７－１）で生成した拘束金オブジェクト
     * 　@
     * ８）生成した売付後総資金オブジェクトの返却する。
     * 
     * 　@[返却値]
     * 　@　@生成した売付後総資金オブジェクト
     * 
     * @@param l_accountInfo - (顧客属性)
     * @@param l_calcCondition - (計算条件)
     * @@param l_newOrderSpecs - (現注文内容)
     * @@param l_lngOrderProductId - (注文銘柄ID)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterSell
     */
    public static WEB3TPCashValuationAfterSell createWEB3TPCashValuationAfterSell(
        WEB3TPAccountInfo l_accountInfo,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        long l_lngOrderProductId)
    {
        final String STR_METHOD_NAME = "createWEB3TPCashValuationAfterSell(WEB3TPAccountInfo, WEB3TPCalcCondition, WEB3TPNewOrderSpec[], long)";
        log.entering(STR_METHOD_NAME);

        /*
         * 売付後総資金オブジェクトを生成する。
         */
        WEB3TPCashValuationAfterSell l_instance = new WEB3TPCashValuationAfterSell();

        //顧客条件をセット
        l_instance.setAccountInfo(l_accountInfo);
        //計算条件をセット
        l_instance.setCalcCondition(l_calcCondition);
        //現注文内容をセット
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        //取引代金をセット
        l_instance.setTransactionAmount(WEB3TPTransactionAmountAfterSell.createWEB3TPTransactionAmountAfterSell(
            l_instance,
            l_lngOrderProductId));
        //預り金をセット
        l_instance.setCashBalance(WEB3TPCashBalance.create(l_instance));
        //拘束金をセット
        l_instance.setRestraint(WEB3TPRestraint.create(l_instance));

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }
}@
