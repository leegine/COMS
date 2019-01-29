head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccPriceAdjustmentValueInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文単価調整値情報(WEB3SuccPriceAdjustmentValueInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.define.WEB3ToSuccSignDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (連続注文単価調整値情報)<BR>
 * 連続注文単価調整値情報。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SuccPriceAdjustmentValueInfo extends Message 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccPriceAdjustmentValueInfo.class);
    
    /**
     * (符号)<BR>
     * 単価調整値の符号。<BR>
     * （+：加算、-：減算）<BR>
     */
    public String sign;
    
    /**
     * (単価調整値)<BR>
     * 単価調整値。
     */
    public String priceAdjustmentValue;
    
    /**
     * @@roseuid 43489605006D
     */
    public WEB3SuccPriceAdjustmentValueInfo() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@符号チェック<BR>
     * 　@１－１）this.符号＝nullの場合、<BR>
     * 　@　@　@　@　@「符号がnull」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02243<BR>
     * <BR>
     * 　@１－２）this.符号が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「符号の値が未定義」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02244<BR>
     * <BR>
     * 　@　@　@　@・"+"<BR>
     * 　@　@　@　@・"-"<BR>
     * <BR>
     * ２）　@単価調整値チェック<BR>
     * 　@２－１）this.単価調整値＝nullの場合、<BR>
     * 　@　@　@　@　@「単価調整値がnull」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02260<BR>
     * <BR>
     * 　@２－２）this.単価調整値が以下のいずれかに該当する場合は、例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException<BR>
     *  　@    tag:   BUSINESS_ERROR_02261<BR>
     * <BR>
     * 　@　@・数字以外<BR>
     * 　@　@・マイナス値<BR>
     * 　@　@・８桁を超える数字<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4326B2500108
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@符号チェック
        // 　@１－１）this.符号＝nullの場合、
        // 　@　@　@　@　@「符号がnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.sign))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02243,
                getClass().getName() + STR_METHOD_NAME,
                "符号が未指定です。");
        }

        // 　@１－２）this.符号が以下の値以外の場合、
        // 　@　@　@　@　@「符号の値が未定義」の例外をスローする。
        if (!WEB3ToSuccSignDivDef.ADD.equals(this.sign)
            && !WEB3ToSuccSignDivDef.SUBTRACT.equals(this.sign))
        {
            String l_strMessage = "符号「" + this.sign + "」が存在しないコード値です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02244,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // ２）　@単価調整値チェック
        // 　@２－１）this.単価調整値＝nullの場合、
        // 　@　@　@　@　@「単価調整値がnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.priceAdjustmentValue))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02260,
                getClass().getName() + STR_METHOD_NAME,
                "単価調整値が未入力です。");
        }
        
        // 　@２－２）this.単価調整値が以下のいずれかに該当する場合は、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.priceAdjustmentValue) 
            || Double.parseDouble(this.priceAdjustmentValue) < 0
            || Double.parseDouble(this.priceAdjustmentValue) > 99999999)
        {
            String l_strMessage = "単価調整値「" + this.priceAdjustmentValue + "」が不正なコード値です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02261,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (get単価調整値)<BR>
     * 単価調整値を取得する。<BR>
     * （this.単価調整値にthis.符号を付加した値を、double型で返却する。）<BR>
     * @@return double
     * @@roseuid 4326C15A0396
     */
    public double getPriceAdjustmentValue() 
    {
        String l_strPriceAdjustmentValue= this.sign + this.priceAdjustmentValue;
        return Double.parseDouble(l_strPriceAdjustmentValue);
    }
    
    /**
     * (set単価調整値)<BR>
     * 単価調整値をセットする。<BR>
     * <BR>
     * this.符号：　@引数の単価調整値の符号（符号なし時はプラス）をセット。<BR>
     * this.単価調整値：　@引数の単価調整値の数値部分をセット。<BR>
     * @@param l_dblPriceAdjustmentValue - (単価調整値)<BR>
     * 単価調整値。<BR>
     * （株式予約注文単位テーブルの同項目の値をセット）
     * @@roseuid 4337BB660022
     */
    public void setPriceAdjustmentValue(double l_dblPriceAdjustmentValue) 
    {
        final String STR_METHOD_NAME =" setPriceAdjustmentValue(double )";
        log.entering(STR_METHOD_NAME);
        
        if (l_dblPriceAdjustmentValue < 0)
        {
            this.sign = WEB3ToSuccSignDivDef.SUBTRACT;
        }
        else
        {
            this.sign = WEB3ToSuccSignDivDef.ADD;
        }
        this.priceAdjustmentValue = Math.abs(l_dblPriceAdjustmentValue) + "";            
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
