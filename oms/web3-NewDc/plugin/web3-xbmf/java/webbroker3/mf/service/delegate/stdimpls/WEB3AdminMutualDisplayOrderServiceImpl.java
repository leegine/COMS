head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������o�^�T�[�r�XImpl(WEB3AdminMutualDisplayOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 ���� (���u) �V�K�쐬 
Revesion History : 2007/02/25 ������ (���u) ���f�� No.546
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.WEB3MutualProductCategory;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEBMFSortConditionDivDef;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputResponse;
import webbroker3.mf.message.WEB3MutualCurrentDisplayOrderComparator;
import webbroker3.mf.message.WEB3MutualAssocProductCodeComparator;
import webbroker3.mf.message.WEB3MutualCategoryCode1Comparator;
import webbroker3.mf.message.WEB3MutualCategoryCode2Comparator;
import webbroker3.mf.message.WEB3MutualCategoryCode3Comparator;
import webbroker3.mf.message.WEB3MutualDisplayOrderGroup;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.message.WEB3MutualProductCodeComparator;
import webbroker3.mf.service.delegate.WEB3AdminMutualDisplayOrderService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�Ǘ��Җ����\�������o�^�T�[�r�XImpl)<BR>
 * �����M���Ǘ��Җ����\�������o�^�T�[�r�X�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderServiceImpl
    extends WEB3MutualClientRequestService
    implements WEB3AdminMutualDisplayOrderService
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualDisplayOrderServiceImpl.class);
    /**
     * ���M�Ǘ��Җ����\�������o�^�T�[�r�X�����{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�ɂ���āA�ȉ��̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * �@@��create�����\�������o�^���͉��<BR>
     * �@@��submit�����\�������o�^
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^Null�B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualDisplayOrderInputRequest)
        {
            //��create�����\�������o�^���͉��
            l_response = 
                this.createMutualDisplayOrderInput(
                    (WEB3AdminMutualDisplayOrderInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualDisplayOrderCompleteRequest)
        {
            //��submit�����\�������o�^
            l_response = 
                this.submitMutualDisplayOrderRegistr(
                    (WEB3AdminMutualDisplayOrderCompleteRequest) l_request);
        }
        return l_response;
    }
    
    /**
     * (create�����\�������o�^���͉��)<BR>
     * ���M�Ǘ��Җ����\�������o�^���͉�ʎ擾���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�Ǘ��Җ����\�������o�^���͉�ʁv�Q��<BR>
     * --------------------------------------------------<BR>
     * �P�j���̓`�F�b�N<BR>
     * �@@���N�G�X�g�f�[�^.validate( )���R�[������B<BR>
     * <BR>
     * �Q�j���������̐ݒ�<BR>
     * �@@���M������ԊǗ�.setBusinessTimestamp( )���R�[������B<BR>
     * <BR>
     * �R�j�Ǘ��҃I�u�W�F�N�g�̎擾<BR>
     * �@@�R�|�P�j�Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B<BR>
     * �@@�R�|�Q�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B<BR>
     * <BR>
     * �S�j�����ꗗ�̎擾<BR>
     * �@@WEBBROKER�V�Ŏ�舵����S�Ă̓��M�������擾����B<BR>
     * �@@�S�|�P�j�擾�����Ǘ��҃I�u�W�F�N�g���،���ЃR�[�h���擾����B<BR>
     * �@@�S�|�Q�j�g�����M�����}�l�[�W��.get���M�������X�g( )���R�[������B<BR>
     * �@@�S�|�R�jget���M�������X�g( )�̖߂�l��null�A�܂���0���ł������ꍇ�A<BR>
     * �@@�@@�@@�@@[�������ʂȂ�]�Ƃ��ė�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:BUSINESS_ERROR_01279<BR>
     * <BR>
     * �T�j���M�������ׂ̍쐬<BR>
     * �@@  ���M�Ǘ��Җ����\�������o�^�ꗗ�s�I�u�W�F�N�g�̔z����쐬����B<BR>
     * �@@�T�|�P�j���M�Ǘ��Җ����\�������o�^�ꗗ�s�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�T�|�Q�j�ȉ��̃v���p�e�B��ݒ肷��B<BR>
     * �@@�@@�@@�@@  ���\����<BR>
     * �@@�@@�@@�@@  �������R�[�h<BR>
     * �@@�@@�@@�@@  �����M��������R�[�h<BR>
     * �@@�@@�@@�@@  ��������<BR>
     * �@@�@@�@@�@@  �����M�����J�e�S���[�P�`�R<BR>
     * �@@�T�|�R�j�g�����M�����}�l�[�W��.get���M�����J�e�S���[( )���R�[�����A<BR>
     * �@@�@@�@@    �ȉ��̃v���p�e�B��ݒ肷��B<BR>
     * �@@�@@�@@�@@       �����M�����J�e�S���[�R�[�h�P<BR>
     * �@@�@@�@@�@@       �����M�����J�e�S���[���̂P<BR>
     * �@@�@@�@@�@@       �����M�����J�e�S���[�R�[�h�Q<BR>
     * �@@�@@�@@�@@       �����M�����J�e�S���[���̂Q<BR>
     * �@@�@@�@@�@@       �����M�����J�e�S���[�R�[�h�R<BR>
     * �@@�@@�@@�@@       �����M�����J�e�S���[���̂R<BR>
     * <BR>
     * �U�j�\�[�g����<BR>
     * �@@ this.sort���M�����ꗗ����( )���R�[�����A���ёւ������{����B<BR>
     * <BR>
     * �V�jsort���M�����ꗗ����( )�̖߂�l���A<BR>
     *    ���X�|���X�f�[�^.�����\�������o�^�ꗗ<BR>
     * �@@ �ɃZ�b�g���A�ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��Җ����\�������o�^���͉�ʃ��N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminMutualDisplayOrderInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F9450121
     */
    protected WEB3AdminMutualDisplayOrderInputResponse createMutualDisplayOrderInput(
        WEB3AdminMutualDisplayOrderInputRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createMutualDisplayOrderInput(" +
            "WEB3AdminMutualDisplayOrderInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1�j���̓`�F�b�N 
        //�@@  ���N�G�X�g�f�[�^.validate( )���R�[������B 
        l_request.validate();

        //���M�E�O��MMF�\���敪
        //��null�̏ꍇ�A�u2:�����v�Ƃ���
        if (l_request.mutualFrgnMmfDisplayDiv == null)
        {
            l_request.mutualFrgnMmfDisplayDiv =
                WEB3MutualFrgnMmfDisplayDivDef.BOTH;
        }

        //1.3�j�Ǘ��҃I�u�W�F�N�g�̎擾 
        //  �Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, false);
        
        //1.5�j�擾�����Ǘ��҃I�u�W�F�N�g���،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6�j�g�����M�����}�l�[�W��.get���M�������X�g( )���R�[������B 
        //�،���ЂɕR�t���S�Ă̓��M�����̈ꗗ���擾����B
        //[get���M�������X�g�ɓn������]
        //�@@�،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h
        //  ��������������i��1�j
        //  ���������f�[�^�R���e�i�i��1�j
        //�@@�\�[�g�����敪���h���t�ꗗ�Ɖ�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_tradingModule.getProductManager();

        //(��1)  ��������������/���������f�[�^�R���e�i
        //    �@@���N�G�X�g.���M�E�O��MMF�\���敪 = "���M�̂�"�̏ꍇ
        //       ��������������F
        //           "system_handling_div != ? and fund_type != ?"
        //       ���������f�[�^�R���e�i�F
        //           "0�FWEBBROKER�V�Ŏ�舵��Ȃ�", "MutualFundTypeEnum.�O��MMF" �̔z��
        String l_strSearchCondCharacter = null;
        List l_lisSearchCondDataContainer = new ArrayList();
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_request.mutualFrgnMmfDisplayDiv))
        {
            //��������������
            l_strSearchCondCharacter = "system_handling_div != ? and fund_type != ?";
            //���������f�[�^�R���e�i
            l_lisSearchCondDataContainer.add(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN);
            l_lisSearchCondDataContainer.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
        }

        //    �A���N�G�X�g.���M�E�O��MMF�\���敪 = "�O��MMF�̂�"�̏ꍇ
        //       ��������������F
        //           "system_handling_div != ? and fund_type = ?"
        //       ���������f�[�^�R���e�i�F
        //           "0�FWEBBROKER�V�Ŏ�舵��Ȃ�", "MutualFundTypeEnum.�O��MMF" �̔z��
        else if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_request.mutualFrgnMmfDisplayDiv))
        {
            //��������������
            l_strSearchCondCharacter = "system_handling_div != ? and fund_type = ?";
            //���������f�[�^�R���e�i
            l_lisSearchCondDataContainer.add(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN);
            l_lisSearchCondDataContainer.add(MutualFundTypeEnum.FOREIGN_MMF.intValue() + "");
        }

        //    �B���N�G�X�g.���M�E�O��MMF�\���敪 = "����"�̏ꍇ
        //       ��������������F
        //           "system_handling_div != ?"
        //       ���������f�[�^�R���e�i�F
        //           "0�FWEBBROKER�V�Ŏ�舵��Ȃ�"�݂̂̔z��
        else if (WEB3MutualFrgnMmfDisplayDivDef.BOTH.equals(l_request.mutualFrgnMmfDisplayDiv))
        {
            //��������������
            l_strSearchCondCharacter = "system_handling_div != ?";
            //���������f�[�^�R���e�i
            l_lisSearchCondDataContainer.add(WEB3SystemHandlingDivDef.WEBBROKER3_DONOT_TREAT_IT_IN);
        }

        int l_intLength = l_lisSearchCondDataContainer.size();
        String[] l_strSearchCondDataContainer = new String[l_intLength];
        l_lisSearchCondDataContainer.toArray(l_strSearchCondDataContainer);

        List l_lisMutualFundProductList =
            l_mfProductManager.getMutualFundProductList(
                l_strInstitutionCode,
                l_strSearchCondCharacter,
                l_strSearchCondDataContainer,
                WEBMFSortConditionDivDef.MUTUAL_BUY_LIST);
        
        //get���M�������X�g( )�̖߂�l��null�A�܂���0���ł������ꍇ�A 
        //�@@[�������ʂȂ�]�Ƃ��ė�O���X���[����B 
        if (l_lisMutualFundProductList == null || 
            l_lisMutualFundProductList.size() == 0)
        {
            log.debug("�������ʂȂ��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01279,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.7) �J��Ԃ�����
        // ���M�������ׂ̍쐬 
        // get���M�������X�g( )�̖߂�l�̌������J��Ԃ���
        // ���M�Ǘ��Җ����\�������o�^�ꗗ�s�I�u�W�F�N�g��
        List l_lismfProductRentn = new Vector();
        for (int i = 0; i < l_lisMutualFundProductList.size(); i++)
        {
            //1.7.1) ���M�Ǘ��Җ����\�������o�^�ꗗ�s( )
            WEB3MutualDisplayOrderGroup l_mutualDisplayOrderGroup = 
                new WEB3MutualDisplayOrderGroup();
        
            //1.7.2)�v���p�e�B�E�Z�b�g *1
            //  get���M�������X�g( )�̖߂�l�̒���蓊�M����Params���擾���A 
            //  �����������M�Ǘ��Җ����\�������o�^�ꗗ�s�I�u�W�F�N�g�ɁA 
            //  �ȉ��̃v���p�e�B���Z�b�g����B                     
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_lisMutualFundProductList.get(i);
            MutualFundProductParams l_mfProductParams = 
                new MutualFundProductParams(l_mfProductRow);
            // ���\���������M����Params.get�\������( )
			if(l_mfProductParams.getIndicationRankingIsNull())
			{
				l_mutualDisplayOrderGroup.displayOrder = null;
			}
			else
			{
            	l_mutualDisplayOrderGroup.displayOrder = 
                	l_mfProductParams.getIndicationRanking() + "";
			}
            // �������R�[�h�����M����Params.get�����R�[�h( ) 
            l_mutualDisplayOrderGroup.mutualProductCode = 
                l_mfProductParams.getProductCode();
            // �����M��������R�[�h�����M����Params.get���M��������R�[�h( ) 
            l_mutualDisplayOrderGroup.mutualAssocProductCode = 
                l_mfProductParams.getMutualassocProductCode();
            // �������������M����Params.get������( ) 
            l_mutualDisplayOrderGroup.mutualProductName = 
                l_mfProductParams.getStandardName();

            // ��������t���؎��ԁ�(*) 
            // (*-1) ���M������ԊǗ�.reset�����R�[�h( )���R�[������B
            // [reset�����R�[�h�ɓn������] 
            // �����R�[�h�����M����Params.get�����R�[�h( ) 
            WEB3MutualFundTradingTimeManagement.resetProductCode(
                l_mfProductParams.getProductCode());

            // (*-2) ���M������ԊǗ�.setTimestamp( )���R�[������B 
            WEB3MutualFundTradingTimeManagement.setTimestamp();
            // (*-3) ���M������ԊǗ�.get������t���؎���( )���R�[�����A 
            // �߂��ꂽ�l"HHMMSS"��"HH:MM"�ɕҏW���ăZ�b�g����B
            
            String l_strOrderCloseTime = 
                WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
            Date l_datOrderCloseTime = 
                WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
            l_strOrderCloseTime = 
                WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");                    
            log.debug("l_strOrderCloseTime" + l_strOrderCloseTime);
            l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
            	+ WEB3GentradeTimeDef.STR_COLON
            	+ l_strOrderCloseTime.substring(2, 4);
            l_mutualDisplayOrderGroup.orderCloseTime = 
                l_strOrderCloseTime;
        
            //1.7.3�j���M����Params.get�J�e�S���[�R�[�h( )��null�̏ꍇ
            if (l_mfProductParams.getCategoryCode() == null)
            {
                //1.7.3.1�j�v���p�e�B�E�Z�b�g *2
                //���M�����Ǘ��Җ����\�������o�^�ꗗ�s�I�u�W�F�N�g�� 
                //  �ȉ����Z�b�g����B 
                //  �����M�����J�e�S���[�R�[�h�P��null
                l_mutualDisplayOrderGroup.categoryCode1 = null;
                //  �����M�����J�e�S���[���̂P��null 
                l_mutualDisplayOrderGroup.categoryName1 = null;
                //  �����M�����J�e�S���[�R�[�h�Q��null 
                l_mutualDisplayOrderGroup.categoryCode2 = null;
                //  �����M�����J�e�S���[���̂Q��null 
                l_mutualDisplayOrderGroup.categoryName2 = null;
                //  �����M�����J�e�S���[�R�[�h�R��null
                l_mutualDisplayOrderGroup.categoryCode3 = null;
                //  �����M�����J�e�S���[���̂R��null
                l_mutualDisplayOrderGroup.categoryName3 = null;
            }
        
            //1.7.4�j���M����Params.get�J�e�S���[�R�[�h( )!=null�̏ꍇ
            else
            {
                //1.7.4.1�jget���M�����J�e�S���[(String, String)
                //  �擾�������M�����J�e�S���[�I�u�W�F�N�g������"A�J�e�S���["�Ƃ���
                //  [get���M�����J�e�S���[�ɓn������] 
                //�@@�،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h 
                //�@@���M�����J�e�S���[�R�[�h���������ʂ̓��M����Params.get�J�e�S���[�R�[�h( )
                WEB3MutualProductCategory l_mutualProductCategoryA =
                    l_mfProductManager.getMutualFundProductCategory(
                        l_strInstitutionCode,
                        l_mfProductParams.getCategoryCode());
                if (l_mutualProductCategoryA != null)
                {
                
                    //1.7.4.2�jget�e�J�e�S���[�R�[�h( )
                    String l_strParaentCategoryCodeA = 
                        l_mutualProductCategoryA.getParentCategoryCode();
                    log.debug("get�e�J�e�S���[�R�[�hA( ) =" + l_strParaentCategoryCodeA);
                    // ��A�J�e�S���[�݂̂��擾�ł����ꍇ
                    //�@@�����M�����J�e�S���[�R�[�h�P�@@��A�J�e�S���[.get�J�e�S���[�R�[�h( )
                    l_mutualDisplayOrderGroup.categoryCode1 = 
                        l_mutualProductCategoryA.getCategoryCode();
                    //�@@�����M�����J�e�S���[���̂P�@@ ��A�J�e�S���[.get�J�e�S���[����( ) 
                    l_mutualDisplayOrderGroup.categoryName1 = 
                        l_mutualProductCategoryA.getCategoryName();
                    //�@@�����M�����J�e�S���[�R�[�h�Q�@@��null 
                    l_mutualDisplayOrderGroup.categoryCode2 = null;
                    //�@@�����M�����J�e�S���[���̂Q�@@ ��null 
                    l_mutualDisplayOrderGroup.categoryName2 = null;
                    //�@@�����M�����J�e�S���[�R�[�h�R�@@��null 
                    l_mutualDisplayOrderGroup.categoryCode3 = null;
                    //�@@�����M�����J�e�S���[���̂R�@@ ��null 
                    l_mutualDisplayOrderGroup.categoryName3 = null;
                    //1.7.4.3�jA�J�e�S���[.get�e�J�e�S���[�R�[�h( )�̖߂�l!=null�̏ꍇ
                    if (l_strParaentCategoryCodeA != null)
                    {
                        //1.7.4.3.1�jget���M�����J�e�S���[(String, String)
                        //  B�J�e�S���[�̐e�J�e�S���[�ƂȂ铊�M�����J�e�S���[�I�u�W�F�N�g���擾����B
                        //  ���M�����J�e�S���[�I�u�W�F�N�g���擾����B 
                        //  [get���M�����J�e�S���[�ɓn������] 
                        //     �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h 
                        //     ���M�����J�e�S���[�R�[�h��A�J�e�S���[.get�e�J�e�S���[�R�[�h( )
                        WEB3MutualProductCategory l_l_mutualProductCategoryB =
                            l_mfProductManager.getMutualFundProductCategory(
                                l_strInstitutionCode,
                                l_strParaentCategoryCodeA);
                        if (l_l_mutualProductCategoryB != null)
                        {
                            //1.7.4.3.2�jget�e�J�e�S���[�R�[�h( )
                            String l_strParentCategoryCodeB = 
                                l_l_mutualProductCategoryB.getParentCategoryCode();
                            log.debug("get�e�J�e�S���[�R�[�hB( ) =" + l_strParentCategoryCodeB);
                            //��A�J�e�S���[�AB�J�e�S���[���擾�ł����ꍇ 
                            //�@@�����M�����J�e�S���[�R�[�h�P�@@��B�J�e�S���[.get�J�e�S���[�R�[�h( )
                            l_mutualDisplayOrderGroup.categoryCode1 = 
                                l_l_mutualProductCategoryB.getCategoryCode();
                            //�@@�����M�����J�e�S���[���̂P�@@ ��B�J�e�S���[.get�J�e�S���[����( ) 
                            l_mutualDisplayOrderGroup.categoryName1 = 
                                l_l_mutualProductCategoryB.getCategoryName();
                            //�@@�����M�����J�e�S���[�R�[�h�Q�@@��A�J�e�S���[.get�J�e�S���[�R�[�h( ) 
                            l_mutualDisplayOrderGroup.categoryCode2 = 
                                l_mutualProductCategoryA.getCategoryCode();
                            //�@@�����M�����J�e�S���[���̂Q�@@ ��A�J�e�S���[.get�J�e�S���[����( ) 
                            l_mutualDisplayOrderGroup.categoryName2 = 
                                l_mutualProductCategoryA.getCategoryName();
                            //�@@�����M�����J�e�S���[�R�[�h�R�@@��null 
                            l_mutualDisplayOrderGroup.categoryCode3 = null;
                            //�@@�����M�����J�e�S���[���̂R�@@ ��null
                            l_mutualDisplayOrderGroup.categoryName3 = null;
                            //1.7.4.3.3�jB�J�e�S���[.get�e�J�e�S���[�R�[�h( )�̖߂�l!=null�̏ꍇ
                            if (l_strParentCategoryCodeB != null)
                            {
                                //1.7.4.3.3.1�jget���M�����J�e�S���[(String, String)
                                //  �擾�������M�����J�e�S���[�I�u�W�F�N�g������"C�J�e�S���["�Ƃ���
                                //  ���M�����J�e�S���[�I�u�W�F�N�g���擾����B 
                                // [get���M�����J�e�S���[�ɓn������] 
                                // �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h 
                                // ���M�����J�e�S���[�R�[�h��B�J�e�S���[.get�e�J�e�S���[�R�[�h( )
                                WEB3MutualProductCategory 
                                    l_l_mutualProductCategoryC =
                                        l_mfProductManager.getMutualFundProductCategory(
                                            l_strInstitutionCode,
                                            l_strParentCategoryCodeB);
                                if (l_l_mutualProductCategoryC != null)
                                {
                                    //1.7.4.3.3.2�jget�e�J�e�S���[�R�[�h( )
                                    String l_strParentCategoryCodeC = 
                                        l_l_mutualProductCategoryC.getParentCategoryCode();
                                    log.debug("get�e�J�e�S���[�R�[�hC( ) =" + l_strParentCategoryCodeC);
                                    //��A�AB�AC�S�ẴJ�e�S���[���擾�ł����ꍇ 
                                    //�@@�����M�����J�e�S���[�R�[�h�P�@@��C�J�e�S���[.get�J�e�S���[�R�[�h( )
                                    l_mutualDisplayOrderGroup.categoryCode1 = 
                                        l_l_mutualProductCategoryC.getCategoryCode();
                                    //�@@�����M�����J�e�S���[���̂P�@@ ��C�J�e�S���[.get�J�e�S���[����( )
                                    l_mutualDisplayOrderGroup.categoryName1 = 
                                        l_l_mutualProductCategoryC.getCategoryName();
                                    //�@@�����M�����J�e�S���[�R�[�h�Q�@@��B�J�e�S���[.get�J�e�S���[�R�[�h( )
                                    l_mutualDisplayOrderGroup.categoryCode2 = 
                                        l_l_mutualProductCategoryB.getCategoryCode();
                                    //�@@�����M�����J�e�S���[���̂Q�@@ ��B�J�e�S���[.get�J�e�S���[����( ) 
                                    l_mutualDisplayOrderGroup.categoryName2 = 
                                        l_l_mutualProductCategoryB.getCategoryName();
                                    //�@@�����M�����J�e�S���[�R�[�h�R�@@��A�J�e�S���[.get�J�e�S���[�R�[�h( ) 
                                    l_mutualDisplayOrderGroup.categoryCode3 = 
                                        l_mutualProductCategoryA.getCategoryCode();
                                    //�@@�����M�����J�e�S���[���̂R�@@ ��A�J�e�S���[.get�J�e�S���[����( )
                                    l_mutualDisplayOrderGroup.categoryName3 = 
                                        l_mutualProductCategoryA.getCategoryName();
                                }            
                            }        
                        }
                    }
                }
            }
            l_lismfProductRentn.add(l_mutualDisplayOrderGroup);
        }
        
        //1.7.6�jsort���M�����ꗗ����(���M�Ǘ��Җ����\�������o�^�ꗗ�s[ ], ���M�\�[�g�L�[[ ])
        //  ���N�G�X�g�œn���ꂽ�\�[�g�L�[�ɉ��������ɕ��בւ����s���B 
        //  [sort���M�����ꗗ���ׂɓn������] 
        //�@@  ���M�������ׁ��u���M�������ׂ̍쐬�v�ō쐬���� 
        //�@@  ���M�Ǘ��Җ����\�������o�^�ꗗ�s�I�u�W�F�N�g�̔z�� s
        //�@@  �\�[�g�L�[�����N�G�X�g�f�[�^.���M�\�[�g�L�[
        WEB3MutualDisplayOrderGroup[] l_mfDisplayOrderGroup =
            new WEB3MutualDisplayOrderGroup[l_lismfProductRentn.size()];
        l_lismfProductRentn.toArray(l_mfDisplayOrderGroup);
        
        //1.9) create���X�|���X( )(
        WEB3AdminMutualDisplayOrderInputResponse l_response =
            (WEB3AdminMutualDisplayOrderInputResponse) l_request.createResponse();
        l_response.displayOrderGroups = 
            this.sortMutualProductList(l_mfDisplayOrderGroup, l_request.sortKeys);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (submit�����\�������o�^)<BR>
     * ���M�Ǘ��Җ����\�������o�^���������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�Ǘ��Җ����\�������o�^�����v�Q��<BR>
     * --------------------------------------------------<BR>
     * �P�j���̓`�F�b�N<BR>
     * �@@  ���N�G�X�g�f�[�^.validate( )���R�[������B<BR>
     * <BR>
     * �Q�j���������̐ݒ�<BR>
     * �@@ ���M������ԊǗ�.setBusinessTimestamp( )���R�[������B<BR>
     * <BR>
     * �R�j�Ǘ��҃I�u�W�F�N�g�̎擾<BR>
     * �@@�R�|�P�j�Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B<BR>
     * �@@�R�|�Q�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B<BR>
     * �@@�R�|�R�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����p�X���[�h( )���R�[������B<BR>
     * <BR>
     * �S�j�������̍X�V����<BR>
     * �@@  ���N�G�X�g�f�[�^.�����\�������X�V�l�ꗗ�̌������A�ȉ����J��Ԃ��B<BR>
     * �@@�S�|�P�j�g�����M�����}�l�[�W��.get�X�V�p���M����( )���R�[������B<BR>
     * �@@�S�|�Q�j�S�|�P�j�Ŏ擾�����g�����M�����I�u�W�F�N�g.getDataSourceObject( )<BR>
     *          ���R�[������B<BR>
     * �@@�S�|�R�j�擾����DataSourceObject�𓊐M�����}�X�^Params�ɃL���X�g���A<BR>
     * �@@�@@�@@�@@  �����Ɉȉ��Ƀv���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@  ���\������<BR>
     * �@@�@@�@@�@@  ���ŏI�X�V�҃R�[�h<BR>
     * �@@�@@�@@�@@  ���X�V���t<BR>
     * �@@�S�|�S�j�ݒ肵�����M�����}�X�^Params�I�u�W�F�N�g<BR>
     *           �̒l�Łu���M�����}�X�^�[�e�[�u���v��<BR>
     * �@@�@@�@@�@@  Update�������s���B<BR>
     * <BR>
     * �T�j���X�|���X�f�[�^��ԋp����B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�Ǘ��Җ����\�������o�^�������N�G�X�g�I�u�W�F�N�g
     * @@return WEB3AdminMutualDisplayOrderCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F9B7021B
     */
    protected WEB3AdminMutualDisplayOrderCompleteResponse submitMutualDisplayOrderRegistr(
        WEB3AdminMutualDisplayOrderCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitMutualDisplayOrderRegistr(" +
            "WEB3AdminMutualDisplayOrderCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^.validate( )���R�[������B 
        l_request.validate();
        
        //1.3�j�Ǘ��҃I�u�W�F�N�g�̎擾 
        //  �Ǘ��҃N���X.getInstanceFrom���O�C�����( )���R�[������B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4�j�擾�����Ǘ��҃I�u�W�F�N�g.validate����( )���R�[������B
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS, true);
        
        //1.5�jvalidate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6�j�擾�����Ǘ��҃I�u�W�F�N�g���،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7�jget�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();
        
        //1.8�j�J��Ԃ�����
        // ���N�G�X�g�f�[�^.�����\�������X�V�l�ꗗ�̌�����
        //  �J��Ԃ��������s���B
        for (int i = 0; i < l_request.displayOrderChangeList.length; i++)
        {
            //1.8.1�j���M����Params�I�u�W�F�N�g�̎擾
            //1.8.1.1�jget�X�V�p���M����(String, String)
            // [get�X�V�p���M�����ɓn������] 
            //�@@   �،���ЃR�[�h���Ǘ��҃I�u�W�F�N�g����擾�����،���ЃR�[�h 
            //�@@   �����R�[�h�����N�G�X�g�f�[�^.�����\�������X�V�l�ꗗ.�����R�[�h
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mfProductManager =
                (WEB3MutualFundProductManager) l_tradingModule.getProductManager();
            WEB3MutualFundProduct l_mutualFundProduct =
                l_mfProductManager.getUpdateMutualFundTradedProduct(
                    l_strInstitutionCode,
                    l_request.displayOrderChangeList[i].mutualProductCode);
        
            //1.8.1.2�j���擾�������M�����I�u�W�F�N�g���DataSourceObject���擾��
            // ���M�����}�X�^Params�N���X�ŃL���X�g����B
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_mutualFundProduct.getDataSourceObject();
            MutualFundProductParams l_mfProductParams = 
                new MutualFundProductParams(l_mfProductRow);
            //�����M�����}�X�^Params�I�u�W�F�N�g�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //�@@�\�����ʁ����N�G�X�g�f�[�^.�����\�������X�V�l�ꗗ.�\����
            if (l_request.displayOrderChangeList[i].displayOrder == null)
            {
                l_mfProductParams.setIndicationRanking(null);
            }
            else
            {
                l_mfProductParams.setIndicationRanking(
                    Integer.parseInt(l_request.displayOrderChangeList[i].displayOrder));
            }
            //�@@�ŏI�X�V�҃R�[�h���擾�����Ǘ��҃R�[�h
            l_mfProductParams.setLastUpdater(l_strAdministratorCode);
            //�@@�X�V���t�i�I�����C���j��GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
            l_mfProductParams.setOnlineUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            try
            {
                //1.8.1.3�jgetDefaultProcessor( )
                QueryProcessor l_queryProcessor = 
                    Processors.getDefaultProcessor();
                
                //1.8.1.4�jdoUpdateQuery(Row)
                l_queryProcessor.doUpdateQuery(l_mfProductParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //1.9�jcreate���X�|���X( )
        WEB3AdminMutualDisplayOrderCompleteResponse l_response =
            (WEB3AdminMutualDisplayOrderCompleteResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (sort���M�����ꗗ����)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â��ē��M�������ׂ̃\�[�g���s���B <BR>
     * <BR>
     * �P)�@@ArrayList���쐬 <BR>
     * <BR>
     * �Q)�@@����:�\�[�g�L�[�̔z�񐔕�Loop���� <BR>
     * <BR>
     * �@@�Q�|�P)����:�\�[�g�L�[.�L�[���ڂ��擾 <BR>
     * <BR>
     * �@@�Q�|�Q)����:�\�[�g�L�[.����/�~�����擾 <BR>
     * <BR>
     * �@@�Q�|�R)�L�[���ڂɂ�镪�򏈗� <BR>
     * �@@�@@  �L�[���ڂ����ݕ\�����ł������ꍇ�A���ݕ\����Comparator�𐶐� <BR>
     * �@@�@@  [�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@  �L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐� <BR>
     * �@@�@@  [�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@  �L�[���ڂ����M��������R�[�h�ł������ꍇ�A<BR>
     *       ���M��������R�[�hComparator�𐶐� <BR>
     * �@@�@@  [�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@  �L�[���ڂ����M�����J�e�S���[�R�[�h�P�ł������ꍇ�A<BR>
     * �@@    ���M�����J�e�S���[�R�[�h�PComparator�𐶐� <BR>
     * �@@�@@  [�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@  �L�[���ڂ����M�����J�e�S���[�R�[�h�Q�ł������ꍇ�A<BR>
     * �@@    ���M�����J�e�S���[�R�[�h�QComparator�𐶐� <BR>
     * �@@�@@  [�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�@@  �L�[���ڂ����M�����J�e�S���[�R�[�h�R�ł������ꍇ�A<BR>
     * �@@    ���M�����J�e�S���[�R�[�h�RComparator�𐶐� <BR>
     * �@@    [�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] <BR>
     * <BR>
     * �@@�Q�|�S)�@@�Q�|�R)�ɂč쐬����Comparator��ArrayList�ɒǉ� <BR>
     * <BR>
     * �R)�@@ArrayList����Comparator�̔z����쐬 <BR>
     * <BR>
     * �S)�@@Comparator�̔z�񏇂̃\�[�g���� <BR>
     *      (web3-common)WEB3ArraysUtility.sort(<BR>
     *      ����:���M�������ׁAComparator[]) <BR>
     * <BR>
     * �T)�@@�\�[�g���ꂽ���M�������ׂ̔z���ԋp
     * 
     * @@param displayOrderGroups
     *  - (���M��������)
     * @@param sortKeys - (�\�[�g�L�[)
     * @@return webbroker3.mf.message.WEB3MutualDisplayOrderGroup[]
     * @@roseuid 415799EE0153
     */
    protected WEB3MutualDisplayOrderGroup[] sortMutualProductList(
        WEB3MutualDisplayOrderGroup[] l_mfdisplayOrderGroups,
        WEB3MutualSortKey[] sortKeys)
    {
        final String STR_METHOD_NAME = 
            "sortMutualProductList(" +
            "WEB3MutualDisplayOrderGroup[] displayOrderGroups," +
            "WEB3MutualSortKey[] sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@ArrayList���쐬 
        List l_lisComparator = new Vector();
        
        //�Q)�@@����:�\�[�g�L�[�̔z�񐔕�Loop���� 
        for (int i = 0; i < sortKeys.length; i++)
        {
            //�Q�|�P)�@@����:�\�[�g�L�[.�L�[���ڂ��擾
            String l_strKeyItem = sortKeys[i].keyItem;
            
            //�Q�|�Q)�@@����:�\�[�g�L�[.����/�~�����擾 
            String l_strAscDesc = sortKeys[i].ascDesc;
        
            // �Q�|�R)�L�[���ڂɂ�镪�򏈗� 
            //�@@�L�[���ڂ����ݕ\�����ł������ꍇ�A���ݕ\����Comparator�𐶐� 
            //�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��]
            if (WEB3MFSortkeyItemDef.DISPLAY_ORDER.equals(l_strKeyItem))
            {
                WEB3MutualCurrentDisplayOrderComparator 
                    l_currentDisplayOrderComparator = 
                        new WEB3MutualCurrentDisplayOrderComparator(l_strAscDesc);
                l_lisComparator.add(l_currentDisplayOrderComparator);
            }
            //�@@�L�[���ڂ������R�[�h�ł������ꍇ�A�����R�[�hComparator�𐶐� 
            //�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��]
            if (WEB3MFSortkeyItemDef.PRODUCT_CODE.equals(l_strKeyItem))
            {
                WEB3MutualProductCodeComparator 
                    l_productCodeComparator = 
                        new WEB3MutualProductCodeComparator(l_strAscDesc);
                l_lisComparator.add(l_productCodeComparator);
            }
            //�@@ �L�[���ڂ����M��������R�[�h�ł������ꍇ�A���M��������R�[�hComparator�𐶐� 
            if (WEB3MFSortkeyItemDef.MUTUAL_ASSOC_PRODUCT_CODE.equals(l_strKeyItem))
            {
                WEB3MutualAssocProductCodeComparator 
                    l_mutualAssocProductCodeComparator = 
                        new WEB3MutualAssocProductCodeComparator(l_strAscDesc);
                l_lisComparator.add(l_mutualAssocProductCodeComparator);
            }
            //�@@�L�[���ڂ����M�����J�e�S���[�R�[�h�P�ł������ꍇ�A 
            //�@@���M�����J�e�S���[�R�[�h�PComparator�𐶐� 
            //�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��] 
            if (WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1.equals(l_strKeyItem))
            {
                WEB3MutualCategoryCode1Comparator 
                    l_mutualCategoryCode1Comparator = 
                        new WEB3MutualCategoryCode1Comparator(l_strAscDesc);
                l_lisComparator.add(l_mutualCategoryCode1Comparator);
            }
            //�@@�L�[���ڂ����M�����J�e�S���[�R�[�h�Q�ł������ꍇ�A 
            //�@@���M�����J�e�S���[�R�[�h�QComparator�𐶐� 
            //�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��]
            if (WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_2.equals(l_strKeyItem))
            {
                WEB3MutualCategoryCode2Comparator 
                    l_mutualCategoryCode2Comparator = 
                        new WEB3MutualCategoryCode2Comparator(l_strAscDesc);
                l_lisComparator.add(l_mutualCategoryCode2Comparator);
            }
            //�@@�L�[���ڂ����M�����J�e�S���[�R�[�h�R�ł������ꍇ�A 
            //�@@���M�����J�e�S���[�R�[�h�RComparator�𐶐� 
            //�@@[�R���X�g���N�^�̃p�����[�^=�Q�|�Q)�Ŏ擾��������/�~��]
            if (WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_3.equals(l_strKeyItem))
            {
                WEB3MutualCategoryCode3Comparator 
                    l_mutualCategoryCode3Comparator = 
                        new WEB3MutualCategoryCode3Comparator(l_strAscDesc);
                l_lisComparator.add(l_mutualCategoryCode3Comparator);
            }
            
            //�Q�|�S)�@@�Q�|�R)�ɂč쐬����Comparator��ArrayList�ɒǉ�
        }
            
        // �R)�@@ArrayList����Comparator�̔z����쐬
        Comparator[] l_comparator = new  Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparator);
        
        // �S)�@@Comparator�̔z�񏇂̃\�[�g����
        // (web3-common)WEB3ArraysUtility.sort(����:���M�������ׁAComparator[])
        WEB3ArraysUtility.sort(l_mfdisplayOrderGroups, l_comparator);
        
        // �T)�@@�\�[�g���ꂽ���M�������ׂ̔z���ԋp 
        return l_mfdisplayOrderGroups;

    }
}@
