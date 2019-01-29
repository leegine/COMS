head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDesignateEmbeddedNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�w�薄�ʒm�T�[�r�XImpl(WEB3IfoDesignateEmbeddedNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 ����� (���u) �V�K�쐬
                          001: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.data.HostFotypeClosingContSpecParams;
import webbroker3.ifo.data.HostFotypeClosingContSpecRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.service.delegate.WEB3IfoDesignateEmbeddedNotifyService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingTypesDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;

import webbroker3.gentrade.WEB3GentradeBizDate;


/**
 * (�敨OP�w�薄�ʒm�T�[�r�XImpl)<BR>
 * <BR>
 * �敨OP�w�薄�ʒm�T�[�r�X�����N���X<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3IfoDesignateEmbeddedNotifyServiceImpl implements WEB3IfoDesignateEmbeddedNotifyService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDesignateEmbeddedNotifyServiceImpl.class);

    /**
     * (create�w�薄�ʒm)<BR>
     * <BR>
     * �w�薄�ʒm�L���[�e�[�u���ɍs���쐬����B<BR>
     * <BR>
     * �P�j�@@�����J�e�S������<BR>
     * �@@�ԍϒ����łȂ��ꍇ�i�����P��.�����J�e�S�� != OrderCategEnum.�h�ԍϒ����h�j�A<BR>
     * �������I������B<BR>
     * �@@�ԍϒ����̏ꍇ�̂݁A�ȍ~�̏��������{����B<BR>
     * <BR>
     *�Q�j�@@�ԍώw����擾<BR>
�@@   *�ȉ��̎菇�ɂāA�����P�ʂɊ֘A����ԍώw��������ׂĎ擾����B<BR>
     *�Q�|�P�j�����̒����P�ʂ��AIfoContractSettleOrderUnit�^�ɃL���X�g����B<BR>
     *�Q�|�Q�j�����P��.getContractsToClose()���\�b�h���R�[������B<BR>
     * <BR>
     * �R�j�@@�w�薄�ʒm�s�쐬<BR>
     * �@@�擾�����ԍώw����̗v�f���ɁA�w�薄�ʒm�s��insert����B<BR>
     * �@@�ȉ��A�ԍώw����̗v�f���̌J��Ԃ������B<BR>
     *
     * �@@�R�|�P�j�@@���ʃI�u�W�F�N�g�擾<BR>
     * �@@�敨OP�|�W�V�����}�l�[�W��.getContract()�ɂāA�ԍώw����.���ʂh�c��<BR>
     * �Y�����錚�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[getContract()�Ɏw�肷�����]<BR>
     * �@@���ʂh�c�F�@@�ԍώw����.���ʂh�c<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�敨OP�w�薄�ʒmParams�I�u�W�F�N�g����<BR>
     * �@@�敨OP�w�薄�ʒmParams�I�u�W�F�N�g�𐶐����A<BR>
     * �@@�����P�ʁA�ԍώw����A���ʂ̓��e���A�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�� �敨OP�w�薄�ʒm�s�ҏW���e�ɂ��ẮA<BR>
     * �@@�u����_�敨OP�w�薄�ʒm�L���[�e�[�u��.xls�v<BR>
     *  �@@[�敨OP�w�薄�ʒm��� DB�X�V�d�l]�V�[�g���Q�ƁB<BR>
     * <BR>
     * �@@�R�|�R�j�@@�sinsert<BR>
     * �@@���������s��DB��insert����B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@roseuid 408CCB9B02C7
     */
    public void createDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createDesignateEmbeddedNotify(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�ԍϒ����łȂ��ꍇ�i�����P��.�����J�e�S�� != OrderCategEnum.�h�ԍϒ����h�j
        //�������I������B
        //if (!OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
        if (!(OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()) || 
                OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()) ||
                OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg())
               ))
        {
            return;
        }
        

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

        //�ԍώw����擾
        IfoClosingContractSpec[] l_closingContractSpecs = ((IfoContractSettleOrderUnit)l_orderUnit).getContractsToClose();

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        try
        {

            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            
            //���҃R�[�h
            String l_strTraderCode = null;
            if (l_orderUnit.getTraderId() != 0)
            {
                Trader l_trader = l_finObjMgr.getTrader(l_orderUnit.getTraderId());     //throw NotFoundException
                l_strTraderCode = l_trader.getTraderCode();
            }
            
            Market l_market = l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());  //throw NotFoundException

            Product l_product = l_orderUnit.getProduct();
            IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();

            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();

            for (int i = 0; i < l_closingContractSpecs.length; i++)
            {

                //���ʃI�u�W�F�N�g�擾
                Contract l_contract = l_positionManager.getContract(l_closingContractSpecs[i].getContractId());   //throw NotFoundException
                IfoContractRow l_contractRow = (IfoContractRow)l_contract.getDataSourceObject();

                //�敨OP�w�薄�ʒmParams�I�u�W�F�N�g����
                HostFotypeClosingContSpecParams l_params = new HostFotypeClosingContSpecParams();
                if(WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    l_params.setRequestCode(WEB3HostRequestCodeDef.OPTION_DESIGNATE_EMBEDDED_INDICATION);   //�f�[�^�R�[�h:EI806(�����w���I�v�V�����w�薄�w��)
                }
                if(WEB3FuturesOptionDivDef.FUTURES.equals(l_orderUnitRow.getFutureOptionDiv()))
                {
                    l_params.setRequestCode(WEB3HostRequestCodeDef.FUTURES_DESIGNATE_EMBEDDED_INDICATION);   //�f�[�^�R�[�h:EI807(�����w���敨�w�薄�w��)
                }
                l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //�،���ЃR�[�h
                l_params.setBranchCode(l_branch.getBranchCode());                                       //���X�R�[�h
                l_params.setAccountCode(l_mainAccount.getAccountCode());                                //�ڋq�R�[�h
                l_params.setTraderCode(l_strTraderCode);                                       //���҃R�[�h
                l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //���ʃR�[�h
                l_params.setMarketCode(Long.parseLong(l_market.getMarketCode()));                       //�s��R�[�h
                l_params.setTargetProductCode(l_productRow.getUnderlyingProductCode());                 //�����Y�����R�[�h

                l_params.setDelivaryMonth(l_productRow.getMonthOfDelivery());                           //����
                
                //�敨�I�v�V�������i
                if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_params.setFutureOptionProductType(WEB3IfoProductTypeDef.CALL_OPTIONS);    
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_params.setFutureOptionProductType(WEB3IfoProductTypeDef.PUT_OPTIONS);
                }
                else if (IfoDerivativeTypeEnum.FUTURES.equals(l_productRow.getDerivativeType()))
                {
                    l_params.setFutureOptionProductType(WEB3IfoProductTypeDef.FUTURES);
                }
                
                double l_dblStrkePrice = l_productRow.getStrikePrice();
                if (Double.isNaN(l_dblStrkePrice))
                {
                    l_dblStrkePrice = 0D;
                }
                l_params.setStrikePrice(l_dblStrkePrice);                                 //�s�g���i
                
                l_params.setSplitType(l_productRow.getSplitType());                                     //����
                l_params.setContractType(l_contractRow.getContractType());                              //���敪
                l_params.setClosingType(WEB3ClosingTypesDef.OPEN_DATE_UNIT_PRICE);                      //�w��敪: 4(�����E�P��)

                //�����̑O�c�Ɠ��̎擾
                WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_contractRow.getOpenDate());
                Date l_prevBizDate = l_gentradeBizDate.roll(-1);
                l_params.setOpenDate(l_prevBizDate);  
                //�����N����
                double l_dblContractPrice = l_contractRow.getContractPrice();
                if (Double.isNaN(l_dblContractPrice))
                {
                    l_dblStrkePrice = 0D;
                }
                double l_dblQuantity = l_closingContractSpecs[i].getQuantity();
                if (Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0D;
                }
                l_params.setStrikePrice(l_dblStrkePrice);   

                l_params.setContractPrice(l_dblContractPrice);                            //���P��
                
                l_params.setQuantity(l_dblQuantity);                          //������
                l_params.setCreateDatetime(l_currentTime);                                              //�w������
                l_params.setLastUpdatedTimestamp(l_currentTime);                                        //�X�V����
                l_params.setStatus(WEB3StatusDef.NOT_DEAL);                                             //�����敪
                //�sinsert
                l_processor.doInsertQuery(l_params);    //throw DataFindException, DataQueryException, DataNetworkException
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (undo�w�薄�ʒm)<BR>
     * <BR>
     * �w�薄�ʒm�L���[�e�[�u���̍s�𖳌��ɂ���B<BR>
     * <BR>
     * �@@�敨OP�w�薄�ʒm�L���[�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s���폜�idelete�j����B<BR>
     * <BR>
     * [����]<BR>
     * �敨OP�w�薄�ʒm�L���[�e�[�u��.�،���ЃR�[�h = <BR>
     * �@@�����P��.���X�h�c�ɊY�����镔�X�̏،���ЃR�[�h<BR>
     * �敨OP�w�薄�ʒm�L���[�e�[�u��.���X�R�[�h = <BR>
     * �@@�����P��.���X�h�c�ɊY�����镔�X�̕��X�R�[�h<BR>
     * �敨OP�w�薄�ʒm�L���[�e�[�u��.�����R�[�h = <BR>
     * �@@�����P��.�����h�c�ɊY������ڋq�̌����R�[�h<BR>
     * �敨OP�w�薄�ʒm�L���[�e�[�u��.���ʃR�[�h = <BR>
     * �@@�����P��.���ʃR�[�h<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@roseuid 408F1B35001F
     */
    public void undoDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoDesignateEmbeddedNotify(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
            //MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            String l_strWhere = " institution_code = ? and branch_code = ? and request_code  = ? and order_request_number = ? ";
            Object[] l_objWheres = {
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                WEB3HostRequestCodeDef.OPTION_DESIGNATE_EMBEDDED_INDICATION,
                l_orderUnitRow.getOrderRequestNumber()};
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            
            l_processor.doDeleteAllQuery(HostFotypeClosingContSpecRow.TYPE, l_strWhere, l_objWheres);    //throw DataQueryException, DataNetworkException
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
