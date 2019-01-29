head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果アップロード１件サービス実装クラス(WEB3AdminIpoLotResultUploadUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 李海波 (中訊) 新規作成
Revesion History : 2004/12/29 坂上(SRA) 残対応>>>044,045
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3SendMailStatusDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者IPO抽選結果アップロード１件サービス実装クラス<BR>
 * <BR>
 * トランザクション属性：　@TX_CREATE_NEW
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadUnitServiceImpl implements WEB3AdminIpoLotResultUploadUnitService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadUnitServiceImpl.class);
    
    /**
     * @@roseuid 4112F1900354
     */
    public WEB3AdminIpoLotResultUploadUnitServiceImpl() 
    {
     
    }
    
    /**
     * 抽選結果を更新する。<BR>
     * <BR>
     * 新規抽選アップロード行について、IPO申告データ更新を行う。<BR>
     * <BR>
     * １）　@配列取得<BR>
     * 　@IPO申告List.toArray()にて、IPO申告の配列を取得する。<BR>
     * <BR>
     * ２）　@顧客に該当するIPO申告を取得する。<BR>
     * 　@１）で取得した配列より、以下の条件に一致する要素を取得する。<BR>
     * 　@取得できなかった場合は、補欠者のデータが存在しないと判定し、例外を<BR>
     * スローする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@顧客コード == IPO申告[index].get顧客コード()<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00510<BR>
     * <BR>
     * ３）　@IPO申告／余力を更新する。<BR>
     * 　@update抽選結果をコールする。<BR>
     * <BR>
     * 　@[update抽選結果()に指定する引数]<BR>
     * 　@IPO申告：　@（２）で取得したIPO申告）<BR>
     * 　@is新規抽選：　@is新規抽選<BR>
     * 　@管理者：　@管理者<BR>
     * 　@抽選結果：　@抽選結果<BR>
     * 　@当選数量：　@当選数量<BR>
     * 　@優先順位：　@優先順位<BR>
     * <BR>
     * ４）　@更新後の要素をListより削除<BR>
     * 　@IPO申告List.indexOf(), remove()にて、該当要素をListより削除する。<BR>
     * <BR>
     * 　@[indexOf()に指定する引数]<BR>
     * 　@arg0：　@IPO申告[index]<BR>
     * <BR>
     * 　@[remove()に指定する引数]<BR>
     * 　@arg0：　@（indexOf()の戻り値）<BR>
     * <BR>
     * @@param l_orderList - IPO申告のArrayList
     * 
     * @@param l_isNewLottery - 新規抽選かどうかの判定<BR>
     * <BR>
     * 　@true：　@新規抽選<BR>
     * 　@false：　@繰上抽選
     * @@param l_administrator - 管理者オブジェクト
     * 
     * @@param l_mainAccount - 顧客オブジェクト
     * @@param l_strLotResult - 抽選結果
     * @@param l_dblElectedQuantity - 当選数量
     * @@param l_lngSubstitutePriority - 優先順位
     * @@roseuid 40F61BB00292
     */
    public void updateLotResult(
        ArrayList l_orderList, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        MainAccount l_mainAccount, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException 
         
    {
        
        final String STR_METHOD_NAME = " updateLotResult(ArrayList, boolean, WEB3Administrator, MainAccount, String, double, long)";
        log.entering(STR_METHOD_NAME);
        
        //１）配列取得
        int l_intSize = l_orderList.size();
        if(l_intSize == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
			//2004/12/8 U00531  例外設定引数が不適切なため修正　@坂上@@SRA  START
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName() + STR_METHOD_NAME);
//			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80020,"CollectionタイプのパラメータSizeは０できない");
			//2004/12/8 U00531  例外設定引数が不適切なため修正　@坂上@@SRA  END
        }
        WEB3IpoOrderImpl[] l_ipoOrder = new WEB3IpoOrderImpl[l_intSize];
        l_orderList.toArray(l_ipoOrder);
        //２）　@顧客に該当するIPO申告を取得する。
        int l_intFlag = 0;
        String l_strAccountCode = l_mainAccount.getAccountCode();
        String l_strBranchCode =  l_mainAccount.getBranch().getBranchCode();
        for(int i = 0; i < l_intSize; i++)
        {
            
            if(l_strAccountCode.equals(l_ipoOrder[i].getAccountCode()) && 
            		l_strBranchCode.equals(l_ipoOrder[i].getMainAccount().getBranch().getBranchCode()))
            {
                
                l_intFlag++;
                int l_intM = l_orderList.indexOf(l_ipoOrder[i]);
                //３）　@IPO申告／余力を更新する
                WEB3IpoOrderImpl l_ipoOrder1 = l_ipoOrder[i];
                this.updateLotResult(
                    l_ipoOrder1,
                    l_isNewLottery,
                    l_administrator,
                    l_strLotResult,
                    l_dblElectedQuantity,
                    l_lngSubstitutePriority);
                    
                l_orderList.remove(l_intM);
                break;
            }
            
        }
        
        if(l_intFlag == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00510,this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * 抽選結果を更新する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「IPO（管理者・抽選結果ＵＬ）update抽選結果」<BR>
     * 参照。
     * @@param l_order - IPO申告オブジェクト
     * 
     * @@param l_isNewLottery - 新規抽選かどうかの判定<BR>
     * <BR>
     * 　@true：　@新規抽選<BR>
     * 　@false：　@繰上抽選
     * @@param l_administrator - 管理者オブジェクト
     * 
     * @@param l_strLotResult - 抽選結果
     * @@param l_dblElectedQuantity - 当選数量
     * @@param l_lngSubstitutePriority - 優先順位
     * @@roseuid 40F64C8F02B1
     */
    public void updateLotResult(
        WEB3IpoOrderImpl l_order, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException
        
    {
        
        final String STR_METHOD_NAME = " updateLotResult(WEB3IPOOrder, boolean, WEB3Administrator, String, double, long)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get顧客()
        MainAccount l_account = l_order.getMainAccount();

		// 拡張アカウントマネージャ取得
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3GentradeAccountManager l_gentradeAccountManaer = 
			(WEB3GentradeAccountManager) l_finApp.getAccountManager();
				
        //1.2.口座をロックする。
		log.debug("口座をロックする。");
		l_gentradeAccountManaer.lockAccount(
			l_account.getInstitution().getInstitutionCode(),
			l_account.getBranch().getBranchCode(),
			l_account.getAccountCode());
        
        //1.3.getDataSourceObject()
        //1.4.getIPO銘柄()
        //1.5.更新用の行オブジェクトを生成する。
        l_order.createForUpdateParams();
        IpoOrderParams l_ipoOrderRow= (IpoOrderParams)l_order.getDataSourceObject();
        Product l_product = l_order.getProduct();
        
        //1.6.is補欠者()
        //1.7.is辞退()
        //1.8.補欠者で辞退済みの場合、例外をスロー
        if(l_order.isWaiting() && l_order.isDecline())
        {
            log.debug("補欠者で既に辞退している");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01735,
                getClass().getName() + STR_METHOD_NAME,
                l_account.getBranch().getBranchCode() + "," + l_account.getAccountCode()); 
        }
                
        //1.9.(*) 行オブジェクト（getDataSourceObject()の戻り値）に更新値をセットする。
        if (WEB3LotResultDef.ELECTION.equals(l_strLotResult) ||
            WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult))
        {
            l_ipoOrderRow.setElectedQuantity((long)l_dblElectedQuantity);   
        }
       
        if (l_isNewLottery == true)
        {
            l_ipoOrderRow.setLotResult(l_strLotResult);
            
        } else
        {
            l_ipoOrderRow.setLotResultRetry(l_strLotResult);
            
        }
        
        if (l_isNewLottery == true && (WEB3LotResultDef.DEFEAT).equals(l_strLotResult))
        {
            l_ipoOrderRow.setSubstitutePriority(null);
        } else if(l_isNewLottery == false)
        {
        //既存値
        } else
        {
            l_ipoOrderRow.setSubstitutePriority(l_lngSubstitutePriority);
        }
        
        if ((WEB3LotResultDef.DEFEAT).equals(l_strLotResult))
        {
            l_ipoOrderRow.setSendMailStatus(WEB3SendMailStatusDef.EXCEPT_OBJECT);
            
        } else
        {
            l_ipoOrderRow.setSendMailStatus(WEB3SendMailStatusDef.SEND_MAIL);
        }
        
        double l_dblPrice;
        
        if ((((IpoProductRow)l_product.getDataSourceObject()).getPublicPriceIsNull()))
        {
            l_dblPrice = 0;
        }
        else
        {
            l_dblPrice = ((IpoProductRow)l_product.getDataSourceObject()).getPublicPrice();    
        }

        double l_dblPayAmount = l_dblPrice*l_dblElectedQuantity;
        l_ipoOrderRow.setPayAmount(l_dblPayAmount);
        l_ipoOrderRow.setLastUpdater(l_administrator.getAdministratorCode());
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        l_ipoOrderRow.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());
        
        //1.10.get補助口座()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)l_order.getSubAccount();
        
        //1.11 余力再計算が必要な場合
        if (!l_isNewLottery || !WEB3LotResultDef.DEFEAT.equals(l_strLotResult))
        {
            //1.11.1 余力再計算(補助口座)
            WEB3TPTradingPowerReCalcService l_tpTPS  
                = (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTPS.reCalcTradingPower(l_subAccount);
        }
        
        //1.12.saveIPO申告(IPO申告)
        //３） DB更新
        try 
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_ipoOrderRow);  
            log.exiting(STR_METHOD_NAME);
        } catch (DataFindException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        } catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        } catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
}
@
