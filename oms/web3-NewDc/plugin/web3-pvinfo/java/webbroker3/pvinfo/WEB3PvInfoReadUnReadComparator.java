head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoReadUnReadComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 未読既読Comoparator(WEB3PvInfoReadUnReadComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/19 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit;
import webbroker3.util.WEB3LogUtility;

/**
 * (未読既読Comoparator)<BR>
 * 未読既読Comparator<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoReadUnReadComparator implements Comparator
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3PvInfoReadUnReadComparator.class);

    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;

    /**
     * (未読既読Comparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoReadUnReadComparator
     * @@roseuid 414679400206
     */
    public WEB3PvInfoReadUnReadComparator(String l_strOrderBy)
    {
        final String STR_METHOD_NAME = " WEB3PvInfoReadUnReadComparator(String)";
        log.entering(STR_METHOD_NAME );
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            String l_strErrorInfo = "パラメータの値が'A：昇順'、'D：降順'以外です。";
            log.error(STR_METHOD_NAME + l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);

        }
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * （compareの実装） <BR>
     * <BR>
     * 未読既読フラグの比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の表示メッセージ情報１、表示メッセージ情報２を<BR>
     * 　@表示メッセージ情報型（表示メッセージ情報）にcastする。 <BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastした表示メッセージ情報１、表示メッセージ情報２について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（表示メッセージ情報１.未読既読フラグ < <BR>
     * 表示メッセージ情報２.未読既読フラグ）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（表示メッセージ情報１.未読既読フラグ = <BR>
     * 表示メッセージ情報２.未読既読フラグ）の場合、0を返却する。 <BR>
     * 　@・（表示メッセージ情報１.未読既読フラグ > <BR>
     * 表示メッセージ情報２.未読既読フラグ）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（表示メッセージ情報１.未読既読フラグ < <BR>
     * 表示メッセージ情報２.未読既読フラグ）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（表示メッセージ情報１.未読既読フラグ = <BR>
     * 表示メッセージ情報２.未読既読フラグ）の場合、0を返却する。 <BR>
     * 　@・（表示メッセージ情報１.未読既読フラグ > <BR>
     * 表示メッセージ情報２.未読既読フラグ）の場合、負の整数（-1）を返却する。 <BR>
     * @@param l_dispMessage1 - (表示メッセージ情報1)<BR>
     * 表示メッセージ情報オブジェクト1<BR>
     * @@param l_dispMessage2 - (表示メッセージ情報2)<BR>
     * 表示メッセージ情報オブジェクト2<BR>
     * @@return int
     * @@roseuid 4146792303AC
     */
    public int compare(Object l_dispMessage1, Object l_dispMessage2)
    {

        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );

        //１）　@引数のcast
        if( ! (l_dispMessage1 instanceof WEB3PvInfoDisplayMessageUnit)
            || !(l_dispMessage2 instanceof WEB3PvInfoDisplayMessageUnit))
        {
            String l_strErrorMessage =
                "パラメータの類型が不正、該当する'WEB3PvInfoDisplayMessageUnit' 類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        WEB3PvInfoDisplayMessageUnit l_dispPvInfoMessage1 = (WEB3PvInfoDisplayMessageUnit)l_dispMessage1;
        WEB3PvInfoDisplayMessageUnit l_dispPvInfoMessage2 = (WEB3PvInfoDisplayMessageUnit)l_dispMessage2;

        //２）　@比較

        //get readFlag
        String l_strReadFlag1 = (l_dispPvInfoMessage1.readFlag) ? WEB3PvInfoReadFlagDef.READ_YES : WEB3PvInfoReadFlagDef.READ_NO;
        String l_strReadFlag2 = (l_dispPvInfoMessage2.readFlag) ? WEB3PvInfoReadFlagDef.READ_YES : WEB3PvInfoReadFlagDef.READ_NO;

        int l_intResult;
        //２）　@比較                    
        if (l_strReadFlag1 == null || l_strReadFlag2 == null)
        {
            if (l_strReadFlag1 == null && l_strReadFlag2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strReadFlag1 == null)
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
            int l_intCompare = l_strReadFlag1.compareTo(l_strReadFlag2);
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
     * @@roseuid 4146792303CB
     */
    public boolean equals(Object l_dispMessage)
    {
        final String STR_METHOD_NAME = " equals(Object)";
        log.entering(STR_METHOD_NAME );

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
