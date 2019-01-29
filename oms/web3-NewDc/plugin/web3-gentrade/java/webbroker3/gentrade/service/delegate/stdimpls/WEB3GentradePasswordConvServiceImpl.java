head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 暗証番号変換サービス実装クラス(WEB3GentradePasswordConvServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
Revision History : 2007/08/22 沢田(ＳＲＡ) オペレータログイン改善
Revision History : 2007/10/04 大辻(ＳＲＡ) 契約FA様向けパスワードリセット対応
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fitechlabs.xtrade.kernel.data.DataSources;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordTools;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.PasswordUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferResponse;
import webbroker3.gentrade.service.delegate.WEB3GentradePasswordConvService;
import webbroker3.util.WEB3LogUtility;

/**
 * 暗証番号変換サービス実装クラス<br /> 
 * <br />
 * 
 * @@author ＳＲＡ仲川
 */
public class WEB3GentradePasswordConvServiceImpl
	implements WEB3GentradePasswordConvService
{
	/**
	 * ログ出力ユーティリティ
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3GentradePasswordConvServiceImpl.class);

	/**
	 * SP名　@顧客マスタ中間テーブル更新
	 */
	private static final String SP_SET_MAIN_ACCOUNT = "w3jPassword_pk.updCustPWInfo_sp"; 

	/**
	 * SP名　@口座開設 暗証番号伝票（G5511）中間テーブル更新
	 */
	private static final String SP_SET_ACC_OPEN = "w3jPassword_pk.updAccOpenPWInfo_sp"; 

	/**
	 * SP名　@顧客マスタ移管用テーブル更新
	 */
	private static final String SP_SET_TRANSFER = "w3jTrnsferPassword_pk.updTrnsCustPWInfo_sp"; 

    /**
     * デフォルトコンストラクタ
     */
	public WEB3GentradePasswordConvServiceImpl() {}
 
	/**
	 * 暗証番号変換処理を行う。<br />
	 * 
	 * @@param l_request - リクエストデータ
	 * @@exception  SYSTEM_ERROR_80003 DBエラー
	 * @@return WEB3BackResponse<br />
	 * @@roseuid 421036A8039E
	 */
	public WEB3BackResponse execute(WEB3BackRequest l_request) 
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " execute(WEB3BackRequest)";
		log.entering(STR_METHOD_NAME);
        
		WEB3BackResponse l_response = null;
        
		if (l_request instanceof 
			WEB3GentradePasswordConvAccOpenRequest)
		{
			l_response =  this.convAccOpenBuffer(
				(WEB3GentradePasswordConvAccOpenRequest)l_request
				);
        } else if (l_request instanceof 
            WEB3GentradePasswordConvExpAccOpenRequest)
        {
            l_response =  this.convExpAccOpenBuffer(
                (WEB3GentradePasswordConvExpAccOpenRequest)l_request
                );
		} else if (l_request instanceof 
			WEB3GentradePasswordConvMainAccountRequest)
		{
			l_response =  this.convMainAccountBuffer(
				(WEB3GentradePasswordConvMainAccountRequest)l_request
				);
		} else if (l_request instanceof 
			WEB3GentradePasswordConvWeb2TransferRequest)
		{
			l_response =  this.transfer(
				(WEB3GentradePasswordConvWeb2TransferRequest)l_request
				);
		} else if (l_request instanceof 
			WEB3GentradePasswordConvSonarTraderRequest)
		{
			l_response =  this.convSonarTrader(
				(WEB3GentradePasswordConvSonarTraderRequest)l_request
				);
		} else
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
				this.getClass().getName() + STR_METHOD_NAME,
				"不正なリクエスト型 [" + l_request.getClass().getName() + "]"
				);
		}
        
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
   
	/**
	 * 【顧客マスタ中間テーブル暗証番号更新】<br />
	 * 中間テーブル内の暗証番号（SONAR暗号化形式）を復号化、
	 * xTrade-Hash，WEB3暗号化形式に変換し、対応項目に更新（DB-update）する。<br />
	 * <br />
	 * 1) 中間テーブル読込<br />
	 * 　@中間テーブル(顧客(新規・変更分))[buf_mst_cust_newchg]を読み、
	 * 暗証番号[d_card_password]項目値を取得する。（※全行対象）<br />
	 * ※全行対象
	 * <br />
	 * 2) SONAR形式で暗号化された暗証番号（4桁）を復号化する<br />
	 * 　@1)で読込んだ各暗証番号について、4桁の復号化を行う。<br />
	 * <br />
	 * 3) xTrade-Hash形式に暗号化<br />
	 * 　@2)で復号化した各文字列について、以下の通り2種類の暗号化を行う。<br />
	 * <br />
	 * 　@3-1) PasswordTool.hashPassword()にて暗号化する。<br />
	 * 　@3-2) WEB3Encrypt.encrypt()にて暗号化する。<br />
	 * <br />
	 * 4) 中間テーブル更新(SP)<br />
	 * 　@3)で暗号化した各暗証番号を、中間テーブル(顧客(新規・変更分))
	 * [buf_mst_cust_newchg]の対応する項目に更新（Update）する。<br />
	 * <br />
	 * 　@xTrade暗号化パスワード[xtrade_encrypted_password] = 3-1)の処理結果<br />
	 * 　@WEB3暗号化パスワード[web3_encrypted_password] = 3-2)の処理結果<br />
	 * <br />
	 * @@see PasswordTools#hashPassword
	 * @@see WEB3Crypt#encrypt
	 * @@param l_request リクエストデータ
	 * @@exception  SYSTEM_ERROR_80003 DBエラー
	 * @@return WEB3GentradePasswordConvMainAccountResponse
	 */
	protected WEB3GentradePasswordConvMainAccountResponse convMainAccountBuffer(
		WEB3GentradePasswordConvMainAccountRequest l_request
		) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
		" convMainAccountBuffer(WEB3GentradePasswordConvMainAccountRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvMainAccountResponse l_response = 
			  new WEB3GentradePasswordConvMainAccountResponse(l_request);

		try {
			Connection l_con = 
				DataSources.getDefaultDataSource().getConnection();
			PreparedStatement l_statement = l_con.prepareStatement(
					"SELECT " +
					"COMP_CODE, " +
					"BR_CODE, " +
					"CUST_CODE, " +
					"CUST_CODE_CD, " +
					"D_CARD_PASSWORD " +
					"FROM BUF_MST_CUST_NEWCHG"
					);
			ResultSet l_rs = l_statement.executeQuery();
			CallableStatement l_cstatement =
				l_con.prepareCall(
				"{call " + SP_SET_MAIN_ACCOUNT + "(?,?,?,?,?,?,?)}"
				);
			
			WEB3Crypt crypt = new WEB3Crypt(); 
			while (l_rs.next()) 
			{
				String institutionCode = l_rs.getString("COMP_CODE");
				String branchCode = l_rs.getString("BR_CODE");
				String accountCode = l_rs.getString("CUST_CODE");
				String checkDigit = l_rs.getString("CUST_CODE_CD");
				String password = l_rs.getString("D_CARD_PASSWORD");
				
				String l_decryptPassword = 
					this.decryptSONAREncodingPassword(password);
				if (l_decryptPassword == null)
				{
					log.info("[BUF_MST_CUST_NEWCHG] 暗証番号の長さが不正であるか、暗証番号がセットされておりません。" +
						"(COMP_CODE: " + institutionCode + ", " +  
						"BR_CODE: " + branchCode + ", " +  
						"CUST_CODE: " + accountCode + ", " +
						"CUST_CODE_CD: " + checkDigit + ")"
						);
                    continue;
				}

				String l_web3EncryptPassword = 
					crypt.encrypt(l_decryptPassword);
				String l_xTradeEncryptPassword = 
                    PasswordUtils.hashPassword(l_decryptPassword);
				
				l_cstatement.setString(1, institutionCode);
				l_cstatement.setString(2, branchCode);
				l_cstatement.setString(3, accountCode);
				l_cstatement.setString(4, checkDigit);
				l_cstatement.setString(5, l_web3EncryptPassword);
				l_cstatement.setString(6, l_xTradeEncryptPassword);
				l_cstatement.registerOutParameter(7, java.sql.Types.INTEGER, 0);

				l_cstatement.execute();
				int l_res = l_cstatement.getInt(7);
				if (l_res != 0)
				{
					log.error("[BUF_MST_CUST_NEWCHG Update-SP-Error] " +
						"COMP_CODE: " + institutionCode + ", " +  
						"BR_CODE: " + branchCode + ", " +  
						"CUST_CODE: " + accountCode + ", " +  
						"CUST_CODE_CD: " + checkDigit  
						);
				}
			}

			l_cstatement.close();
			l_rs.close();
			l_statement.close();
		} catch (SQLException e) 
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"SQLエラー",
				e
				);
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
     * 【口座開設見込客テーブル暗証番号更新】<br />
     * 口座開設見込客内の初期パスワード（SONAR暗号化形式）を復号化、
     * WEB3暗号化形式に変換し、対応項目に更新（DB-update）する。<br />
     * <br />
     * 1) 口座開設見込客テーブル読込<br />
     * 　@口座開設見込客[exp_account_open]を読み、
     * 初期パスワード[initial_password]項目値を取得する。（※全行対象）<br />
     * <br />
     * 2) SONAR形式で暗号化された暗証番号（4桁）を復号化する<br />
     * 　@1)で読込んだ各初期パスワードについて、4桁の復号化を行う。<br />
     * <br />
     * 3) WEB3形式に暗号化<br />
     * 　@2)で復号化した各文字列について、WEB3Encrypt.encrypt()にて暗号化を行う。<br />
     * <br />
     * 4) 中間テーブル更新(SP)<br />
     * 　@3)で暗号化した各初期パスワードを、口座開設見込客テーブル
     * [exp_account_open]を以下の通り更新（Update）する。<br />
     * 　@初期パスワード[initial_password]=
     * 　@更新者コード[last_updater]="ikan_067"
     * 　@更新日時[last_updated_timestamp]=現在日時
     * <br />
     * @@see WEB3Crypt#encrypt
     * @@param l_request リクエストデータ
     * @@exception  SYSTEM_ERROR_80003 DBエラー
     * @@return WEB3GentradePasswordConvExpAccOpenResponse
     */
    protected WEB3GentradePasswordConvExpAccOpenResponse convExpAccOpenBuffer(
        WEB3GentradePasswordConvExpAccOpenRequest l_request
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
        " convExpAccOpenBuffer(WEB3GentradePasswordConvExpAccOpenRequest)";
        log.entering(STR_METHOD_NAME);
        
        java.sql.Timestamp l_timestamp = new java.sql.Timestamp(
            GtlUtils.getSystemTimestamp().getTime());

        WEB3GentradePasswordConvExpAccOpenResponse l_response = 
              new WEB3GentradePasswordConvExpAccOpenResponse(l_request);

        try {
            Connection l_con = 
                DataSources.getDefaultDataSource().getConnection();
            PreparedStatement l_statement = l_con.prepareStatement(
                    "SELECT " +
                    "INSTITUTION_CODE, " +
                    "ACC_OPEN_REQUEST_NUMBER, " +
                    "INITIAL_PASSWORD " +
                    "FROM EXP_ACCOUNT_OPEN"
                    );
            ResultSet l_rs = l_statement.executeQuery();
            
            PreparedStatement l_updateStatement =
                l_con.prepareStatement(
                "UPDATE EXP_ACCOUNT_OPEN SET " + 
                    "INITIAL_PASSWORD = ? ," + 
                    "LAST_UPDATER = ? ," + 
                    "LAST_UPDATED_TIMESTAMP = ? " + 
                "WHERE (INSTITUTION_CODE = ? AND ACC_OPEN_REQUEST_NUMBER = ?)"
                );
            
            WEB3Crypt crypt = new WEB3Crypt(); 
            while (l_rs.next()) 
            {
                String institutionCode = l_rs.getString("INSTITUTION_CODE");
                String requestNumber = l_rs.getString("ACC_OPEN_REQUEST_NUMBER");
                String password = l_rs.getString("INITIAL_PASSWORD");
                
                String l_decryptPassword = 
                    this.decryptSONAREncodingPassword(password);
                if (l_decryptPassword == null)
                {
                    log.info(
                        "[EXP_ACCOUNT_OPEN] 暗証番号の長さが不正であるか、暗証番号がセットされておりません。" +
                        "(INSTITUTION_CODE: " + institutionCode + ", " +  
                        "ACC_OPEN_REQUEST_NUMBER: " + requestNumber + ")"  
                        );
                    continue;
                }

                String l_web3EncryptPassword = 
                    crypt.encrypt(l_decryptPassword);
                
                l_updateStatement.setString(1, l_web3EncryptPassword);
                l_updateStatement.setString(2, "ikan_067");
                l_updateStatement.setTimestamp(3, l_timestamp);
                l_updateStatement.setString(4, institutionCode);
                l_updateStatement.setString(5, requestNumber);

                int res = l_updateStatement.executeUpdate();
            }

            l_updateStatement.close();
            l_rs.close();
            l_statement.close();
        } catch (SQLException e) 
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "SQLエラー",
                e
                );
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

	/**
	 * 【口座開設 暗証番号伝票（G5511）中間テーブル更新】<br />
	 * 中間テーブル内のWEB3暗号化形式の暗証番号を復号化し、
	 * 更新（DB-update）する。<br />
	 * <br />
	 * 1) テーブル読込(SP)<br />
	 * 　@中間テーブル（暗証番号伝票(G5511)テーブル）[buf_password_voucher]を読み、
	 * 暗証番号[password]項目値を取得する。（※全行対象）<br />
	 * <br />
	 * 2) WEB3形式で暗号化された暗証番号を復号化する<br />
	 * 　@1)で読込んだ各暗証番号について、復号化を行う。<br />
	 * <br />
	 * 3) テーブル更新(SP)<br />
	 * 　@2)で復号化した各暗証番号にて、中間テーブル(暗証番号伝票(G5511)テーブル)
	 * [buf_password_voucher].暗証番号[password]項目を更新する。（Update）<br />
	 * <br />
	 * @@see WEB3Crypt#decrypt
	 * @@param l_request リクエストデータ
	 * @@exception  SYSTEM_ERROR_80003 DBエラー
	 * @@return WEB3GentradePasswordConvAccOpenResponse
	 */
	protected WEB3GentradePasswordConvAccOpenResponse convAccOpenBuffer(
		WEB3GentradePasswordConvAccOpenRequest l_request
		) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
		" convAccOpenBuffer(WEB3GentradePasswordConvAccOpenRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvAccOpenResponse l_response = 
			  new WEB3GentradePasswordConvAccOpenResponse(l_request);

		try {
			Connection l_con =	DataSources.getDefaultDataSource().getConnection();
			PreparedStatement l_statement = l_con.prepareStatement(
					"SELECT " +
					"ORDER_REQUEST_NUMBER, " +
					"REQUEST_CODE, " +
					"INSTITUTION_CODE, " +
					"BRANCH_CODE, " +
					"ACCOUNT_CODE, " +
					"PASSWORD " +
					"FROM BUF_PASSWORD_VOUCHER"
					);
			ResultSet l_rs = l_statement.executeQuery();
			
			CallableStatement l_cstatement = l_con.prepareCall(
				"{call " + SP_SET_ACC_OPEN + "(?,?,?,?,?,?,?)}"
				);
			
			WEB3Crypt crypt = new WEB3Crypt(); 
			while (l_rs.next()) 
			{
				String orderReqNumber = l_rs.getString("ORDER_REQUEST_NUMBER");
				String requestCode = l_rs.getString("REQUEST_CODE");
				String institutionCode = l_rs.getString("INSTITUTION_CODE");
				String branchCode = l_rs.getString("BRANCH_CODE");
				String accountCode = l_rs.getString("ACCOUNT_CODE");
				String password = l_rs.getString("PASSWORD");
				
				String l_decryptPassword = crypt.decrypt(password);
				
				l_cstatement.setString(1, orderReqNumber);
				l_cstatement.setString(2, requestCode);
				l_cstatement.setString(3, institutionCode);
				l_cstatement.setString(4, branchCode);
				l_cstatement.setString(5, accountCode);
				l_cstatement.setString(6, l_decryptPassword);
				l_cstatement.registerOutParameter(7, java.sql.Types.INTEGER, 0);

				l_cstatement.execute();
				int l_res = l_cstatement.getInt(7);
				if (l_res != 0)
				{
					log.error("[BUF_PASSWORD_VOUCHER Update-SP-Error] " +
						"ORDER_REQUEST_NUMBER: " + orderReqNumber + ", " +  
						"REQUEST_CODE: " + requestCode + ", " +  
						"INSTITUTION_CODE: " + institutionCode + ", " +  
						"BRANCH_CODE: " + branchCode + ", " +  
						"ACCOUNT_CODE: " + accountCode  
						);
				}
			}

			l_cstatement.close();
			l_rs.close();
			l_statement.close();
		} catch (SQLException e) 
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"SQLエラー",
				e
				);
		}
		
		
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * 【WEB2暗証番号データ移管】<br />
	 * 移管用テーブル内の暗証番号（WEB2暗号化形式）を復号化し、
	 * xTrade-Hash，WEB3暗号化形式に変換、対応項目を更新（DB-update）する。<br />
	 * <br />
	 * 1) テーブル読込(SP)<br />
	 * 　@顧客マスター移管用テーブル[transfer_main_account]を読み、
	 * 暗証番号[trading_password]項目値を取得する。（※全行対象）<br />
	 * <br />
	 * 2) WEB3形式で暗号化された暗証番号（8桁）を復号化する<br />
	 * 　@1)で読込んだ各暗証番号について、8桁の復号化を行う。<br />
	 * 　@復号化対象の暗証番号が8桁でない場合はエラーとする。<br />
	 * <br />
	 * 3) xTrade-Hash形式に暗号化<br />
	 * 　@2)で復号化した各文字列について、以下の通り2種類の暗号化を行う。<br />
	 * <br />
	 * 　@3-1) PasswordTool.hashPassword()にて暗号化する。<br />
	 * 　@3-2) WEB3Encrypt.encrypt()にて暗号化する。<br />
	 * <br />
	 * 4) テーブル更新(SP)<br />
	 * 　@3)で暗号化した各暗証番号を、顧客マスター移管用テーブル
	 * [transfer_main_account]の対応する項目に更新する。<br />
	 * <br />
	 * 　@xTrade暗号化パスワード[xtrade_encrypted_password] = 3-1)の処理結果<br />
	 * 　@WEB3暗号化パスワード[web3_encrypted_password] = 3-2)の処理結果<br />
	 * <br />
	 * @@see WEB3Crypt#decrypt
	 * @@param l_request リクエストデータ
	 * @@exception  SYSTEM_ERROR_80003 DBエラー
	 * @@return WEB3GentradePasswordConvAccOpenResponse
	 */
	protected WEB3GentradePasswordConvWeb2TransferResponse transfer(
		WEB3GentradePasswordConvWeb2TransferRequest l_request)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
		" transfer(WEB3GentradePasswordConvWeb2TransferRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvWeb2TransferResponse l_response = 
			  new WEB3GentradePasswordConvWeb2TransferResponse(l_request);

		try {
			Connection l_con =	DataSources.getDefaultDataSource().getConnection();
			PreparedStatement l_statement = l_con.prepareStatement(
					"SELECT " +
					"INSTITUTION_CODE, " +
					"BRANCH_CODE, " +
					"ACCOUNT_CODE, " +
					"TRADING_PASSWORD " +
					"FROM TRANSFER_MAIN_ACCOUNT"
					);
			ResultSet l_rs = l_statement.executeQuery();
			
			CallableStatement l_cstatement = l_con.prepareCall(
				"{call " + SP_SET_TRANSFER + "(?,?,?,?,?,?)}"
				);
			
			WEB3Crypt crypt = new WEB3Crypt(); 
			while (l_rs.next()) 
			{
				String institutionCode = l_rs.getString("INSTITUTION_CODE");
				String branchCode = l_rs.getString("BRANCH_CODE");
				String accountCode = l_rs.getString("ACCOUNT_CODE");
				String password = l_rs.getString("TRADING_PASSWORD");
				
				String l_decryptPassword = 
					this.decryptWEB2EncodingPassword(password);
				if (l_decryptPassword == null)
				{
					log.info("[TRANSFER_MAIN_ACCOUNT] 暗証番号の長さが不正であるか、暗証番号がnullです。" +
						"(INSTITUTION_CODE: " + institutionCode + ", " +  
						"BRANCH_CODE: " + branchCode + ", " +  
						"ACCOUNT_CODE: " + accountCode + ")"  
						);
                    continue;
				}
					
				log.debug("---------------------------------------");
				log.debug("TRANSFER_MAIN_ACCOUNT.TRADING_PASSWORD [" + password + "]");
				log.debug("DECODING PASSWORD(CLEAR-TEXT) [" + l_decryptPassword + "]");
				log.debug("\n\n");

				String l_web3EncryptPassword = 
					crypt.encrypt(l_decryptPassword);
				String l_xTradeEncryptPassword = 
                    PasswordUtils.hashPassword(l_decryptPassword);
				
				l_cstatement.setString(1, institutionCode);
				l_cstatement.setString(2, branchCode);
				l_cstatement.setString(3, accountCode);
				l_cstatement.setString(4, l_web3EncryptPassword);
				l_cstatement.setString(5, l_xTradeEncryptPassword);
				l_cstatement.registerOutParameter(6, java.sql.Types.INTEGER, 0);

				l_cstatement.execute();
				int l_res = l_cstatement.getInt(6);
				if (l_res != 0)
				{
					log.error("[TRANSFER_MAIN_ACCOUNT Update-SP-Error] " +
						"INSTITUTION_CODE: " + institutionCode + ", " +  
						"BRANCH_CODE: " + branchCode + ", " +  
						"ACCOUNT_CODE: " + accountCode  
						);
				}
			}

			l_cstatement.close();
			l_rs.close();
			l_statement.close();
		} catch (SQLException e) 
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"SQLエラー",
				e
				);
		}
		
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
	/**
	 * SONAR暗号化形式の暗証番号(4桁)を復号化する。
	 * 
	 * @@param l_sonarEncodingPassword 暗証番号（SONAR暗号化形式）
	 * @@return String 復号化した暗証番号
	 */
	private String decryptSONAREncodingPassword(
		String l_sonarEncodingPassword
		) 
	{
		if (l_sonarEncodingPassword == null ||
			l_sonarEncodingPassword.length() != 4 ||
            l_sonarEncodingPassword.equals("    ")) 
		{
			return null;
		}

		String l_decryptPassword = null;
		int l_addValue = 2947;
		int l_subValue = 10000;
		int l_intValue = 0;
		
		l_decryptPassword = l_sonarEncodingPassword.substring(2, 3) + 
			l_sonarEncodingPassword.substring(0, 1) +
			l_sonarEncodingPassword.substring(3, 4) +
			l_sonarEncodingPassword.substring(1, 2);
			
		l_intValue = Integer.parseInt(l_decryptPassword);
		l_intValue = l_intValue - l_addValue;

		if (l_intValue < 0)
		{
			l_intValue = l_intValue + l_subValue;
		}
		l_decryptPassword = String.valueOf(l_intValue);

		for ( ; l_decryptPassword.length() < 4; )
		{
			l_decryptPassword = "0"+ l_decryptPassword;
		}
		return l_decryptPassword;
	}
	
	/**
	 * WEB2暗号化形式の暗証番号(8桁)を復号化する。
	 * 
	 * @@param l_web2EncodingPassword 暗証番号（WEB2暗号化形式）
	 * @@return String 復号化した暗証番号
	 */
	private String decryptWEB2EncodingPassword(
		String l_web2EncodingPassword
		) 
	{
		if (l_web2EncodingPassword == null ||
			l_web2EncodingPassword.length() != 8) 
		{
			return null;
		}
		
		char[] l_passwordChars = l_web2EncodingPassword.toCharArray();
		
		StringBuffer l_buf = new StringBuffer();
		l_buf.append(l_passwordChars[5]);
		l_buf.append(l_passwordChars[0]);
		l_buf.append(l_passwordChars[6]);
		l_buf.append(l_passwordChars[4]);
		l_buf.append(l_passwordChars[1]);
		l_buf.append(l_passwordChars[7]);
		l_buf.append(l_passwordChars[3]);
		l_buf.append(l_passwordChars[2]);
		
		String l_strbuf = l_buf.toString(); 
		return l_strbuf.trim();
	}

    /**
     * 【中間テーブル（扱者情報）暗証番号追加】
     * 中間テーブル（扱者情報）内のSONAR扱者コードをxTrade、WEB3暗号化形式に変換
     * し、対応項目を更新（DB-update）する。
     *
     * １）中間テーブル（扱者情報）読込
     * 　@　@中間テーブル（扱者情報）[buf_sonar_trader]を読み、
     * 　@　@証券会社設定初期パスワード[comp_trader_password]項目値を取得する。（※全行対象）
     *
     * ２）xTrade、WEB3形式に暗号化
     * 　@　@上記１）で取得した各文字列について、以下の通り2種類の暗号化を行う。
     *
     * 　@２－１）PasswordTool.hashPassword()にて暗号化する。
     * 　@２－２）WEB3Encrypt.encrypt()にて暗号化する。
     *
     * ３）中間テーブル更新
     * 　@　@上記２）で暗号化した各初期パスワードを、中間テーブル（扱者情報）
     * 　@　@の各項目に更新（Update）する。
     * 　@　@xTrade形式＝SONAR扱者xtrade初期パスワード[sonar_trader_xtrade_password]
     * 　@　@WEB3形式＝SONAR扱者web3初期パスワード[sonar_trader_web3_password]
     *
     * @@see WEB3Crypt#encrypt
     * @@param l_request リクエストデータ
     * @@exception  SYSTEM_ERROR_80003 DBエラー
     * @@return WEB3GentradePasswordConvSonarTraderResponse
     */
	protected WEB3GentradePasswordConvSonarTraderResponse convSonarTrader(
		WEB3GentradePasswordConvSonarTraderRequest l_request
		) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
		" convSonarTrader(WEB3GentradePasswordConvSonarTraderRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvSonarTraderResponse l_response =
			new WEB3GentradePasswordConvSonarTraderResponse(l_request);

		try {
			Connection l_con =
				DataSources.getDefaultDataSource().getConnection();
			PreparedStatement l_statement = l_con.prepareStatement(
					"SELECT " +
					"COMP_CODE, " +
					"BR_CODE, " +
					"SONAR_TRADER_CODE, " +
					"COMP_TRADER_PASSWORD " +
					"FROM BUF_SONAR_TRADER"
					);
			ResultSet l_rs = l_statement.executeQuery();
			
			PreparedStatement l_updateStatement =
				l_con.prepareStatement(
				"UPDATE BUF_SONAR_TRADER " +
				"SET SONAR_TRADER_XTRADE_PASSWORD = ? ," +
				"    SONAR_TRADER_WEB3_PASSWORD = ? " +
				"WHERE (COMP_CODE = ? " +
				"  AND  BR_CODE = ? " +
				"  AND  SONAR_TRADER_CODE = ?)"
				);
			
			WEB3Crypt crypt = new WEB3Crypt();
			while (l_rs.next())
			{
				String compCode = l_rs.getString("COMP_CODE");
				String brCode = l_rs.getString("BR_CODE");
				String sonarTraderCode = l_rs.getString("SONAR_TRADER_CODE");
				String compTraderPassword = l_rs.getString("COMP_TRADER_PASSWORD");

				String l_web3EncryptPassword =
					crypt.encrypt(compTraderPassword);
				String l_xTradeEncryptPassword = 
                    PasswordUtils.hashPassword(compTraderPassword);

				l_updateStatement.setString(1, l_xTradeEncryptPassword);
				l_updateStatement.setString(2, l_web3EncryptPassword);
				l_updateStatement.setString(3, compCode);
				l_updateStatement.setString(4, brCode);
				l_updateStatement.setString(5, sonarTraderCode);

				int res = l_updateStatement.executeUpdate();
			}

			l_updateStatement.close();
			l_rs.close();
			l_statement.close();
		} catch (SQLException e)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"SQLエラー",
				e
				);
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
