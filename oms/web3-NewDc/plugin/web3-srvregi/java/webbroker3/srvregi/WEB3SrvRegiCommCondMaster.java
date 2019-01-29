head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCommCondMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用手数料条件マスター(WEB3SrvRegiCommCondMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 張学剛 (中訊) 新規作成
*/

package webbroker3.srvregi;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.srvregi.data.SrvRegiCommCondMasterParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用手数料条件マスター)<BR>
 * サービス利用手数料条件マスタークラス<BR>        
 *             
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3SrvRegiCommCondMaster implements BusinessObject
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiCommCondMaster.class);
        
    /**
     * (サービス利用手数料条件マスター行)<BR>
     */
    private SrvRegiCommCondMasterParams srvRegiCommCondMasterParams;

    /**
     * (サービス利用手数料条件マスター)<BR>
     * デフォルトコンストラクタ<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strOrderAccProduct - (注文受付商品)<BR>
     * @@param l_strCommProdTypeName - (商品名)<BR>
     * @@roseuid 4186E4B0025E
     */
    protected WEB3SrvRegiCommCondMaster(String l_strInstitutionCode, String l_strOrderAccProduct, String l_strCommProdTypeName) 
    {
        final String STR_METHOD_NAME = " WEB3SrvRegiCommCondMaster(String, String, String) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvRegiCommCondMasterParams = new SrvRegiCommCondMasterParams();
        this.srvRegiCommCondMasterParams.setInstitutionCode(l_strInstitutionCode);
        this.srvRegiCommCondMasterParams.setOrderAccProduct(l_strOrderAccProduct);
        this.srvRegiCommCondMasterParams.setCommProdTypeName(l_strCommProdTypeName);
        
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     *（getDataSourceObjectの実装）<BR>
     * <BR>
     * this.サービス利用手数料条件マスター行を返却する。<BR>
     * @@return Object
     * @@roseuid 4186E477028D
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);  
        return this.srvRegiCommCondMasterParams;
    }
    
    /**
     * (get注文受付商品)<BR>
     * this.サービス利用手数料条件マスター行.get注文受付商品( )の戻り値を返却する。<BR>
     * @@return String
     * @@roseuid 4186E4DE01C2
     */
    public String getOrderAccProduct() 
    {
        final String STR_METHOD_NAME = " getOrderAccProduct() ";
        log.entering(STR_METHOD_NAME);
        
        String l_strOrderAccProduct = this.srvRegiCommCondMasterParams.getOrderAccProduct();
        
        log.exiting(STR_METHOD_NAME);  
        return l_strOrderAccProduct;
    }
    
    /**
     * (get商品名)<BR>
     * this.サービス利用手数料条件マスター行.get商品名( )の戻り値を返却する。<BR>
     * @@return String
     * @@roseuid 4186E4C7001C
     */
    public String getCommProdTypeName() 
    {
        final String STR_METHOD_NAME = " getCommProdTypeName() ";
        log.entering(STR_METHOD_NAME);
        
        String l_strCommProdTypeName = this.srvRegiCommCondMasterParams.getCommProdTypeName();
        
        log.exiting(STR_METHOD_NAME); 
        return l_strCommProdTypeName;
    }
}
@
