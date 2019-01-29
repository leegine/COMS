head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文クライアントリクエストサービス(WEB3ToSuccClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 孟東(中訊) 新規作成
                 : 2006/10/30 唐性峰(中訊)　@モデルNo.160
*/

package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (連続注文クライアントリクエストサービス)<BR>
 * 連続注文クライアントリクエストサービス
 * <BR>
 * @@author 孟東 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccClientRequestService extends WEB3ClientRequestService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccClientRequestService.class);
    
    /**
     * @@roseuid 4348D89A035B
     */
    public WEB3ToSuccClientRequestService() 
    {

    }
    
    /**
     * (get補助口座)<BR>
     * （getSubAccount()のオーバーロード）<BR>
     * 引数の商品区分に該当する補助口座を取得する。<BR>
     * <BR>
     * １）補助口座タイプを決定する。<BR>
     * <BR>
     * 　@パラメータ.商品区分が、<BR>
     * 　@　@["現物株式"の場合]<BR>
     * 　@　@　@[信用客(*1)の場合]<BR>
     * 　@　@　@　@補助口座タイプ = SubAccountTypeEnum.信用取引口座<BR>
     * 　@　@　@[信用客以外の場合]<BR>
     * 　@　@　@　@補助口座タイプ = SubAccountTypeEnum.株式取引口座<BR>
     * <BR>
     * 　@　@["信用取引"の場合]<BR>
     * 　@　@　@補助口座タイプ = SubAccountTypeEnum.信用取引口座<BR>
     * <BR>
     * 　@　@["先物"の場合]<BR>
     * 　@　@　@補助口座タイプ = SubAccountTypeEnum.証拠金口座<BR>
     * <BR>
     * 　@　@["オプション"の場合]<BR>
     * 　@　@　@補助口座タイプ = 顧客(*2).getOP取引口座タイプ()<BR>
     * <BR>
     * ２）super.get補助口座()にて補助口座を取得し、<BR>
     * 　@返却する。<BR>
     * <BR>
     * 　@[get補助口座()に指定する引数]<BR>
     * 　@　@補助口座タイプ：　@決定した補助口座タイプ<BR>
     * <BR>
     * (*1)信用客<BR>
     * 　@super.get口座().is信用口座開設()の戻り値 == trueの場合<BR>
     * <BR>
     * 　@[is信用口座開設()に指定する引数]<BR>
     * 　@　@弁済区分：　@"指定なし"<BR>
     * <BR>
     * (*2)顧客<BR>
     * 　@super.get口座()にて取得<BR>
     * @@param l_strCommodityDiv 商品区分<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * <BR> 
     * @@return WEB3GentradeSubAccount
     * @@throws WEB3BaseException
     * @@roseuid 431D6AE60124
     */
    public WEB3GentradeSubAccount getSubAccount(String l_strCommodityDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount(String)";
        log.entering(STR_METHOD_NAME);
        
        SubAccountTypeEnum l_subAccountTypeEnum = null;
        WEB3GentradeSubAccount l_subAccount = null;
        
        //１）補助口座タイプを決定する。 
        // パラメータ.商品区分が、 
        //   ["現物株式"の場合] 
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv))
        {
            //[信用客(*1)の場合] 
            if (((WEB3GentradeMainAccount)super.getMainAccount()).isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                //補助口座タイプ = SubAccountTypeEnum.信用取引口座 
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                //補助口座タイプ = SubAccountTypeEnum.株式取引口座
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
        }
        //"信用取引"の場合
        else if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            //補助口座タイプ = SubAccountTypeEnum.信用取引口座 
            l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
        }
        //"先物"の場合
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv))
        {
            //補助口座タイプ = SubAccountTypeEnum.証拠金口座
            l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
        }
        //"オプション"の場合
        else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            //補助口座タイプ = 顧客(*2).getOP取引口座タイプ() 
            l_subAccountTypeEnum = 
                ((WEB3GentradeMainAccount)super.getMainAccount()).getOpSubAccountType();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //２）super.get補助口座()にて補助口座を取得し、返却する。 
        l_subAccount = 
            (WEB3GentradeSubAccount)super.getSubAccount(l_subAccountTypeEnum);
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
