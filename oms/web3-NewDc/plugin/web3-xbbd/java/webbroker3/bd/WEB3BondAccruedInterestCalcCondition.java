head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAccruedInterestCalcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 経過利子計算条件(WEB3BondAccruedInterestCalcCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 郭英 (中訊) 新規作成 
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.data.AccruedInterestCalcCondDao;
import webbroker3.bd.data.AccruedInterestCalcCondRow;


/**
 * (経過利子計算条件)<BR>
 * 経過利子計算条件<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3BondAccruedInterestCalcCondition 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondAccruedInterestCalcCondition.class);   
    
    /**
     * (経過利子計算条件Row)<BR>
     * 経過利子計算条件行オブジェクト <BR>
     * （自動生成DAOクラス）<BR>
     */
    private AccruedInterestCalcCondRow accruedInterestCalcCondRow;
    
    /**
     * @@roseuid 44FBFEE1037A
     */
    public WEB3BondAccruedInterestCalcCondition() 
    {
     
    }
    
    /**
     * (経過利子計算条件)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * 引数の条件に一致する経過利子計算条件オブジェクトを返却する。 <BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@引数の値にて経過利子計算条件テーブルを検索する。 <BR>
     * <BR>
     * 　@　@[検索条件]<BR>
     * 　@　@引数.経過利子計算タイプ<BR>
     * <BR>
     * <BR>
     * ２）　@行オブジェクトセット <BR>
     * 　@検索結果の行オブジェクト（経過利子計算条件Row）を <BR>
     * this.経過利子計算条件Rowにセットする。 <BR>
     * @@param l_strAccruedInterestCalcType - (経過利子計算タイプ)<BR>
     * 経過利子計算タイプ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44F394C90213
     */
    public WEB3BondAccruedInterestCalcCondition(String l_strAccruedInterestCalcType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3BondAccruedInterestCalcCondition(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@DB検索  
            //　@引数の値にて経過利子計算条件テーブルを検索する。  
            //　@　@[検索条件] 
            //　@　@引数.経過利子計算タイプ 
            AccruedInterestCalcCondRow l_row = 
                AccruedInterestCalcCondDao.findRowByPk(l_strAccruedInterestCalcType);
            //DataFindException, DataNetworkException, DataQueryException
            
            //２）　@行オブジェクトセット  
            //　@検索結果の行オブジェクト（経過利子計算条件Row）を  
            //this.経過利子計算条件Rowにセットする。
            this.accruedInterestCalcCondRow = l_row;
            log.exiting(STR_METHOD_NAME);            
        }
        catch (DataFindException l_ex)
        {
            log.error("経過利子計算条件テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }         
    }
    
    /**
     * (get基準日区分)<BR>
     * 経過利子計算条件Rowから基準日区分を取得する。<BR>
     * @@return String
     * @@roseuid 44F393130119
     */
    public String getBaseDateDiv() 
    {
        return this.accruedInterestCalcCondRow.getBaseDateDiv();
    }
    
    /**
     * (get基準日数区分)<BR>
     * 経過利子計算条件Rowから基準日数区分を取得する。<BR>
     * @@return String
     * @@roseuid 44F393540281
     */
    public String getBaseDaysDiv() 
    {
        return this.accruedInterestCalcCondRow.getBaseDaysDiv();
    }
    
    /**
     * (get経過日数区分)<BR>
     * 経過利子計算条件Rowから経過日数区分を取得する。<BR>
     * @@return String
     * @@roseuid 44F393670158
     */
    public String getElapsedDaysDiv() 
    {
        return this.accruedInterestCalcCondRow.getElapsedDaysDiv();
    }
    
    /**
     * (get計算基準方式)<BR>
     * 経過利子計算条件Rowから計算基準方式を取得する。<BR>
     * @@return String
     * @@roseuid 44F39375031D
     */
    public String getCalcBaseForm() 
    {
        return this.accruedInterestCalcCondRow.getCalcBaseForm();
    }
    
    /**
     * (get単価桁数)<BR>
     * 経過利子計算条件Rowから単価桁数を取得する。<BR>
     * @@return int
     * @@roseuid 44F393930177
     */
    public int getUnitPriceScale() 
    {
        return this.accruedInterestCalcCondRow.getUnitPriceScale();
    }
    
    /**
     * (get単位未満処理区分)<BR>
     * 経過利子計算条件Rowから単位未満処理区分を取得する。<BR>
     * @@return String
     * @@roseuid 44F394630196
     */
    public String getUnitRoundDiv() 
    {
        return this.accruedInterestCalcCondRow.getUnitRoundDiv();
    }
    
    /**
     * (get金額未満処理区分)<BR>
     * 経過利子計算条件Rowから金額未満処理区分を取得する。<BR>
     * @@return String
     * @@roseuid 44F394890223
     */
    public String getAmountRoundDiv() 
    {
        return this.accruedInterestCalcCondRow.getAmountRoundDiv();
    }
    
    /**
     * (get経過利子計算タイプ )<BR>
     * 経過利子計算条件Rowから経過利子計算タイプを取得する。
     * @@return String
     */
    public String getAccruedInterestCalcType()
    {
        return this.accruedInterestCalcCondRow.getAccruedInterestCalcType();
    }
}
@
