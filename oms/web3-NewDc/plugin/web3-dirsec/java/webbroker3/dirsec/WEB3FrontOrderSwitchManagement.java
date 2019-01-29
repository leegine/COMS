head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderSwitchManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�����o�H�ؑ֏���) (WEB3FrontOrderSwitchManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.115
Revesion History : 2007/02/17  ���؎q (���u) �����̖��No.002-004
Revesion History : 2007/02/28  �g��i (���u) �c�a�X�V�d�lNo.005-007
Revesion History : 2007/02/28  �Ј��� (���u) �d�l�ύX���f��No.061-089 No.096 No.097
Revesion History : 2007/02/28  �Ј��� (���u) �����̖��No.010
Revesion History : 2007/06/26  ���n�m (���u) �����̖��No.012
Revesion History : 2008/12/05  ���� (���u) ���f��No.142 No.144 No.146
Revesion History : 2009/02/25  ���ʗ� (���u) �c�a�X�V�d�lNo.013
Revesion History : 2009/03/02  ���ʗ� (���u) �c�a�X�V�d�lNo.014
Revesion History : 2009/04/03  �����F (���u) ���f��No.152
*/
package webbroker3.dirsec;

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

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3ChangeReqResDivDef;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3NoticeTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.dirsec.define.WEB3AdminFrontDataCodeDef;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.MarketNoticeManagementRow;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeDao;
import webbroker3.gentrade.data.VirtualServerChangeParams;
import webbroker3.gentrade.data.VirtualServerInformationParams;
import webbroker3.gentrade.data.VirtualServerInformationRow;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3LogUtility;

/**
 * �����o�H�ؑ֏����N���X<BR>
 * <BR>
 * WEB3FrontOrderSwitchManagement<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3FrontOrderSwitchManagement {
    
    // �t�����g�����Ǘ����ʃT�[�r�X
    private WEB3AdminDirSecFrontOrderCommonService commonService;
    
    // �،���ЃR�[�h
    private String institutionCode;
    
    // �s��R�[�h
    private String marketCode;
    
    // �t�����g����������敪�R�[�h
    private String frotOrderExCode;
    
    // �t�����g�����V�X�e���敪
    private String frotOrderSystemDiv;
    
    // �����^�C�v
    private String productType;
    
    // �ؑ֏��������敪
    private String changeProcessDiv;
    
    // �f�[�^�R�[�h
    private String dataCode;
    
    //���[�U�f�[�^
    private String userData;
    
    // �����o�H�敪
    private String submitOrderDiv;
    
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FrontOrderSwitchManagement.class);

    
    /**
     * WEB3FrontOrderSwitchManagement�N���X�̃I�u�W�F�N�g�𐶐�����B  <BR>
     * <BR>
     * �P�j�e�����̐ݒ�������Ȃ��B  <BR>
     * <BR>
     *  �@@�P�|�P�jthis.�t�����g�����Ǘ����ʃT�[�r�X = �p�����[�^.�t�����g�����Ǘ����ʃT�[�r�X <BR>
     * <BR>
     *  �@@�P�|�Q�jthis.�،���ЃR�[�h = �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�،���ЃR�[�h <BR>
     * <BR>
     *  �@@�P�|�R�jthis.�s��R�[�h = �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�s��R�[�h <BR>
     * <BR>
     *  �@@�P�|�S�jthis.�t�����g����������敪�R�[�h = �p�����[�^.�t�����g�����Ǘ����ʃT�[�r�X.get�t�����g����������敪�R�[�h�i�j <BR>
     * <BR>
     *  �@@�@@�@@[����] <BR>
     *  �@@�@@�@@�t�����g�����s��R�[�h�F �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�ϊ��s��R�[�h <BR>
     * <BR>
     *  �@@�P�|�T�jthis.�t�����g�����V�X�e���敪 = �p�����[�^.�t�����g�����Ǘ����ʃT�[�r�X.get�t�����g�����V�X�e���敪�i�j <BR>
     * <BR>
     *  �@@�@@�@@[����] <BR>
     *  �@@�@@�@@�t�����g�����s��R�[�h�F �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�ϊ��s��R�[�h <BR>
     * <BR>
     *  �@@�P�|�U�jthis.�����^�C�v = �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�����^�C�v <BR>
     * <BR>
     *  �@@�P�|�V�jthis.�ؑ֏��������敪 = �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�ؑ֏��������敪   <BR>
     * <BR>
     *  �@@�P�|�W�jthis.�f�[�^�R�[�h = �p�����[�^.�t�����g�����Ǘ����ʃT�[�r�X.get�f�[�^�R�[�h�i�j <BR>
     * <BR>
     *  �@@�@@�@@[����] <BR>
     *  �@@�@@�@@�ؑ֋N���敪�F �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�ؑ֋N���敪   <BR>
     *  �@@�@@�@@�ؑ֏��������敪�F this.�ؑ֏��������敪  <BR>
     * <BR>
     *  �@@�P�|�X�jthis.���[�U�f�[�^ = �p�����[�^.�t�����g�����Ǘ����ʃT�[�r�X.get���[�U�f�[�^�i�j <BR>
     * <BR>
     *  �@@�@@�@@[����] <BR>
     *  �@@�@@�@@�ϊ��s��R�[�h�F �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�ϊ��s��R�[�h <BR>
     *  �@@�@@�@@�����^�C�v�F this.�����^�C�v    <BR>
     *  �@@�@@�@@�ؑ֋N���敪�F �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�ؑ֋N���敪   <BR>
     * <BR>
     *  �@@�P�|�P�O�jthis.�����o�H�敪 = �p�����[�^.�Ǘ��ҁE�����o�H�ؑ֊������N�G�X�g.�����o�H�敪  <BR>
     * <BR>
     * @@param �t�����g�����Ǘ����ʃN���X�I�u�W�F�N�g - �t�����g�����Ǘ����ʃN���X�I�u�W�F�N�g�B<BR>
     * @@param �����o�H�ؑ֊����I�u�W�F�N�g - �����o�H�ؑ֊����I�u�W�F�N�g�B<BR>
     * @@roseuid 42F2B62A0259
     */
    public WEB3FrontOrderSwitchManagement(WEB3AdminDirSecFrontOrderCommonService l_commonService, 
                                                        WEB3AdminFrontRouteChangeCompleteRequest l_request)
    {
        // �t�����g�����Ǘ����ʃN���X�I�u�W�F�N�g
        this.commonService = l_commonService;
        // �،���ЃR�[�h
        this.institutionCode = l_request.institutionCode;
        // �s��R�[�h
        this.marketCode = l_request.marketCode;
        // �t�����g����������敪�R�[�h
        this.frotOrderExCode = l_commonService.getFrontOrderExchangeCode(l_request.convertMarketCode);
        // �t�����g�����V�X�e���敪
        this.frotOrderSystemDiv = l_commonService.getFrontSystemDiv(l_request.convertMarketCode);
        // �����^�C�v
        this.productType = l_request.productType;
        // �ؑ֏��������敪
        this.changeProcessDiv = l_request.changeProcessDiv;
        // �f�[�^�R�[�h
        this.dataCode = l_commonService.getDataCode(l_request.changeStartDiv,changeProcessDiv);
        // ���[�U�f�[�^
        this.userData = l_commonService.getUserData(l_request.convertMarketCode, l_request.productType, changeProcessDiv);
        // �����o�H�敪
        this.submitOrderDiv = l_request.submitOrderRouteDiv;
    }

    /**
     *�����o�H�ؑւ̐V�K���s�������s���B<BR>
     *<BR>
     *�P�j�@@update�����o�H�����i�j�Ŕ�����ؑփe�[�u�����X�V����B<BR>
     *<BR>
     *�Q�j�@@get���z�T�[�o���i�j�����s���A���z�T�[�o�����擾����B<BR>
     *<BR>
     *�R�j�@@�Q�j�Ŏ擾�������R�[�h��0���̏ꍇ<BR>
     *<BR>
     *�@@�R�|�P�j�@@return����B<BR>
     *<BR>
     *�S�j�@@�Q�j�Ŏ擾�������R�[�h���A���[�v�������s���B<BR>
     *<BR>
     *�@@�S�|�P�j�@@execute��Q���z�T�[�o���R�[�h�i�j�����s���A��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h��o�^����B<BR>
     *<BR>
     *�@@�@@�@@[����]<BR>
     *�@@�@@�@@���z�T�[�o���Row�F ���z�T�[�o���Row<BR>
     *<BR>
     *�T�j�@@if�@@�ؑ֏��������敪 == "�O�F�ʔԏƉ������"�̏ꍇ<BR>
     *<BR>
     *�@@�T�|�P�j�@@MAXAS�g���K�[�𔭍s�i�ʔԏƉ�w�������j�B<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@new WEB3MQMessageSpec(this.�،���ЃR�[�h , "AX0X1", this.���[�U�f�[�^)<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@WEB3MQGatewayService.send(���b�Z�[�W���e�FWEB3MQMessageSpec)<BR>
     *<BR>
     *�@@�T�|�Q�j�@@return����B<BR>
     *<BR>
     *�U�j�@@else �ؑ֏��������敪 != "�O�F�ʔԏƉ������"�̏ꍇ�A<BR>
     *<BR>
     *�@@�U�|�P�j�@@this.�����^�C�v = 1�F�����̏ꍇ<BR>
     *<BR>
     *�@@�@@�U�|�P�|�P�j�@@get�s���t�s�������i�j�����s���A�s���t�s���������擾����B<BR>
     *<BR>
     *�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�Ȃ�<BR>
     *<BR>
     *�@@�@@�U�|�P�|�Q�j�@@�U�|�P�|�P�j�Ŏ擾�������R�[�h��0���łȂ��ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�U�|�P�|�P�j�Ŏ擾�������R�[�h���A���[�v�������s���B<BR>
     *<BR>
     *�@@�@@�@@�U�|�P�|�Q�|�P�j�@@validate�L���[�e�[�u���d���G���[�i�j�����s���A�G���[�i�߂�l��true�j�̏ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     *<BR>
     *�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�Г��������ځF ������������L���[Ro���i�s���t�s�������j.�Г���������<BR>
     *<BR>
     *�@@�@@�@@�U�|�P�|�Q�|�Q�j�@@�G���[�łȂ��ꍇ<BR>
     *<BR>
     *�@@�@@�@@�@@�U�|�P�|�Q�|�Q�|�P�j�@@create�s���t�s�����������i�j�����s���A�s���t�s�������̒����������쐬����B<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�@@�@@������������L���[Ro���i�s���t�s�������j�F ������������L���[Ro���i�s���t�s�������j<BR>
     *<BR>
     *�@@�@@�@@�@@�U�|�P�|�Q�|�Q�|�Q�j�@@update�s�����������敪�i�j�����s���A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�s���t�s�������̏����敪��9�F�s���t�m�F���ɍX�V����B<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�@@�@@������������L���[Ro���i�s���t�s�������j�F ������������L���[Ro���i�s���t�s�������j<BR>
     *<BR>
     *�@@�U�|�Q�j�@@this.�����^�C�v = 6�F�敨�I�v�V�����̏ꍇ<BR>
     *<BR>
     *�@@�@@�U�|�Q�|�P�j�@@get�敨OP�s���t�s�������i�j�����s���A�s���t�s���������擾����B<BR>
     *<BR>
     *�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�Ȃ�<BR>
     *<BR>
     *�@@�@@�U�|�Q�|�Q�j�@@�U�|�Q�|�P�j�Ŏ擾�������R�[�h��0���łȂ��ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�U�|�Q�|�P�j�Ŏ擾�������R�[�h���A���[�v�������s���B<BR>
     *<BR>
     *�@@�@@�@@�U�|�Q�|�Q�|�P�j�@@validate�敨OP�L���[�e�[�u���d���G���[�i�j�����s���A�G���[�i�߂�l��true�j�̏ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     *<BR>
     *�@@�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�@@�Г��������ځF �敨OP��������L���[Ro���i�s���t�s�������j.�Г���������<BR>
     *<BR>
     *�@@�@@�@@�U�|�Q�|�Q�|�Q�j�@@�G���[�łȂ��ꍇ<BR>
     *<BR>
     *�@@�@@�@@�@@�U�|�Q�|�Q�|�Q�|�P�j�@@create�敨OP�s���t�s�����������i�j�����s���A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�s���t�s�������̒����������쐬����B<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�@@�@@�敨OP��������L���[Ro���i�s���t�s�������j�F �敨OP��������L���[Ro���i�s���t�s�������j<BR>
     *<BR>
     *�@@�@@�@@�@@�U�|�Q�|�Q�|�Q�|�Q�j�@@update�敨OP�s�����������敪�i�j�����s���A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�s���t�s�������̏����敪��9�F�s���t�m�F���ɍX�V����B<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�@@�@@�敨OP��������L���[Ro���i�s���t�s�������j�F �敨OP��������L���[Ro���i�s���t�s�������j<BR>
     *<BR>
     *�@@�U�|�R�j�@@MAXAS�g���K�[�𔭍s�i�ʒm��s�w�������j�B<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@new WEB3MQMessageSpec(this.�،���ЃR�[�h , "AX9X1", this.���[�U�f�[�^)<BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@WEB3MQGatewayService.send(���b�Z�[�W���e�FWEB3MQMessageSpec)<BR>
     * @@roseuid 42F2C0B40084
     */
    public void executeOrderRouteSwitching() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "executeOrderRouteSwitching()";
        log.entering(STR_METHOD_NAME); 
        // ���z�T�[�o���List
        List l_virtualServerLists = new ArrayList();
        // �g���K�[���s�T�[�r�X�C���X�^���X
        WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
        
        // �����o�H�𖳌�
        this.updateOrderRouteInvalidity();
    
        // ���z�T�[�o�����擾
        l_virtualServerLists = this.getVirtualServerInfo();
        
        if(l_virtualServerLists.size() == 0)
        {
            return;
        }
        
        Iterator l_virtualServerObj = l_virtualServerLists.iterator();
        
        while(l_virtualServerObj.hasNext())
        {
            // VirtualServerInformationRow�I�u�W�F�N�g�̒��o
            VirtualServerInformationRow l_virServerInfoRow = (VirtualServerInformationParams)l_virtualServerObj.next();

            // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h��o�^����
            this.executeVirtualServChange(l_virServerInfoRow);
        }
        
        // �ؑ֏��������敪���ʔԏƉ�������̏ꍇ�AMAXAS�g���K�[�𔭍s
        if(this.changeProcessDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE))
        {
            // �g���K�[���s�������s���B
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(this.institutionCode, WEB3AdminFrontDataCodeDef.NUBER_REF_REQ_CODE,
                                                                 this.userData);

            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);
        
            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> �ʔԏƉ�����M �����I�I�I");
            }
            else
            {
                log.debug("==> �ʔԏƉ�����M ���s �I�I�I");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            return;
        }
        // �ؑ֏����������S�������������̏ꍇ
        else
        {
            if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(this.productType))
            {
                //�s���t�s�������̎擾
                List l_grayOrders = this.getMarketAcceptGrayOrder();
                
                if(l_grayOrders.size() != 0)
                {
                    Iterator l_grayObj = l_grayOrders.iterator();
                    
                    while(l_grayObj.hasNext())
                    {
                        // HostEqtypeOrderAllRow�I�u�W�F�N�g�̒��o
                        HostEqtypeOrderAllRow l_hostRow = (HostEqtypeOrderAllRow)l_grayObj.next();
                        
                        // �d���G���[�`�F�b�N
                        boolean l_isHostRepeat = this.validateHostEqtypeRepeat(l_hostRow.getCorpCode());
                        // ���R�[�h�����݂���ꍇ�A�d���G���[���X���[����
                        if(l_isHostRepeat)
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }
                        else
                        {
                            // ���������쐬
                            this.createMarketAcceptModifyOrder(l_hostRow);
                            
                            // �O���[�����̏����敪��"�s���t�m�F��"�ɍX�V����
                            this.updateGrayOrderStatus(l_hostRow);
                        }
                    }
                  }
            }
            else if(Integer.toString(ProductTypeEnum.IFO.intValue()).equals(this.productType))
            {

                //�s���t�s�������̎擾
                List l_grayOrders = this.getIfoMarketAcceptGrayOrder();
                
                if(l_grayOrders.size() != 0)
                {
                    Iterator l_grayObj = l_grayOrders.iterator();
                    
                    while(l_grayObj.hasNext())
                    {
                        // HostFotypeOrderAllRow�I�u�W�F�N�g�̒��o
                        HostFotypeOrderAllRow l_hostRow = (HostFotypeOrderAllRow)l_grayObj.next();
                        
                        // �d���G���[�`�F�b�N
                        boolean l_isHostRepeat = this.validateIfoHostFotypeRepeat(l_hostRow.getCorpCode());
                        // ���R�[�h�����݂���ꍇ�A�d���G���[���X���[����
                        if(l_isHostRepeat)
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }
                        else
                        {
                            // ���������쐬
                            this.createIfoMarketAcceptModifyOrder(l_hostRow);
                            
                            // �O���[�����̏����敪��"�s���t�m�F��"�ɍX�V����
                            this.updateIfoGrayOrderStatus(l_hostRow);
                        }
                    }
                  }
            }
            
            //MAXAS�g���K�[(�ʒm��s�����v��)�𔭍s
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(this.institutionCode, WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REQ_CODE, this.userData);
        
            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);

            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> �ʒm��s�����v���������M �����I�I�I");
            }
            else
            {
                log.debug("==> �ʒm��s�����v���������M ���s �I�I�I");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            } 
        }
    
        log.exiting(STR_METHOD_NAME);        
    }
   
    /**
     * ������ؑփe�[�u���̗L���t���O�𖳌��ɐ؂�ւ���B<BR>
     * <BR>
     * �P�j ���������I�u�W�F�N�g����<BR>
     * <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�،���ЃR�[�h = ?  and <BR>
     * �@@�@@�s��R�[�h = ? and <BR>
     * �@@�@@�t�����g�����V�X�e���敪 = ? and <BR>
     * �@@�@@�����^�C�v = ?<BR>
     * �@@�@@�����o�H�敪 = ? <BR>
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i����<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�،���ЃR�[�h��ݒ�B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�s��R�[�h��ݒ�B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�t�����g�����V�X�e���敪��ݒ�B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@this.�����^�C�v��ݒ�B<BR>
     * <BR>
     * �@@�Q�|�T�j�@@this.�����o�H�敪��ݒ�B<BR>
     * <BR>
     * �R�j�@@doUpdateAllQuery�i�j�ŁA�L���t���O"�O�FOFF"�ɍX�V����B<BR>
     * <BR>
     * �@@�@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�@@�u�����o�H�ؑ�_������ؑփe�[�u��.xls�v���Q�ƁB<BR>
     * @@roseuid 42F2CEFC006D
     */
    protected void updateOrderRouteInvalidity()throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "updateOrderRouteInvalidity()";
        log.entering(STR_METHOD_NAME);
        
        // ������ؑփe�[�u���I�u�W�F�N�g����
