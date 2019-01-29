head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込入力サービスImpl(WEB3AioCashoutInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 黄建 (中訊) 新規作成
Revesion History : 2004/10/25 周勇(中訊) レビュー
Revesion History : 2004/12/09 周勇 (中訊) 残対応
Revesion History : 2007/03/16 何文敏 (中訊) モデルNo.716
Revesion History : 2010/01/28 武波　@ (中訊)モデルNo.1260
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutInputResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BankCodeDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTransferedFinInstitution;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金申込入力サービスImpl)<BR>
 * 出金申込入力サービス実装クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutInputServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashoutInputService 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutInputServiceImpl.class);  
    
    /**
     * 出金申込入力サービス処理を実施する。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込入力）入力画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBDE8100DB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //１.１）get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);          
          
        //１.２）validate注文(SubAccount)
        //注文共通チェックを実施する。 
        //以下のチェックを行う。 
        // －受付時間チェック        
        //－システム停止中チェック  
        //－顧客のチェック（Ｙ客、管理ロック等） 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        
        //========================FinApp============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //チェックを行う
        l_aioOrderManager.validateOrder(l_subAccount);        
        
        //１.３）get発注日( )
        //発注日を取得する。 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("発注日を取得する = " + l_datOrderBizDate);           
        
        //１.４）ArrayList( )
        //ArrayListのインスタンスを生成する。 
        List  l_lisTransScheduledDates = new Vector();
         
        //１.５）get直近振込日(SubAccount, Date)
        //直近の振込日を取得する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        Date l_datClosestTransfer = 
            l_aioOrderManager.getClosestTransferDate(
                l_subAccount, l_datOrderBizDate);
        log.debug("直近の振込日を取得する = " + l_datClosestTransfer);        
         
        //１.６）add(arg0 : Object)
        //ArrayListに要素を追加する。 
        //[引数] 
        //arg0： get直近振込日()の戻り値 
        l_lisTransScheduledDates.add(l_datClosestTransfer);
        
        //１.７）getInstitution( )
        //証券会社オブジェクトを取得する。
        Institution l_institution = l_subAccount.getInstitution(); 
        
        //=========remain zhou-yong NO.4 begin ============
        
        //1.8) calc営業日(Timestamp, int)(営業日計算::calc営業日)
        //アイテムの定義
        //現在日付の5日後を算出する。
        //[引数] 
        //基準日： 現在日付 
        //加算／減算日数： 5
        Timestamp l_currentDateBeforeFive = 
            new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(5);
        log.debug("l_currentDateBeforeFive = " + l_currentDateBeforeFive);
        
        //=========remain zhou-yong NO.4 end ============
        
        //１.9）証券会社.getDateSourceObject().出金予約実施 > 0 の場合
        //その値の回数のLoop処理を
        
        //出金予約実施を取得する。   
        InstitutionRow  l_institutionRow = 
            (InstitutionRow) l_institution.getDataSourceObject();
        int l_intPaymentReserve = Integer.parseInt(l_institutionRow.getPaymentReserve());            

        if (l_intPaymentReserve > 0)
        {
            for (int i = 1; i <= l_intPaymentReserve; i++)
            {
                //１.9.１）calc営業日(Timestamp, int)
                //振込予定日を算出する。 
                //[引数] 
                //基準日： get直近振込日() の戻り値 
                //加算／減算日数：Loop回数
            
                //基準日を取得する
                Timestamp l_dteEstTransferDate = 
                    new Timestamp(l_datClosestTransfer.getTime());
                Timestamp l_tscalcBizDate  =  
                    new WEB3GentradeBizDate(l_dteEstTransferDate).roll(i);
                                
                //===========remain zhou-yong NO.6 begin =========
                
                //算出した振込予定日の日付 > 現在日付の5日後の日付 の場合、
                //リストに追加しない。
                if(WEB3DateUtility.compareToDay(l_tscalcBizDate, l_currentDateBeforeFive) <= 0)
                {
                    //１.9.２） add(arg0 : Object)
                    //ArrayListに要素を追加する。 
                    //[引数] 
                    //arg0： calc営業日()の戻り値 
                    l_lisTransScheduledDates.add(l_tscalcBizDate);
                }
                
                //===========remain zhou-yong NO.6 end =========
            }
        }
        
        //1.10） toArray( )
        //振込予定日の配列を返却する。 
        Date[] l_datTransScheduledDateLists = 
            new Date[l_lisTransScheduledDates.size()];
        l_lisTransScheduledDates.toArray(l_datTransScheduledDateLists);

        //1.11）ArrayList( )
        List  l_lisPaymentPowerLists = new Vector();
        
        //1.12）メッセージ 振込予定日リストの要素毎のLoop処理
        int l_intTransScheduledDateLength = l_datTransScheduledDateLists.length;
        
        for (int i = 0; i < l_intTransScheduledDateLength; i++)
        {
            //===========remain zhou-yong NO.1 begin=========
            
            //1.12.1）get出金可能額～出金入力画面表示用～(補助口座 : 補助口座, 受渡日 : Date)
            //振込予定日の出金可能額を取得する。 
            //[引数] 
            //補助口座： get補助口座()の戻り値 
            //受渡日： 振込予定日リストの要素 
            WEB3TPTradingPowerService l_tPTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            double l_dblCashoutPossiblePrice =  
                l_tPTradingPowerService.getPaymentTradingPowerAioCashoutInput(
                    l_gentradeSubAccount, l_datTransScheduledDateLists[i]);           
            
            //===========remain zhou-yong NO.1 end=========
            
            //1.12.2）add(arg0 : Object)
            //ArrayListに要素を追加する。 
            //[引数] 
            //arg0： get出金可能額()の戻り値 
            l_lisPaymentPowerLists.add(
                WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice));
        }
                
        //１.１３）toArray( )
        //出金余力の配列を返却する。 
        String[] l_strPaymentPowerLists = 
            new String[l_lisPaymentPowerLists.size()];
        l_lisPaymentPowerLists.toArray(l_strPaymentPowerLists);
        
        //１.１４）getMainAccount( )
        //顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount(); 
        
        //is円貨振込先（銀行口座）登録
        //当該顧客が、振込先（銀行口座）に円貨登録しているかどうかを判定する。
        boolean l_blnIsJapaneseCurrencyBankAccountRegi =
            l_gentradeMainAccount.isJapaneseCurrencyBankAccountRegi();
         
        //１.１６）get振込先金融機@関( )
        //顧客の振込先金融機@関オブジェクトを取得する。 
        WEB3GentradeTransferedFinInstitution 
            l_gentradeTransferedFinInstitution =
                l_gentradeMainAccount.getTransferedFinInstitution();
     
        //１.１７）get銀行コード( )
        //銀行コードを取得する。
        String l_strFinInstitutionCode = 
            l_gentradeTransferedFinInstitution.getFinInstitutionCode();
        
        //１.１８）get銀行名( )
        //銀行名を取得する。
        String l_strFinInstitutionName =
            l_gentradeTransferedFinInstitution.getFinInstitutionName();
                
        //１.１９) get銀行名() == nullの場合
        if ( l_strFinInstitutionName == null)
        {
			TransferedFinInstitutionRow l_transferedFinInstitutionRow = null;
			try
			{
				//振込先金融機@関テーブルより以下の条件で検索する
				//[取得条件] 
				// 証券会社コード
				// 部店コード
				// 顧客コード
				// 指定区分="A"
				l_transferedFinInstitutionRow = TransferedFinInstitutionDao.findRowByPk(
				    l_subAccount.getInstitution().getInstitutionCode(),
				    l_gentradeMainAccount.getBranch().getBranchCode(),
				    l_gentradeMainAccount.getAccountCode(),
                    "A" );
			}
			catch (DataFindException l_ex)
			{
				//例外をスロー
				log.debug("該当する振込先金融機@関がありません。");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
				    WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			catch (DataException l_ex)
			{
				//例外をスロー
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			if (WEB3FinTransferDivDef.BANK_TRANSFER.equals(l_transferedFinInstitutionRow.getTransferDiv()))
			{
				//振込先金融機@関.振替区分="1(銀行）"の場合、例外をスローする。
				log.debug("振替区分=1(銀行)の場合、銀行名に値がありません。");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);   
			}
        }   

        //===============remain zhou-yong NO.2 begin =============
        
        //get支店コード( )(振込先金融機@関::get支店コード)
        //アイテムの定義
        //支店コードを取得する。 
        String l_strFinBranchCode =
            l_gentradeTransferedFinInstitution.getFinBranchCode();

        //===============remain zhou-yong NO.2 end =============        
        
        //１.２０）get支店名( )
        //支店名を取得する。 
        String l_strFinBranchName =
            l_gentradeTransferedFinInstitution.getFinBranchName();
        
        //１.２１）get預金区分( )
        //預金区分を取得する。
        String l_strFinSaveDiv =
            l_gentradeTransferedFinInstitution.getFinSaveDiv();
         
        //１.２２）get口座番号( )
        //口座番号を取得する。
        String l_strFinAccountNo =
            l_gentradeTransferedFinInstitution.getFinAccountNo();
        
        //１.２３）createResponse( ) レスポンスデータを生成する。 
        WEB3AioCashoutInputResponse l_aioCashoutInputResponse =
            (WEB3AioCashoutInputResponse) l_request.createResponse();    
        
        //１.２４）(*) 以下のとおりに、プロパティをセットする。

        //顧客.is円貨振込先（銀行口座）登録()の戻り値 = falseの場合、"0:未登録"をセット
        if (!l_blnIsJapaneseCurrencyBankAccountRegi)
        {
            l_aioCashoutInputResponse.transRegistDiv = WEB3BankAccountRegiDef.NOT_REGISTER;
        }
        //顧客.is円貨振込先（銀行口座）登録()の戻り値 = trueの場合、"1:登録済"をセット
        else
        {
            l_aioCashoutInputResponse.transRegistDiv = WEB3BankAccountRegiDef.ALREADY_REGISTER;
        }

        //レスポンス.銀行コード = 振込先金融機@関.get銀行コード()の戻り値
        l_aioCashoutInputResponse.bankCode = l_strFinInstitutionCode;
        
        //レスポンス.銀行名 = 振込先金融機@関.get銀行名()の戻り値
        l_aioCashoutInputResponse.bankName = l_strFinInstitutionName;
        
        //レスポンス.支店名 = 振込先金融機@関.get支店名()の戻り値
        l_aioCashoutInputResponse.branchBankName = l_strFinBranchName;
        
        //レスポンス.預金区分 = 振込先金融機@関.get預金区分()の戻り値
        l_aioCashoutInputResponse.depositDiv = l_strFinSaveDiv;
        
        //==========remain zhou-yong NO.3 begin ===========
        
        //レスポンス.口座番号 = （以下のとおり）
        //１）・銀行コード="9900"（郵貯）の場合、振込先金融機@関.get支店コード()の戻り値 + "-" + get口座番号( )の戻り値  をセット
        //    ・銀行コード!="9900"（郵貯）の場合、get口座番号( )の戻り値の結果  をセット
		if(WEB3BankCodeDef.POSTAL_SAVINGS.equals(l_strFinInstitutionCode))
		{
			l_aioCashoutInputResponse.accountID = l_strFinBranchCode + "-" + l_strFinAccountNo;
		}
		else 
		{
			l_aioCashoutInputResponse.accountID = l_strFinAccountNo;
		}
        
        //==========remain zhou-yong NO.3 end ===========
        
        //レスポンス.出金余力 = 出金余力リスト
        l_aioCashoutInputResponse.paymentPowerList = l_strPaymentPowerLists;
        
        //レスポンス.振込予定日 = 振込予定
        l_aioCashoutInputResponse.transScheduledDateList = l_datTransScheduledDateLists;
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutInputResponse;
    }
}
@
