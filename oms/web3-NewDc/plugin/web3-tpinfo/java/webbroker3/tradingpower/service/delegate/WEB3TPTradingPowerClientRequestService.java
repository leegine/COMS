head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力クライアントリクエストサービスクラス(WEB3TPTradingPowerClientRequestService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.service.delegate;

import java.text.NumberFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (余力クライアントリクエストサービス)<BR>
 * 余力クライアントリクエストサービス。<BR>
 * 
 * @@author asano(SCS)
 */
public abstract class WEB3TPTradingPowerClientRequestService 
{
   /**
    * ログユーティリティ
    */
   private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerClientRequestService.class);
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B667C2005D
    */
   public WEB3TPTradingPowerClientRequestService() 
   {
    
   }
   
   /**
    * (get補助口座)<BR>
    * （getSubAccountのオーバーロード）<BR>
    * 
    * １）ログインセキュリティサービスより補助口座を取得する。<BR>
    * <BR>
    * ２）顧客オブジェクトを取得する。<BR>
    * <BR>
    * ３）is信用口座開設の判定<BR>
    * <BR>
    * 　@○開設の場合<BR>
    * 　@　@補助口座<株式取引口座(預り金)>オブジェクトを取得し、リターンする。<BR>
    * 　@○未開設の場合<BR>
    * 　@　@補助口座<株式信用取引口座(保証金)>オブジェクトを取得し、リターンする。<BR>
     * <BR>
    * @@return webbroker3.gentrade.WEB3GentradeSubAccount
    * @@roseuid 41B6603102DD
    */
   public WEB3GentradeSubAccount getSubAccount()
   {
       // OpLoginSecurityServiceからAccountIdを取得
       OpLoginSecurityService l_loginService =
           (OpLoginSecurityService) Services.getService(
               OpLoginSecurityService.class);
        long l_lngAccountId = l_loginService.getAccountId();
        
        try
        {
            //顧客
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount( l_lngAccountId );
            //補助口座
            SubAccount l_subAccount = null;
              
           //信用口座開設の判定
           if( !l_mainAccount.isMarginAccountEstablished( WEB3GentradeRepaymentDivDef.DEFAULT ) )
           {
                //現物顧客　@銘柄タイプ：株式取引口座(預り金)
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
           }
           else
           {
                //信用顧客　@銘柄タイプ：株式信用取引口座(保証金)>
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
          }
          
          //補助口座を返却
          return new WEB3GentradeSubAccount( (SubAccountRow)l_subAccount.getDataSourceObject() );
          
       } 
       catch (NotFoundException nfe)
       {
           log.error(nfe.getMessage(), nfe);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               getClass().getName() + "getSubAccount()",
               nfe);
       } 
       catch (DataQueryException dqe) {
           log.error(dqe.getMessage(), dqe);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               getClass().getName() + "getSubAccount()",
               dqe);
       } 
       catch (DataNetworkException dne) {
           log.error(dne.getMessage(), dne);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               getClass().getName() + "getSubAccount()",
               dne);
       }

    }
        
    /**
     * double型をString形式にフォーマットする。<BR>
     * <BR>
     * @@param param
     * @@return String
     */
    protected String format(double l_param)
    {
        NumberFormat l_nf = NumberFormat.getInstance();
        l_nf.setGroupingUsed( false );
        return l_nf.format( l_param ); 
    }
    
}
@
