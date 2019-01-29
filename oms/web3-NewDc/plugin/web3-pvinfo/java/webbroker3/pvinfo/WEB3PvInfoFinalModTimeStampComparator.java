head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoFinalModTimeStampComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 最終更新日時Comparator(WEB3PvInfoFinalModTimeStampComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/19 李丁銀(中訊) 作成
*/
package webbroker3.pvinfo;
 
import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3DateUtility;
/**
 * (最終更新日時Comparator)<BR>
 * 最終更新日時Comparator<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoFinalModTimeStampComparator implements Comparator
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoFinalModTimeStampComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;

    /**
     * (最終更新日時Comparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoFinalModTimeStampComparator
     * @@roseuid 41467BFF0050
     */
    public WEB3PvInfoFinalModTimeStampComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = " WEB3PvInfoFinalModTimeStampComparator(String)";
        log.entering(STR_METHOD_NAME);
        
        if( l_strOrderBy == null 
             || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) 
             && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            String l_strErrorInfo = " パラメータの値が'A：昇順'、'D：降順'以外です。";
            log.error(l_strErrorInfo);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME, l_strErrorInfo);  
        }
        
        this.orderBy = l_strOrderBy;     
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （compareの実装） <BR>
     * <BR>
     * 最終更新日時(更新日付)の比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の表示メッセージ情報１、表示メッセージ情報２を<BR>
     * 　@表示メッセージ情報型（表示メッセージ情報）にcastする。 <BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastした表示メッセージ情報１、表示メッセージ情報２について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（表示メッセージ情報１.更新日付 < 表示メッセージ情報２.更新日付）<BR>
     * の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（表示メッセージ情報１.更新日付 = 表示メッセージ情報２.更新日付）<BR>
     * の場合、0を返却する。 <BR>
     * 　@・（表示メッセージ情報１.更新日付 > 表示メッセージ情報２.更新日付）<BR>
     * の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（表示メッセージ情報１.更新日付 < 表示メッセージ情報２.更新日付）<BR>
     * の場合、正の整数（1）を返却する。 <BR>
     * 　@・（表示メッセージ情報１.更新日付 = 表示メッセージ情報２.更新日付）<BR>
     * の場合、0を返却する。 <BR>
     * 　@・（表示メッセージ情報１.更新日付 > 表示メッセージ情報２.更新日付）<BR>
     * の場合、負の整数（-1）を返却する。 <BR>
     * @@param l_dispMessage1 - (表示メッセージ情報1)<BR>
     * 表示メッセージ情報オブジェクト1<BR>
     * @@param l_dispMessage2 - (表示メッセージ情報2)<BR>
     * 表示メッセージ情報オブジェクト2<BR>
     * @@return int
     * @@roseuid 41467BFF0021
     */
    public int compare(Object l_dispMessage1, Object l_dispMessage2)
    { 
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );
        
        
        // １）引数のcast           
        if(!(l_dispMessage1 instanceof WEB3PvInfoDisplayMessageUnit) || !(l_dispMessage2 instanceof WEB3PvInfoDisplayMessageUnit))
        {
            String l_strErrorMessage = "パラメータの類型が不正、該当する'WEB3PvInfoDisplayContentsUnit' 類型。";
            log.error(l_strErrorMessage); 
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, l_strErrorMessage);  
        } 
           
        Date l_dateDispMessage1 = ((WEB3PvInfoDisplayMessageUnit) l_dispMessage1).lastUpdatedTimestamp;
        Date l_dateDispMessage2 = ((WEB3PvInfoDisplayMessageUnit) l_dispMessage2).lastUpdatedTimestamp;

        //  ２）比較
        int l_intCompate = WEB3DateUtility.compareToSecond(l_dateDispMessage1,l_dateDispMessage2);
        int l_intResult;
        if (l_intCompate == 0)
        {
            l_intResult = 0;
        }
        else if(l_intCompate < 0)
        {
            l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
        }
        else
        {
            l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
        }

        log.exiting(STR_METHOD_NAME);
        return l_intResult;        
    }   

    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * @@param l_dispMessage - (arg0)<BR>
     * @@return boolean
     * @@roseuid 41467BFF0031
     */
    public boolean equals(Object l_dispMessage)
    {
        return false;
    }
}
@
