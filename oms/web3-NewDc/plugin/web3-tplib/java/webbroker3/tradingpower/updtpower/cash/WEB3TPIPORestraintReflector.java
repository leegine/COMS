head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPIPORestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 堀野 和美(FLJ) 新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (IPO拘束金)<BR>
 * IPO拘束金を表現する。
 */
public class WEB3TPIPORestraintReflector
    extends WEB3TPRestraintReflector
{
    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPIPORestraintReflector.class);
    
    /**
     * (トランザクション発生日)<BR>
     */
    private Date finTransactionDate;

    /**
     * @@roseuid 4104BBC402E9
     */
    public WEB3TPIPORestraintReflector()
    {

    }

    /**
     * (createIPO拘束金)<BR>
     * IPO拘束金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@値を設定<BR>
     * 	拘束金      ＝  IPO注文.IPO購入申込代金<BR>
     * 	変動反映開始日、変動反映終了日設定：calc変動反映日(客勘反映日))<BR>
     * 　@　@（客勘反映日は購入申込締切日（目論見書記載）あるいはnull）<BR>
     * <BR>
     * ３）　@インスタンスを返却<BR>
     * <BR>
     * @@param l_calcCondition (計算条件)
     * @@param l_payAmount (購入申込代金)
     * @@param l_lotDate (抽選日)
     * @@param l_accountReflectDate (客勘反映日)
     */
    public static WEB3TPIPORestraintReflector create(
            WEB3TPCalcCondition l_calcCondition,
            double l_payAmount,
            Date l_lotDate,
            Date l_accountReflectDate)
    {
        WEB3TPIPORestraintReflector l_instance = new WEB3TPIPORestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_payAmount);
        l_instance.setFinTransactionDate(l_lotDate);
        l_instance.calcReflectDay(l_accountReflectDate);
        return l_instance;
    }

    /**
     * (calc変動反映日)<BR>
     * 変動反映開始日、変動反映終了日を以下のようにセットする。 <BR>
     * <BR>
     * 変動反映開始日： = this.getトランザクション発生日() <BR>
     * <BR>
     * [引数．客勘反映日=nullの場合] <BR>
     * 　@変動反映終了日=営業日(T+5) <BR>
     * <BR>
     * [以外（引数．客勘反映日 ≠ null)の場合] <BR>
     * 　@変動反映終了日=引数．客勘反映日-1<BR>
     * <BR>
     * @@param l_accountReflectDate - 客勘反映日
     */
    public void calcReflectDay(Date l_accountReflectDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay";

        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //開始日   	
        setReflectStartDay(this.getFinTransactionDate());

        //終了日
        //客勘反映日が設定されていない場合（購入申込前の場合）
        if (l_accountReflectDate == null)
        {
            setReflectEndDay(l_datT5);
        }
        else
        {
            //購入申込最終日がT+0以前である場合ロードされないはず
            if(WEB3DateUtility.compareToDay(l_datT0, l_accountReflectDate) >=0)
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //客勘反映日（購入申込最終日）がT+5以降である場合はT+4まで拘束する
            if(WEB3DateUtility.compareToDay(l_datT5, l_accountReflectDate) <= 0)
            {
                setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_4));
            }
            //それ以外は客勘反映日の前日まで拘束
            else
            {                
                setReflectEndDay(getCalcCondition().rollBizDate(l_accountReflectDate, -1));
            }
        }
    }

    /**
     * (getトランザクション発生日)<BR>
     * トランザクションタイプを返す。<BR>
     * @@return Date
     */
    public Date getFinTransactionDate()
    {
        return finTransactionDate;
    }

    /**
     * (setトランザクション発生日)<BR>
     * 引数をトランザクション発生日にセットする。<BR>
     * @@param l_finTransactionDate - (トランザクション発生日)
     */
    public void setFinTransactionDate(Date l_finTransactionDate)
    {
        finTransactionDate = l_finTransactionDate;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     * 
     * @@return String
     */
    public String toString()
    {
        String l_strYYYYMMDDFormat = "yyyy/MM/dd";

        return ToStringUtils
            .newToStringBuilder(this)
            .append("finTransactionDate", WEB3DateUtility.formatDate(this.getFinTransactionDate(), l_strYYYYMMDDFormat))
            .appendSuper(super.toString())
            .toString();
    }

}
@