//      OrderSwitchingParams l_switchParamas = new OrderSwitchingParams();
        // �X�V�p�����[�^Map
        Map updateMap = new HashMap(); 
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and market_code = ? ");        
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                this.institutionCode,
                this.marketCode,
                this.frotOrderSystemDiv,
                this.productType,
                this.submitOrderDiv
            };        

        // �X�V���ڂ𐶐�
        updateMap.put("valid_flag",WEB3ValidFlag.OFF);
        updateMap.put("last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateAllQuery(OrderSwitchingRow.TYPE,l_sbWhere.toString(), l_objWhere, updateMap);
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
    }
   
    /**
     * ���z�T�[�o���e�[�u������A���z�T�[�o�����擾����B<BR>
     * <BR>
     * �P�j ���������I�u�W�F�N�g����<BR>
     * <BR>
     *  �@@�@@[����] <BR>
     *  �@@�@@�،���ЃR�[�h = ?  and <BR>
     *  �@@�@@�t�����g����������敪�R�[�h = ? and <BR>
     *  �@@�@@�t�����g�����V�X�e���敪 = ? and <BR>
     *  �@@�@@�t�����g��������敪�R�[�h = ?<BR>
     *  �@@�@@�����^�C�v = ? <BR>
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i����<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�،���ЃR�[�h��ݒ�B <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�t�����g����������敪�R�[�h��ݒ�B <BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�t�����g�����V�X�e���敪��ݒ�B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@"�P�F��������"��ݒ�B <BR>
     * <BR>
     * �@@�Q�|�T�j�@@this.�����^�C�v��ݒ�B<BR>
     * <BR>
     * �R�j�@@doFindAllQuery()�ŁA���R�[�h���擾<BR>
     * <BR>
     * �S�j�@@�������ʂ�ԋp����B<BR>
     * @@return List<BR>
     * @@roseuid 42F2F1D3008C
     */
    protected List getVirtualServerInfo() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getVirtualServerInfo()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstVirtualServers = new ArrayList();
        
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
                this.institutionCode,
                this.frotOrderExCode,
                this.frotOrderSystemDiv,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                this.productType,
            };        
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstVirtualServers = l_queryProcessor.doFindAllQuery(VirtualServerInformationRow.TYPE,
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
        
     return l_lstVirtualServers;
    }
   
    /**
     * ��Q���z�T�[�o���R�[�h���A�e�[�u���ɑ��݂��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@VirtualServerChangeDao.findDaoByPk()�ŁA�����������s���B<BR>
     * <BR>
     * �Q�j�@@if ���R�[�h�����݂���ꍇ�A<BR>
     * <BR>
     *        true��ԋp����B<BR>
     *      else<BR>
     * <BR>
     *        false��ԋp����B<BR>
     * @@param ���z�T�[�oNo.�i�s��j - ���z�T�[�oNo.�i�s��j�B<BR>
     * @@param �ؑ֎w�������敪 - �ؑ֎w�������敪�B<BR>
     * @@param �ʒm��� - �ʒm��ʁB<BR>
     * @@param �t�����g����������敪�R�[�h - �t�����g����������敪�R�[�h�B<BR>
     * @@return boolean<BR>
     * @@roseuid 42F302C001E4
     */
    private boolean validateVirtualServerRecord(String l_virServerNo, String l_changeReqResDiv, 
                                                        String l_noticeType, String l_frontOrderExCode)throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateVirtualServerRecord()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            VirtualServerChangeDao l_dao = VirtualServerChangeDao.findDaoByPk(l_virServerNo, l_changeReqResDiv, l_noticeType, l_frontOrderExCode);
        }
        catch(DataFindException l_finEx)
        {
            return false;
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
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀf�[�^��Insert����B<BR>
     * <BR>
     * [�p�����[�^]<BR>
     * �ؑ֎w�������敪<BR>
     * �ʒm���<BR>
     * ���z�T�[�o���e�[�u��Row<BR>
     * <BR>
     * �P�j�@@VirtualServerChangeParams�N���X�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@if�@@�ؑ֎w�������敪 = "�ʒm��s�v��" ����<BR>
     * �@@�@@�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h = "����" or "����"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�Q�|�P�j�@@get�ŏI�ʒmNo�i�j���������s���A��t�n�ʒm�ʔԂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"��t�n"<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�@@get�ŏI�ʒmNo�i�j���������s���A���n�ʒm�ʔԂ��擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"���n"<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     * �R�j�@@if�@@�p�����[�^.�ؑ֎w�������敪 = "�ʒm�đ��v��"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�R�|�P�j�@@get�ŏI�ʒmNo�i�j���������s���A�ŏI�ʒmNo���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@�ʒm���<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     * �S�j�@@set�ؑ֊Ǘ��e�[�u�����R�[�h�i�j���������s���A��Q���z�T�[�o�ؑ֊Ǘ��e�[�u��Params���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���z�T�[�o���e�[�u��Row�F�@@���z�T�[�o���e�[�u��Row<BR>
     * �@@�@@�ؑ֎w�������敪�F�@@�ؑ֎w�������敪<BR>
     * �@@�@@�ʒm��ʁF�@@�ʒm���<BR>
     * �@@�@@�ŏI�ʒmNo�F�@@�ŏI�ʒmNo<BR>
     * �@@�@@��s�v������t�n�ŏI�ʒmNo�F�@@��t�n�ʒm�ʔ�<BR>
     * �@@�@@��s�v�������n�ŏI�ʒmNo�F�@@���n�ʒm�ʔ�<BR>
     * <BR>
     * �T�j�@@doInsertQuery�i�j���������s����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@VirtualServerChangeParams�F�@@��Q���z�T�[�o�ؑ֊Ǘ��e�[�u��Params<BR>
     * <BR>
     * �@@���G���[�̏ꍇ�A��O���X���[����B<BR>
     * @@param �ؑ֎w�������敪 - �ؑ֎w�������敪�B<BR>
     * @@param �ʒm���<BR>
     * @@param ���z�T�[�o���e�[�u��Row - ���z�T�[�o���e�[�u��Row�B<BR>
     * @@roseuid 42F3204A02DE
     */
    protected void insertVirtualServerChange(String l_changeReqResDiv, String l_noticeType, 
                                                    VirtualServerInformationRow l_virtualInfoRow) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "insertVirtualServerChange(String, String, VirtualServerInformationRow)";
        log.entering(STR_METHOD_NAME);

        // VirtualServerChangeParams�N���X�̃C���X�^���X�𐶐�
        VirtualServerChangeParams l_changeParams = new VirtualServerChangeParams();
        // �ŏI�ʒmNo
        String l_finalNoticeNo = null;
        //��t�n�ʒm�ʔ�
        String l_strAcceptNoticeNo = null;
        //���n�ʒm�ʔ�
        String l_strExecutedNoticeNo = null;
        //���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h
        String l_strFrontOrderExchangeCode = l_virtualInfoRow.getFrontOrderExchangeCode();

        try
        {
            //if�@@�ؑ֎w�������敪 = "�ʒm��s�v��" ����
            //���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h = "����" or "����"�̏ꍇ�A
            if (WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST.equals(l_changeReqResDiv)
                && (WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
                    || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)))
            {
                //get�ŏI�ʒmNo�i�j���������s���A��t�n�ʒm�ʔԂ��擾����B
                l_strAcceptNoticeNo = this.getFinalNoticeNo(
                    l_virtualInfoRow.getVirtualServerNumberMarket(),
                    WEB3NoticeTypeDef.ACCEPT_TYPE,
                    l_strFrontOrderExchangeCode);

                //get�ŏI�ʒmNo�i�j���������s���A���n�ʒm�ʔԂ��擾����B
                l_strExecutedNoticeNo = this.getFinalNoticeNo(
                     l_virtualInfoRow.getVirtualServerNumberMarket(),
                     WEB3NoticeTypeDef.EXECUTED_TYPE,
                     l_strFrontOrderExchangeCode);
            }

            // �ؑ֎w�������敪���ʒm�đ��v���̏ꍇ�A�ŏI�ʒmNo���擾
            if(l_changeReqResDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST))
            {
                l_finalNoticeNo = this.getFinalNoticeNo(l_virtualInfoRow.getVirtualServerNumberMarket(), l_noticeType, 
                                                                                l_virtualInfoRow.getFrontOrderExchangeCode());
            }
            // �C���T�[�g���ڂ��Z�b�g����
            l_changeParams = this.setVirtualServerChangeRecord(
                l_virtualInfoRow,
                l_changeReqResDiv,
                l_noticeType,
                l_finalNoticeNo,
                l_strAcceptNoticeNo,
                l_strExecutedNoticeNo);

            // Insert����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_changeParams);
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
    }
   
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u����Insert���鍀�ڂ��Z�b�g����B<BR>
     * <BR>
     * [�p�����[�^]<BR>
     * ���z�T�[�o���e�[�u��Row<BR>
     * �ؑ֎w�������敪<BR>
     * �ʒm���<BR>
     * �ŏI�ʒmNo<BR>
     * ��s�v������t�n�ŏI�ʒmNo<BR>
     * ��s�v�������n�ŏI�ʒmNo<BR>
     * <BR>
     * �@@�P�j�@@VirtualServerChangeParams�N���X�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�j�@@Insert���鍀�ڂ��Z�b�g����B<BR>
     * �@@�@@ �@@�@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�@@ �@@�@@�u�����o�H�ؑ�_��Q���z�T�[�o�ؑ֊Ǘ��e�[�u��.xls�v�� <BR>
     * �@@�@@ �@@�@@ �V�[�g�u�i�ʔԏƉ���j��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���v�� <BR>
     *  �@@�@@ �@@�@@�V�[�g�u�i�S���������j��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���v���Q�ƁB <BR>
     * <BR>
     *  �@@�@@ �@@�@@���ؑ֎w�������敪���ʒm�đ��v���̏ꍇ�̂݁A�ŏI�ʒmNo != null�ƂȂ�B<BR>
     * <BR>
     * �R�j�@@VirtualServerChangeParams�I�u�W�F�N�g��ԋp����B<BR>
     * @@param ���z�T�[�o���e�[�u��Row - ���z�T�[�o���e�[�u��Row�I�u�W�F�N�g�B<BR>
     * @@param �ؑ֎w�������敪 - �ؑ֎w�������敪�B<BR>
     * @@param �ʒm��� - �ʒm��ʁB<BR>
     * @@param �ŏI�ʒmNo - �ŏI�ʒmNo�B<BR>
     * @@param ��s�v������t�n�ŏI�ʒmNo - ��s�v������t�n�ŏI�ʒmNo�B<BR>
     * @@param ��s�v�������n�ŏI�ʒmNo - ��s�v�������n�ŏI�ʒmNo�B<BR>
     * @@return VirtualServerChangeParams<BR>
     * @@roseuid 42F3300A03C8
     */
    private VirtualServerChangeParams setVirtualServerChangeRecord(
        VirtualServerInformationRow l_priVirtualInfoRow,
        String l_priReqResDiv,
        String l_priNoticeDiv,
        String l_priFinalNoticeNo,
        String l_strAcceptNoticeNo,
        String l_strExecutedNoticeNo)
    {
        // VirtualServerChangeParams�C���X�^���X�̐���
        VirtualServerChangeParams l_priVirtualServPrams = new VirtualServerChangeParams();
        
        // ���z�T�[�oNo.�i�s��j���Z�b�g
        l_priVirtualServPrams.virtual_server_number_market = l_priVirtualInfoRow.getVirtualServerNumberMarket();
        // �ؑ֎w�������敪
        l_priVirtualServPrams.change_req_res_div = l_priReqResDiv;
        // �ʒm���
        l_priVirtualServPrams.notice_type = l_priNoticeDiv;
        // �،���ЃR�[�h
        l_priVirtualServPrams.institution_code = l_priVirtualInfoRow.getInstitutionCode();
        // �t�����g����������敪�R�[�h
        l_priVirtualServPrams.front_order_exchange_code = l_priVirtualInfoRow.getFrontOrderExchangeCode();
        // �t�����g�����V�X�e���敪
        l_priVirtualServPrams.front_order_system_code = l_priVirtualInfoRow.getFrontOrderSystemCode();
        // �t�����g��������敪�R�[�h
        l_priVirtualServPrams.front_order_trade_code = WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE;
        // �N���C�A���gNo
        l_priVirtualServPrams.client_number = l_priVirtualInfoRow.getClientNumber();
        // �ʒm�t�@@�C��No
        l_priVirtualServPrams.notice_file_number = l_priVirtualInfoRow.getNoticeFileNumber();
        // �đ��v���ʒm�ԍ�from
        if(l_priFinalNoticeNo != null)
        {
            long l_longNoticeNo = 0;
            
            l_longNoticeNo = Long.parseLong(l_priFinalNoticeNo);
 
            l_priVirtualServPrams.resend_notice_number_from = new Long(l_longNoticeNo);    
        }
        //��s����t�n�ʒm�ʔ�
        if (l_strAcceptNoticeNo != null)
        {
            long l_lngAcceptNoticeNo = 0;

            l_lngAcceptNoticeNo = Long.parseLong(l_strAcceptNoticeNo);

            l_priVirtualServPrams.agency_accept_notice_number = new Long(l_lngAcceptNoticeNo);
        }
        //��s�����n�ʒm�ʔ�
        if (l_strExecutedNoticeNo != null)
        {
            long l_lngExecutedNoticeNo = 0;

            l_lngExecutedNoticeNo = Long.parseLong(l_strExecutedNoticeNo);

            l_priVirtualServPrams.agency_exec_notice_number = new Long(l_lngExecutedNoticeNo);
        }

        // �����敪
        l_priVirtualServPrams.status = WEB3StatusDef.NOT_DEAL;
        // �����^�C�v
        l_priVirtualServPrams.product_type = l_priVirtualInfoRow.getProductType();
        
        return l_priVirtualServPrams;
    }
   
    /**
     * �s��ʒm�Ǘ��e�[�u���ŁA�ʒm��ʂ�"01�F��t�n" or <BR>
     * "02�F���n"�̃��R�[�h�̍ŏI�ʒm�ԍ����擾����B<BR>
     * <BR>
     * �P�j�@@��������������𐶐�����B<BR>
     * <BR>
     *  �@@�@@[����] <BR>
     *  �@@�@@���z�T�[�oNo.�i�s��j = ? and <BR>
     *  �@@�@@�ʒm��� = ? and <BR>
     *  �@@�@@�t�����g����������敪�R�[�h = ? and<BR>
     *  �@@�@@�o�߉c�Ɠ��� = ? <BR>
     * <BR>
     * �Q�j�@@�\�[�g����������𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�\�[�g�����F�@@�ʒm�ԍ�.desc<BR>
     * <BR>
     * �R�j�@@��������������R���e�i�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�R�|�P�j�@@�p�����[�^.���z�T�[�oNo.�i�s��j��ݒ�B <BR>
     * <BR>
     * �@@�@@�@@�R�|�Q�j�@@�p�����[�^.�ʒm��ʂ�ݒ�B <BR>
     * <BR>
     * �@@�@@�@@�R�|�R�j�@@�p�����[�^.�t�����g����������敪�R�[�h��ݒ�B <BR>
     * <BR>
     * �@@�@@�@@�R�|�S�j�@@0��ݒ�B  <BR>
     * <BR>
     * �S�j�@@doFindAllQuery()�ŁA�f�[�^���擾����B<BR>
     * <BR>
     * �T�j�@@�ŏI�ʒm�ԍ���ԋp����B<BR>
     * <BR>
     * �@@�T�|�P�j�@@�������ʂ�0���̏ꍇ <BR>
     * <BR>
     * �@@�@@�T�|�P�|�P�j�@@�p�����[�^.�t�����g����������敪�R�[�h = "����" or "����"�̏ꍇ�A <BR>
     * �@@�@@�@@  �@@�@@�@@�@@�@@�@@�ŏI�ʒm�ԍ� = 0��ԋp����B <BR>
     * �@@�@@�T�|�P�|�Q�j�@@��L�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ŏI�ʒm�ԍ� = 1��ԋp����B<BR>
     * <BR>
     * �@@�T�|�Q�j�@@�f�[�^�����݂���ꍇ�A�ŏI�ʒm�ԍ���Row�I�u�W�F�N�g����擾���ԋp����B<BR>
     * @@param ���z�T�[�oNo.�i�s��j - ���z�T�[�oNo.�i�s��j�B<BR>
     * @@param �ʒm��� - �ʒm��ʁB<BR>
     * @@param �t�����g����������敪�R�[�h - �t�����g����������敪�R�[�h�B<BR>
     * @@return String<BR>
     * @@roseuid 42F340C20398
     */
    private String getFinalNoticeNo(String l_priServNoMarket, String l_priNoticeType, String l_priFrontExCode) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getFinalNoticeNo(String, String, String)";
        log.entering(STR_METHOD_NAME);
        // �ŏI�ʒmNo
        String l_finalNoticeNo = null;
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_lstMarketNotices = new ArrayList();
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_market = ? ");
        l_sbWhere.append(" and notice_type = ? ");        
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and biz_date_count = ? ");

        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_priServNoMarket,
                l_priNoticeType,
                l_priFrontExCode,
                Integer.toString(0)
            };
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // �ʒm�ԍ��擾�ׂ̈ɁA���R�[�h��1���擾����B
            l_lstMarketNotices = l_queryProcessor.doFindAllQuery(MarketNoticeManagementRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    "notice_number desc",
                                                                    null,
                                                                    l_objWhere,
                                                                    1,
                                                                    0);
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
        // �������ʂ�0���̏ꍇ
        if(l_lstMarketNotices.size() == 0)
        {
            //�p�����[�^.�t�����g����������敪�R�[�h = "����" or "����"�̏ꍇ�A
            //�ŏI�ʒm�ԍ� = 0��ԋp����
            if (WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_priFrontExCode)
                || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_priFrontExCode))
            {
                l_finalNoticeNo = "0";
            }
            else
            {
                //��L�ȊO�̏ꍇ�A�ŏI�ʒm�ԍ� = 1��ԋp����B
                l_finalNoticeNo = "1";
            }
        }
        // �ŏI�ʒmNo���擾
        else
        {
            MarketNoticeManagementRow row = (MarketNoticeManagementRow)l_lstMarketNotices.get(0); 
            l_finalNoticeNo = Long.toString(row.getNoticeNumber());
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_finalNoticeNo;
    }
   
    /**
     * ������������L���[�e�[�u������A�t�����g�������n�Ŏs���t�s���������擾����B <BR>
     * <BR>
     * �P�j�@@��������������𐶐�����B    <BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@�،���ЃR�[�h = ? and    <BR>
     * �@@�t�����g����������敪�R�[�h = ? and <BR>
     * �@@�t�����g�����V�X�e���敪 = ? and   <BR>
     * �@@�t�����g��������敪�R�[�h = ? and  <BR>
     * �@@�����o�H�敪 = ? and <BR>
     * �@@�����敪 in (?,?) <BR>
     * <BR>
     * �Q�j�@@��������������R���e�i�𐶐�����B    <BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.�،���ЃR�[�h��ݒ�B  <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�t�����g����������敪�R�[�h��ݒ�B   <BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�t�����g�����V�X�e���敪��ݒ�B <BR>
     * <BR>
     * �@@�Q�|�S�j�@@1�F����������ݒ�B    <BR>
     * <BR>
     * �@@�Q�|�T�j�@@2�F�t�����g�������n��ݒ�B    <BR>
     * <BR>
     * �@@�Q�|�U�j�@@1�F���M�ς�ݒ�B <BR>
     * <BR>
     * �@@�Q�|�V�j�@@2�FAMG���͊�����ݒ�B <BR>
     * <BR>
     * �R�j�@@doFindAllQuery�i�j�ŁA�s���t�s���������擾�B   <BR>
     * <BR>
     * �S�j�@@�擾���ʂ�ԋp����B   <BR>
     * <BR>
     * @@return List<BR>
     * @@roseuid 42F6A97E029F
     */
    private List getMarketAcceptGrayOrder() throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getMarketAcceptGrayOrder()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_grayOrders = new ArrayList();
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");        
        l_sbWhere.append(" and status in (?,?) ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                this.institutionCode,
                this.frotOrderExCode,
                this.frotOrderSystemDiv,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION,
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_grayOrders = l_queryProcessor.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
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

        return l_grayOrders;
    }
    
    /**
     * �敨OP��������L���[�e�[�u������A�t�����g�������n�Ŏs���t�s���������擾����B   <BR>
     * <BR>
     * �P�j�@@��������������𐶐�����B    <BR>
     * <BR>
     *  �@@[����]   <BR>
     *  �@@�،���ЃR�[�h = ? and    <BR>
     *  �@@�t�����g����������敪�R�[�h = ? and <BR>
     *  �@@�t�����g�����V�X�e���敪 = ? and   <BR>
     *  �@@�t�����g��������敪�R�[�h = ? and  <BR>
     *  �@@�����o�H�敪 = ? and <BR>
     *  �@@�����敪 in (?,?) <BR>
     * <BR>
     * �Q�j�@@��������������R���e�i�𐶐�����B    <BR>
     * <BR>
     *  �@@�Q�|�P�j�@@this.�،���ЃR�[�h��ݒ�B  <BR>
     * <BR>
     *  �@@�Q�|�Q�j�@@this.�t�����g����������敪�R�[�h��ݒ�B   <BR>
     * <BR>
     *  �@@�Q�|�R�j�@@this.�t�����g�����V�X�e���敪��ݒ�B <BR>
     * <BR>
     *  �@@�Q�|�S�j�@@1�F����������ݒ�B    <BR>
     * <BR>
     *  �@@�Q�|�T�j�@@2�F�t�����g�������n��ݒ�B    <BR>
     * <BR>
     *  �@@�Q�|�U�j�@@1�F���M�ς�ݒ�B <BR>
     * <BR>
     *  �@@�Q�|�V�j�@@2�FAMG���͊�����ݒ�B <BR>
     * <BR>
     * �R�j�@@doFindAllQuery�i�j�ŁA�s���t�s���������擾�B   <BR>
     * <BR>
     * �S�j�@@�擾���ʂ�ԋp����B   <BR>
     * @@return List<BR>
     * @@roseuid 
     */
    private List getIfoMarketAcceptGrayOrder() throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = ".getIfoMarketAcceptGrayOrder()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_grayOrders = new ArrayList();
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");        
        l_sbWhere.append(" and status in (?,?) ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                this.institutionCode,
                this.frotOrderExCode,
                this.frotOrderSystemDiv,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION,
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_grayOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
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

        return l_grayOrders;
    }

    /**
     * ����敪 = 0�F����ȊO�A�S���������敪 = 1�F�S�������������������ɁA<BR>
     * ������������L���[�e�[�u�����������A<BR>
     * ���R�[�h�����݂����true��ԋp����B    <BR>
     * <BR>
     * �P�j�@@���������I�u�W�F�N�g���� <BR>
     * <BR>
     * �@@[����]   <BR>
     * �@@����敪 = ? and   <BR>
     * �@@�Г��������� = ? and <BR>
     * �@@�S���������敪 = ?    <BR>
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i����    <BR>
     * <BR>
     * �@@�Q�|�P�j�@@0�F����ȊO��ݒ�B    <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�p�����[�^.�Г��������ڂ�ݒ�B  <BR>
     * <BR>
     * �@@�Q�|�R�j�@@1�F�S����������ݒ�B   <BR>
     * <BR>
     * �R�j�@@doFindAllQuery()�ŁA���R�[�h�������B   <BR>
     * <BR>
     * �S�j�@@�������ʂ����݂����true��ԋp�A���݂��Ȃ����false��ԋp����B <BR>
     * <BR>
     * @@param �Г��������� - �Г��������ځB<BR>
     * @@return boolean<BR>
     * @@roseuid 42F6E0AB0321
     */
    protected boolean validateHostEqtypeRepeat(String l_corpCode) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateHostEqtypeRepeat(String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_hostOrders = new ArrayList();
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                WEB3CancelDivDef.EXCEPT_CANCEL,
                l_corpCode,
                WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE,
            };
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);

            // ��L�[����Ɍ���
//          HostEqtypeOrderAllDao l_dao = HostEqtypeOrderAllDao.findDaoByPk(WEB3CancelDivDef.EXCEPT_CANCEL, l_corpCode, WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
        }
//        // �f�[�^�����݂��Ȃ��ꍇ��false��ԋp
//        catch(DataFindException l_finEx)
//        {
//            return false;
//        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        // �f�[�^�����݂��Ȃ��ꍇ��false��ԋp
        if(l_hostOrders.size() == 0)
        {
            return false;            
        }
        log.exiting(STR_METHOD_NAME);

        return true;
    }
    
    /**
     * ����敪 = 0�F����ȊO�A�S���������敪 = 1�F�S�������������������ɁA <BR>
     * �敨OP��������L���[�e�[�u�����������A<BR>
     * ���R�[�h�����݂����true��ԋp����B <BR>
     * <BR>
     * �P�j�@@���������I�u�W�F�N�g���� <BR>
     * <BR>
     *  �@@ �@@[����] <BR>
     * �@@ �@@ ����敪 = ? and <BR>
     * �@@ �@@ �Г��������� = ? and <BR>
     * �@@ �@@ �S���������敪 = ? <BR>
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i���� <BR>
     * <BR>
     *   �@@ �@@�Q�|�P�j�@@0�F����ȊO��ݒ�B <BR>
     * <BR>
     *   �@@ �@@�Q�|�Q�j�@@�p�����[�^.�Г��������ڂ�ݒ�B <BR>
     * <BR>
     *   �@@ �@@�Q�|�R�j�@@1�F�S����������ݒ�B <BR>
     * <BR>
     * �R�j�@@doFindAllQuery()�ŁA���R�[�h�������B <BR>
     * <BR>
     * �S�j�@@�������ʂ����݂����true��ԋp�A���݂��Ȃ����false��ԋp����B<BR>
     * <BR>
     * @@param �Г��������� - �Г��������ځB<BR>
     * @@return boolean<BR>
     * @@roseuid 42F6E0AB0321
     */
    private boolean validateIfoHostFotypeRepeat(String l_corpCode) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = ".validateIfoHostFotypeRepeat(String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayList�I�u�W�F�N�g�̐���
        List l_hostOrders = new ArrayList();
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                WEB3CancelDivDef.EXCEPT_CANCEL,
                l_corpCode,
                WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE,
            };
        
        try
        {
            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
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
        // �f�[�^�����݂��Ȃ��ꍇ��false��ԋp
        if(l_hostOrders.size() == 0)
        {
            return false;            
        }
        log.exiting(STR_METHOD_NAME);

        return true;
    }
   
    /**
     * ������������L���[�e�[�u���Ɏs���t�s�������̒����������쐬����B   <BR>
     * <BR>
     * �P�j�@@HostEqtypeOrderAllParams�I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�e���ڂ�ݒ肷��B    <BR>
     * <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V  <BR>
     * �@@�u�����o�H�ؑ�_������������L���[�e�[�u��.xls�v��    <BR>
     * �@@�@@�V�[�g�u�����o�H�ؑ�_������������L���[�e�[�u���iinsert�j�v���Q�ƁB <BR>
     * <BR>
     * �R�j�@@doInsertQuery�i�j���������s���A�f�[�^��Insert����B  <BR>
     * <BR>
     * @@param ������������L���[Row�i�s���t�s�������j - ������������L���[Row�i�s���t�s�������j�B<BR>
     * @@roseuid 42F6E0B0019A
     */
    protected void createMarketAcceptModifyOrder(HostEqtypeOrderAllRow l_hostEqtypeRow) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "createMarketAcceptNodifyOrder(HostEqtypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        // HostEqtypeOrderAllParams�C���X�^���X�𐶐�
        HostEqtypeOrderAllParams orderAllParams = new HostEqtypeOrderAllParams();
        
        // �f�[�^�R�[�h = "AI802"(������������)���Z�b�g
        orderAllParams.request_code = WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE;
		// ����ID���Z�b�g
		orderAllParams.account_id = new Long(l_hostEqtypeRow.getAccountId());
        // �،���ЃR�[�h���Z�b�g
        orderAllParams.institution_code = l_hostEqtypeRow.getInstitutionCode();
        // ���X�R�[�h���Z�b�g
        orderAllParams.branch_code = l_hostEqtypeRow.getBranchCode();
        // ���ʃR�[�h���Z�b�g
        orderAllParams.order_request_number = l_hostEqtypeRow.getOrderRequestNumber();
        // �����R�[�h���Z�b�g
        orderAllParams.product_code = l_hostEqtypeRow.getProductCode();
        // �󒍓������Z�b�g
        orderAllParams.received_date_time = l_hostEqtypeRow.getReceivedDateTime();
        // �����o�H�敪 = �t�����g�������n���Z�b�g
        orderAllParams.submit_order_route_div = WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION;
        // ���t���ʂ��Z�b�g
        if(l_hostEqtypeRow.getSellOrderQuantity() != 0.0D && ! l_hostEqtypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.sell_order_quantity = new Double(l_hostEqtypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.sell_order_quantity = new Double(l_hostEqtypeRow.getSellOrderQuantity());
        }
        // ���t���ʂ��Z�b�g
        if(l_hostEqtypeRow.getBuyOrderQuantity() != 0.0D && ! l_hostEqtypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.buy_order_quantity = new Double(l_hostEqtypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.buy_order_quantity = new Double(l_hostEqtypeRow.getBuyOrderQuantity());
        }
        // �w�l���Z�b�g
		if(l_hostEqtypeRow.getPriceConditionType() != null && l_hostEqtypeRow.getPriceConditionType().equals(" "))
		{
            if(l_hostEqtypeRow.getChangeLimitPriceIsNull())
            {
                orderAllParams.limit_price = new Double(l_hostEqtypeRow.getLimitPrice());
            }
            else
            {
                orderAllParams.limit_price = new Double(l_hostEqtypeRow.getChangeLimitPrice());
            }
		}
		// �������ʂ��Z�b�g
        if(! l_hostEqtypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.change_quantity = new Double(l_hostEqtypeRow.getChangeQuantity());
        }
		else if(l_hostEqtypeRow.getSellOrderQuantity() != 0.0D)
		{
			orderAllParams.change_quantity = orderAllParams.sell_order_quantity;
		}
		else
		{
			orderAllParams.change_quantity = orderAllParams.buy_order_quantity;
		}
		// �����w�l���Z�b�g
		if(l_hostEqtypeRow.getPriceConditionType() != null && l_hostEqtypeRow.getPriceConditionType().equals(" "))
		{
            if(l_hostEqtypeRow.getChangeLimitPriceIsNull())
            {
                orderAllParams.change_limit_price = new Double(l_hostEqtypeRow.getLimitPrice());
            }
            else
            {
                orderAllParams.change_limit_price = new Double(l_hostEqtypeRow.getChangeLimitPrice());
            }
		}
        // ����敪���Z�b�g
        orderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
        // �t�����g����������敪�R�[�h���Z�b�g
        orderAllParams.front_order_exchange_code = l_hostEqtypeRow.getFrontOrderExchangeCode();
        // �t�����g�����V�X�e���敪���Z�b�g
        orderAllParams.front_order_system_code = l_hostEqtypeRow.getFrontOrderSystemCode();
        // �t�����g��������敪�R�[�h���Z�b�g
        orderAllParams.front_order_trade_code = l_hostEqtypeRow.getFrontOrderTradeCode();
        // �Г��������ڂ��Z�b�g
        orderAllParams.setCorpCode(l_hostEqtypeRow.getCorpCode());

        //�i������j�Г��������ڂ��Z�b�g
        //�s���t�s������.�t�����g����������敪�R�[�h == "����" or "����"�@@���A
        //�����i�s���t�s������.�f�[�^�R�[�h == "AI802"������敪 == "0"�j�̏ꍇ
        //�@@�E�s���t�s������.�i������j�Г���������
        //��L�O�̏ꍇ
        //�@@�E�s���t�s������.�Г���������
        String l_strFrontOrderExchangeCode = l_hostEqtypeRow.getFrontOrderExchangeCode();
        String l_strRequestCode = l_hostEqtypeRow.getRequestCode();
        String l_strCancelDiv = l_hostEqtypeRow.getCancelDiv();

        if ((WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
            || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode))
            && WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE.equals(l_strRequestCode)
            && WEB3CancelDivDef.EXCEPT_CANCEL.equals(l_strCancelDiv))
        {
            orderAllParams.setOrgCorpCode(l_hostEqtypeRow.getOrgCorpCode());
        }
        else
        {
            orderAllParams.setOrgCorpCode(l_hostEqtypeRow.getCorpCode());
        }

        // �S���������敪 = "�S��������"���Z�b�g
        orderAllParams.setAllOrderChangeDiv(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
        // �����敪 = "������"���Z�b�g
        orderAllParams.setStatus(WEB3StatusDef.NOT_DEAL);
        
        try{
            // Insert����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(orderAllParams);
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
    }
    
    /**
     * �敨OP��������L���[�e�[�u���Ɏs���t�s�������̒����������쐬����B <BR>
     * <BR>
     * �P�j�@@HostFotypeOrderAllParams�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�e���ڂ�ݒ肷��B<BR>
�@@   *   �@@ �@@�y��Trade�z�⑫����.DB�X�V<BR>
�@@   *   �@@ �@@�u�����o�H�ؑ�_�敨OP��������L���[�e�[�u��.xls�v�� <BR>
�@@�@@ *   �@@ �@@�V�[�g�u�����o�H�ؑ�_�敨OP��������L���[�e�[�u���iinsert�j�v���Q�ƁB <BR>
�@@�@@ * <BR>
     * �R�j�@@doInsertQuery�i�j���������s���A�f�[�^��Insert����B<BR>
     * @@param �敨OP��������L���[Row�i�s���t�s�������j- �敨OP��������L���[Row�i�s���t�s�������j�B<BR>
     * @@roseuid 
     */
    private void createIfoMarketAcceptModifyOrder(HostFotypeOrderAllRow l_hostFotypeRow) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = ".createIfoMarketAcceptModifyOrder(HostFotypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        // HostFotypeOrderAllParams�C���X�^���X�𐶐�
        HostFotypeOrderAllParams orderAllParams = new HostFotypeOrderAllParams();
       
        //�f�[�^�R�[�h
        if (WEB3IfoProductTypeDef.FUTURES.equals(l_hostFotypeRow.getFutureOptionProductType()))
        {
            orderAllParams.request_code = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
        }
        else
        {
            orderAllParams.request_code = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;
        }
        
        //�����h�c
        orderAllParams.account_id = new Long(l_hostFotypeRow.getAccountId());
        //�،���ЃR�[�h
        orderAllParams.institution_code = l_hostFotypeRow.getInstitutionCode();
        //���X�R�[�h
        orderAllParams.branch_code = l_hostFotypeRow.getBranchCode(); 
        //���ʃR�[�h
        orderAllParams.order_request_number = l_hostFotypeRow.getOrderRequestNumber();
        //�����R�[�h
        orderAllParams.product_code = l_hostFotypeRow.getProductCode();
        //�󒍓���
        orderAllParams.received_date_time = l_hostFotypeRow.getReceivedDateTime();
        //�����o�H�敪
        orderAllParams.submit_order_route_div = WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION;
        //���t����
        if(l_hostFotypeRow.getSellOrderQuantity() != 0.0D && ! l_hostFotypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.sell_order_quantity = new Double(l_hostFotypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.sell_order_quantity = new Double(l_hostFotypeRow.getSellOrderQuantity());
        }
        // ���t���ʂ��Z�b�g
        if(l_hostFotypeRow.getBuyOrderQuantity() != 0.0D && ! l_hostFotypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.buy_order_quantity = new Double(l_hostFotypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.buy_order_quantity = new Double(l_hostFotypeRow.getBuyOrderQuantity());
        }
        // �w�l���Z�b�g
        if(l_hostFotypeRow.getChangeLimitPriceIsNull())
        {
            orderAllParams.limit_price = new Double(l_hostFotypeRow.getLimitPrice());
        }
        else
        {
            orderAllParams.limit_price = new Double(l_hostFotypeRow.getChangeLimitPrice());
        }
        // �������ʂ��Z�b�g
        if(l_hostFotypeRow.getChangeQuantityIsNull() && l_hostFotypeRow.getSellOrderQuantity() != 0.0D)
        {
            orderAllParams.change_quantity = orderAllParams.sell_order_quantity;
        }
        else if(l_hostFotypeRow.getChangeQuantityIsNull() && l_hostFotypeRow.getSellOrderQuantity() == 0.0D)
        {
            orderAllParams.change_quantity = orderAllParams.buy_order_quantity;
        }
        else if(!l_hostFotypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.change_quantity = new Double(l_hostFotypeRow.getChangeQuantity());
        }
        // �����w�l���Z�b�g
        if(l_hostFotypeRow.getChangeLimitPriceIsNull())
        {
                orderAllParams.change_limit_price = new Double(l_hostFotypeRow.getLimitPrice());
                
        }
        else 
        {
            orderAllParams.change_limit_price = new Double(l_hostFotypeRow.getChangeLimitPrice());
        }
        // ����敪���Z�b�g
        orderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
        // �t�����g����������敪�R�[�h���Z�b�g
        orderAllParams.front_order_exchange_code = l_hostFotypeRow.getFrontOrderExchangeCode();
        // �t�����g�����V�X�e���敪���Z�b�g
        orderAllParams.front_order_system_code = l_hostFotypeRow.getFrontOrderSystemCode();
        // �t�����g��������敪�R�[�h���Z�b�g
        orderAllParams.front_order_trade_code = l_hostFotypeRow.getFrontOrderTradeCode();
        // �Г��������ڂ��Z�b�g
        orderAllParams.setCorpCode(l_hostFotypeRow.getCorpCode());
        // �i������j�Г��������ڂ��Z�b�g
        orderAllParams.org_corp_code = l_hostFotypeRow.getCorpCode();
        // �S���������敪 = "�S��������"���Z�b�g
        orderAllParams.setAllOrderChangeDiv(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
        // �����敪 = "������"���Z�b�g
        orderAllParams.setStatus(WEB3StatusDef.NOT_DEAL);
        
        try
        {
            // Insert����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(orderAllParams);
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
    }
   
    /**
     * �s���t�s�������̏����敪��"�X�F�s���t�m�F��"�ɍX�V����B<BR>
     * <BR>
     * �P�j�@@���������I�u�W�F�N�g����<BR>
     * <BR>
     *    �@@ �@@[����]<BR>
     *    �@@ �@@����敪 = ? and<BR>
     *    �@@ �@@�Г��������� = ? and<BR>
     *    �@@ �@@�S���������敪 = ? and<BR>
     *    �@@ �@@�����敪 in (?, ?)<BR>
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i����<BR>
     * <BR>
     *    �@@ �@@�Q�|�P�j�@@�p�����[�^.������������L���[Ro��.����敪��ݒ�B<BR>
     * <BR>
     *    �@@ �@@�Q�|�Q�j�@@�p�����[�^.������������L���[Ro��.�Г��������ڂ�ݒ�B<BR>
     * <BR>
     *    �@@ �@@�Q�|�R�j�@@�p�����[�^.������������L���[Ro��.�S���������敪��ݒ�B<BR>
     * <BR>
     *    �@@ �@@�Q�|�S�j�@@1�F���M�ς�ݒ�B<BR>
     * <BR>
     *    �@@ �@@�Q�|�T�j�@@2�FAMG���͊�����ݒ�B<BR>
     * <BR>
     * �R�j�@@doUpdateQuery�i�j�ŁA���R�[�h���X�V����B<BR>
     * <BR>
     *    �@@ �@@�y��Trade�z�⑫����.DB�X�V<BR>
     *    �@@ �@@�u�����o�H�ؑ�_������������L���[�e�[�u��.xls�v��<BR>
     *    �@@ �@@�V�[�g�u�����o�H�ؑ�_������������L���[�e�[�u�� (update)�v���Q�ƁB<BR>
     * <BR>
     * @@param ������������L���[Row�i�s���t�s�������j - ������������L���[Row�i�s���t�s�������j�B<BR>
     * @@roseuid 42F6F4660211
     */
    protected void updateGrayOrderStatus(HostEqtypeOrderAllRow l_hostEqtypeRo��) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "updateGrayOrderStatus(HostEqtypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        
        // Map�I�u�W�F�N�g�̐���
        Map l_mapStatus = new HashMap();
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        l_sbWhere.append(" and status in (?,?) ");
        
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_hostEqtypeRo��.getCancelDiv(),
                l_hostEqtypeRo��.getCorpCode(),
                l_hostEqtypeRo��.getAllOrderChangeDiv(),
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };

        l_mapStatus.put("status",WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
        l_mapStatus.put("last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        try{
            // Update����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateAllQuery(HostEqtypeOrderAllRow.TYPE, l_sbWhere.toString(), l_objWhere, l_mapStatus);
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
    }

    /**
     * �s���t�s�������̏����敪��"�X�F�s���t�m�F��"�ɍX�V����B<BR>
     * <BR>
     * �P�j�@@���������I�u�W�F�N�g����<BR>
     * <BR>
     *    �@@ �@@[����]<BR>
     *    �@@ �@@����敪 = ? and<BR>
     *    �@@ �@@�Г��������� = ? and<BR>
     *    �@@ �@@�S���������敪 = ? and<BR>
     *    �@@ �@@�����敪 in (?, ?)<BR>
     * <BR>
     * �Q�j�@@���������f�[�^�R���e�i����<BR>
     * <BR>
     *    �@@ �@@�Q�|�P�j�@@�p�����[�^.�敨OP��������L���[Ro��.����敪��ݒ�B <BR>
     * <BR>
     *    �@@ �@@�Q�|�Q�j�@@�p�����[�^.�敨OP��������L���[Ro��.�Г��������ڂ�ݒ�B <BR>
     * <BR>
     *    �@@ �@@�Q�|�R�j�@@�p�����[�^.�敨OP��������L���[Ro��.�S���������敪��ݒ�B <BR>
     * <BR>
     *    �@@ �@@�Q�|�S�j�@@1�F���M�ς�ݒ�B<BR>
     * <BR>
     *    �@@ �@@�Q�|�T�j�@@2�FAMG���͊�����ݒ�B<BR>
     * <BR>
     * �R�j�@@doUpdateQuery�i�j�ŁA���R�[�h���X�V����B<BR>
     * <BR>
     *    �@@ �@@�y��Trade�z�⑫����.DB�X�V<BR>
     *    �@@ �@@�u�����o�H�ؑ�_�敨OP��������L���[�e�[�u��(update)�v�� <BR>
     *    �@@ �@@�V�[�g�u�����o�H�ؑ�_�敨OP��������L���[�e�[�u��(update)�v���Q�ƁB
     * <BR>
     * @@param �敨OP��������L���[Ro���i�s���t�s�������j- �敨OP��������L���[Ro���i�s���t�s�������j�B<BR>
     * @@roseuid 42F6F4660211
     */
    private void updateIfoGrayOrderStatus(HostFotypeOrderAllRow l_hostFotypeRo��) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = ".updateIfoGrayOrderStatus(HostFotypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        
        // Map�I�u�W�F�N�g�̐���
        Map l_mapStatus = new HashMap();
        
        // ��������������̐���
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        l_sbWhere.append(" and status in (?,?) ");
        
        
        // ���������R���e�i�̐���
        Object[] l_objWhere =
            {
                l_hostFotypeRo��.getCancelDiv(),
                l_hostFotypeRo��.getCorpCode(),
                l_hostFotypeRo��.getAllOrderChangeDiv(),
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };

        l_mapStatus.put("status",WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
        l_mapStatus.put("last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        try{
            // Update����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateAllQuery(HostFotypeOrderAllRow.TYPE, l_sbWhere.toString(), l_objWhere, l_mapStatus);
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
    }
    
    /**
     * ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���ɐؑ֎w���v���n���R�[�h��}������B   <BR>
     * <BR>
     * [�p�����[�^]<BR>
     * ���z�T�[�o���e�[�u��Row<BR>
     * <BR>
     *  �P�j�@@this.�ؑ֏����������ʔԏƉ���̏ꍇ�A�ʔԏƉ�v�����R�[�h��o�^<BR>
     * <BR>
     *  �@@�P�|�P�j�@@validate��Q���z�T�[�o���R�[�h�i�j�����s���A    <BR>
     *  �@@�@@�@@�@@�@@�@@��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���ɃL�[�d�������݂��Ȃ����`�F�b�N�������Ȃ��B <BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʔԏƉ�v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"�f�t�H���g"<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     *  �@@�P�|�Q�j�@@�G���[���Ȃ��ꍇ�Ainsert��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���i�j�����s���A <BR>
     *  �@@�@@�@@�@@�@@�@@�ʔԏƉ�v�����R�[�h��o�^����B  <BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʔԏƉ�v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"�f�t�H���g"<BR>
     * �@@�@@�@@�@@���z�T�[�o���e�[�u��Row�F�@@���z�T�[�o���e�[�u��Row <BR>
     * <BR>
     *  �Q�j�@@�ʒm��s�����v�����R�[�h��o�^  <BR>
     * <BR>
     *  �@@�Q�|�P�j�@@validate��Q���z�T�[�o���R�[�h�i�j�����s���A    <BR>
     *  �@@�@@�@@�@@�@@�@@��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���ɃL�[�d�������݂��Ȃ����`�F�b�N�������Ȃ��B <BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm��s�����v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"�f�t�H���g"<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     *  �@@�Q�|�Q�j�@@�G���[���Ȃ��ꍇ�Ainsert��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���i�j�����s���A <BR>
     *  �@@�@@�@@�@@�@@�@@�ʒm��s�����v�����R�[�h��o�^����B    <BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm��s�����v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"�f�t�H���g"<BR>
     * �@@�@@�@@�@@���z�T�[�o���e�[�u��Row�F�@@���z�T�[�o���e�[�u��Row<BR>
     * <BR>
     *  �R�j�@@�ʒm��s�v�����R�[�h��o�^    <BR>
     * <BR>
     *  �@@�R�|�P�j�@@validate��Q���z�T�[�o���R�[�h�i�j�����s���A    <BR>
     *  �@@�@@�@@�@@�@@�@@��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���ɃL�[�d�������݂��Ȃ����`�F�b�N�������Ȃ��B <BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm��s�v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"�f�t�H���g"<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     *  �@@�R�|�Q�j�@@�G���[���Ȃ��ꍇ�Ainsert��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���i�j�����s���A <BR>
     *  �@@�@@�@@�@@�@@�@@�ʒm��s�v�����R�[�h��o�^����B  <BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm��s�v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"�f�t�H���g"<BR>
     * �@@�@@�@@�@@���z�T�[�o���e�[�u��Row�F�@@���z�T�[�o���e�[�u��Row<BR>
     * <BR>
     *  �S�j�@@�ʒm�đ��v���i��t�n�j���R�[�h��o�^   <BR>
     * <BR>
     *  �@@�S�|�P�j�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�S�|�P�|�P�j�@@validate��Q���z�T�[�o���R�[�h�i�j�����s���A<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���ɃL�[�d�������݂��Ȃ����`�F�b�N�������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm�đ��v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"��t�n"<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     * �@@�@@�@@�S�|�P�|�Q�j�@@�G���[���Ȃ��ꍇ�Ainsert��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���i�j�����s���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ʒm�đ��v���i��t�n�j���R�[�h��o�^����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm�đ��v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"��t�n"<BR>
     * �@@�@@�@@�@@���z�T�[�o���e�[�u��Row�F�@@���z�T�[�o���e�[�u��Row<BR>
     * <BR>
     *  �T�j�@@�ʒm�đ��v���i���n�j���R�[�h��o�^   <BR>
     * <BR>
     *  �@@�T�|�P�j�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�@@�T�|�P�|�P�j�@@validate��Q���z�T�[�o���R�[�h�i�j�����s���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���ɃL�[�d�������݂��Ȃ����`�F�b�N�������Ȃ��B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@���z�T�[�oNo.�i�s��j�F�@@���z�T�[�o���e�[�u��Row.get���z�T�[�oNo.�i�s��j<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm�đ��v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"���n"<BR>
     * �@@�@@�@@�@@�t�����g����������敪�R�[�h�F�@@���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h<BR>
     * <BR>
     * �@@�@@�@@�T�|�P�|�Q�j�@@�G���[���Ȃ��ꍇ�Ainsert��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���i�j�����s���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ʒm�đ��v���i���n�j���R�[�h��o�^����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�ؑ֎w�������敪�F�@@"�ʒm�đ��v��"<BR>
     * �@@�@@�@@�@@�ʒm��ʁF�@@"���n"<BR>
     * �@@�@@�@@�@@���z�T�[�o���e�[�u��Row�F�@@���z�T�[�o���e�[�u��Row<BR>
     * <BR>
     * @@param ���z�T�[�o���e�[�u��Row - ���z�T�[�o���e�[�u��Row<BR>
     * @@roseuid 
     */
    private void executeVirtualServChange(VirtualServerInformationRow l_priVirServInfoRow) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "executeVirtualServChange(VirtualServerInformationRow)";
        log.entering(STR_METHOD_NAME);
        
        // ���z�T�[�oNo(�s��)�擾
        String l_virServerNumMarket = l_priVirServInfoRow.getVirtualServerNumberMarket();
        // �t�����g����������敪�R�[�h�擾
        String l_frotnExCode = l_priVirServInfoRow.getFrontOrderExchangeCode();
        // ��Q���z�T�[�o�d���L�[�敪
        boolean l_isrepeat = false;

        // �ؑ֏����������ʔԏƉ���̏ꍇ�A�ʔԏƉ�v�����R�[�h��o�^
        if(this.changeProcessDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE)) 
        {
            // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���A���R�[�h�d���`�F�b�N
            l_isrepeat = this.validateVirtualServerRecord(l_virServerNumMarket, WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST , 
                                                            WEB3NoticeTypeDef.DEFAULT, l_frotnExCode);
                    
            // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
            if(l_isrepeat)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                this.insertVirtualServerChange(WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST, 
                                                        WEB3NoticeTypeDef.DEFAULT, l_priVirServInfoRow);
            }
        }
        // �ʒm��s�����v�����R�[�h��o�^
        // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���A���R�[�h�d���`�F�b�N
        l_isrepeat = this.validateVirtualServerRecord(l_virServerNumMarket, WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST,
                                                             WEB3NoticeTypeDef.DEFAULT, l_frotnExCode);
                    
        // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
        if(l_isrepeat)
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            this.insertVirtualServerChange(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST, 
                                                WEB3NoticeTypeDef.DEFAULT, l_priVirServInfoRow);
        }
        // �ʒm��s�v�����R�[�h��o�^
        // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���A���R�[�h�d���`�F�b�N
        l_isrepeat = this.validateVirtualServerRecord(l_virServerNumMarket, WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST, 
                                                            WEB3NoticeTypeDef.DEFAULT, l_frotnExCode);
                    
        // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
        if(l_isrepeat)
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            this.insertVirtualServerChange(WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST, 
                                                    WEB3NoticeTypeDef.DEFAULT, l_priVirServInfoRow);
        }
        // �ʒm�đ��v���i��t�n�j���R�[�h��o�^
        //���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A
        if (!(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_frotnExCode)
            || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_frotnExCode)))
        {
            // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���A���R�[�h�d���`�F�b�N
            l_isrepeat = this.validateVirtualServerRecord(
                l_virServerNumMarket,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                WEB3NoticeTypeDef.ACCEPT_TYPE,
                l_frotnExCode);

            // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
            if (l_isrepeat)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                this.insertVirtualServerChange(
                    WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                    WEB3NoticeTypeDef.ACCEPT_TYPE,
                    l_priVirServInfoRow);
            }
        }
        // �ʒm�đ��v���i���n�j���R�[�h��o�^
        //���z�T�[�o���e�[�u��Row.�t�����g����������敪�R�[�h != "����" or "����"�̏ꍇ�A
        if (!(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_frotnExCode)
            || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_frotnExCode)))
        {
            // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���A���R�[�h�d���`�F�b�N
            l_isrepeat = this.validateVirtualServerRecord(
                l_virServerNumMarket,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                WEB3NoticeTypeDef.EXECUTED_TYPE,
                l_frotnExCode);

            // ��Q���z�T�[�o�ؑ֊Ǘ��e�[�u���Ƀ��R�[�h�����݂���ꍇ�A�G���[��ԋp����B
            if (l_isrepeat)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                 this.insertVirtualServerChange(
                     WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                     WEB3NoticeTypeDef.EXECUTED_TYPE,
                     l_priVirServInfoRow);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
