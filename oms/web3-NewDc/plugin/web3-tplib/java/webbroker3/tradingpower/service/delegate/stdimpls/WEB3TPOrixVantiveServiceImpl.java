head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixVantiveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : Vantive�A�g�T�[�r�XImpl�N���X(WEB3TPOrixVantiveServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) �V�K�쐬
 Revision History : 2008/10/22 �И��� (���u) QA:WEB3-TPLIB-A-CD-0087
 */
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.text.*;
import java.util.*;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPMarginSecurityInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.data.OrixTpCalcResultEquityParams;
import webbroker3.tradingpower.data.OrixTpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPTradingStopDivDef;
import webbroker3.tradingpower.message.*;
import webbroker3.tradingpower.service.delegate.WEB3TPOrixVantiveService;
import webbroker3.util.WEB3LogUtility;

/**
 * (Vantive�A�g�T�[�r�XImpl)<BR>
 * Vantive�A�g�T�[�r�X�����N���X�B<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public class WEB3TPOrixVantiveServiceImpl implements WEB3TPOrixVantiveService 
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TPOrixVantiveServiceImpl.class);

    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();
   
   /**
    * (�R���X�g���N�^)
    */
   public WEB3TPOrixVantiveServiceImpl()
   {
   }
   
   /**
    * (execute)<BR>
    * �]�͌v�Z���ʃT�[�r�X���������{����B<BR>
    * 
    * @@param l_request
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
   {
       final String METHOD_NAME = "execute(WEB3BackRequest)";
       log.entering(METHOD_NAME);
       
       //�]�͌v�Z����
       if( l_request instanceof WEB3TPOrixTpCalcResultRequest )
       {
           return createOrixTpCalcResultResponse( (WEB3TPOrixTpCalcResultRequest)l_request );
       }
       else 
       {
           log.error("�s���ȃ��N�G�X�g�ł��B");
           throw new WEB3BaseRuntimeException( WEB3ErrorCatalog.SYSTEM_ERROR_80018, "execute()" );
       }    
   }


   /**
    * (create�]�͌v�Z����)<BR>
    * �]�͌v�Z���ʏ��������{����B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u�i�]�͌v�Z���ʃT�[�r�X�jcreate�]�͌v�Z���ʁv�Q�ƁB<BR>
    * <BR>
    * @@param l_request
    * @@return webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultResponse
    */
    protected WEB3TPOrixTpCalcResultResponse createOrixTpCalcResultResponse(WEB3TPOrixTpCalcResultRequest l_request) throws WEB3BaseException 
    {

        final String INST_CODE               = "46";    /* ��ЃR�[�h */
        final String BR_CODE                 = "307";   /* ���X�R�[�h */
        final String MORNING_PROC            = "1";     /* �����敪 1:�����N�� */
        final String EVENING_PROC            = "2";     /* �����敪 2:�[���N�� */
        final String STR_METHOD_NAME         = "createOrixTpCalcResultResponse(WEB3TPOrixTpCalcResultRequest)"; /* ���\�b�h�� */
        final int DEFAULT_COMMIT_POINT       = 1000;    /* �f�t�H���g�R�~�b�g�|�C���g */

        log.entering(STR_METHOD_NAME);

        //�����敪�����w��̏ꍇ
        if ((l_request.procDiv.equals(MORNING_PROC) == false) && 
            (l_request.procDiv.equals(EVENING_PROC) == false))
        {
            //�G���[���X���[
            log.error("�����敪�����w��ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //From-To�̎w�肪�t�̏ꍇ
        if (l_request.fromAccountID > l_request.toAccountID)
        {
            //�G���[���X���[
            log.error("�p�����[�^�l�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���X�|���X�擾
        WEB3TPOrixTpCalcResultResponse l_response = (WEB3TPOrixTpCalcResultResponse)l_request.createResponse();

        // ���N�G�X�g�擾
        long  l_lngFromAccountId = l_request.fromAccountID; /* From����ID */
        long  l_lngToAccountId   = l_request.toAccountID;   /* To����ID */
        String l_strProcDiv      = l_request.procDiv;       /* �����敪 */

        //���X�|���X��From����ID��To����ID���Z�b�g
        l_response.fromAccountID = l_request.fromAccountID;
        l_response.toAccountID   = l_request.toAccountID;

        //�R�~�b�g�|�C���g���擾
        int l_intCommitPoint;
        if (GtlUtils.getTradingSystem().getPreference("system.tpvantive.commit.count") != null)
        {
        	//SYSTEM_PREFERENCES����擾
        	l_intCommitPoint = Integer.parseInt(GtlUtils.getTradingSystem().getPreference("system.tpvantive.commit.count"));
        }
        else
        {
        	//�f�t�H���g�̃R�~�b�g�|�C���g���Z�b�g
        	l_intCommitPoint = DEFAULT_COMMIT_POINT;
        }
        
        int l_intFromIndex;                                 /* �]�͌v�ZParams�I�u�W�F�N�g�̊J�nIndex */
		int l_intToIndex;                                   /* �]�͌v�ZParams�I�u�W�F�N�g�̏I��Index */
        StringBuffer l_strWhereBuf   = null;                /* SQL WHERE��i�[������o�b�t�@@ */
        String l_strWhere            = null;                /* SQL WHERE��i�[������ */ 
        StringBuffer l_strOrderByBuf = null;                /* SQL ORDER BY��i�[������o�b�t�@@ */
        String l_strOrderBy          = null;                /* SQL ORDER BY��i�[������ */

        //Vantive�A�gTransactionCallback()
        WEB3TPOrixVantiveTransactionCallback l_transactionCallBack =
            new WEB3TPOrixVantiveTransactionCallback();
        
    	//�����ڋq�̎擾(�����N���̎��̂݋N��)
        if (l_strProcDiv.equals(MORNING_PROC))
        {
	        List l_lisTpCalcResultEquityParams       = null;
	        List l_lisTpCalcResultEquityDetailParams = null;
	        try 
	        {
	            Object[] l_bindVars =
                {
                new Long(l_lngFromAccountId),
                new Long(l_lngToAccountId)
                };
	            QueryProcessor l_qp = Processors.getDefaultProcessor();
	            //�ڋq���̒��߂̗]�͌v�Z����<�����ڋq>Param�̎擾
	            //where��
	            l_strWhereBuf = new StringBuffer(" ");
	            l_strWhereBuf.append("(account_id,created_timestamp) IN ");
	            l_strWhereBuf.append("( ");
	            l_strWhereBuf.append("  SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_equity ");
	            l_strWhereBuf.append("  WHERE account_id BETWEEN ? AND ? ");
	            l_strWhereBuf.append("  GROUP BY account_id ");
	            l_strWhereBuf.append(") ");
	            l_strWhere = l_strWhereBuf.toString();
	            //order by��
	            l_strOrderByBuf = new StringBuffer(" ");
	            l_strOrderByBuf.append("account_id");
	            l_strOrderBy = l_strOrderByBuf.toString();
	            l_lisTpCalcResultEquityParams = l_qp.doFindAllQuery(TpCalcResultEquityParams.TYPE,
	                                            l_strWhere,
	                                            l_strOrderBy,
	                                            null,
	                                            l_bindVars,
												new RowType[] {TpCalcResultEquityParams.TYPE});
	            //�ڋq���̒��߂̗]�͌v�Z���ʏڍ�<�����ڋq>Param�̎擾
	            //where��
	            l_strWhereBuf = new StringBuffer(" ");
	            l_strWhereBuf.append("calc_result_equity_id IN ");
	            l_strWhereBuf.append("( ");
	            l_strWhereBuf.append("  SELECT calc_result_equity_id FROM tp_calc_result_equity ");
	            l_strWhereBuf.append("  WHERE (account_id,created_timestamp) IN ");
	            l_strWhereBuf.append("  ( ");
	            l_strWhereBuf.append("    SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_equity ");
	            l_strWhereBuf.append("    WHERE account_id BETWEEN ? AND ? ");
	            l_strWhereBuf.append("    GROUP BY account_id ");
	            l_strWhereBuf.append("  ) ");
	            l_strWhereBuf.append(") ");
	            l_strWhere = l_strWhereBuf.toString();
	            //order by��
	            l_strOrderByBuf = new StringBuffer(" ");
	            l_strOrderByBuf.append("account_id");
	            l_strOrderBy = l_strOrderByBuf.toString();
	            l_lisTpCalcResultEquityDetailParams = l_qp.doFindAllQuery(TpCalcResultEquityDetailParams.TYPE,
	                                            l_strWhere,
	                                            l_strOrderBy,
	                                            null,
	                                            l_bindVars,
												new RowType[] {TpCalcResultEquityDetailParams.TYPE});
	        } 
	        catch (DataNetworkException e)
	        {
	            log.error( e.getMessage(), e);
	            throw new WEB3BaseRuntimeException(
	                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                           "�]�͌v�Z����<�����ڋq>�̃N�G���擾�Ɏ��s���܂����B");
	        } 
	        catch (DataQueryException e) 
	        {
	            log.error( e.getMessage(), e);
	            throw new WEB3BaseRuntimeException(
	                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                       "�]�͌v�Z����<�����ڋq>�̃N�G���擾�Ɏ��s���܂����B");
	        }
			log.info("�����ڋq�擾���� : "+Long.toString(l_lisTpCalcResultEquityParams.size()));
	        
			l_intFromIndex = 0;
			l_intToIndex = 0;
	    	while (l_intToIndex < l_lisTpCalcResultEquityParams.size())
	    	{
	            try
	            {
	            	l_intFromIndex = l_intToIndex;
	                if( l_lisTpCalcResultEquityParams.size() < (l_intFromIndex + l_intCommitPoint))
	                {
	                	l_intToIndex = l_lisTpCalcResultEquityParams.size();
	                }
	                else
	                {
	                	l_intToIndex = l_intFromIndex + l_intCommitPoint;
	                }
	            	l_transactionCallBack.setTpCalcResultParams(l_lisTpCalcResultEquityParams.subList(l_intFromIndex, l_intToIndex));
	            	l_transactionCallBack.setTpCalcResultDetailParams(l_lisTpCalcResultEquityDetailParams.subList(l_intFromIndex, l_intToIndex));
	            	l_transactionCallBack.setBlnMargin(false);
	                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
	                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);
	                l_response.equityRows = l_response.equityRows + l_transactionCallBack.getAffectedRows();
					log.info("�����ڋq:" + Long.toString(l_transactionCallBack.getAffectedRows()) + "���R�~�b�g���܂����B");
	            }
	            catch (DataNetworkException dne)
	            {
	                log.error(dne.getMessage(), dne);
	                throw new WEB3BaseRuntimeException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                    STR_METHOD_NAME,
	                    dne.getMessage(),
	                    dne);
	            }
	            catch (DataCallbackException dce)
	            {
	                log.error(dce.getMessage(), dce);
	                throw new WEB3BaseRuntimeException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                    STR_METHOD_NAME,
	                    dce.getMessage(),
	                    dce);
	            }
	            catch (DataException de)
	            {
	                log.error(de.getMessage(), de);
	                throw new WEB3BaseRuntimeException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                    STR_METHOD_NAME,
	                    de.getMessage(),
	                    de);
	            }
	            finally
				{
	                l_response.equityRows = l_transactionCallBack.getAffectedRows();
				}
	    	}
        }

        //�g�����U�N�V�����R�[���o�b�N�I�u�W�F�N�g�̑}��������0�N���A
        l_transactionCallBack.setAffectedRows(0);

        //�M�p�ڋq�̎擾
        List l_lisTpCalcResultMarginParams = null;
        List l_lisTpCalcResultMarginDetailParams = null;
        try 
        {
            Object[] l_bindVars =
            {
            new Long(l_lngFromAccountId),
            new Long(l_lngToAccountId)
            };
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            //�ڋq���̒��߂̗]�͌v�Z����<�M�p�ڋq>Param�̎擾
            //where��
            l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("(account_id,created_timestamp) IN ");
            l_strWhereBuf.append("( ");
            l_strWhereBuf.append("  SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_margin ");
            l_strWhereBuf.append("  WHERE account_id BETWEEN ? AND ? ");
            l_strWhereBuf.append("  GROUP BY account_id ");
            l_strWhereBuf.append(") ");
            l_strWhere = l_strWhereBuf.toString();
            //order by��
            l_strOrderByBuf = new StringBuffer(" ");
            l_strOrderByBuf.append("account_id");
            l_strOrderBy = l_strOrderByBuf.toString();
            l_lisTpCalcResultMarginParams = l_qp.doFindAllQuery(TpCalcResultMarginParams.TYPE,
                                            l_strWhere,
                                            l_strOrderBy,
                                            null,
                                            l_bindVars,
											new RowType[] {TpCalcResultMarginParams.TYPE});
            //�ڋq���̒��߂̗]�͌v�Z���ʏڍ�<�M�p�ڋq>Param�̎擾
            //where��
            l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("calc_result_margin_id IN ");
            l_strWhereBuf.append("( ");
            l_strWhereBuf.append("  SELECT calc_result_margin_id FROM tp_calc_result_margin ");
            l_strWhereBuf.append("  WHERE (account_id,created_timestamp) IN ");
            l_strWhereBuf.append("  ( ");
            l_strWhereBuf.append("    SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_margin ");
            l_strWhereBuf.append("    WHERE account_id BETWEEN ? AND ? ");
            l_strWhereBuf.append("    GROUP BY account_id ");
            l_strWhereBuf.append("  ) ");
            l_strWhereBuf.append(") ");
            l_strWhere = l_strWhereBuf.toString();
            //order by��
            l_strOrderByBuf = new StringBuffer(" ");
            l_strOrderByBuf.append("account_id");
            l_strOrderBy = l_strOrderByBuf.toString();
            l_lisTpCalcResultMarginDetailParams = l_qp.doFindAllQuery(TpCalcResultMarginDetailParams.TYPE,
                                            l_strWhere,
                                            l_strOrderBy,
                                            null,
                                            l_bindVars,
											new RowType[] {TpCalcResultMarginDetailParams.TYPE});
        } 
        catch (DataNetworkException e)
        {
            log.error( e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                       "�]�͌v�Z����<�M�p�ڋq>�̃N�G���擾�Ɏ��s���܂����B");
        } 
        catch (DataQueryException e) 
        {
            log.error( e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                       "�]�͌v�Z����<�M�p�ڋq>�̃N�G���擾�Ɏ��s���܂����B");
        }
		log.info("�M�p�ڋq�擾���� : "+Long.toString(l_lisTpCalcResultMarginParams.size()));
        
		l_intFromIndex = 0;
		l_intToIndex = 0;
    	while (l_intToIndex < l_lisTpCalcResultMarginParams.size())
    	{
            try
            {
            	l_intFromIndex = l_intToIndex;
                if( l_lisTpCalcResultMarginParams.size() < (l_intFromIndex + l_intCommitPoint))
                {
                	l_intToIndex = l_lisTpCalcResultMarginParams.size();
                }
                else
                {
                	l_intToIndex = l_intFromIndex + l_intCommitPoint;
                }
            	l_transactionCallBack.setTpCalcResultParams(l_lisTpCalcResultMarginParams.subList(l_intFromIndex, l_intToIndex));
            	l_transactionCallBack.setTpCalcResultDetailParams(l_lisTpCalcResultMarginDetailParams.subList(l_intFromIndex, l_intToIndex));
            	l_transactionCallBack.setBlnMargin(true);
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);
				log.info("�M�p�ڋq:" + Long.toString(l_transactionCallBack.getAffectedRows()) + "���R�~�b�g���܂����B");
            }
            catch (DataNetworkException dne)
            {
                log.error(dne.getMessage(), dne);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dne.getMessage(),
                    dne);
            }
            catch (DataCallbackException dce)
            {
                log.error(dce.getMessage(), dce);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dce.getMessage(),
                    dce);
            }
            catch (DataException de)
            {
                log.error(de.getMessage(), de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            finally
			{
                l_response.marginRows = l_transactionCallBack.getAffectedRows();
			}
    	}    	
    	log.exiting(STR_METHOD_NAME);
        //���X�|���X�ԋp
        return l_response;
    }
    /**
     * (Vantive�A�g�g�����U�N�V�����R�[���o�b�N)
     */
    public class WEB3TPOrixVantiveTransactionCallback implements TransactionCallback
    {
		private List tpCalcResultParams;
		private List tpCalcResultDetailParams;
    	private boolean blnMargin;
    	private int affectedRows;

    	/**
         * (�R���X�g���N�^)
         */
        public WEB3TPOrixVantiveTransactionCallback()
        {

        }

        /**
         * (process)
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "WEB3TPOrixVantiveTransactionCallback.process()";
            final String INST_CODE               = "46";    /* ��ЃR�[�h */
            final String BR_CODE                 = "307";   /* ���X�R�[�h */
            final String NOT_DEPOSIT_CUSTOMER    = "0";     /* �a��،��ڋq�łȂ� */
            final String DEPOSIT_CUSTOMER        = "1";     /* �a��،��ڋq */
            final String DEPOSIT_MARGIN          = "2";     /* �M�p�ڋq���� */
            final String TRADING_STOP_ORIX       = "2";     /* �]�͎�����~�t���O (�����~ or �]�͕s��)ORIX�A�g */
            final String MARGIN_SEC_RATE_DEFAULT = "00000"; /* ��K�������R�[�h �����l */
            final double MARGIN_SEC_RATE_MAX    = 999.99;  /* ��K��������L�� �ő�l */
            log.entering(STR_METHOD_NAME);

            Iterator l_iteTpCalcResultParams = tpCalcResultParams.iterator();
            Iterator l_iteTpCalcResultDetailParams = tpCalcResultDetailParams.iterator();

            //����]�̓T�[�r�X�擾
            WEB3TPTradingPowerService l_tpService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            // �V�X�e�����t�̎擾
            SimpleDateFormat l_sdfYMD = new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            Date l_datSysDate = new Date();

            //�����ڋq�̏ꍇ
            if (blnMargin == false)
            {
                while(l_iteTpCalcResultParams.hasNext())
                {
                    //�]�͌v�Z����<�����ڋq>Params���擾
                	TpCalcResultEquityParams l_tpCalcResultEquityParams = (TpCalcResultEquityParams)l_iteTpCalcResultParams.next();

                	//�]�͌v�Z���ʏڍ�<�����ڋq>Params���擾
                	TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams = (TpCalcResultEquityDetailParams)l_iteTpCalcResultDetailParams.next();

                    //�⏕�����I�u�W�F�N�g���擾
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount = 
                            new WEB3GentradeSubAccount(
                                (SubAccountRow) new WEB3GentradeMainAccount(l_tpCalcResultEquityParams.getAccountId()).getSubAccount(
                                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getDataSourceObject());
                    }
                    catch (NotFoundException nfe)
                    {
                        log.error(nfe.getMessage(), nfe);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00670,
                            STR_METHOD_NAME,
                            nfe);
                    }

                    //���Y�]�͏��<�����ڋq>�̎擾
                    WEB3TPTradingPowerCalcEquity l_tpCalcEquity;
					try {
                        l_tpCalcEquity = l_tpService.getTradingPowerCalcEquity(l_subAccount,
                                                                               l_tpCalcResultEquityParams,
                                                                               l_tpCalcResultEquityDetailParams);
					} catch (WEB3SystemLayerException se) {
                        log.error("���Y�]�͏��<�����ڋq>�̎擾�Ɏ��s���܂����BCalcResultEquityId : " + 
                      		      Long.toString(l_tpCalcResultEquityParams.getCalcResultEquityId()));
                        throw new WEB3BaseRuntimeException(
                        		WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                STR_METHOD_NAME,
                                "���Y�]�͏��<�����ڋq>�̎擾�Ɏ��s���܂����BCalcResultEquityId : " + 
								Long.toString(l_tpCalcResultEquityParams.getCalcResultEquityId()),
    							se);
					}
					log.debug("Equity:" + Long.toString(l_tpCalcResultEquityParams.getAccountId()));
					//�I���b�N�X�]�͌v�Z����<�����ڋq>Params�𐶐�����
                    OrixTpCalcResultEquityParams l_calcResultEquityParams = new OrixTpCalcResultEquityParams();
                    //���ʔԍ�
                    l_calcResultEquityParams.setCalcResultEquityId(l_tpCalcEquity.getCalcResultEquity().calc_result_equity_id);
                    //����ID
                    l_calcResultEquityParams.setAccountId(l_tpCalcResultEquityParams.getAccountId());
                    //��ЃR�[�h
                    l_calcResultEquityParams.setInstitutionCode(INST_CODE);
                    //��ƔN����
                    l_calcResultEquityParams.setWorkDate(l_sdfYMD.format(l_datSysDate));
                    //���X�R�[�h
                    l_calcResultEquityParams.setBranchCode(BR_CODE);
                    //�����R�[�h
                    l_calcResultEquityParams.setAccountCode(Long.toString(l_tpCalcResultEquityParams.getAccountId()).substring(8, 14));
                    //�ڋq����
                    //�a��،��]�����ڋq�̏ꍇ
                    if (l_tpCalcEquity.getCalcCondition().isAssetEvalDiv())
                    {
                        l_calcResultEquityParams.setAssetEvaluationDiv(DEPOSIT_CUSTOMER);
                    }
                    //�a��،��]�����ڋq�łȂ��ꍇ
                    else
                    {
                        l_calcResultEquityParams.setAssetEvaluationDiv(NOT_DEPOSIT_CUSTOMER);
                    }
                    //�a���(T+0�`T+4) = get�a����c��(T+0�`T+4) - get�������ϑ��(T+0�`T+4)
                    l_calcResultEquityParams.setAccountBalance0((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_0) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultEquityParams.setAccountBalance1((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_1) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setAccountBalance2((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_2) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setAccountBalance3((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_3) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setAccountBalance4((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_4) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //�����[����(T+1�`T+4) = get�����������(T+1�`T+4)
                    l_calcResultEquityParams.setTodayUnexecutedAmount1((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setTodayUnexecutedAmount2((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setTodayUnexecutedAmount3((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setTodayUnexecutedAmount4((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //���v�蒲���z(T+0�`T+4) = get���v��S����(T+0�`T+4)
                    l_calcResultEquityParams.setDayTradeRestraint0((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultEquityParams.setDayTradeRestraint1((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setDayTradeRestraint2((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setDayTradeRestraint3((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setDayTradeRestraint4((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //���̑��S����(T+0�`T+4) = get���̑��S����(T+0�`T+4)
                    l_calcResultEquityParams.setOtherRestraint0((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultEquityParams.setOtherRestraint1((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setOtherRestraint2((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setOtherRestraint3((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setOtherRestraint4((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //�a��،��]����(T+3�`T+4) = get�a��،��]����(T+3�`T+4)
                    l_calcResultEquityParams.setTrustSecurityAsset3((long)l_tpCalcEquity.getTrustSecurityAsset(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setTrustSecurityAsset4((long)l_tpCalcEquity.getTrustSecurityAsset(WEB3TPSpecifiedPointDef.T_4));
                    //�������t�]��(T+3�`T+4) = calc�������t�\�z(T+3�`T+4)
                    l_calcResultEquityParams.setEquityTradingPower3((long)l_tpCalcEquity.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setEquityTradingPower4((long)l_tpCalcEquity.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //�������t�]��(T+4') = calc�������t�\�z<���v��S�����l��>(T+4)
                    l_calcResultEquityParams.setEquityTradingPower4Dash((long)l_tpCalcEquity.calcEquityTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //�������t�����]��(T+3�`T+4) = calc�g�p�\����(T+3�`T+4)
                    l_calcResultEquityParams.setActualAccountBalance3((long)l_tpCalcEquity.calcActualAccountBalance(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setActualAccountBalance4((long)l_tpCalcEquity.calcActualAccountBalance(WEB3TPSpecifiedPointDef.T_4));
                    //�������t�����]��(T+4') = calc���o�\����(T+4')
                    l_calcResultEquityParams.setActualAccountBalance4Dash((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_4));
                    //�����]��(T+1�`T+4) = calc���o�\����(T+1�`T+4)
                    l_calcResultEquityParams.setActualPaymentBalance1((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setActualPaymentBalance2((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setActualPaymentBalance3((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setActualPaymentBalance4((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_4));
                    //�o�����сE�\��(T+0�`T+2) = get�o���z(T+0�`T+2)
                    l_calcResultEquityParams.setPaymentAmountDesignate0((long)l_tpCalcEquity.getCalcResultDetailEquity().getPaymentAmountDesignate0());
                    l_calcResultEquityParams.setPaymentAmountDesignate1((long)l_tpCalcEquity.getCalcResultDetailEquity().getPaymentAmountDesignate1());
                    l_calcResultEquityParams.setPaymentAmountDesignate2((long)l_tpCalcEquity.getCalcResultDetailEquity().getPaymentAmountDesignate2());
                    //�����]���z(�ی�A��p�ΏہB100%�]��) = get�����]���z(��c)
                    l_calcResultEquityParams.setEquityAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getEquityAssetExecuted());
                    //�ݐϓ���(MMF��)�]���z = get�ݐϓ����]���z(��c)
                    l_calcResultEquityParams.setRuitoAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getRuitoAssetExecuted());
                    //�����M���]���z = get�����M���]���z(��c)
                    l_calcResultEquityParams.setMutualFundAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getMutualFundAssetExecuted());
                    //���]���z = get���]���z(��c)
                    l_calcResultEquityParams.setBondAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getBondAssetExecuted());
                    //�]�͎�����~�t���O
                    //�����~�̏ꍇ
                    if (l_tpCalcEquity.getCalcCondition().isTradingStop()) 
                    {
                        l_calcResultEquityParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //����\�̏ꍇ
                    else
                    {
                        //calc���o�\������0�����̏ꍇ
                        if (l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_0) < 0) 
                        {
                            l_calcResultEquityParams.setTradingStop(TRADING_STOP_ORIX);
                        }
                        //calc���o�\������0�ȏ�̏ꍇ
                        else
                        {
                            l_calcResultEquityParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                        }                  
                    }
                    //�o���]�͒�~�t���O
                    //�]�͉̏ꍇ
                    if (l_tpCalcEquity.getCalcCondition().isPaymentStop()) 
                    {
                        l_calcResultEquityParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //�]�͕s�̏ꍇ
                    else
                    {
                        l_calcResultEquityParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //���̑����i���t�]�͒�~�t���O
                    //�]�͉̏ꍇ
                    if (l_tpCalcEquity.getCalcCondition().isOtherTradingStop()) 
                    {
                        l_calcResultEquityParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //�]�͕s�̏ꍇ
                    else
                    {
                        l_calcResultEquityParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //�쐬���t
                    l_calcResultEquityParams.setCreatedTimestamp(l_datSysDate);
                    //�^�C���X�^���v
                    l_calcResultEquityParams.setLastUpdatedTimestamp(l_datSysDate);
                    /*
                     * ���������A�I���b�N�X�]�͌v�Z����(�����ڋq)Params���A�I���b�N�X�]�͌v�Z����(�����ڋq)�e�[�u����Insert����B
                     */
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();
                        l_processor.doInsertQuery(l_calcResultEquityParams);
                        affectedRows++;
                    }
                    catch (DataException de)
                    {
                        log.error(de.getMessage(), de);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                            STR_METHOD_NAME,
                            de.getMessage() +
                            "calc_result_equity_Id : " + l_calcResultEquityParams.getCalcResultEquityId() + "," +
							"account_Id : " + l_calcResultEquityParams.getAccountId(),
                            de);
                    }
                }
            }
            //�M�p�ڋq�̏ꍇ
            else
            {
                while(l_iteTpCalcResultParams.hasNext())
                {
                    //�]�͌v�Z����<�M�p�ڋq>Params���擾
                	TpCalcResultMarginParams l_tpCalcResultMarginParams = (TpCalcResultMarginParams)l_iteTpCalcResultParams.next();

                	//�]�͌v�Z���ʏڍ�<�M�p�ڋq>Params���擾
                	TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = (TpCalcResultMarginDetailParams)l_iteTpCalcResultDetailParams.next();

                	//�⏕�����I�u�W�F�N�g���擾
                    WEB3GentradeSubAccount l_subAccount = null;
                    //�⏕�������擾
                    try
                    {
                        l_subAccount = 
                            new WEB3GentradeSubAccount(
                                (SubAccountRow) new WEB3GentradeMainAccount(l_tpCalcResultMarginParams.getAccountId()).getSubAccount(
                                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getDataSourceObject());
                    }
                    catch (NotFoundException nfe)
                    {
                        log.error(nfe.getMessage(), nfe);
                        throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00670,
                                STR_METHOD_NAME,
                                nfe);
                    } 
                    //���Y�]�͏��<�M�p�ڋq>�̎擾
                    WEB3TPTradingPowerCalcMargin l_tpCalcMargin = null;
					try {
                        l_tpCalcMargin = l_tpService.getTradingPowerCalcMargin(l_subAccount,
                                                                               l_tpCalcResultMarginParams,
                                                                               l_tpCalcResultMarginDetailParams);
					} catch (WEB3SystemLayerException se) {
                        log.error("���Y�]�͏��<�M�p�ڋq>�̎擾�Ɏ��s���܂����BCalcResultEquityId : " + 
                        		  Long.toString(l_tpCalcResultMarginParams.getCalcResultMarginId()));
                        throw new WEB3BaseRuntimeException(
                        		WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                STR_METHOD_NAME,
                                "���Y�]�͏��<�M�p�ڋq>�̎擾�Ɏ��s���܂����BCalcResultEquityId : " + 
								Long.toString(l_tpCalcResultMarginParams.getCalcResultMarginId()),
    							se);
					}
					log.debug("Margin:" + Long.toString(l_tpCalcResultMarginParams.getAccountId()));
					//�I���b�N�X�]�͌v�Z����<�M�p�ڋq>Params�𐶐�����
                    OrixTpCalcResultMarginParams l_calcResultMarginParams = new OrixTpCalcResultMarginParams();
                    //���ʔԍ�
                    l_calcResultMarginParams.setCalcResultMarginId(l_tpCalcMargin.getCalcResultMargin().calc_result_margin_id);
                    //����ID
                    l_calcResultMarginParams.setAccountId(l_tpCalcResultMarginParams.getAccountId());
                    //��ЃR�[�h
                    l_calcResultMarginParams.setInstitutionCode(INST_CODE);
                    //��ƔN����
                    l_calcResultMarginParams.setWorkDate(l_sdfYMD.format(l_datSysDate));
                    //���X�R�[�h
                    l_calcResultMarginParams.setBranchCode(BR_CODE);
                    //�����R�[�h
                    l_calcResultMarginParams.setAccountCode(Long.toString(l_tpCalcResultMarginParams.getAccountId()).substring(8, 14));
                    //�ڋq����
                    l_calcResultMarginParams.setAssetEvaluationDiv(DEPOSIT_MARGIN);
                    //�a���(T+0�`T+4) = get�a����c��(T+0�`T+4) - get�������ϑ��(T+0�`T+4)
                    l_calcResultMarginParams.setAccountBalance0((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_0) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setAccountBalance1((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_1) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setAccountBalance2((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_2) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setAccountBalance3((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_3) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setAccountBalance4((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_4) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //�����[����(T+1�`T+4) = get�����������(T+1�`T+4)
                    l_calcResultMarginParams.setTodayUnexecutedAmount1((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setTodayUnexecutedAmount2((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setTodayUnexecutedAmount3((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setTodayUnexecutedAmount4((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //���v�蒲���z(T+0�`T+4) = get���v��S����(T+0�`T+4)
                    l_calcResultMarginParams.setDayTradeRestraint0((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setDayTradeRestraint1((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setDayTradeRestraint2((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setDayTradeRestraint3((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setDayTradeRestraint4((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //���̑��S����(T+0�`T+4) = get���̑��S����(T+0�`T+4)
                    l_calcResultMarginParams.setOtherRestraint0((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setOtherRestraint1((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setOtherRestraint2((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setOtherRestraint3((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setOtherRestraint4((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //�����ۏ؋�(T+0�`T+4) = get�����ۏ؋�(T+0�`T+4)
                    l_calcResultMarginParams.setMarginAccountBalance0((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setMarginAccountBalance1((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginAccountBalance2((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginAccountBalance3((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginAccountBalance4((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_4));
                    //��p�،��]���z(T+0�`T+4) = get��p�،��]���z(T+0�`T+4)
                    l_calcResultMarginParams.setSubstituteSecurityAsset0((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setSubstituteSecurityAsset1((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setSubstituteSecurityAsset2((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setSubstituteSecurityAsset3((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setSubstituteSecurityAsset4((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_4));
                    //���ʕ]�����v = get�����ό��ʕ]�����v(T+0)
                    l_calcResultMarginParams.setContractAssetProfitLoss((long)l_tpCalcMargin.getContractAssetProfitLoss(WEB3TPSpecifiedPointDef.T_0));
                    //���o�� = get���ʏ��o��
                    l_calcResultMarginParams.setContractTotalCost((long)l_tpCalcMargin.getContractTotalCost(WEB3TPSpecifiedPointDef.T_0));
                    //����n���ʌ��ϑ��v�݌v(T+0�`T+3) = -get����n���ʌ��ϑ�(T+0�`T+3)
                    l_calcResultMarginParams.setUndeliContractLoss0(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setUndeliContractLoss1(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setUndeliContractLoss2(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setUndeliContractLoss3(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_3));
                    //���ό��ʑO�����i�]���z���v = get���ό��ʑO�����i�]��<����>
                    l_calcResultMarginParams.setTodayRepayContractPreAsset((long)l_tpCalcMargin.getCalcResultDetailMargin().getTodayRepayContractPreAsset());
                    //���ʋ��z(T+0�`T+4) = get�����ό��ʑ��(T+0�`T+4)
                    l_calcResultMarginParams.setContractAmountDayRepay0((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setContractAmountDayRepay1((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setContractAmountDayRepay2((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setContractAmountDayRepay3((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setContractAmountDayRepay4((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_4));
                    //�ۏ؋��]��(T+0�`T+4) = calc�ۏ؋��]��(T+0�`T+4)
                    l_calcResultMarginParams.setMarginPower0((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setMarginPower1((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginPower2((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginPower3((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginPower4((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_4));
                    //�V�K���]��(T+1�`T+4) = calc�M�p�V�K���\�z(T+1�`T+4)
                    l_calcResultMarginParams.setMarginTradingPower1((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginTradingPower2((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginTradingPower3((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginTradingPower4((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //�ۏ؋��a����(T+0�`T+4) = calc�ۏ؋��a����(T+0�`T+4)
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_0) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate0(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_0));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate0(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_1) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate1(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_1));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate1(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_2) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate2(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_2));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate2(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_3) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate3(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_3));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate3(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_4) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate4(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_4));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate4(null);
                    }
                    //�����]��(T+3�`T+4) = calc�M�p�����\�z(T+3�`T+4)
                    l_calcResultMarginParams.setActRecTradingPower3((long)l_tpCalcMargin.calcActualReceiptTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setActRecTradingPower4((long)l_tpCalcMargin.calcActualReceiptTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //�����]��(T+4') = calc�M�p�����\�z<���v��S�����l��>(T+4)
                    l_calcResultMarginParams.setActRecTradingPower4Dash((long)l_tpCalcMargin.calcActualReceiptTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //���������t�]��(T+3�`T+4) = calc�������t�\�z(T+3�`T+4)
                    l_calcResultMarginParams.setEquityTradingPower3((long)l_tpCalcMargin.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setEquityTradingPower4((long)l_tpCalcMargin.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //���������t�]��(T+4') = calc�������t�\�z<���v��S�����l��>(T+4)
                    l_calcResultMarginParams.setEquityTradingPower4Dash((long)l_tpCalcMargin.calcEquityTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //����n���ʋ��z�݌v(T+0�`T+3) = get����n���ʑ��(T+0�`T+3)
                    l_calcResultMarginParams.setUndeliContractAmount0((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setUndeliContractAmount1((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setUndeliContractAmount2((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setUndeliContractAmount3((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_3));
                    //�ۏ؋����o�]��(T+0�`T+4) = calc�ۏ؋����o�]��(T+0�`T+4)
                    l_calcResultMarginParams.setMarginDrawPower0((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setMarginDrawPower1((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginDrawPower2((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginDrawPower3((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginDrawPower4((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_4));
                    //�������o�]��(T+1�`T+4) = calc���̑����i���t�\�z<���v��S�����l��>(T+1�`T+4)
                    l_calcResultMarginParams.setOtherTradingPower1((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setOtherTradingPower2((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setOtherTradingPower3((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setOtherTradingPower4((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //��������(T+0�`T+4) = calc���������z(T+0�`T+4)
                    l_calcResultMarginParams.setDemandamount0((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setDemandamount1((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setDemandamount2((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setDemandamount3((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setDemandamount4((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_4));
                    //�o�����сE�\��(T+0�`T+2) = get�o���z(T+0�`T+2)
                    l_calcResultMarginParams.setPaymentAmountDesignate0((long)l_tpCalcMargin.getCalcResultDetailMargin().getPaymentAmountDesignate0());
                    l_calcResultMarginParams.setPaymentAmountDesignate1((long)l_tpCalcMargin.getCalcResultDetailMargin().getPaymentAmountDesignate1());
                    l_calcResultMarginParams.setPaymentAmountDesignate2((long)l_tpCalcMargin.getCalcResultDetailMargin().getPaymentAmountDesignate2());
                    //��K���`�F�b�N�G���[�������̎擾
                    WEB3TPMarginSecurityInfo[] l_marginSecInfo = null;
					try {
						l_marginSecInfo = l_tpService.getMarginSecurityInfo(l_subAccount);
					} catch (WEB3SystemLayerException se) {
                        log.error(se.getMessage(), se);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            STR_METHOD_NAME,
                            se.getMessage(),
							se);
					}
/*
                    WEB3TPMarginSecurityInfo[] l_marginSecInfo = new WEB3TPMarginSecurityInfo[2];
                    WEB3TPMarginSecurityInfo l_marginSecList1 = new WEB3TPMarginSecurityInfo();
                    WEB3TPMarginSecurityInfo l_marginSecList2 = new WEB3TPMarginSecurityInfo();
                    l_marginSecList1.marginSecProductId = 1301860100000L;
                    l_marginSecList1.marginSecRate = 999.99;
                    l_marginSecList2.marginSecProductId = 1301860200000L;
                    l_marginSecList2.marginSecRate = 1000.0;               
                    l_marginSecInfo[0] = l_marginSecList1;
                    l_marginSecInfo[1] = l_marginSecList2;
*/
                    //��K���`�F�b�N�G���[����������ꍇ
                    if (l_marginSecInfo != null)
                    {
                        String l_strMarginSecProductCode = ""; /* ��K���Ώۖ��� */
                        double l_dblMarginSecRate        = 0;  /* ��K����L�� */

                        for (int i=0;i<l_marginSecInfo.length;i++)
                        {
                            //�ő��L���̖���ID�A��L�����擾����B
                            if (l_dblMarginSecRate < l_marginSecInfo[i].marginSecRate)
                            {
                                //��K���Ώۖ��� = ��K���Ώۖ���ID
                                l_strMarginSecProductCode = Long.toString(l_marginSecInfo[i].marginSecProductId).substring(4, 9);
                                //��K����L�� = ��K����L��
                                l_dblMarginSecRate = l_marginSecInfo[i].marginSecRate;
                            }
                        }
                        //999.99 �𒴂���ꍇ��999.99�ɐݒ�
                        if (l_dblMarginSecRate > MARGIN_SEC_RATE_MAX) 
                        {
                            l_calcResultMarginParams.setMarginSecRate(MARGIN_SEC_RATE_MAX);
                        }
                        else
                        {
                            l_calcResultMarginParams.setMarginSecRate(l_dblMarginSecRate);
                        }
                        l_calcResultMarginParams.setMarginSecProductCode(l_strMarginSecProductCode);
                    }
                    //��K���`�F�b�N�G���[�������Ȃ��ꍇ
                    else
                    {
                        l_calcResultMarginParams.setMarginSecProductCode(MARGIN_SEC_RATE_DEFAULT);
                        l_calcResultMarginParams.setMarginSecRate(null);
                    }
                    //�����]���z(�ی�A��p�ΏہB100%�]��) = get�����]���z(��c)
                    l_calcResultMarginParams.setEquityAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getEquityAssetExecuted());
                    //�ݐϓ���(MMF��)�]���z = get�ݐϓ����]���z(��c)
                    l_calcResultMarginParams.setRuitoAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getRuitoAssetExecuted());
                    //�����M���]���z = get�����M���]���z(��c)
                    l_calcResultMarginParams.setMutualFundAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getMutualFundAssetExecuted());
                    //���]���z = get���]���z(��c)
                    l_calcResultMarginParams.setBondAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getBondAssetExecuted());
                    //�]�͎�����~�t���O
                    //�����~�̏ꍇ
                    if (l_tpCalcMargin.getCalcCondition().isTradingStop()) 
                    {
                        l_calcResultMarginParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //����\�̏ꍇ
                    else
                    {
                    	//calc�a��������]�͂�0���� �܂��� calc�Ǐؗ]�͂�0�����̏ꍇ
                        if (l_tpCalcMargin.calcAccountBalanceDemandPower(WEB3TPSpecifiedPointDef.T_0) < 0 ||
                        	l_tpCalcMargin.calcMarginCallPower(WEB3TPSpecifiedPointDef.T_0)	< 0) 
                        {
                            l_calcResultMarginParams.setTradingStop(TRADING_STOP_ORIX);
                        }
                        //calc���o�\������0�ȏ�̏ꍇ
                        else
                        {
                            l_calcResultMarginParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                        }                  
                    }
                    //�M�p�V�K���]�͒�~�t���O
                    //�����~�̏ꍇ
                    if (l_tpCalcMargin.getCalcCondition().isMarginOpenPositionStop()) 
                    {
                        l_calcResultMarginParams.setMarginOpenPositionStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //����\�̏ꍇ
                    else
                    {
                        l_calcResultMarginParams.setMarginOpenPositionStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //�o���]�͒�~�t���O
                    //�]�͉̏ꍇ
                    if (l_tpCalcMargin.getCalcCondition().isPaymentStop()) 
                    {
                        l_calcResultMarginParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //�]�͕s�̏ꍇ
                    else
                    {
                        l_calcResultMarginParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //���̑����i���t�]�͒�~�t���O
                    //�]�͉̏ꍇ
                    if (l_tpCalcMargin.getCalcCondition().isOtherTradingStop()) 
                    {
                        l_calcResultMarginParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //�]�͕s�̏ꍇ
                    else
                    {
                        l_calcResultMarginParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //�쐬���t
                    l_calcResultMarginParams.setCreatedTimestamp(l_datSysDate);
                    //�^�C���X�^���v
                    l_calcResultMarginParams.setLastUpdatedTimestamp(l_datSysDate);
                    /*
                     * ���������A�I���b�N�X�]�͌v�Z����(�M�p�ڋq)Params���A�I���b�N�X�]�͌v�Z����(�����ڋq)�e�[�u����Insert����B
                     */
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();
                        l_processor.doInsertQuery(l_calcResultMarginParams);
                        affectedRows++;
                    }
                    catch (DataException de)
                    {
                        log.error(de.getMessage(), de);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                            STR_METHOD_NAME,
                            de.getMessage() +
                            "calc_result_margin_Id : " + l_calcResultMarginParams.getCalcResultMarginId() + "," +
							"account_Id : " + l_calcResultMarginParams.getAccountId(),
                            de);
                    }
                }
            }        
            log.exiting(STR_METHOD_NAME);
            return null;
        }

		/**
		 * @@return affectedRows ��߂��܂��B
		 */
		public int getAffectedRows() {
			return affectedRows;
		}
		/**
		 * @@param tpCalcResultParams tpCalcResultParams ��ݒ�B
		 */
		public void setTpCalcResultParams(List tpCalcResultParams) {
			this.tpCalcResultParams = tpCalcResultParams;
		}
		/**
		 * @@param tpCalcResultDetailParams tpCalcResultDetailParams ��ݒ�B
		 */
		public void setTpCalcResultDetailParams(List tpCalcResultDetailParams) {
			this.tpCalcResultDetailParams = tpCalcResultDetailParams;
		}
		/**
		 * @@param blnMargin blnMargin ��ݒ�B
		 */
		public void setBlnMargin(boolean blnMargin) {
			this.blnMargin = blnMargin;
		}
		/**
		 * @@param affectedRows affectedRows ��ݒ�B
		 */
		public void setAffectedRows(int affectedRows) {
			this.affectedRows = affectedRows;
		}

    }
}
@
