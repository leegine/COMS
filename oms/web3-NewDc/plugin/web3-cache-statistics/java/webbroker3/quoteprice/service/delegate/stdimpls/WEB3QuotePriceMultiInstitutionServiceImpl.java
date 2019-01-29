head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceMultiInstitutionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 時価終値保存サービス実装(共同系対応)(WEB3QuotePriceMultiInstitutionServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/10 劉(FLJ) 新規作成
 */

package webbroker3.quoteprice.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.quoteprice.message.*;
import webbroker3.quoteprice.service.delegate.*;
import webbroker3.util.*;

/**
 * （時価終値保存サービスインターフェース(共同系対応)。
 * @@version 1.0
 */
public class WEB3QuotePriceMultiInstitutionServiceImpl
    implements WEB3QuotePriceMultiInstitutionService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePriceMultiInstitutionServiceImpl.class);

    /**
     * デフォルト銘柄コード開始レンジ
     */
    private final static Long DEFAULT_FROM_PRODUCT_CODE = new Long(0);

    /**
     * デフォルト銘柄コード終了レンジ
     */
    private final static Long DEFAULT_TO_PRODUCT_CODE = new Long(99999);
    
    /**
     * デフォルト銘柄コード終了レンジ
     */
    private final static Long DEFAULT_TO_PRODUCT_CODE_IFO = new Long(999999999);

    /**
     * 時価終値保存サ処理(共同系対応）
     *
     * @@param l_request WEB3GenRequest
     * @@throws WEB3BaseException
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        //1.1共同系会社一覧を取得する
        List l_list = new ArrayList();
        WEB3QuotePriceResponse l_response = new WEB3QuotePriceResponse();
        try
        {
            l_list = getInstitutions();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        //1.2会社ごと時価終値を保存する
        if (l_list != null)
        {
            l_response.result = new WEB3QuotePriceSaveResult[l_list.size()];
            
            if("IFO".equals(((WEB3QuotePriceRequest)l_request).quote_table))
            {
            	// 先物時価を取得
            	l_response.quote_table = "IFO"; 
                for (int i = 0; i < l_list.size(); i++)
                {
                    InstitutionRow l_row = (InstitutionRow) l_list.get(i);
                    WEB3QuotePriceSaveRequest l_saveRequest = new WEB3QuotePriceSaveRequest();
                    l_saveRequest.institutionCd = l_row.getInstitutionCode();
                    l_saveRequest.fromProductCd = DEFAULT_FROM_PRODUCT_CODE;
                    l_saveRequest.toProductCd = DEFAULT_TO_PRODUCT_CODE_IFO;
                    log.info("start Institution:" + l_saveRequest.institutionCd + "...");
                    WEB3QuotePriceIfoService l_service = (WEB3QuotePriceIfoService)
                        Services
                        .getService(WEB3QuotePriceIfoService.class);

                    WEB3QuotePriceSaveResult l_result = l_service.execute(l_saveRequest);
                    l_response.result[i] = l_result;
                    log.info("save result >>> " + ObjectPrettyPrinter.toString(l_result));
                    log.info("end Institution:" + l_saveRequest.institutionCd + ".");

                }
            }else{
            	// 株式銘柄時価を取得
            	l_response.quote_table = "EQTYPE";  
                for (int i = 0; i < l_list.size(); i++)
                {
                    InstitutionRow l_row = (InstitutionRow) l_list.get(i);
                    WEB3QuotePriceSaveRequest l_saveRequest = new WEB3QuotePriceSaveRequest();
                    l_saveRequest.institutionCd = l_row.getInstitutionCode();
                    l_saveRequest.fromProductCd = DEFAULT_FROM_PRODUCT_CODE;
                    l_saveRequest.toProductCd = DEFAULT_TO_PRODUCT_CODE;
                    log.info("start Institution:" + l_saveRequest.institutionCd + "...");
                    WEB3QuotePriceEquityService l_service = (WEB3QuotePriceEquityService)
                        Services
                        .getService(WEB3QuotePriceEquityService.class);

                    WEB3QuotePriceSaveResult l_result = l_service.execute(l_saveRequest);
                    l_response.result[i] = l_result;
                    log.info("save result >>> " + ObjectPrettyPrinter.toString(l_result));
                    log.info("end Institution:" + l_saveRequest.institutionCd + ".");

                }
            }
        }
        return l_response;
    }

    /**
     * 共同系会社一覧を取得する
     *
     * @@throws Exception
     * @@return List
     */
    private List getInstitutions() throws Exception
    {
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            InstitutionParams.TYPE);
        return l_lisSearchResult;
    }

}
@
