head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoRegistMethodComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 登録方法@Comparator(WEB3PvInfoRegistMethodComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/19 李丁銀(中訊) 作成
*/
package webbroker3.pvinfo;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit;
import webbroker3.util.WEB3LogUtility;

/**
 * (登録方法@Comparator)<BR>
 * 登録方法@Comparator<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoRegistMethodComparator implements Comparator
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoRegistMethodComparator.class);

    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;

    /**
     * (登録方法@Comparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoRegistMethodComparator
     * @@roseuid 41467BBC0060
     */
    public WEB3PvInfoRegistMethodComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = " WEB3PvInfoRegistMethodComparator(String)";
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
     * 登録方法@(表示条件番号)の比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の表示メッセージ情報１、表示メッセージ情報２を<BR>
     * 　@表示メッセージ情報型（表示メッセージ情報）にcastする。 <BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastした表示メッセージ情報１、表示メッセージ情報２について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（表示メッセージ情報１.表示条件番号 < <BR>
     * 表示メッセージ情報２.表示条件番号）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（表示メッセージ情報１.表示条件番号 = <BR>
     * 表示メッセージ情報２.表示条件番号）の場合、0を返却する。 <BR>
     * 　@・（表示メッセージ情報１.表示条件番号 > <BR>
     * 表示メッセージ情報２.表示条件番号）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（表示メッセージ情報１.表示条件番号 < <BR>
     * 表示メッセージ情報２.表示条件番号）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（表示メッセージ情報１.表示条件番号 = <BR>
     * 表示メッセージ情報２.表示条件番号）の場合、0を返却する。 <BR>
     * 　@・（表示メッセージ情報１.表示条件番号 > <BR>
     * 表示メッセージ情報２.表示条件番号）の場合、負の整数（-1）を返却する。 <BR>
     * @@param l_dispMessage1 - (表示メッセージ情報1)<BR>
     * 表示メッセージ情報オブジェクト1<BR>
     * @@param l_dispMessage2 - (表示メッセージ情報2)<BR>
     * 表示メッセージ情報オブジェクト2<BR>
     * @@return int
     * @@roseuid 41467BBC0031
     */
    public int compare(Object l_dispMessage1, Object l_dispMessage2)
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );
               
        // １）　@引数のcast        
        if(!(l_dispMessage1 instanceof WEB3PvInfoDisplayMessageUnit) 
            || !(l_dispMessage2 instanceof WEB3PvInfoDisplayMessageUnit))
        {
            String l_strErrorMessage = "パラメータの類型が不正、該当する'WEB3PvInfoDisplayContentsUnit' 類型。";
            log.error(l_strErrorMessage); 
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, l_strErrorMessage);  
        }

        String l_dispContentsId1 = ((WEB3PvInfoDisplayMessageUnit)l_dispMessage1).conditionNumber;
        String l_dispContentsId2 = ((WEB3PvInfoDisplayMessageUnit)l_dispMessage2).conditionNumber;

        int l_intResult;
        //２）　@比較                    
        if (l_dispContentsId1 == null || l_dispContentsId2 == null)
        {
            if (l_dispContentsId1 == null && l_dispContentsId2 == null)
            {
                l_intResult = 0;
            }
            else if (l_dispContentsId1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }            
        }
        else
        {
            int l_intCompare = l_dispContentsId1.compareTo(l_dispContentsId2);
            if(l_intCompare == 0)
            {
                l_intResult = 0;                       
            }
            else if(l_intCompare < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
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
     * @@roseuid 41467BBC0050
     */
    public boolean equals(Object l_dispMessage)
    {
        return false;
    }
}
@
