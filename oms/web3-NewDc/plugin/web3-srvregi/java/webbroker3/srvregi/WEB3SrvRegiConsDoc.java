head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiConsDoc.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 同意書文言(WEB3SrvRegiConsDoc.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 張学剛 (中訊) 新規作成
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiConsDocParams;
import webbroker3.srvregi.data.SrvRegiConsDocRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (同意書文言)<BR>
 * 同意書文言エンティティクラス<BR>                    
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3SrvRegiConsDoc 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiConsDoc.class);
       
    /**
     * (最大文字数)<BR>
     * テーブル上VARCHAR2(4000)の列である為、その半数を指定<BR>
     */
    public static  int MAX_CHARACTER_QUANTITY = 2000;
    
    /**
     * (証券会社コード)
     */
    private String institutionCode;
    
    /**
     * (サービス区分)<BR>
     */
    private String srvDiv;
    
    /**
     * (文言)<BR>
     */
    private String cons;
    
    /**
     * @@roseuid 416F4A9E007D
     */
    public WEB3SrvRegiConsDoc() 
    {
     
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.証券会社コードを返す。<BR>
     * @@return String
     * @@roseuid 41049D5C0010
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (getサービス区分)<BR>
     * サービス区分を返す。<BR>
     * <BR>
     * this.サービス区分を返す。<BR>
     * @@return String
     * @@roseuid 41049D5C002F
     */
    public String getSrvDiv() 
    {
        return this.srvDiv;
    }
    
    /**
     * (get文言)<BR>
     * 文言を返す。<BR>
     * <BR>
     * this.文言を返却する。<BR>
     * @@return String
     * @@roseuid 40F5164301AC
     */
    public String getCons() 
    {
        return this.cons;
    }
    
    /**
     * (set文言)<BR>
     * 同意書の文言を設定する。<BR>
     * <BR>
     * this.文言に引数.文言を設定する。<BR>
     * @@param l_strCons - (文言)<BR>
     * 同意書の文言<BR>
     * @@roseuid 40F51680013F
     */
    public void setCons(String l_strCons) 
    {
        this.cons = l_strCons;
    }
    
    /**
     * (save同意書文言)<BR>
     * 同意書文言をデータベースに保存する。(Update)<BR>
     * <BR>
     * 1) 以下の条件で同意書文言テーブルを検索する。<BR>
     * [検索条件]<BR>
     * 　@証券会社コード=引数.証券会社コード and<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     *  1-1) 検索結果＞0件の場合、配列の先頭の同意書文言<BR>
     *       Paramsオブジェクトを取得する。<BR>
     *  1-2) 取得した同意書文言Params.get作成日付( )をコールし、<BR>
     * 　@　@　@作成日付を取得する。<BR>
     * <BR>
     * 2) 以下の条件に合致するレコードを、同意書文言テーブルから削除する。<BR>
     * [削除条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード and<BR>
     * 　@サービス区分 = 引数.サービス区分<BR>
     * <BR>
     * 2) 引数.同意書文言がnullの場合、処理を終了する。<BR>
     * <BR>
     * 3) 引数.同意書文言.toCharArray()をコールし、文字配列を取得する。<BR>
     * <BR>
     * 4) 文字配列を最大文字数単位で分割し、その分割した文字配列単位で<BR>
     * 　@生成したStringの配列を生成する。<BR>
     * <BR>
     * 　@例）　@文字配列が{'サ','ー','ビ','ス','利','用'}で最大文字数が3の場合、<BR>
     * 　@　@　@{'サ','ー','ビ','ス'}、{'利','用'}の２つの文字配列をもとに２つのStringオブジェクト<BR>
     * 　@　@　@を生成し、要素数２のStringの配列に設定する。<BR>
     * <BR>
     * 5) これ以降の処理はStringの配列の要素数分繰り返す。<BR>
     * <BR>
     * 6) 同意書文言Paramsオブジェクトを生成し、以下の値を設定する。<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * 　@行番号=String配列の要素番号（0～n） + 1<BR>
     * 　@文言=String配列[要素番号]<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=1-2)で取得した作成日付<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 7) 同意書文言Paramsオブジェクトの情報をデータベースにインサートする。<BR>
     * <BR>
     * 8) 作成した同意書文言Paramsオブジェクトの内容で、同意書文言テーブルを<BR>
     * 　@更新（Insert）する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strConsDoc - (同意書文言)
     * @@roseuid 413E635D0262
     */
    public void saveConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc)";
        log.entering(STR_METHOD_NAME);
        
        //1) 以下の条件で同意書文言テーブルを検索する。
        //[検索条件]
        //証券会社コード=引数.証券会社コード and 
        //サービス区分=引数.サービス区分 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strSrvDiv
            };
        List l_lisRecords = null;
        SrvRegiConsDocParams l_srvRegiConsDocParams = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SrvRegiConsDocRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);//DataNetworkException,DataQueryException

        
            //1-1) 検索結果＞0件の場合、配列の先頭の同意書文言Paramsオブジェクトを取得する。
            Timestamp l_tsSystemTime = null;
            if (l_lisRecords.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME);
            }
            else
            {
                l_srvRegiConsDocParams = (SrvRegiConsDocParams)l_lisRecords.get(0);
                //1-2) 取得した同意書文言Params.get作成日付( )をコールし、         
                l_tsSystemTime = l_srvRegiConsDocParams.getCreatedTimestamp();
            }
           
            //2) 以下の条件に合致するレコードを、同意書文言テーブルから削除する。
            //[削除条件]
            //証券会社コード = 引数.証券会社コード and
            //サービス区分 = 引数.サービス区分              
            l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryExceptio
            
            StringBuffer l_sbWhereNew = new StringBuffer();
            l_sbWhereNew.append(" institution_code = ? ");
            l_sbWhereNew.append(" and srv_div = ? ");
    
            Object[] l_objWhereNew =
                {
                    l_strInstitutionCode,
                    l_strSrvDiv
                };
                                          
            QueryProcessor l_queryProcessorNew = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessorNew.doDeleteAllQuery(SrvRegiConsDocRow.TYPE, l_sbWhereNew.toString(), l_objWhereNew);//DataNetworkException,DataQueryException
            
            //2) 引数.同意書文言がnullの場合、処理を終了する。
            if (l_strConsDoc == null || "".equals(l_strConsDoc.trim()))
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
           
            //3) 引数.同意書文言.toCharArray()をコールし、文字配列を取得する。
            char[] l_chConsDoc = l_strConsDoc.toCharArray();
            
            //4) 文字配列を最大文字数単位で分割し、その分割した文字配列単位で生成したStringの配列を生成する。
            int l_intDocLength = 0;
            if (l_chConsDoc.length != 0)
            {
                l_intDocLength = l_chConsDoc.length / MAX_CHARACTER_QUANTITY;
                if (l_chConsDoc.length % MAX_CHARACTER_QUANTITY != 0)
                {
                    l_intDocLength = l_intDocLength + 1;
                }
            }
            
            String[] l_strNewConsDoc = new String[l_intDocLength];
            
            int l_intConsDocLength = l_chConsDoc.length;
            
            for (int i=0; i < l_intDocLength; i++)
            {
                l_strNewConsDoc[i] = ""; 
                for (int j=0; j<MAX_CHARACTER_QUANTITY; j++)
                {
                    int l_intNewConsDocLength = j + i * MAX_CHARACTER_QUANTITY;
                    if (l_intNewConsDocLength < l_intConsDocLength)
                    {
                        l_strNewConsDoc[i] = l_strNewConsDoc[i] + l_chConsDoc[j + i * MAX_CHARACTER_QUANTITY];
                    }
                }
            }
                    
            //5) これ以降の処理はStringの配列の要素数分繰り返す。
            for (int i = 0; i < l_strNewConsDoc.length; i++)
            {
                //6) 同意書文言Paramsオブジェクトを生成し、以下の値を設定する。<BR>
                l_srvRegiConsDocParams = new SrvRegiConsDocParams();
            
                //証券会社コード=引数.証券会社コード
                l_srvRegiConsDocParams.setInstitutionCode(l_strInstitutionCode);
            
                //サービス区分=引数.サービス区分
                l_srvRegiConsDocParams.setSrvDiv(l_strSrvDiv);
            
                //行番号=String配列の要素番号（0～n） + 1
                l_srvRegiConsDocParams.setLineNumber(i + 1);
            
                //文言=String配列[要素番号]
                l_srvRegiConsDocParams.setLineValue(String.valueOf(l_strNewConsDoc[i]));
            
                //更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )
                String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();
                l_srvRegiConsDocParams.setLastUpdater(l_strAdministratorCode);
            
                //作成日付=1-2)で取得した作成日付
                l_srvRegiConsDocParams.setCreatedTimestamp(l_tsSystemTime);
            
                //更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_srvRegiConsDocParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem( ).getSystemTimestamp( ));
            
                //7) 同意書文言Paramsオブジェクトの情報をデータベースにインサートする。                      
                //8) 作成した同意書文言Paramsオブジェクトの内容で、同意書文言テーブルを更新（Insert）する。
          
                l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
                l_queryProcessor.doInsertQuery(l_srvRegiConsDocParams);//DataNetworkException,DataQueryException
            }
        
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiConsDoc.class.getName() + STR_METHOD_NAME);
        }
              
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (saveNew同意書文言)<BR>
     * 同意書文言をデータベースに保存する。(Insert)<BR>
     * <BR>
     * 1) 引数.同意書文言がnullの場合、処理を終了する。<BR>
     * <BR>
     * 2) 引数.同意書文言.toCharArray()をコールし、文字配列を取得する。<BR>
     * <BR>
     * 3) 文字配列を最大文字数単位で分割し、その分割した文字配列単位で<BR>
     * 　@生成したStringの配列を生成する。<BR>
     * <BR>
     * 　@例）　@文字配列が{'サ','ー','ビ','ス','利','用'}で最大文字数が4の場合、<BR>
     * 　@　@　@{'サ','ー','ビ','ス'}、{'利','用'}の2つの文字配列をもとに2つのStringオブジェクト<BR>
     * 　@　@　@を生成し、要素数2のStringの配列に設定する。<BR>
     * <BR>
     * 4) これ以降の処理はStringの配列の要素数分繰り返す。<BR>
     * <BR>
     * 5) 同意書文言Paramsオブジェクトを生成し、以下の値を設定する。<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * 　@行番号=String配列の要素番号（0～n） + 1<BR>
     * 　@文言=String配列[要素番号]<BR>
     * 　@更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )<BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * 6) 同意書文言Paramsオブジェクトの情報をデータベースにインサートする。<BR>
     * <BR>
     * 7) 作成した同意書文言Paramsオブジェクトの内容で、同意書文言テーブルを<BR>
     * 　@更新（Insert）する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_strConsDoc - (同意書文言)<BR>
     * @@roseuid 413E635D0272
     */
    public void saveNewConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewConsDoc(String l_strInstitutionCode, String l_strSrvDiv, String l_strConsDoc)";
        log.entering(STR_METHOD_NAME);
        //1) 引数.同意書文言がnullの場合、処理を終了する。
        char[] l_chConsDoc;
        if (l_strConsDoc == null || l_strConsDoc.trim().equals(""))
        {
            return;
        }
        else
        {
            //2) 引数.同意書文言.toCharArray()をコールし、文字配列を取得する。
            l_chConsDoc = l_strConsDoc.toCharArray();
            
        }
        
        //3) 文字配列を最大文字数単位で分割し、その分割した文字配列単位で生成したStringの配列を生成する。        
        int l_intDocLength = 0;
        if (l_chConsDoc.length != 0)
        {
            l_intDocLength = l_chConsDoc.length / MAX_CHARACTER_QUANTITY;
            if (l_chConsDoc.length % MAX_CHARACTER_QUANTITY != 0)
            {
                l_intDocLength = l_intDocLength + 1;
            }
        }
        
        String[] l_strNewConsDoc = new String[l_intDocLength];
        
        int l_intConsDocLength = l_chConsDoc.length;
        
        for (int i = 0; i < l_intDocLength; i++)
        {
            l_strNewConsDoc[i] = ""; 
            for (int j = 0; j < MAX_CHARACTER_QUANTITY; j++)
            {
                int l_intNewConsDocLength = j + i * MAX_CHARACTER_QUANTITY;
                if (l_intNewConsDocLength < l_intConsDocLength)
                {
                    l_strNewConsDoc[i] = l_strNewConsDoc[i] + l_chConsDoc[j + i * MAX_CHARACTER_QUANTITY];
                }                
            }
        }
        //4) これ以降の処理はStringの配列の要素数分繰り返す。
        for (int i = 0; i < l_strNewConsDoc.length; i++)
        {
            //5) 同意書文言Paramsオブジェクトを生成し、以下の値を設定する。
            SrvRegiConsDocParams l_srvRegiConsDocParams = new SrvRegiConsDocParams();
        
            //証券会社コード=引数.証券会社コード
            l_srvRegiConsDocParams.setInstitutionCode(l_strInstitutionCode);
        
            //サービス区分=引数.サービス区分
            l_srvRegiConsDocParams.setSrvDiv(l_strSrvDiv);
        
            //行番号=String配列の要素番号（0～n） + 1
            l_srvRegiConsDocParams.setLineNumber(i + 1);
        
            //文言=String配列[要素番号]
            l_srvRegiConsDocParams.setLineValue(l_strNewConsDoc[i]);
        
            //更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )
            String l_strAdministratorCode = WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode();       
            l_srvRegiConsDocParams.setLastUpdater(l_strAdministratorCode);
        
            //作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            l_srvRegiConsDocParams.setCreatedTimestamp(l_tsSystemTime);
        
            //更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
            l_srvRegiConsDocParams.setLastUpdatedTimestamp(l_tsSystemTime);
            
            //6) 同意書文言Paramsオブジェクトの情報をデータベースにインサートする。
            //7) 作成した同意書文言Paramsオブジェクトの内容で、同意書文言テーブルを更新（Insert）する。
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
                l_queryProcessor.doInsertQuery(l_srvRegiConsDocParams);//DataNetworkException,DataQueryException
            }
            catch (DataNetworkException l_ex)
            {
                //DBアクセスが失敗の場合
                log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME); 
                //例外をスローする
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     getClass().getName() + STR_METHOD_NAME,
                     l_ex.getMessage(), l_ex);
            }
            catch (DataQueryException l_ex)
            {
                //DBアクセスが失敗の場合
                log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME); 
                //例外をスローする
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
        }
               
        log.exiting(STR_METHOD_NAME);        
    }
}@
