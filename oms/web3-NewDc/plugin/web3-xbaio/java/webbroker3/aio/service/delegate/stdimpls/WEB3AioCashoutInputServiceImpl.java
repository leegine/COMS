head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\�����̓T�[�r�XImpl(WEB3AioCashoutInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ���� (���u) �V�K�쐬
Revesion History : 2004/10/25 ���E(���u) ���r���[
Revesion History : 2004/12/09 ���E (���u) �c�Ή�
Revesion History : 2007/03/16 �����q (���u) ���f��No.716
Revesion History : 2010/01/28 ���g�@@ (���u)���f��No.1260
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutInputResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BankCodeDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTransferedFinInstitution;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o���\�����̓T�[�r�XImpl)<BR>
 * �o���\�����̓T�[�r�X�����N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutInputServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashoutInputService 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutInputServiceImpl.class);  
    
    /**
     * �o���\�����̓T�[�r�X���������{����B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���\�����́j���͉�ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBDE8100DB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //�P.�P�jget�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);          
          
        //�P.�Q�jvalidate����(SubAccount)
        //�������ʃ`�F�b�N�����{����B 
        //�ȉ��̃`�F�b�N���s���B 
        // �|��t���ԃ`�F�b�N        
        //�|�V�X�e����~���`�F�b�N  
        //�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        
        //========================FinApp============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //�`�F�b�N���s��
        l_aioOrderManager.validateOrder(l_subAccount);        
        
        //�P.�R�jget������( )
        //���������擾����B 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("���������擾���� = " + l_datOrderBizDate);           
        
        //�P.�S�jArrayList( )
        //ArrayList�̃C���X�^���X�𐶐�����B 
        List  l_lisTransScheduledDates = new Vector();
         
        //�P.�T�jget���ߐU����(SubAccount, Date)
        //���߂̐U�������擾����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        Date l_datClosestTransfer = 
            l_aioOrderManager.getClosestTransferDate(
                l_subAccount, l_datOrderBizDate);
        log.debug("���߂̐U�������擾���� = " + l_datClosestTransfer);        
         
        //�P.�U�jadd(arg0 : Object)
        //ArrayList�ɗv�f��ǉ�����B 
        //[����] 
        //arg0�F get���ߐU����()�̖߂�l 
        l_lisTransScheduledDates.add(l_datClosestTransfer);
        
        //�P.�V�jgetInstitution( )
        //�،���ЃI�u�W�F�N�g���擾����B
        Institution l_institution = l_subAccount.getInstitution(); 
        
        //=========remain zhou-yong NO.4 begin ============
        
        //1.8) calc�c�Ɠ�(Timestamp, int)(�c�Ɠ��v�Z::calc�c�Ɠ�)
        //�A�C�e���̒�`
        //���ݓ��t��5������Z�o����B
        //[����] 
        //����F ���ݓ��t 
        //���Z�^���Z�����F 5
        Timestamp l_currentDateBeforeFive = 
            new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(5);
        log.debug("l_currentDateBeforeFive = " + l_currentDateBeforeFive);
        
        //=========remain zhou-yong NO.4 end ============
        
        //�P.9�j�،����.getDateSourceObject().�o���\����{ > 0 �̏ꍇ
        //���̒l�̉񐔂�Loop������
        
        //�o���\����{���擾����B   
        InstitutionRow  l_institutionRow = 
            (InstitutionRow) l_institution.getDataSourceObject();
        int l_intPaymentReserve = Integer.parseInt(l_institutionRow.getPaymentReserve());            

        if (l_intPaymentReserve > 0)
        {
            for (int i = 1; i <= l_intPaymentReserve; i++)
            {
                //�P.9.�P�jcalc�c�Ɠ�(Timestamp, int)
                //�U���\������Z�o����B 
                //[����] 
                //����F get���ߐU����() �̖߂�l 
                //���Z�^���Z�����FLoop��
            
                //������擾����
                Timestamp l_dteEstTransferDate = 
                    new Timestamp(l_datClosestTransfer.getTime());
                Timestamp l_tscalcBizDate  =  
                    new WEB3GentradeBizDate(l_dteEstTransferDate).roll(i);
                                
                //===========remain zhou-yong NO.6 begin =========
                
                //�Z�o�����U���\����̓��t > ���ݓ��t��5����̓��t �̏ꍇ�A
                //���X�g�ɒǉ����Ȃ��B
                if(WEB3DateUtility.compareToDay(l_tscalcBizDate, l_currentDateBeforeFive) <= 0)
                {
                    //�P.9.�Q�j add(arg0 : Object)
                    //ArrayList�ɗv�f��ǉ�����B 
                    //[����] 
                    //arg0�F calc�c�Ɠ�()�̖߂�l 
                    l_lisTransScheduledDates.add(l_tscalcBizDate);
                }
                
                //===========remain zhou-yong NO.6 end =========
            }
        }
        
        //1.10�j toArray( )
        //�U���\����̔z���ԋp����B 
        Date[] l_datTransScheduledDateLists = 
            new Date[l_lisTransScheduledDates.size()];
        l_lisTransScheduledDates.toArray(l_datTransScheduledDateLists);

        //1.11�jArrayList( )
        List  l_lisPaymentPowerLists = new Vector();
        
        //1.12�j���b�Z�[�W �U���\������X�g�̗v�f����Loop����
        int l_intTransScheduledDateLength = l_datTransScheduledDateLists.length;
        
        for (int i = 0; i < l_intTransScheduledDateLength; i++)
        {
            //===========remain zhou-yong NO.1 begin=========
            
            //1.12.1�jget�o���\�z�`�o�����͉�ʕ\���p�`(�⏕���� : �⏕����, ��n�� : Date)
            //�U���\����̏o���\�z���擾����B 
            //[����] 
            //�⏕�����F get�⏕����()�̖߂�l 
            //��n���F �U���\������X�g�̗v�f 
            WEB3TPTradingPowerService l_tPTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            double l_dblCashoutPossiblePrice =  
                l_tPTradingPowerService.getPaymentTradingPowerAioCashoutInput(
                    l_gentradeSubAccount, l_datTransScheduledDateLists[i]);           
            
            //===========remain zhou-yong NO.1 end=========
            
            //1.12.2�jadd(arg0 : Object)
            //ArrayList�ɗv�f��ǉ�����B 
            //[����] 
            //arg0�F get�o���\�z()�̖߂�l 
            l_lisPaymentPowerLists.add(
                WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice));
        }
                
        //�P.�P�R�jtoArray( )
        //�o���]�͂̔z���ԋp����B 
        String[] l_strPaymentPowerLists = 
            new String[l_lisPaymentPowerLists.size()];
        l_lisPaymentPowerLists.toArray(l_strPaymentPowerLists);
        
        //�P.�P�S�jgetMainAccount( )
        //�ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount(); 
        
        //is�~�ݐU����i��s�����j�o�^
        //���Y�ڋq���A�U����i��s�����j�ɉ~�ݓo�^���Ă��邩�ǂ����𔻒肷��B
        boolean l_blnIsJapaneseCurrencyBankAccountRegi =
            l_gentradeMainAccount.isJapaneseCurrencyBankAccountRegi();
         
        //�P.�P�U�jget�U������Z�@@��( )
        //�ڋq�̐U������Z�@@�փI�u�W�F�N�g���擾����B 
        WEB3GentradeTransferedFinInstitution 
            l_gentradeTransferedFinInstitution =
                l_gentradeMainAccount.getTransferedFinInstitution();
     
        //�P.�P�V�jget��s�R�[�h( )
        //��s�R�[�h���擾����B
        String l_strFinInstitutionCode = 
            l_gentradeTransferedFinInstitution.getFinInstitutionCode();
        
        //�P.�P�W�jget��s��( )
        //��s�����擾����B
        String l_strFinInstitutionName =
            l_gentradeTransferedFinInstitution.getFinInstitutionName();
                
        //�P.�P�X) get��s��() == null�̏ꍇ
        if ( l_strFinInstitutionName == null)
        {
			TransferedFinInstitutionRow l_transferedFinInstitutionRow = null;
			try
			{
				//�U������Z�@@�փe�[�u�����ȉ��̏����Ō�������
				//[�擾����] 
				// �،���ЃR�[�h
				// ���X�R�[�h
				// �ڋq�R�[�h
				// �w��敪="A"
				l_transferedFinInstitutionRow = TransferedFinInstitutionDao.findRowByPk(
				    l_subAccount.getInstitution().getInstitutionCode(),
				    l_gentradeMainAccount.getBranch().getBranchCode(),
				    l_gentradeMainAccount.getAccountCode(),
                    "A" );
			}
			catch (DataFindException l_ex)
			{
				//��O���X���[
				log.debug("�Y������U������Z�@@�ւ�����܂���B");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
				    WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			catch (DataException l_ex)
			{
				//��O���X���[
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			if (WEB3FinTransferDivDef.BANK_TRANSFER.equals(l_transferedFinInstitutionRow.getTransferDiv()))
			{
				//�U������Z�@@��.�U�֋敪="1(��s�j"�̏ꍇ�A��O���X���[����B
				log.debug("�U�֋敪=1(��s)�̏ꍇ�A��s���ɒl������܂���B");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);   
			}
        }   

        //===============remain zhou-yong NO.2 begin =============
        
        //get�x�X�R�[�h( )(�U������Z�@@��::get�x�X�R�[�h)
        //�A�C�e���̒�`
        //�x�X�R�[�h���擾����B 
        String l_strFinBranchCode =
            l_gentradeTransferedFinInstitution.getFinBranchCode();

        //===============remain zhou-yong NO.2 end =============        
        
        //�P.�Q�O�jget�x�X��( )
        //�x�X�����擾����B 
        String l_strFinBranchName =
            l_gentradeTransferedFinInstitution.getFinBranchName();
        
        //�P.�Q�P�jget�a���敪( )
        //�a���敪���擾����B
        String l_strFinSaveDiv =
            l_gentradeTransferedFinInstitution.getFinSaveDiv();
         
        //�P.�Q�Q�jget�����ԍ�( )
        //�����ԍ����擾����B
        String l_strFinAccountNo =
            l_gentradeTransferedFinInstitution.getFinAccountNo();
        
        //�P.�Q�R�jcreateResponse( ) ���X�|���X�f�[�^�𐶐�����B 
        WEB3AioCashoutInputResponse l_aioCashoutInputResponse =
            (WEB3AioCashoutInputResponse) l_request.createResponse();    
        
        //�P.�Q�S�j(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //�ڋq.is�~�ݐU����i��s�����j�o�^()�̖߂�l = false�̏ꍇ�A"0:���o�^"���Z�b�g
        if (!l_blnIsJapaneseCurrencyBankAccountRegi)
        {
            l_aioCashoutInputResponse.transRegistDiv = WEB3BankAccountRegiDef.NOT_REGISTER;
        }
        //�ڋq.is�~�ݐU����i��s�����j�o�^()�̖߂�l = true�̏ꍇ�A"1:�o�^��"���Z�b�g
        else
        {
            l_aioCashoutInputResponse.transRegistDiv = WEB3BankAccountRegiDef.ALREADY_REGISTER;
        }

        //���X�|���X.��s�R�[�h = �U������Z�@@��.get��s�R�[�h()�̖߂�l
        l_aioCashoutInputResponse.bankCode = l_strFinInstitutionCode;
        
        //���X�|���X.��s�� = �U������Z�@@��.get��s��()�̖߂�l
        l_aioCashoutInputResponse.bankName = l_strFinInstitutionName;
        
        //���X�|���X.�x�X�� = �U������Z�@@��.get�x�X��()�̖߂�l
        l_aioCashoutInputResponse.branchBankName = l_strFinBranchName;
        
        //���X�|���X.�a���敪 = �U������Z�@@��.get�a���敪()�̖߂�l
        l_aioCashoutInputResponse.depositDiv = l_strFinSaveDiv;
        
        //==========remain zhou-yong NO.3 begin ===========
        
        //���X�|���X.�����ԍ� = �i�ȉ��̂Ƃ���j
        //�P�j�E��s�R�[�h="9900"�i�X���j�̏ꍇ�A�U������Z�@@��.get�x�X�R�[�h()�̖߂�l + "-" + get�����ԍ�( )�̖߂�l  ���Z�b�g
        //    �E��s�R�[�h!="9900"�i�X���j�̏ꍇ�Aget�����ԍ�( )�̖߂�l�̌���  ���Z�b�g
		if(WEB3BankCodeDef.POSTAL_SAVINGS.equals(l_strFinInstitutionCode))
		{
			l_aioCashoutInputResponse.accountID = l_strFinBranchCode + "-" + l_strFinAccountNo;
		}
		else 
		{
			l_aioCashoutInputResponse.accountID = l_strFinAccountNo;
		}
        
        //==========remain zhou-yong NO.3 end ===========
        
        //���X�|���X.�o���]�� = �o���]�̓��X�g
        l_aioCashoutInputResponse.paymentPowerList = l_strPaymentPowerLists;
        
        //���X�|���X.�U���\��� = �U���\��
        l_aioCashoutInputResponse.transScheduledDateList = l_datTransScheduledDateLists;
        
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutInputResponse;
    }
}
@
