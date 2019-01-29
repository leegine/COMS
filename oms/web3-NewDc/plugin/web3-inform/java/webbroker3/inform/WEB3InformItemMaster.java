head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformItemMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種連絡項目マスタ(WEB3InformItemMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
Revesion History : 2007/6/05 武波 (中訊) モデルNo.055、No.068
Revesion History : 2007/6/14 周墨洋 (中訊) モデルNo.086
*/
package webbroker3.inform;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ItemCheckModeDef;
import webbroker3.inform.data.InformCtrlItemMasterParams;
import webbroker3.inform.data.InformCtrlItemMasterRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (各種連絡項目マスタ)<BR>
 * 各種連絡項目マスタクラス<BR>
 */
public class WEB3InformItemMaster implements BusinessObject
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformItemMaster.class);

    /**
     * (各種連絡項目マスタ行)<BR>
     * 各種連絡項目マスタ行オブジェクト<BR>
     */
    private InformCtrlItemMasterParams informCtrlItemMasterParams;

    /**
     * @@roseuid 41EE642C02EE
     */
    public WEB3InformItemMaster()
    {

    }

    /**
     * (各種連絡項目マスタ)<BR>
     * コンストラクタ<BR>
     * 各種連絡項目マスタインスタンスを生成する。<BR>
     * <BR>
     * １）以下の条件で、各種連絡項目マスタテーブルからレコードを取得する。<BR>
     * <BR>
     * [取得条件]<BR>
     * 証券会社コード： 引数.証券会社コード<BR>
     * 部店コード： 引数.部店コード<BR>
     * 連絡種別： 引数.連絡種別<BR>
     * 項目物理名： 引数.項目物理名<BR>
     * 
     * ２）取得した行オブジェクトをthis.各種連絡項目行にセットする。
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
     * @@roseuid 41BD3EE800B1
     */
    public WEB3InformItemMaster(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strInformDiv,
        String l_strItemSymbolName) throws NotFoundException,WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3InformItemMaster";
        log.entering(STR_METHOD_NAME);
        try
        {
          
            String l_strQuery = "institution_code = ? ";
            l_strQuery += " and branch_code = ?";
            l_strQuery += " and inform_div = ?";
            l_strQuery += " and item_symbol_name = ?";
        
            Object[] l_queryContainer = new Object[] {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strInformDiv,
                l_strItemSymbolName};
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                InformCtrlItemMasterRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                throw new NotFoundException(STR_METHOD_NAME);
            }
            InformCtrlItemMasterParams l_params = (InformCtrlItemMasterParams)l_lisRecords.get(0); 
            this.informCtrlItemMasterParams = new InformCtrlItemMasterParams(l_params);
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


        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (get項目最小長)<BR>
     * 項目最小長を取得する。<BR>
     * <BR>
     * this.各種連絡項目マスタ行.項目最小長を返却する。<BR>
     * @@return Integer
     * @@roseuid 41BD336E0082
     */
    public Integer getItemMin()
    {     
        return this.informCtrlItemMasterParams.item_min;
    }

    /**
     * (get項目最大長)<BR>
     * 項目最大長を取得する。<BR>
     * <BR>
     * this.各種連絡項目マスタ行.項目最大長を返却する。<BR>
     * @@return Integer
     * @@roseuid 41BD33A902C4
     */
    public Integer getItemMax()
    {
        return this.informCtrlItemMasterParams.item_max;
    }

    /**
     * (get項目チェック方式)<BR>
     * 項目チェック方式を取得する。<BR>
     * <BR>
     * this.各種連絡項目マスタ行.項目チェック方式を返却する。<BR>
     * @@return String
     * @@roseuid 41BD33BF0302
     */
    public String getItemCheckMode()
    {
        return this.informCtrlItemMasterParams.item_check_mode;
    }

    /**
     * (validate必須項目)<BR>
     * 必須項目に値が入力されているかの判定を行う。<BR>
     * <BR>
     * 必須項目チェック<BR>
     * 　@－必須項目で、値が入力されていない場合（this.is必須項目() == true && 項目値 == null）、<BR>falseを返却する。<BR>
     * 　@－以外、trueを返却する。<BR>
     * @@param l_item - (項目値)<BR>
     * 項目値<BR>
     * 
     * @@return boolean
     * @@roseuid 41BD343A0043
     */
    public boolean validateNecessaryItem(Object l_item)
    {
        final String STR_METHOD_NAME = "validateNecessaryItem(Object l_item)";
        log.entering(STR_METHOD_NAME);
        if (this.isNecessaryItem() && l_item == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (validate有効値)<BR>
     * 項目が有効な値であるかの判定を行う。<BR>
     * <BR>
     * <BR>
     * １）　@未入力の場合（項目値 == null）は、trueを返却する。<BR>
     * <BR>
     * ２）　@有効値チェック<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”チェックなし”）の場合、trueを返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”半角数字のみ”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isDigit(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”半角英数字”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isLetterAndDigit(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”半角英字”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isLetter(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”半角カナ”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.is1byteKanaString(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”全角文字”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isWbyteString(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”住所／氏名カナ”）の場合<BR>
     * 　@　@　@　@－以下の文字のいずれかに該当するかとチェックし、結果を返却する。<BR>
     *       全角カナ，半角ｶﾅ，全角英字，半角英字，全角数字，半角数字，<BR>
     *       "-"，"ー"，"("，")"，"（"，"）"，"　@"，" "<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”メールアドレス”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isMailAddress(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”フラグ”）の場合<BR>
     * 　@　@－（項目値 == Boolean.TRUE || 項目値 == Boolean.FALSE）の場合true、<BR>以外はfalseを返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”郵便番号”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isZipCode(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”電話番号／携帯番号”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isPhoneNumber(項目値.toString())を返却する。<BR>
     * <BR>
     *   ※上記チェック方式以外の場合は、trueを返却する。<BR> 
     * @@param l_item - (項目値)<BR>
     * 項目値<BR>
     * @@return boolean
     * @@roseuid 41BD353201F9
     */
    public boolean validateEffectiveValue(Object l_item)
    {
        final String STR_METHOD_NAME = "validateEffectiveValue(Object l_item)";
        log.entering(STR_METHOD_NAME);

        //１）　@未入力の場合（項目値 == null）は、trueを返却する。<BR>
        if (l_item == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //２）　@有効値チェック
        //　@○ （this.get項目チェック方式() == ”チェックなし”）の場合、trueを返却する。
        if (WEB3ItemCheckModeDef.DEFAULT.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //　@○ （this.get項目チェック方式() == ”半角数字のみ”）の場合
        //　@　@－WEB3StringTypeUtility.isDigit(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.HALF_NUMBER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isDigit(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”半角英数字”）の場合
        //　@　@－WEB3StringTypeUtility.isLetterAndDigit(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.HALF_ALPHABET_NUMBER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isLetterAndDigit(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”半角英字”）の場合
        //　@　@－WEB3StringTypeUtility.isLetter(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.HALF_ALPHABET.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isLetter(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”半角カナ”）の場合
        //　@　@－WEB3StringTypeUtility.is1byteKanaString(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.HALF_KANA.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.is1byteKanaString(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”全角文字”）の場合
        //　@　@－WEB3StringTypeUtility.isWbyteString(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.FULL_CHARACTER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isWbyteString(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”住所／氏名カナ”）の場合
        //－以下の文字のいずれかに該当するかとチェックし、結果を返却する。<BR>
        //   全角カナ，半角ｶﾅ，全角英字，半角英字，全角数字，半角数字，<BR>
        //    "-"，"ー"，"("，")"，"（"，"）"，"　@"，" "
        else if (WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return this.isAddressGivenNameKana(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”メールアドレス”）の場合
        //　@　@－WEB3StringTypeUtility.isMailAddress(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.MAIL_ADDRESS.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isMailAddress(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”フラグ”）の場合
        //　@　@－（項目値 == Boolean.TRUE || 項目値 == Boolean.FALSE）の場合true、以外はfalseを返却する。
        else if (WEB3ItemCheckModeDef.BOOLEAN_FLAG.equals(this.getItemCheckMode()))
        {
            if (BooleanEnum.TRUE.stringValue().equals(l_item) || BooleanEnum.FALSE.stringValue().equals(l_item))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

        }

        //　@○ （this.get項目チェック方式() == ”郵便番号”）の場合
        //　@　@－WEB3StringTypeUtility.isZipCode(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.ZIP_CODE.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isZipCode(l_item.toString());
        }

        //　@○ （this.get項目チェック方式() == ”電話番号／携帯番号”）の場合
        //　@　@－WEB3StringTypeUtility.isPhoneNumber(項目値.toString())を返却する。
        else if (WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isPhoneNumber(l_item.toString());
        }

        //  ※上記チェック方式以外の場合は、trueを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (validateレングス)<BR>
     * 項目が有効な項目長の範囲内であるかを判定する。<BR>
     * <BR>
     * １）　@未入力の場合（項目値 == null）は、trueを返却する。<BR>
     * <BR>
     * ２）　@項目型の判定<BR>
     * 　@項目値のデータ型がStringでない場合、trueを返却する。<BR>
     * <BR>
     * ３）　@項目レングスチェック　@※項目値がString型の場合のみ実施<BR>
     * 　@３－１）　@最小長チェック<BR>
     * 　@　@最小長に指定がある場合（this.get項目最小長() != null）、<BR>
     * 　@　@最小長より大きいレングスであるかを判定する。<BR>
     * 　@　@　@－（this.get項目最小長 > 項目値.toString.length）の場合falseを返却する。<BR>
     * <BR>
     * 　@３－２）　@最大長チェック<BR>
     * 　@　@最大長に指定がある場合（this.get項目最大長() != null）、<BR>
     * 　@　@最大長より大きいレングスであるかを判定する。<BR>
     * 　@　@　@－（this.get項目最大長 (項目値.toString.length）の場合falseを返却する。<BR>
     * @@param l_item - (項目値)<BR>
     * 項目値<BR>
     * 
     * @@return boolean
     * @@roseuid 41BD368B013D
     */
    public boolean validateLength(Object l_item)
    {
        final String STR_METHOD_NAME = "validateLength(Object l_item)";
        log.entering(STR_METHOD_NAME);
        //１）　@未入力の場合（項目値 == null）は、trueを返却する。
        //
        if (l_item == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //２）　@項目型の判定
        //　@項目値のデータ型がStringでない場合、trueを返却する。
        //
        else if (!(l_item instanceof String))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //３）　@項目レングスチェック　@※項目値がString型の場合のみ実施

        else
        {
            //　@３－１）　@最小長チェック
            //　@　@最小長に指定がある場合（this.get項目最小長() != null）、
            //　@　@最小長より大きいレングスであるかを判定する。
            //　@　@　@－（this.get項目最小長 > 項目値.toString.length）の場合falseを返却する。
            //
            if (this.getItemMin() != null)
            {
                if (this.getItemMin().compareTo(new Integer(l_item.toString().length())) > 0) 
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
            //　@３－２）　@最大長チェック
            //　@　@最大長に指定がある場合（this.get項目最大長() != null）、
            //　@　@最大長より大きいレングスであるかを判定する。
            //　@　@　@－（this.get項目最大長 (項目値.toString.length）の場合falseを返却する。
            if (this.getItemMax() != null)
            {
                if (this.getItemMax().compareTo(new Integer(l_item.toString().length())) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
            return true;
        }
//        log.entering(STR_METHOD_NAME);
//        return true;
    }

    /**
     * (is必須項目)<BR>
     * 必須項目かどうかを判定する。<BR>
     * <BR>
     * this.各種連絡項目マスタ行.必須項目フラグ == BooleanEnum.TRUE の場合、true<BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 41BD331E019B
     */
    public boolean isNecessaryItem()
    {
        final String STR_METHOD_NAME = "isNecessaryItem()";
        log.entering(STR_METHOD_NAME);
        if (this.informCtrlItemMasterParams.necessary_flag == BooleanEnum.TRUE.intValue())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        

    }

    /**
     * (is有効コードチェック)<BR>
     * 有効コード値チェックを行うかを判定する。<BR>
     * <BR>
     * （this.get項目チェック方式() == ”有効ｺｰﾄﾞﾁｪｯｸ”）の場合true、以外falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 41BD36FD0351
     */
    public boolean isEffectiveCodeCheck()
    {
        final String STR_METHOD_NAME = "isEffectiveCodeCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.VALID_CODE_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is銘柄チェック)<BR>
     * 銘柄チェックを行うかを判定する。<BR>
     * <BR>
     * （this.get項目チェック方式() == ”銘柄ﾁｪｯｸ”）の場合true、以外falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 41BD372E0053
     */
    public boolean isProductCheck()
    {
        final String STR_METHOD_NAME = "isProductCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.PRODUCT_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

    }

    /**
     * (getDefault項目マスタ)<BR>
     * 指定の値で各種連絡項目マスタオブジェクトを生成する。 <BR>
     * <BR>
     * 各種連絡項目マスタParamsを生成し、以下の通りプロパティをセットし返却する。 <BR>
     * <BR>
     * 　@必須項目フラグ = 引数.必須項目フラグ <BR>
     * 　@項目最小長 = 0　@ <BR>
     * 　@項目最大長 = 引数.項目最大長 <BR>
     * 　@項目チェック方式 = 引数.項目チェック方式<BR>
     * @@param l_blnNecessaryItemFlag - (必須項目フラグ)<BR>
     * 必須項目フラグ<BR>
     * 
     * @@param l_intItemMax - (項目最大長)<BR>
     * 項目最大長<BR>
     * 
     * @@param l_strItemCheckMode - (項目チェック方式)<BR>
     * 項目チェック方式<BR>
     * 
     * @@return WEB3InformItemMaster
     * @@roseuid 41BD3FF000D0
     */
    public static WEB3InformItemMaster getDefaultItemMaster(
        BooleanEnum l_blnNecessaryItemFlag,
        int l_intItemMax,
        String l_strItemCheckMode)
    {
        final String STR_METHOD_NAME = "getDefaultItemMaster";
        log.entering(STR_METHOD_NAME);
        WEB3InformItemMaster l_master = new WEB3InformItemMaster();
        l_master.informCtrlItemMasterParams = new InformCtrlItemMasterParams();
        
        l_master.informCtrlItemMasterParams.setNecessaryFlag(l_blnNecessaryItemFlag.intValue());
        l_master.informCtrlItemMasterParams.setItemMin(0);
        l_master.informCtrlItemMasterParams.setItemMax(l_intItemMax);
        l_master.informCtrlItemMasterParams.setItemCheckMode(l_strItemCheckMode);
        log.exiting(STR_METHOD_NAME);
        return l_master;
    }

    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.各種連絡項目マスタ行を返却する。<BR>
     * @@return Object
     * @@roseuid 41BD32900266
     */
    public Object getDataSourceObject()
    {
        return this.informCtrlItemMasterParams;
    }
    
    private boolean isAddressGivenNameKana(String l_strItemValue)
    {
        if (WEB3StringTypeUtility.isEmpty(l_strItemValue)) 
        {
            return false;
        }
        
        char[] l_chItemValues = l_strItemValue.toCharArray();
        int l_intLength = l_chItemValues.length;
        
        for (int i = 0; i < l_intLength; i++)
        {
            if (!(WEB3StringTypeUtility.isWbyteKanaChar(l_chItemValues[i]) || WEB3StringTypeUtility.is1byteKanaChar(l_chItemValues[i])
                || WEB3StringTypeUtility.isWbyteEng(l_chItemValues[i]) || WEB3StringTypeUtility.isSingleEng(l_chItemValues[i])
                || WEB3StringTypeUtility.isWbyteNum(l_chItemValues[i]) || WEB3StringTypeUtility.isSingleNum(l_chItemValues[i])
                || '-' == l_chItemValues[i] || 'ー' == l_chItemValues[i] || '(' == l_chItemValues[i] || ')' == l_chItemValues[i]
                || '（' == l_chItemValues[i] || '）' == l_chItemValues[i] || '　@' == l_chItemValues[i] || ' ' == l_chItemValues[i]))
            {
                return false;
            }
        }
        
        return true;
    }

    /**
     * (is投信銘柄チェック)<BR>
     * 投信銘柄チェックを行うかを判定する。<BR>
     * <BR>
     * （this.get項目チェック方式() == ”投信銘柄ﾁｪｯｸ”）の場合true、以外falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isMutualProductCheck()
    {
        final String STR_METHOD_NAME = "isMutualProductCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.MUTUALFUND_PRODUCT_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is債券銘柄チェック )<BR>
     * 債券銘柄チェックを行うかを判定する。<BR>
     * <BR>
     * （this.get項目チェック方式() == ”債券銘柄ﾁｪｯｸ”）の場合true、以外falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isBondProductCheck()
    {
        final String STR_METHOD_NAME = "isBondProductCheck()";
        log.entering(STR_METHOD_NAME);
        if (WEB3ItemCheckModeDef.BOND_PRODUCT_CHECK.equals(this.getItemCheckMode()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
