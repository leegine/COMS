head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderActionBranchCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング申告履歴.部店コードComparator(WEB3IpoBookbuildingOrderActionBranchCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo;

import java.util.Comparator;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * ブックビルディング申告履歴.部店コードComparator
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderActionBranchCodeComparator implements Comparator 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
          WEB3IpoBookbuildingOrderActionBranchCodeComparator.class);        
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     */
    private String orderBy;
    
    /**
     * @@roseuid 411308340177
     */
    public WEB3IpoBookbuildingOrderActionBranchCodeComparator() 
    {
     
    }
    
    /**
     * (ブックビルディング申告履歴.部店コードComparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     * 
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionBranchCodeComparator
     * @@roseuid 40EE95CD02A6
     */
    public WEB3IpoBookbuildingOrderActionBranchCodeComparator(String l_strOrderBy) 
    {
        if(l_strOrderBy == null || 
            (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
            
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 部店コードの比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数のﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１、ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴２を<BR>ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴型にcastする。<BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastしたﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１、ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴２について<BR> 
     * それぞれのﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴.ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴行.部店ＩＤに<BR>該当する顧客を取得する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.部店.getBranchCode() < 
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>申告履歴２.部店.getBranchCode()）の場合、負の整数（-1）を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.部店.getBranchCode() = 
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>申告履歴２.部店.getBranchCode()）の場合、0を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.部店.getBranchCode() > 
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>申告履歴２.部店.getBranchCode()）の場合、正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.部店.getBranchCode() < 
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>申告履歴２.部店.getBranchCode()）の場合、正の整数（1）を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.部店.getBranchCode() = 
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>申告履歴２.部店.getBranchCode()）の場合、0を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.部店.getBranchCode() > 
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>申告履歴２.部店.getBranchCode()）の場合、負の整数（-1）を返却する。<BR>
     * @@param l_bookbuildingOrderAction1 - (ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１)<BR>
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴オブジェクト１
     * 
     * @@param l_bookbuildingOrderAction2 - (ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴２)<BR>
     * ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴オブジェクト２
     * 
     * @@return int
     * @@roseuid 40EE95CD02A1
     */
    public int compare(Object l_bookbuildingOrderAction1, Object l_bookbuildingOrderAction2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );
        
        //１）　@引数のcast
        if( ! (l_bookbuildingOrderAction1 instanceof WEB3IpoBookbuildingOrderActionImpl) 
            || !(l_bookbuildingOrderAction2 instanceof WEB3IpoBookbuildingOrderActionImpl))
        {
            String l_strErrorMessage = 
                "パラメータの類型が不正、該当する'WEB3IpoBookbuildingOrderActionImpl' 類型。";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        
        WEB3IpoBookbuildingOrderActionImpl l_bookBuildingIpoOrderAction1 = 
            (WEB3IpoBookbuildingOrderActionImpl )l_bookbuildingOrderAction1;
        WEB3IpoBookbuildingOrderActionImpl l_bookBuildingIpoOrderAction2 = 
            (WEB3IpoBookbuildingOrderActionImpl )l_bookbuildingOrderAction2;

        //２）　@比較
        
        //Get branch id
        long l_lngBranchID1 = ((IpoBookbuildingOrderActionRow)(l_bookBuildingIpoOrderAction1.getDataSourceObject())).getBranchId();
        long l_lngBranchID2 = ((IpoBookbuildingOrderActionRow)(l_bookBuildingIpoOrderAction2.getDataSourceObject())).getBranchId();
        
        try
        {
            //construct branch
            Branch l_branch1 = new WEB3GentradeBranch(l_lngBranchID1);//DataFindException, DataQueryException, DataNetworkException
            Branch l_branch2 = new WEB3GentradeBranch(l_lngBranchID2);//DataFindException, DataQueryException, DataNetworkException
            
            //compare
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if(l_branch1.getBranchCode().compareTo(l_branch2.getBranchCode()) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if(l_branch1.getBranchCode().compareTo(l_branch2.getBranchCode()) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }
            else if(WEB3AscDescDef.DESC.equals(this.orderBy))
            { 
                if(l_branch1.getBranchCode().compareTo(l_branch2.getBranchCode()) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if(l_branch1.getBranchCode().compareTo(l_branch2.getBranchCode()) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
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
        catch (DataFindException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataQueryException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        
    }
    
    /**
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。<BR>
     * falseを返却する。
     * @@param l_arg0 - (arg0)
     * @@return boolean
     * @@roseuid 40EE95CD02A4
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
