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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ïؔԍ��ϊ��T�[�r�X�����N���X(WEB3GentradePasswordConvServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
Revision History : 2007/08/22 ��c(�r�q�`) �I�y���[�^���O�C�����P
Revision History : 2007/10/04 ���(�r�q�`) �_��FA�l�����p�X���[�h���Z�b�g�Ή�
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
 * �Ïؔԍ��ϊ��T�[�r�X�����N���X<br /> 
 * <br />
 * 
 * @@author �r�q�`����
 */
public class WEB3GentradePasswordConvServiceImpl
	implements WEB3GentradePasswordConvService
{
	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3GentradePasswordConvServiceImpl.class);

	/**
	 * SP���@@�ڋq�}�X�^���ԃe�[�u���X�V
	 */
	private static final String SP_SET_MAIN_ACCOUNT = "w3jPassword_pk.updCustPWInfo_sp"; 

	/**
	 * SP���@@�����J�� �Ïؔԍ��`�[�iG5511�j���ԃe�[�u���X�V
	 */
	private static final String SP_SET_ACC_OPEN = "w3jPassword_pk.updAccOpenPWInfo_sp"; 

	/**
	 * SP���@@�ڋq�}�X�^�ڊǗp�e�[�u���X�V
	 */
	private static final String SP_SET_TRANSFER = "w3jTrnsferPassword_pk.updTrnsCustPWInfo_sp"; 

    /**
     * �f�t�H���g�R���X�g���N�^
     */
	public WEB3GentradePasswordConvServiceImpl() {}
 
	/**
	 * �Ïؔԍ��ϊ��������s���B<br />
	 * 
	 * @@param l_request - ���N�G�X�g�f�[�^
	 * @@exception  SYSTEM_ERROR_80003 DB�G���[
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
				"�s���ȃ��N�G�X�g�^ [" + l_request.getClass().getName() + "]"
				);
		}
        
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
   
	/**
	 * �y�ڋq�}�X�^���ԃe�[�u���Ïؔԍ��X�V�z<br />
	 * ���ԃe�[�u�����̈Ïؔԍ��iSONAR�Í����`���j�𕜍����A
	 * xTrade-Hash�CWEB3�Í����`���ɕϊ����A�Ή����ڂɍX�V�iDB-update�j����B<br />
	 * <br />
	 * 1) ���ԃe�[�u���Ǎ�<br />
	 * �@@���ԃe�[�u��(�ڋq(�V�K�E�ύX��))[buf_mst_cust_newchg]��ǂ݁A
	 * �Ïؔԍ�[d_card_password]���ڒl���擾����B�i���S�s�Ώہj<br />
	 * ���S�s�Ώ�
	 * <br />
	 * 2) SONAR�`���ňÍ������ꂽ�Ïؔԍ��i4���j�𕜍�������<br />
	 * �@@1)�œǍ��񂾊e�Ïؔԍ��ɂ��āA4���̕��������s���B<br />
	 * <br />
	 * 3) xTrade-Hash�`���ɈÍ���<br />
	 * �@@2)�ŕ����������e������ɂ��āA�ȉ��̒ʂ�2��ނ̈Í������s���B<br />
	 * <br />
	 * �@@3-1) PasswordTool.hashPassword()�ɂĈÍ�������B<br />
	 * �@@3-2) WEB3Encrypt.encrypt()�ɂĈÍ�������B<br />
	 * <br />
	 * 4) ���ԃe�[�u���X�V(SP)<br />
	 * �@@3)�ňÍ��������e�Ïؔԍ����A���ԃe�[�u��(�ڋq(�V�K�E�ύX��))
	 * [buf_mst_cust_newchg]�̑Ή����鍀�ڂɍX�V�iUpdate�j����B<br />
	 * <br />
	 * �@@xTrade�Í����p�X���[�h[xtrade_encrypted_password] = 3-1)�̏�������<br />
	 * �@@WEB3�Í����p�X���[�h[web3_encrypted_password] = 3-2)�̏�������<br />
	 * <br />
	 * @@see PasswordTools#hashPassword
	 * @@see WEB3Crypt#encrypt
	 * @@param l_request ���N�G�X�g�f�[�^
	 * @@exception  SYSTEM_ERROR_80003 DB�G���[
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
					log.info("[BUF_MST_CUST_NEWCHG] �Ïؔԍ��̒������s���ł��邩�A�Ïؔԍ����Z�b�g����Ă���܂���B" +
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
				"SQL�G���[",
				e
				);
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
     * �y�����J�݌����q�e�[�u���Ïؔԍ��X�V�z<br />
     * �����J�݌����q���̏����p�X���[�h�iSONAR�Í����`���j�𕜍����A
     * WEB3�Í����`���ɕϊ����A�Ή����ڂɍX�V�iDB-update�j����B<br />
     * <br />
     * 1) �����J�݌����q�e�[�u���Ǎ�<br />
     * �@@�����J�݌����q[exp_account_open]��ǂ݁A
     * �����p�X���[�h[initial_password]���ڒl���擾����B�i���S�s�Ώہj<br />
     * <br />
     * 2) SONAR�`���ňÍ������ꂽ�Ïؔԍ��i4���j�𕜍�������<br />
     * �@@1)�œǍ��񂾊e�����p�X���[�h�ɂ��āA4���̕��������s���B<br />
     * <br />
     * 3) WEB3�`���ɈÍ���<br />
     * �@@2)�ŕ����������e������ɂ��āAWEB3Encrypt.encrypt()�ɂĈÍ������s���B<br />
     * <br />
     * 4) ���ԃe�[�u���X�V(SP)<br />
     * �@@3)�ňÍ��������e�����p�X���[�h���A�����J�݌����q�e�[�u��
     * [exp_account_open]���ȉ��̒ʂ�X�V�iUpdate�j����B<br />
     * �@@�����p�X���[�h[initial_password]=
     * �@@�X�V�҃R�[�h[last_updater]="ikan_067"
     * �@@�X�V����[last_updated_timestamp]=���ݓ���
     * <br />
     * @@see WEB3Crypt#encrypt
     * @@param l_request ���N�G�X�g�f�[�^
     * @@exception  SYSTEM_ERROR_80003 DB�G���[
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
                        "[EXP_ACCOUNT_OPEN] �Ïؔԍ��̒������s���ł��邩�A�Ïؔԍ����Z�b�g����Ă���܂���B" +
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
                "SQL�G���[",
                e
                );
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

	/**
	 * �y�����J�� �Ïؔԍ��`�[�iG5511�j���ԃe�[�u���X�V�z<br />
	 * ���ԃe�[�u������WEB3�Í����`���̈Ïؔԍ��𕜍������A
	 * �X�V�iDB-update�j����B<br />
	 * <br />
	 * 1) �e�[�u���Ǎ�(SP)<br />
	 * �@@���ԃe�[�u���i�Ïؔԍ��`�[(G5511)�e�[�u���j[buf_password_voucher]��ǂ݁A
	 * �Ïؔԍ�[password]���ڒl���擾����B�i���S�s�Ώہj<br />
	 * <br />
	 * 2) WEB3�`���ňÍ������ꂽ�Ïؔԍ��𕜍�������<br />
	 * �@@1)�œǍ��񂾊e�Ïؔԍ��ɂ��āA���������s���B<br />
	 * <br />
	 * 3) �e�[�u���X�V(SP)<br />
	 * �@@2)�ŕ����������e�Ïؔԍ��ɂāA���ԃe�[�u��(�Ïؔԍ��`�[(G5511)�e�[�u��)
	 * [buf_password_voucher].�Ïؔԍ�[password]���ڂ��X�V����B�iUpdate�j<br />
	 * <br />
	 * @@see WEB3Crypt#decrypt
	 * @@param l_request ���N�G�X�g�f�[�^
	 * @@exception  SYSTEM_ERROR_80003 DB�G���[
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
				"SQL�G���[",
				e
				);
		}
		
		
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * �yWEB2�Ïؔԍ��f�[�^�ڊǁz<br />
	 * �ڊǗp�e�[�u�����̈Ïؔԍ��iWEB2�Í����`���j�𕜍������A
	 * xTrade-Hash�CWEB3�Í����`���ɕϊ��A�Ή����ڂ��X�V�iDB-update�j����B<br />
	 * <br />
	 * 1) �e�[�u���Ǎ�(SP)<br />
	 * �@@�ڋq�}�X�^�[�ڊǗp�e�[�u��[transfer_main_account]��ǂ݁A
	 * �Ïؔԍ�[trading_password]���ڒl���擾����B�i���S�s�Ώہj<br />
	 * <br />
	 * 2) WEB3�`���ňÍ������ꂽ�Ïؔԍ��i8���j�𕜍�������<br />
	 * �@@1)�œǍ��񂾊e�Ïؔԍ��ɂ��āA8���̕��������s���B<br />
	 * �@@�������Ώۂ̈Ïؔԍ���8���łȂ��ꍇ�̓G���[�Ƃ���B<br />
	 * <br />
	 * 3) xTrade-Hash�`���ɈÍ���<br />
	 * �@@2)�ŕ����������e������ɂ��āA�ȉ��̒ʂ�2��ނ̈Í������s���B<br />
	 * <br />
	 * �@@3-1) PasswordTool.hashPassword()�ɂĈÍ�������B<br />
	 * �@@3-2) WEB3Encrypt.encrypt()�ɂĈÍ�������B<br />
	 * <br />
	 * 4) �e�[�u���X�V(SP)<br />
	 * �@@3)�ňÍ��������e�Ïؔԍ����A�ڋq�}�X�^�[�ڊǗp�e�[�u��
	 * [transfer_main_account]�̑Ή����鍀�ڂɍX�V����B<br />
	 * <br />
	 * �@@xTrade�Í����p�X���[�h[xtrade_encrypted_password] = 3-1)�̏�������<br />
	 * �@@WEB3�Í����p�X���[�h[web3_encrypted_password] = 3-2)�̏�������<br />
	 * <br />
	 * @@see WEB3Crypt#decrypt
	 * @@param l_request ���N�G�X�g�f�[�^
	 * @@exception  SYSTEM_ERROR_80003 DB�G���[
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
					log.info("[TRANSFER_MAIN_ACCOUNT] �Ïؔԍ��̒������s���ł��邩�A�Ïؔԍ���null�ł��B" +
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
				"SQL�G���[",
				e
				);
		}
		
		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
	/**
	 * SONAR�Í����`���̈Ïؔԍ�(4��)�𕜍�������B
	 * 
	 * @@param l_sonarEncodingPassword �Ïؔԍ��iSONAR�Í����`���j
	 * @@return String �����������Ïؔԍ�
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
	 * WEB2�Í����`���̈Ïؔԍ�(8��)�𕜍�������B
	 * 
	 * @@param l_web2EncodingPassword �Ïؔԍ��iWEB2�Í����`���j
	 * @@return String �����������Ïؔԍ�
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
     * �y���ԃe�[�u���i���ҏ��j�Ïؔԍ��ǉ��z
     * ���ԃe�[�u���i���ҏ��j����SONAR���҃R�[�h��xTrade�AWEB3�Í����`���ɕϊ�
     * ���A�Ή����ڂ��X�V�iDB-update�j����B
     *
     * �P�j���ԃe�[�u���i���ҏ��j�Ǎ�
     * �@@�@@���ԃe�[�u���i���ҏ��j[buf_sonar_trader]��ǂ݁A
     * �@@�@@�،���Аݒ菉���p�X���[�h[comp_trader_password]���ڒl���擾����B�i���S�s�Ώہj
     *
     * �Q�jxTrade�AWEB3�`���ɈÍ���
     * �@@�@@��L�P�j�Ŏ擾�����e������ɂ��āA�ȉ��̒ʂ�2��ނ̈Í������s���B
     *
     * �@@�Q�|�P�jPasswordTool.hashPassword()�ɂĈÍ�������B
     * �@@�Q�|�Q�jWEB3Encrypt.encrypt()�ɂĈÍ�������B
     *
     * �R�j���ԃe�[�u���X�V
     * �@@�@@��L�Q�j�ňÍ��������e�����p�X���[�h���A���ԃe�[�u���i���ҏ��j
     * �@@�@@�̊e���ڂɍX�V�iUpdate�j����B
     * �@@�@@xTrade�`����SONAR����xtrade�����p�X���[�h[sonar_trader_xtrade_password]
     * �@@�@@WEB3�`����SONAR����web3�����p�X���[�h[sonar_trader_web3_password]
     *
     * @@see WEB3Crypt#encrypt
     * @@param l_request ���N�G�X�g�f�[�^
     * @@exception  SYSTEM_ERROR_80003 DB�G���[
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
				"SQL�G���[",
				e
				);
		}

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
