head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenJudgeWaiting.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設審査待ち(WEB3AccOpenJudgeWaiting.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 肖志偉 (中訊) 新規作成
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.data.AccOpenWaitingRow;
import webbroker3.accountopen.define.WEB3AccOpenCheckStateDivDef;
import webbroker3.accountopen.define.WEB3AccOpenFromWebSortKeyDef;
import webbroker3.accountopen.define.Web3AccOpenSortKeyDef;
import webbroker3.accountopen.message.WEB3AccOpenSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3CheckDivDef;
import webbroker3.common.define.WEB3ReviewCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設審査待ち)<BR>
 * 口座開設審査待ち<BR>
 *                                                              
 * @@author 肖志偉
 * @@version 1.0
 */

public class WEB3AccOpenJudgeWaiting 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AccOpenJudgeWaiting.class);

    /**
     * (口座開設審査待ちデータ)<BR>
     * 口座開設審査待ちParamsの一覧<BR>
     */
    private List accOpenWaitingParamsList;
    
    /**
     * (口座開設審査待ち)<BR>
     * コンストラクタ<BR> 
     * <BR>
     * １）口座開設審査待ちデータのオブジェクトを生成する<BR> 
     */
    public WEB3AccOpenJudgeWaiting()
    {
        final String STR_METHOD_NAME = 
            " WEB3AccOpenJudgeWaiting()";
        log.entering(STR_METHOD_NAME);
        
        //１）口座開設審査待ちデータのオブジェクトを生成する
        this.accOpenWaitingParamsList = new ArrayList();

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (add口座開設審査待ち行)<BR>
     * 口座開設審査待ち行を追加する。<BR> 
     * <BR>
     * １）口座開設審査待ち行 == null の場合 <BR>
     * 　@false を返却する <BR>
     * <BR>
     * ２）口座開設審査待ちデータに、口座開設審査待ち行を追加する <BR>
     * <BR>
     * ３）true を返却する <BR>
     * @@param l_accOpenWaitingParams - (口座開設審査待ち行) <BR>
     * 口座開設審査待ち行 <BR>
     * <BR>
     * ※ 口座開設審査待ちParamsクラスはDDLより自動生成する。<BR>
     * @@return boolean
     */
    public boolean addAccOpenWaitingParams(AccOpenWaitingParams l_accOpenWaitingParams)
    {
        final String STR_METHOD_NAME = 
            " addAccOpenWaitingParams(AccOpenWaitingParams)";
        log.entering(STR_METHOD_NAME);

        //１）口座開設審査待ち行 == null の場合 
		//false を返却する 
        if (l_accOpenWaitingParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）口座開設審査待ちデータに、口座開設審査待ち行を追加する
        if (this.accOpenWaitingParamsList != null)
        {
            this.accOpenWaitingParamsList.add(l_accOpenWaitingParams);  
        }
        
		//３）true を返却する 
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     *(get口座開設審査待ち行数)<BR>
     *口座開設審査待ちデータのレコード件数を返す。<BR>
     * @@return int
     */
    public int getAccOpenWaitingParamsNumber()
    {
        final String STR_METHOD_NAME = 
            " getAccOpenWaitingParamsNumbers()";
        log.entering(STR_METHOD_NAME);
        
        //口座開設審査待ちデータのレコード件数を返す。
        int l_intAccOpenWaitingParamsSize = 0;
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size();
        }
        
        log.debug("口座開設審査待ちデータのレコード件数を返す。" 
            + l_intAccOpenWaitingParamsSize);
        
        log.exiting(STR_METHOD_NAME);
        return l_intAccOpenWaitingParamsSize;
    }
    
    /**
     * (get口座開設審査待ち行)<BR>
     * 指定した取得行番号の口座開設審査待ち行を返却する。<BR>
     * <BR>
     * １）this.get口座開設審査待ち行数から全件数を取得する。<BR> 
     * <BR>
     * ２）取得行番号のチェック <BR>
     * 　@取得行番号 < 0 または 取得行番号 >= 全件数　@の場合 <BR>
     * 　@null を返却する <BR>
     * <BR>
     * ３）口座開設審査待ちデータ.get(取得行番号) を返却する <BR>
     * <BR>
     * ※ 口座開設審査待ちParamsクラスはDDLより自動生成する。<BR>
     * @@param l_intGetLineNo - (取得行番号)<BR>
     * 取得したい口座開設審査待ちデータの行数<BR>
     * @@return AccOpenWaitingParams
     */
    public AccOpenWaitingParams getAccOpenWaitingParams(int l_intGetLineNo)
    {
        final String STR_METHOD_NAME = 
            " getAccOpenWaitingParams(int l_intGetLineNo)";
        log.entering(STR_METHOD_NAME);

        //１）this.get口座開設審査待ち行数から全件数を取得する。 
        int l_intAccOpenWaitingParamsSize = 
            this.getAccOpenWaitingParamsNumber();

        //２）取得行番号のチェック 
		//取得行番号 < 0 または 取得行番号 >= 全件数　@の場合 
		//null を返却する
        if (l_intGetLineNo < 0 || l_intGetLineNo >= l_intAccOpenWaitingParamsSize)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //３）口座開設審査待ちデータ.get(取得行番号) を返却する 
        AccOpenWaitingParams l_accOpenWaitingParams = null;
        if (this.accOpenWaitingParamsList != null && 
            !this.accOpenWaitingParamsList.isEmpty())
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(
                    l_intGetLineNo);
            
            l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_accOpenWaitingParams;
    }
    
    /**
     * (set口座開設審査待ち行 )<BR>
     * 口座開設審査待ち行を更新する。<BR> 
     * <BR>
     * １）引数のチェック<BR> 
     * 　@１－１）口座開設審査待ち行 == null の場合<BR> 
     * 　@　@false を返却する <BR>
     * <BR>
     * 　@１－２）取得行番号のチェック<BR> 
     * 　@　@this.get口座開設審査待ち行数から全件数を取得する。<BR> 
     * 　@　@更新行番号 < 0 または 更新行番号 >= 全件数　@の場合 <BR>
     * 　@　@false を返却する <BR>
     * <BR>
     * ２）口座開設審査待ちデータの更新行番号のオブジェクトを<BR> 
     * 　@口座開設審査待ち行で上書きする <BR>
     * <BR>
     * ３）true を返却する<BR> 
     * @@param l_accOpenWaitingParams - (口座開設審査待ち行)<BR>
     * 口座開設審査待ち行 <BR>
     * <BR>
     * ※ 口座開設審査待ちParamsクラスはDDLより自動生成する。<BR>
     * @@param l_intUpdateLineNo - (更新行番号)<BR>
     * 更新したい口座開設審査待ちデータの行数<BR>
     * @@return boolean
     */
    public boolean setAccOpenWaitingParams(
        AccOpenWaitingParams l_accOpenWaitingParams,
        int l_intUpdateLineNo)
    {
        final String STR_METHOD_NAME = 
            " setAccOpenWaitingParams(AccOpenWaitingParams, int)";
        log.entering(STR_METHOD_NAME);

		//１）引数のチェック 
		//１－１）口座開設審査待ち行 == null の場合 
		//false を返却する
        if (l_accOpenWaitingParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
		//１－２）取得行番号のチェック 
		//this.get口座開設審査待ち行数から全件数を取得する。 
		//更新行番号 < 0 または 更新行番号 >= 全件数　@の場合 
		//false を返却する 
        int l_intAccOpenWaitingParamsSize = 
            this.getAccOpenWaitingParamsNumber();
        if (l_intUpdateLineNo < 0 || l_intUpdateLineNo >= l_intAccOpenWaitingParamsSize)
        {
            log.exiting(STR_METHOD_NAME);
            return false;  
        }
        
		//２）口座開設審査待ちデータの更新行番号のオブジェクトを 
		//口座開設審査待ち行で上書きする
        
        if (this.accOpenWaitingParamsList != null)
        {
            this.accOpenWaitingParamsList.set(l_intUpdateLineNo, l_accOpenWaitingParams);
        }
        
		//３）true を返却する 
        log.exiting(STR_METHOD_NAME);
        return true;    
    }
    
    /**
     * (select審査対象識別コード一覧)<BR>
     * 指定している条件を生成し、識別コードの一覧を取得する。<BR> 
     * <BR>
     * １）　@検索条件文字列インスタンス（：String）を生成<BR>  
     * 　@this.create検索条件文字列-審査対象識別コード一覧() から取得する<BR> 
     * <BR>
     * 　@[引数]<BR> 
     * 　@　@部店コード、顧客コード、識別コード、<BR> 
     * 　@　@発生日（自）、発生日（至）、審査種別、審査担当者コード、審査状況<BR> 
     * <BR>
     * ２）　@検索条件データコンテナインスタンス（：String[]）を生成<BR>  
     * 　@this.create検索条件データコンテナ-審査対象識別コード一覧() から取得する<BR> 
     * <BR>
     * 　@[引数]<BR> 
     * 　@　@証券会社コード、部店コード、顧客コード、識別コード、<BR> 
     * 　@　@発生日（自）、発生日（至）、審査種別、審査担当者コード<BR> 
     * <BR>
     * ３）　@ソート条件文字列インスタンス（：String）を生成<BR>  
     * 　@this.createソート条件文字列-審査対象識別コード一覧() から取得する <BR>
     * <BR>
     * 　@[引数]<BR> 
     * 　@　@ソートキー<BR> 
     *<BR> 
     * ３）　@QueryProcessor.doFindAllQuery( )により、<BR>
     * 口座開設審査待ちParamsのListを取得する。<BR>  
     * 　@該当データがない場合、nullを返却する <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数]<BR>  
     * 　@　@rowType：　@口座開設審査待ちRow.TYPE <BR> 
     * 　@　@where：　@検索条件文字列インスタンス <BR>
     * 　@　@orderBy：　@ソート条件文字列インスタンス <BR>
     * 　@　@conditions：　@null  <BR>
     * 　@　@bindVars：　@検索条件データコンテナインスタンス <BR>
     * <BR>
     * ４）　@返却値Collection（：ArrayList)生成<BR>  
     * 　@返却値Collection（：ArrayList)を生成する。<BR>  
     * <BR>
     * ５）　@口座開設審査待ちParamsのListの件数分Loopする<BR> 
     * <BR>
     * 　@５－１）返却値Collectionにセットした中に重複した識別コードがある場合、<BR>
     * 　@continue する。<BR> 
     * <BR>
     * 　@５－２）返却値Collectionに、<BR>
     * 　@口座開設審査待ちParamsのList[index]を追加する<BR> 
     * <BR>
     * 　@　@[add()に指定する引数]<BR>  
     * 　@　@口座開設審査待ちParamsのList[index].識別コード<BR> 
     * <BR>
     * ６）返却値Collectionを返却する <BR>
     * 　@返却値Collection.toArrays(new String[0])<BR> 
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strOccurDateFrom - (発生日（自）)<BR>
     * 発生日（自）<BR>
     * <BR>
     * YYYYMMDD形式<BR>
     * @@param l_strOccurDateTo - (発生日（至）)<BR>
     * 発生日（至） <BR>
     * <BR>
     * YYYYMMDD形式<BR>
     * @@param l_strReviewCode - (審査種別)<BR>
     * 審査種別<BR>
     * @@param l_strCheckerCode - (審査担当者コード)<BR>
     * 審査担当者コード<BR>
     * @@param l_strCheckState - (審査状況)<BR>
     * 審査状況<BR>
     * @@param l_sortKeys (ソートキー)<BR>
     * 口座開設ソートキーの配列<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public String[] selectJudgeObjectAccOpenRequestNumberList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strRequestNumber,
        String l_strOccurDateFrom,
        String l_strOccurDateTo,
        String l_strReviewCode,
        String l_strCheckerCode,
        String l_strCheckState,
        WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " selectJudgeObjectAccOpenRequestNumberList(" +
            "String, String, String, String, String, " +
            "String, String, String, String, WEB3AccOpenSortKey[])"; 
        log.entering(STR_METHOD_NAME);
        
		//１）　@検索条件文字列インスタンス（：String）を生成  
		//this.create検索条件文字列-審査対象識別コード一覧() から取得する 
		//[引数] 
		//部店コード、顧客コード、識別コード、 
		//発生日（自）、発生日（至）、審査種別、審査担当者コード、審査状況 
        String l_strQueryString = 
            this.createQueryStringForJudgeObjectAccOpenRequestNumberList(
                l_strBranchCode,
                l_strAccountCode,
                l_strRequestNumber,
                l_strOccurDateFrom,
                l_strOccurDateTo,
                l_strReviewCode,
                l_strCheckerCode,
                l_strCheckState);

		//２）　@検索条件データコンテナインスタンス（：String[]）を生成  
		//this.create検索条件データコンテナ-審査対象識別コード一覧() から取得する 
		//[引数] 
		//証券会社コード、部店コード、顧客コード、識別コード、 
		//発生日（自）、発生日（至）、審査種別、審査担当者コード
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainerForJudgeObjectAccOpenRequestNumberList(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strRequestNumber,
                l_strOccurDateFrom,
                l_strOccurDateTo,
                l_strReviewCode,
                l_strCheckerCode);

        //３）ソート条件文字列インスタンス（：String）を生成  
		//this.createソート条件文字列-審査対象識別コード一覧() から取得する 
		//[引数] 
		//ソートキー 
        String l_strSortKey = 
            this.createSortCondForJudgeObjectAccOpenRequestNumberList(l_sortKeys);
        
        //３）　@QueryProcessor.doFindAllQuery( )により、
        //口座開設審査待ちParamsのListを取得する。  
		//該当データがない場合、nullを返却する 
        
        List l_lisAccOpenWaitingRecords = null;
		try
        {
			//[doFindAllQuery()に指定する引数]  
			//rowType：　@口座開設審査待ちRow.TYPE  
			//where：　@検索条件文字列インスタンス 
			//orderBy：　@ソート条件文字列インスタンス 
			//conditions：　@null  
			//bindVars：　@検索条件データコンテナインスタンス  
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenWaitingRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenWaitingRow.TYPE,
                    l_strQueryString,
                    l_strSortKey,
                    null,
                    l_strQueryDataContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex); 
            log.exiting(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
        if (l_lisAccOpenWaitingRecords == null || 
            l_lisAccOpenWaitingRecords.isEmpty())
        {
	        log.exiting(STR_METHOD_NAME);
	        return null;
        }
        
		//４）　@返却値Collection（：ArrayList)生成  
		//返却値Collection（：ArrayList)を生成する。
        ArrayList l_lisCollection = new ArrayList();
       
		//５）　@口座開設審査待ちParamsのListの件数分Loopする 
		//５－１）返却値Collectionにセットした中に重複した識別コードがある場合、continue する。 
		//５－２）返却値Collectionに、口座開設審査待ちParamsのList[index]を追加する 
		//[add()に指定する引数]  
		//口座開設審査待ちParamsのList[index].識別コード 
        Map l_uniqueMap = new Hashtable();
        for (int i = 0; i < l_lisAccOpenWaitingRecords.size(); i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) l_lisAccOpenWaitingRecords.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
              
            String l_strAccOpenRequestNumber = 
                l_accOpenWaitingParams.getAccOpenRequestNumber();

            if (l_uniqueMap.containsKey(l_strAccOpenRequestNumber))
            {
                continue;
            }
            else
            {
                l_lisCollection.add(l_strAccOpenRequestNumber);
                l_uniqueMap.put(l_strAccOpenRequestNumber, "1");
            }
        }
        
		//６）返却値Collectionを返却する 
		//返却値Collection.toArrays(new String[0]) 
        String[] l_strCollection = new String[l_lisCollection.size()];
        l_lisCollection.toArray(l_strCollection);
        
        log.exiting(STR_METHOD_NAME);
        return l_strCollection;
    }
    
    /**
     * (create検索条件文字列-審査対象識別コード一覧)<BR>
     * 検索条件文字列を編集する。<BR>  
     * <BR>
     * １）　@戻り値生成  <BR>
     * 　@戻り値の検索条件文字列インスタンス（：StringBuffer）を生成 <BR> 
     * <BR>
     * ２）　@証券会社コード条件追加  <BR>
     * 　@証券会社コード条件を追加する。<BR>  
     * <BR>
     * 　@" institution_code = ? "  <BR>
     * <BR>
     * ３）　@部店コード条件追加  <BR>
     * 　@部店コード != "000"(全部店)の場合、部店コード条件を追加する。 <BR> 
     * <BR>
     * 　@" and branch_code = ? "  <BR>
     * <BR>
     * ４）　@顧客コード条件追加 <BR>
     * 　@顧客コード != nullの場合、顧客コード条件を追加する。<BR>  
     * <BR>
     * 　@" and account_code like ? "  <BR>
     * <BR>
     * ５）　@識別コード条件追加 <BR>
     * 　@識別コード != nullの場合、識別コード条件を追加する。<BR>  
     * <BR>
     * 　@" and acc_open_request_number like ? "  <BR>
     * <BR>
     * ６）　@発生日（自）条件追加 <BR>
     * 　@発生日（自） != nullの場合、発生日時条件を追加する。<BR>  
     * <BR>
     * 　@" and to_char(created_timestamp, 'YYYYMMDD') >= ? "  <BR>
     * <BR>
     * ７）　@発生日（至）条件追加 <BR>
     * 　@発生日（至） != nullの場合、発生日時条件を追加する。 <BR> 
     * <BR>
     * 　@" and to_char(created_timestamp, 'YYYYMMDD') <= ? "<BR>  
     * <BR>
     * ８）　@審査種別条件追加 <BR>
     * 　@審査種別 != "0"(全て)の場合、審査種別条件を追加する。  <BR>
     * <BR>
     * 　@" and review_code = ? "  <BR>
     * <BR>
     * ９）　@審査担当者コード条件追加 <BR>
     * 　@審査担当者コード != nullの場合、審査担当者コード条件を追加する。<BR>  
     * <BR>
     * 　@" and checker_code like ? "  <BR>
     * <BR>
     * １０）　@審査状況条件追加 <BR>
     * 　@１０－１）審査状況 = "1"(審査待ち)の場合 <BR>
     * 　@　@審査区分 = "0"(審査待ち)　@の条件を追加する。  <BR>
     * <BR>
     * 　@　@" and check_div = '0' "  <BR>
     * <BR>
     * 　@１０－２）審査状況 = "2"(審査済み)の場合 <BR>
     * 　@　@審査区分 = "1"(認可) or "2"(否認)　@の条件を追加する。<BR>  
     * <BR>
     * 　@　@" and check_div in ('1', '2') "  <BR>
     * <BR>
     * 　@　@１０－３）審査状況 = "3"(承認済)の場合 <BR>
     * 　@　@　@審査区分 = "1"(認可)　@の条件を追加する。  <BR>
     * <BR>
     * 　@　@　@" and check_div = '1' "  <BR>
     * <BR>
     * 　@　@１０－４）審査状況 = "4"(否認済)の場合 <BR>
     * 　@　@　@審査区分 = "2"(否認)　@の条件を追加する。  <BR>
     * <BR>
     * 　@　@　@" and check_div = '2' "  <BR>
     * <BR>
     * １１）検索条件文字列インスタンス.toString() を返却する <BR>
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード
     * @@param l_strOccurDateFrom - (発生日（自）)<BR>
     * 発生日（自）<BR>
     * <BR>
     * YYYYMMDD形式<BR>
     * @@param l_strOccurDateTo - (発生日（至）)<BR>
     * 発生日（至） <BR>
     * <BR>
     * YYYYMMDD形式<BR>
     * @@param l_strReviewCode - (審査種別)<BR>
     * 審査種別
     * @@param l_strCheckerCode - (審査担当者コード)<BR>
     * 審査担当者コード
     * @@param l_strCheckState - (審査状況)<BR>
     * 審査状況
     * @@return String
     */
    private String createQueryStringForJudgeObjectAccOpenRequestNumberList(
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strRequestNumber,
        String l_strOccurDateFrom,
        String l_strOccurDateTo,
        String l_strReviewCode,
        String l_strCheckerCode,
        String l_strCheckState)
    {
        final String STR_METHOD_NAME = 
            " createQueryStringForJudgeObjectAccOpenRequestNumberList(" +
            "String, String, String, String, " +
            "String, String, String, String)"; 
        log.entering(STR_METHOD_NAME);
        
		//１）　@戻り値生成  
		//戻り値の検索条件文字列インスタンス（：StringBuffer）を生成 
        StringBuffer l_strQueryString = new StringBuffer();
        
		//２）　@証券会社コード条件追加  
		//証券会社コード条件を追加する。
        //" institution_code = ? "  
        l_strQueryString.append(" institution_code = ? ");

		//３）　@部店コード条件追加  
		//部店コード != "000"(全部店)の場合、部店コード条件を追加する。  
		//" and branch_code = ? "  
        if (!WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCode))
        {
            l_strQueryString.append(" and branch_code = ? ");
        }
        
		//４）　@顧客コード条件追加 
		//顧客コード != nullの場合、顧客コード条件を追加する。  
		//" and account_code like ? " 
        if (l_strAccountCode != null)
        {
            l_strQueryString.append(" and account_code like ? ");
        }
        
		//５）　@識別コード条件追加 
		//識別コード != nullの場合、識別コード条件を追加する。  
		//" and acc_open_request_number like ? "  
        if (l_strRequestNumber != null)
        {
            l_strQueryString.append(" and acc_open_request_number like ? ");
        } 
        
		//６）　@発生日（自）条件追加 
		//発生日（自） != nullの場合、発生日時条件を追加する。  
		//" and to_char(created_timestamp, 'YYYYMMDD') >= ? "  
        if (l_strOccurDateFrom != null)
        {
            l_strQueryString.append(" and to_char(created_timestamp, 'YYYYMMDD') >= ? ");
        }
        
		//７）　@発生日（至）条件追加 
		//発生日（至） != nullの場合、発生日時条件を追加する。  
		//" and to_char(created_timestamp, 'YYYYMMDD') <= ? "  
        if (l_strOccurDateTo != null)
        {
            l_strQueryString.append(" and to_char(created_timestamp, 'YYYYMMDD') <= ? ");
        }
        
		//８）　@審査種別条件追加 
		//審査種別 != "0"(全て)の場合、審査種別条件を追加する。  
		//" and review_code = ? "  
        if (!WEB3ReviewCodeDef.DEFAULT.equals(l_strReviewCode))
        {
            l_strQueryString.append(" and review_code = ? ");
        }
        
		//９）　@審査担当者コード条件追加 
		//審査担当者コード != nullの場合、審査担当者コード条件を追加する。  
		//" and checker_code like ? "  
        if (l_strCheckerCode != null)
        {
            l_strQueryString.append(" and checker_code like ? ");
        }
        
		//１０）　@審査状況条件追加 
		//１０－１）審査状況 = "1"(審査待ち)の場合 
		//審査区分 = "0"(審査待ち)　@の条件を追加する。  
		//" and check_div = '0' "  
        if (WEB3AccOpenCheckStateDivDef.JUDGE_WAITING.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div = '0' ");
        }
        
		//１０－２）審査状況 = "2"(審査済み)の場合 
		//審査区分 = "1"(認可) or "2"(否認)　@の条件を追加する。  
		//" and check_div in ('1', '2') "  
        else if (WEB3AccOpenCheckStateDivDef.JUDGE_COMPLETE.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div in ('1', '2') ");
        }
        
		//１０－３）審査状況 = "3"(承認済)の場合 
		//審査区分 = "1"(認可)　@の条件を追加する。  
		//" and check_div = '1' "  
        else if (WEB3AccOpenCheckStateDivDef.APPROVAL_COMPLETE.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div = '1' ");
        }
		//１０－４）審査状況 = "4"(否認済)の場合 
		//審査区分 = "2"(否認)　@の条件を追加する。  
		//" and check_div = '2' "  
        else if (WEB3AccOpenCheckStateDivDef.OPEN_COMPLETE.equals(l_strCheckState))
        {
            l_strQueryString.append(" and check_div = '2' ");
        }
        
		//１１）検索条件文字列インスタンス.toString() を返却する 
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString.toString();
    }
    
    /**
     * (create検索条件データコンテナ-審査対象識別コード一覧)<BR>
     * 検索条件データコンテナを編集する。<BR>  
     * <BR>
     * １）　@戻り値生成<BR>  
     * 　@戻り値編集用インスタンス（：ArrayList）を生成<BR>  
     * <BR>
     * ２）　@証券会社コード条件追加<BR>  
     * 　@検索条件データコンテナインスタンスに、証券会社コードを追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@証券会社コード <BR>
     * <BR>
     * ３）　@部店コード条件追加<BR>  
     * 　@部店コード != "000"(全部店)の場合、<BR>  
     * 　@検索条件データコンテナインスタンスに、部店コードを追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@部店コード <BR>
     * <BR>
     * ４）　@顧客コード条件追加<BR>  
     * 　@顧客コード != nullの場合、<BR> 
     * 　@検索条件データコンテナインスタンスに、顧客コードを追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@顧客コード＋'％' <BR>
     * <BR>
     * ５）　@識別コード条件追加<BR>  
     * 　@識別コード != nullの場合、<BR> 
     * 　@検索条件データコンテナインスタンスに、識別コードを追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@識別コード＋'％' <BR>
     * <BR>
     * ６）　@発生日（自）条件追加<BR>  
     * 　@発生日（自） != nullの場合、<BR> 
     * 　@検索条件データコンテナインスタンスに、発生日（自）を追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@発生日（自） <BR>
     * <BR>
     * ７）　@発生日（至）条件追加<BR>  
     * 　@発生日（至） != nullの場合、<BR> 
     * 　@検索条件データコンテナインスタンスに、発生日（至）を追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@発生日（至）<BR> 
     * <BR>
     * ８）　@審査種別条件追加 <BR>
     * 　@審査種別 != "0"(全て)の場合、<BR> 
     * 　@検索条件データコンテナインスタンスに、審査種別を追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@審査種別 <BR>
     * <BR>
     * ９）　@審査担当者コード条件追加<BR>  
     * 　@審査担当者コード != nullの場合、<BR> 
     * 　@検索条件データコンテナインスタンスに、審査担当者コードを追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@審査担当者コード＋'％' <BR>
     * <BR>
     * １０）　@配列を返却<BR>  
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR> 
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード
     * @@param l_strOccurDateFrom - (発生日（自）)<BR>
     * 発生日（自）<BR>
     * <BR>
     * YYYYMMDD形式<BR>
     * @@param l_strOccurDateTo - (発生日（至）)<BR>
     * 発生日（至） <BR>
     * <BR>
     * YYYYMMDD形式<BR>
     * @@param l_strReviewCode - (審査種別)<BR>
     * 審査種別
     * @@param l_strCheckerCode - (審査担当者コード)<BR>
     * 審査担当者コード
     * @@return String[]
     */
    private String[] createQueryDataContainerForJudgeObjectAccOpenRequestNumberList(
            String l_strInstitutionCode,
            String l_strBranchCode,
            String l_strAccountCode,
            String l_strRequestNumber,
            String l_strOccurDateFrom,
            String l_strOccurDateTo,
            String l_strReviewCode,
            String l_strCheckerCode)
    {
        final String STR_METHOD_NAME = 
            " createQueryDataContainerForJudgeObjectAccOpenRequestNumberList(" +
            "String, String, String, String, String, " +
            "String, String, String)"; 
        log.entering(STR_METHOD_NAME);
        
		//１）　@戻り値生成  
		//戻り値編集用インスタンス（：ArrayList）を生成  
        ArrayList l_lisQueryContainer = new ArrayList();

		//２）　@証券会社コード条件追加  
		//検索条件データコンテナインスタンスに、証券会社コードを追加する。  
		//[add()に指定する引数]  
		//証券会社コード 
        l_lisQueryContainer.add(l_strInstitutionCode);
        
		//３）　@部店コード条件追加  
		//部店コード != "000"(全部店)の場合、  
		//検索条件データコンテナインスタンスに、部店コードを追加する。  
		//[add()に指定する引数]  
		//部店コード 
        if (!WEB3BranchCodeDef.DEFAULT.equals(l_strBranchCode))
        {
            l_lisQueryContainer.add(l_strBranchCode);
        }

        //４）　@顧客コード条件追加  
		//顧客コード != nullの場合、 
		//検索条件データコンテナインスタンスに、顧客コードを追加する。  
		//[add()に指定する引数]  
		//顧客コード＋'％' 
        if (l_strAccountCode != null)
        {
            l_lisQueryContainer.add(l_strAccountCode + '%');
        }  

        //５）　@識別コード条件追加  
		//識別コード != nullの場合、 
		//検索条件データコンテナインスタンスに、識別コードを追加する。  
		//[add()に指定する引数]  
		//識別コード＋'％' 
        if (l_strRequestNumber != null)
        {
            l_lisQueryContainer.add(l_strRequestNumber + '%');
        } 

        //６）　@発生日（自）条件追加  
		//発生日（自） != nullの場合、 
		//検索条件データコンテナインスタンスに、発生日（自）を追加する。  
		//[add()に指定する引数]  
		//発生日（自） 
        if (l_strOccurDateFrom != null)
        {
            l_lisQueryContainer.add(l_strOccurDateFrom);
        }

        //７）　@発生日（至）条件追加  
		//発生日（至） != nullの場合、 
		//検索条件データコンテナインスタンスに、発生日（至）を追加する。  
		//[add()に指定する引数]  
		//発生日（至） 
        if (l_strOccurDateTo != null)
        {
            l_lisQueryContainer.add(l_strOccurDateTo);
        }

        //８）　@審査種別条件追加 
		//審査種別 != "0"(全て)の場合、 
		//検索条件データコンテナインスタンスに、審査種別を追加する。  
		//[add()に指定する引数]  
		//審査種別 
        if (!WEB3ReviewCodeDef.DEFAULT.equals(l_strReviewCode))
        {
            l_lisQueryContainer.add(l_strReviewCode);
        }

        //９）　@審査担当者コード条件追加  
		//審査担当者コード != nullの場合、 
		//検索条件データコンテナインスタンスに、審査担当者コードを追加する。  
		//[add()に指定する引数]  
		//審査担当者コード＋'％' 
        if (l_strCheckerCode != null)
        {
            l_lisQueryContainer.add(l_strCheckerCode + '%');
        } 
        
        //１０）　@配列を返却  
		//戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }
    
    /**
     * (createソート条件文字列-審査対象識別コード一覧 )<BR>
     * ソート条件文字列を編集する。<BR>  
     * <BR>
     * １）引数のソートキーが示すキー項目を、口座開設審査待ち列物理名に変換する <BR> 
     * 発生日時（occurredDate）の場合、口座開設審査待ち.作成日時<BR> 
     * 識別コード（requestNumber）の場合、口座開設審査待ち.識別コード <BR>  
     * ２）ソートキーの指定の通り、ソート条件文字列（order by句）を編集し返却する。<BR>  
     * <BR>
     * ※　@ソートキーに指定される項目は以下の通り。<BR> 
     * 　@口座開設審査待ち.作成日時 <BR>
     * 　@口座開設審査待ち.識別コード <BR>
     * @@param l_sortKeys (ソートキー)<BR>
     * 口座開設ソートキーの配列
     * @@return String
     * @@throws WEB3BaseException
     */
    private String createSortCondForJudgeObjectAccOpenRequestNumberList(
        WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createSortCondForJudgeObjectAccOpenRequestNumberList(WEB3AccOpenSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug(" __parameter_error__ ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
		//１）引数のソートキーが示すキー項目を、口座開設審査待ち列物理名に変換する 
		//ソートキーの指定の通り、ソート条件文字列（order by句）を編集し返却する。  
		//※　@ソートキーに指定される項目は以下の通り。        
        String l_strSortCond = "";
        int l_intSortKeyLength = l_sortKeys.length;
        
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            WEB3AccOpenSortKey l_sortKey = l_sortKeys[i];
            if (l_sortKey == null)
            {
                log.debug("WEB3AccOpenSortKey is Null");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }

            //口座開設審査待ち.作成日時
            if (WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += Web3AccOpenSortKeyDef.CREATED_TIMESTAMP;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            
            //口座開設審査待ち.識別コード
            else if (WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_sortKey.keyItem))
            {
                l_strSortCond += Web3AccOpenSortKeyDef.ACC_OPEN_REQUEST_NUMBER;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
        }

        l_strSortCond = 
            l_strSortCond.substring(0, l_strSortCond.length() - 2) + " ";

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;          
    }
    
    /**
     * (select審査対象一覧)<BR>
     * 指定している条件を生成し、口座開設審査待ち一覧を取得する。<BR> 
     * <BR>
     * １）　@検索条件文字列インスタンス（：String）を生成<BR>  
     * 　@this.create検索条件文字列-審査対象一覧() から取得する<BR> 
     * <BR>
     * 　@[引数]<BR> 
     * 　@　@識別コード[]<BR> 
     * <BR>
     * ２）　@検索条件データコンテナインスタンス（：String[]）を生成<BR>  
     * 　@this.create検索条件データコンテナ-審査対象一覧() から取得する <BR>
     * <BR>
     * 　@[引数]<BR> 
     * 　@　@証券会社コード <BR>
     * 　@　@識別コード[]<BR> 
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、<BR>
     * 口座開設審査待ちオブジェクトのListを取得する。<BR>  
     * 　@該当データがない場合、0を返却する <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数]<BR>  
     * 　@　@rowType：　@口座開設審査待ちRow.TYPE <BR> 
     * 　@　@where：　@検索条件文字列インスタンス <BR>
     * 　@　@bindVars：　@検索条件データコンテナインスタンス<BR> 
     * <BR>
     * ４）　@口座開設審査待ちオブジェクトのListを口座開設審査待ちデータにセットする<BR> 
     * <BR>
     * ５）　@口座開設審査待ちオブジェクトのListの件数を取得し、返却する<BR> 
     * @@param l_strRequestNumbers - (識別コード)<BR>
     * 識別コードの一覧
     * @@return int
     * @@throws WEB3BaseException
     */
    public int selectJudgeObjectList(
            String l_strInstitutionCode,
            String[] l_strRequestNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " selectJudgeObjectList(String[])";
        log.entering(STR_METHOD_NAME);
        
		//１）　@検索条件文字列インスタンス（：String）を生成  
		//this.create検索条件文字列-審査対象一覧() から取得する 
		//[引数] 
		//識別コード[] 
        String l_strQueryString = 
            this.createQueryStringForJudgeObjectList(l_strRequestNumbers);
        
		//２）　@検索条件データコンテナインスタンス（：String[]）を生成  
		//this.create検索条件データコンテナ-審査対象一覧() から取得する 
		//[引数] 
        //証券会社コード
		//識別コード[]
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainerForJudgeObjectList(l_strInstitutionCode, l_strRequestNumbers);
        
		//３）　@QueryProcessor.doFindAllQuery( )により、口座開設審査待ちオブジェクトのListを取得する。  
        List l_lisAccOpenWaitingRecords = null;
		try
        {
			//[doFindAllQuery()に指定する引数]  
			//rowType：　@口座開設審査待ちRow.TYPE  
			//where：　@検索条件文字列インスタンス 
			//bindVars：　@検索条件データコンテナインスタンス
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenWaitingRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenWaitingRow.TYPE,
                    l_strQueryString,
                    l_strQueryDataContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
		//該当データがない場合、0を返却する 
        if (l_lisAccOpenWaitingRecords == null || 
            l_lisAccOpenWaitingRecords.isEmpty())
        {
	        log.exiting(STR_METHOD_NAME);
	        return 0;
        }
        
		//４）口座開設審査待ちオブジェクトのListを口座開設審査待ちデータにセットする
        for (int i = 0; i < l_lisAccOpenWaitingRecords.size(); i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) l_lisAccOpenWaitingRecords.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
            if (this.accOpenWaitingParamsList != null)
            {
                this.accOpenWaitingParamsList.add(l_accOpenWaitingParams);
            }
        }
        
		//５）口座開設審査待ちオブジェクトのListの件数を取得し、返却する
        log.exiting(STR_METHOD_NAME);
        return l_lisAccOpenWaitingRecords.size();
        
    }
    
    /**
     * (select審査対象一覧)<BR>
     * 指定している条件を生成し、口座開設審査待ち一覧を取得する。<BR> 
     * <BR>
     * １）　@検索条件文字列インスタンス（：String）を生成  <BR>
     * 　@this.create検索条件文字列-審査対象一覧() から取得する <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@識別コード[] <BR>
     * 
     * ２）　@検索条件データコンテナインスタンス（：String[]）を生成<BR>  
     * 　@this.create検索条件データコンテナ-審査対象一覧() から取得する<BR> 
     * <BR>
     * 　@[引数] <BR>
     * 　@　@証券会社コード <BR>
     * 　@　@識別コード[] <BR>
     * <BR>
     * ３）　@ソート条件文字列インスタンス（：String）を生成  <BR>
     * 　@this.createソート条件文字列-審査対象一覧() から取得する <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@ソートキー <BR>
     * <BR>
     * ４）　@QueryProcessor.doFindAllQuery( )により、<BR>
     * 口座開設審査待ちオブジェクトのListを取得する。<BR>  
     * 　@該当データがない場合、0を返却する <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数]  <BR>
     * 　@　@rowType：　@口座開設審査待ちRow.TYPE <BR> 
     * 　@　@where：　@検索条件文字列インスタンス <BR>
     * 　@　@orderBy：　@ソート条件文字列インスタンス <BR>
     * 　@　@conditions：　@null  <BR>
     * 　@　@bindVars：　@検索条件データコンテナインスタンス <BR>
     * <BR>
     * ５）　@口座開設審査待ちオブジェクトのListを口座開設審査待ちデータにセットする<BR> 
     * <BR>
     * ６）　@口座開設審査待ちオブジェクトのListの件数を取得し、返却する<BR> 
     * @@param l_strRequestNumbers - (識別コード)<BR>
     * 識別コードの一覧
     * @@param l_sortKeys (ソートキー)<BR>
     * 口座開設ソートキーの配列
     * @@return int
     * @@throws WEB3BaseException
     */
    public int selectJudgeObjectList(
	        String l_strInstitutionCode,
	        String[] l_strRequestNumbers, WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " selectJudgeObjectList(String[], WEB3AccOpenSortKey[])";
        log.entering(STR_METHOD_NAME);

        //１）　@検索条件文字列インスタンス（：String）を生成  
		//this.create検索条件文字列-審査対象一覧() から取得する
		//[引数] 
		//識別コード[] 
        String l_strQueryString = 
            this.createQueryStringForJudgeObjectList(l_strRequestNumbers);
        
		//２）　@検索条件データコンテナインスタンス（：String[]）を生成  
		//this.create検索条件データコンテナ-審査対象一覧() から取得する 
		//[引数] 
        //証券会社コード
		//識別コード[]
        String[] l_strQueryDataContainers = 
            this.createQueryDataContainerForJudgeObjectList(l_strInstitutionCode, l_strRequestNumbers);
        
		//３）　@ソート条件文字列インスタンス（：String）を生成  
		//this.createソート条件文字列-審査対象一覧() から取得する 
		//[引数] 
		//ソートキー 
        String l_sortKey = 
            this.createSortCondStringForJudgeObjectList(l_sortKeys);
        
		//４）　@QueryProcessor.doFindAllQuery( )により、口座開設審査待ちオブジェクトのListを取得する。  
		//該当データがない場合、nullを返却する 
        List l_lisAccOpenWaitingRecords = null;
		try
        {
			//[doFindAllQuery()に指定する引数]  
			//rowType：　@口座開設審査待ちRow.TYPE  
			//where：　@検索条件文字列インスタンス 
			//orderBy：　@ソート条件文字列インスタンス 
			//conditions：　@null  
			//bindVars：　@検索条件データコンテナインスタンス。 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenWaitingRecords =
                l_queryProcessor.doFindAllQuery(
                    AccOpenWaitingRow.TYPE,
                    l_strQueryString,
                    l_sortKey,
                    null,
                    l_strQueryDataContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                + "."
                + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        
		//該当データがない場合、0を返却する 
        if (l_lisAccOpenWaitingRecords == null || 
            l_lisAccOpenWaitingRecords.isEmpty())
        {
	        log.exiting(STR_METHOD_NAME);
	        return 0;
        }
        
		//５）口座開設審査待ちオブジェクトのListを口座開設審査待ちデータにセットする 
        for (int i = 0; i < l_lisAccOpenWaitingRecords.size(); i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) l_lisAccOpenWaitingRecords.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
            if (this.accOpenWaitingParamsList != null)
            {
                this.accOpenWaitingParamsList.add(l_accOpenWaitingParams);
            }
        }
        
		//６）口座開設審査待ちオブジェクトのListの件数を取得し、返却する
        log.exiting(STR_METHOD_NAME);
        return l_lisAccOpenWaitingRecords.size();
        
    }
    
    /**
     * (create検索条件文字列-審査対象一覧)<BR>
     * 検索条件文字列を編集する。<BR>  
     * <BR>
     * １）　@戻り値生成  <BR>
     * 　@戻り値の検索条件文字列インスタンス（：StringBuffer）を生成 <BR> 
     * <BR>
     * ２）　@証券会社コード条件追加  <BR>
     * 　@証券会社コード条件を追加する。<BR>  
     * <BR>
     * 　@" institution_code = ? "  <BR>
     * <BR>
     * ３）　@識別コード条件追加  <BR>
     * 　@識別コード条件を追加する。<BR>
     * <BR>
     * 　@" and acc_open_request_number in (?,?,…,?) " <BR> 
     * <BR>
     * 　@? は識別コードの件数分追加する <BR>
     * <BR>
     * ４）検索条件文字列インスタンス.toString() を返却する <BR>
     * @@param l_strRequestNumbers - (識別コード)<BR>
     * 識別コードの一覧
     * @@return String
     * @@throws WEB3BaseException
     */
    private String createQueryStringForJudgeObjectList(
        String[] l_strRequestNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createQueryStringForJudgeObjectList(String[])";
        log.entering(STR_METHOD_NAME);
        
		//１）　@戻り値生成  
		//戻り値の検索条件文字列インスタンス（：StringBuffer）を
        StringBuffer l_strQueryString = new StringBuffer();
        String l_strQueryStringReturn = "";
        
        int l_intRequestNumbers = 0;
        if (l_strRequestNumbers != null && l_strRequestNumbers.length != 0)
        {
            l_intRequestNumbers = l_strRequestNumbers.length;
        }
        
		//２）　@証券会社コード条件追加  
		//証券会社コード条件を追加する。
        //" institution_code = ? "  
        l_strQueryString.append(" institution_code = ? ");

		//３）　@識別コード条件追加  
		//識別コード条件を追加する。  
		//" acc_open_request_number in (?,?,…,?) "  
		//? は識別コードの件数分追加する
        
        l_strQueryString.append(" and acc_open_request_number in (");
        
        for (int i = 0; i < l_intRequestNumbers; i++)
        {
            l_strQueryString.append("?, ");  
        }
        
        l_strQueryStringReturn = 
            l_strQueryString.substring(0, l_strQueryString.length() - 2) + ") ";
        
		//４）検索条件文字列インスタンス.toString() を返却する 
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringReturn;
    }
    
    /**
     * (create検索データコンテナ-審査対象一覧)<BR>
     * 検索条件データコンテナを編集する。<BR>  
     * <BR>
     * １）　@戻り値生成 <BR> 
     * 　@戻り値編集用インスタンス（：ArrayList）を生成<BR>  
     * <BR>
     * ２）　@証券会社コード条件追加<BR>  
     * 　@検索条件データコンテナインスタンスに、証券会社コードを追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@証券会社コード <BR>
     * <BR>
     * ３）　@識別コード条件追加<BR>  
     * 　@検索条件データコンテナインスタンスに、識別コードを件数分追加する。<BR>  
     * <BR>
     * 　@[add()に指定する引数]<BR>  
     * 　@識別コード <BR>
     * <BR>
     * ４）　@配列を返却<BR>  
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR> 
     * @@param l_strRequestNumbers - (識別コード)<BR>
     * 識別コードの一覧
     * @@return String[]
     * @@throws WEB3BaseException
     */
    private String[] createQueryDataContainerForJudgeObjectList(
        String l_strInstitutionCode,
        String[] l_strRequestNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createQueryDataContainerForJudgeObjectList(String[])";
        log.entering(STR_METHOD_NAME);
        
        int l_intRequestNumbers = 0;
        if (l_strRequestNumbers != null && l_strRequestNumbers.length != 0)
        {
            l_intRequestNumbers = l_strRequestNumbers.length;
        }
        
		//１）　@戻り値生成  
		//戻り値編集用インスタンス（：ArrayList）を生成 
        ArrayList l_lisJudgeObjectList = new ArrayList();

		//２）　@証券会社コード条件追加  
		//検索条件データコンテナインスタンスに、証券会社コードを追加する。  
		//[add()に指定する引数]  
		//証券会社コード 
        l_lisJudgeObjectList.add(l_strInstitutionCode);
        
		//３）　@識別コード条件追加  
		//検索条件データコンテナインスタンスに、識別コードを件数分追加する。  
		//[add()に指定する引数]  
		//識別コード 
        for(int i = 0 ; i < l_intRequestNumbers; i++)
        {
            l_lisJudgeObjectList.add(l_strRequestNumbers[i]);
        }
        
		//４）配列を返却  
		//戻り値編集用インスタンス（：ArrayList）.toArray()を返却する
        String[] l_strRequestNumber = new String[l_lisJudgeObjectList.size()];
        l_lisJudgeObjectList.toArray(l_strRequestNumber);
        
        log.exiting(STR_METHOD_NAME);
        return l_strRequestNumber;
    }
    
    /**
     * (createソート条件文字列-審査対象一覧)<BR>
     * ソート条件文字列を編集する。<BR>  
     * <BR>
     * １）引数のソートキーが示すキー項目を、口座開設審査待ち列物理名に変換する<BR>  
     * 発生日時（occurredDate）の場合、口座開設審査待ち.作成日時<BR>  
     * 識別コード（requestNumber）の場合、口座開設審査待ち.識別コード <BR> 
     * ２）ソートキーの指定の通り、ソート条件文字列（order by句）を編集し返却する。<BR>  
     * <BR>
     * ※　@ソートキーに指定される項目は以下の通り。<BR> 
     * 　@口座開設審査待ち.作成日時 <BR>
     * 　@口座開設審査待ち.識別コード <BR>
     * ３）引数に識別コードが含まれていない場合、<BR>  
     * 次の項目をソート条件文字列に追加する <BR>  
     * 　@キー項目：「識別コード」 <BR>  
     * 　@昇順／降順：「A：昇順」 <BR>  
     * ４）次の項目をソート条件文字列に追加する <BR>  
     * 　@キー項目：「通番」 <BR>  
     * 　@昇順／降順：「A：昇順」 <BR>  
     * <BR>  
     * @@param l_sortKeys (ソートキー)<BR>
     * 口座開設ソートキーの配列
     * @@return String
     * @@throws WEB3BaseException
     */
    private String createSortCondStringForJudgeObjectList(
        WEB3AccOpenSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createSortCondStringForJudgeObjectList(WEB3AccOpenSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        int l_intSortKeysLength = 0;
        if (l_sortKeys != null && l_sortKeys.length != 0)
        {
            l_intSortKeysLength = l_sortKeys.length;
        }
        
		//１）引数のソートキーが示すキー項目を、口座開設審査待ち列物理名に変換する、  
		//ソートキーの指定の通り、ソート条件文字列（order by句）を編集し返却する。  
		//※　@ソートキーに指定される項目は以下の通り。        
        String l_strSortCond = "";
        String l_strSortKey = "";
        
        boolean l_blnIsAccOpenRequestNumber = false;
        
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            WEB3AccOpenSortKey l_sortKey = l_sortKeys[i];
            if (l_sortKey == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }

            //３）引数に識別コードが含まれていない場合、次の項目をソート条件文字列に追加する 
    		//キー項目：「識別コード」 
    		//昇順／降順：「A：昇順」
            
            //口座開設審査待ち.作成日時
            if(WEB3AccOpenFromWebSortKeyDef.OCCRRRED_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += Web3AccOpenSortKeyDef.CREATED_TIMESTAMP;
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }   
            }
            else if (WEB3AccOpenFromWebSortKeyDef.REQUEST_NUMBER.equals(l_sortKey.keyItem))
            {
                l_blnIsAccOpenRequestNumber = true;
                l_strSortKey = l_sortKey.ascDesc;
                l_strSortCond += Web3AccOpenSortKeyDef.ACC_OPEN_REQUEST_NUMBER;
                if (WEB3AscDescDef.ASC.equals(l_strSortKey))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }

            }
        }
        
        //口座開設審査待ち.識別コード
        if (!l_blnIsAccOpenRequestNumber)
        {
            l_strSortCond += Web3AccOpenSortKeyDef.ACC_OPEN_REQUEST_NUMBER; 
            l_strSortCond += " asc, "; 
        }

        //４）次の項目をソート条件文字列に追加する 
		//キー項目：「通番」 
		//昇順／降順：「A：昇順」
        
        //口座開設審査待ち.通番
        l_strSortCond += Web3AccOpenSortKeyDef.SERIAL_NO + " asc ";

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;       
    }
    
    /**
     * (insert口座開設審査待ち)<BR>
     * 口座開設審査待ちデータの内容をinsertする <BR>
     * <BR>
     * １）処理日時を取得する。<BR> 
     *　@処理日時（登録用） = GtlUtils.getTradingSystem().getSystemTimestamp()<BR>  
     *<BR> 
     *　@２）口座開設審査待ちデータの件数分Loopする<BR> 
     * 　@口座開設審査待ちデータ[Index].作成日時 = 処理日時（登録用）<BR> 
     * 　@口座開設審査待ちデータ[Index].更新日時 = 処理日時（登録用）<BR> 
     * <BR>
     *	２－２）QueryProcessor.doInsertQuery()メソッドをコールする。<BR> 
     * <BR>
     * 　@[doInsertQuery()にセットするパラメータ] <BR> 
     * 　@　@arg0：　@口座開設審査待ちデータ[Index] <BR>
     * <BR>
     * ※ 口座開設審査待ちデータには、<BR>
     * 口座開設審査待ちParamのオブジェクトがセットされている<BR> 
     * <BR> 
     * @@throws WEB3BaseException
     */
    public void insertAccOpenWaiting() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " insertAccOpenWaiting()";
        log.entering(STR_METHOD_NAME);
        
		//１）処理日時を取得する。
        //処理日時（登録用） = GtlUtils.getTradingSystem().getSystemTimestamp()
        Timestamp l_tsTradingSystem = 
            GtlUtils.getTradingSystem().getSystemTimestamp();

        int l_intAccOpenWaitingParamsSize = 0;
        
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size(); 
        }
        
        //２）口座開設審査待ちデータの件数分Loopする 
        
        for (int i = 0; i < l_intAccOpenWaitingParamsSize; i++)
        {
            try
            {
                AccOpenWaitingRow l_accOpenWaitingRow = 
                    (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(i);
                AccOpenWaitingParams l_accOpenWaitingParams = 
                    new AccOpenWaitingParams(l_accOpenWaitingRow);

                //２－１）作成日時、更新日時に処理日時（登録用）をセットする。 

                //口座開設審査待ちデータ[Index].作成日時 = 処理日時（登録用） 
                l_accOpenWaitingParams.setCreatedTimestamp(l_tsTradingSystem);
                //口座開設審査待ちデータ[Index].更新日時 = 処理日時（登録用） 
                l_accOpenWaitingParams.setLastUpdatedTimestamp(l_tsTradingSystem);

        		//２－２）QueryProcessor.doInsertQuery()メソッドをコールする。  
        		//[doInsertQuery()にセットするパラメータ] 
        		//arg0：口座開設審査待ちデータ[Index]
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_accOpenWaitingParams);
            }
            catch (DataFindException l_dfe)
            {
                log.error(STR_METHOD_NAME, l_dfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(STR_METHOD_NAME, l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update口座開設審査待ち)<BR>
     * 口座開設審査待ちデータの内容をupdateする <BR>
     * <BR>
     *１）処理日時を取得する。<BR> 
     * 　@処理日時（登録用） = GtlUtils.getTradingSystem().getSystemTimestamp() <BR> 
     *２）口座開設審査待ちデータの件数分Loopする<BR> 
     * <BR>
     *２－１）更新日時に処理日時（登録用）をセットする。<BR> 
     * 　@口座開設審査待ちデータ[Index].更新日時 = 処理日時（登録用） <BR> 
     *２－２）QueryProcessor.doUpdateQuery()メソッドをコールする。<BR> 
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>  
     * 　@　@arg0：　@口座開設審査待ちデータ[Index] <BR>
     * <BR>
     * ※ 口座開設審査待ちデータには、<BR>
     * 口座開設審査待ちParamのオブジェクトがセットされている<BR> 
     * <BR> 
     * @@throws WEB3BaseException
     */
    public void updateAccOpenWaiting() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " updateAccOpenWaiting()";
        log.entering(STR_METHOD_NAME);
        
		//１）処理日時を取得する。
        //処理日時（登録用） = GtlUtils.getTradingSystem().getSystemTimestamp()
        Timestamp l_tsTradingSystem = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
        
		//２）口座開設審査待ちデータの件数分Loopする 
        int l_intAccOpenWaitingParamsSize = 0;
        
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size(); 
        }

        for (int i = 0; i < l_intAccOpenWaitingParamsSize; i++)
        {
            try
            {
                //２－１）更新日時に処理日時（登録用）をセットする。 
                AccOpenWaitingRow l_accOpenWaitingRow = 
                    (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(i);
                AccOpenWaitingParams l_accOpenWaitingParams = 
                    new AccOpenWaitingParams(l_accOpenWaitingRow);
                
                //口座開設審査待ちデータ[Index].更新日時 = 処理日時（登録用）
                l_accOpenWaitingParams.setLastUpdatedTimestamp(l_tsTradingSystem);
                
        		//２－２）QueryProcessor.doUpdateQuery()メソッドをコールする。   
        		//[doUpdateQuery()にセットするパラメータ]  
        		//arg0：口座開設審査待ちデータ[Index]
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_accOpenWaitingParams);
            }
            catch (DataFindException l_dfe)
            {
                log.error(STR_METHOD_NAME, l_dfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(STR_METHOD_NAME, l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                        + "."
                        + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is審査済)<BR>
     * 口座開設審査待ちデータの内容が審査済かチェックする<BR> 
     * <BR>
     * １）口座開設審査待ちデータの件数分Loopする <BR>
     * <BR>
     * ２）口座開設審査待ちデータ[Index].審査区分 != "0"(審査待ち)の場合<BR> 
     * 　@true を返却する <BR>
     * <BR>
     * ３）すべての口座開設審査待ちデータの審査区分が"0"(審査待ち)の場合 <BR>
     * 　@false を返却する <BR>
     * <BR>
     * ※ 口座開設審査待ちデータには、<BR>
     * 口座開設審査待ちParamのオブジェクトがセットされている<BR> 
     * @@return boolean
     */
    public boolean isChecked()
    {
        final String STR_METHOD_NAME = " isChecked()";
        log.entering(STR_METHOD_NAME);
        
		//１）口座開設審査待ちデータの件数分Loopする
        int l_intAccOpenWaitingParamsSize = 0;
        
        if (this.accOpenWaitingParamsList != null)
        {
            l_intAccOpenWaitingParamsSize = 
                this.accOpenWaitingParamsList.size(); 
        }
        
        int l_intSize = 0;
        for (int i = 0; i < l_intAccOpenWaitingParamsSize; i++)
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(i);
            AccOpenWaitingParams l_accOpenWaitingParams = 
                new AccOpenWaitingParams(l_accOpenWaitingRow);
            
            //２）口座開設審査待ちデータ[Index].審査区分 != "0"(審査待ち)の場合 
    		//true を返却する
            
            if (!WEB3CheckDivDef.CHECK_WAITING.equals(l_accOpenWaitingParams.getCheckDiv()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;  
            }
            else
            {
                l_intSize++;
            }
        }
        
        if (l_intSize == l_intAccOpenWaitingParamsSize)
        {
    		//３）すべての口座開設審査待ちデータの審査区分が"0"(審査待ち)の場合 
    		//false を返却する 
            log.exiting(STR_METHOD_NAME);
            return false;  
        }
        
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get審査区分)<BR>
     * 指定した取得行番号の口座開設審査待ち.審査区分を返却する。<BR> 
     * <BR>
     * １）this.get口座開設審査待ち行数から全件数を取得する。<BR> 
     * <BR>
     * ２）取得行番号のチェック <BR>
     * 　@取得行番号 < 0 または 取得行番号 >= 全件数　@の場合 <BR>
     * 　@null を返却する <BR>
     * <BR>
     * ３）口座開設審査待ちデータ.get(取得行番号)で <BR>
     * 　@口座開設審査待ちParamsオブジェクトを取得する <BR>
     * <BR>
     * ４）口座開設審査待ちParams.審査区分を返却する<BR> 
     * <BR>
     * ※ 口座開設審査待ちParamsクラスはDDLより自動生成する。<BR>
     * @@param l_intGetLineNo - (取得行番号) <BR>
     * 取得したい口座開設審査待ちデータの行数<BR>
     * @@return String
     */
    public String getCheckDiv(int l_intGetLineNo)
    {
        final String STR_METHOD_NAME = 
            " getCheckDiv(int l_intGetLineNo)";
        log.entering(STR_METHOD_NAME);
        
		//１）this.get口座開設審査待ち行数から全件数を取得する。 
        int l_intAccOpenWaitingParamsSize = 
            this.getAccOpenWaitingParamsNumber();
        
		//２）取得行番号のチェック 
		//　@取得行番号 < 0 または 取得行番号 >= 全件数　@の場合 
		//　@null を返却する 
        if (l_intGetLineNo < 0 || l_intGetLineNo >= l_intAccOpenWaitingParamsSize)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
		//３）口座開設審査待ちデータ.get(取得行番号)で 
		//　@口座開設審査待ちParamsオブジェクトを取得する 
        String l_strCheckDiv = null;
        
        if (this.accOpenWaitingParamsList != null && 
            !this.accOpenWaitingParamsList.isEmpty())
        {
            AccOpenWaitingRow l_accOpenWaitingRow = 
                (AccOpenWaitingRow) this.accOpenWaitingParamsList.get(
                    l_intGetLineNo);
            
    		//４）口座開設審査待ちParams.審査区分を返却する
            l_strCheckDiv = l_accOpenWaitingRow.getCheckDiv();
            log.debug("口座開設審査待ちParams.審査区分 = " + l_strCheckDiv);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCheckDiv;
    }
}
@
