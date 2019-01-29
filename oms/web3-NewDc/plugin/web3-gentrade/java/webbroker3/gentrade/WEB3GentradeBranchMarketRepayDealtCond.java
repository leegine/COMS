head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBranchMarketRepayDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （部店市場弁済別）取扱条件(WEB3GentradeBranchMarketRepayDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/13 鄒政 (中訊) 新規作成
Revesion History : 2004/08/31 孟東 (中訊) get取扱可能弁済()を削除
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondDao;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （部店市場弁済別）取扱条件<BR>
 * 証券会社、部店、市場、弁済区分、弁済期限値毎 <BR>
 * の取扱可能条件を表現する。 <BR>
 * 信用取引等で使用。<BR>
 * <BR>
 * （DBレイアウト 「（部店市場弁済別）取扱条件.xls」参照）<BR>
 */
public class WEB3GentradeBranchMarketRepayDealtCond implements BusinessObject
{
    
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketRepayDealtCond.class);

    /**
     * （部店市場弁済別）取扱条件Row行オブジェクト<BR>
     * （DAO自動生成クラス）<BR>
     */
    private BranchMarketRepayDealtCondRow branchMarketRepayDealtCondRow;    

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、<BR>
     * 引数の行オブジェクトをプロパティにセットする。<BR>
     * @@param l_branchMarketRepayDealtCondRow - 
     * （部店市場弁済別）取扱条件・行オブジェクト<BR>
     * @@return WEB3GentradeBranchMarketRepayDealtCond
     * @@roseuid 40B137CA0231
     */
    public WEB3GentradeBranchMarketRepayDealtCond(BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow)
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBranchMarketRepayDealtCond(BranchMarketRepayDealtCondRow)";
        if(l_branchMarketRepayDealtCondRow == null)
        {
            log.error("（部店市場弁済別）取扱条件・行オブジェクト = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "（部店市場弁済別）取扱条件・行オブジェクト = null");
        }
        this.branchMarketRepayDealtCondRow = l_branchMarketRepayDealtCondRow;

    }

    /**
     * コンストラクタ。<BR>
     * 引数の条件に一致する（部店市場弁済別）取扱条件 <BR>
     * オブジェクトを返却する。 <BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@引数の値にて（部店市場弁済別）取扱条件テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（（部店市場弁済別）取扱条件Row）を<BR>
     *   this.（部店市場弁済別）取扱条件にセットする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@param l_strRepaymentDiv - 弁済区分<BR>
     *     1：制度信用  2：一般信用 <BR>
     * @@param l_dbRepaymentNum - 弁済期限値<BR>
     * 
     * @@return WEB3GentradeBranchMarketRepayDealtCond
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B137CA01F3
     */
    public WEB3GentradeBranchMarketRepayDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBranchMarketRepayDealtCond(String,String,String,String,double)";
        log.entering(STR_METHOD_NAME);

        long l_lngRepaymentNum = (long)l_dbRepaymentNum;
        int l_intRepaymentNum = Integer.parseInt(Long.toString(l_lngRepaymentNum));
        BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow;

        try
        {
            l_branchMarketRepayDealtCondRow = 
                BranchMarketRepayDealtCondDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strMarketCode,
                    l_strRepaymentDiv,
                    l_intRepaymentNum);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        this.branchMarketRepayDealtCondRow = l_branchMarketRepayDealtCondRow;
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * this.部店市場弁済別取扱条件Rowを返却する。<BR>
     * @@return Object
     * @@roseuid 40B137CA01E3
     */
    public Object getDataSourceObject()
    {
        return this.branchMarketRepayDealtCondRow;
    }

    /**
     * (is取扱可能)<BR>
     * 取扱可能であるかを返却する。<BR>
     * <BR>
     * this.（部店市場弁済別）取扱条件Rowのプロパティ「取扱可能」が、<BR>
     * ”取扱可能”であればtrue、以外falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 40B137CA0203
     */
    public boolean isHandlingPossible()
    {
        if(WEB3DealtDef.CAN_DEALT.equals(this.branchMarketRepayDealtCondRow.getMartCanDealt()))
        {
            return true;
        }
        return false;
    }

    /**
     * (get売買限度単位)<BR>
     * 引数指定値に応じて、売買限度単位を取得する。<BR>
     * <BR>
     * 　@以下の通り判定し、限度単位値を返却する。<BR>
     * <BR>
     * ○引数のis新規建＝true（新規建）　@かつ　@<BR>
     *    引数のis買建＝true（買）の場合 <BR>
     * 　@  －引数の上場区分＝一部上場の場合は、<BR>
     *       ”新規買建限度単位（一部上場）”を返却する。<BR>
     * 　@  －引数の上場区分＝二部上場の場合は、”新規 <BR>
     *        買建限度単位（二部上場）”を返却する。<BR>
     * 　@  －引数の上場区分が上記以外の場合は、”新規 <BR>
     *        買建限度単位（一部上場）”を返却する。<BR>
     * <BR>
     * ○引数のis新規建＝true（新規建）　@かつ　@<BR>
     *    引数のis買建＝false（売）の場合<BR>
     * 　@  －引数の上場区分＝一部上場の場合は、”新規 <BR>
     *        売建限度単位（一部上場）”を返却する。<BR>
     * 　@  －引数の上場区分＝二部上場の場合は、”新規売建 <BR>
     *        限度単位（二部上場）”を返却する。<BR>
     * 　@  －引数の上場区分が上記以外の場合は、”新規売建 <BR>
     *        限度単位（一部上場）”を返却する。<BR>
     * <BR>
     * ○引数のis新規建＝false（返済）　@かつ　@<BR>
     *    引数のis買建＝true（買）の場合 <BR>
     * 　@  －引数の上場区分＝一部上場の場合は、”買建 <BR>
     *        返済限度単位（一部上場）”を返却する。<BR>
     * 　@  －引数の上場区分＝二部上場の場合は、”買建 <BR>
     *        返済限度単位（二部上場）”を返却する。<BR>
     * 　@  －引数の上場区分が上記以外の場合は、”買建返済 <BR>
     *        限度単位（一部上場）”を返却する。<BR>
     * <BR>
     * ○引数のis新規建＝false（返済）　@かつ　@<BR>
     *    引数のis買建＝false（売）の場合 <BR>
     * 　@  －引数の上場区分＝一部上場の場合は、”売建 <BR>
     *        返済限度単位（一部上場）”を返却する。<BR>
     * 　@  －引数の上場区分＝二部上場の場合は、”売建 <BR>
     *        返済限度単位（二部上場）”を返却する。<BR>
     * 　@  －引数の上場区分が上記以外の場合は、”売建 <BR>
     *        返済限度単位（一部上場）”を返却する。<BR>
     * @@param l_strListingDivision - 上場区分<BR>
     *    株式取引銘柄マスタにて定義されている上場区分。<BR>
     * @@param l_blnIsOpenContract - is新規建<BR>
     *    新規建注文かどうかを判別するフラグ。<BR>
     *    新規建の場合はtrue、返済の場合はfalse。<BR>
     * @@param l_blnIsBuyOrder - is買建<BR>
     *    買建／売建を判別するフラグ。<BR>
     *    買建の場合はtrue、売建の場合はfalse。<BR>
     * 
     * @@return double
     * @@roseuid 40B137CA0222
     */
    public double getDealingLimitUnit(
        String l_strListingDivision,
        boolean l_blnIsOpenContract,
        boolean l_blnIsBuyOrder)
    {
        final String STR_METHOD_NAME = "getDealingLimitUnit(String,boolean,boolean)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblDealingLimitUnit = 0;
        
        //○引数のis新規建＝true（新規建）　@かつ　@引数のis買建＝true（買）の場合
        if(l_blnIsOpenContract && l_blnIsBuyOrder)
        {
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝一部上場の場合は、
                //”新規買建限度単位（一部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMLong1stSec();
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝二部上場の場合は、”新規 
                //買建限度単位（二部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMLong2ndSec();
                
            }
            else
            {
                //－引数の上場区分が上記以外の場合は、”新規 
                //買建限度単位（一部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMLong1stSec();
            }  
        }
        //○引数のis新規建＝true（新規建）　@かつ　@引数のis買建＝false（売）の場合
        else if(l_blnIsOpenContract && ( ! l_blnIsBuyOrder))
        {         
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝一部上場の場合は、”新規 
                //売建限度単位（一部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMShort1stSec(); 
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝二部上場の場合は、”新規売建 
                //限度単位（二部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMShort2ndSec(); 
            }
            else
            {
                //－引数の上場区分が上記以外の場合は、”新規売建 
                //限度単位（一部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitMShort1stSec(); 
            } 
        }
        //○引数のis新規建＝false（返済）　@かつ　@引数のis買建＝true（買）の場合
        else if(( ! l_blnIsOpenContract) && l_blnIsBuyOrder)
        {            
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝一部上場の場合は、”買建 
                //返済限度単位（一部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmLong1stSec(); 
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝二部上場の場合は、”買建 
                //返済限度単位（二部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmLong2ndSec(); 
            }
            else
            {
                //－引数の上場区分が上記以外の場合は、”買建返済 
                //限度単位（一部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmLong1stSec(); 
            }
            
        }
        //○引数のis新規建＝false（返済）　@かつ　@引数のis買建＝false（売）の場合
        else  if(( ! l_blnIsOpenContract) && ( ! l_blnIsBuyOrder))
        {
            if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝一部上場の場合は、”売建 
                //返済限度単位（一部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmShort1stSec(); 
            }
            else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
            {
                //－引数の上場区分＝二部上場の場合は、”売建 
                //返済限度単位（二部上場）”を返却する。
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmShort2ndSec(); 
            }
            else
            {
                //－引数の上場区分が上記以外の場合は、”売建 
                //返済限度単位（一部上場）”を返却する
                l_dblDealingLimitUnit = this.branchMarketRepayDealtCondRow.getLimitedUnitCmShort1stSec(); 
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblDealingLimitUnit;
    }

    /**
     * （staticメソッド）<BR>
     * get（部店市場弁済別）取扱条件<BR>
     * <BR>
     * 引数の部店に該当する（部店市場弁済別）取扱条件<BR>
     * オブジェクトを全て取得する。<BR>
     * ※市場コード、弁済区分、弁済期限値 昇順で取得する。<BR>
     * <BR>
     * １）DB検索 <BR>
     * 　@（部店市場弁済別）取扱条件テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 部店.getInstitution().getInstitutionCode()<BR>
     * 　@部店コード = 部店.getBranchCode()<BR>
     * 　@※市場コード、弁済区分、弁済期限値 昇順でソートして取得する。<BR>
     * <BR>
     * ２）オブジェクト生成 <BR>
     * 　@検索結果の（部店市場弁済別）取扱条件行オブジェクトを指定し、<BR>
     * 　@（部店市場弁済別）取扱条件オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを配列で返却する。<BR>
     * <BR>
     * @@param l_genBranch - 部店オブジェクト<BR>
     * @@return WEB3GentradeBranchMarketRepayDealtCond[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B137CA0241
     */
    public static WEB3GentradeBranchMarketRepayDealtCond[] getBranchMarketRepayDealtCond(
        WEB3GentradeBranch l_genBranch)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getBranchMarketRepayDealtCond(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);
        
        //１）DB検索 
        //* 　@（部店市場弁済別）取扱条件テーブルを以下の条件で検索する。
        //* 　@[条件]
        //* 　@証券会社コード = 部店.getInstitution().getInstitutionCode()
        //* 　@部店コード = 部店.getBranchCode()
        //* 　@※市場コード、弁済区分、弁済期限値 昇順でソートして取得する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        String l_strOrderBy = "to_number(market_code), repayment_div, repayment_num ";
        Object[] l_objWhere =
        {
            l_genBranch.getInstitution().getInstitutionCode(),
            l_genBranch.getBranchCode()
        };
        
        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                BranchMarketRepayDealtCondRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeBranchMarketRepayDealtCond.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）オブジェクト生成 
        //検索結果の（部店市場弁済別）取扱条件行オブジェクトを指定し、
        //（部店市場弁済別）取扱条件オブジェクトを生成する。
        //生成したオブジェクトを配列で返却する。
        int l_intSize  = l_lisRecords.size();
        WEB3GentradeBranchMarketRepayDealtCond[] l_genBranchMarketRepayDealtConds = 
            new WEB3GentradeBranchMarketRepayDealtCond[l_intSize];
        for(int i = 0; i < l_intSize; i++)
        {
            l_genBranchMarketRepayDealtConds[i] = 
                new WEB3GentradeBranchMarketRepayDealtCond((BranchMarketRepayDealtCondRow)l_lisRecords.get(i));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_genBranchMarketRepayDealtConds;
    }

    /**
     * (get市場コード)<BR>
     * 本オブジェクトが保持する市場コードを取得する。<BR>
     * <BR>
     * this.（部店市場弁済別）取扱条件Row.市場コードを返却する。<BR>
     * @@return java.lang.String
     * @@roseuid 40B137CA0260
     */
    public String getMarketCode()
    {
        return this.branchMarketRepayDealtCondRow.getMarketCode();
    }

    /**
     * （staticメソッド）<BR>
     * (get取扱可能市場)<BR>
     * 引数の部店に該当する（部店市場弁済別）取扱条件オブジェクト<BR>
     * を全て取得し、 引数の弁済区分(*)、弁済期限値(*)に該当する<BR>
     * オブジェクトの市場コードの配列を返却する。<BR> 
     * ※配列の順序は、市場コード昇順とする。<BR>
     *  <BR>
     * (*)弁済区分、弁済期限値は指定時のみチェック内容に追加する。<BR>
     *    弁済期限値のみの指定は不可。<BR>
     *  <BR>
     * １）引数チェック <BR>
     * 引数.弁済区分==”DEFAULT（指定なし）”、かつ、<BR>
     * 引数.弁済期限値!=”DEFAULT（指定なし）”の場合、<BR> 
     * 「引数指定エラー」の例外をthrowする。<BR>
     * class    : WEB3BaseRuntimeException<BR>
     * tag      : SYSTEM_ERROR_80017<BR> 
     *  <BR> 
     * ２）データ取得 <BR> 
     * this.get（部店市場弁済別）取扱条件(部店)により、<BR>  
     * 引数の部店に該当する（部店市場弁済別）取扱条件 <BR> 
     * オブジェクトを全て取得する。 <BR>
     *  <BR>
     * ３）ArrayListを生成する。<BR>
     *  <BR>
     * ４）取扱可能チェック <BR>
     *   ２）で取得したオブジェクト数分、以下のチェックを行い市場コード<BR>
     *   をArrayListに追加する。 <BR>
     *  <BR>
     * [チェック内容] <BR>
     * ---------------------------------------------------<BR> 
     * ４－１）this.is取扱可能() == trueであること。 <BR>
     *  <BR>
     * ４－２）以下の条件によりチェックを分岐する。 <BR>
     *    [パラメータ.弁済区分!=”DEFAULT（指定なし）”の場合] <BR>
     *      （部店市場弁済別）取扱条件.弁済区分 != パラメータ.弁済区分の<BR>
     *      場合、次のオブジェクトへ処理を移行する。(continue) <BR> 
     *    [パラメータ.弁済期限値!=”DEFAULT（指定なし）”の場合] <BR>
     *      （部店市場弁済別）取扱条件.弁済期限値 != パラメータ.弁済期限値<BR>
     *      の場合、次のオブジェクトへ処理を移行する。(continue) <BR>
     * ----------------------------------------------------<BR> 
     *  <BR>
     * ４－３）上記チェックを通過したオブジェクトについて、以下の処理を <BR>
     * 実施する。生成したArrayListに市場コードを追加する。<BR> 
     * ※ArrayListに追加する市場コードは、重複しないもののみをセットすること。<BR> 
     *  <BR>
     * ５）作成したArrayList.toArray()の結果を返却する。 <BR> 
     *  <BR> 
     * @@param l_genBranch - 部店オブジェクト<BR>
     * @@param l_strRepaymentDiv - 弁済区分<BR>
     *   (WEB3GentradeRepaymentDivDefにて定義) <BR>
     * @@param l_dbRepaymentNum - 弁済期限値<BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40B137CA0270
     */
    public static String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getHandlingPossibleMarket(WEB3GentradeBranch, String, double)";
        log.entering(STR_METHOD_NAME);
     
        //１）引数チェック 
        //* 引数.弁済区分==”DEFAULT（指定なし）”、かつ、
        //* 引数.弁済期限値!=”DEFAULT（指定なし）”の場合、
        //* 「引数指定エラー」の例外をthrowする
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv) 
           && (Double.compare(l_dbRepaymentNum,0) != 0))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeBranchMarketRepayDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                "引数.弁済区分 = " + l_strRepaymentDiv 
                    + ", 引数.弁済期限値 = " + l_dbRepaymentNum);
        }
        
        //２）データ取得
        //this.get（部店市場弁済別）取扱条件(部店)により、
        //引数の部店に該当する（部店市場弁済別）取扱条件
        //オブジェクトを全て取得する。
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds = 
            getBranchMarketRepayDealtCond(l_genBranch);
        
        //４）取扱可能チェック
        WEB3GentradeBranchMarketRepayDealtCond l_tmpBranchMarketRepayDealtCond;
        BranchMarketRepayDealtCondRow l_tmpBranchMarketRepayDealtCondRow;
        String l_strTmpMarketCode;
        ArrayList l_lstHandlingPossibleMarkets = new ArrayList();
        int l_intSize  = l_branchMarketRepayDealtConds.length;
        for(int i = 0; i<l_intSize; i++ )
        {
            //get（部店市場弁済別）取扱条件オブジェクト
            l_tmpBranchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];
            l_tmpBranchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_tmpBranchMarketRepayDealtCond.getDataSourceObject();           
            
            //４－１）this.is取扱可能() == trueであること
            if(l_tmpBranchMarketRepayDealtCond.isHandlingPossible())
            {
                //４－２）以下の条件によりチェックを分岐する。 
                //*    [パラメータ.弁済区分!=”DEFAULT（指定なし）”の場合] 
                //*      （部店市場弁済別）取扱条件.弁済区分 != パラメータ.弁済区分の
                //*      場合、次のオブジェクトへ処理を移行する。(continue) 
                //*    [パラメータ.弁済期限値!=”DEFAULT（指定なし）”の場合] 
                //*      （部店市場弁済別）取扱条件.弁済期限値 != パラメータ.弁済期限値
                //*      の場合、次のオブジェクトへ処理を移行する。(continue) 
                if( ! WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv))
                {
                    if( ! l_strRepaymentDiv.equals(l_tmpBranchMarketRepayDealtCondRow.getRepaymentDiv()))
                    {
                        continue;
                    }
                }
                if(Double.compare(l_dbRepaymentNum,0) != 0)
                {
                    if(Double.compare(l_dbRepaymentNum,l_tmpBranchMarketRepayDealtCondRow.getRepaymentNum()) != 0)
                    {
                        continue;
                    }
                }
                
                //４－３）生成したArrayListに市場コードを追加する
                //ArrayListに追加する市場コードは、重複しないもののみをセットすること。
                l_strTmpMarketCode = l_tmpBranchMarketRepayDealtCond.getMarketCode();
                if( ! l_lstHandlingPossibleMarkets.contains(l_strTmpMarketCode))
                {
                    l_lstHandlingPossibleMarkets.add(l_strTmpMarketCode);
                }
                
            }
        }
        
        //５）作成したArrayListを配列して返す。
        l_intSize = l_lstHandlingPossibleMarkets.size();
        String[] l_strMarketCodes = new String[l_intSize];
        for(int i = 0; i<l_intSize; i++ )
        {
            l_strMarketCodes[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }
    
    /**
     *  (get取引中取扱可能市場)<BR>
     * （staticメソッド）<BR>
     * 引数の部店に該当する（部店市場弁済別）取扱条件オブジェクトを<BR>
     * 全て取得し、引数の弁済区分(*)、弁済期限値(*)に該当する<BR>
     * オブジェクトの市場コードのうち、取引中の市場の市場コードのみの<BR>
     * 配列を返却する。<BR>
     * ※配列の順序は、市場コード昇順とする。<BR>
     *  <BR>
     * (*)弁済区分、弁済期限値は指定時のみチェック内容に追加する。<BR>
     * 弁済期限値のみの指定は不可。<BR>
     *  <BR>
     * １）引数チェック<BR>
     * 引数.弁済区分==”DEFAULT（指定なし）”、かつ、<BR>
     * 引数.弁済期限値!=”DEFAULT（指定なし）”の場合、<BR>
     * 「引数指定エラー」の例外をthrowする。<BR>
     *  <BR>
     * ２）データ取得<BR>
     * this.get（部店市場弁済別）取扱条件(部店)により、引数の部店に<BR>
     * 該当する（部店市場弁済別）取扱条件オブジェクトを全て取得する。<BR>
     *  <BR>
     * ３）ArrayListを生成する。<BR>
     *  <BR>
     * ４）取扱可能チェック<BR>
     *  ２）で取得したオブジェクト数分、以下のチェックを行い市場コードを<BR>
     *  ArrayListに追加する。<BR>
     *  <BR>
     * [チェック内容]
     * ----------------------------------------------------<BR>
     * ４－１）this.is取扱可能() == trueであること。<BR>
     *  <BR>
     * ４－２）以下の条件によりチェックを分岐する。<BR>
     *   [パラメータ.弁済区分!=”DEFAULT（指定なし）”の場合]<BR>
     *      （部店市場弁済別）取扱条件.弁済区分 != パラメータ.弁済区分<BR>
     *       の場合、次のオブジェクトへ処理を移行する。(continue)<BR>
     *  <BR>
     *   [パラメータ.弁済期限値!=”DEFAULT（指定なし）”の場合]<BR>
     *      （部店市場弁済別）取扱条件.弁済期限値 != パラメータ.弁済期限値<BR>
     *       の場合、次のオブジェクトへ処理を移行する。(continue)<BR>
     *  <BR>
     * ４－３）取引中の市場(*1)であること。<BR>
     *  <BR>
     * (*1)取引中の市場<BR>
     * 拡張金融オブジェクトマネージャ.get市場(部店.証券会社コード, <BR>
     * 当該レコードの市場コード)により市場オブジェクトを取得。取得した<BR>
     * 市場.取引停止≠"取引停止中"の場合は、取引中の市場と判定する。<BR>
     * ※市場オブジェクトが取得できなかった場合は、取引中の市場ではないと<BR>
     * 判定する。<BR>
     * ----------------------------------------------------<BR>
     *  <BR>
     * ４－４）上記チェックを通過したオブジェクトについて、以下の処理を<BR>
     * 実施する。生成したArrayListに市場コードを追加する。<BR>
     * ※ArrayListに追加する市場コードは、重複しないもののみをセットすること<BR>
     *  <BR>
     * ５）作成したArrayList.toArray()の結果を返却する。<BR>
     * <BR>
     * @@param l_genBranch - 部店オブジェクト<BR>
     * @@param l_strRepaymentDiv - 弁済区分<BR>
     *   (WEB3GentradeRepaymentDivDefにて定義) <BR>
     * @@param l_dbRepaymentNum - 弁済期限値<BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     */
    public static String[] getTradingHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        String l_strRepaymentDiv,
        double l_dbRepaymentNum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getTradingHandlingPossibleMarket(WEB3GentradeBranch, String, double)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_genFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //１）引数チェック 
        //* 引数.弁済区分==”DEFAULT（指定なし）”、かつ、
        //* 引数.弁済期限値!=”DEFAULT（指定なし）”の場合、
        //* 「引数指定エラー」の例外をthrowする
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv) 
           && (Double.compare(l_dbRepaymentNum,0) != 0))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeBranchMarketRepayDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                "引数.弁済区分 = " + l_strRepaymentDiv 
                    + ", 引数.弁済期限値 = " + l_dbRepaymentNum);
        }
        
        //２）データ取得
        //this.get（部店市場弁済別）取扱条件(部店)により、
        //引数の部店に該当する（部店市場弁済別）取扱条件
        //オブジェクトを全て取得する。
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds = 
            getBranchMarketRepayDealtCond(l_genBranch);
        
        //４）取扱可能チェック
        //    ２）で取得したオブジェクト数分、以下のチェックを行い市場コードをArrayListに追加する。
        WEB3GentradeBranchMarketRepayDealtCond l_tmpBranchMarketRepayDealtCond;
        BranchMarketRepayDealtCondRow l_tmpBranchMarketRepayDealtCondRow;
        String l_strTmpMarketCode;
        ArrayList l_lstHandlingPossibleMarkets = new ArrayList();
        int l_intSize  = l_branchMarketRepayDealtConds.length;
        for(int i = 0; i<l_intSize; i++ )
        {
            //get（部店市場弁済別）取扱条件オブジェクト
            l_tmpBranchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];
            l_tmpBranchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_tmpBranchMarketRepayDealtCond.getDataSourceObject();  
                
            //４－１）this.is取扱可能() == trueであること
            if(l_tmpBranchMarketRepayDealtCond.isHandlingPossible() == false)
            {
                continue;
            }
            
            //４－２）以下の条件によりチェックを分岐する。 
            //*    [パラメータ.弁済区分!=”DEFAULT（指定なし）”の場合] 
            //*      （部店市場弁済別）取扱条件.弁済区分 != パラメータ.弁済区分の
            //*      場合、次のオブジェクトへ処理を移行する。(continue) 
            //*    [パラメータ.弁済期限値!=”DEFAULT（指定なし）”の場合] 
            //*      （部店市場弁済別）取扱条件.弁済期限値 != パラメータ.弁済期限値
            //*      の場合、次のオブジェクトへ処理を移行する。(continue) 
            if( ! WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentDiv))
            {
                if( ! l_strRepaymentDiv.equals(l_tmpBranchMarketRepayDealtCondRow.getRepaymentDiv()))
                {
                    continue;
                }
            }
            if(Double.compare(l_dbRepaymentNum,0) != 0)
            {
                if(Double.compare(l_dbRepaymentNum,l_tmpBranchMarketRepayDealtCondRow.getRepaymentNum()) != 0)
                {
                    continue;
                }
            }
            
            //４－３）取引中の市場(*1)であること。
            //(*1)取引中の市場
            //拡張金融オブジェクトマネージャ.get市場(部店.証券会社コード, 
            //当該レコードの市場コード)により市場オブジェクトを取得。取得した
            //市場.取引停止≠"取引停止中"の場合は、取引中の市場と判定する。
            //※市場オブジェクトが取得できなかった場合は、取引中の市場ではないと判定する
            Market l_market = null;
            try
            {
                l_market =
                    l_genFinObjectManager.getMarket(
                        l_genBranch.getInstitution().getInstitutionCode(),
                        l_tmpBranchMarketRepayDealtCond.getMarketCode());
            }
            catch (NotFoundException nfe)
            {
                log.debug("市場オブジェクトが取得できなかった場合は、取引中の市場ではないと判定する");
            }
            boolean l_isTradingMarket = false;
            if(l_market != null)
            {
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                if(WEB3SuspensionDef.SUSPENSION.equals(l_marketRow.getSuspension()) == false)
                {
                    l_isTradingMarket = true;
                }
            }
            if(l_isTradingMarket == false)
            {
                continue;
            }
            
            //４－４）生成したArrayListに市場コードを追加する
            //ArrayListに追加する市場コードは、重複しないもののみをセットすること。
            l_strTmpMarketCode = l_tmpBranchMarketRepayDealtCond.getMarketCode();
            if( ! l_lstHandlingPossibleMarkets.contains(l_strTmpMarketCode))
            {
                l_lstHandlingPossibleMarkets.add(l_strTmpMarketCode);
            }
            
        }
        
        //５）作成したArrayListを配列して返す。
        l_intSize = l_lstHandlingPossibleMarkets.size();
        String[] l_strMarketCodes = new String[l_intSize];
        for(int i = 0; i<l_intSize; i++ )
        {
            l_strMarketCodes[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
        
    }
    
}
@
