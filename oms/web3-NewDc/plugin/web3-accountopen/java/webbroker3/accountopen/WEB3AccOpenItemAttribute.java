head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenItemAttribute.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設項目属性(WEB3AccOpenItemAttribute.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張威 (中訊) 新規作成
*/

package webbroker3.accountopen;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.data.AccOpenItemAttributeRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設項目属性)<BR>
 * 口座開設項目属性<BR>
 *                                                              
 * @@author 張威
 * @@version 1.0
 */
public class WEB3AccOpenItemAttribute 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenItemAttribute.class);

    /**
     * (口座開設項目属性List)<BR>
     * 口座開設項目属性行（：口座開設項目属性Params）のリスト<BR>
     * <BR>
     * ※ 口座開設項目属性ParamsクラスはDDLより自動生成する。<BR>
     */
    private List accOpenItemAttributeParamsList;
    
    /**
     * (口座開設項目属性)<BR>
     * コンストラクタ。<BR>
     * 口座開設項目属性を生成する。<BR>
     * <BR>
     * 以下の条件で口座開設項目属性テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@口座開設項目属性.証券会社コード = 証券会社コード And<BR>
     * 　@口座開設項目属性.部店コード = 部店コード And<BR>
     * 　@口座開設項目マスタ.口座区分 = 口座区分 And<BR>
     * 　@口座開設項目属性.項目物理名 = 項目物理名<BR>
     * <BR>
     * 検索結果リストをプロパティにセットしたインスタンスを生成する。 <BR>
     * <BR>
     * ※ 口座開設項目属性ParamsクラスはDDLより自動生成する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strMainAccountType - 口座区分
     * 
     * @@param l_strColumnName - 項目物理名<BR>
     * <BR>
     * ※「口座開設見込客テーブル」の列物理名。<BR>
     * 
     * @@return webbroker3.accountopen.WEB3AccOpenItemAttribute
     * @@roseuid 4187518602E1
     */
    public WEB3AccOpenItemAttribute(String l_strInstitutionCode, String l_strBranchCode,
            String l_strMainAccountType, String l_strColumnName) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenItemAttribute(String, String, String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        //Q&A:WEB3-ACCOUNTOPEN-A-UT-0022:コンストラクタの引数の型の変更(MainAccountTypeEnum　@⇒　@String)をお願いします。
        
        String l_strWhere = " institution_code = ? and branch_code = ? and account_div = ? and item_symbol_name = ? ";
        
        Object[] l_obj = {l_strInstitutionCode, l_strBranchCode, l_strMainAccountType, l_strColumnName};
        
        try
        {
            this.accOpenItemAttributeParamsList = Processors.getDefaultProcessor().doFindAllQuery(AccOpenItemAttributeRow.TYPE, l_strWhere, l_obj); //DataQueryException, DataNetworkException
            
            if (this.accOpenItemAttributeParamsList == null || this.accOpenItemAttributeParamsList.size() == 0)
            {
                throw new NotFoundException("検索結果に一致する行が存在しない");
            }
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate有効コード値)<BR>
     * 項目が有効なコード値であるかの判定を行う。<BR>
     * <BR>
     * ※ 有効コード値チェック以外の有効値チェック（数字のみ，等）は、<BR>
     * 　@　@口座開設項目属性#validate有効値()にて実施。<BR>
     * <BR>
     * １）　@未入力の場合（項目値 == null）は、trueを返却する。<BR>
     * <BR>
     * ２）　@有効コード値チェック<BR>
     * 　@this.attributeValues()にて、属性値の配列を取得する。<BR>
     * <BR>
     * 　@取得した属性値[]内に、項目値と一致する要素が存在する場合はtrue、<BR>
     * 　@存在しない場合はfalseを返却する。<BR>
     * @@param l_strItemValue - 項目値
     * @@return boolean
     * @@roseuid 41874FF2032F
     */
    public boolean validateValidCodeValue(String l_strItemValue) 
    {
        final String STR_METHOD_NAME = " validateValidCodeValue(String)";
        
        log.entering(STR_METHOD_NAME);
        
        if (l_strItemValue == null)
        {
            log.debug("未入力の場合（項目値 == null）は、trueを返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        String[] l_strAttributeValues = this.attributeValues();
        
        int l_intLength = l_strAttributeValues.length;
        
        for (int i = 0; i < l_intLength; i++)
        {
            if (l_strItemValue.equals(l_strAttributeValues[i]))
            {
                log.debug("取得した属性値[]内に、項目値と一致する要素が存在する場合はtrueを返却する。");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        log.debug("取得した属性値[]内に、項目値と一致する要素が存在しない場合はfalseを返却する。");
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (attributeNames)<BR>
     * this.口座開設項目属性Listの各行オブジェクトより、項目属性名を<BR>
     * すべて取得し配列にて返却する。<BR>
     * @@return String[]
     * @@roseuid 418755230301
     */
    public String[] attributeNames() 
    {
        final String STR_METHOD_NAME = " attributeNames()";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        String[] l_strAttributeNames = new String[l_intLengh];
        for (int i = 0; i < l_intLengh; i++)
        {
            l_strAttributeNames[i] = ((AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i)).getAttributeName();            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_strAttributeNames;
    }
    
    /**
     * (attributeValues)<BR>
     * this.口座開設項目属性Listの各行オブジェクトより、項目属性値を<BR>
     * すべて取得し配列にて返却する。<BR>
     * @@return String[]
     * @@roseuid 4187545400BE
     */
    public String[] attributeValues() 
    {
        final String STR_METHOD_NAME = " attributeValues()";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        String[] l_strAttributeValues = new String[l_intLengh];
        for (int i = 0; i < l_intLengh; i++)
        {
            l_strAttributeValues[i] = ((AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i)).getAttributeValue();            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_strAttributeValues;
    }
    
    /**
     * (get下限値)<BR>
     * this.口座開設項目属性Listの各行オブジェクトより、<BR>
     * （項目属性値 == 引数の項目属性値）に該当する行の下限値を返却する。<BR>
     * <BR>
     * （項目属性値 == 引数の項目属性値）に該当する行が2件以上ある場合は、<BR>
     * データ不整合と判断し。例外をスローする。<BR>
     * @@param l_strItemAttributeValue - 項目属性値
     * @@return Double
     * @@roseuid 418755380264
     */
    public Double getRangeFrom(String l_strItemAttributeValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRangeFrom(String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        Double l_rangeFrom = null;
        
        int l_intLineCnt = 0;
        
        AccOpenItemAttributeRow l_itemAttributeRow = null;
        
        for (int i = 0; i < l_intLengh; i++)
        {
            if (this.attributeValues()[i].equals(l_strItemAttributeValue))
            {
                l_itemAttributeRow = (AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i);
                
                if (!l_itemAttributeRow.getRangeFromIsNull())
                {
                    l_rangeFrom = new Double(l_itemAttributeRow.getRangeFrom());
                }
                
                l_intLineCnt += 1;
            }
        }
        
        if (l_intLineCnt >= 2)
        {
            log.debug(STR_METHOD_NAME + "データ不整合");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ STR_METHOD_NAME,
                "該当する行が2件以上する");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_rangeFrom;
    }
    
    /**
     * (get上限値)<BR>
     * this.口座開設項目属性Listの各行オブジェクトより、<BR>
     * （項目属性値 == 引数の項目属性値）に該当する行の上限値を返却する。<BR>
     * <BR>
     * （項目属性値 == 引数の項目属性値）に該当する行が2件以上ある場合は、<BR>
     * データ不整合と判断し。例外をスローする。<BR>
     * @@param l_strItemAttributeValue - 項目属性値
     * @@return Double
     * @@roseuid 418755CE0003
     */
    public Double getRangeTo(String l_strItemAttributeValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRangeTo(String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intLengh = this.accOpenItemAttributeParamsList.size();
        
        Double l_rangeFrom = null;
        
        int l_intLineCnt = 0;

        AccOpenItemAttributeRow l_itemAttributeRow = null;
        
        for (int i = 0; i < l_intLengh; i++)
        {
            if (this.attributeValues()[i].equals(l_strItemAttributeValue))
            {
                l_itemAttributeRow = (AccOpenItemAttributeRow)this.accOpenItemAttributeParamsList.get(i);
                
                if (!l_itemAttributeRow.getRangeToIsNull())
                {
                    l_rangeFrom = new Double(l_itemAttributeRow.getRangeTo());
                }
                
                l_intLineCnt += 1;
            }
        }
        
        if (l_intLineCnt >= 2)
        {
            log.debug(STR_METHOD_NAME + "データ不整合");
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+ STR_METHOD_NAME,
                "該当する行が2件以上する");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_rangeFrom;
    }
}
@
