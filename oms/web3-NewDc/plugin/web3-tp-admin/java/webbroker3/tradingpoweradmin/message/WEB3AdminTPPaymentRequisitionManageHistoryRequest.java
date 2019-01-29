head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理履歴リクエストクラス(WEB3AdminTPPaymentRequisitionManageHistoryRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 入金請求管理履歴リクエスト
 */
public class WEB3AdminTPPaymentRequisitionManageHistoryRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_payment_requisition_manage_history";

    /**
     * 会社コード
     */
    public String institutionCode;
    
    /**
     * 部店コード
     */
    public String branchCode;
    
    /**
     * 顧客コード
     */
    public String accountCode;

    /**
     * 顧客属性
     */
    public String customerAttribute;

    /**
     * @@roseuid 4412A9C90390
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。
 　@　@* （ただし、当クラス内で完結する簡易チェックのみとする。）
     *１）部店コードチェック
     * this.部店コードが以下の条件に該当する場合、
     * 「部店コードエラー」の例外をスローする。
     * ・this.部店コード == null
     * ・this.部店コード.length != 3
     * ・this.部店コード != 数値
     *
     * ２）顧客指定の場合(顧客コード  != nullのとき)
     * this.顧客コードが以下の条件に該当する場合、
     * 「顧客コードエラー」の例外をスローする。
     * ・this.顧客コード.length != 6
     * ・this.顧客コード != 数値
     *
     * @@roseuid 440535530083
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String METHOD_NAME = "validate()";

 		//１）部店コードチェック
 		if(branchCode != null)
 		{
 		    if(branchCode.length() != 3)
 		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);
	  		try
			{
	  			Integer.parseInt(branchCode);
			}
	  		catch(NumberFormatException ne)
			{
	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME, ne.getMessage());
			}
 		}

        //２）顧客指定の場合
 		//顧客コードチェック
 		if(accountCode != null)
 		{
 		    if(accountCode.length() != 6)
 		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME);
	  		try
			{
	  			Integer.parseInt(accountCode);
			}
	  		catch(NumberFormatException ne)
			{
	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME, ne.getMessage());
			}
 		}
    }

	public WEB3GenResponse createResponse() {
		return new WEB3AdminTPPaymentRequisitionManageHistoryResponse();
	}
}
@
