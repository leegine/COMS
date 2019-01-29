head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeCurrency.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （共通）通貨(WEB3GentradeCurrency.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/22 栄イ (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyPK;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (（共通）通貨)<BR>
 * （共通）通貨<BR>
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3GentradeCurrency implements BusinessObject
{
    /** 
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCurrency.class);
    
    /**
     * (（共通）通貨行)
     * （共通）通貨行オブジェクト<BR>
     */
    private GenCurrencyParams genCurrencyParams;
    
    /**
     * (get為替レート)<BR>
     * <BR>
     * 為替レートを取得する。<BR>
     * <BR>
     * （入力為替レート != 0）の場合、入力為替レートを返却する。 <BR>
     * 以外、以下の判定を行う。 <BR>
     * <BR>
     * ●　@買付約定の場合（is買付 == true && is約定計算 == true） <BR>
     * 　@get買付約定為替レート()の戻り値を返却する。 <BR>
     * <BR>
     * ●　@売付約定の場合（is買付 == false && is約定計算 == true） <BR>
     * 　@get売付約定為替レート()の戻り値を返却する。 <BR>
     * <BR>
     * ●　@買付注文の場合（is買付 == true && is約定計算 == false） <BR>
     * 　@get買付基準為替レート()の戻り値を返却する。 <BR>
     * <BR>
     * ●　@売付注文の場合（is買付 == false && is約定計算 == false） <BR>
     * 　@get売付基準為替レート()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_blnIsBuy  is買付<BR>
     * 買付かの判定 <BR>
     * <BR>
     * true：買 <BR>
     * false：売 <BR>
     * @@param l_blnIsExec  is約定計算<BR>
     * 約定計算かの判定 <BR>
     * <BR>
     * true：約定計算 <BR>
     * false：概算計算<BR>
     * @@param l_dblInputExchangeRate  入力為替レート<BR>
     * 入力為替レート（※0指定可）<BR>
     * @@return double 
     */
    public double getExchangeRate(
        boolean l_blnIsBuy, 
        boolean l_blnIsExec, 
        double l_dblInputExchangeRate)
    {
        final String STR_METHOD_NAME = "getExchangeRate(boolean, boolean, double)";
        log.entering(STR_METHOD_NAME);
        
        //（入力為替レート != 0）の場合、入力為替レートを返却する。
        if(l_dblInputExchangeRate != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblInputExchangeRate;
        }
        
        //以外、以下の判定を行う。
        
        //買付約定の場合（is買付 == true && is約定計算 == true）
        //get買付約定為替レート()の戻り値を返却する。
        if(l_blnIsBuy && l_blnIsExec)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getBuyExecRate();
        }
            
        //売付約定の場合（is買付 == false && is約定計算 == true）
        //get売付約定為替レート()の戻り値を返却する。
        if(!l_blnIsBuy && l_blnIsExec)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getSellExecRate();
        }
           
        //買付注文の場合（is買付 == true && is約定計算 == false）
        //get買付基準為替レート()の戻り値を返却する。
        if(l_blnIsBuy && !l_blnIsExec)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getBuyBaseRate();
        }
            
        //売付注文の場合（is買付 == false && is約定計算 == false）
        //get売付基準為替レート()の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return this.getSellBaseRate();
    }
    
    /**
     * (get円貨換算丸め方式)<BR>
     * <BR>
     * 円貨換算丸め方式を取得する。 <BR>
     * <BR>
     * this.（共通）通貨行.円貨換算丸め方式を返却する。 <BR>
     * <BR>
     * @@return String 
     */
    public String getChangeJpyRoundDiv()
    {
        final String STR_METHOD_NAME = "getChangeJpyRoundDiv()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.円貨換算丸め方式を返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getChangeJpyRoundDiv();
    }
    
    /**
     * (get外貨換算丸め方式)<BR>
     * <BR>
     * 外貨換算丸め方式を取得する。 <BR>
     * <BR>
     * this.（共通）通貨行.外貨換算丸め方式を返却する。 <BR>
     * <BR>
     * @@return String 
     */
    public String getChangeFCcyRoundDiv()
    {
        final String STR_METHOD_NAME = "getChangeFCcyRoundDiv()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.外貨換算丸め方式を返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getChangeFCcyRoundDiv();
    }
    
    /**
     * (get小数部桁数)<BR>
     * <BR>
     * 小数部桁数を取得する。 <BR>
     * <BR>
     * this.（共通）通貨行.小数部桁数を返却する。 <BR>
     * <BR>
     * @@return int 
     */
    public int getScale()
    {
        final String STR_METHOD_NAME = "getScale()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.小数部桁数を返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getScale();
    }
    
    /**
     * (get証券会社コード)<BR>
     * <BR>
     * 証券会社コードを取得する。 <BR>
     * <BR>
     * this.（共通）通貨行.証券会社コードを返却する。 <BR>
     * <BR>
     * @@return String 
     */
    public String getInstitutionCode()
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.証券会社コードを返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getInstitutionCode();
    }
    
    /**
     * (get通貨名)<BR>
     * <BR>
     * 通貨名を取得する。 <BR>
     * <BR>
     * this.（共通）通貨行.通貨名を返却する。 <BR>
     * <BR>
     * @@return String 
     */
    public String getCurrencyName()
    {
        final String STR_METHOD_NAME = "getCurrencyName()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.通貨名を返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrencyName();
    }
    
    /**
     * (get通貨コード)<BR>
     * <BR>
     * 通貨コードを取得する。 <BR>
     * <BR>
     * this.(共通)通貨行.通貨コードを返却する。 <BR>
     * <BR>
     * @@return String 
     */
    public String getCurrencyCode()
    {
        final String STR_METHOD_NAME = "getCurrencyCode()";
        log.entering(STR_METHOD_NAME);
        
        //this.(共通)通貨行.通貨コードを返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrencyCode();
    }
    
    /**
     * (get買付基準為替レート)<BR>
     * <BR>
     * 買付基準為替レートを取得する。<BR>
     * <BR>
     * this.（共通）通貨行.今回買付為替レートを返却する <BR>
     * <BR>
     * @@return double 
     */
    public double getBuyBaseRate()
    {
        final String STR_METHOD_NAME = "getBuyBaseRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.今回買付為替レートを返却する
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentBuyRate();
    }
    
    /**
     * (get買付約定為替レート)<BR>
     * <BR>
     * 買付約定為替レートを取得する。<BR>
     * <BR>
     * this.（共通）通貨行.今回買付約定為替レートを返却する。<BR>
     * <BR>
     * @@return double 
     */
    public double getBuyExecRate()
    {
        final String STR_METHOD_NAME = "getBuyExecRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.今回買付約定為替レートを返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentBuyExecRate();
    }
    
    /**
     * (get売付基準為替レート)<BR>
     * <BR>
     * 売付基準為替レートを取得する。<BR>
     * <BR>
     * this.（共通）通貨行.今回売付為替レートを返却する。<BR>
     * <BR>
     * @@return double 
     */
    public double getSellBaseRate()
    {
        final String STR_METHOD_NAME = "getSellBaseRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.今回売付為替レートを返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentSellRate();
    }
    
    /**
     * (get売付約定為替レート)<BR>
     * <BR>
     * 売付約定為替レートを取得する。<BR>
     * <BR>
     * this.（共通）通貨行.今回売付約定為替レートを返却する。<BR>
     * <BR>
     * @@return double 
     */
    public double getSellExecRate()
    {
        final String STR_METHOD_NAME = "getSellExecRate()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行.今回売付約定為替レートを返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams.getCurrentSellExecRate();
    }
    
    /**
     * (（共通）通貨)<BR>
     * <BR>
     * （共通）通貨を生成する。 <BR>
     * <BR>
     * １）　@オブジェクト生成 <BR>
     * 　@（共通）通貨オブジェクトを生成する。 <BR>
     * <BR>
     * ２）　@（共通）通貨行プロパティのセット <BR>
     * 　@（共通）通貨行を生成したオブジェクトのプロパティにセットし、返却する。 <BR>
     * <BR>
     * ※ （共通）通貨ParamsクラスはDDLより自動生成する。<BR>
     * <BR>
     * @@param l_genCurrencyParams  （共通）通貨行<BR>
     * （共通）通貨行オブジェクト <BR>
     * ※（共通）通貨ParamsクラスはDDLより自動生成する<BR>
     * @@throws WEB3BaseException 
     */
    public WEB3GentradeCurrency(GenCurrencyParams l_genCurrencyParams) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeCurrency(GenCurrencyParams)";
        log.entering(STR_METHOD_NAME);
        
        if(l_genCurrencyParams == null)
        {
            log.error("（共通）通貨行オブジェクト = null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "（共通）通貨行オブジェクト = null");
        }
        
        //２）　@（共通）通貨行プロパティのセット
        //　@（共通）通貨行を生成したオブジェクトのプロパティにセットし、返却する。
        this.genCurrencyParams = l_genCurrencyParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (（共通）通貨)<BR>
     * <BR>
     * (staticメソッド) <BR>
     * <BR>
     * （共通）通貨を生成する。 <BR>
     * <BR>
     * 以下の条件で（共通）通貨テーブルを検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@（共通）通貨テーブル.証券会社コード = 引数.証券会社コード <BR>
     * 　@（共通）通貨テーブル.通貨コード = 引数.通貨コード <BR>
     * <BR>
     * 検索結果の（共通）通貨行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールし、 <BR>
     * （共通）通貨オブジェクトを生成する。 <BR>
     * 生成した（共通）通貨オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode  証券会社コード<BR>
     * @@param l_strCurrencyCode  通貨コード<BR>
     * @@throws WEB3BaseException 
     */
    public static WEB3GentradeCurrency genCurrency(
        String l_strInstitutionCode, 
        String l_strCurrencyCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "genCurrency(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //以下の条件で（共通）通貨テーブルを検索する。
        //　@[条件] 
        //　@（共通）通貨テーブル.証券会社コード = 引数.証券会社コード 
        //　@（共通）通貨テーブル.通貨コード = 引数.通貨コード
        GenCurrencyRow l_genCurrencyRow = null;
        try
        {
            GenCurrencyPK l_genCurrencyPK = new GenCurrencyPK(
                l_strInstitutionCode,
                l_strCurrencyCode);
            l_genCurrencyRow = GenCurrencyDao.findRowByPk(l_genCurrencyPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //検索結果の（共通）通貨行オブジェクトを引数に指定して、
        //コンストラクタをコールし、 
        //（共通）通貨オブジェクトを生成する。 
        //生成した（共通）通貨オブジェクトを返却する。
        GenCurrencyParams l_genCurrencyParams = new GenCurrencyParams(l_genCurrencyRow);
        WEB3GentradeCurrency l_gentradeCurrency = new WEB3GentradeCurrency(l_genCurrencyParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_gentradeCurrency;
    }
    
    /**
     * (get（共通）通貨)<BR>
     * <BR>
     * (staticメソッド) <BR>
     * <BR>
     * 指定された証券会社が登録している（共通）通貨をすべて取得する。 <BR>
     * <BR>
     * （共通）通貨テーブルを以下の条件で検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@（共通）通貨テーブル.証券会社コード = 引数.証券会社コード <BR>
     * <BR>
     * 該当行にて（共通）通貨オブジェクトを生成し、配列にて返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode  証券会社コード<BR>
     * @@return WEB3GentradeCurrency[] <BR>
     * @@throws WEB3BaseException
     */
    public static WEB3GentradeCurrency[] getGentradeCurrency(
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getGentradeCurrency(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //（共通）通貨テーブルを以下の条件で検索する。
        // 　@[条件] 
        //　@（共通）通貨テーブル.証券会社コード = 引数.証券会社コード
        List l_lstRecords = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_sbWhere = " institution_code = ? ";     //証券会社コード
            Object[] l_objWhere = {l_strInstitutionCode};
            l_lstRecords = l_processor.doFindAllQuery(
                GenCurrencyRow.TYPE,
                l_sbWhere,
                l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeCurrency.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //該当行にて（共通）通貨オブジェクトを生成し、配列にて返却する。
        WEB3GentradeCurrency[] l_gentradeCurrencys = 
            new WEB3GentradeCurrency[l_lstRecords.size()];
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            GenCurrencyParams l_genCurrencyParams = 
                new GenCurrencyParams((GenCurrencyRow)l_lstRecords.get(i));
            l_gentradeCurrencys[i] =
                new WEB3GentradeCurrency(l_genCurrencyParams);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_gentradeCurrencys;
    }
    
    /**
     * (getDataSourceObject)<BR>
     * <BR>
     * getDataSourceObjectの実装。<BR>
     * <BR>
     * this.（共通）通貨行を返却する。<BR>
     * <BR>
     * @@return Object 
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = "getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        //this.（共通）通貨行を返却する。
        log.exiting(STR_METHOD_NAME);
        return this.genCurrencyParams;
    }
    
    /**
     * (get通貨コード一覧)<BR>
     * <BR>
     * （staticメソッド） <BR>
     * <BR>
     * 指定した証券会社が登録している通貨の通貨コードをすべて取得する。 <BR>
     * <BR>
     * １）get（共通）通貨（）を呼び出し、通貨オブジェクトの配列を取得する。<BR> 
     * <BR>
     * ２）通貨オブジェクトの配列をLoopする。 <BR>
     * 　@２－１）通貨オブジェクトの配列から通貨コードを取得する。 <BR>
     * 　@２－２）通貨コードの配列に通貨コードを追加する。 <BR>
     * <BR>
     * ３）通貨コードの配列を返す。<BR>
     * <BR>
     * @@param l_strInstitutionCode  証券会社コード<BR>
     * @@return String[] <BR>
     * @@throws WEB3BaseException
     */
    public static String[] getCurrencyCodeList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrencyCodeList(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）get（共通）通貨（）を呼び出し、通貨オブジェクトの配列を取得する。
        WEB3GentradeCurrency[] l_gentradeCurrencys = 
            WEB3GentradeCurrency.getGentradeCurrency(l_strInstitutionCode);
        
        //２）通貨オブジェクトの配列をLoopする。 
        //　@２－１）通貨オブジェクトの配列から通貨コードを取得する。
        //　@２－２）通貨コードの配列に通貨コードを追加する。
        int l_intLength = l_gentradeCurrencys.length;
        String[] l_strCurrencyCodes = new String[l_intLength];
        for(int i = 0; i < l_intLength; i++)
        {
            l_strCurrencyCodes[i] = l_gentradeCurrencys[i].getCurrencyCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCurrencyCodes;
    }
}
@
