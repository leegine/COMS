head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʃA�b�v���[�h�P���T�[�r�X�����N���X(WEB3AdminIpoLotResultUploadUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���C�g (���u) �V�K�쐬
Revesion History : 2004/12/29 ���(SRA) �c�Ή�>>>044,045
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3SendMailStatusDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ���IPO���I���ʃA�b�v���[�h�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �g�����U�N�V���������F�@@TX_CREATE_NEW
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadUnitServiceImpl implements WEB3AdminIpoLotResultUploadUnitService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadUnitServiceImpl.class);
    
    /**
     * @@roseuid 4112F1900354
     */
    public WEB3AdminIpoLotResultUploadUnitServiceImpl() 
    {
     
    }
    
    /**
     * ���I���ʂ��X�V����B<BR>
     * <BR>
     * �V�K���I�A�b�v���[�h�s�ɂ��āAIPO�\���f�[�^�X�V���s���B<BR>
     * <BR>
     * �P�j�@@�z��擾<BR>
     * �@@IPO�\��List.toArray()�ɂāAIPO�\���̔z����擾����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�ɊY������IPO�\�����擾����B<BR>
     * �@@�P�j�Ŏ擾�����z����A�ȉ��̏����Ɉ�v����v�f���擾����B<BR>
     * �@@�擾�ł��Ȃ������ꍇ�́A�⌇�҂̃f�[�^�����݂��Ȃ��Ɣ��肵�A��O��<BR>
     * �X���[����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�ڋq�R�[�h == IPO�\��[index].get�ڋq�R�[�h()<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00510<BR>
     * <BR>
     * �R�j�@@IPO�\���^�]�͂��X�V����B<BR>
     * �@@update���I���ʂ��R�[������B<BR>
     * <BR>
     * �@@[update���I����()�Ɏw�肷�����]<BR>
     * �@@IPO�\���F�@@�i�Q�j�Ŏ擾����IPO�\���j<BR>
     * �@@is�V�K���I�F�@@is�V�K���I<BR>
     * �@@�Ǘ��ҁF�@@�Ǘ���<BR>
     * �@@���I���ʁF�@@���I����<BR>
     * �@@���I���ʁF�@@���I����<BR>
     * �@@�D�揇�ʁF�@@�D�揇��<BR>
     * <BR>
     * �S�j�@@�X�V��̗v�f��List���폜<BR>
     * �@@IPO�\��List.indexOf(), remove()�ɂāA�Y���v�f��List���폜����B<BR>
     * <BR>
     * �@@[indexOf()�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@IPO�\��[index]<BR>
     * <BR>
     * �@@[remove()�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@�iindexOf()�̖߂�l�j<BR>
     * <BR>
     * @@param l_orderList - IPO�\����ArrayList
     * 
     * @@param l_isNewLottery - �V�K���I���ǂ����̔���<BR>
     * <BR>
     * �@@true�F�@@�V�K���I<BR>
     * �@@false�F�@@�J�㒊�I
     * @@param l_administrator - �Ǘ��҃I�u�W�F�N�g
     * 
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@param l_strLotResult - ���I����
     * @@param l_dblElectedQuantity - ���I����
     * @@param l_lngSubstitutePriority - �D�揇��
     * @@roseuid 40F61BB00292
     */
    public void updateLotResult(
        ArrayList l_orderList, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        MainAccount l_mainAccount, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException 
         
    {
        
        final String STR_METHOD_NAME = " updateLotResult(ArrayList, boolean, WEB3Administrator, MainAccount, String, double, long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�z��擾
        int l_intSize = l_orderList.size();
        if(l_intSize == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
			//2004/12/8 U00531  ��O�ݒ�������s�K�؂Ȃ��ߏC���@@���@@SRA  START
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName() + STR_METHOD_NAME);
//			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80020,"Collection�^�C�v�̃p�����[�^Size�͂O�ł��Ȃ�");
			//2004/12/8 U00531  ��O�ݒ�������s�K�؂Ȃ��ߏC���@@���@@SRA  END
        }
        WEB3IpoOrderImpl[] l_ipoOrder = new WEB3IpoOrderImpl[l_intSize];
        l_orderList.toArray(l_ipoOrder);
        //�Q�j�@@�ڋq�ɊY������IPO�\�����擾����B
        int l_intFlag = 0;
        String l_strAccountCode = l_mainAccount.getAccountCode();
        String l_strBranchCode =  l_mainAccount.getBranch().getBranchCode();
        for(int i = 0; i < l_intSize; i++)
        {
            
            if(l_strAccountCode.equals(l_ipoOrder[i].getAccountCode()) && 
            		l_strBranchCode.equals(l_ipoOrder[i].getMainAccount().getBranch().getBranchCode()))
            {
                
                l_intFlag++;
                int l_intM = l_orderList.indexOf(l_ipoOrder[i]);
                //�R�j�@@IPO�\���^�]�͂��X�V����
                WEB3IpoOrderImpl l_ipoOrder1 = l_ipoOrder[i];
                this.updateLotResult(
                    l_ipoOrder1,
                    l_isNewLottery,
                    l_administrator,
                    l_strLotResult,
                    l_dblElectedQuantity,
                    l_lngSubstitutePriority);
                    
                l_orderList.remove(l_intM);
                break;
            }
            
        }
        
        if(l_intFlag == 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00510,this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * ���I���ʂ��X�V����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i�Ǘ��ҁE���I���ʂt�k�jupdate���I���ʁv<BR>
     * �Q�ƁB
     * @@param l_order - IPO�\���I�u�W�F�N�g
     * 
     * @@param l_isNewLottery - �V�K���I���ǂ����̔���<BR>
     * <BR>
     * �@@true�F�@@�V�K���I<BR>
     * �@@false�F�@@�J�㒊�I
     * @@param l_administrator - �Ǘ��҃I�u�W�F�N�g
     * 
     * @@param l_strLotResult - ���I����
     * @@param l_dblElectedQuantity - ���I����
     * @@param l_lngSubstitutePriority - �D�揇��
     * @@roseuid 40F64C8F02B1
     */
    public void updateLotResult(
        WEB3IpoOrderImpl l_order, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException
        
    {
        
        final String STR_METHOD_NAME = " updateLotResult(WEB3IPOOrder, boolean, WEB3Administrator, String, double, long)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get�ڋq()
        MainAccount l_account = l_order.getMainAccount();

		// �g���A�J�E���g�}�l�[�W���擾
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3GentradeAccountManager l_gentradeAccountManaer = 
			(WEB3GentradeAccountManager) l_finApp.getAccountManager();
				
        //1.2.���������b�N����B
		log.debug("���������b�N����B");
		l_gentradeAccountManaer.lockAccount(
			l_account.getInstitution().getInstitutionCode(),
			l_account.getBranch().getBranchCode(),
			l_account.getAccountCode());
        
        //1.3.getDataSourceObject()
        //1.4.getIPO����()
        //1.5.�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
        l_order.createForUpdateParams();
        IpoOrderParams l_ipoOrderRow= (IpoOrderParams)l_order.getDataSourceObject();
        Product l_product = l_order.getProduct();
        
        //1.6.is�⌇��()
        //1.7.is����()
        //1.8.�⌇�҂Ŏ��ލς݂̏ꍇ�A��O���X���[
        if(l_order.isWaiting() && l_order.isDecline())
        {
            log.debug("�⌇�҂Ŋ��Ɏ��ނ��Ă���");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01735,
                getClass().getName() + STR_METHOD_NAME,
                l_account.getBranch().getBranchCode() + "," + l_account.getAccountCode()); 
        }
                
        //1.9.(*) �s�I�u�W�F�N�g�igetDataSourceObject()�̖߂�l�j�ɍX�V�l���Z�b�g����B
        if (WEB3LotResultDef.ELECTION.equals(l_strLotResult) ||
            WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult))
        {
            l_ipoOrderRow.setElectedQuantity((long)l_dblElectedQuantity);   
        }
       
        if (l_isNewLottery == true)
        {
            l_ipoOrderRow.setLotResult(l_strLotResult);
            
        } else
        {
            l_ipoOrderRow.setLotResultRetry(l_strLotResult);
            
        }
        
        if (l_isNewLottery == true && (WEB3LotResultDef.DEFEAT).equals(l_strLotResult))
        {
            l_ipoOrderRow.setSubstitutePriority(null);
        } else if(l_isNewLottery == false)
        {
        //�����l
        } else
        {
            l_ipoOrderRow.setSubstitutePriority(l_lngSubstitutePriority);
        }
        
        if ((WEB3LotResultDef.DEFEAT).equals(l_strLotResult))
        {
            l_ipoOrderRow.setSendMailStatus(WEB3SendMailStatusDef.EXCEPT_OBJECT);
            
        } else
        {
            l_ipoOrderRow.setSendMailStatus(WEB3SendMailStatusDef.SEND_MAIL);
        }
        
        double l_dblPrice;
        
        if ((((IpoProductRow)l_product.getDataSourceObject()).getPublicPriceIsNull()))
        {
            l_dblPrice = 0;
        }
        else
        {
            l_dblPrice = ((IpoProductRow)l_product.getDataSourceObject()).getPublicPrice();    
        }

        double l_dblPayAmount = l_dblPrice*l_dblElectedQuantity;
        l_ipoOrderRow.setPayAmount(l_dblPayAmount);
        l_ipoOrderRow.setLastUpdater(l_administrator.getAdministratorCode());
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        l_ipoOrderRow.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());
        
        //1.10.get�⏕����()
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)l_order.getSubAccount();
        
        //1.11 �]�͍Čv�Z���K�v�ȏꍇ
        if (!l_isNewLottery || !WEB3LotResultDef.DEFEAT.equals(l_strLotResult))
        {
            //1.11.1 �]�͍Čv�Z(�⏕����)
            WEB3TPTradingPowerReCalcService l_tpTPS  
                = (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
            l_tpTPS.reCalcTradingPower(l_subAccount);
        }
        
        //1.12.saveIPO�\��(IPO�\��)
        //�R�j DB�X�V
        try 
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_ipoOrderRow);  
            log.exiting(STR_METHOD_NAME);
        } catch (DataFindException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        } catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        } catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
}
@
