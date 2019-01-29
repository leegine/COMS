head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformItemProperty.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種連絡項目属性(WEB3InformItemProperty.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.inform.data.InformCtrlItemAttributeRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (各種連絡項目属性)<BR>
 * 各種連絡項目属性クラス<BR>
 */
public class WEB3InformItemProperty implements BusinessObject
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformItemProperty.class);
    /**
     * (各種連絡項目属性List)<BR>
     * 各種連絡項目属性行オブジェクト（各種連絡項目属性Params）のリスト<BR>
     */
    private List informCtrlItemAttributeList;


    /**
     * (各種連絡項目属性)<BR>
     * コンストラクタ<BR>
     * 各種連絡項目属性インスタンスを生成する。 <BR>
     * <BR>
     * １）以下の条件で、各種連絡項目属性テーブルからレコードを取得する。 <BR>
     * <BR>
     * [取得条件] <BR>
     * 証券会社コード = 引数.証券会社コード<BR>
     * 部店コード = 引数.部店コード<BR>
     * 連絡種別 = 引数.連絡種別<BR>
     * 項目物理名 = 引数.項目物理名<BR>
     * <BR>
     * ２）取得結果リストをプロパティにセットしたインスタンスを生成する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strInformDiv - (連絡種別)<BR>
     * 連絡種別<BR>
     * 
     * @@param l_strItemSymbolName - (項目物理名)<BR>
     * 項目物理名<BR>
     * @@roseuid 41BD41400351
     */
    public WEB3InformItemProperty(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strInformDiv,
        String l_strItemSymbolName) throws NotFoundException,WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3InformItemProperty";
        log.entering(STR_METHOD_NAME);
        //１）以下の条件で、各種連絡項目属性テーブルからレコードを取得する。 <BR>
        //
        //[取得条件] 
        //証券会社コード = 引数.証券会社コード
        //部店コード = 引数.部店コード
        //連絡種別 = 引数.連絡種別
        //項目物理名 = 引数.項目物理名
        //
        String l_strQuery = "institution_code = ? ";
        l_strQuery += " and branch_code = ?";
        l_strQuery += " and inform_div = ?";
        l_strQuery += " and item_symbol_name = ?";
        
        Object[] l_queryContainer = new Object[] {
            l_strInstitutionCode,
            l_strBranchCode,
            l_strInformDiv,
            l_strItemSymbolName};
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(            
                InformCtrlItemAttributeRow.TYPE,
                l_strQuery,
                l_queryContainer
                );  
            //２）取得結果リストをプロパティにセットしたインスタンスを生成する。
            log.exiting(STR_METHOD_NAME);
            if (l_lisRecords != null && l_lisRecords.size() != 0)
            {
                this.informCtrlItemAttributeList = new ArrayList();
                for (int i = 0;i < l_lisRecords.size();i++)
                {
                    this.informCtrlItemAttributeList.add(l_lisRecords.get(i));      
                }
            }
            else
            {
                throw new NotFoundException(STR_METHOD_NAME);
            }
     
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

    }

    /**
     * (validate有効コード値)<BR>
     * 項目が有効なコード値であるかの判定を行う。<BR>
     * <BR>
     * ※ 有効コード値チェック以外の有効値チェック（数字のみ，等）は、<BR>
     * 　@　@各種連絡項目属性#validate有効値()にて実施。<BR>
     * <BR>
     * １）　@未入力の場合（項目値 == null）は、trueを返却する。<BR>
     * <BR>
     * ２）　@有効コード値チェック<BR>
     * 　@this.attributeValues()にて、属性値の配列を取得する。<BR>
     * <BR>
     * 　@取得した属性値[]内に、項目値と一致する要素が存在する場合はtrue、<BR>
     * 　@存在しない場合はfalseを返却する。<BR>
     * @@param l_strItemValue - (項目値)<BR>
     * 項目値<BR>
     * 
     * @@return boolean
     * @@roseuid 41BD3805037F
     */
    public boolean validateEffectiveCodeValue(String l_strItemValue)
    {
        final String STR_METHOD_NAME = "validateEffectiveCodeValue(String l_strItemValue)";
        log.entering(STR_METHOD_NAME);
        //１）　@未入力の場合（項目値 == null）は、trueを返却する。
        //
        if (l_strItemValue == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //２）　@有効コード値チェック
        //　@this.attributeValues()にて、属性値の配列を取得する。
        //
        //　@取得した属性値[]内に、項目値と一致する要素が存在する場合はtrue、
        //　@存在しない場合はfalseを返却する。
        String[] l_strAttributeValues = this.attributeValues();
        if(this.attributeValues() == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        for (int i = 0;i < l_strAttributeValues.length;i++)
        {
            if (l_strItemValue.equals(l_strAttributeValues[i]))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * this.各種連絡項目属性Listの各行オブジェクトより、<BR>
     * 項目属性名をすべて取得し配列にて返却する。<BR>
     * @@return String[]
     * @@roseuid 41BD38160091
     */
    public String[] attributeNames()
    {
        final String STR_METHOD_NAME = "attributeNames()";
        log.entering(STR_METHOD_NAME);
        String[] l_strAttributeNames = null;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_size = this.informCtrlItemAttributeList.size();
            l_strAttributeNames = new String[l_size];
            for (int i = 0; i < l_size; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                l_strAttributeNames[i] = l_informCtrlItemAttribute.getAttributeName();
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_strAttributeNames;
    }

    /**
     * this.各種連絡項目属性Listの各行オブジェクトより、<BR>
     * 項目属性値をすべて取得し配列にて返却する。<BR>
     * @@return String[]
     * @@roseuid 41BD382501AB
     */
    public String[] attributeValues()
    {
        final String STR_METHOD_NAME = "attributeValues()";
        log.entering(STR_METHOD_NAME);
        String[] l_strAttributeValues = null;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_intSize = this.informCtrlItemAttributeList.size();
            l_strAttributeValues = new String[l_intSize];
            for (int i = 0; i < l_intSize; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                l_strAttributeValues[i] = l_informCtrlItemAttribute.getAttributeValue();
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_strAttributeValues;

    }

    /**
     * (get下限値)<BR>
     * this.各種連絡項目属性Listの各行オブジェクトより、<BR>
     * （項目属性値 == 引数の項目属性値）に該当する行の下限値を返却する。<BR>
     * <BR>
     * （項目属性値 == 引数の項目属性値）に該当する行が2件以上ある場合は、<BR>
     * 　@データ不整合と判断し。例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     * 　@tag: SYSTEM_ERROR_80006<BR>
     * @@param l_strItemPropertyValue - (項目属性値)<BR>
     * 項目属性値<BR>
     * 
     * @@return Double
     * @@roseuid 41BD3830018B
     */
    public Double getRangeFrom(String l_strItemPropertyValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRangeFrom(String l_strItemPropertyValue)";
        log.entering(STR_METHOD_NAME);
        int l_intNumber = 0;
        int l_intIndex = 0;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_size = this.informCtrlItemAttributeList.size();
            for (int i = 0; i < l_size; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                if (l_informCtrlItemAttribute.getAttributeValue().equals(l_strItemPropertyValue))
                {
                    l_intNumber++;  
                    l_intIndex = i;                  
                }
            }
            if (l_intNumber >= 2)
            {
                log.error("データ不整合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
            else 
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(l_intIndex);
                log.exiting(STR_METHOD_NAME);
                return new Double(l_informCtrlItemAttribute.getRangeFrom());
            }

        }
        log.exiting(STR_METHOD_NAME);
        return null;

    }

    /**
     * (get上限値)<BR>
     * this.各種連絡項目属性Listの各行オブジェクトより、<BR>
     * （項目属性値 == 引数の項目属性値）に該当する行の上限値を返却する。<BR>
     * <BR>
     * （項目属性値 == 引数の項目属性値）に該当する行が2件以上ある場合は、<BR>
     * タ不整合と判断し。例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     * 　@tag: SYSTEM_ERROR_80006<BR>
     * @@param l_strItemPropertyValue - (項目属性値)<BR>
     * 項目属性値<BR>
     * 
     * @@return Double
     * @@roseuid 41BD39320053
     */
    public Double getRangeTo(String l_strItemPropertyValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRangeTo(String l_strItemPropertyValue)";
        log.entering(STR_METHOD_NAME);
        int l_intNumber = 0;
        int l_intIndex = 0;
        if (this.informCtrlItemAttributeList != null)
        {
            int l_size = this.informCtrlItemAttributeList.size();
            for (int i = 0; i < l_size; i++)
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(i);
                if (l_informCtrlItemAttribute.getAttributeValue().equals(l_strItemPropertyValue))
                {
                    l_intNumber++;  
                    l_intIndex = i;                  
                }
            }
            if (l_intNumber >= 2)
            {
                log.error("データ不整合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                    this.getClass().getName() + STR_METHOD_NAME); 
            }
            else 
            {
                InformCtrlItemAttributeRow l_informCtrlItemAttribute = 
                    (InformCtrlItemAttributeRow)this.informCtrlItemAttributeList.get(l_intIndex);
                log.exiting(STR_METHOD_NAME);
                return new Double(l_informCtrlItemAttribute.getRangeTo());
            }

        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * @@return Object
     * @@roseuid 41EE642D007D
     */
    public Object getDataSourceObject()
    {
        return null;
    }
}
@
