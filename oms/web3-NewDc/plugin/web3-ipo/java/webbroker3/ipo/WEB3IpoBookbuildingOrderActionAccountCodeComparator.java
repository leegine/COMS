head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderActionAccountCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング申告履歴.顧客コードComparator(WEB3IpoBookbuildingOrderActionAccountCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo;

import java.util.Comparator;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * ブックビルディング申告履歴.顧客コードComparator
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderActionAccountCodeComparator implements Comparator 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IpoBookbuildingOrderActionAccountCodeComparator.class);        
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     */
    private String orderBy;
    
    /**
     * @@roseuid 411308340217
     */
    public WEB3IpoBookbuildingOrderActionAccountCodeComparator() 
    {
     
    }
    
    /**
     * (ブックビルディング申告履歴.顧客コードComparator)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順
     * 
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionAccountCodeComparator
     * @@roseuid 40EE9675012A
     */
    public WEB3IpoBookbuildingOrderActionAccountCodeComparator(String l_strOrderBy) 
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
     * 顧客コードの比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast<BR>
     * 　@引数のﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１、ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴２を<BR>ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴型にcastする。<BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastしたﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１、ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴２について、<BR>
     * それぞれのﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴.get口座ＩＤ()に該当する顧客を取得する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.顧客.getAccountCode() < ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>
     * 申告履歴２.顧客.getAccountCode()）の場合、負の整数（-1）を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.顧客.getAccountCode() = ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>
     * 申告履歴２.顧客.getAccountCode()）の場合、0を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.顧客.getAccountCode() > ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>
     * 申告履歴２.顧客.getAccountCode()）の場合、正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.顧客.getAccountCode() < ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>
     * 申告履歴２.顧客.getAccountCode()）の場合、正の整数（1）を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.顧客.getAccountCode() = ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>
     * 申告履歴２.顧客.getAccountCode()）の場合、0を返却する。<BR>
     * 　@・（ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１.顧客.getAccountCode() > ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ<BR>
     * 申告履歴２.顧客.getAccountCode()）の場合、負の整数（-1）を返却する。<BR>
     * @@param l_bookbuildingOrderAction1 - ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴１
     * 
     * @@param l_bookbuildingOrderAction2 - ﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴２
     * 
     * @@return int
     * @@roseuid 40EE9675011B
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
        
        //Get Account id
        long l_lngAccountID1 = ((IpoBookbuildingOrderActionRow)(l_bookBuildingIpoOrderAction1.getDataSourceObject())).getAccountId();
        long l_lngAccountID2 = ((IpoBookbuildingOrderActionRow)(l_bookBuildingIpoOrderAction2.getDataSourceObject())).getAccountId();
        
        try
        {
            //construct Account
            MainAccount l_Account1 = new WEB3GentradeMainAccount(l_lngAccountID1);//DataFindException, DataQueryException, DataNetworkException
            MainAccount l_Account2 = new WEB3GentradeMainAccount(l_lngAccountID2);//DataFindException, DataQueryException, DataNetworkException           

            //compare
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) == 0)
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
                if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) == 0)
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
     * @@roseuid 40EE9675011E
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
