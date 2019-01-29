head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenItemMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設項目マスタ(WEB3AccOpenItemMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/20 張威 (中訊) 新規作成
                   2007/03/12 岡安 (SCS) （モデル）122修正
*/

package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.data.AccOpenItemMasterDao;
import webbroker3.accountopen.data.AccOpenItemMasterParams;
import webbroker3.accountopen.data.AccOpenItemMasterRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ItemCheckModeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設項目マスタ)<BR>
 * 口座開設項目マスタ<BR>
 *                                                          
 * @@author 張威
 * @@version 1.0
 */
public class WEB3AccOpenItemMaster implements BusinessObject
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenItemMaster.class);

    /**
     * (口座開設項目マスタ行)<BR>
     * 口座開設項目マスタ行<BR>
     * <BR>
     * ※ 口座開設項目マスタParamsクラスはDDLより自動生成する。<BR>
     */
    private AccOpenItemMasterParams accOpenItemMasterParams;

    /**
     * (口座開設項目マスタ)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 指定行オブジェクトをプロパティにセットし、インスタンスを生成する。 <BR>
     * <BR>
     * ※ 口座開設項目マスタParamsクラスはDDLより自動生成する。<BR>
     * @@param l_accOpenItemMasterParams - 口座開設項目マスタ行オブジェクト
     *
     * @@return webbroker3.accountopen.WEB3AccOpenItemMaster
     * @@roseuid 418730B5012C
     */
    public WEB3AccOpenItemMaster(AccOpenItemMasterParams l_accOpenItemMasterParams)
    {
        this.accOpenItemMasterParams = l_accOpenItemMasterParams;
    }

    /**
     * (口座開設項目マスタ)<BR>
     * コンストラクタ。<BR>
     * 口座開設項目マスタを生成する。<BR>
     * <BR>
     * 以下の条件で口座開設項目マスタテーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@口座開設項目マスタ.証券会社コード = 証券会社コード And<BR>
     * 　@口座開設項目マスタ.部店コード = 部店コード And<BR>
     * 　@口座開設項目マスタ.口座区分 = 口座区分 And<BR>
     * 　@口座開設項目マスタ.チェックタイプ = チェックタイプ And<BR>
     * 　@口座開設項目マスタ.項目物理名 = 項目物理名<BR>
     * <BR>
     * 検索結果の口座開設項目マスタ行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountDiv - 口座区分<BR>
     * <BR>
     * 0：個人アカウント　@1：法@人アカウント<BR>
     *
     * @@param l_strValidateType - チェックタイプ<BR>
     * <BR>
     * 0：DEFAULT（顧客申込）　@<BR>
     * 1：管理者申込　@<BR>
     * 2：申込更新　@<BR>
     * 3：伝票作成<BR>
     *
     * @@param l_strItemSymbolName - 項目物理名<BR>
     * <BR>
     * ※「口座開設見込客テーブル」の列物理名。<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenItemMaster
     * @@roseuid 41872F7702A3
     */
    public WEB3AccOpenItemMaster(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountDiv, String l_strValidateType, String l_strItemSymbolName) throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenItemMaster(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AccOpenItemMasterRow l_row = AccOpenItemMasterDao.findRowByPk(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, l_strItemSymbolName); //DataNetworkException, DataQueryException
            
            this.accOpenItemMasterParams = new AccOpenItemMasterParams(l_row);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("検索結果に一致する行が存在しない");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.口座開設項目マスタ行を返却する。 <BR>
     * @@return Object
     * @@roseuid 41873856012C
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.accOpenItemMasterParams;   
    }

    /**
     * (is必須項目)<BR>
     * 必須項目かどうかを判定する。<BR>
     * <BR>
     * （this.口座開設項目マスタ行.必須項目フラグ == BooleanEnum.TRUE）の場合、true<BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4187355D0032
     */
    public boolean isNecessaryItem()
    {
        final String STR_METHOD_NAME = " isNecessaryItem()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenItemMasterParams.necessary_flag.equals(BooleanEnum.TRUE))
        {
            log.debug("（this.口座開設項目マスタ行.必須項目フラグ == BooleanEnum.TRUE）の場合");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.debug("（this.口座開設項目マスタ行.必須項目フラグ != BooleanEnum.TRUE）の場合");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get項目最小長)<BR>
     * 項目最小長を取得する。<BR>
     * <BR>
     * this.口座開設項目マスタ行.項目最小長を返却する。<BR>
     * @@return Integer
     * @@roseuid 418738B602B2
     */
    public Integer getItemMin()
    {
        final String STR_METHOD_NAME = " getItemMin()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenItemMasterParams.getItemMinIsNull())
        {
            log.debug("NULLを返却");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        int l_int = this.accOpenItemMasterParams.getItemMin();
        log.exiting(STR_METHOD_NAME);
        return new Integer(l_int);
    }

    /**
     * (get項目最大長)<BR>
     * 項目最大長を取得する。<BR>
     * <BR>
     * this.口座開設項目マスタ行.項目最大長を返却する。<BR>
     * @@return Integer
     * @@roseuid 4187394A0235
     */
    public Integer getItemMax()
    {
        final String STR_METHOD_NAME = " getItemMax()";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenItemMasterParams.getItemMaxIsNull())
        {
            log.debug("NULLを返却");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        int l_int = this.accOpenItemMasterParams.getItemMax();
        log.exiting(STR_METHOD_NAME);
        return new Integer(l_int);
    }

    /**
     * (get項目チェック方式)<BR>
     * 項目チェック方式を取得する。<BR>
     * <BR>
     * this.口座開設項目マスタ行.項目チェック方式を返却する。<BR>
     * @@return String
     * @@roseuid 41874B38015B
     */
    public String getItemCheckMode()
    {
        final String STR_METHOD_NAME = " getItemCheckMode()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.accOpenItemMasterParams.getItemCheckMode();
    }

    /**
     * (validate必須項目)<BR>
     * 必須項目に値が入力されているかの判定を行う。<BR>
     * <BR>
     * 必須項目チェック<BR>
     * 　@－必須項目で、値が入力されていない場合（this.is必須項目() == true && <BR>
     * 項目値 == null）、falseを返却する。<BR>
     * 　@－以外、trueを返却する。　@<BR>
     * @@param l_itemValue - 項目値
     * @@return boolean
     * @@roseuid 418733470090
     */
    public boolean validateNecessaryItem(Object l_itemValue)
    {
        final String STR_METHOD_NAME = " validateNecessaryItem(Object)";
        log.entering(STR_METHOD_NAME);
        
        if (this.isNecessaryItem() && l_itemValue == null)
        {
            log.debug("必須項目で、値が入力されていない場合、falseを返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.debug("trueを返却する。");
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate有効値)<BR>
     * 項目が有効な値であるかの判定を行う。<BR>
     * <BR>
     * ※ 有効コードチェック以外。<BR>
     * ※ 有効コードチェックは、口座開設項目属性#validate有効コード値()にて実施。<BR>
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
     * 　@　@－WEB3StringTypeUtility.isLetterOrDigit(項目値.toString())を返却する。<BR>
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
     * 　@　@－(*2) 以下の文字のみOK。 <BR>
     *　@　@　@全角カナ，半角カナ，全角英字，半角英字，全角数字，半角数字， <BR>
     *　@　@　@"-"，"ー"，"("，")"，"（"，"）"，"　@"，" "　@ <BR>
     *　@　@※参考）　@WEB3StringTypeUtility.isWbyteKanaString(項目値.toString()) <BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”メールアドレス”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isMailAddress(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”フラグ”）の場合<BR>
     * 　@　@－（項目値 == Boolean.TRUE || 項目値 == Boolean.FALSE）の場合true、<BR>
     * 以外はfalseを返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”郵便番号”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isZipCode(項目値.toString())を返却する。<BR>
     * <BR>
     * 　@○ （this.get項目チェック方式() == ”電話番号／携帯番号”）の場合<BR>
     * 　@　@－WEB3StringTypeUtility.isPhoneNumber(項目値.toString())を返却する。<BR>
     * @@param l_itemValue - 項目値
     * @@return boolean
     * @@roseuid 41873ACD0090
     */
    public boolean validateValidValue(Object l_itemValue)
    {
        final String STR_METHOD_NAME = " validateValidValue(Object)";
        log.entering(STR_METHOD_NAME);
        
        if (l_itemValue == null)
        {
            log.debug("１）　@未入力の場合（項目値 == null）は、trueを返却する。 ");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //２）　@有効値チェック 
        log.debug("２）　@有効値チェック ");
        if (WEB3ItemCheckModeDef.DEFAULT.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”チェックなし”）の場合、trueを返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (WEB3ItemCheckModeDef.HALF_NUMBER.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”半角数字のみ”）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isDigit(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.HALF_ALPHABET_NUMBER.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”半角英数字”）の場合");
            log.exiting(STR_METHOD_NAME);
//            return WEB3StringTypeUtility.isLetterAndDigit(l_itemValue.toString());
            return WEB3StringTypeUtility.isLetterOrDigit(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.HALF_ALPHABET.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”半角英字”）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isLetter(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.HALF_KANA.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”半角カナ”）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.is1byteKanaString(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.FULL_CHARACTER.equals(this.getItemCheckMode()))
        {
            return true;
//            log.debug("（this.get項目チェック方式() == ”全角文字”）の場合");
//            log.exiting(STR_METHOD_NAME);
//            return WEB3StringTypeUtility.isWbyteString(l_itemValue.toString());
        }
        
        //Q&A: WEB3-ACCOUNTOPEN-A-UT-0025 & モデル V1.4
        //仕様変更管理台帳 モデル No.017
        if (WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”住所／氏名カナ”）の場合");
            log.exiting(STR_METHOD_NAME);
            return this.isAddressGivenNameKana(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.MAIL_ADDRESS.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”メールアドレス”）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isMailAddress(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.BOOLEAN_FLAG.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”フラグ”）の場合");
            
            if (BooleanEnum.TRUE.equals(l_itemValue) || BooleanEnum.FALSE.equals(l_itemValue))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        if (WEB3ItemCheckModeDef.ZIP_CODE.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”郵便番号”）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isZipCode(l_itemValue.toString());
        }
        
        if (WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”電話番号／携帯番号”）の場合");
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.isPhoneNumber(l_itemValue.toString());
        }

        log.exiting(STR_METHOD_NAME);
        return true;
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
     * 　@　@最大長に指定がある場合（this.get項目最小長() != null）、<BR>
     * 　@　@最大長より大きいレングスであるかを判定する。<BR>
     * 　@　@　@－（this.get項目最大長 < 項目値.toString.length）の場合falseを返却する。<BR>
     * @@param l_itemValue - 項目値
     * @@return boolean
     * @@roseuid 41873B0D010D
     */
    public boolean validateLength(Object l_itemValue)
    {
        final String STR_METHOD_NAME = " validateLength(Object)";
        log.entering(STR_METHOD_NAME);
        
        if (l_itemValue == null)
        {
            log.debug("１）　@未入力の場合（項目値 == null）は、trueを返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (!(l_itemValue instanceof String))
        {
            log.debug("２）項目値のデータ型がStringでない場合、trueを返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("３）　@項目レングスチェック　@※項目値がString型の場合のみ実施");
            if (this.getItemMin() != null && this.getItemMin().intValue() > WEB3StringTypeUtility.getByteLength(l_itemValue.toString()))
            {
                log.debug("（this.get項目最小長 > 項目値.toString.length）の場合falseを返却する。");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            
            if (this.getItemMax() != null && this.getItemMax().intValue() < WEB3StringTypeUtility.getByteLength(l_itemValue.toString()))
            {
                log.debug("（this.get項目最大長 < 項目値.toString.length）の場合falseを返却する。");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        
        log.exiting(STR_METHOD_NAME); 
        return true;
    }

    /**
     * (is有効コードチェック)<BR>
     * 有効コード値チェックを行うかを判定する。<BR>
     * <BR>
     * （this.get項目チェック方式() == ”有効ｺｰﾄﾞﾁｪｯｸ”）の場合true、<BR>
     * 以外falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4193568902B6
     */
    public boolean isValidCodeCheck()
    {
        final String STR_METHOD_NAME = " isValidCodeCheck()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3ItemCheckModeDef.VALID_CODE_CHECK.equals(this.getItemCheckMode()))
        {
            log.debug("（this.get項目チェック方式() == ”有効ｺｰﾄﾞﾁｪｯｸ”）の場合trueを返却する");
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.debug("（this.get項目チェック方式() != ”有効ｺｰﾄﾞﾁｪｯｸ”）の場合falseを返却する");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (getDefault項目マスタ)<BR>
     * 指定の値で口座開設項目マスタオブジェクトを生成する。<BR>
     * <BR>
     * 口座開設項目マスタParamsを生成し、以下の通りプロパティをセットし返却する。<BR>
     * <BR>
     * 　@必須項目フラグ = 必須項目フラグ<BR>
     * 　@項目最小長 = 0　@<BR>
     * 　@項目最大長 = 項目最大長<BR>
     * 　@項目チェック方式 = 項目チェック方式<BR>
     * @@param l_necessaryItemFlag - TRUE／必須項目<BR>
     * FALSE／必須項目でない<BR>
     *
     * @@param l_intItemMax - 項目最大長
     * @@param l_strItemCheckMode - 項目チェック方式<BR>
     * <BR>
     * 00：DEFAULT（チェックなし）　@<BR>
     * 01：有効コード値チェック(*1)<BR>
     * 02：半角数字のみ　@<BR>
     * 03：半角英数字　@<BR>
     * 04：半角英字　@<BR>
     * 05：半角カナ　@<BR>
     * 06：全角文字　@<BR>
     * 07：住所／氏名カナ<BR>
     * 08：メールアドレス　@<BR>
     * 10：郵便番号　@<BR>
     * 11：電話／携帯番号　@<BR>
     * 12：年齢（20歳以上）<BR>
     * 13：フラグ（BooleanEnum.TRUE/FALSE）<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenItemMaster
     * @@roseuid 419359760064
     */
    public static WEB3AccOpenItemMaster getDefaultAccOpenItemMaster(BooleanEnum l_necessaryItemFlag, int l_intItemMax, String l_strItemCheckMode)
    {
        final String STR_METHOD_NAME = " getDefaultAccOpenItemMaster(BooleanEnum, int, String)";
        log.entering(STR_METHOD_NAME);
        
        //口座開設項目マスタParamsを生成
        AccOpenItemMasterParams l_accOpenItemMasterParams = new AccOpenItemMasterParams();
        l_accOpenItemMasterParams.setNecessaryFlag(l_necessaryItemFlag);
        l_accOpenItemMasterParams.setItemMin(0);
        l_accOpenItemMasterParams.setItemMax(l_intItemMax);
        l_accOpenItemMasterParams.setItemCheckMode(l_strItemCheckMode);
        
        WEB3AccOpenItemMaster l_accOpenItemMaster = new WEB3AccOpenItemMaster(l_accOpenItemMasterParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_accOpenItemMaster;
    }
    
    private boolean isAddressGivenNameKana(String l_strItemValue)
    {
        if (WEB3StringTypeUtility.isEmpty(l_strItemValue)) 
        {
            return false;
        }
        
        String l_strCheckValue = WEB3StringTypeUtility.convert(l_strItemValue);
        char[] l_chItemValues = l_strCheckValue.toCharArray();
        int l_intLength = l_chItemValues.length;
        
        for (int i = 0; i < l_intLength; i++)
        {
            //Bug U01650
            if (
                !(WEB3StringTypeUtility.isWbyteKanaChar(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.is1byteKanaChar(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.isWbyteEng(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.isSingleEng(l_chItemValues[i])|| 
                 WEB3StringTypeUtility.isWbyteNum(l_chItemValues[i]) || 
                 WEB3StringTypeUtility.isSingleNum(l_chItemValues[i])|| 
                 '-' == l_chItemValues[i] || 
                 'ー' == l_chItemValues[i] || 
                 '(' == l_chItemValues[i] || 
                 ')' == l_chItemValues[i] || 
                 '（' == l_chItemValues[i] || 
                 '）' == l_chItemValues[i] || 
                 '　@' == l_chItemValues[i] || 
                 ' ' == l_chItemValues[i] || 
                 '－' == l_chItemValues[i] ||
                 0xff0d == l_chItemValues[i]
                 )
                )
            {
                return false;
            }
        }
        
        return true;
    }
}
@
