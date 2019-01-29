head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPServiceChargeRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPServiceChargeRestraint.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/91/26 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (有料サービス利用拘束金)
 */
public class WEB3TPServiceChargeRestraintReflector extends WEB3TPRestraintReflector
{

    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPServiceChargeRestraintReflector.class);
    
    /**
     * (受付日時)<BR>
     */
    private Date acceptedDate;
    
    
   /**
    * @@roseuid 41F7479B0141
    */
   public WEB3TPServiceChargeRestraintReflector() 
   {
    
   }
   
   /**
    * (create有料サービス利用拘束金)
    * 有料サービス利用拘束金を作成し、返却する。
    * @@param l_calcCondition - (余力計算条件)
    * @@param l_dblAmount - (受渡代金)
    * @@param l_datFinTransactionDate - (トランザクション発生日)
    * @@param l_datDeliveryDate - (受渡日)
    * @@roseuid 41F0AABB00A4
    */
   public static WEB3TPServiceChargeRestraintReflector create(WEB3TPCalcCondition l_calcCondition, double l_dblAmount, Date l_datDeliveryDate, Date l_datAcceptedDate) 
   {
       WEB3TPServiceChargeRestraintReflector l_instance = new WEB3TPServiceChargeRestraintReflector();
       l_instance.setCalcCondition(l_calcCondition);
       l_instance.setAmount(l_dblAmount);
       l_instance.setAcceptedDate(l_datAcceptedDate);
       l_instance.calcReflectDay(l_datDeliveryDate);
       return l_instance;       
   }

	/**
    * (get受付日時)<BR>
	 * @@return acceptedDate を戻します。
	 */
	public Date getAcceptedDate() {
		return acceptedDate;
	}
	/**
    * (set受付日時)<BR>
	 * @@param acceptedDate acceptedDate を設定。
	 */
	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
   
   /**
    * (calc変動反映日)<BR>
    * 変動反映開始日、変動反映終了日を<BR>
    * 以下のようにセットする。<BR>
    * <BR>
    * 変動反映開始日＝T+0<BR>
    * 変動反映終了日＝引数.受渡日-1<BR>
    * @@param l_datDeliveryDate - (受渡日)
    * @@roseuid 41F0AAC80084
    */
   public void calcReflectDay(Date l_datDeliveryDate)
   {   	
       final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";

       Date l_datT0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
       Date l_datT5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
       //受付日時がnull
       //受付日時が受渡日より先
       //受渡日がT+0以前
       //受付日時＝受渡日かつ受渡日がＴ＋５以前
       //のデータはロードされないはず
       if ((this.acceptedDate == null) ||
       		(WEB3DateUtility.compareToDay(acceptedDate, l_datDeliveryDate) > 0) ||
       		(WEB3DateUtility.compareToDay(l_datT0, l_datDeliveryDate) >= 0) ||
			((WEB3DateUtility.compareToDay(acceptedDate, l_datDeliveryDate) == 0) &&
					(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) >= 0)))
       {
           throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
       }
       
       
       //受付日時が
       //T+5以前の場合
       if(WEB3DateUtility.compareToDay(l_datT5, acceptedDate) >= 0)
       {
       		
        //受付日時がT+0以前の場合
       		if(WEB3DateUtility.compareToDay(l_datT0, acceptedDate) >= 0)
       		{
       	        //変動反映開始日＝T+0
       	        this.setReflectStartDay(l_datT0);       	       			       			
       		}
       		else
       		{
       	        //変動反映開始日＝受付日時
       	        this.setReflectStartDay(acceptedDate);       	       			
       		}
       }
       else
       {
        //変動反映開始日＝受付日時
        this.setReflectStartDay(l_datT5);       	       	
       }
       
       
       //受渡日が
       //T+5以前の場合
       if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) >= 0)
       {
	        //変動反映終了日＝引数.受渡日-1<BR>
	        this.setReflectEndDay(this.getCalcCondition().rollBizDate(l_datDeliveryDate, -1));
       }
       //T+5より先の場合
       else
       {
	        //変動反映終了日＝T+5<BR>
	        this.setReflectEndDay(l_datT5);       	
       }
       	

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
           .append("acceptedDate", WEB3DateUtility.formatDate(this.getAcceptedDate(), l_strYYYYMMDDFormat))
           .appendSuper(super.toString())
           .toString();
   }
   
}
@
