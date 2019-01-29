head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointApplyTimestampComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込日時Comparator(WEB3AdminPointApplyTimestampComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.point.message.WEB3AdminPointApplyDetail;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (申込日時Comparator)<BR>
 * 申込日時Comparatorクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointApplyTimestampComparator implements Comparator 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPointApplyTimestampComparator.class);        
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順<BR>
     * 　@D：降順<BR>
     */
    private String orderBy;
    
    /**
     * (申込日時Comparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。<BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順<BR>
     * 　@D：降順<BR>
     * 
     * @@roseuid 419C09A1028A
     */
    public WEB3AdminPointApplyTimestampComparator(String l_strOrderBy) 
    {
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
            
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 申込日時の比較を行う。<BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数のポイント申込明細１、ポイント申込明細２をポイント申込明細型に<BR>
     * castする。<BR>
     * <BR>
     * ２）　@比較<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（ポイント申込明細１.申込日時 < ポイント申込明細２.申込日時）の場合、<BR>
     * 負の整数（-1）を返却する。<BR>
     * 　@・（ポイント申込明細１.申込日時 = ポイント申込明細２.申込日時）の場合、<BR>
     * 0を返却する。<BR>
     * 　@・（ポイント申込明細１.申込日時 > ポイント申込明細２.申込日時）の場合、<BR>
     * 正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（ポイント申込明細１.申込日時 < ポイント申込明細２.申込日時）の場合、<BR>
     * 正の整数（1）を返却する。<BR>
     * 　@・（ポイント申込明細１.申込日時 = ポイント申込明細２.申込日時）の場合、<BR>
     * 0を返却する。<BR>
     * 　@・（ポイント申込明細１.申込日時 > ポイント申込明細２.申込日時）の場合、<BR>
     * 負の整数（-1）を返却する。<BR>
     * @@param l_pointApplyDetail1 - (ポイント申込明細１)<BR>
     * ポイント申込明細オブジェクト<BR>
     * 
     * @@param l_pointApplyDetail2 - (ポイント申込明細２)<BR>
     * ポイント申込明細オブジェクト<BR>
     * 
     * @@return int
     * @@roseuid 419C09A102A9
     */
    public int compare(Object l_pointApplyDetail1, Object l_pointApplyDetail2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );
        
        //１）　@引数のcast
        if( ! (l_pointApplyDetail1 instanceof WEB3AdminPointApplyDetail) 
            || !(l_pointApplyDetail2 instanceof WEB3AdminPointApplyDetail))
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当する'WEB3AdminPointApplyDetail' 類型。";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        
        WEB3AdminPointApplyDetail l_pointApplyDetail1_alias = (WEB3AdminPointApplyDetail )l_pointApplyDetail1;
        WEB3AdminPointApplyDetail l_pointApplyDetail2_alias = (WEB3AdminPointApplyDetail )l_pointApplyDetail2;
        
        //２）　@比較
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            if (l_pointApplyDetail1_alias.applyDate != null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }            
            
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            log.exiting(STR_METHOD_NAME);
            return 1;
        }
        if (WEB3AscDescDef.DESC.equals(this.orderBy))
        { 
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            if (l_pointApplyDetail1_alias.applyDate == null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            if (l_pointApplyDetail1_alias.applyDate != null && l_pointApplyDetail2_alias.applyDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }            
            
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            if (WEB3DateUtility.compareToSecond(l_pointApplyDetail1_alias.applyDate, l_pointApplyDetail2_alias.applyDate) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            log.exiting(STR_METHOD_NAME);
            return -1;
        }
        else
        {
            String l_strErrorMessage = 
                "昇順、降順 undefined.";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
    }
    
    /**
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。<BR>
     * falseを返却する。<BR>
     * @@param l_arg0 - (arg0)
     * @@return boolean
     * @@roseuid 419C09A102C8
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
