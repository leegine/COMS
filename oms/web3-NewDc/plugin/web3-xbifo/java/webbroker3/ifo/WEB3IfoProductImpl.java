head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoProductImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP銘柄クラス(WEB3IfoProductImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 李強 新規作成
              001: 2004/07/22 王暁傑 (中訊) WEB3CommisionProductCodeDefでWEB3IfoCommissionProductCodeDefを差し替える
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;

//import webbroker3.ifo.define.WEB3IfoCommissionProductCodeDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
/**
 * 先物OP銘柄オブジェクト<BR>
 * 先物OP銘柄オブジェクトクラス<BR>
 *（DBレイアウト 「先物OP銘柄テーブル.xls」参照）<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3IfoProductImpl extends IfoProductImpl
{
    /**         
     * ログ出力ユーティリティ。         
     */         
    private static WEB3LogUtility log =         
        WEB3LogUtility.getInstance(
            WEB3IfoProductImpl.class);
          
    /**
     * 先物OP銘柄Row行オブジェクト<BR>
     * （自動生成DAOクラス）<BR>
     */
    private IfoProductRow futuresOptionProductRow; 
 
    
    /**
     * コンストラクタ。<BR>
     * 引数の銘柄ＩＤに一致する先物OP銘柄オブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値にて先物OP銘柄テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（先物OP銘柄Row）を<BR>
     * this.先物OP銘柄Rowにセットする。<BR>
     * @@param l_lngProductID - 銘柄ＩＤ
     * @@return webbroker3.ifo.WEB3IfoProductImpl
     * @@roseuid 405E7AA603D2
     */
    public WEB3IfoProductImpl(long l_lngProductID) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(IfoProductDao.findRowByPk(l_lngProductID)); 
        this.futuresOptionProductRow = 
            IfoProductDao.findRowByPk(l_lngProductID);            
    }
    
    public WEB3IfoProductImpl(ProductRow l_row) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(l_row); 
        this.futuresOptionProductRow =  
            IfoProductDao.findRowByPk(l_row.getProductId());   
    }

    public WEB3IfoProductImpl(IfoProductRow l_row) 
        throws DataFindException, DataQueryException, DataNetworkException 
    {   
        super(l_row); 
        this.futuresOptionProductRow = l_row;   
    }
            
    /**
     * (get手数料商品コード)<BR>
     * <BR>
     * 銘柄に該当する手数料商品コードを取得する。<BR>
     * <BR>
     * 本オブジェクト.先物オプション商品.が”先物”の場合<BR>
     * 　@－手数料商品コード.”50：先物”を返却する。<BR>
     * 本オブジェクト.先物オプション商品が”先物”でない場合<BR>
     * 　@－手数料商品コード.”51：株価指数ＯＰ”を返却する。<BR>
     * @@return String
     * @@roseuid 40518FE400D6
     */
    public String getCommissionProductCode()     
    {   
        final String METHOD_NAME = "getCommissionProductCode()";                                                                            
        log.entering(METHOD_NAME);  
        
        String l_strFutureOptionDiv =    
            this.futuresOptionProductRow.getFutureOptionDiv();      
        
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            log.exiting(METHOD_NAME);
            return  WEB3CommisionProductCodeDef.INDEX_FUTURES;
        }   
        else if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            log.exiting(METHOD_NAME);
            return WEB3CommisionProductCodeDef.INDEX_OP;
        }
        
        log.exiting(METHOD_NAME);
        return null;       
    }
    
    /**
     * (get銘柄コード)<BR>
     * @@return String
     * @@roseuid 4051977101D0
     */
    public String getProductCode()
    {
        return this.futuresOptionProductRow.getProductCode();
    }
    
    /**
     * (get指数マスタ)<BR>
     * <BR>
     * 銘柄に該当する指数マスタを取得する。<BR>
     * <BR>
     * 先物OP指数マスタクラスのコンストラクタに、当オブジェクトが保持する指数IDを引数で<BR>指定して生成する。<BR>
     * @@return webbroker3.ifo.WEB3IfoIndexMaster
     * @@roseuid 405E7A050389
     */
    public WEB3IfoIndexMaster getIndexMaster() 
        throws WEB3BaseException
    {
        final String METHOD_NAME = "getIndexMaster()";
        log.entering(METHOD_NAME);  
        
        try
        {
            long l_lngIndexId = 
                this.futuresOptionProductRow.getIndexId();                  
           
            log.exiting(METHOD_NAME);
            return new WEB3IfoIndexMaster(l_lngIndexId);   
        }
        catch (DataException l_de)
        {                            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.toString(),
                l_de);
        }
    }
    
    /**
     * this.先物OP指数銘柄Rowを返却する。
     * @@return Object
     * @@roseuid 405E7AA603D1
     */
    public Object getDataSourceObject() 
    {
        return this.futuresOptionProductRow;
    }
        
    /**
     * (get優先市場)<BR>
     * （getPrimaryMarketの実装）<BR>
     * <BR>
     * this.先物OP銘柄Row.対象市場ＩＤにて市場オブジェクトを生成し返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Market
     * @@roseuid 406916BA031C
     */
    public Market getPrimaryMarket()           
    {  
        long l_lngMarketId = 
            this.futuresOptionProductRow.getTargetMarketId();
        try
        {
            return new WEB3GentradeMarket(l_lngMarketId);       
        }
        catch(DataException l_ex)
        {           
            return null; 
        }
      
    }
    
    /**
     * (get原資産銘柄コード )<BR>
     * <BR>
     * this.先物OP銘柄Row.原資産銘柄コードを返却する。<BR>
     * @@return String
     * @@roseuid 407F997A0389
     */
    public String getUnderlyingProductCode() 
    {
        return this.futuresOptionProductRow.getUnderlyingProductCode();
    }
    
    /**
     * (isオプション銘柄)<BR>
     * オプション銘柄かを判定する。<BR>
     * <BR>
     * this.先物OP銘柄Row.先物／オプション区分 == ”オプション”の場合はtrueを返却する。<BR>
     * 以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 409996320388
     */
    public boolean isOptionProduct() 
    {        
        String l_strFutureOptionDiv =    
               this.futuresOptionProductRow.getFutureOptionDiv();               
       
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            return true;
        }
      
        return false;
       
       
    }
    
    /**
     * (is先物銘柄)<BR>
     * 先物銘柄かを判定する。<BR>
     * <BR>
     * this.先物OP銘柄Row.先物／オプション区分 == ”先物”の場合はtrueを返却する。<BR>
     * 以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 409996C8006B
     */
    public boolean isFuturesProduct() 
    {
        String l_strFutureOptionDiv =    
                  this.futuresOptionProductRow.getFutureOptionDiv();
                              
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv))
        {
            return true;
        }
        
        return false;
    }
}
@
