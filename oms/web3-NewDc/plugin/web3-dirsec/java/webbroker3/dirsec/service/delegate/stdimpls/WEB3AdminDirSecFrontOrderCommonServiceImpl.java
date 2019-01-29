head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecFrontOrderCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�t�����g�����Ǘ����ʃT�[�r�XImpl) (WEB3AdminDirSecFrontOrderCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.118
Revesion History : 2007/02/17  ���؎q (���u) �����̖��No.004
Revesion History : 2007/03/01  �Ӑ� (���u) �d�l�ύX���f��No.023,025,No.041-049
Revesion History : 2007/02/28  �Ј��� (���u) �d�l�ύX���f��No.059-No.060,No.090-093
Revesion History : 2009/05/21  �����F (���u) �d�l�ύX���f��No.153
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeReqResDivDef;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3NoticeTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.dirsec.WEB3FrontOrderSwitchManagement;
import webbroker3.dirsec.define.WEB3AdminFrontDataCodeDef;
import webbroker3.dirsec.define.WEB3AdminFrontHostStatusDivDef;
import webbroker3.dirsec.define.WEB3AdminFrontSwitchStartDivDef;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeDao;
import webbroker3.gentrade.data.VirtualServerChangeRow;
import webbroker3.gentrade.data.VirtualServerInformationRow;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҃t�����g�����Ǘ����ʃT�[�r�XImpl)<BR>
 * <BR>
 * �Ǘ��҃t�����g�����Ǘ����ʃT�[�r�XImpl�N���X<BR>
 * <BR>
 * WEB3AdminDirSecFrontOrderCommonServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminDirSecFrontOrderCommonServiceImpl implements WEB3AdminDirSecFrontOrderCommonService {
    
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecFrontOrderCommonServiceImpl.class);
    
    /**
     * ������ؑփe�[�u�����A�����ɊY�����郌�R�[�h���擾���A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����Ŕ�����ؑփe�[�u������������B<BR>
     * �@@�������^�C�v�A�s��R�[�h�A�t�����g�����V�X�e���敪�A�����o�H�敪�̏����Ń\�[�g����B<BR>
     * <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�����^�C�v in (1�F����, 6�F�敨�I�v�V����) and 
     * �@@�s��R�[�h in (1�F����, 2�F���, 3�F���É�, 9�FNNM, 10�FJASDAQ) 
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@param �����^�C�v - �����^�C�v<BR>
     * @@return ������ؑ�Row[]<BR>
     * @@roseuid 42D252DF0062
     */
    public OrderSwitchingRow[] getOrderRouteSwitchingRows(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getOrderRouteSwitchingRows(String)";
        log.entering(STR_METHOD_NAME);

        OrderSwitchingRow[] l_row = null;
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstSwitchRows = new ArrayList(); 
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type in (?,?) ");
        l_sbWhere.append(" and market_code in (?,?,?,?,?) ");

        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                Integer.toString(ProductTypeEnum.EQUITY.intValue()),
                Integer.toString(ProductTypeEnum.IFO.intValue()),
                WEB3MarketCodeDef.TOKYO,
                WEB3MarketCodeDef.OSAKA,
                WEB3MarketCodeDef.NAGOYA,
                WEB3MarketCodeDef.NNM,
                WEB3MarketCodeDef.JASDAQ
            };        

        try{
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRows = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                "product_type asc, market_code asc, front_order_system_code asc, submit_order_route_div asc",
                null,
                l_objWhere);
            
            // �������ʂ����݂��Ȃ��ꍇ�A�G���[���b�Z�[�W���X���[����B
            if(l_lstSwitchRows.size() == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                 
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            
        log.exiting(STR_METHOD_NAME);
        
        l_row = new OrderSwitchingRow[l_lstSwitchRows.size()];
        
        l_lstSwitchRows.toArray(l_row);
        
        return l_row;
    }
    
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u����"SONAR�S��Q"��<BR>
     * ���R�[�h�����݂��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����ɂāA��Q���z�T�[�o�ؑ֊Ǘ��e�[�u����<BR>
     * �@@��������B<BR>
     * <BR>
     * �@@�ؑ֎w�������敪 = 09�FSONAR�S��Q and<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�t�����g����������敪�R�[�h = �p�����[�^.�t�����g����������敪�R�[�h And<BR>
     * �@@�t�����g�����V�X�e���敪 = �p�����[�^.�t�����g�����V�X�e���敪 And<BR>
     * �@@�t�����g��������敪�R�[�h = 1�F�������� and<BR>
     * �@@�����^�C�v = �p�����[�^.�����^�C�v<BR>
     * <BR>
     * �@@�������ʂ��擾�ł����ꍇ�A��O���X���[����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h<BR>
     * @@param �t�����g����������敪�R�[�h - �t�����g����������敪�R�[�h<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪<BR>
     * @@param �����^�C�v - �����^�C�v<BR>
     * @@roseuid 42D24F1E0275
     */
    public void validateSonarCheck(String l_institutionCode, String l_frontExCode, 
                                    String l_frontSystemCode, String l_strProductType) throws WEB3BusinessLayerException, WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateSonarCheck(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" change_req_res_div = ? ");
        l_sbWhere.append(" and institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");

        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                WEB3ChangeReqResDivDef.SONAR_ALL_TROUBLES,
                l_institutionCode,
                l_frontExCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType
            };        
        
        try{
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
                
            // �������ʂ����݂���ꍇ�A�G���[���b�Z�[�W���X���[����B
            if(l_lstSwitchRoutes.size() != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02213,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                 
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            
    }

    /**
     * �ؑ֋N���敪�̒l�Ɋ�Â��A�ؑ֏��������s����B<BR>
     * <BR>
     * �P�j�@@get�f�[�^�R�[�h�i�j�ŁA�f�[�^�R�[�h���擾����B<BR>
     * <BR>
     * �Q�j�@@get���[�U�f�[�^�i�j�ŁA���[�U�f�[�^���擾����B<BR>
     * <BR>
     * �@@ �@@[����] <BR>
     * �@@ �@@�ϊ��s��R�[�h�F �p�����[�^.���N�G�X�g.�ϊ��s��R�[�h <BR>
     * �@@ �@@�����^�C�v�F �p�����[�^.���N�G�X�g.�����^�C�v <BR>
     * �@@ �@@�ؑ֋N���敪�F �p�����[�^.���N�G�X�g.�ؑ֋N���敪 <BR>
     * <BR>
     * �R�j�@@if�@@�ؑ֋N���敪 == "0:�����o�H�ؑ�"�̏ꍇ<BR>
     * <BR>
     * �@@�R�|�P�j�@@�����o�H�ؑ֏����N���X�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�I�u�W�F�N�g.execute�����o�H�ؑցi�j�ŁA�����o�H�V�K���s�������s���B<BR>
     * <BR>
     * <BR>
     * �S�j�@@else�@@if�@@�ؑ֋N���敪 == "6:�S���������ċN��"�̏ꍇ<BR>
     * <BR>
     * �@@�S�|�P�j MAXAS�g���K�[�𔭍s�B�@@<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@new WEB3MQMessageSpec(�p�����[�^.���N�G�X�g.�،���ЃR�[�h , �P�j�̃f�[�^�R�[�h, �Q�j�̃��[�U�f�[�^)<BR>
     * �@@�@@�@@�@@�@@�@@WEB3MQGatewayService.send(���b�Z�[�W���e�FWEB3MQMessageSpec)<BR>
     * <BR>
     * �@@�S�|�Q�j�@@return����B<BR>
     * <BR>
     * �T�j�@@else<BR>
     * <BR>
     * �@@�@@�T�|�P�j�@@get��Q���z�T�[�o�ؑ֊Ǘ��e�[�u�����R�[�h�i�j�ŁA�v���n���R�[�h�������ς̃��R�[�h����������B<BR>
     * <BR>
     * <BR>
     * �@@�@@�T�|�Q�j�@@�T�|�P�j�ŁA�擾�������R�[�h�̃T�C�Y��Loop�������s���B<BR>
     * <BR>
     * �@@�@@�@@�T�|�Q�|�P�j�@@get�ؑ֎w�������n�敪�i�j�ŁA�����n�敪���擾�B<BR>
     * <BR>
     * �@@�@@�@@�T�|�Q�|�Q�j�@@VirtualServerChangeDao.findRowByPk()�ŁA�����n�̃��R�[�h���擾�B<BR>
     * ���B<BR>
     * <BR>
     * �@@�@@�@@�T�|�Q�|�R�j�@@�T�|�Q�|�Q�j�Ń��R�[�h�����݂��Ȃ��ꍇ<BR>
     * �@@�@@�@@ �@@�@@�@@�T�|�Q�|�R�|�P�j�@@set�ؑ֊Ǘ��e�[�u���X�V���ځi�j�ŁA<BR>
     * �@@�@@�@@ �@@�@@�@@��Q���z�T�[�o�ؑ֊Ǘ��e�[�u�����X�V����p�����[�^���擾����B<BR>
     * <BR>
     * �@@�@@�@@ �@@�@@�@@ �@@�@@�@@[����]<BR>
     * �@@�@@�@@ �@@�@@�@@ �@@�@@�@@�Ȃ�<BR>
     * <BR>
     * �@@�@@�@@ �@@�@@�@@�T�|�Q�|�R�|�Q�j�@@doUpdateQuery�i�j�ŁA�v���n�����σ��R�[�h�𖢏����ɍX�V����B<BR>
     * <BR>
     * �@@�@@�T�|�R�j�@@MAXAS�g���K�[�𔭍s�B<BR>
     * �@@�@@ �@@�@@new WEB3MQMessageSpec(�p�����[�^.���N�G�X�g.�،���ЃR�[�h , �P�j�̃f�[�^�R�[�h, �Q�j�̃��[�U�f�[�^)<BR>
     * �@@�@@ �@@�@@WEB3MQGatewayService.send(���b�Z�[�W���e�FWEB3MQMessageSpec)<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@WEB3MQMessageSpec = new WEB3MQMessageSpec(�،���ЃR�[�h , <BR>
     * �f�[�^�R�[�h ,���[�U�f�[�^)<BR>
     * <BR>
     * @@param ���N�G�X�g�I�u�W�F�N�g - �����o�H�ؑ֊������N�G�X�g�B<BR>
     * @@roseuid 42F19DD600EA
     */
    public void executeSwitchTransactionIssue(WEB3AdminFrontRouteChangeCompleteRequest l_request) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "executeSwitchTransactionIssue(WEB3AdminFrontRouteChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        // �،���ЃR�[�h
        String l_institutionCode = l_request.institutionCode;
        // �ؑ֏��������敪
        String l_changeProcessDiv = l_request.changeProcessDiv;
        // �ؑ֋N���敪
        String l_changeStartDiv = l_request.changeStartDiv;
        // �g���K�[���s���s�����߂̃f�[�^�R�[�h���擾
        String l_dataCode = this.getDataCode(l_changeStartDiv,l_changeProcessDiv);
        // �g���K�[���s���s�����߂̃��[�U�f�[�^���擾
        String l_userData = this.getUserData(l_request.convertMarketCode,l_request.productType, l_changeStartDiv);
        // �g���K�[���s�T�[�r�X�C���X�^���X
        WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
        // ��Q���z�T�[�o�e�[�u���v���n�E������List
        List l_lstReqVirtuals = new ArrayList();

        // �ؑ֋N���敪�������o�H�ؑւ̏ꍇ�A�����o�H�ؑ֏����N���X�ɂĐV�K���s�������s���B
        if(l_changeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH))
        {
            // �����o�H�ؑ֏����N���X�I�u�W�F�N�g
            WEB3FrontOrderSwitchManagement l_switchManagement = new WEB3FrontOrderSwitchManagement(this, l_request);   
            
            // �����o�H�ؑ֐V�K���s
            l_switchManagement.executeOrderRouteSwitching();           
        }
        // �ؑ֋N���敪���S���������ċN���̏ꍇ�A�g���K�[���s�������s���B
        else if(l_changeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.ALLCORR_REQ_RESTART))
        {
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(l_institutionCode, l_dataCode, l_userData);
            
            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);
            
            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> �S���������ċN�����M �����I�I�I");
            }
            else
            {
                log.debug("==> �S���������ċN�����M ���s �I�I�I");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            return;
        }
        else
        {
            // �v���n�ŏ����ς̃��R�[�h���擾
            l_lstReqVirtuals = this.getVirtualServerChangeRecord(l_request);

            //Loop����
            Iterator  virtualsObj = l_lstReqVirtuals.iterator(); 
            
            while(virtualsObj.hasNext())
            {
                // VirtualServerChangeRow�I�u�W�F�N�g�̒��o
                VirtualServerChangeRow l_changeRow = (VirtualServerChangeRow)virtualsObj.next();
                
                // �����n�敪���擾
                String  l_resDiv = this.getSwitchPointResponseDiv(l_changeStartDiv);
                
                // �擾�����v���n�E�����σ��R�[�h����ɁA�����n���R�[�h���擾
                try
                {
                    VirtualServerChangeDao.findRowByPk(l_changeRow.getVirtualServerNumberMarket(),l_resDiv,
                                            l_changeRow.getNoticeType(), l_changeRow.getFrontOrderExchangeCode());                    
                }
                // ���R�[�h�����݂��Ȃ��ꍇ�A�v���n�����σ��R�[�h�𖢏����ɍX�V����B
                catch(DataFindException notData)
                {
                    try
                    {
                        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                        l_queryProcessor.doUpdateQuery(l_changeRow.getPrimaryKey(), this.setVirtualServerChangeUpdateCalums());
                    }
                    catch (DataException l_de)
                    {
                        log.error(STR_METHOD_NAME, l_de);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_de.getMessage(),
                        l_de);
                    }
                }
                catch (DataException de)
                {
                    log.error(STR_METHOD_NAME, de);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }
            }
            // �g���K�[���s�������s���B
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(l_institutionCode, l_dataCode, l_userData);
            
            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);
            
            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> �ċN�����M �����I�I�I");
            }
            else
            {
                log.debug("==> �ċN�����M ���s �I�I�I");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }

    /**
     * �����̔�����ؑ�Rows��蔭������̈ꗗ��<BR>
     * �쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.������ؑ�Rows�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�@@��������C���X�^���X�𐶐�����B<BR>
     * �@@�Q�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�ϊ��s��R�[�h = get�t�����g�����s��R�[�h�i������ؑ�Row.�s��R�[�h, <BR>
     * ������ؑ�Row.�t�����g�����V�X�e���敪�j<BR>
     * �@@�@@�s��R�[�h = ������ؑ�Row.�s��R�[�h<BR>
     * �@@�@@�t�����g�����V�X�e���敪 = ������ؑ�Row.�t�����g�����V�X�e���敪<BR>
     * �@@�@@�����^�C�v = ������ؑ�Row.�����^�C�v<BR>
     * �@@�@@�����o�H = ������ؑ�Row.�����o�H�敪<BR>
     * �@@�@@�L���t���O = ������ؑ�Row.�L���t���O<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param ������ؑ�Rows - ������ؑ�Row�I�u�W�F�N�g�̔z��<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminOrderRouteInfo[]<BR>
     * @@roseuid 42D25498035F
     */
    public WEB3AdminOrderRouteSwitchingInfo[] createSwitchRouteInfoList(OrderSwitchingRow[] l_switchRows)    
    {
        final String STR_METHOD_NAME = "createSwitchRouteInfoList(OrderSwitchingRow[])";
        log.entering(STR_METHOD_NAME);
        
        // WEB3AdminOrderRouteSwitchingInfo[]�^�̔z��𐶐�
        WEB3AdminOrderRouteSwitchingInfo[] routeInfo = null;
        
        // ArrayList�̐���
        List l_lstSwitchInfos = new ArrayList();
        
        for(int i = 0; l_switchRows.length > i ; i++)
        {
            // ��������C���X�^���X����
            WEB3AdminOrderRouteSwitchingInfo l_switchInfo = new WEB3AdminOrderRouteSwitchingInfo();

            // �ϊ��s��R�[�h���i�[ 
            l_switchInfo.convertMarketCode = this.getFrontOrderMarketCode(l_switchRows[i].getMarketCode(), 
                                                                            l_switchRows[i].getFrontOrderSystemCode());

            // �s��R�[�h���i�[
            l_switchInfo.marketCode = l_switchRows[i].getMarketCode();
            
            // �t�����g�����V�X�e���敪���i�[
            l_switchInfo.frontOrderSystemCode = l_switchRows[i].getFrontOrderSystemCode();
            
            // �����^�C�v���i�[
            l_switchInfo.productType = Integer.toString(l_switchRows[i].getProductType().intValue());
            
            // �����o�H�敪���i�[
            l_switchInfo.submitOrderRouteDiv = l_switchRows[i].getSubmitOrderRouteDiv();
            
            // �L���t���O���i�[
            l_switchInfo.validFlag = l_switchRows[i].getValidFlag();
            
            // ��������C���X�^���X��List�Ɋi�[
            l_lstSwitchInfos.add(l_switchInfo);
        }
        // List�̃T�C�Y���A�z��I�u�W�F�N�g�𐶐�
        routeInfo = new WEB3AdminOrderRouteSwitchingInfo[l_lstSwitchInfos.size()];
        
        // List�̓��e��z��^�ɕϊ�
        l_lstSwitchInfos.toArray(routeInfo);

        log.exiting(STR_METHOD_NAME);
        return routeInfo;
    }

    /**
     * ������ؑփe�[�u������f�[�^���������A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@List�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@DB����<BR>
     * �@@�ȉ��̏����Ŕ�����ؑփe�[�u������������B<BR>
     * �������^�C�v�A�s��R�[�h�A�t�����g�����V�X�e���敪�̏����Ń\�[�g����B<BR>
     * <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h and <BR>
     * �@@�����^�C�v in (1�F����, 6�F�敨�I�v�V����)  and <BR>
     * �@@�����o�H�敪 = "�R�F�t�����g�������n"<BR>
     * <BR>
     * �S�j�@@�������ʂ�0���̏ꍇ�A�G���[�R�[�h���X���[����B<BR>
     * <BR>
     * �T�j�@@�擾�������ʂ�List�I�u�W�F�N�g�Ɋi�[����B<BR>
     * <BR>
     * �U�j�@@List�I�u�W�F�N�g��ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@return List<BR>
     * @@roseuid 42E46BB2019E
     */
    public List getFrontSwitchRouteTarget(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFrontSwitchRouteTarget(String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type in ( ?,?) ");
        l_sbWhere.append(" and submit_order_route_div = ? ");

        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                Integer.toString(ProductTypeEnum.EQUITY.intValue()),
                Integer.toString(ProductTypeEnum.IFO.intValue()),
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION
            };        
        
        try{
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                "product_type asc, market_code asc, front_order_system_code asc",
                null,
                l_objWhere);
            
            // �������ʂ�0���̏ꍇ�A�G���[���b�Z�[�W���X���[����B
            if(l_lstSwitchRoutes.size() == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                 
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            

        log.exiting(STR_METHOD_NAME);
        return l_lstSwitchRoutes;
    }

    /**
     * ���z�T�[�o���e�[�u������A���z�T�[�o�������擾���A�����������I�u�W�F�N�g�Ɋi<BR>
     * �[����B<BR>
     * <BR>
     * �P�j �t�����g����������敪�R�[�h���擾����B <BR>
     *   �P�|�P�j�@@�p�����[�^.�����^�C�v��1�F�����̏ꍇ�A<BR>
     *      ���������T�[�r�X.get�t�����g����������敪�R�[�h�i�j<BR>
     *    [����] <BR>
     *    �s��R�[�h�F �p�����[�^.�s��R�[�h <BR>
     * <BR>
     *   �P�|�Q�j�@@�p�����[�^.�����^�C�v��6�F�敨�I�v�V�����̏ꍇ�A<BR>
     *      �敨OP�����T�[�r�X.get�t�����g����������敪�R�[�h�i�j <BR>
     *      [����] <BR>
     *      �s��R�[�h�F �p�����[�^.�s��R�[�h <BR>
     * <BR>     
     * �Q�j�@@DB����<BR>
     * <BR>
     *  �����F�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h and <BR>
     *         �t�����g����������敪�R�[�h = <BR>
     * �P�j�Ŏ擾�����t�����g����������敪�R�[�h and <BR>
     *         �t�����g�����V�X�e���敪 = �p�����[�^.�t�����g�����V�X�e���敪 and <BR>
     *         �t�����g��������敪�R�[�h = "�P�F��������" and <BR>
     *         �����^�C�v = �p�����[�^.�����^�C�v <BR> 
     * <BR>
     * �R�j�@@�擾�������I�u�W�F�N�g.���z�T�[�o���� = �������ʌ���<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �����^�C�v - �����^�C�v�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param �����������I�u�W�F�N�g - �����������I�u�W�F�N�g�B<BR>
     * @@roseuid 42E467C801CC
     */
    public void getVitualServerCount(String l_institutionCode, String l_marketCode, String l_frontSystemCode,String l_strProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getVitualServerCount(String, String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // �t�����g����������敪�R�[�h
        String l_frontExchangeCode = null;

        // �s��R�[�h����ɁA�t�����g����������敪�R�[�h���擾
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        // �敨OP�����T�[�r�X
        WEB3IfoFrontOrderService l_orderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        
        // �p�����[�^.�����^�C�v��1�F�����̏ꍇ�A���������T�[�r�X.get�t�����g����������敪�R�[�h�i�j
        if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(l_strProductType))
        {
            l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        }
        
        if (Integer.toString(ProductTypeEnum.IFO.intValue()).equals(l_strProductType))
        {
            l_frontExchangeCode = l_orderService.getFrontOrderExchangeCode(l_marketCode);
        }
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstVirtualCounts = new ArrayList(); 
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");   
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_institutionCode,
                l_frontExchangeCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType
            };        
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstVirtualCounts = l_queryProcessor.doFindAllQuery(
                VirtualServerInformationRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
            
            // �������ʌ������A�����������N���X.���z�T�[�o�����Ɋi�[
            l_processInfoUnit.virtualServerQuantity = Integer.toString(l_lstVirtualCounts.size());
        }
        
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            
    }

    /**
     * ��Q���z�T�[�o�Ǘ��e�[�u������ؑ֎w�������敪�������n�̃��R�[�h���擾���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@���������T�[�r�X.get�t�����g����������敪�R�[�h�i�j����A�t�����g�������<BR>
     * ���敪�R�[�h���擾����B<BR>
     * <BR>
     * �Q�j�@@List�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �R�j�@@DB����<BR>
     * �@@�ȉ��̏����ŏ�Q���z�T�[�o�Ǘ��e�[�u������������B<BR>
     * <BR>
     * �،���ЃR�[�h�R�[�h =�P�j�Ŏ擾�����،���ЃR�[�h and <BR>
     * �t�����g����������敪�R�[�h =�P�j�Ŏ擾�����t�����g����������敪�R�[�h and <BR>
     * �t�����g�����V�X�e���敪 = �p�����[�^.�t�����g�����V�X�e���敪 and <BR>
     * �t�����g��������敪�R�[�h = "�P�F��������" and <BR>
     * �����^�C�v = �p�����[�^.�����^�C�v and<BR>
     * �ؑ֎w�������敪 in ('02:�ʔԏƉ��' , <BR>
     *                              '04:�ʒm��s��������' , <BR>
     *                              '06:�ʒm��s����' , <BR>
     *                              '08:�ʒm�đ�����')<BR>
     * <BR>
     * �S�j�@@�擾�������ʂ�List�I�u�W�F�N�g�Ɋi�[����B<BR>
     * <BR>
     * �T�j�@@List�I�u�W�F�N�g��ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v - �����^�C�v�B<BR>
     * @@return List<BR>
     * @@roseuid 42E4A1EA0315
     */
    public List getSwitchRouteResRcord(String l_institutionCode, 
        String l_marketCode, 
        String l_frontSystemCode, 
        String l_strProductType) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getSwitchRouteResRcord(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // �t�����g����������敪�R�[�h
        String l_frontExchangeCode = null;
        // �����n���R�[�hList
        List l_lstResRcords = null;

        // �s��R�[�h����ɁA�t�����g����������敪�R�[�h���擾
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and change_req_res_div in (?,?,?,?) ");   
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_institutionCode,
                l_frontExchangeCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType, 
                WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_RESPONSE,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_RESPONSE,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_RESPONSE,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE                
            };        
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstResRcords = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            

        return l_lstResRcords;
    }

    /**
     * ��Q���z�T�[�o�Ǘ��e�[�u������ؑ֎w�������敪���v���n�̃��R�[�h���擾���A���ʂ�<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@���������T�[�r�X.get�t�����g����������敪�R�[�h�i�j����A�t�����g�������<BR>
     * ���敪�R�[�h���擾����B<BR>
     * <BR>
     * �Q�j�@@List�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �R�j�@@DB����<BR>
     * �@@�ȉ��̏����ŏ�Q���z�T�[�o�Ǘ��e�[�u������������B<BR>
     * <BR>
     * �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h and 
     * �t�����g����������敪�R�[�h =�P�j�Ŏ擾�����t�����g����������敪�R�[�h and <BR>
     * �t�����g�����V�X�e���敪 = �p�����[�^.�t�����g�����V�X�e���敪 and <BR>
     * �t�����g��������敪�R�[�h = "�P�F��������" and <BR>
     * �����^�C�v = �p�����[�^.�����^�C�v and<BR>
     * �ؑ֎w�������敪 in ('01:�ʔԏƉ�v��' , <BR>
     *                              '03:�ʒm��s�����v��' , <BR>
     *                              '05:�ʒm��s�v��' , <BR>
     *                              '07:�ʒm�đ��v��')<BR>
     * <BR>
     * �S�j�@@�擾�������ʂ�List�I�u�W�F�N�g�Ɋi�[����B<BR>
     * <BR>
     * �T�j�@@List�I�u�W�F�N�g��ԋp����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v - �����^�C�v�B<BR>
     * @@return List<BR>
     * @@roseuid 42FC54A400A2
     */
    public List getSwitchRouteReqRcord(String l_institutionCode, 
        String l_marketCode, 
        String l_frontSystemCode, 
        String l_strProductType) throws WEB3SystemLayerException  
    {
        final String STR_METHOD_NAME = "getSwitchRouteReqRcord(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // �t�����g����������敪�R�[�h
        String l_frontExchangeCode = null;
        // �v���n���R�[�hList
        List l_lstReqRcords = null;

        // �s��R�[�h����ɁA�t�����g����������敪�R�[�h���擾
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and change_req_res_div in (?,?,?,?) ");   
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_institutionCode,
                l_frontExchangeCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType,
                WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST                
            };        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstReqRcords = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            

        return l_lstReqRcords;
    }

    /**
     * ������ؑփe�[�u�����猻�����o�H���������A���ʂ��擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����Ŕ�����ؑփe�[�u������������B<BR>
     * <BR>
     * �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h and <BR>
     * �����^�C�v = �p�����[�^.�����^�C�v and <BR>
     * �s��R�[�h = �p�����[�^.�s��R�[�h and <BR>
     * �t�����g�����V�X�e���敪 = �p�����[�^.�t�����g�����V�X�e���敪 and <BR>
     * �L���t���O = "�P�FON"<BR>
     * <BR>
     * �Q�j�@@if�@@�������ʂ�0���̏ꍇ�A"�X�F������~"�������������N���X�Ɋi�[����B<BR>
     * <BR>
     * �R�j�@@else�@@�擾�������ʂ������������N���X�Ɋi�[����B<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v - �����^�C�v<BR>
     * @@param �����������N���X - �����������N���X�B<BR>
     * @@return String<BR>
     * @@roseuid 42EE00700203
     */
    public String getNowOrderRoute(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode, String l_strProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getNowOrderRoute(String, String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_code = ? ");        
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and valid_flag = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strProductType,
                l_strMarketCode,
                l_strFrontSystemCode,
                WEB3ValidFlag.ON
            };        
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
            
            // �������ʂ�0���̏ꍇ�A"�X�F������~"���Z�b�g
            if(l_lstSwitchRoutes.size() == 0)
            {
                l_processInfoUnit.submitOrderRouteDiv = WEB3SubmitOrderRouteDivDef.ORDER_STOP;
                
                return WEB3SubmitOrderRouteDivDef.ORDER_STOP;                  
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            
        
        // �����o�H�敪���Z�b�g
        OrderSwitchingRow l_swtichRow = (OrderSwitchingParams)l_lstSwitchRoutes.get(0);
        
        l_processInfoUnit.submitOrderRouteDiv = l_swtichRow.getSubmitOrderRouteDiv();
        
        return l_swtichRow.getSubmitOrderRouteDiv();
    }

    /**
     * ������������L���[�e�[�u������A"�s���t�m�F�O"�A"�s���t�m�F��"�A<BR>
     * "�s���t�m�F��"�̒����������擾���A���������I�u�W�F�N�g�Ɋi�[����B<BR>
     * <BR>
     *�P�j�@@���������T�[�r�X.get�t�����g����������敪�R�[�h�i�j����A<BR>
     *�@@�@@�@@�t�����g����������敪�R�[�h�� <BR>
     *�@@�@@�@@�擾����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�s��R�[�h�F �p�����[�^.�s��R�[�h <BR>
     *<BR>
     * �Q�j�@@"�s���t�m�F�O"�A"�s���t�m�F��"�A"�s���t�m�F��"�̃f�[�^��Loop�������s���A�擾����B<BR>
     * <BR>
     * for(�v���f�[�^�T�C�Y)<BR>
     * <BR>
     * �@@�Q�|�P�j�@@����������𐶐�����B<BR>
     * <BR>
     * �@@�@@�،���ЃR�[�h = �H and <BR>
     * �@@�@@�t�����g����������敪�R�[�h = �H and <BR>
     * �@@�@@�t�����g�����V�X�e���敪 = �H and <BR>
     * �@@�@@�t�����g��������敪�R�[�h = �H and <BR>
     * �@@�@@�����o�H�敪 = �H and <BR>
     * �@@�@@if( "�s���t�m�F�O" )<BR>
     * <BR>
     * �@@�@@�@@ �����敪 in (? , ?)<BR>
     * <BR>
     * �@@�@@else("�s���t�m�F��" or "�s���t�m�F��")<BR>
     * <BR>
     * �@@�@@�@@ �����敪 = ?<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@�p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�R�j�@@�P�j�Ŏ擾�����t�����g����������敪�R�[�h��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�S�j�@@�p�����[�^.�t�����g�����V�X�e���敪��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�T�j�@@"�P�F��������"��ArrayList�ǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�U�j�@@if( "�s���t�m�F�O" , "�s���t�m�F��" )<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�Q�F�t�����g�������n"��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@else�i "�s���t�m�F��" �j�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�R�F�t�����g�������n"��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�V�j�@@if( "�s���t�m�F�O" )<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�P�F���M��"��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�Q�FAMG���͊���"��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@else if�i "�s���t�m�F��" , "�s���t�m�F��" �j�@@<BR>
     *   �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�X�F�s���t�m�F��"��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@else�i"�s���t�m�F��" �j�@@<BR>
     *   �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�W�F�s���t�m�F��"��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@DB����<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�����������I�u�W�F�N�g�Ɍ������i�[����B<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�P�j�@@if�i"�s���t�m�F�O"�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������I�u�W�F�N�g.�m�F�O���� = �f�[�^�擾�T�C�Y<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�Q�j�@@if�i"�s���t�m�F��"�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������I�u�W�F�N�g.�m�F������ = �f�[�^�擾�T�C�Y<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�R�j�@@if�i"�s���t�m�F��"�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������I�u�W�F�N�g.�m�F�ό��� = �f�[�^�擾�T�C�Y�@@<BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h - �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@param ���������I�u�W�F�N�g - ���������I�u�W�F�N�g�B<BR>
     * @@roseuid 42EE05720280
     */
    public void getGrayOrder(String l_institutionCode, String l_marketCode, 
            String l_frontSystemCode, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getGrayOrder(String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // �t�����g����������敪�R�[�h
        String l_frontExchangeCode = null;

        // �s��R�[�h����ɁA�t�����g����������敪�R�[�h���擾
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // �L���[�e�[�u����t�����敪��List�Ɋi�[
        List l_lstStatusDivs = new ArrayList();
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE);
        
        // List�T�C�Y���Aloop�������s���B
        for(int lstSize = 0; l_lstStatusDivs.size() > lstSize ; lstSize++)
        {
            // ArrayList�I�u�W�F�N�g�̐���
            List l_lstHostRcords = new ArrayList(); 
        
            // ��������������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and front_order_exchange_code = ? ");
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and front_order_trade_code = ? ");
            l_sbWhere.append(" and submit_order_route_div = ? ");            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_sbWhere.append(" and status in (?,?) ");                
            }
            else
            {
                l_sbWhere.append(" and status = ? ");            
            }
            
            // ���������R���e�i�̐���
            List l_lstWheres = new ArrayList();
            
            l_lstWheres.add(l_institutionCode);
            l_lstWheres.add(l_frontExchangeCode);
            l_lstWheres.add(l_frontSystemCode);
            l_lstWheres.add(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)) || 
                    WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION);
            }
            else{
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);            
            }
            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize))){
                // ���M��
                l_lstWheres.add(WEB3FrontOrderStatusDef.SENDED);
                // AMG���͊���
                l_lstWheres.add(WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize))){
                // �s���t�m�F��
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize))){
                // �s���t�m�F��
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMED);
            }

            // �z��ɕϊ�            
            Object[] l_objWhere = l_lstWheres.toArray();
 
            try
            {
                // DB����
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lstHostRcords = l_queryProcessor.doFindAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataException de)
            {
                log.error(STR_METHOD_NAME, de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            
            // �s��m�F�O���R�[�h�̏ꍇ�A�����������I�u�W�F�N�g.�m�F�O�����Ƀ��R�[�h�������i�[
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.beforeNumber = Integer.toString(l_lstHostRcords.size());
            }
            // �s��m�F�����R�[�h�̏ꍇ�A�����������I�u�W�F�N�g.�m�F�������Ƀ��R�[�h�������i�[
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.inNumber = Integer.toString(l_lstHostRcords.size());
            }
            // �s��m�F�σ��R�[�h�̏ꍇ�A�����������I�u�W�F�N�g.�m�F�ό����Ƀ��R�[�h�������i�[
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.afterNumber = Integer.toString(l_lstHostRcords.size());
            }                                        
        }
    }

    /**
     * �f�[�^�R�[�h��ԋp����B<BR>
     * <BR>
     * �m�p�����[�^�n<BR>
     * �ؑ֋N���敪<BR>
     * <BR>
     * �P�j�@@�f�[�^�R�[�h�𐶐�����B<BR>
     * �@@�P�|�P�j�@@if�@@�ؑ֋N���敪 == "�O�F�����o�H�ؑ�"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@String�@@"�f�[�^�R�[�h" = "�`�w0�`�w1"<BR>
     * <BR>
     * �@@�P�|�Q�j�@@if�@@�ؑ֋N���敪 == "�P�F�ʔԏƉ�v���ċN��"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@String�@@"�f�[�^�R�[�h" = "�`�w0�`�w1"<BR>
     * <BR>
     * �@@�P�|�R�j�@@if�@@�ؑ֋N���敪 == "�Q�F�ʒm��s�����v���ċN��"�̏ꍇ<BR>
     * <BR>
     *                  if�@@�ؑ֏��������敪 == �h�O�F�ʔԏƉ�������h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@          String�@@"�f�[�^�R�[�h" = "�`�w�y�x1"<BR>
     *                  if�@@�ؑ֏��������敪 == �h�P�F�S�������������h�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@          String�@@"�f�[�^�R�[�h" = "�`�w9�w1"<BR>
     * <BR>
     * �@@�P�|�S�j�@@if�@@�ؑ֋N���敪 == "�R�F�ʒm��s�v���ċN��"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@String�@@"�f�[�^�R�[�h" = "�`�w9�w1"<BR>
     * <BR>
     * �@@�P�|�T�j�@@if�@@�ؑ֋N���敪 == <BR>
     * "�S�F�ʒm�đ��v���i��t�n�j�ċN��"or"�T�F�ʒm�đ��v���i���n�j�ċN��"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@String�@@"�f�[�^�R�[�h" = "�`�w8�w1"<BR>
     * <BR>
     * �@@�P�|�U�j�@@if�@@�ؑ֋N���敪 == "�U�F�S���������ċN��"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@String�@@"�f�[�^�R�[�h" = "�`�w2�w1"<BR>
     * <BR>
     * �Q�j�@@�f�[�^�R�[�h��ԋp����B<BR>
     * @@param �ؑ֋N���敪 - �ؑ֋N���敪�B<BR>
     * @@param �ؑ֏��������敪 - �ؑ֏��������敪�B<BR>
     * @@return String<BR>
     * @@roseuid 42F1E259002E
     */
    public String getDataCode(String l_strSwitchBootDiv,String l_changeProcessDiv) 
    {
        // return�l
        String retDataCode = null;
        
        // �ؑ֋N���敪 =�����o�H�ؑւ̏ꍇ�A�ʔԏƉ�v���R�[�h���Z�b�g
        if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NUBER_REF_REQ_CODE;
        }
        // �ؑ֋N���敪 =�ʔԏƉ�v���ċN���̏ꍇ�A�ʔԏƉ�v���R�[�h���Z�b�g
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NUBER_REF_REQ_CODE;
        }
        // �ؑ֋N���敪 =�ʒm��s�����v���ċN���̏ꍇ�A�ʒm��s�����v���R�[�h���Z�b�g
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART))
        {
            // �ؑ֏��������敪���h0:�ʔԏƉ�������h�̏ꍇ
            if(l_changeProcessDiv.equals(WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH))
            {
                retDataCode = WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REL_REQ_CODE;
            }
            // �ؑ֏��������敪���h1:�S�������������h�̏ꍇ
            else
            {
                retDataCode = WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REQ_CODE;
            }
        }
        // �ؑ֋N���敪 =�ʒm��s�v���ċN���̏ꍇ�A�ʒm��s�v���R�[�h���Z�b�g
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REQ_CODE;
        }
        // �ؑ֋N���敪 =�ʒm�đ��v���i��t�n�j�ċN�� or �ʒm�đ��v���i���n�j�ċN���̏ꍇ�A�ʒm�đ��v���R�[�h���Z�b�g
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                                                                                l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NOTICERESEND_REQ_CODE;
        }
        // �ؑ֋N���敪 =�S���������ċN���̏ꍇ�A�S���������v���R�[�h���Z�b�g
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.ALLCORR_REQ_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.ALLCORR_REQ_CODE;
        }

        return retDataCode;
    }

    /**
     * ���[�U�f�[�^��ԋp����B    <BR>
     * <BR>
     * �P�j�ؑ֋N���敪 =�ʒm�đ��v���i��t�n�j�ċN�� || �ʒm�đ��v���i���n�j�ċN���̏ꍇ�A  <BR>
     * <BR>
     *  �@@�P�|�P�jget�ʒm��ʁi�j�ŁA�ʒm��ʂ��擾����B  <BR>
     * <BR>
     *  �@@�@@[����]  <BR>
     *  �@@�@@�ؑ֋N���敪 = �p�����[�^.�ؑ֋N���敪 <BR>
     * <BR>
     *  �@@�P�|�Q�jString "���[�U�f�[�^" = "�p�����[�^.�ϊ��s��R�[�h" + "�ʒm���" <BR>
     * <BR>
     * �Q�jelse ���[�U�f�[�^ = �p�����[�^.�ϊ��s��R�[�h   <BR>
     * <BR>
     * �R�j���[�U�f�[�^ += �p�����[�^.�����^�C�v <BR>
     * <BR>
     * �S�j�l��ԋp����B   <BR>
     * <BR>
     * @@param �ϊ��s��R�[�h - ��ʕ\���p�ϊ��s��R�[�h�B<BR>
     * @@param �����^�C�v - �����^�C�v�B<BR>
     * @@param �ؑ֋N���敪 - �ؑ֋N���敪�B<BR>
     * @@return String<BR>
     * @@roseuid 42F1E26B0138
     */
    public String getUserData(String l_strConvertMarketCode, String l_strProductType, String l_strChangeStartDiv) 
    {
        // return�l
        String retUserData = null;
        
        StringBuffer editUserCode = new StringBuffer();
        
        // �ʒm���
        String noticeType = null;
        
        // �ؑ֋N���敪 =�ʒm�đ��v���i��t�n�j�ċN�� || �ʒm�đ��v���i���n�j�ċN���̏ꍇ�A�ʒm��ʁ�"01" || "02"���Z�b�g
        if(l_strChangeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                l_strChangeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART)) 
        {
            noticeType = this.getNoticeType(l_strChangeStartDiv);
            
            // �ϊ��s��R�[�h�ƁA�ʒm��ʂ������B
            retUserData = editUserCode.append(l_strConvertMarketCode).append(noticeType).toString();
        }
        // �ϊ��s��R�[�h���i�[
        else
        {
            retUserData = l_strConvertMarketCode;
        }
        
        retUserData += l_strProductType;

        return retUserData;
    }

    /**
     * �����ɉ����āA�ؑ֎w�������敪�̗v���n��ԋp����B<BR>
     * <BR>
     * [�p�����[�^]<BR>
     * �ؑ֋N���敪<BR>
     * <BR>
     * <BR>
     * �P�j�@@if �ؑ֋N���敪 == "1:�ʔԏƉ�v���ċN��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"01:�ʔԏƉ�v��"<BR>
     * <BR>
     * �Q�j�@@else if �ؑ֋N���敪 == "2:�ʒm��s�����v���ċN��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"03:�ʒm��s�����v��"<BR>
     * <BR>
     * �R�j�@@else if �ؑ֋N���敪 == "3:�ʒm��s�v���ċN��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"05:�ʒm��s�v��"<BR>
     * <BR>
     * �S�j�@@else if �ؑ֋N���敪 == <BR>
     * "4:�ʒm�đ��v���i��t�n�j�ċN��"�@@or�@@5:�ʒm�đ��v���i���n�j�ċN��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"07:�ʒm�đ��v��"<BR>
     * @@param �ؑ֋N���敪 - �ؑ֋N���敪�B<BR>
     * @@return String<BR>
     * @@roseuid 430037A700A5
     */
    private String getSwitchPointReqDiv(String l_switchStartDiv)
    {
        // return�l
        String l_reqDiv = null;

        // �ؑ֋N���敪���ʔԏƉ�v���ċN���̏ꍇ�A�ʔԏƉ�v����Ԃ�
        if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST;
        }
        // �ؑ֋N���敪���ʒm��s�����v���ċN���̏ꍇ�A�ʒm��s�����v����Ԃ�
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST;
        }
        // �ؑ֋N���敪���ʒm��s�v���ċN���̏ꍇ�A�ʒm��s�v����Ԃ�
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST;
        }
        // �ؑ֋N���敪���ʒm�đ��v���i��t�n�jor�ʒm�đ��v���i���n�j�ċN���̏ꍇ�A�ʒm�đ��v����Ԃ�
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                    l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST;
        }
        
        return l_reqDiv;
    }

    /**
     * �����ɉ����āA�ؑ֎w�������敪�̉����n��ԋp����B<BR>
     * <BR>
     * [�p�����[�^]<BR>
     * �ؑ֋N���敪<BR>
     * <BR>
     * <BR>
     * �P�j�@@if �ؑ֋N���敪 == "1:�ʔԏƉ�v���ċN��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"02:�ʔԏƉ��"<BR>
     * <BR>
     * �Q�j�@@else if �ؑ֋N���敪 == "2:�ʒm��s�����v���ċN��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"04:�ʒm��s��������"<BR>
     * <BR>
     * �R�j�@@else if �ؑ֋N���敪 == "3:�ʒm��s�v���ċN��"<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"06:�ʒm��s����"<BR>
     * <BR>
     * �S�j�@@else if �ؑ֋N���敪 == <BR>
     * "4:�ʒm�đ��v���i��t�n�j�ċN��"�@@or�@@5:�ʒm�đ��v���i���n�j�ċN��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@return�@@"08:�ʒm�đ�����"<BR>
     * @@param �ؑ֋N���敪 - �ؑ֋N���敪�B<BR>
     * @@return String<BR>
     * @@roseuid 430068E70358
     */
    private String getSwitchPointResponseDiv(String l_switchStartDiv)
    {
        // return�l
        String l_resDiv = null;

        // �ؑ֋N���敪���ʔԏƉ�v���ċN���̏ꍇ�A�ʔԏƉ����Ԃ�
        if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_RESPONSE;
        }
        // �ؑ֋N���敪���ʒm��s�����v���ċN���̏ꍇ�A�ʒm��s����������Ԃ�
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_RESPONSE;
        }
        // �ؑ֋N���敪���ʒm��s�v���ċN���̏ꍇ�A�ʒm��s������Ԃ�
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_RESPONSE;
        }
        // �ؑ֋N���敪���ʒm�đ��v���i��t�n�jor�ʒm�đ��v���i���n�j�ċN���̏ꍇ�A�ʒm�đ�������Ԃ�
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                                l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE;
        }
        
        return l_resDiv;
    }

    /**
     * �ؑ֋N���敪�ɉ������ʒm��ʂ�ԋp����B<BR>
     * <BR>
     * [�p�����[�^]<BR>
     * �ؑ֋N���敪<BR>
     * <BR>
     * <BR>
     * �P�j�@@if�@@�ؑ֋N���敪 == "4:�ʒm�đ��v���i��t�n�j�ċN��"�̏ꍇ<BR>
     * <BR>
     * �@@�@@return "01:��t�n"<BR>
     * <BR>
     * �Q�j�@@else if�@@�ؑ֋N���敪 == "5:�ʒm�đ��v���i���n�j�ċN��"�̏ꍇ<BR>
     * <BR>
     * �@@�@@return "02:���n"<BR>
     * <BR>
     * �R�j�@@else<BR>
     * <BR>
     * �@@�@@return "00:�f�t�H���g"<BR>
     * @@param �ؑ֋N���敪 - �ؑ֋N���敪�B<BR>
     * @@return String<BR>
     * @@roseuid 43003DE801FC
     */
    private String getNoticeType(String l_switchStartDiv) 
    {
        // return�l
        String l_notice = null;

        // �ؑ֋N���敪���ʒm�đ��v���i��t�n�j�̏ꍇ�A��t�n��Ԃ�
        if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART))
        {
            l_notice = WEB3NoticeTypeDef.ACCEPT_TYPE;
        }
        // �ؑ֋N���敪���ʒm�đ��v���i���n�j�̏ꍇ�A���n��Ԃ�
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            l_notice = WEB3NoticeTypeDef.EXECUTED_TYPE;
        }
        else
        {
            l_notice = WEB3NoticeTypeDef.DEFAULT;
        }

        return l_notice;
    }

    /**
     * ������ؑփe�[�u����DEOS���R�[�h�̗L�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�ȉ��̏����Ŕ�����ؑփe�[�u������������B<BR>
     * <BR>
     * �@@[����] 
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h and  <BR>
     * �@@�����^�C�v = �p�����[�^.�����^�C�v and <BR>
     * �@@�s��R�[�h = �p�����[�^.�s��R�[�h and <BR>
     * �@@�����o�H�敪 = "�Q�F�t�����g�������n" and  <BR>
     * �@@�t�����g�����V�X�e���敪 = �p�����[�^.�t�����g�����V�X�e���敪 <BR>
     * <BR>
     * �Q�j�@@���R�[�h�̗L����ԋp����B<BR>
     * <BR>
     * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
     * @@param �s��R�[�h�B<BR>
     * @@param �t�����g�����V�X�e���敪�B<BR>
     * @@param �����^�C�v�B<BR>
     * @@return java.lang.boolean<BR>
     * @@roseuid 42E46BB2019E
     */
    public boolean isFrontRoute(String l_strInstitutionCode, String l_strMarketCode, String l_frontSysDiv, 
        String l_strProductType) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getFrontSwitchRoute(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strProductType,
                l_strMarketCode,
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION,
                l_frontSysDiv
            };        
        
        try{
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
            
            // �������ʂ�0���̏ꍇ�Afalse���X���[����B
            if(l_lstSwitchRoutes.size() == 0)
            {
                return false;
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            

        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * �t�����g�����s��R�[�h����A�t�����g����������敪�R�[�h���擾����B<BR>
     * <BR>
     * �P�j�@@�t�����g�����s��R�[�h2���̓�1�����擾����B<BR>
     * <BR>
     * �Q�j�@@�擾�����l��ԋp����B<BR>
     * @@param �t�����g�����s��R�[�h - ��ʕ\���Ŏg�p�����s��R�[�h�B<BR>
     * @@return String<BR>
     * @@roseuid 42D6473100A9
     */
    public String getFrontOrderExchangeCode(String l_strConvertMarketCode) 
    {
        
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)";
        log.entering(STR_METHOD_NAME);

        // �ԋp����I�u�W�F�N�g����
        String l_frontExCode = null;
        
        // ������1���ڂ��擾
        l_frontExCode = l_strConvertMarketCode.substring(0,1);

        log.exiting(STR_METHOD_NAME);
        return l_frontExCode;
    }

    /**
     * �t�����g�����s��R�[�h����A�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^:�t�����g�����s��R�[�h2���ڂ��擾����B<BR>
     * <BR>
     * �Q�j�@@�擾�����l��ԋp����B<BR>
     * @@param �t�����g�����s��R�[�h - ��ʕ\���Ŏg�p�����s��R�[�h�B<BR>
     * @@return String<BR>
     * @@roseuid 42D6480F0377
     */
    public String getFrontSystemDiv(String l_strConvertMarketCode) 
    {
        final String STR_METHOD_NAME = "getFrontSystemDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        // �ԋp����I�u�W�F�N�g����
        String l_SysDiv = null;
        
        // ������2���ڂ��擾
        l_SysDiv = l_strConvertMarketCode.substring(1);

        log.exiting(STR_METHOD_NAME);
        return l_SysDiv;
    }

    /**
     * �����̎s��R�[�h�A�t�����g�����V�X�e���敪�R�[�h����A��ʕ\���p��<BR>
     * �s��R�[�h�ɕϊ����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�s��R�[�h��JASDAQ�@@or�@@NNM�̏ꍇ�A�s��R�[�h��"�Q"�i��؁j�ɕϊ�����B<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�ƃt�����g�����V�X�e���敪�R�[�h����������B<BR>
     * <BR>
     * �R�j�@@���������l��ԋp����B<BR>
     * @@param �s��R�[�h - �s��R�[�h.<BR>
     * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
     * @@return String<BR>
     * @@roseuid 42E0A66C02AD
     */
    public String getFrontOrderMarketCode(String l_marketCode, String l_frontSystemCode) 
    {
        final String STR_METHOD_NAME = "getFrontOrderMarketCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        // �ϊ��s��R�[�h
        String l_editmarketCode = null;

        if (l_marketCode.equals(WEB3MarketCodeDef.JASDAQ) || l_marketCode.equals(WEB3MarketCodeDef.NNM))
        {
            l_editmarketCode = WEB3FrontOrderExchangeCodeDef.OSAKA_SECURITIES_EXCHANGE;
        }
        else
        {
            l_editmarketCode = l_marketCode;
        }
        
        StringBuffer l_unitMarketCode = new StringBuffer();
        
        // �s��R�[�h�ƃV�X�e���敪������
        l_unitMarketCode.append(l_editmarketCode).append(l_frontSystemCode);
        
        return l_unitMarketCode.toString();
    }
   
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���ŁA�v���n���R�[�h�������ς̃��R�[�h����������B<BR>
     * <BR>
     * �P�j�@@������������<BR>
     * <BR>
     * �@@�����F�،���ЃR�[�h = ? and <BR>
     * �@@�@@�@@�@@�@@�t�����g����������敪�R�[�h = ? and <BR>
     * �@@�@@�@@�@@�@@�t�����g�����V�X�e���敪 = ? and <BR>
     * �@@�@@�@@�@@�@@�t�����g��������敪�R�[�h = ? and <BR>
     * �@@�@@�@@�@@�@@�ؑ֎w�������敪 = ? and <BR>
     * �@@�@@�@@�@@�@@�ʒm��� = ? and <BR>
     * �@@�@@�@@�@@�@@�����敪 = ?<BR>
     * <BR>
     * �Q�j�@@���������R���e�i���� <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�p�����[�^.���N�G�X�g.�،���ЃR�[�h��ݒ�B <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@get�t�����g����������敪�R�[�h�i�j�����s���A�߂�l��ݒ�B<BR>
     * <BR>
     * �@@ �@@ �@@[����]  <BR>
     * �@@ �@@ �@@�p�����[�^.���N�G�X�g.�ϊ��s��R�[�h  <BR>
     * <BR>
     * �@@�Q�|�R�j�@@get�t�����g�����V�X�e���敪�i�j�����s���A�߂�l��ݒ�B  <BR>
     * <BR>
     * �@@ �@@ �@@[����]  <BR>
     * �@@ �@@ �@@�p�����[�^.���N�G�X�g.�ϊ��s��R�[�h  <BR>
     * <BR>
     * �@@�Q�|�S�j�@@1�F����������ݒ�B <BR>
     * <BR>
     * �@@�Q�|�T�j�@@get�ؑ֎w���v���n�敪�i�j�����s���A�߂�l��ݒ�B <BR>
     * <BR>
     * �@@ �@@ �@@[����] <BR>
     * �@@ �@@ �@@�p�����[�^.���N�G�X�g.�ؑ֋N���敪 <BR>
     * <BR>
     * �@@�Q�|�U�j�@@get�ʒm��ʁi�j�����s���A�߂�l��ݒ�B <BR>
     * <BR>
     * �@@ �@@ �@@[����] <BR>
     * �@@ �@@ �@@�p�����[�^.���N�G�X�g.�ؑ֋N���敪 <BR>
     * <BR>
     * �@@�Q�|�V�j�@@1�F�����ς�ݒ�B <BR>
     * <BR>
     * �R�j�@@doFindAllQuery()�Ńf�[�^���擾�B<BR>
     * <BR>
     * �S�j�@@�������ʂ�ԋp����B<BR>
     * @@param ���N�G�X�g�I�u�W�F�N�g - �Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g�B<BR>
     * @@return List<BR>
     * @@roseuid 430020A20170
     */
    private List getVirtualServerChangeRecord(WEB3AdminFrontRouteChangeCompleteRequest l_priRequest) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getVirtualServerChangeRecord(WEB3AdminFrontRouteChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        // �����σ��R�[�hList
        List l_lstReqRcords = null;

        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and change_req_res_div = ? ");
        l_sbWhere.append(" and notice_type = ? ");
        l_sbWhere.append(" and status = ? ");

        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_priRequest.institutionCode,
                this.getFrontOrderExchangeCode(l_priRequest.convertMarketCode),
                this.getFrontSystemDiv(l_priRequest.convertMarketCode),
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                this.getSwitchPointReqDiv(l_priRequest.changeStartDiv),
                this.getNoticeType(l_priRequest.changeStartDiv),
                WEB3StatusDef.DEALT
            };        
       
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstReqRcords = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lstReqRcords;
    }
   
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���̃f�[�^���X�V����p�����[�^��ݒ肷��B<BR>
     * <BR>
     * �P�j�@@Map�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�X�V���鍀�ڂ�Map�ɐݒ肷��B<BR>
     * <BR>
     * �@@�@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�@@�u�����o�H�ؑ�_��Q���z�T�[�o�ؑ֊Ǘ��e�[�u��.xls�v�� <BR>
     * �@@�@@�V�[�g�u��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���iupdate�j�v���Q�ƁB<BR>
     * <BR>
     * �R�j�@@Map�I�u�W�F�N�g��ԋp����B<BR>
     * @@return Map<BR>
     * @@roseuid 42F1C03A02FD
     */
    private Map setVirtualServerChangeUpdateCalums() 
    {
        // Update����Map
        Map status = new HashMap();
        
        status.put( "status" , WEB3StatusDef.NOT_DEAL);
        status.put( "last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        
        return status;
    }

    /**
     *�敨OP��������L���[�e�[�u������A"�s���t�m�F�O"�A"�s���t�m�F��"�A<BR>
     *"�s���t�m�F��"�̒����������擾���A���������I�u�W�F�N�g�Ɋi�[����B <BR>
     *<BR>
     *�P�j�@@�敨OP�����T�[�r�X.get�t�����g����������敪�R�[�h�i�j����A
     *�@@�@@�@@�t�����g����������敪�R�[�h���擾����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�s��R�[�h�F �p�����[�^.�s��R�[�h <BR>
     *<BR>
     *�Q�j�@@"�s���t�m�F�O"�A"�s���t�m�F��"�A
     *�@@�@@�@@"�s���t�m�F��"�̃f�[�^��Loop�������s���A�擾����B <BR>
     *<BR>
     *for(�v���f�[�^�T�C�Y) <BR>
     *<BR>
     *�@@�Q�|�P�j�@@����������𐶐�����B <BR>
     *<BR>
     *�@@�@@�،���ЃR�[�h = �H and  <BR>
     *�@@�@@�t�����g����������敪�R�[�h = �H and  <BR>
     *�@@�@@�t�����g�����V�X�e���敪 = �H and  <BR>
     *�@@�@@�t�����g��������敪�R�[�h = �H and  <BR>
     *�@@�@@�����o�H�敪 = �H and  <BR>
     *�@@�@@if( "�s���t�m�F�O" ) <BR>
     *<BR>
     *�@@�@@�@@ �����敪 in (? , ?) <BR>
     *<BR>
     *�@@�@@else("�s���t�m�F��" or "�s���t�m�F��") <BR>
     *<BR>
     *�@@�@@�@@ �����敪 = ? <BR>
     *<BR>
     *�@@�Q�|�Q�j�@@���������f�[�^�R���e�i���쐬����B <BR>
     *<BR>
     *�@@�@@�Q�|�Q�|�P�j�@@ArrayList�𐶐�����B <BR>
     *<BR>
     *�@@�@@�Q�|�Q�|�Q�j�@@�p�����[�^.�،���ЃR�[�h��ArrayList�ɒǉ�����B <BR>
     *<BR>
     *�@@�@@�Q�|�Q�|�R�j�@@�P�j�Ŏ擾�����t�����g����������敪�R�[�h��ArrayList�ɒǉ�����B <BR>
     *<BR>
     *�@@�@@�Q�|�Q�|�S�j�@@�p�����[�^.�t�����g�����V�X�e���敪��ArrayList�ɒǉ�����B <BR>
     *<BR>
     *�@@�@@�Q�|�Q�|�T�j�@@"�P�F��������"��ArrayList�ǉ�����B <BR>
     *<BR>
     *�@@�@@�Q�|�Q�|�U�j�@@if( "�s���t�m�F�O" , "�s���t�m�F��" ) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�Q�F�t�����g�������n"��ArrayList�ɒǉ�����B <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@else�i "�s���t�m�F��" �j�@@ <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�R�F�t�����g�������n"��ArrayList�ɒǉ�����B <BR>
     *<BR>
     *�@@�@@�Q�|�Q�|�V�j�@@if( "�s���t�m�F�O" ) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�P�F���M��"��ArrayList�ɒǉ�����B <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�Q�FAMG���͊���"��ArrayList�ɒǉ�����B <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@else if�i "�s���t�m�F��" �j <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�X�F�s���t�m�F��"��ArrayList�ɒǉ�����B <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@else if�i"�s���t�m�F��" �j�@@ <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@"�W�F�s���t�m�F��"��ArrayList�ɒǉ�����B <BR>
     *<BR>
     *�@@�Q�|�R�j�@@DB���� <BR>
     *<BR>
     *�@@�Q�|�S�j�@@�����������I�u�W�F�N�g�Ɍ������i�[����B <BR>
     *<BR>
     *�@@�@@�Q�|�S�|�P�j�@@if�i"�s���t�m�F�O"�j <BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������I�u�W�F�N�g.�m�F�O���� = �f�[�^�擾�T�C�Y <BR>
     *<BR>
     *�@@�@@�Q�|�S�|�Q�j�@@if�i"�s���t�m�F��"�j <BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������I�u�W�F�N�g.�m�F������ = �f�[�^�擾�T�C�Y <BR>
     *<BR>
     *�@@�@@�Q�|�S�|�R�j�@@if�i"�s���t�m�F��"�j <BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������I�u�W�F�N�g.�m�F�ό��� = �f�[�^�擾�T�C�Y�@@<BR>
     *<BR>
     * @@param l_institutionCode - �،���ЃR�[�h<BR>
     * @@param l_marketCode - �s��R�[�h<BR>
     * @@param l_frontSystemCode - �t�����g�����V�X�e���敪<BR>
     * @@param l_processInfoUnit - �����������<BR>
    */
    public void getIfoGrayOrder(String l_institutionCode, 
        String l_marketCode, 
        String l_frontSystemCode, 
        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getIfoGrayOrder(String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // �t�����g����������敪�R�[�h
        String l_frontExchangeCode = null;

        // �s��R�[�h����ɁA�t�����g����������敪�R�[�h���擾
        WEB3IfoFrontOrderService l_ifoFrontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        
        l_frontExchangeCode = l_ifoFrontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // �L���[�e�[�u����t�����敪��List�Ɋi�[
        List l_lstStatusDivs = new ArrayList();
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE);
        
        // List�T�C�Y���Aloop�������s���B
        for(int lstSize = 0; l_lstStatusDivs.size() > lstSize ; lstSize++)
        {
            // ArrayList�I�u�W�F�N�g�̐���
            List l_lstHostRcords = new ArrayList(); 
        
            // ��������������̐���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and front_order_exchange_code = ? ");
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and front_order_trade_code = ? ");
            l_sbWhere.append(" and submit_order_route_div = ? ");            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_sbWhere.append(" and status in (?,?) ");                
            }
            else
            {
                l_sbWhere.append(" and status = ? ");            
            }
            
            // ���������R���e�i�̐���
            List l_lstWheres = new ArrayList();
            
            l_lstWheres.add(l_institutionCode);
            l_lstWheres.add(l_frontExchangeCode);
            l_lstWheres.add(l_frontSystemCode);
            l_lstWheres.add(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)) || 
                    WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION);
            }
            else{
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);            
            }
            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize))){
                // ���M��
                l_lstWheres.add(WEB3FrontOrderStatusDef.SENDED);
                // AMG���͊���
                l_lstWheres.add(WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize))){
                // �s���t�m�F��
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize))){
                // �s���t�m�F��
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMED);
            }

            // �z��ɕϊ�            
            Object[] l_objWhere = l_lstWheres.toArray();
 
            try
            {
                // DB����
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lstHostRcords = l_queryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataException de)
            {
                log.error(STR_METHOD_NAME, de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            
            // �s��m�F�O���R�[�h�̏ꍇ�A�����������I�u�W�F�N�g.�m�F�O�����Ƀ��R�[�h�������i�[
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.beforeNumber = Integer.toString(l_lstHostRcords.size());
            }
            // �s��m�F�����R�[�h�̏ꍇ�A�����������I�u�W�F�N�g.�m�F�������Ƀ��R�[�h�������i�[
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.inNumber = Integer.toString(l_lstHostRcords.size());
            }
            // �s��m�F�σ��R�[�h�̏ꍇ�A�����������I�u�W�F�N�g.�m�F�ό����Ƀ��R�[�h�������i�[
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.afterNumber = Integer.toString(l_lstHostRcords.size());
            }                                        
        }
    }
}
@
