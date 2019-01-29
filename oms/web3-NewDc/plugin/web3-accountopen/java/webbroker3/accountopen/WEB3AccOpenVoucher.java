head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucher.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票(WEB3AccOpenVoucher.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 李頴淵 新規作成
                   2007/06/14 岡安 (SCS) 実装の問題 No.004
Revesion History : 2009/08/28 張騰宇 モデル194
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenVoucherItemParams;
import webbroker3.accountopen.data.AccOpenVoucherItemRow;
import webbroker3.accountopen.data.AccOpenVoucherMasterParams;
import webbroker3.accountopen.data.AccOpenVoucherMasterRow;
import webbroker3.accountopen.data.AccOpenVoucherStatusDao;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EditWayDivDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設伝票)<BR>
 * 口座開設伝票<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0 
 */
public abstract class WEB3AccOpenVoucher implements BusinessObject 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenVoucher.class);
    
    /**
     * (口座開設伝票マスタ行)<BR>
     * 口座開設伝票マスタ行オブジェクトの配列<BR>
     * <BR>
     * ※ 口座開設伝票マスタParamsクラスはDDLより自動生成する。<BR>
     */
    protected AccOpenVoucherMasterParams[] accOpenVoucherMasterParamses;
    
    /**
     * (口座開設見込客)<BR>
     * 口座開設見込客オブジェクト<BR>
     */
    protected WEB3AccOpenExpAccountOpen accOpenExpAccountOpen;
    
    /**
     * @@roseuid 41B45E6E03B9
     */
    public WEB3AccOpenVoucher() 
    {
     
    }
    
    /**
     * (isオンライン伝票)<BR>
     * オンライン伝票かを判定する。<BR>
     * @@return boolean
     * @@roseuid 4191C8840217
     */
    public abstract boolean isOnlineVoucher();
    
    /**
     * (getデータコード)<BR>
     * データコードを取得する。<BR>
     * @@return String
     * @@roseuid 4191C71F02B3
     */
    public abstract String getRequestCode();
    
    /**
     * (get伝票コード)<BR>
     * 伝票コードを取得する。<BR>
     * @@return String
     * @@roseuid 419DDD35021A
     */
    public abstract String getVoucherCode();
    
    /**
     * (get確定済項目名)<BR>
     * 当該伝票で使用している口座開設見込客の列物理名を配列で取得する。<BR>
     * @@return String[]
     * @@roseuid 4192E8960323
     */
    public abstract String[] getConfirmedItemName() throws WEB3BaseException;
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * <BR>
     * this.口座開設見込客.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 4191C7840081
     */
    public String getInstitutionCode() 
    {
        final String STR_METHOD_NAME = " getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }
    
    /**
     * (get部店コード)<BR>
     * 部店コードを取得する。<BR>
     * <BR>
     * this.口座開設見込客.部店コードを返却する。<BR>
     * @@return String
     * @@roseuid 41932DD900A3
     */
    public String getBranchCode() 
    {
        final String STR_METHOD_NAME = " getBranchCode()";
        log.entering(STR_METHOD_NAME);
        String l_strBranchCode = this.accOpenExpAccountOpen.getBranchCode();
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * 口座区分を取得する。<BR> 
     * <BR>
     * this.口座開設見込客.口座区分を返却する。<BR> 
     * @@return String
     * @@roseuid 41B144EC01D4
     */
    public String getAccountDiv() 
    {
        final String STR_METHOD_NAME = " getAccountDiv()";
        log.entering(STR_METHOD_NAME);
        String l_strAccountDiv = this.accOpenExpAccountOpen.getAccountDiv();
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountDiv;
    }
    
    /**
     * (get識別コード)<BR>
     * 識別コード（口座開設見込客）を取得する。<BR>
     * <BR>
     * this.口座開設見込客.識別コードを返却する。<BR>
     * @@return String
     * @@roseuid 419DD7410065
     */
    public String getRequestNumber() 
    {
        final String STR_METHOD_NAME = " getRequestNumber()";
        log.entering(STR_METHOD_NAME);
        String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestNumber;
    }
    
    /**
     * (getユーザデータ)<BR>
     * ユーザデータ領域にセットする項目を取得する。<BR>
     * <BR>
     * nullを返却する。<BR>
     * <BR>
     * ※ ユーザデータ領域を使用する伝票のみoverrideする。<BR>
     * @@return String
     * @@roseuid 4191C7C00033
     */
    public String getUserData() 
    {
        final String STR_METHOD_NAME = " getUserData()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (getカスタマイズ参照項目)<BR>
     * 伝票項目に出力する値をが格納された、口座開設見込客テーブルの<BR>
     * 列物理名を取得する。<BR>
     * <BR>
     * １）　@口座開設伝票項目マスタ読み込み<BR>
     * 　@口座開設伝票項目マスタテーブルを以下の条件①@で検索する。<BR>
     * 　@該当行がない場合、条件②で検索する。<BR>
     * <BR>
     * 　@[条件①@]<BR>
     * 　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR>
     * 　@口座開設伝票項目マスタ.部店コード = this.get部店コード() And<BR>
     * 　@口座開設伝票項目マスタ.データコード = this.getデータコード()<BR>
     * 　@口座開設伝票項目マスタ.伝票通番 = 伝票通番 And<BR>
     * 　@口座開設伝票項目マスタ.出力項目物理名 = 伝票出力項目物理名<BR>
     * <BR>
     * 　@[条件②]<BR>
     * 　@口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And<BR>
     * 　@口座開設伝票項目マスタ.部店コード = this.get部店コード() And<BR>
     * 　@口座開設伝票項目マスタ.データコード = this.getデータコード()<BR>
     * 　@口座開設伝票項目マスタ.伝票通番 = 伝票通番 And<BR>
     * 　@口座開設伝票項目マスタ.出力項目物理名 = 伝票出力項目物理名<BR>
     * <BR>
     * ２）　@入力項目物理名セット <BR>
     * 　@○ 伝票項目にカスタマイズが入っている場合（１）で該当行が存在した場合）の処理。 <BR>
     * <BR>
     * 　@　@－見込客テーブルより値を取得する項目であれば、項目値を配列で返却する。 (*) <BR>
     * 　@　@　@※ 但し、項目値がnullのものは除く <BR>
     * <BR>
     * 　@　@(*) 見込客テーブルより値を取得する項目の判定 <BR>
     * 　@　@　@（口座開設伝票項目マスタ.項目編集方法@ == <BR>
     * 　@　@　@　@　@1：口座開設見込客テーブルの項目より編集） または、 <BR>
     * 　@　@　@（口座開設伝票項目マスタ.項目編集方法@ == <BR>
     * 　@　@　@　@　@2：口座開設見込客テーブルの項目を半角ｶﾅに変換して編集） の場合、 <BR>
     * 　@　@　@以下の項目値を配列で返却する。 <BR>
     * <BR>
     * 　@　@　@　@　@口座開設伝票項目マスタ.入力項目物理名１ <BR>
     * 　@　@　@　@　@口座開設伝票項目マスタ.入力項目物理名２ <BR>
     * 　@　@　@　@　@口座開設伝票項目マスタ.入力項目物理名３ <BR>
     * <BR>
     * 　@　@　@（口座開設伝票項目マスタ.項目編集方法@ == 3：和暦日付を西暦日付に変換） の場合、 <BR>
     * 　@　@　@以下の項目値を配列で返却する。 <BR>
     * <BR>
     * 　@　@　@　@　@口座開設伝票項目マスタ.入力項目物理名１ <BR>
     * 　@　@　@　@　@口座開設伝票項目マスタ.入力項目物理名２ <BR>
     * <BR>
     * 　@　@－以外、nullを返却する。<BR>
     * <BR>
     * 　@○ 伝票項目にカスタマイズが入っていない場合（１）で該当行が存在しない場合）<BR>
     * 　@　@引数の伝票参照項目初期値[]を返却する。<BR>
     * @@param l_strSerialNo - 伝票通番
     * @@param l_strVoucherOutputItemName - 伝票出力項目物理列名
     * @@param l_strVoucherRefItemInitValues - 伝票参照項目初期値の配列
     * 
     * @@return String[]
     * @@roseuid 4192EF140101
     */
    public String[] getCustomizingRefItem(String l_strSerialNo, String l_strVoucherOutputItemName, String[] l_strVoucherRefItemInitValues) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCustomizingRefItem(String, String, String[])";
        log.entering(STR_METHOD_NAME);
        //１）　@口座開設伝票項目マスタ読み込み
        try
        {   
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            
            //[条件①@] 
            String l_strWhere =
                "institution_code = ? and " +        //口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And 
                "branch_code = ? and " +             //口座開設伝票項目マスタ.部店コード = this.get部店コード() And 
                "request_code = ? and " +            //口座開設伝票項目マスタ.データコード = this.getデータコード() 
                "serial_no = ? and " +               //口座開設伝票項目マスタ.伝票通番 = 伝票通番 And 
                "output_item_symbol_name = ? ";      //口座開設伝票項目マスタ.出力項目物理名 = 伝票出力項目物理名
            
            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strBranchCode = this.getBranchCode();
            String l_strRequestCode = this.getRequestCode();

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strBranchCode,
                 l_strRequestCode,
                 l_strSerialNo,
                 l_strVoucherOutputItemName};
                    
            List l_lisRows = null;
            log.debug("[条件①@] ");
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherItemRow.TYPE,
                    l_strWhere,
                    l_bindVars);
                     
            //該当行がない場合、条件②で検索する。 
            if (l_lisRows.size() == 0 || l_lisRows == null)
            {
                //[条件②]  
                String l_strWhere2 =
                    "institution_code = ? and " +        //口座開設伝票項目マスタ.証券会社コード = this.get証券会社コード() And 
                    "branch_code = ? and " +             //口座開設伝票項目マスタ.部店コード = ”000” And 
                    "request_code = ? and " +            //口座開設伝票項目マスタ.データコード = this.getデータコード() 
                    "serial_no = ? and " +               //口座開設伝票項目マスタ.伝票通番 = 伝票通番 And 
                    "output_item_symbol_name = ? ";      //口座開設伝票項目マスタ.出力項目物理名 = 伝票出力項目物理名

                Object l_bindVars2[] =
                    {l_strInstitutionCode,
                     "000",
                     l_strRequestCode,
                     l_strSerialNo,
                     l_strVoucherOutputItemName};
                    
                l_lisRows = null;
                log.debug("[条件②] ");
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherItemRow.TYPE,
                        l_strWhere2,
                        l_bindVars2);    
            }
            
            //伝票項目にカスタマイズが入っている場合（１）で該当行が存在した場合）の処理
            if (l_lisRows != null && l_lisRows.size() != 0)
            {
                log.debug("伝票項目にカスタマイズが入っている場合（１）で該当行が存在した場合");
                AccOpenVoucherItemParams l_accOpenVoucherItemParams = (AccOpenVoucherItemParams)l_lisRows.get(0);
                //見込客テーブルより値を取得する項目であれば(*)、以下の項目値を配列で返却する。
                if (WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM.equals(l_accOpenVoucherItemParams.getEditWayDiv()) ||
                    WEB3EditWayDivDef.EXP_ACCOUNT_OPEN_ITEM_TO_HALFKANA.equals(l_accOpenVoucherItemParams.getEditWayDiv()))
                {
                    log.debug("見込客テーブルより値を取得する項目であれば");
                    List l_lis = new ArrayList();
                    
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName1() != null)
                    {
                        log.debug("InputItemSymbolName1");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName1());    
                    }
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName2() != null)
                    {
                        log.debug("InputItemSymbolName2");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName2());    
                    }
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName3() != null)
                    {
                        log.debug("InputItemSymbolName3");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName3());    
                    }
                    // 但し、項目値がnullのものは除く
                    if (l_lis.size() != 0)
                    {
                        log.debug("項目値がnullのものは除く");
                        String[] l_str = new String[l_lis.size()];
                        l_lis.toArray(l_str);
                        log.exiting(STR_METHOD_NAME);
                        return l_str;
                    }
                    //以外、nullを返却する。
                    else
                    {
                        log.debug("以外、nullを返却する");
                        log.exiting(STR_METHOD_NAME);
                        return null;
                    }
                }
                else if (WEB3EditWayDivDef.WEST_DATE_CHANGE_TO_JAP_DATE.equals(l_accOpenVoucherItemParams.getEditWayDiv()))
                {
                    log.debug("見込客テーブルより値を取得する項目であれば");
                    List l_lis = new ArrayList();

                    if (l_accOpenVoucherItemParams.getInputItemSymbolName1() != null)
                    {
                        log.debug("InputItemSymbolName1");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName1());    
                    }
                    if (l_accOpenVoucherItemParams.getInputItemSymbolName2() != null)
                    {
                        log.debug("InputItemSymbolName2");
                        l_lis.add(l_accOpenVoucherItemParams.getInputItemSymbolName2());    
                    }
                    // 但し、項目値がnullのものは除く
                    if (l_lis.size() != 0)
                    {
                        log.debug("項目値がnullのものは除く");
                        String[] l_str = new String[l_lis.size()];
                        l_lis.toArray(l_str);
                        log.exiting(STR_METHOD_NAME);
                        return l_str;
                    }
                    //以外、nullを返却する。
                    else
                    {
                        log.debug("以外、nullを返却する");
                        log.exiting(STR_METHOD_NAME);
                        return null;
                    }
                }
                else
                {
                    log.debug("return null");
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
            }
            else
            {
                //引数の伝票参照項目初期値[]を返却する。
                log.debug("引数の伝票参照項目初期値[]を返却する");
                log.exiting(STR_METHOD_NAME);
                //U01000
                return l_strVoucherRefItemInitValues;
            }

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
    
    /**
     * (is伝票作成)<BR>
     * 当該伝票を作成するかを判定する。<BR>
     * <BR>
     * （this.口座開設伝票マスタ行[] == null）の場合、<BR>
     * 伝票作成が不要であると判定しfalseを返却する。<BR>
     * <BR>
     * 以外、trueを返却する。<BR>
     * @@param l_accOpenVoucherMasterParams - 口座開設伝票マスタ行オブジェクト<BR>
     * @@return boolean
     * @@roseuid 4192E2660110
     */
    public boolean isVoucherCreated() 
    {
        final String STR_METHOD_NAME = " isVoucherCreated()";
        log.entering(STR_METHOD_NAME);

        if (this.accOpenVoucherMasterParamses == null)
        {
            log.debug("口座開設伝票マスタ行[] == null");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.debug("口座開設伝票マスタ行[] != null");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (isValid伝票作成項目)<BR>
     * 当該口座開設見込客データの判定項目が、伝票作成条件に<BR>
     * 一致しているかを判定する。<BR>
     * <BR>
     * １）　@判定項目値取得<BR>
     * 　@項目による判定が必要ない場合<BR>
     * （引数の口座開設伝票マスタ行.伝票作成判定項目 == null）、trueを返却する。<BR>
     * <BR>
     * 　@以外、this.口座開設見込客.get項目値()にて、伝票作成判定項目の値を<BR>
     * 取得する。<BR>
     * <BR>
     * 　@[get項目値()に指定する引数]<BR>
     * 　@列物理名：　@this.口座開設伝票マスタ行.伝票作成判定項目<BR>
     * <BR>
     * ２）　@判定値と比較する<BR>
     * 　@引数の口座開設伝票マスタ行.伝票作成判定値と２－１）で取得した値が<BR>
     * 同一である場合、trueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_accOpenVoucherMasterParams - 口座開設伝票マスタ行オブジェクト<BR>
     * <BR>
     * ※ 口座開設伝票マスタParamsクラスはDDLより自動生成する。<BR>
     * 
     * @@return boolean
     * @@roseuid 4192D29B00F1
     */
    public boolean isValidVoucherCreatedItem(AccOpenVoucherMasterParams l_accOpenVoucherMasterParams) 
    {
        final String STR_METHOD_NAME = " isValidVoucherCreatedItem(AccOpenVoucherMasterParams)";
        log.entering(STR_METHOD_NAME);
        if (l_accOpenVoucherMasterParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);   
        }
        
        //１）　@判定項目値取得
        //引数の口座開設伝票マスタ行.伝票作成判定項目 == null）、trueを返却する
        if (l_accOpenVoucherMasterParams.getCreateVoucherColumn() == null)
        {
            log.debug("伝票作成判定項目 == null、trueを返却する");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            String l_strItemValue = null;

            String l_strCreateVoucherColumn = l_accOpenVoucherMasterParams.getCreateVoucherColumn();
            if (this.accOpenExpAccountOpen.getItemValue(l_strCreateVoucherColumn) != null)
            {
                l_strItemValue = 
                    this.accOpenExpAccountOpen.getItemValue(l_strCreateVoucherColumn).toString();
            }

            //２）　@判定値と比較する    
            if (l_accOpenVoucherMasterParams.getCreateVoucherValue() != null &&
                l_accOpenVoucherMasterParams.getCreateVoucherValue().equals(l_strItemValue))
            {
                log.debug("判定値と比較する");
                log.exiting(STR_METHOD_NAME);
                return true;    
            }
            else
            {
                log.debug("return false");
                log.exiting(STR_METHOD_NAME);
                return false;    
            }
        }
    }
    
    /**
     * (set口座開設見込客)<BR>
     * 口座開設見込客オブジェクトをプロパティにセットする。<BR>
     * @@param l_accOpenExpAccountOpen - 口座開設見込客オブジェクト
     * @@roseuid 4192E04202B6
     */
    public void setAccOpenExpAccountOpen(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen) 
    {
        this.accOpenExpAccountOpen = l_accOpenExpAccountOpen;
    }
    
    /**
     * (set伝票マスタ)<BR>
     * 口座開設伝票マスタをインスタンスにセットする。 <BR>
     * <BR>
     * 口座開設伝票マスタテーブルを以下の条件①@で検索する。 <BR>
     * 該当行がない場合、条件②で検索する。 <BR>
     * 検索結果をthis.口座開設伝票マスタ[]にセットする。 <BR>
     * <BR>
     * 　@[条件①@] <BR>
     * 　@口座開設伝票マスタ.証券会社コード = this.get証券会社コード() And <BR>
     * 　@口座開設伝票マスタ.部店コード = this.get部店コード() And <BR>
     * 　@口座開設伝票マスタ.口座区分 = this.get口座区分()　@And <BR>
     * 　@口座開設伝票マスタ.データコード = this.getデータコード() <BR>
     * <BR>
     * 　@[条件②] <BR>
     * 　@口座開設伝票マスタ.証券会社コード = this.get証券会社コード() And <BR>
     * 　@口座開設伝票マスタ.部店コード = "000" And <BR>
     * 　@口座開設伝票マスタ.口座区分 = this.get口座区分()　@And <BR>
     * 　@口座開設伝票マスタ.データコード = this.getデータコード() <BR>
     * <BR>
     * [条件①@]，[条件②]の両方で該当行がなかった場合は、<BR>
     * this.口座開設伝票マスタ[]にnullをセットする。 <BR>
     * @@roseuid 4192D1500305
     */
    public void setAccOpenVoucherMaster() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccOpenVoucherMaster()";
        log.entering(STR_METHOD_NAME);
        
        try
        {   
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            
            //口座開設伝票マスタテーブルを以下の条件①@で検索する。
            //[条件①@] 
            String l_strWhere =
                "institution_code = ? and " +        //口座開設伝票マスタ.証券会社コード = this.get証券会社コード() And 
                "branch_code = ? and " +             //口座開設伝票マスタ.部店コード = this.get部店コード() And 
                "account_div = ? and " +             //口座開設伝票マスタ.口座区分 = this.get口座区分()　@And
                "request_code = ? ";                 //口座開設伝票マスタ.データコード = this.getデータコード() 
            
            String l_strInstitutionCode = this.getInstitutionCode();
            String l_strBranchCode = this.getBranchCode();
            String l_strAccountDiv = this.getAccountDiv();
            String l_strRequestCode = this.getRequestCode();

            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strBranchCode,
                 l_strAccountDiv,
                 l_strRequestCode};
                    
            List l_lisRows = null;
            log.debug("[条件①@] ");
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    AccOpenVoucherMasterRow.TYPE,
                    l_strWhere,
                    l_bindVars);
                     
            //該当行がない場合、条件②で検索する。 
            if (l_lisRows.size() == 0)
            {
                log.debug("[条件②] ");
                //[条件②]  
                Object l_bindVars2[] =
                    {l_strInstitutionCode,
                     "000",
                     l_strAccountDiv,
                     l_strRequestCode};
                    
                l_lisRows = null;
                l_lisRows =
                    l_queryProcesser.doFindAllQuery(
                        AccOpenVoucherMasterRow.TYPE,
                        l_strWhere,
                        l_bindVars2);    
            }
            
            //検索結果をthis.口座開設伝票マスタ[]にセットする。
            int l_intSize = 0;
            if (l_lisRows != null)
            {
                l_intSize = l_lisRows.size();
            }
            
            if (l_intSize != 0)
            {
                log.debug("this.accOpenVoucherMasterParamses = l_accOpenVoucherMasterParams;");
                List l_lis = new ArrayList();
                for (int i = 0; i < l_intSize; i++)
                {
                    l_lis.add(l_lisRows.get(i));
                }
                AccOpenVoucherMasterParams[] l_accOpenVoucherMasterParams = new AccOpenVoucherMasterParams[l_intSize];
                l_lis.toArray(l_accOpenVoucherMasterParams);
                this.accOpenVoucherMasterParamses = l_accOpenVoucherMasterParams;

            }
            else
            {
                log.debug("this.accOpenVoucherMasterParamses = null;");
                //[条件①@]，[条件②]の両方で該当行がなかった場合は、this.口座開設伝票マスタ[]にnullをセットする
                this.accOpenVoucherMasterParamses = null;
            }

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save伝票行)<BR>
     * 口座開設伝票を１件登録する。<BR>
     * @@param l_strSerialNo - 伝票通番
     * @@roseuid 4191CA720081
     */
    public abstract void saveVoucherRow(String l_strSerialNo) throws WEB3BaseException;
    
    /**
     * (saveDelete伝票行)<BR>
     * 口座開設伝票を１件削除する。<BR>
     * @@param l_strSerialNo - 伝票通番
     * 
     * @@return boolean
     * @@roseuid 419C27E4036E
     */
    public abstract boolean saveDeleteVoucherRow(String l_strSerialNo) throws WEB3BaseException; 
    
    /**
     * (is作成可能伝票)<BR>
     * 指定伝票通番が、伝票作成可能な状態であるかを判定する。<BR>
     * <BR>
     * this.口座開設見込客.is伝票作成可能()の戻り値を返却する。<BR>
     * <BR>
     * [is伝票作成可能()に指定する引数]<BR>
     * 口座開設伝票作成ステータス：　@<BR>
     * 　@this.口座開設見込客.口座伝票作成ステータス[]のうち、<BR>
     * 以下の条件に当てはまる要素<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@データコード = this.getデータコード()<BR>
     * 　@伝票通番 = 伝票通番<BR>
     * <BR>
     * 条件に当てはまる口座開設伝票作成ステータスがない場合はtrueを返却。<BR>
     * @@param l_strSerialNo - 伝票通番
     * 
     * @@return boolean
     * @@roseuid 419C5223038D
     */
    public boolean isCreatedPossibleVoucher(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCreatedPossibleVoucher(String)";
        log.entering(STR_METHOD_NAME);
        //this.口座開設見込客.is伝票作成可能()の戻り値を返却する。                    
        //is伝票作成可能()に指定する引数。                   
        String l_strRequestCode = this.getRequestCode();

        boolean l_blnVoucherCreatedPossible = true;
            
        WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatus = this.accOpenExpAccountOpen.getVoucherStatus();
        int l_intLength = 0;
        if (l_accOpenVoucherCreatedStatus != null)
        {
            l_intLength = l_accOpenVoucherCreatedStatus.length;
        }
        
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("口座開設見込客.口座伝票作成ステータス[]");
            AccOpenVoucherStatusRow l_accOpenVoucherStatusRow = 
                (AccOpenVoucherStatusRow)l_accOpenVoucherCreatedStatus[i].getDataSourceObject();
                    
            if (l_accOpenVoucherStatusRow.getRequestCode().equals(l_strRequestCode) &&
                l_accOpenVoucherStatusRow.getSerialNo().equals(l_strSerialNo))
            {
                l_blnVoucherCreatedPossible = 
                    this.accOpenExpAccountOpen.isVoucherCreatedPossible(l_accOpenVoucherCreatedStatus[i]);     
            }
        }                  
     
        log.debug("return l_blnVoucherCreatedPossible");
        log.exiting(STR_METHOD_NAME);
        return l_blnVoucherCreatedPossible;          
    }
    
    /**
     * (save伝票作成ステータス)<BR>
     * 伝票作成ステータスをDBに更新する。<BR>
     * <BR>
     * this.口座開設見込客.get伝票ステータス()にて、関連する伝票作成ステータスの<BR>
     * 配列を取得する。<BR>
     * 取得した伝票作成ステータスのうち、以下の条件に当てはまるオブジェクトが<BR>
     * 存在するかを判定する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@データコード = this.getデータコード()<BR>
     * 　@伝票通番 = 伝票通番<BR>
     * <BR>
     * オブジェクトが存在した場合、updateにて口座開設伝票作成ステータステーブルを<BR>
     * 更新する。<BR>
     * オブジェクトが存在ない場合、insertにて口座開設伝票作成ステータステーブルを<BR>
     * 更新する。<BR>
     * <BR>
     * <BR>
     * 更新内容はDB更新仕様参照。<BR>
     * <BR>
     * 　@○　@0:00 ～ ONLINE終了時間までの間の場合<BR>
     * 　@　@　@（* 当日0:00 <= TradingSystem.getSystemTimestamp() < <BR>
     * 取引時間管理.get市場閉局時間()）<BR>
     * 　@　@－DB更新仕様「伝票作成_口座開設伝票作成ステータス<BR>
     * DB更新仕様.xls#作成済」参照<BR>
     * <BR>
     * 　@○　@以外の場合<BR>
     * 　@　@－DB更新仕様「伝票作成_口座開設伝票作成ステータス<BR>
     * DB更新仕様.xls#送信保留中」参照<BR>
     * @@param l_strSerialNo - 伝票通番
     * @@roseuid 41948C5A00DD
     */
    public void saveAccOpenVoucherStatus(String l_strSerialNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveAccOpenVoucherStatus(String)";
        log.entering(STR_METHOD_NAME);
        //口座開設見込客.get伝票ステータス()にて、関連する伝票作成ステータスの* 配列を取得する。
        WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatus = 
            this.accOpenExpAccountOpen.getVoucherStatus();
            
        //条件に当てはまるオブジェクトが存在するかを判定する。
        int l_intLength = 0;
        if (l_accOpenVoucherCreatedStatus != null)
        {
            l_intLength = l_accOpenVoucherCreatedStatus.length;
        }
        
        String l_strRequestCode = this.getRequestCode();
        boolean l_blnDiv = false;
        AccOpenVoucherStatusRow l_accOpenVoucherStatusRowUpdate = null;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("boolean l_blnDiv = false;");
            AccOpenVoucherStatusRow l_accOpenVoucherStatusRow = 
                (AccOpenVoucherStatusRow)l_accOpenVoucherCreatedStatus[i].getDataSourceObject();
                    
            if (l_accOpenVoucherStatusRow.getRequestCode().equals(l_strRequestCode) &&
                l_accOpenVoucherStatusRow.getSerialNo().equals(l_strSerialNo))
            {
                log.debug("l_blnDiv = true;");
                l_accOpenVoucherStatusRowUpdate = l_accOpenVoucherStatusRow;
                l_blnDiv = true;
            }
        }  
        
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        Calendar l_caldendar = new GregorianCalendar();
        l_caldendar.setTime(new Date(l_tsSystemTimestamp.getTime()));

        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strTradeCloseTime = 
            WEB3GentradeTradingTimeManagement.getTradeCloseTime(l_clendarContext.getMarketCode(), l_clendarContext.getProductCode());
        log.debug("l_strTradeCloseTime = " + l_strTradeCloseTime);
        Date l_datTradeCloseTime = null;
        if (l_strTradeCloseTime != null && l_strTradeCloseTime.length() > 4)
        {
            log.debug("(l_strTradeCloseTime != null && l_strTradeCloseTime.length() > 4)");
            log.debug("l_tsSystemTimestamp = " + l_tsSystemTimestamp);
            Calendar l_cal = new GregorianCalendar();
            l_cal.set(l_caldendar.get(Calendar.YEAR), 
                l_caldendar.get(Calendar.MONTH), 
                l_caldendar.get(Calendar.DATE), 
                Integer.parseInt(l_strTradeCloseTime.substring(0, 2)), 
                Integer.parseInt(l_strTradeCloseTime.substring(2, 4)));
                  
            l_datTradeCloseTime = l_cal.getTime();
            log.debug("l_datTradeCloseTime = " + l_datTradeCloseTime); 
        } 
                
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            AdministratorRow l_administratorRow =
                AdministratorDao.findRowByLoginId(l_opLoginSec.getLoginInfo().getLoginId());
            if (l_administratorRow == null)
            {
                log.debug("データ不整合エラー");            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    getClass().getName() + STR_METHOD_NAME
                );
            }
            
            if (l_blnDiv)
            {   
                log.debug("updateにて口座開設伝票作成ステータステーブルを更新する");
                AccOpenVoucherStatusParams l_accOpenVoucherStatusParamsUpdate = 
                    new AccOpenVoucherStatusParams(l_accOpenVoucherStatusRowUpdate);
                    
    
                //オブジェクトが存在した場合、updateにて口座開設伝票作成ステータステーブルを更新する。
                //0:00 ～ ONLINE終了時間までの間の場合
                if (WEB3DateUtility.compareTime(l_tsSystemTimestamp, l_datTradeCloseTime) < 0)
                {
                    log.debug("DB更新仕様「伝票作成_口座開設伝票作成ステータスDB更新仕様.xls#作成済」参照");
                    //DB更新仕様「伝票作成_口座開設伝票作成ステータスDB更新仕様.xls#作成済」参照
                    l_accOpenVoucherStatusParamsUpdate.setVoucherStatus(WEB3VoucherStatusDef.CREATE_COMPLETE);
                    l_accOpenVoucherStatusParamsUpdate.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setErrorCode(null); 
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doUpdateQuery(l_accOpenVoucherStatusParamsUpdate); 
                      
                }
                //以外の場合
                //DB更新仕様「伝票作成_口座開設伝票作成ステータスDB更新仕様.xls#送信保留中」参照
                else
                {
                    log.debug("DB更新仕様「伝票作成_口座開設伝票作成ステータスDB更新仕様.xls#送信保留中」参照");
                    l_accOpenVoucherStatusParamsUpdate.setVoucherStatus(WEB3VoucherStatusDef.SEND_RESERVING);
                    l_accOpenVoucherStatusParamsUpdate.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsUpdate.setErrorCode(null);                    
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsUpdate.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doUpdateQuery(l_accOpenVoucherStatusParamsUpdate); 
                }
            }
            else
            {
                log.debug("insertにて口座開設伝票作成ステータステーブルを更新する");
                //オブジェクトが存在ない場合、insertにて口座開設伝票作成ステータステーブルを更新する
                AccOpenVoucherStatusParams l_accOpenVoucherStatusParamsInsert = new AccOpenVoucherStatusParams();
                    
                //0:00 ～ ONLINE終了時間までの間の場合
                if (WEB3DateUtility.compareTime(l_tsSystemTimestamp, l_datTradeCloseTime) < 0)
                {
                    log.debug("DB更新仕様「伝票作成_口座開設伝票作成ステータスDB更新仕様.xls#作成済」参照");
                    //DB更新仕様「伝票作成_口座開設伝票作成ステータスDB更新仕様.xls#作成済」参照
                    l_accOpenVoucherStatusParamsInsert.setInstitutionCode(accOpenExpAccountOpen.getInstitutionCode());
                    l_accOpenVoucherStatusParamsInsert.setAccOpenRequestNumber(accOpenExpAccountOpen.getRequestNumber());
                    l_accOpenVoucherStatusParamsInsert.setRequestCode(this.getRequestCode());
                    l_accOpenVoucherStatusParamsInsert.setSerialNo(l_strSerialNo);
                    l_accOpenVoucherStatusParamsInsert.setVoucherStatus(WEB3VoucherStatusDef.CREATE_COMPLETE);
                    l_accOpenVoucherStatusParamsInsert.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setErrorCode(null); 
                    l_accOpenVoucherStatusParamsInsert.setCreatedTimestamp(l_tsSystemTimestamp);
                    l_accOpenVoucherStatusParamsInsert.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsInsert.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doInsertQuery(l_accOpenVoucherStatusParamsInsert); 
                      
                }
                //以外の場合
                else
                {
                    log.debug("以外の場合");
                    l_accOpenVoucherStatusParamsInsert.setInstitutionCode(accOpenExpAccountOpen.getInstitutionCode());
                    l_accOpenVoucherStatusParamsInsert.setAccOpenRequestNumber(accOpenExpAccountOpen.getRequestNumber());
                    l_accOpenVoucherStatusParamsInsert.setRequestCode(this.getRequestCode());
                    l_accOpenVoucherStatusParamsInsert.setSerialNo(l_strSerialNo);
                    l_accOpenVoucherStatusParamsInsert.setVoucherStatus(WEB3VoucherStatusDef.SEND_RESERVING);
                    l_accOpenVoucherStatusParamsInsert.setSendTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setRecvTimestamp(null);
                    l_accOpenVoucherStatusParamsInsert.setErrorCode(null); 
                    l_accOpenVoucherStatusParamsInsert.setCreatedTimestamp(l_tsSystemTimestamp);
                    l_accOpenVoucherStatusParamsInsert.setLastUpdater(l_administratorRow.getAdministratorCode());
                    l_accOpenVoucherStatusParamsInsert.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                    l_queryProcesser.doInsertQuery(l_accOpenVoucherStatusParamsInsert); 
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
     
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (伝票作成)<BR>
     * 伝票作成が必要かを判定する。<BR>
     * 作成が必要な場合、該当する口座開設伝票を作成する。<BR>
     * <BR>
     * １）伝票毎の作成要否判定<BR>
     * <BR>
     * 　@１－１）口座開設伝票マスタ行のチェック<BR>
     * 　@　@this.is伝票作成()をコールする。<BR>
     *　@　@戻り値がfalseの場合（作成対象外伝票の場合）、<BR>
     *　@　@falseを返却し、以降の処理は実施しない。(return false;)<BR>
     * <BR>
     * ２）　@伝票作成<BR> 
     * this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－４）の処理を実施する。<BR> 
     * <BR>
     * 　@２－１）　@伝票作成判定項目のチェック<BR> 
     * 　@　@２－１－１）　@要素毎の作成要否判定<BR>
     * 　@　@　@this.isValid伝票作成項目()にて、<BR>
     * 　@　@　@該当要素の伝票作成が必要かを判定する。<BR>
     * <BR> 
     * <BR>
     * 　@　@　@[isValid伝票作成項目()に指定する引数]<BR> 
     * 　@　@　@口座開設伝票マスタ行：　@this.口座開設伝票マスタ行[index]<BR> 
     * <BR>
     * 　@　@２－１－２）　@削除処理実施<BR> 
     * 　@　@　@伝票作成が不要な場合（this.isValid伝票作成項目() == false）、<BR>　@　@ 
     * 　@　@　@this.saveDelete伝票行()をコールする。 <BR>
     * <BR>
     * 　@　@　@[saveDelete伝票行()に指定する引数]<BR> 
     * 　@　@　@伝票通番：　@this.口座開設伝票マスタ行[index].伝票通番<BR> 
     * <BR>
     * 　@　@２－１－３）　@伝票作成ステータス行削除 <BR>
     * 　@　@　@伝票作成が不要な場合（this.isValid伝票作成項目() == false）、 <BR>
     * 　@　@　@口座開設伝票作成ステータステーブルの以下の条件に当てはまる行について <BR>
     * 　@　@　@伝票作成ステータスを 削除（Delete row）し、 <BR>
     * 　@　@　@当該要素について、以降の処理は実施しない。（continue;）<BR> 
     * <BR>
     * 　@　@　@[条件] <BR>
     * 　@　@　@証券会社コード = this.口座開設見込客.get証券会社コード() And <BR>
     * 　@　@　@識別コード = this.口座開設見込客.get識別コード() And <BR>
     * 　@　@　@データコード = this.getデータコード() And <BR>
     * 　@　@　@伝票通番 = 伝票通番 <BR>
     * <BR>
     * 　@２－２）　@伝票作成可能判定<BR> 
     * 　@　@this.is作成可能伝票()をコールし、作成可能な状態かを判定する。<BR> 
     * 　@　@伝票作成が不可な場合（this.is作成可能伝票() == false）、 <BR>
     * 　@　@当該要素について、以降の処理は実施しない。（continue;） <BR>
     * <BR>
     * 　@　@[is作成可能伝票()に指定する引数]<BR> 
     * 　@　@伝票通番：　@this.口座開設伝票マスタ行[index].伝票通番<BR> 
     * <BR>
     * 　@２－３）　@伝票行挿入 <BR>
     * 　@　@this.save伝票行()をコールし、伝票データ行をDBに登録する。 <BR>
     * <BR>
     * 　@　@[save伝票行()に指定する引数] <BR>
     * 　@　@伝票通番：　@this.口座開設伝票マスタ行[index].伝票通番 <BR>
     * <BR>
     * 　@２－４）　@伝票作成ステータス行更新 <BR>
     * 　@　@this.save伝票作成ステータス()をコールし、伝票作成ステータスデータ行をDBに登録する。 <BR>
     * <BR>
     * 　@　@[save伝票作成ステータス()に指定する引数] <BR>
     * 　@　@伝票通番：　@this.口座開設伝票マスタ行[index].伝票通番 <BR>
     * <BR>
     * ３）　@作成フラグ返却 <BR>
     * 　@２）で、１行でも伝票行を挿入した場合、trueを返却する。 <BR>
     * 　@以外、falseを返却する。 <BR>
     * @@return boolean
     */
    public boolean createVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createVoucher()";
        log.entering(STR_METHOD_NAME);

        //１）伝票毎の作成要否判定
        if (!this.isVoucherCreated())
        {
            //作成不要な伝票の場合、falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        int l_intLength = this.accOpenVoucherMasterParamses.length;
        
        //口座開設伝票マスタ行[]の各要素毎に、２－１）～２－４）の処理を実施する。
        int l_intDiv = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("口座開設伝票マスタ行[]の各要素毎に、２－１）～２－４）の処理を実施する。");
            String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
            //２－１）伝票作成判定項目のチェック
            
            //２－１－１）要素毎の作成要否判定
            log.debug("２－１－１）要素毎の作成要否判定");
            boolean l_blnValidVoucherCreatedItem =
                this.isValidVoucherCreatedItem(accOpenVoucherMasterParamses[i]);

            //２－１－２）削除処理実施
            if (!l_blnValidVoucherCreatedItem)
            {
                log.debug("２－１－２）削除処理実施");
                boolean l_blnSaveDeleteVoucherRow = this.saveDeleteVoucherRow(l_strSerialNo);

                log.debug("【作成不要な伝票削除】 : " + l_blnSaveDeleteVoucherRow);

                //２－１－３）伝票作成ステータス行削除
                log.debug("２－１－３）伝票作成ステータス行削除");
                try
                {
                    QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                    String l_strWhere =
                        "institution_code = ? "
                        + " and acc_open_request_number = ? "
                        + " and request_code = ? "
                        + " and serial_no = ? ";

                    Object l_objBindVars[] = {
                        this.accOpenExpAccountOpen.getInstitutionCode(),
                        this.accOpenExpAccountOpen.getRequestNumber(),
                        this.getRequestCode(),
                        l_strSerialNo};

                    int l_intDeleteStatusResult =
                        l_queryProcesser.doDeleteAllQuery(
                            AccOpenVoucherStatusRow.TYPE,
                            l_strWhere,
                            l_objBindVars);
                    log.debug("口座開設伝票作成ステータス削除レコード： " + l_intDeleteStatusResult);

                }
                catch (DataNetworkException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);            
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);            
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.toString(),
                        l_ex);
                }
                //判定の結果、伝票の作成が不要だった場合、以降の処理を行わない。
                continue;
            }

            //２－２）　@伝票作成可能判定
            boolean l_blnCreatedPossibleVoucher = this.isCreatedPossibleVoucher(l_strSerialNo);
            if (!l_blnCreatedPossibleVoucher)
            {
                log.debug("２－２）　@伝票作成可能判定");
                continue;
            }
            
            //２－３）　@伝票行挿入 
            log.debug("２－３）　@伝票行挿入");
            this.saveVoucherRow(l_strSerialNo);
            l_intDiv = l_intDiv + 1;
            
            //２－４）　@伝票作成ステータス行更新
            log.debug("伝票作成ステータス行更新");
            this.saveAccOpenVoucherStatus(l_strSerialNo);
        }
        
        if (l_intDiv > 0)
        {
            log.debug("return true;");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("return false;");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (伝票送信)<BR>
     * オンライン伝票をHOSTへ送信する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（口座開設伝票作成）伝票送信」参照。<BR>
     * @@roseuid 4191DC1502D3
     */
    public void sendVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sendVoucher()";
        log.entering(STR_METHOD_NAME);
        //1.1  isオンライン伝票
        boolean l_blnOnlineVoucher = this.isOnlineVoucher();
        
        //1.2 オンライン伝票の場合（isオンライン伝票() == true）、MQトリガを実施する
        if (l_blnOnlineVoucher)
        {
            //1.2.1 get証券会社コード( )
            String l_strInstitutionCode = this.getInstitutionCode();
            
            //1.2.2 getデータコード
            String l_strRequestCode = this.getRequestCode() + "T";
            
            //1.2.3 getユーザデータ
            String l_strUserData = this.getUserData();
            
            //1.2.4 WEB3MQMessageSpec
            WEB3MQMessageSpec l_web3MQMessageSpec = 
                new WEB3MQMessageSpec(l_strInstitutionCode, l_strRequestCode, l_strUserData);
            log.debug("[MQ Trigger] 証券会社コード:" + l_strInstitutionCode +
                ", データコード:" + l_strRequestCode + 
                ", ユーザデータ:" + l_strUserData);
                
            //1.2.5 send(WEB3MQMessageSpec)
            WEB3MQGatewayService l_web3MQGatewayService = 
                (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
            WEB3MQSendResult l_web3MQSendResult = l_web3MQGatewayService.send(l_web3MQMessageSpec);
            if (l_web3MQSendResult.isFailedResult())
            {
                log.info("l_web3MQSendResult.isFailedResult()");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_web3MQSendResult.getErrorInfo(),
                    getClass().getName() + STR_METHOD_NAME);       
            }
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (伝票削除)<BR>
     * 対象の各伝票通番毎に、伝票削除を実施する。<BR>
     * 伝票を削除した場合、該当する伝票作成ステータス行を削除する。<BR>
     * <BR>
     * １）　@口座開設伝票マスタ行のチェック<BR> 
     * 　@　@this.is伝票作成()をコールする。 <BR>
     * 　@　@戻り値がfalseの場合、falseを返却し、<BR> 
     * 　@　@以降の処理は実施しない。(return false;) <BR>
     * <BR>
     * ２）　@伝票削除<BR>
     * this.口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する。<BR>
     * <BR>
     * ２－１）　@伝票作成判定項目のチェック<BR> 
     * 　@　@this.isValid伝票作成項目()にて、該当要素の伝票削除が必要かを判定する。<BR> 
     * 　@　@戻り値がfalseの場合、以降の処理は実施しない。(continue;) <BR>
     * <BR>
     * 　@　@　@[isValid伝票作成項目()に指定する引数]<BR> 
     * 　@　@　@口座開設伝票マスタ行：　@this.口座開設伝票マスタ行[index]<BR>
     * <BR>
     * 　@２－２）　@削除処理実施<BR>
     * 　@　@this.saveDelete伝票行()をコールする。<BR>
     * <BR>
     * 　@　@[saveDelete伝票行()に指定する引数]<BR>
     * 　@　@伝票通番：　@this.口座開設伝票マスタ行[index].伝票通番<BR>
     * <BR>
     * 　@２－３）　@伝票作成ステータス行削除<BR>
　@　@ *      伝票を削除した場合（２－２）の戻り値 == true）、<BR>
　@　@ *      口座開設伝票作成ステータステーブルの以下の条件に当てはまる行について<BR>
　@ 　@*      伝票作成ステータスを 0：DEFAULT（未作成）　@に更新（Update row）する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = this.口座開設見込客.get証券会社コード() And<BR>
     * 　@　@識別コード = this.口座開設見込客.get識別コード() And<BR>
     * 　@　@データコード = this.getデータコード() And<BR>
     * 　@　@伝票通番 = 伝票通番<BR>
     * <BR>
     * ３）　@削除フラグ返却<BR>
     * 　@２）で、１行でも伝票行を削除した場合、trueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 419C278E012C
     */
    public boolean deleteVoucher() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " deleteVoucher()";
        log.entering(STR_METHOD_NAME);

        //１）口座開設伝票マスタ行のチェック
        if (!this.isVoucherCreated())
        {
            //this.口座開設伝票マスタ行[] == nullの場合falseを返却
            return false;
        }

        try
        {
            //２）　@伝票削除
            //口座開設伝票マスタ行[]の各要素毎に、２－１）～２－３）の処理を実施する。
            int l_intDiv = 0;
            int l_intLength = this.accOpenVoucherMasterParamses.length;
            
            for (int i = 0; i < l_intLength; i++)
            {
                //２－１）伝票作成判定項目のチェック
                if (!this.isValidVoucherCreatedItem(this.accOpenVoucherMasterParamses[i]))
                {
                    log.debug("該当要素の伝票削除が不要な場合、次の要素に処理を移す。");
                    continue;
                }

                //２－２）　@削除処理実施
                log.debug("削除処理実施");
                String l_strSerialNo = accOpenVoucherMasterParamses[i].getSerialNo();
                boolean l_blnDeleteVoucher = this.saveDeleteVoucherRow(l_strSerialNo);
            
                //２－３）　@伝票作成ステータス行削除
                if (l_blnDeleteVoucher)
                {
                    QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                    String l_strInstitutionCode = this.accOpenExpAccountOpen.getInstitutionCode();
                    String l_strRequestNumber = this.accOpenExpAccountOpen.getRequestNumber();
                    String l_strRequestCode = this.getRequestCode();
                    
                    AccOpenVoucherStatusRow l_row = AccOpenVoucherStatusDao.findRowByInstitutionCodeAccOpenRequestNumberRequestCodeSerialNo(
                        l_strInstitutionCode,
                        l_strRequestNumber,
                        l_strRequestCode,
                        l_strSerialNo);
                    
                    if (l_row != null)
                    {
                        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                        AccOpenVoucherStatusParams l_params = new AccOpenVoucherStatusParams(l_row);
                        l_params.setVoucherStatus(WEB3VoucherStatusDef.DEFAULT);
                        l_params.setLastUpdatedTimestamp(l_tsSystemTimestamp);
                        l_queryProcesser.doUpdateQuery(l_params);
                        l_intDiv = l_intDiv + 1;
                    }
                }   
            }
            
            //２）　@削除フラグ返却
            if (l_intDiv > 0)
            {
                log.debug("削除フラグ返却 true");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.debug("削除フラグ返却 false");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }

    /**
     * (is対象伝票)<BR>
     * 指定の伝票が、口座開設伝票マスタにより<BR>
     * 処理対象に指定されているかを判定する。<BR>
     * <BR>
     * １）　@口座開設伝票マスタ行のチェック<BR> 
     * 　@　@this.is伝票作成()をコールする。 <BR>
     * 　@　@戻り値がfalseの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@口座開設伝票マスタ行[]の各要素のチェック<BR> 
     * this.口座開設伝票マスタ行[]の各要素毎に、処理を実施する。<BR> 
     * <BR>
     * 　@２－１）　@伝票作成判定項目のチェック<BR> 
     * 　@　@this.isValid伝票作成項目(口座開設伝票マスタ行)にて、<BR>
     *　@　@判定値のチェックをする。 <BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@口座開設伝票マスタ行：　@this.口座開設伝票マスタ行[i番目の要素]<BR>
     * <BR>
     * 　@　@※要素中、戻り値が一つでもtrueであれば、trueを返却する。<BR>
     * <BR>
     * ３）　@以外、falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isTargetVoucher() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTargetVoucher()";
        log.entering(STR_METHOD_NAME);

        //口座開設伝票マスタ行のチェック
        if (!this.isVoucherCreated())
        {
            log.debug("口座開設伝票マスタ行[] == null");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        for (int i = 0; i < this.accOpenVoucherMasterParamses.length; i++) 
        {
            if (isValidVoucherCreatedItem(accOpenVoucherMasterParamses[i]))
            {
                log.debug("処理対象伝票");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        log.debug("口座開設伝票マスタ行[] != null && 伝票作成条件に一致しない");
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * 文字を切り捨<BR>
     * @@param l_str - 文字
     * @@param l_intByteNumber - サイズ
     */
    protected String getStringByByteNumber(String l_str, int l_intByteNumber)
    {
        if (l_str == null)
        {
            return null;
        }
        
        if (l_intByteNumber >= WEB3StringTypeUtility.getByteLength(l_str))
        {
            return l_str;
        }
        
        if ("".equals(l_str) || l_intByteNumber <= 0)
        {
            return "";
        }
        
        byte[] l_bytesInit = l_str.getBytes();

        String l_strCut = new String(l_bytesInit, 0, l_intByteNumber);
        
        String l_strLast = l_str.substring(l_strCut.length() - 1, l_strCut.length());
        
        boolean l_blnIsSingle = WEB3StringTypeUtility.isSingle(l_strLast);
        
        if (l_blnIsSingle)
        {
            return l_strCut;
        }
        else
        {        
            String l_strCutAgain = l_strCut.substring(0, l_strCut.length() - 1);
        
            int l_intCutAgain = WEB3StringTypeUtility.getByteLength(l_strCutAgain);
        
            if (l_intByteNumber - l_intCutAgain > 1)
            {
                return l_strCut;
            }
            else
            {
                return l_strCutAgain;
            }
        }
    }

    /**
     * 全角文字を切り捨<BR>
     * @@param l_str - 全角文字
     * @@param l_intByteNumber - サイズ
     */
    protected String getEmStringByByteNumber(String l_str, int l_intByteNumber)
    {

    	if (l_str == null)
    	{
    		return null;
    	}

    	int retLen = l_intByteNumber / 2;
    	if (l_str.length() <= retLen)
    	{
    		return l_str;
    	}

    	return l_str.substring(0, retLen);
    }
}
@
