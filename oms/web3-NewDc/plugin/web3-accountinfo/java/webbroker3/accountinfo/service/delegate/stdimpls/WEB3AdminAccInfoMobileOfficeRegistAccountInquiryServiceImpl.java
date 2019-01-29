head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスImpl(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17 カク寛新 (中訊) 新規作成
Revesion History : 2006/12/14 徐大方 (中訊) 仕様変更モデル154,ＤＢ更新仕様040
Revesion History : 2006/12/15 徐大方 (中訊) 仕様変更モデル155,156
Revesion History : 2006/12/20 周捷 (中訊) 仕様変更モデル158
Revesion History : 2007/01/19 何文敏 (中訊) 仕様変更モデル160、ＤＢ更新仕様042
Revesion History : 2007/02/10 謝旋 (中訊) 仕様変更モデル188
Revesion History : 2009/02/12 SCS大嶋 仕様変更モデル255
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMaster;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.WEB3AccInfoOccupationChangeRegistVoucherCreated;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistPK;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.accountinfo.define.WEB3RegistStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeChangeAccount;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountInfoAcceptStatusDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3OccupationcodeUpdateDef;
import webbroker3.common.define.WEB3RealnameUpdateDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.AccountInfoMstPK;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスImpl)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービス実装クラス<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.class);

    /**
     * @@roseuid 418F3A050232
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl()
    {

    }

    /**
     * 
     * 携帯番号・勤務先情報変更申込顧客一覧表示処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報<BR>
     * 変更申込顧客問合せﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報<BR>
     * 変更申込顧客ﾘｸｴｽﾄの場合 <BR>
     * 　@－get情報携帯番号・勤務先情報変更申込顧客一覧()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更申込一括判定確認リクエストの場合<BR>
     * 　@－validate一括判定()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエストの場合<BR>
     * 　@－submit一括判定()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4149722B02CA
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾘｸｴｽﾄの場合
        //－get入力画面()をコールする
        if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest) l_request);
        }
        //引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾘｸｴｽﾄの場合
        //－get情報携帯番号・勤務先情報変更申込顧客一覧()をコールする
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistAccountRequest)
        {
            
            l_response = this.getRegistAccountList((WEB3AdminAccInfoMobileOfficeRegistAccountRequest) l_request);
        }
        //引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更申込一括判定確認リクエストの場合
        //－validate一括判定()をコールする。
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest)
        {
            l_response = this.validateJudgement((WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエストの場合
        //－submit一括判定()をコールする。
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)
        {
            l_response = this.submitJudgement((WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)l_request);
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 携帯番号・勤務先情報変更申込顧客問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（携帯番号・勤務先情報変更申込問合せ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5EC103E6
     */
    protected WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse getInputScreen(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest l_request)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.3) 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse) l_request.createResponse();

        //1.4) プロパティセット
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        
        //前営業日：　@TradingSystem.getSystemTimestamp()の前営業日
        Timestamp l_gentradeBizDate = WEB3GentradeUtils.getBizDate(l_tsSystemTimestamp, -1);
        l_response.previousBizDate = WEB3DateUtility.toDay(l_gentradeBizDate);
        
        //前日：　@TradingSystem.getSystemTimestamp()の前日
        Date l_datPreviousDate = WEB3DateUtility.addDay(l_tsSystemTimestamp,-1);
        l_response.previousDate = WEB3DateUtility.toDay(l_datPreviousDate);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get変更申込顧客一覧)<BR>
     * 携帯番号・勤務先情報変更申込顧客一覧表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（携帯番号・勤務先情報変更申込問合せ）get変更申込顧客一覧」参照。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse
     * @@roseuid 414971670098
     */
    protected WEB3AdminAccInfoMobileOfficeRegistAccountResponse getRegistAccountList(WEB3AdminAccInfoMobileOfficeRegistAccountRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRegistAccountList(WEB3AdminAccInfoMobileOfficeRegistAccountRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.4) validate部店権限(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);

        //1.5) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.6) create検索条件文字列(String, String[], String, Date, Date, String)
        String l_strQueryString =
            this.createQueryString(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode,
                l_request.startDate,
                l_request.endDate,
                l_request.searchApplyStateDiv);

        //1.7) create検索条件データコンテナ(String, String[], String, Date, Date, String)
        String[] l_strQueryContainer =
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode,
                l_request.startDate,
                l_request.endDate,
                l_request.searchApplyStateDiv);
        
        //createソート条件
        String l_sortCondition = this.createSortCondition(l_request.sortKeys);

        //1.8) get携帯番号・勤務先情報変更申込(String, String[])
        List l_lisAccInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_strQueryString, l_strQueryContainer,l_sortCondition);

        //ArrayList()
        //変更前申込顧客Listオブジェクトの生成
        ArrayList l_lisBeforeAccount = new ArrayList();
        
        //ArrayList()
        //変更後申込顧客Listオブジェクトの生成
        ArrayList l_lisAfterAccount = new ArrayList();
        
        if(l_lisAccInfoMobileOfficeInfoRegist == null || l_lisAccInfoMobileOfficeInfoRegist.isEmpty())
        {
            WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response =
                (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
                
            WEB3AccInfoMobileOfficeChangeAccount[] l_beforeAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0]; 

            WEB3AccInfoMobileOfficeChangeAccount[] l_afterAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0];

            l_response.beforeChangeAccountList = l_beforeAccounts;
            l_response.afterChangeAccountList = l_afterAccounts;

            //総ページ数
            l_response.totalPages = "0";

            //総レコード数
            l_response.totalRecords = "0";

            //(表示ページ番号)
            l_response.pageIndex = "1";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //リクエストデータ.口座区分 != null の場合、以下の処理を実行
        WEB3PageIndexInfo l_pageIndexInfo = null;
        if (l_request.accountType != null)
        {
            //get口座区分情報List(List, String)
            //引数の口座区分と合致した条件のListを生成し、返却する。
            //[get口座区分情報List（）に指定する引数]
            //携帯番号・勤務先情報変更申込List ： get携帯番号・勤務先情報変更申込（）の戻り値
            //口座区分 ： リクエストデータ.口座区分
            List l_lstAccountTypeInfo = WEB3AccInfoMobileOfficeInfoRegist.getAccountTypeInfoList(
                l_lisAccInfoMobileOfficeInfoRegist,
                l_request.accountType);
                
            if(l_lstAccountTypeInfo == null || l_lstAccountTypeInfo.isEmpty())
            {
                WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response =
                    (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
                
                WEB3AccInfoMobileOfficeChangeAccount[] l_beforeAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0]; 

                WEB3AccInfoMobileOfficeChangeAccount[] l_afterAccounts = new WEB3AccInfoMobileOfficeChangeAccount[0];

                l_response.beforeChangeAccountList = l_beforeAccounts;
                l_response.afterChangeAccountList = l_afterAccounts;

                //総ページ数
                l_response.totalPages = "0";

                //総レコード数
                l_response.totalRecords = "0";

                //(表示ページ番号)
                l_response.pageIndex = "1";

                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
            //WEB3PageIndexInfoオブジェクトの生成
            //[コンストラクタに指定する引数]
            //l_list ： get口座区分情報List（）の戻り値
            //l_intRequestPageIndex ： リクエストデータ.要求ページ番号
            //l_intRequestPageSize ： リクエストデータ.ページ内表示行数
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lstAccountTypeInfo,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

            //getListReturned()
            //指定したページ行のレコードを取得する。
            List l_lstPageIndexInfo = l_pageIndexInfo.getListReturned();

            //getListReturned()戻り値の各要素毎のLOOP
            int l_intPageIndexInfoLength = l_lstPageIndexInfo.size();
            for (int i = 0; i < l_intPageIndexInfoLength; i++)
            {
                //get変更前申込情報(顧客)
                //顧客オブジェクトより、変更前の携帯番号・勤務先情報変更申込顧客オブジェクトを生成し、
                //返却する。
                //[get変更前申込情報（）に指定する引数]
                //顧客オブジェクト：　@List.get（i）.get顧客（）の戻り値
                WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                    (WEB3AccInfoMobileOfficeInfoRegist)l_lstPageIndexInfo.get(i);
                WEB3AccInfoMobileOfficeChangeAccount l_beforeChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getBeforeChangeInfo(
                        l_accInfoMobileOfficeInfoRegist.getMainAccount());

                //get変更後申込情報
                //携帯番号・勤務先情報変更申込オブジェクトより、変更後の携帯番号・勤務先情報
                //変更申込顧客オブジェクトを生成し、返却する。
                WEB3AccInfoMobileOfficeChangeAccount l_afterChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                //add(arg0 : Object)
                //get変更前申込情報（）の戻り値をadd()
                l_lisBeforeAccount.add(l_beforeChangeAccount);

                //add(arg0 : Object)
                //get変更後申込情報（）の戻り値をadd()
                l_lisAfterAccount.add(l_afterChangeAccount);
            }
        }
        //リクエストデータ.口座区分 == null の場合、以下の処理を実行
        else
        {
            //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
            //WEB3PageIndexInfoオブジェクトの生成
            //[コンストラクタに指定する引数]
            //l_list ： get携帯番号・勤務先情報変更申込（）の戻り値
            //l_intRequestPageIndex ： リクエストデータ.要求ページ番号
            //l_intRequestPageSize ： リクエストデータ.ページ内表示行数
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisAccInfoMobileOfficeInfoRegist,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

            //getListReturned()
            //指定したページ行のレコードを取得する。
            List l_lstPageIndexInfo = l_pageIndexInfo.getListReturned();

            //getListReturned()戻り値の各要素毎のLOOP
            int l_intPageIndexInfoLength = l_lstPageIndexInfo.size();
            for (int i = 0; i < l_intPageIndexInfoLength; i++)
            {
                //get変更前申込情報(顧客)
                //顧客オブジェクトより、変更前の携帯番号・勤務先情報変更申込顧客オブジェクトを生成し、
                //返却する。
                //[get変更前申込情報（）に指定する引数]
                //顧客オブジェクト：　@List.get（i）.get顧客（）の戻り値
                WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                    (WEB3AccInfoMobileOfficeInfoRegist)l_lstPageIndexInfo.get(i);
                WEB3AccInfoMobileOfficeChangeAccount l_beforeChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getBeforeChangeInfo(
                        l_accInfoMobileOfficeInfoRegist.getMainAccount());

                //get変更後申込情報
                //携帯番号・勤務先情報変更申込オブジェクトより、変更後の携帯番号・勤務先情報
                //変更申込顧客オブジェクトを生成し、返却する。
                WEB3AccInfoMobileOfficeChangeAccount l_afterChangeAccount =
                    l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                //add(arg0 : Object)
                //get変更前申込情報（）の戻り値をadd()
                l_lisBeforeAccount.add(l_beforeChangeAccount);

                //add(arg0 : Object)
                //get変更後申込情報（）の戻り値をadd()
                l_lisAfterAccount.add(l_afterChangeAccount);
            }
        }

        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ()
        //レスポンスデータを生成する。
        WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();

        //プロパティセット
        WEB3AccInfoMobileOfficeChangeAccount[] l_beforeAccounts = null;
        if (!l_lisBeforeAccount.isEmpty())
        {
            l_beforeAccounts = new WEB3AccInfoMobileOfficeChangeAccount[l_lisBeforeAccount.size()];
            l_lisBeforeAccount.toArray(l_beforeAccounts);
        }

        WEB3AccInfoMobileOfficeChangeAccount[] l_afterAccounts = null;
        if (!l_lisAfterAccount.isEmpty())
        {
            l_afterAccounts = new WEB3AccInfoMobileOfficeChangeAccount[l_lisAfterAccount.size()];
            l_lisAfterAccount.toArray(l_afterAccounts);
        }

        l_response.beforeChangeAccountList = l_beforeAccounts;
        l_response.afterChangeAccountList = l_afterAccounts;

        //総ページ数
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";

        //総レコード数
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        //(表示ページ番号)
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値の検索条件文字列インスタンス（：String）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード条件を追加する。<BR>
     * <BR>
     * 　@" institution_code =  ? "<BR>
     * <BR>
     * ３）　@部店条件追加<BR>
     * 　@部店条件を追加する。部店コード[]の要素数分"?"を編集する。<BR>
     * <BR>
     * 　@" and branch_id in (?, ?,,,) "<BR>
     * <BR>
     * ４）　@顧客コード条件追加 <BR>
　@   *    顧客指定の場合（顧客コード != null）<BR>
     *    顧客コード指定文字列を検索条件文字列に追加する。<BR>
　@   *   " and account_code like ? "<BR>
     * <BR>
     * ５）　@開始日，終了日条件追加 <BR>
     * 　@開始日，終了日が指定されている場合（開始日 != null && 終了日 != null）、<BR>
     * 　@申込日の範囲指定を文字列インスタンスに追加する。<BR>
     * <BR>
     * 　@" and created_timestamp >= ? " +<BR>
     * 　@" and created_timestamp < ? "<BR>
     * <BR>
     * ６）　@申込状況区分条件追加<BR>
     * 　@申込状況区分が指定されている場合（申込状況区分 != null）、<BR>
     * 　@申込状況区分指定を文字列インスタンスに追加する。<BR>
     * <BR>
     * 　@－申込状況区分 == ”判定待ち”、または”判定待ち（確認中）”の場合<BR>
     * 　@　@※未判定で削除済みの行は表示しない。<BR>
     * 　@　@　@" and (decision = ?  and delete_flag = 0)"<BR>
     * <BR>
     * 　@－申込状況区分 == ”判定済み”の場合<BR>
     * 　@　@　@" and decision in (?, ?)"<BR>
     * <BR>
     * ７）　@文字列インスタンスを返却 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * 
     * @@param l_strAccountCode - 顧客コード
     * @@param l_datStartDate - 開始日
     * @@param l_datEndDate - 終了日
     * @@param l_strRegistStateDiv - 申込状況区分<BR>
     * <BR>
     * 0：　@判定待ち<BR>
     * 1：　@判定待ち（確認中）<BR>
     * 2：　@判定済み<BR>
     * 
     * @@return String
     * @@roseuid 4149773302F9
     */
    protected String createQueryString(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Date l_datStartDate,
        Date l_datEndDate,
        String l_strRegistStateDiv)
    {
        
        final String STR_METHOD_NAME =
            " createQueryString(" +
                " String l_strInstitutionCode, " +
                " String[] l_strBranchCodes, " +
                " String l_strAccountCode, " +
                " Date l_datStartDate, " +
                " Date l_datEndDate, " +
                " String l_strRegistStateDiv)";
        log.entering(STR_METHOD_NAME);

        //１）　@戻り値生成 戻り値の検索条件文字列インスタンス（：String）を生成
        String l_strSearchCond;

        //2) 証券会社条件追加 証券会社コード条件を追加する。
        l_strSearchCond = " institution_code =  ? ";

        //3) 部店条件追加 部店条件を追加する。部店コード[]の要素数分"?"を編集する
        int l_intBranchCodesCnt = l_strBranchCodes.length;
        
        if (l_intBranchCodesCnt > 0)
        {
            StringBuffer l_sbQueryBranchCodes = new StringBuffer();
            
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                if (l_sbQueryBranchCodes.length() != 0)
                {
                    l_sbQueryBranchCodes.append(", ");
                }
                l_sbQueryBranchCodes.append("?");
                
            }                
            l_strSearchCond += " and branch_id in (" + l_sbQueryBranchCodes.toString() + ")";             
        }

        //4) 顧客指定の場合（顧客コード != null）、顧客コード指定文字列を検索条件文字列に追加する。
        if (l_strAccountCode != null && !"".equals(l_strAccountCode) )
        {
            
            l_strSearchCond += " and account_code like ? ";
        }

        //5) 開始日，終了日が指定されている場合（開始日 != null && 終了日 != null）、申込日の範囲指定を文字列インスタンスに追加する。
        if (l_datStartDate != null && l_datEndDate != null)
        {
            
            //l_strSearchCond += " and created_timestamp >= ?  and created_timestamp < ? ";
            l_strSearchCond += " and to_char (created_timestamp , 'YYYYMMDD') >= ? " + " and to_char (created_timestamp, 'YYYYMMDD') < ? ";
        }

        //6) 申込状況区分が指定されている場合（申込状況区分 != null）、申込状況区分指定を文字列インスタンスに追加する。
        if (l_strRegistStateDiv != null && !"".equals(l_strRegistStateDiv))
        {
            
            //申込状況区分 == ”判定待ち”、または”判定待ち（確認中）”の場合
            if (WEB3RegistStateDivDef.WAIT_DECISION.equals(l_strRegistStateDiv)
                || WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING.equals(l_strRegistStateDiv))
            {
                
                l_strSearchCond += " and (decision = ?  and delete_flag = 0)";

            }
            //申込状況区分 == ”判定済み”の場合
            if (WEB3RegistStateDivDef.DECISION_COMPLETE.equals(l_strRegistStateDiv))
            {
                
                l_strSearchCond += " and decision in (?, ?)";
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSearchCond;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード文字列を追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@証券会社コード<BR>
     * <BR>
     * ３）　@部店条件追加<BR>
     * 　@部店コード[]に該当する部店ＩＤをすべて追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]　@<BR>
     * 　@部店ＩＤ※<BR>
     * <BR>
     * 　@※アカウントマネージャ.getBranch(証券会社，部店コード)にて取得する。<BR>
     * 　@※証券会社オブジェクトは、アカウントマネージャ.getInstitution(証券会社コード)<BR>
     * にて取得する。<BR>
     * <BR>
     *４）　@顧客コード条件追加 <BR>
     *　@  顧客指定の場合（顧客コード != null）、顧客コード文字列をリストに追加する。<BR>
     * <BR>
     * 　@ [add()に指定する引数]<BR>
　@   *    顧客コード + "%"<BR>
     * <BR>
     * ５）　@開始日，終了日条件追加 <BR>
     * 　@開始日，終了日が指定されている場合（開始日 != null && 終了日 != null）、<BR>
     * 　@開始日，終了日をリストに追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@開始日を文字列（yyyyMMdd）に変換した値<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@終了日の翌日を文字列（yyyyMMdd）に変換した値<BR>
     * <BR>
     * ６）　@申込状況区分条件追加<BR>
     * 　@申込状況区分が指定されている場合（申込状況区分 != null）、<BR>
     * 　@判定結果指定を文字列インスタンスに追加する。<BR>
     * <BR>
     * 　@－申込状況区分 == ”判定待ち”、または”判定待ち（確認中）”の場合<BR>
     * 　@[add()に指定する引数]<BR>
     * 　@判定結果.0：DEFAULT（未判定）<BR>
     * <BR>
     * 　@－申込状況区分 == ”判定済み”の場合<BR>
     * 　@[add()に指定する引数]<BR>
     * 　@判定結果.1：承認<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@判定結果.2：不可<BR>
     * <BR>
     * ７）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * 
     * @@param l_strAccountCode - 顧客コード
     * @@param l_datStartDate - 開始日
     * @@param l_datEndDate - 終了日
     * @@param l_strRegistStateDiv - 申込状況区分<BR>
     * <BR>
     * 0：　@判定待ち<BR>
     * 1：　@判定待ち（確認中）<BR>
     * 2：　@判定済み<BR>
     * 
     * @@return String[]
     * @@roseuid 41497D650098
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Date l_datStartDate,
        Date l_datEndDate,
        String l_strRegistStateDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(String l_strInstitutionCode," +
                " String[] l_strBranchCodes," +
                " String l_strAccountCode," +
                " Date l_datStartDate," +
                " Date l_datEndDate," +
                " String l_strRegistStateDiv)";
        log.entering(STR_METHOD_NAME);

        //1) 戻り値編集用インスタンス（：ArrayList）を生成
        List l_lisQueryContainer = new ArrayList();

        //2) 証券会社コード文字列を追加する
        l_lisQueryContainer.add(l_strInstitutionCode);

        //3) 部店コード[]に該当する部店ＩＤをすべて追加する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManage = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        try
        {
            
            //証券会社
            Institution l_institution = l_accManage.getInstitution(l_strInstitutionCode);

            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                
                Branch l_branch = l_accManage.getBranch(l_institution, l_strBranchCodes[i]);
                long l_lngBranchId = l_branch.getBranchId();
                l_lisQueryContainer.add(new Long(l_lngBranchId) + "");
            }
        }
        catch (NotFoundException l_ex)
        {
            
            log.error(" Error in 部店条件追加....... ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //4) 顧客指定の場合（顧客コード != null）、口座ＩＤ文字列をリストに追加する
        if ( l_strAccountCode != null && ! "".equals(l_strAccountCode))
        {
            
            l_lisQueryContainer.add(l_strAccountCode + "%");  
        }
        //5) 開始日，終了日が指定されている場合（開始日 != null && 終了日 != null）、開始日，終了日をリストに追加する
        if (l_datStartDate != null && l_datEndDate != null)
        {
            
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datStartDate, "yyyyMMdd"));
            Date l_datNextDate = WEB3DateUtility.addDay(l_datEndDate,1);
            l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datNextDate, "yyyyMMdd"));
        }
        //6)申込状況区分条件追加
        if (WEB3RegistStateDivDef.WAIT_DECISION.equals(l_strRegistStateDiv)
            || WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING.equals(l_strRegistStateDiv))
        {
            
            l_lisQueryContainer.add(WEB3JudgmentResultDivDef.DEFAULT);
        }
        else if (WEB3RegistStateDivDef.DECISION_COMPLETE.equals(l_strRegistStateDiv))
        {
            
            l_lisQueryContainer.add(WEB3JudgmentResultDivDef.CONSENT);
            l_lisQueryContainer.add(WEB3JudgmentResultDivDef.IMPOSSIBILITY);
        }

        //7)配列を返却
        String[] l_strQueryDataContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryDataContainers);
        
        

        log.exiting(STR_METHOD_NAME);
        return l_strQueryDataContainers;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。 <BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を編集する。<BR> 
     * <BR>
     * １）ソートキーを編集 <BR>
     * 　@１－１）ソートキー = 申込日時の場合 <BR>
     * 　@　@　@携帯番号.勤務先情報変更申込テーブル.申込日時 <BR>
     * <BR>
     * 　@１－２）ソート条件 = 部店コードの場合 <BR>
     * 　@　@　@携帯番号.勤務先情報変更申込テーブル.部店コード <BR>
     * <BR>
     * 　@１－３）ソート条件 = 顧客コードの場合 <BR>
     * 　@　@　@携帯番号.勤務先情報変更申込テーブル.顧客コード <BR>
     * <BR>
     * 　@１－４）ソート条件 = 判定日時の場合 <BR>
     * 　@　@　@携帯番号.勤務先情報変更申込テーブル.判定日時 <BR>
     * <BR>
     * ２）ソート条件に該当するソート条件を編集する。 <BR>
     * 　@　@　@昇順：asc <BR>
     * 　@　@　@降順：desc <BR>
     * @@param String l_sortKeys- お客様情報ソートキー
     */
    protected String createSortCondition(WEB3AccInfoSortKey[] l_sortKeys)
    {
    	String l_strSortConditon = "";
    	//テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を編集する。
    	int l_intSortKeysLen = 0;
    	if (l_sortKeys != null)
    	{
    		l_intSortKeysLen = l_sortKeys.length;
    	}
    	for (int i = 0;i < l_intSortKeysLen;i++)
    	{
    		if (i != 0)
    		{
    			l_strSortConditon += ","; 
    		}
    		String l_sortKey = l_sortKeys[i].keyItem;
    		//１）ソートキーを編集 
    		//　@１－１）ソートキー = 申込日時の場合 
    		//　@　@　@携帯番号.勤務先情報変更申込テーブル.申込日時 
        	if (WEB3AccInfoKeyItemDef.APPLY_DATE.equals(l_sortKey))
        	{
        		l_strSortConditon += "created_timestamp ";
        	}

    		//　@１－２）ソート条件 = 部店コードの場合 
    		//　@　@substr(携帯番号.勤務先情報変更申込テーブル.部店ID, 3, 3)
        	else if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKey))
        	{
        		l_strSortConditon += "substr(branch_id,3,3) ";
        	}
        	
    		//　@１－３）ソート条件 = 顧客コードの場合 
    		//　@　@　@携帯番号.勤務先情報変更申込テーブル.顧客コード 
        	else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKey))
        	{
        		l_strSortConditon += "account_code ";
        	}
        	
    		//　@１－４）ソート条件 = 判定日時の場合 
    		//　@　@　@携帯番号.勤務先情報変更申込テーブル.判定日時 
        	else if (WEB3AccInfoKeyItemDef.JUDGEMENT_DATE.equals(l_sortKey))
        	{
        		l_strSortConditon += "decision_timestamp ";
        	}
    		//２）ソート条件に該当するソート条件を編集する。 
    		//　@　@　@昇順：asc 
    		//　@　@　@降順：desc 
        	if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
        	{
        		l_strSortConditon += "asc";
        	}
        	else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
        	{
        		l_strSortConditon += "desc";
        	}
     
    	}

   	

    	return l_strSortConditon;
    }

    /**
     * (validate一括判定)<BR>
     * 携携帯番号・勤務先情報変更申込一括判定確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（携帯番号・勤務先情報変更申込問合せ）validate一括判定」参照。<BR>
     * ===================================================================== <BR>
     * シーケンス図 「お客様情報（携帯番号・勤務先情報変更申込問合せ）validate一括判定」<BR>
     * (validate一括判定)<BR>
     * 変更前申込顧客Listのサイズが０の場合、例外をスロー<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01169 <BR>
     * ===================================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報携帯番号・勤務先情報変更申込一括判定確認リクエスト<BR>
     * @@throws WEB3BaseException
     * @@return WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse
     * @@roseuid 4149773302F9
     */
    protected WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse validateJudgement(
        WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateJudgement(WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //validate部店権限(部店コード : String[])
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //変更前申込顧客Listオブジェクトの生成
        List l_lisBeforChangeInfo = new ArrayList();

        //変更後申込顧客Listオブジェクトの生成
        List l_lisChangedChangeInfo = new ArrayList();

        int l_intAccountCodeLength = l_request.accountCode.length;
        // リクエストデータ.顧客コードの要素分、Loop処理
        for (int i = 0; i < l_intAccountCodeLength; i++)
        {
            String l_strBranchCode = l_request.branchCode[i];
            String l_strAccountCode = l_request.accountCode[i];
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            try
            {
                l_gentradeMainAccount =
                    l_accountManager.getMainAccount(
                        l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("顧客コードに対応する顧客は登録されていません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // get携帯番号・勤務先情報変更申込(顧客)
            WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_gentradeMainAccount);

            if (l_accInfoMobileOfficeInfoRegist != null)
            {
                //getDataSourceObject( )
                MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow =
                    (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();

                MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams =
                    new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);

                //判定確認中フラグ
                l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.TRUE);
                //管理者.管理者コード
                l_mobileOfficeInfoRegistParams.setLastUpdater(l_strAdministratorCode);
                //処理日時　@※TradingSystem.getSystemTimestamp()
                l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                try
                {
                    //doUpdateQuery(arg0 : Row)
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //get変更前申込情報(顧客)
                WEB3AccInfoMobileOfficeChangeAccount l_beforChangeInfo =
                    l_accInfoMobileOfficeInfoRegist.getBeforeChangeInfo(l_gentradeMainAccount);

                //get変更後申込情報( )
                WEB3AccInfoMobileOfficeChangeAccount l_changedChangeInfo =
                    l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                //get変更前申込情報（）の戻り値をadd()
                l_lisBeforChangeInfo.add(l_beforChangeInfo);

                //get変更後申込情報（）の戻り値をadd()
                l_lisChangedChangeInfo.add(l_changedChangeInfo);
            }
        }

        //変更前申込顧客Listのサイズが０の場合、例外をスロー
        //エラーメッセージ
        //「携帯番号・勤務先情報変更申込がデータ不整合です。」
        if (l_lisBeforChangeInfo.size() == 0)
        {
            log.debug("携帯番号・勤務先情報変更申込がデータ不整合です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01169,
                this.getClass().getName() + STR_METHOD_NAME,
                "携帯番号・勤務先情報変更申込がデータ不整合です。");
        }

        //createResponse( )
        WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_request.createResponse();

        //（*2）プロパティセット
        WEB3AccInfoMobileOfficeChangeAccount[] l_beforeChangeAccountList = null;
        WEB3AccInfoMobileOfficeChangeAccount[] l_changedChangeInfoList = null;
        l_beforeChangeAccountList =
            new WEB3AccInfoMobileOfficeChangeAccount[l_lisBeforChangeInfo.size()];
        l_lisBeforChangeInfo.toArray(l_beforeChangeAccountList);
        l_changedChangeInfoList =
            new WEB3AccInfoMobileOfficeChangeAccount[l_lisChangedChangeInfo.size()];
        l_lisChangedChangeInfo.toArray(l_changedChangeInfoList);

        l_response.beforeChangeAccountList = l_beforeChangeAccountList;
        l_response.afterChangeAccountList = l_changedChangeInfoList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit一括判定)<BR>
     * 携帯番号・勤務先情報変更申込一括判定完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（携帯番号・勤務先情報変更申込問合せ）submit一括判定」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエスト<BR>
     * @@return WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4149773302F9
     */
    protected WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse submitJudgement(
        WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitJudgement(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        //リクエストデータの簡易チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報()
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者権限チェックを行う。
        //[validate権限()に指定する引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.顧客基本情報（基本）
        //is更新：　@true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);

        //validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかのチェックを行う。
        //[validate取引パスワード（）に指定する引数]
        //パスワード ： リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //validate部店権限(部店コード : String[])
        //当該管理者が、指定の部店を取り扱えるかをチェックする。
        //[validate部店権限（）に指定する引数]
        //部店コード[] ： リクエストデータ.部店コード[]
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社コード()
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get管理者コード()
        //管理者コードを取得する。
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //リクエストデータ.顧客コードの要素分、Loop処理
        int l_intCustomerCodeLength = l_request.accountCode.length;
        for (int i = 0; i < l_intCustomerCodeLength; i++)
        {
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            //顧客オブジェクトを取得する。
            //[get顧客()に指定する引数]
            //証券会社コード：　@get証券会社コード()の戻り値
            //部店コード：　@リクエストデータ.部店コード[ i ]
            //口座コード：　@リクエストデータ.顧客コード [ i ]
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            try
            {
                l_gentradeMainAccount =
                    l_gentradeAccountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_request.branchCode[i],
                        l_request.accountCode[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("顧客コードに対応する顧客は登録されていません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //部店コード
            String l_strBranchCode = l_gentradeMainAccount.getBranch().getBranchCode();
            //顧客コード
            String l_accountCode = l_gentradeMainAccount.getAccountCode();
            
        	//lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        	//口座をロックする。
        	//[引数] 
        	//証券会社コード：　@get証券会社コード()の戻り値 
        	//部店コード：　@get顧客()の戻り値.部店コード 
        	//口座コード：　@get顧客()の戻り値.顧客コード [ i ]          
            l_gentradeAccountManager.lockAccount(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_accountCode);

            //get携帯番号・勤務先情報変更申込(顧客)
            //携帯番号・勤務先情報変更申込データを取得する。
            //[get携帯番号・勤務先情報変更申込()に指定する引数]
            //顧客：　@get顧客() の戻り値
            WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_gentradeMainAccount);

            //変更申込情報がある場合（get携帯番号・勤務先情報変更申込() != null）、
            //判定情報，変更申込情報を更新する。
            if (l_accInfoMobileOfficeInfoRegist != null)
            {
                //createForUpdateParams()
                //更新用に行オブジェクトのcloneを生成する。
                l_accInfoMobileOfficeInfoRegist.createForUpdateParams();

                //set判定(String, String)
                //判定情報をセットする。
                //[set判定()に指定する引数]
                //管理者コード：　@get管理者コード()の戻り値
                //判定結果：　@リクエストデータ.判定結果区分
                l_accInfoMobileOfficeInfoRegist.setDecision(l_strAdministratorCode, l_request.judgmentResultDiv);

                //getDataSourceObject()
                //携帯番号・勤務先情報行オブジェクトを取得する。
                MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow =
                    (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();

                MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams =
                    new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);

                MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParamsTemp =
                    new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);

                //判定確認中フラグ:0：FALSE（判定確認中でない）
                l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.FALSE);

                //判定者コード:管理者.管理者コード
                l_mobileOfficeInfoRegistParams.setDecisionUpdater(l_strAdministratorCode);

                //判定日時:処理日時　@※TradingSystem.getSystemTimestamp()
                l_mobileOfficeInfoRegistParams.setDecisionTimestamp(GtlUtils.getSystemTimestamp());

                //削除フラグ:1：TRUE（削除：無効）
                l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.TRUE);

                //更新者コード:管理者.管理者コード
                l_mobileOfficeInfoRegistParams.setLastUpdater(l_strAdministratorCode);

                //更新日時:処理日時　@※TradingSystem.getSystemTimestamp()
                l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //doUpdateQuery(arg0 : Row)
                //携帯番号・勤務先情報変更申込テーブルを更新する。
                //[doUpdateQuery()に指定する引数]
                //arg0（：Row）：　@getDataSourceObject()
                QueryProcessor l_processor = null;
                try
                {
                    l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //リクエストデータ.判定結果区分 = 1（承認）の場合のみ処理実施
                //分岐フロー
                //リクエストデータ.判定結果区分 = 1（承認）の場合のみ、処理を実施する。
                if (WEB3JudgmentResultDivDef.CONSENT.equals(l_request.judgmentResultDiv))
                {
                    //getDataSourceObject()
                    //顧客オブジェクト（get顧客()の戻り値）より、行オブジェクトを取得する。
                    MainAccountRow l_mainAccountRow =
                        (MainAccountRow)l_gentradeMainAccount.getDataSourceObject();
                    MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);

                    //連絡先電話番号（携帯）:携帯番号・勤務先情報変更申込テーブル.携帯番号
                    l_mainAccountParams.setMobile(l_mobileOfficeInfoRegistParams.getMobile());

                    //勤務先名称:携帯番号・勤務先情報変更申込テーブル.勤務先名称
                    l_mainAccountParams.setOffice(l_mobileOfficeInfoRegistParams.getOffice());

                    //勤務先郵便番号:携帯番号・勤務先情報変更申込テーブル.勤務先郵便番号
                    l_mainAccountParams.setOfficeZipCode(l_mobileOfficeInfoRegistParams.getOfficeZipCode());

                    //勤務先住所:携帯番号・勤務先情報変更申込テーブル.勤務先住所
                    l_mainAccountParams.setOfficeAddress(l_mobileOfficeInfoRegistParams.getOfficeAddress());

                    //勤務先電話番号:携帯番号・勤務先情報変更申込テーブル.勤務先電話番号
                    l_mainAccountParams.setOfficeTelephone(l_mobileOfficeInfoRegistParams.getOfficeTelephone());

                    //役職:携帯番号・勤務先情報変更申込テーブル.役職
                    l_mainAccountParams.setPost(l_mobileOfficeInfoRegistParams.getPost());

                    //携帯番号・勤務先情報更新者コード:管理者.管理者コード
                    l_mainAccountParams.setMbOffLastUpdater(l_strAdministratorCode);

                    //携帯番号・勤務先情報更新日時:処理日時　@※TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setMbOffLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    //更新日時:処理日時　@※TradingSystem.getSystemTimestamp()
                    l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                    //doUpdateQuery(arg0 : Row)
                    //顧客マスタテーブルに携帯番号・勤務先情報を更新する。
                    try
                    {
                        l_processor = Processors.getDefaultProcessor();
                        l_processor.doUpdateQuery(l_mainAccountParams);
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error("DBへのアクセスに失敗しました", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    } 
                    catch (DataNetworkException l_ex)
                    {
                        log.error("DBへのアクセスに失敗しました", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    // get補助口座(口座ID : , 補助口座タイプ : )
                    // 補助口座を取得
                    // [get補助口座（）に指定する引数]
                    // 口座ID ： 顧客オブジェクト.getAccountId()
                    // 補助口座タイプ ： SubAccountTypeEnum EQUITY_SUB_ACCOUNT
                    SubAccountTypeEnum l_subAccountTyoeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                    long l_lngAccountId = l_gentradeMainAccount.getAccountId();
                    SubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount =
                            l_gentradeAccountManager.getSubAccount(l_lngAccountId,l_subAccountTyoeEnum);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.error(" DBへのアクセスに失敗しました");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                    // get部店プリファ@レンス(補助口座)
                    // 顧客オブジェクトより、部店用プリファ@レンステーブルから
                    // 顧客正式名称更新、職業コード更新チェックを取得する。
                    // [get部店プリファ@レンス()に指定する引数]
                    // 補助口座 ： get補助口座（）の戻り値
                    String[] l_intBranchPreferences = this.getBranchPreferences(l_subAccount);

                    //get口座情報マスタ(顧客)
                    //口座情報マスタデータを取得する。
                    //[get口座情報マスタ()に指定する引数]
                    //顧客：　@get顧客() の戻り値
                    WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_gentradeMainAccount);
                    WEB3AccInfoMaster l_accInfoMasterTemp = WEB3AccInfoMaster.getAccInfoMaster(l_gentradeMainAccount);

                    //get口座情報マスタ != null の場合、口座情報マスタ情報を更新する。
                    if (l_accInfoMaster != null)
                    {
                        //getDataSourceObject()
                        //口座情報マスタ行オブジェクトを取得する。
                        AccountInfoMstParams l_accountInfoMstParams = 
                            new AccountInfoMstParams((AccountInfoMstParams)l_accInfoMaster.getDataSourceObject());

                        //口座情報マスタParams.更新日時 = 処理日時　@※TradingSystem.getSystemTimestamp()
                        l_accountInfoMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        //口座情報マスタParams.更新者コード = 管理者.携帯番号.勤務先情報変更申込．更新者コード
                        l_accountInfoMstParams.setLastUpdater(l_mobileOfficeInfoRegistParams.getLastUpdater());

                        //this.getプリファ@レンス()の戻り値.index(1)が"1"の場合、
                        //携帯番号・勤務先情報変更申込.正式名称１
                        //それ以外、既存値
                        if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                        {
                            l_accountInfoMstParams.setRealName1(l_mobileOfficeInfoRegistParams.getRealName1());
                        }

                        //this.getプリファ@レンス()の戻り値.index(1)が"1"の場合、
                        //携帯番号・勤務先情報変更申込.正式名称２
                        //それ以外、既存値
                        if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                        {
                            l_accountInfoMstParams.setRealName2(l_mobileOfficeInfoRegistParams.getRealName2());
                        }

                        //this.getプリファ@レンス()の戻り値.index(0)が"1"の場合、
                        //携帯番号・勤務先情報変更申込.職業
                        //それ以外、既存値
                        if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                        {
                            l_accountInfoMstParams.setOccupationDiv(l_mobileOfficeInfoRegistParams.getOccupationDiv());
                        }

                        //口座情報マスタParams.代表者名（漢字）姓 = 携帯番号・勤務先情報変更申込.代表者名（漢字）姓
                        l_accountInfoMstParams.setRepresentFamilyName(
                            l_mobileOfficeInfoRegistParams.getRepresentFamilyName());

                        //口座情報マスタParams.代表者名（漢字）名 = 携帯番号・勤務先情報変更申込.代表者名（漢字）名
                        l_accountInfoMstParams.setRepresentGivenName(
                            l_mobileOfficeInfoRegistParams.getRepresentGivenName());

                        //口座情報マスタParams.代表者名（カナ）姓 = 携帯番号・勤務先情報変更申込.代表者名（カナ）姓
                        l_accountInfoMstParams.setRepresentFamilyNameAlt1(
                            l_mobileOfficeInfoRegistParams.getRepresentFamilyNameAlt1());

                        //口座情報マスタParams.代表者名（カナ）名 =  携帯番号・勤務先情報変更申込.代表者名（カナ）名
                        l_accountInfoMstParams.setRepresentGivenNameAlt1(
                            l_mobileOfficeInfoRegistParams.getRepresentGivenNameAlt1());

                        //口座情報マスタParams.代表者権= 携帯番号・勤務先情報変更申込.代表者権
                        l_accountInfoMstParams.setRepresentPower(l_mobileOfficeInfoRegistParams.getRepresentPower());

                        //口座情報マスタParams.取引責任者名（漢字）姓= 携帯番号・勤務先情報変更申込.取引先責任者名（漢字）姓
                        l_accountInfoMstParams.setDirectorFamilyName(
                            l_mobileOfficeInfoRegistParams.getDirectorFamilyName());

                        //口座情報マスタParams.取引責任者名（漢字）名= 携帯番号・勤務先情報変更申込.取引先責任者名（漢字）名
                        l_accountInfoMstParams.setDirectorGivenName(
                            l_mobileOfficeInfoRegistParams.getDirectorGivenName());

                        //口座情報マスタParams.取引責任者名（カナ）姓= 携帯番号・勤務先情報変更申込.取引先責任者名（カナ）姓
                        l_accountInfoMstParams.setDirectorFamilyNameAlt1(
                            l_mobileOfficeInfoRegistParams.getDirectorFamilyNameAlt1());

                        //口座情報マスタParams.取引責任者名（カナ）名= 携帯番号・勤務先情報変更申込.取引先責任者名（カナ）名
                        l_accountInfoMstParams.setDirectorGivenNameAlt1(
                            l_mobileOfficeInfoRegistParams.getDirectorGivenNameAlt1());

                        //口座情報マスタParams.取引責任者　@所属部署= 携帯番号・勤務先情報変更申込.取引責任者所属部署
                        l_accountInfoMstParams.setDirectorDepartment(
                            l_mobileOfficeInfoRegistParams.getDirectorDepartment());

                        //口座情報マスタParams.取引責任者　@役職= 携帯番号・勤務先情報変更申込.取引責任者役職 
                        l_accountInfoMstParams.setDirectorPost(l_mobileOfficeInfoRegistParams.getDirectorPost());

                        //口座情報マスタParams.取引責任者郵便番号= 携帯番号・勤務先情報変更申込.取引先責任者郵便番号
                        l_accountInfoMstParams.setDirectorZipCode(l_mobileOfficeInfoRegistParams.getDirectorZipCode());

                        //口座情報マスタParams.取引責任者住所１= 携帯番号・勤務先情報変更申込.取引先責任者住所１ 
                        l_accountInfoMstParams.setDirectorAddress1(
                            l_mobileOfficeInfoRegistParams.getDirectorAddress1());

                        //口座情報マスタParams.取引責任者住所２= 携帯番号・勤務先情報変更申込.取引先責任者住所２ 
                        l_accountInfoMstParams.setDirectorAddress2(
                            l_mobileOfficeInfoRegistParams.getDirectorAddress2());

                        //口座情報マスタParams.取引責任者住所３= 携帯番号・勤務先情報変更申込.取引先責任者住所３
                        l_accountInfoMstParams.setDirectorAddress3(
                            l_mobileOfficeInfoRegistParams.getDirectorAddress3());

                        //口座情報マスタParams.取引責任者生年月日　@年号=
                        //携帯番号・勤務先情報変更申込.取引先責任者生年月日年号
                        l_accountInfoMstParams.setDirectorEraBorn(l_mobileOfficeInfoRegistParams.getDirectorEraBorn());

                        //口座情報マスタParams.取引責任者生年月日= 携帯番号・勤務先情報変更申込.取引先責任者生年月日
                        l_accountInfoMstParams.setDirectorBornDate(
                            l_mobileOfficeInfoRegistParams.getDirectorBornDate());

                        //口座情報マスタParams.取引責任者会社直通番号= 携帯番号・勤務先情報変更申込.取引先責任者会社直通番号
                        l_accountInfoMstParams.setDirectorCorpTelephone(
                            l_mobileOfficeInfoRegistParams.getDirectorCorpTelephone());

                        //口座情報マスタParams.その他連絡先（携帯、自宅等）= 携帯番号・勤務先情報変更申込.その他の連絡先
                        l_accountInfoMstParams.setOtherContact(l_mobileOfficeInfoRegistParams.getOtherContact());

                        //口座情報マスタParams.所属 = 携帯番号・勤務先情報変更申込.所属
                        l_accountInfoMstParams.setDepartment(l_mobileOfficeInfoRegistParams.getDepartment());

                        //口座情報マスタParams.連絡先優先順位 １位 = 携帯番号.勤務先情報変更申込．連絡先優先順位 1位
                        l_accountInfoMstParams.setContactPriority1(
                            l_mobileOfficeInfoRegistParams.getContactPriority1());

                        //口座情報マスタParams.連絡先優先順位 2位 = 携帯番号.勤務先情報変更申込．連絡先優先順位 2位
                        l_accountInfoMstParams.setContactPriority2(
                            l_mobileOfficeInfoRegistParams.getContactPriority2());

                        //口座情報マスタParams.連絡先優先順位 3位 = 携帯番号.勤務先情報変更申込．連絡先優先順位 3位
                        l_accountInfoMstParams.setContactPriority3(
                            l_mobileOfficeInfoRegistParams.getContactPriority3());

                        //口座情報マスタParams.国籍 = 携帯番号.勤務先情報変更申込．国籍
                        l_accountInfoMstParams.setNationality(l_mobileOfficeInfoRegistParams.getNationality());

                        //口座情報マスタParams.国籍その他 = 携帯番号.勤務先情報変更申込．国籍その他
                        l_accountInfoMstParams.setNationalityOther(
                            l_mobileOfficeInfoRegistParams.getNationalityOther());

                        //doInsertQuery(arg0 : Row)
                        //口座情報マスタテーブルに新規行を挿入する。
                        try
                        {
                            l_processor = Processors.getDefaultProcessor();
                            l_processor.doUpdateQuery(l_accountInfoMstParams);
                        }
                        catch (DataQueryException l_ex)
                        {
                            log.error("DBへのアクセスに失敗しました", l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        } 
                        catch (DataNetworkException l_ex)
                        {
                            log.error("DBへのアクセスに失敗しました", l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }

                    //get口座情報マスタ == null の場合、口座情報マスタ情報を新規作成する。
                    if (l_accInfoMaster == null)
                    {
                        //get変更後申込情報()
                        WEB3AccInfoMobileOfficeChangeAccount l_accInfoMobileOfficeChangeAccount =
                            l_accInfoMobileOfficeInfoRegist.getChangedChangeInfo();

                        //createNew口座情報マスタ(顧客, 携帯番号・勤務先情報, String)
                        //口座情報マスタオブジェクトを新規作成する。
                        //[createNew口座情報マスタ()に指定する引数]
                        //顧客： get顧客()の戻り値
                        //携帯番号・勤務先情報： get変更後申込情報の戻り値.携帯番号・勤務先情報
                        //更新者コード： get管理者コード()の戻り値
                        l_accInfoMaster = WEB3AccInfoMaster.createNewAccInfoMaster(
                            (MainAccount)l_gentradeMainAccount,
                            l_accInfoMobileOfficeChangeAccount.mobileOfficeInfo,
                            l_strAdministratorCode);

                        //getDataSourceObject()
                        //口座情報マスタ行オブジェクトを取得する。
                        AccountInfoMstRow l_accountInfoMstRow =
                            (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();

                        //doUpdateQuery(arg0 : Row)
                        //口座情報マスタテーブルを更新する。
                        try
                        {
                            l_processor = Processors.getDefaultProcessor();
                            l_processor.doInsertQuery(l_accountInfoMstRow);
                        }
                        catch (DataQueryException l_ex)
                        {
                            log.error("DBへのアクセスに失敗しました", l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        } 
                        catch (DataNetworkException l_ex)
                        {
                            log.error("DBへのアクセスに失敗しました", l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                                this.getClass().getName() + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                    }

                    boolean l_blnVoucherCreated = 
                        WEB3AccInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(l_mobileOfficeInfoRegistParamsTemp, l_accInfoMasterTemp);

                    //is伝票作成（）がtrueの場合
                    if (l_blnVoucherCreated)
                    {
                        //get部店プリファ@レンス（）の戻り値index[0]の値 = "1" の場合、以下の処理を実行
                        if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                        {
                            //職業変更申込伝票作成( )
                            WEB3AccInfoOccupationChangeRegistVoucherCreated l_occupationChangeRegistVoucherCreated =
                                new WEB3AccInfoOccupationChangeRegistVoucherCreated();

                            // create職業変更伝票(long, String)
                            // 職業変更に伴う取残・電子交付・特定口座伝票（GI844）を作成する。
                            // [create職業変更伝票（）に指定する引数]
                            // 変更申込ID ： 携帯番号・勤務先情報変更申込情報.携帯番号・勤務先情報変更申込ID
                            // 職業コード ： 携帯番号・勤務先情報変更申込情報.職業区分
                            // 顧客オブジェクト ： get顧客（）の戻り値
                            long l_lngChangeRegistID = l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId();
                            String l_strOccupationCode = l_mobileOfficeInfoRegistParams.getOccupationDiv();
                            l_occupationChangeRegistVoucherCreated.createOccupationChangeVoucher(
                                l_lngChangeRegistID, l_strOccupationCode ,l_gentradeMainAccount);
                            
                            try
                            {
                                //口座伝票作成の場合、携帯番号.勤務先情報変更申込テーブルの受付結果区分を 
                                //"0：受付未済"に更新する。 
                                Map l_updateMap = new HashMap(); 
                                l_updateMap.put("accept_status", WEB3AccountInfoAcceptStatusDef.NOT_ACCEPT);
                                l_processor.doUpdateQuery(
                                    new MobileOfficeInfoRegistPK(l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId()), 
                                    l_updateMap);

                                //口座情報マスタの職業更新日時を更新する。
                                Map l_updateMap1 = new HashMap();
                                TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                                l_updateMap1.put("occupation_updated_timestamp", l_tradingSys.getSystemTimestamp());
                                l_processor.doUpdateQuery(
                                    new AccountInfoMstPK(l_mobileOfficeInfoRegistParams.getAccountId()),
                                    l_updateMap1);
                            }
                            catch (DataFindException l_ex)
                            {
                            
                                log.error(" DBへのアクセスに失敗しました");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                            }
                            catch (DataNetworkException l_ex)
                            {
                            
                                log.error(" DBへのアクセスに失敗しました");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                            }
                            catch (DataQueryException l_ex)
                            {
                            
                                log.error(" DBへのアクセスに失敗しました");
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                            }
                        }
                    }
                }             
            }
        }

        //createResponse()
        //レスポンスデータを生成する。
        WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse l_response =
            (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get部店プリファ@レンス )<BR>  
     * 顧客オブジェクトより、部店用プリファ@レンステーブルから<BR>
     * 顧客正式名称更新、職業コード更新チェックを取得する。<BR>
     * <BR>
     * １）　@以下の要素の配列を生成し、返却する。<BR>
     * <BR>
     * １－１）　@DB検索 （職業コード更新）<BR>
     * 　@部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID = 補助口座.getBranch().getBranchId() And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.お客様情報職業コード更新 And<BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、""(空文字)をセットする。<BR>
     * <BR>
     * １－２）　@DB検索 （顧客正式名称更新）<BR>
     * 　@部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID = 補助口座.getBranch().getBranchId() And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.お客様情報顧客正式名称更新 And<BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、""(空文字)をセットする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BaseException
     * @@return l_strBranchPerferences
     * 
     */
    private String[] getBranchPreferences(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        String[] l_strBranchPerferences = new String[2];
        try 
        {
            // １－１）DB検索 （職業コード更新）
            // 部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する。
            // 部店ID = 補助口座.getBranch().getBranchId() And
            // プリファ@レンス名 = プリファ@レンス名.お客様情報職業コード更新 And
            // プリファ@レンス名の連番 = 1
            BranchPreferencesRow l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.OCCUPATIONCODE_UPDATE,
                    1);

            //検索結果が取得できなかった場合、""(空文字)をセットする。
            if (l_branchReferencesRow == null)
            {
                l_strBranchPerferences[0] = "";
            }
            else
            {
                l_strBranchPerferences[0] = l_branchReferencesRow.getValue();
            }

            // １－２）　@DB検索 （顧客正式名称更新）
            // 部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する
            // 部店ID = 補助口座.getBranch().getBranchId() And
            // プリファ@レンス名 = プリファ@レンス名.お客様情報顧客正式名称更新 And
            // プリファ@レンス名の連番 = 1
            BranchPreferencesRow l_branchReferenceRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.REALNAME_UPDATE,
                    1);

            //検索結果が取得できなかった場合、""(空文字)をセットする。
            if (l_branchReferenceRow == null)
            {
                l_strBranchPerferences[1] = "";
            }
            else
            {
                l_strBranchPerferences[1] = l_branchReferenceRow.getValue();
            }
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

        log.exiting(STR_METHOD_NAME);
        return l_strBranchPerferences;
    }
}
@
