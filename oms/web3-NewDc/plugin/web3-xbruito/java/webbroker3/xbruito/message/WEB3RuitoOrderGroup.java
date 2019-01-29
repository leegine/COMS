head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投注文照会注文単位クラス(WEB3RuitoOrderGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 累投注文照会注文単位<BR>
 */
public class WEB3RuitoOrderGroup extends Message
{

    /**
     * 注文ID<BR>
     */
    public String id;

    /**
     * 累積投資のファ@ンドコード<BR>
     */
    public String ruitoProductCode;

    /**
     * 累積投資のファ@ンド名<BR>
     */
    public String ruitoProductName;

    /**
     * 売買区分(累投)<BR>
     * <BR>
     * 1:買付  2:全部解約  3:金額指定解約　@4：口数指定解約<BR>
     */
    public String ruitoDealingType;

    /**
     * 注文日時<BR>
     */
    public Date orderDate;

    /**
     * 注文数量区分<BR>
     * <BR>
     * 3: 金額　@4:口数<BR>
     */
    public String ruitoOrderQuantityType;

    /**
     * 注文数量<BR>
     */
    public String ruitoOrderQuantity;

    /**
     *  1:受付済(新規注文)　@        3:発注済(新規注文)<BR>
     *  6:発注失敗(新規注文)     12:受付済(取消注文)<BR>
     * 14:発注済(取消注文)　@　@   15:発注失敗(取消注文)<BR>
     * 30．受付済（MRF解約あり）  31．発注済（MRF解約あり）<BR>
     * 32．発注失敗（ＭＲＦ解約取消注文）<BR>
     */
    public String orderState;

    /**
     * 取消可能フラグ<BR>
     * true：取消可能　@false：取消不可<BR>
     */
    public boolean cancelFlag;

    /**
     * 注文チャネル<BR>
     * <BR>
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル<BR>
     * （コールセンターの時のみ使用）<BR>
     */
    public String orderChannel;

    /**
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット<BR>
     * 4：i-mode　@5：Vodafone　@6：AU　@9：HOST<BR>
     * （コールセンターの時のみ使用）<BR>
     */
    public String orderRootDiv;

    /**
     * 取扱者コード<BR>
     * （コールセンターの時のみ使用）<BR>
     */
    public String operatorCode;

    /**
     * コンストラクタ<BR>
     * @@roseuid 4087532101A1
     */
    public WEB3RuitoOrderGroup()
    {

    }
}
@
